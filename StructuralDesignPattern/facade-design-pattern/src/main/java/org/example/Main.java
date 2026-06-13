package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Movie Booking via Facade Pattern ---");

        MovingBookingFacade bookingFacade = new MovingBookingFacade();

        // Client interacts only with the Facade interface
        bookingFacade.bookTicket(
                "Movie1",
                "Seat1",
                "Account1",
                100,
                500.00,
                "Email1",
                "Booking Confirmation",
                "User1");

        System.out.println("\n--- Canceling booking via Facade Pattern ---");
        bookingFacade.cancelTicket(
                "Movie1",
                "Seat1",
                "Account1",
                100,
                500.00,
                "Email1",
                "Booking Confirmation",
                "User1");
    }
}
