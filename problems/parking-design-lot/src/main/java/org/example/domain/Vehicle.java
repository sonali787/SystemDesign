package org.example.domain;

import java.util.UUID;

public class Vehicle {

    private UUID id;
    private VehicleType vehicleType;
    private String licencePlate;

    public enum VehicleType {
        CAR, BIKE, TRUCK, EV
    }

    public Vehicle(String licencePlate, VehicleType vehicleType) {
        this.id = UUID.randomUUID();
        this.vehicleType = vehicleType;
        this.licencePlate = licencePlate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vehicleType=" + vehicleType +
                ", licencePlate='" + licencePlate + '\'' +
                '}';
    }
}
