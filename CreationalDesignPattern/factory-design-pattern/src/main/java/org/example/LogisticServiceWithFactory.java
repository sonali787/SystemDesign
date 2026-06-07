package org.example;

public class LogisticServiceWithFactory {

    public LogisticServiceWithFactory(String mode) {
        // SOLUTION: Factory Pattern
        // We use the factory to get the object. The service is now DECOUPLED.
        // It doesn't know about 'Air' or 'Roads' classes!
        Logistics logistics = LogisticsFactory.createLogistics(mode);
        
        if (logistics != null) {
            logistics.send();
        } else {
            System.out.println("Mode is not supported : " + mode);
        }
    }
}
