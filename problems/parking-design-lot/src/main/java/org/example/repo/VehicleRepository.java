package org.example.repo;

import org.example.domain.Vehicle;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleRepository {
    private final Map<UUID, Vehicle> vehicles = new ConcurrentHashMap<>();

    public Optional<Vehicle> findById(UUID id) {
        return Optional.ofNullable(vehicles.get(id));
    }

    public Optional<Vehicle> findByLicencePlate(String licencePlate) {
        return vehicles.values().stream()
                .filter(v -> v.getLicencePlate().equalsIgnoreCase(licencePlate))
                .findFirst();
    }

    public Vehicle save(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
        return vehicle;
    }
}
