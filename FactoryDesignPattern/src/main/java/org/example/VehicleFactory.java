package org.example;

public class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("CAR")) {
            return new Car();
        }
        if (type.equalsIgnoreCase("BIKE"))
            return new Bike();

        if (type.equalsIgnoreCase("BUS"))
            return new Bus();

        throw new IllegalArgumentException("Invalid vehicle type");
    }
}
