package org.example.repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.example.domain.ParkingSlot;
import org.example.domain.Vehicle;

public class SlotRepository {
    private Map<UUID, ParkingSlot> slots = new ConcurrentHashMap<>();

    public ParkingSlot save(ParkingSlot slot) {
        slots.put(slot.getId(), slot);
        return slot;
    }

    public Optional<ParkingSlot> findById(UUID slotId) {
        return Optional.ofNullable(slots.get(slotId));
    }

    public List<ParkingSlot> findAvailableSlots(Vehicle.VehicleType vehicleType) {
        return slots.values().stream()
                .filter(slot -> slot.getSlots() == vehicleType && !slot.isOccupied())
                .collect(Collectors.toList());
    }

    public Optional<ParkingSlot> allocateSlot(Vehicle.VehicleType vehicleType) {
        return slots.values().stream()
                .filter(slot -> slot.getSlots() == vehicleType && !slot.isOccupied())
                .findFirst()
                .map(slot -> {
                    slot.setOccupied(true);
                    return slot;
                });
    }

    public void releaseSlot(UUID slotId) {
        slots.computeIfPresent(slotId, (id, slot) -> {
            slot.setOccupied(false);
            return slot;
        });
    }

    public Map<Vehicle.VehicleType, Long> getSlotStatistics() {
        return slots.values().stream()
                .collect(Collectors.groupingByConcurrent(
                        ParkingSlot::getSlots,
                        Collectors.counting()));
    }

    public void clear() {
        slots.clear();
    }
}