package org.example;

// Concrete implementation of Logistics
public class Air implements Logistics{
    @Override
    public  void  send(){
        System.out.println("Sending via Air");
    }
}
