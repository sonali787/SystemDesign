package org.example;

public class LogisticService {

    public LogisticService(String mode){
        // PROBLEM 1: Tight Coupling
        // LogisticService directly depends on the concrete classes (Air, Roads).
        // It is responsible for both CREATING the object and USING it.
        if (mode == "AIR"){
            Logistics logistics = new Air(); // Tight coupling to Air
            logistics.send();
        }else if (mode == "Road"){
            Logistics logistics = new Roads(); // Tight coupling to Roads
            logistics.send();
        }else{
            // PROBLEM 2: Open-Closed Principle Violation
            // If we want to add "Sea" logistics, we MUST modify this existing code 
            // to add another else-if block.
            System.out.println("Mode is not supporter : {}" + mode);
        }
        
    }
}
