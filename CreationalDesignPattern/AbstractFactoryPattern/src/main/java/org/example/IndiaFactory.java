package org.example;

public class IndiaFactory {

    public static PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType == "razorpay") {
            return new RazorpayPayment();
        } else if (gatewayType == "PayUGateway") {
            return new PayUGatewayPayment();
        } else {
            throw new IllegalArgumentException("Invalid gateway type");
        }
    }

    public static void createInvoice(){
         Invoice invoice = new GSTInvoice();
         invoice.generateInvoice();
    }
}
