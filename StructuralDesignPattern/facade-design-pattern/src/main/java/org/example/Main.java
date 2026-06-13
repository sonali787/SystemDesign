package org.example;

public class Main {
    public static void main(String[] args) {
        MovingBookingFacade bookingFacade = new MovingBookingFacade();

        System.out.println("==================================================");
        System.out.println("PHASE 1: Pure Facade Pattern (Direct Arguments)");
        System.out.println("==================================================");

        // Call booking with direct parameters (Old method)
        bookingFacade.bookTicket(
                "Movie1",
                "Seat1",
                "Account1",
                100,
                500.00,
                "Email1",
                "Booking Confirmation - Phase 1",
                "User1"
        );

        System.out.println("\n--- Canceling booking (Phase 1) ---");
        bookingFacade.cancelTicket(
                "Movie1",
                "Seat1",
                "Account1",
                100,
                500.00,
                "Email1",
                "Booking Confirmation - Phase 1",
                "User1"
        );

        System.out.println("\n==================================================");
        System.out.println("PHASE 2: Facade + Builder Pattern (Request Object)");
        System.out.println("==================================================");

        // Construct request using Builder
        BookingRequest request = new BookingRequest.Builder()
                .setMovieId("Movie1")
                .setSeatId("Seat1")
                .setPaymentMethod("Account1")
                .setLoyaltyPoints(100)
                .setAmount(500.00)
                .setEmail("Email1")
                .setMessage("Booking Confirmation - Phase 2")
                .setUserId("User1")
                .build();

        // Call booking with Request object (New method)
        bookingFacade.bookTicket(request);

        System.out.println("\n--- Canceling booking (Phase 2) ---");
        bookingFacade.cancelTicket(request);
    }
}

