package org.example.controller;

import org.example.services.TicketService;

public class EntryController {
    private TicketService ticketService;

    public EntryController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

}
