package org.example.controller;

import java.lang.classfile.ClassFile.Option;
import java.util.Optional;
import java.util.UUID;

import org.example.domain.ParkingSlot;
import org.example.domain.Ticket;
import org.example.domain.Vehicle;
import org.example.services.SlotService;
import org.example.services.TicketService;

public class EntryController {
    private TicketService ticketService;
    private SlotService slotService;

    public EntryController(TicketService ticketService, SlotService slotService) {
        this.ticketService = ticketService;
        this.slotService = slotService;
        System.out.println("[CONTROLLER] EntryController initialized");

    }

    public EntryResult enterVehicle(String lisencePlate, Vehicle.VehicleType vehicleType) {
        System.out.println("[CONTROLLER] ==============> Entry of Vehicle with plate " + lisencePlate + " requested ");

        try {
            // Create Vehicle

            Vehicle vehicle = new Vehicle(lisencePlate, vehicleType);
            System.out.println("[CONTROLLER] Vehicle created: " + vehicle.getId());

            // Allocate Slot

            Optional<ParkingSlot> slot = slotService.allocateSlot(vehicleType);

            if (!slot.isPresent()) {
                System.out.println("[CONTROLLER] No available slots for vehicle type: " + vehicleType);
                return new EntryResult(false, null, null, "No available slots for vehicleType " + vehicleType);
            }

            ParkingSlot allocatedSlot = slot.get();
            System.out.println("[CONTROLLER] Slot allocated: " + allocatedSlot.getId());

            // Generate ticket

            Ticket ticket = ticketService.generateTicket(vehicle, allocatedSlot.getId());
            System.out.println("[CONTROLLER] Ticket Generated: " + ticket.getId());

            return new EntryResult(true, ticket.getId(), allocatedSlot.getId(), "Vehicle entered successfully");

        } catch (Exception e) {
            System.out.println("[CONTROLLER] Error: " + e.getMessage());
            return new EntryResult(false, null, null, "Error: " + e.getMessage());
        }

    }

    public static class EntryResult {
        private final boolean success;
        private final UUID ticketId;
        private final UUID slotId;
        private final String message;

        public EntryResult(boolean success, UUID ticketId, UUID slotId, String message) {
            this.success = success;
            this.ticketId = ticketId;
            this.slotId = slotId;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public UUID getTicketId() {
            return ticketId;
        }

        public UUID getSlotId() {
            return slotId;
        }

        public String getMessage() {
            return message;
        }
    }

}
