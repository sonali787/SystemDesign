package org.example.withoutDecorator;

import org.example.Pizza;

public class MargheritaWithOlivesAndJalapenos implements Pizza {

    @Override
    public String getDescription() {
        return "Margarita Pizza, Olives, Jalapenos";
    }

    @Override
    public double getCost() {
        return 250;
    }
}
