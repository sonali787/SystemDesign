package org.example.withoutDecorator;

import org.example.Pizza;

public class MargheritaWithOlives implements Pizza {

    @Override
    public String getDescription() {
        return "Margarita Pizza, Olives";
    }

    @Override
    public double getCost() {
        return 230;
    }
}
