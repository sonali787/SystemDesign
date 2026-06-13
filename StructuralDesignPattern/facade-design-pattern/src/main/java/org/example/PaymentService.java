package org.example;

public class PaymentService {
    public void processPayment(String movieId, double amount, String accountId) {
        System.out.println("Payment processed for movie " + movieId + " Account Id " + accountId + " Amount " + amount);
    }

    public void cancelPayment(String movieId, double amount, String accountId) {
        System.out.println("Payment cancelled for movie " + movieId + " Account Id " + accountId + " Amount " + amount);
    }
}
