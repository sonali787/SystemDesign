package org.example;

public class TicketService {
    public void generateTicket(String movieId, String seatNumber) {
        System.out.println("Ticket generated for movie " + movieId + " Seat Number " + seatNumber);
    }

    public void cancelTicket(String movieId, String seatNumber) {
        System.out.println("Ticket cancelled for movie " + movieId + " Seat Number " + seatNumber);
    }
}
