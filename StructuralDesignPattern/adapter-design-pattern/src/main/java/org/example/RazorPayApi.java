package org.example;

public class RazorPayApi {

    public void makePayment(String invoiceId,double amount){
        System.out.println("Paid Rs. " + amount + " Using RazorPay for order " + invoiceId);

    }
}
