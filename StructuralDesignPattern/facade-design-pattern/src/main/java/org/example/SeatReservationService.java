package org.example;

public class SeatReservationService {
    public void reserveSeat(String seatNumber, String movieId) {
        System.out.println("Seat reserved" + seatNumber + " Movie id" + movieId);
    }

    public void cancelSeat(String seatNumber, String movieId) {
        System.out.println("Seat cancelled");
    }
}
