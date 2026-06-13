package org.example.withoutDecorator;

import org.example.Pizza;

/**
 * Base pizza — the only class that does NOT duplicate topping logic.
 */
public class PlainMargheritaPizza implements Pizza {

    @Override
    public String getDescription() {
        return "Margarita Pizza";
    }

    @Override
    public double getCost() {
        return 200;
    }
}
