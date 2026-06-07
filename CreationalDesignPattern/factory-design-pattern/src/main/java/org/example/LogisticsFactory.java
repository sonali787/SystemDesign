package org.example;

public class LogisticsFactory {

    // Centralized object creation logic
    // We move the "if-else" out of the service and into this factory.
    public static Logistics createLogistics(String mode) {
        if (mode.equalsIgnoreCase("AIR")) {
            return new Air();
        } else if (mode.equalsIgnoreCase("ROAD")) {
            return new Roads();
        } else if (mode.equalsIgnoreCase("TRAIN")) {
            return new Trains();
        }
        
        // If we want to add "Sea", we only modify THIS factory class,
        // leaving our LogisticServiceWithFactory completely untouched!
        return null; 
    }
}
