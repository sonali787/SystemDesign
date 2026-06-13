package org.example;

/**
 * Facade class that simplifies interactions with the complex movie booking subsystem.
 * It provides a single point of contact for the client to book or cancel tickets,
 * orchestrating the underlying Payment, Seat Reservation, Ticket, Notification,
 * and Loyalty Point services.
 */
public class MovingBookingFacade {

    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private TicketService ticketService;
    private NotificationService notificationService;
    private LoyalityPointService loyalityPointService;

    /**
     * Initializes all subsystem services.
     */
    public MovingBookingFacade() {
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.ticketService = new TicketService();
        this.notificationService = new NotificationService();
        this.loyalityPointService = new LoyalityPointService();

    }

    /**
     * Orchestrates the complete workflow to book a movie ticket.
     */
    public void bookTicket(String movieId, String seatId, String paymentMethod, int loyalityPoint, double amount,
            String email, String message, String userId) {

        paymentService.processPayment(movieId, amount, paymentMethod);
        seatReservationService.reserveSeat(seatId, movieId);
        ticketService.generateTicket(movieId, seatId);
        notificationService.sendNotification(email, message);
        loyalityPointService.redeemLoyalityPoints(userId, loyalityPoint);
    }

    /**
     * Orchestrates the workflow to cancel a movie ticket booking.
     */
    public void cancelTicket(String movieId, String seatId, String paymentMethod, int loyalityPoint, double amount,
            String email, String message, String userId) {

        paymentService.cancelPayment(movieId, amount, paymentMethod);
        seatReservationService.cancelSeat(seatId, movieId);
        ticketService.cancelTicket(movieId, seatId);
        notificationService.sendNotification(email, message);

    }

}

