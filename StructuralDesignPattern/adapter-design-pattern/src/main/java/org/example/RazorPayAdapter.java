package org.example;

public class RazorPayAdapter implements PaymentGateway{

    @Override
    public void pay(String orderId, double amount) {
        RazorPayApi razorPayApi = new RazorPayApi();
        razorPayApi.makePayment(orderId,amount);
    }
}
