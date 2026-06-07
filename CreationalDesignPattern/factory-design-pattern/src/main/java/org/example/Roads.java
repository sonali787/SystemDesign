package org.example;

// Concrete implementation of Logistics
public class Roads implements Logistics{
    @Override
    public void send() {
        System.out.println("Sending via Roads");
    }
}
