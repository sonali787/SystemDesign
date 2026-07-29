package org.example.services;

import org.example.domain.ParkingSlot;
import org.example.domain.Ticket;
import org.example.domain.Vehicle;
import org.example.repo.*;

import java.util.Optional;
import java.util.UUID;

public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket generateTicket(Vehicle vehicle, UUID slotId) {
        System.out.println(
                "[SERVICE] Generating new ticket for vehicle " + vehicle.getId() + " " + vehicle.getLicencePlate());

        if (ticketRepository.findById(vehicle.getId()).isPresent()) {
            System.out.println("[SERVICE] Ticket already exists for vehicle " + vehicle.getId());
            return ticketRepository.findById(vehicle.getId()).get();
        }
        Ticket ticket = new Ticket(vehicle, slotId);
        ticketRepository.save(ticket);
        System.out.println("[SERVICE] Ticket Generated: " + ticket.getId());
        return ticket;

    }

    public Optional<Ticket> getTicket(UUID id) {
        System.out.println("[SERVICE] Fetching ticket: " + id);
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket;
    }

    public void deactivateTicket(UUID id) {
        System.out.println("[SERVICE] Deactivating ticket: " + id);
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticket.get().deactivate();
            ticketRepository.save(ticket.get());
            System.out.println("[SERVICE] Ticket Deactivated: " + id);
        } else {
            System.out.println("[SERVICE] Ticket Not Found: " + id);
        }
    }

}
