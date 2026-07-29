package org.example;

/**
 * Concrete Component — the simplest base pizza with no toppings.
 * Acts as the inner-most object in a decorator chain; every topping decorator
 * ultimately delegates its cost/description call back to this (or another concrete component).
 */
public class PlainPizza implements Pizza{

    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 100;
    }
}
