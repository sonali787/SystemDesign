package org.example.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.example.domain.Ticket;
import org.example.services.TicketService;

public class ExitController {
    private TicketService ticketService;

    public ExitController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void exitVehicle(Ticket ticket) {
        System.out.println("[CONTROLLER] ==============> Exit of Vehicle with ticket " + ticket.getId() + " requested");

        try {

            // ticket scanning

            LocalDateTime entryTime = ticket.getEntryTime();
            LocalDateTime exitTime = LocalDateTime.now();

            Duration duration = Duration.between(entryTime, exitTime);

            System.out.println("[CONTROLLER] Vehicle exited successfully");
        } catch (Exception e) {
            System.out.println("[CONTROLLER] Error: " + e.getMessage());
        }
    }

}
