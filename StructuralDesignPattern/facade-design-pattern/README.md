# Facade Design Pattern

## What is the Facade Pattern?

The **Facade** pattern is a structural design pattern that provides a simplified, unified interface to a complex set of classes, library, framework, or subsystem. Instead of making the client communicate with multiple subsystem components directly, the facade acts as a single point of contact that delegates the requests to the appropriate subsystem classes.

Think of it like booking a movie ticket at a theater counter. Instead of you going to the Seat Allocation section, the Payment desk, the Ticket Printing machine, and the Loyalty points counter yourself, you talk to a single cashier (the Facade). The cashier handles all these steps for you and hands you the final ticket.

---

## The Problem: Tight Coupling with Complex Subsystems

Imagine a movie ticket booking platform. To complete a booking, the client application needs to interact with several independent services:
1. **SeatReservationService:** Reserves the seat for the movie.
2. **PaymentService:** Handles payments and processes or cancels transactions.
3. **TicketService:** Generates or cancels physical/digital ticket passes.
4. **NotificationService:** Sends booking/cancellation notifications (e.g., via email).
5. **LoyalityPointService:** Manages and redeems user loyalty points.

### The naive approach — Client calling all services directly

Without a Facade, the client code becomes tightly coupled to all of these subsystems:

```
                  ┌────────────────┐
                  │  Client Code   │
                  └──────┬──┬──┬───┘
         ┌───────────────┘  │  │   └───────────────┐
         ▼                  │  │                   ▼
┌─────────────────┐         │  │          ┌─────────────────┐
│ PaymentService  │◄────────┘  │          │  TicketService  │
└─────────────────┘            ▼          └─────────────────┘
                     ┌───────────────────┐
                     │SeatReservationServ│
                     └───────────────────┘
```

### Why this breaks down

| Issue | What happens |
|-------|--------------|
| **Tight Coupling** | The client must know the details, method signatures, and correct order of invocation for every single subsystem. |
| **Code Duplication** | If another client (e.g., Web, Mobile, Admin API) wants to book a ticket, they all must duplicate the exact workflow logic. |
| **Hard to Maintain** | If any subsystem class updates its method signatures or if a new step is added (e.g., fraud check), every client using these services must be updated. |
| **High Cognitive Load** | The client has to manage transaction-like state (e.g., if ticket generation fails, they have to manually trigger seat cancellation and payment refund). |

---

## The Solution: A Unified Facade Interface

Instead of letting the client manage all these dependencies, we introduce the `MovingBookingFacade` class. The facade exposes simple, high-level methods (`bookTicket` and `cancelTicket`) and encapsulates the complex orchestration of the individual subsystem services.

```
┌────────────────┐
│  Client Code   │
└───────┬────────┘
        │
        ▼ (Simple, unified interface)
┌────────────────────────┐
│  MovingBookingFacade   │
└───────┬──┬──┬──┬───────┘
        │  │  │  │  └───────────────┐
        │  │  │  └──────────┐       │
        ▼  ▼  ▼             ▼       ▼
┌──────┐┌──────┐┌──────┐┌──────┐┌──────┐
│ Seat ││Paymnt││Ticket││Notif ││Loylty│
│Reserv││ Serv ││ Serv ││ Serv ││ Serv │
└──────┘└──────┘└──────┘└──────┘└──────┘
```

Now, the client only needs to communicate with `MovingBookingFacade`. All complexity, service instantiation, and workflow sequencing are hidden.

---

## Components

| Class | Role | Description |
|-------|------|-------------|
| `MovingBookingFacade` | **Facade** | Orchestrates the subsystem classes to provide simple `bookTicket` and `cancelTicket` interfaces. |
| `PaymentService` | Subsystem Class | Processes and cancels payments. |
| `SeatReservationService` | Subsystem Class | Reserves and cancels seats. |
| `TicketService` | Subsystem Class | Generates and cancels tickets. |
| `NotificationService` | Subsystem Class | Sends out notifications to users. |
| `LoyalityPointService` | Subsystem Class | Manages customer loyalty points. |
| `Main` | Client | Interacts with the subsystem only through the Facade. |

---

## Code Walkthrough

### 1. The Subsystem Services

These are simple classes representing different parts of the movie booking subsystem:

```java
// PaymentService.java
public class PaymentService {
    public void processPayment(String movieId, double amount, String accountId) {
        System.out.println("Payment processed for movie " + movieId + " Account Id " + accountId + " Amount " + amount);
    }
    public void cancelPayment(String movieId, double amount, String accountId) {
        System.out.println("Payment cancelled for movie " + movieId + " Account Id " + accountId + " Amount " + amount);
    }
}
```

### 2. The Facade

The `MovingBookingFacade` encapsulates all the subsystems and coordinates them:

```java
// MovingBookingFacade.java
public class MovingBookingFacade {
    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private TicketService ticketService;
    private NotificationService notificationService;
    private LoyalityPointService loyalityPointService;

    public MovingBookingFacade() {
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.ticketService = new TicketService();
        this.notificationService = new NotificationService();
        this.loyalityPointService = new LoyalityPointService();
    }

    public void bookTicket(String movieId, String seatId, String paymentMethod, int loyaltyPoints, double amount,
                           String email, String message, String userId) {
        // Sequenced workflow execution
        paymentService.processPayment(movieId, amount, paymentMethod);
        seatReservationService.reserveSeat(seatId, movieId);
        ticketService.generateTicket(movieId, seatId);
        notificationService.sendNotification(email, message);
        loyalityPointService.redeemLoyalityPoints(userId, loyaltyPoints);
    }

    public void cancelTicket(String movieId, String seatId, String paymentMethod, int loyaltyPoints, double amount,
                             String email, String message, String userId) {
        // Rollback / cancellation sequence
        paymentService.cancelPayment(movieId, amount, paymentMethod);
        seatReservationService.cancelSeat(seatId, movieId);
        ticketService.cancelTicket(movieId, seatId);
        notificationService.sendNotification(email, message);
        loyalityPointService.redeemLoyalityPoints(userId, loyaltyPoints);
    }
}
```

### 3. The Client (`Main.java`)

Instead of instantiating five different classes and calling their methods in order, the client just does:

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Movie Booking via Facade Pattern ---");
        MovingBookingFacade bookingFacade = new MovingBookingFacade();

        // Single client call to perform the entire complex sequence
        bookingFacade.bookTicket(
            "Movie1", "Seat1", "Account1", 100, 500.00, 
            "Email1", "Booking Confirmation", "User1"
        );
    }
}
```

---

## When to Use the Facade Pattern

*   **To provide a simple interface** to a complex subsystem.
*   **To reduce dependencies** between client classes and subsystems, fostering looser coupling.
*   **To layer your subsystems.** Use a facade to define entry points to each subsystem level, allowing them to communicate only through facades.

## Benefits

*   **Shields clients** from the complexity of subsystem components.
*   **Promotes weak coupling** between the subsystem and its clients.
*   **Centralizes orchestration logic** making code more readable, maintainable, and reusable.

## Trade-offs

*   A facade can become a **God object** (coupled to all classes of an app) if not structured carefully.

---

## Run Instructions

Execute the application using Maven from this directory:

```bash
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```
