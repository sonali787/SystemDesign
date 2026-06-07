package org.example;

// Concrete implementation of Logistics for Trains
public class Trains implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending via Trains");
    }
}
