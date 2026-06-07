package org.example;

public class LogisticFactory {
    public static Logistics getLogistics(String mode){
        if (mode == "AIR"){
            return new Air(); // Tight coupling to Air

        }else if (mode == "Road"){
            return new Roads(); // Tight coupling to Roads

        }else return  new Trains();

    }
}
