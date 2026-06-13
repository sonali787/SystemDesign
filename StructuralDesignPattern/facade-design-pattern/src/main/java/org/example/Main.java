package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Movie Booking via Facade Pattern ---");
        
        MovingBookingFacade bookingFacade = new MovingBookingFacade();

        // Construct the booking request using the Builder pattern to handle many parameters
        BookingRequest request = new BookingRequest.Builder()
                .setMovieId("Movie1")
                .setSeatId("Seat1")
                .setPaymentMethod("Account1")
                .setLoyaltyPoints(100)
                .setAmount(500.00)
                .setEmail("Email1")
                .setMessage("Booking Confirmation")
                .setUserId("User1")
                .build();

        // Client interacts only with the Facade interface passing the single request object
        bookingFacade.bookTicket(request);

        System.out.println("\n--- Canceling booking via Facade Pattern ---");
        bookingFacade.cancelTicket(request);
    }
}
