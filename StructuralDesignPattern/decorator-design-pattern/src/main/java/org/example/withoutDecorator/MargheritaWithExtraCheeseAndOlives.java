package org.example.withoutDecorator;

import org.example.Pizza;

public class MargheritaWithExtraCheeseAndOlives implements Pizza {

    @Override
    public String getDescription() {
        return "Margarita Pizza, Extra Cheese, Olives";
    }

    @Override
    public double getCost() {
        return 280;
    }
}
