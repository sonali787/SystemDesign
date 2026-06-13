package org.example.withoutDecorator;

import org.example.Pizza;

public class MargheritaWithJalapenos implements Pizza {

    @Override
    public String getDescription() {
        return "Margarita Pizza, Jalapenos";
    }

    @Override
    public double getCost() {
        return 220;
    }
}
