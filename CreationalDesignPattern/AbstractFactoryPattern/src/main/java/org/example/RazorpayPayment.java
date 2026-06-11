package org.example;

public class RazorpayPayment implements PaymentGateway{

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment VIA RazorPay : {}" +amount);
    }
}
