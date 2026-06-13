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
     * Phase 2: Facade + Builder (Passing a constructed Request object)
     * Orchestrates the complete workflow to book a movie ticket.
     */
    public void bookTicket(BookingRequest request) {
        System.out.println("[Facade Phase 2] Booking via Builder BookingRequest...");
        paymentService.processPayment(request.getMovieId(), request.getAmount(), request.getPaymentMethod());
        seatReservationService.reserveSeat(request.getSeatId(), request.getMovieId());
        ticketService.generateTicket(request.getMovieId(), request.getSeatId());
        notificationService.sendNotification(request.getEmail(), request.getMessage());
        loyalityPointService.redeemLoyalityPoints(request.getUserId(), request.getLoyaltyPoints());
    }

    /**
     * Phase 1: Pure Facade (Passing all arguments directly)
     * Orchestrates the complete workflow to book a movie ticket.
     */
    public void bookTicket(String movieId, String seatId, String paymentMethod, int loyalityPoint, double amount,
            String email, String message, String userId) {
        System.out.println("[Facade Phase 1] Booking via direct arguments...");
        paymentService.processPayment(movieId, amount, paymentMethod);
        seatReservationService.reserveSeat(seatId, movieId);
        ticketService.generateTicket(movieId, seatId);
        notificationService.sendNotification(email, message);
        loyalityPointService.redeemLoyalityPoints(userId, loyalityPoint);
    }

    /**
     * Phase 2: Facade + Builder (Passing a constructed Request object)
     * Orchestrates the workflow to cancel a movie ticket booking.
     */
    public void cancelTicket(BookingRequest request) {
        System.out.println("[Facade Phase 2] Cancelling via Builder BookingRequest...");
        paymentService.cancelPayment(request.getMovieId(), request.getAmount(), request.getPaymentMethod());
        seatReservationService.cancelSeat(request.getSeatId(), request.getMovieId());
        ticketService.cancelTicket(request.getMovieId(), request.getSeatId());
        notificationService.sendNotification(request.getEmail(), request.getMessage());
    }

    /**
     * Phase 1: Pure Facade (Passing all arguments directly)
     * Orchestrates the workflow to cancel a movie ticket booking.
     */
    public void cancelTicket(String movieId, String seatId, String paymentMethod, int loyalityPoint, double amount,
            String email, String message, String userId) {
        System.out.println("[Facade Phase 1] Cancelling via direct arguments...");
        paymentService.cancelPayment(movieId, amount, paymentMethod);
        seatReservationService.cancelSeat(seatId, movieId);
        ticketService.cancelTicket(movieId, seatId);
        notificationService.sendNotification(email, message);
    }

}

