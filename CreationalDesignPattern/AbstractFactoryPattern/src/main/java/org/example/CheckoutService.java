package org.example;

public class CheckoutService {

    private String gatewayType;

    public CheckoutService(String gatewayType) {
        this.gatewayType = gatewayType;
    }

    public void checkout(Float amount) {
        PaymentGateway paymentGateway = IndiaFactory.createPaymentGateway(gatewayType);
        // Create invoice based on the payment gateway type

        IndiaFactory.createInvoice();

    }
}
