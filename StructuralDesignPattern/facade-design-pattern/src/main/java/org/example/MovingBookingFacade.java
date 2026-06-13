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
    public void bookTicket(BookingRequest request) {

        paymentService.processPayment(request.getMovieId(), request.getAmount(), request.getPaymentMethod());
        seatReservationService.reserveSeat(request.getSeatId(), request.getMovieId());
        ticketService.generateTicket(request.getMovieId(), request.getSeatId());
        notificationService.sendNotification(request.getEmail(), request.getMessage());
        loyalityPointService.redeemLoyalityPoints(request.getUserId(), request.getLoyaltyPoints());
    }

    /**
     * Orchestrates the workflow to cancel a movie ticket booking.
     */
    public void cancelTicket(BookingRequest request) {

        paymentService.cancelPayment(request.getMovieId(), request.getAmount(), request.getPaymentMethod());
        seatReservationService.cancelSeat(request.getSeatId(), request.getMovieId());
        ticketService.cancelTicket(request.getMovieId(), request.getSeatId());
        notificationService.sendNotification(request.getEmail(), request.getMessage());

    }

}

