package org.example.domain;

import java.util.List;
import java.util.UUID;

public class Floor {
    private UUID id;
    private String floorNumber;
    private List<ParkingSlot> parkingSlots;

    public Floor(String floorNumber, List<ParkingSlot> parkingSlots) {
        this.id = UUID.randomUUID();
        this.floorNumber = floorNumber;
        this.parkingSlots = parkingSlots;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", floorNumber='" + floorNumber + '\'' +
                ", parkingSlots=" + parkingSlots +
                '}';
    }
}
