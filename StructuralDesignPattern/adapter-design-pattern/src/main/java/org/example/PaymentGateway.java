package org.example;

public interface PaymentGateway {
    public void pay(String orderId, double amount);
}
