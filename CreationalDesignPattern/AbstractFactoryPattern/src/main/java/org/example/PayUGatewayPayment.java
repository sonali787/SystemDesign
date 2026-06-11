package org.example;

public class PayUGatewayPayment implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment is processed via PayU : {}"  + amount);
    }
}
