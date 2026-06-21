package org.example.domain;

import java.util.UUID;

public class ParkingSlot {
    private UUID id;
    private Vehicle.VehicleType slots;
    private boolean isOccupied;
    private int floorNumber;

    public ParkingSlot(Vehicle.VehicleType slots, boolean isOccupied, int floorNumber) {
        this.id = UUID.randomUUID();
        this.slots = slots;
        this.isOccupied = isOccupied;
        this.floorNumber = floorNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Vehicle.VehicleType getSlots() {
        return slots;
    }

    public void setSlots(Vehicle.VehicleType slots) {
        this.slots = slots;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", slots=" + slots +
                ", isOccupied=" + isOccupied +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
