package org.example;

public class PayUGateway implements PaymentGateway{

    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid Rs. " + amount + " Using PayU for order " + orderId);
    }
}
