package org.example.repo;

import org.example.domain.Floor;
import org.example.domain.ParkingSlot;
import org.example.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingSlotRepository {
    private final List<Floor> floors = new CopyOnWriteArrayList<>();

    public ParkingSlotRepository() {
        initializeDefaultFloors();
    }

    private void initializeDefaultFloors() {
        // Floor 0
        List<ParkingSlot> slotsF0 = new ArrayList<>();
        slotsF0.add(new ParkingSlot(Vehicle.VehicleType.BIKE, false, 0));
        slotsF0.add(new ParkingSlot(Vehicle.VehicleType.CAR, false, 0));
        slotsF0.add(new ParkingSlot(Vehicle.VehicleType.TRUCK, false, 0));
        floors.add(new Floor("0", slotsF0));

        // Floor 1
        List<ParkingSlot> slotsF1 = new ArrayList<>();
        slotsF1.add(new ParkingSlot(Vehicle.VehicleType.BIKE, false, 1));
        slotsF1.add(new ParkingSlot(Vehicle.VehicleType.CAR, false, 1));
        slotsF1.add(new ParkingSlot(Vehicle.VehicleType.EV, false, 1));
        floors.add(new Floor("1", slotsF1));
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public Optional<ParkingSlot> findById(UUID id) {
        return floors.stream()
                .flatMap(f -> f.getParkingSlots().stream())
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public Optional<ParkingSlot> findFirstBySlotsAndIsOccupiedFalse(Vehicle.VehicleType slots) {
        for (Floor floor : floors) {
            Optional<ParkingSlot> slotOpt = floor.getParkingSlots().stream()
                    .filter(s -> s.getSlots() == slots && !s.isOccupied())
                    .findFirst();
            if (slotOpt.isPresent()) {
                return slotOpt;
            }
        }
        return Optional.empty();
    }

    public ParkingSlot save(ParkingSlot slot) {
        return slot;
    }

    public List<ParkingSlot> findAll() {
        List<ParkingSlot> slots = new ArrayList<>();
        for (Floor floor : floors) {
            slots.addAll(floor.getParkingSlots());
        }
        return slots;
    }
}
