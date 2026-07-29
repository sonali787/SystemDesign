package org.example.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private UUID slotId;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private boolean isActive;

    public Ticket(Vehicle vehicle, UUID slotId) {
        this.id = UUID.randomUUID();
        this.vehicle = vehicle;
        this.slotId = slotId;
        this.entryTime = LocalDateTime.now();
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSlotId() {
        return slotId;
    }

    public void setSlotId(UUID slotId) {
        this.slotId = slotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", slotId=" + slotId +
                ", vehicle=" + vehicle.toString() +
                ", entryTime=" + entryTime +
                ", isActive=" + isActive +
                '}';
    }
}
