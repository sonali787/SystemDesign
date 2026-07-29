package org.example;

/**
 * Concrete Component — a standalone pizza with its own fixed description and cost.
 * It is the real object being decorated; decorators wrap around it to add toppings
 * without modifying this class.
 */
public class MarghrietaPizza implements Pizza{

    @Override
    public String getDescription() {
        return "Margarita Pizza";
    }

    @Override
    public double getCost() {
        return 200;
    }
}
