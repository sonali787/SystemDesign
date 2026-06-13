package org.example.withoutDecorator;

import org.example.Pizza;

public class MargheritaWithExtraCheese implements Pizza {

    @Override
    public String getDescription() {
        return "Margarita Pizza, Extra Cheese";
    }

    @Override
    public double getCost() {
        return 250;
    }
}
