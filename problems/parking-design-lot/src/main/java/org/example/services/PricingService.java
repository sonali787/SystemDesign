package org.example.services;

import org.example.domain.PricingRule;
import org.example.domain.Ticket;
import org.example.repo.PricingRuleRepository;

public class PricingService {
    private PricingRuleRepository pricingRuleRepository;

    public PricingService(PricingRuleRepository pricingRuleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
    }

    public double calculatePrice(Ticket ticket) {
        System.out.println("[SERVICE] Calculating price for ticket: " + ticket.getId());

        PricingRule pricingRule = pricingRuleRepository.findByVehicleType(ticket.getVehicle().getVehicleType())
                .orElseThrow(() -> new RuntimeException(
                        "Pricing rule not found for vehicle type: " + ticket.getVehicle().getVehicleType()));

        return 0;
    }

}
