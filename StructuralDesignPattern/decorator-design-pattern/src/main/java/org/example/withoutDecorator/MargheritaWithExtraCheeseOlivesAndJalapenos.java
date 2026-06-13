package org.example.withoutDecorator;

import org.example.Pizza;

public class MargheritaWithExtraCheeseOlivesAndJalapenos implements Pizza {

    @Override
    public String getDescription() {
        return "Margarita Pizza, Extra Cheese, Olives, Jalapenos";
    }

    @Override
    public double getCost() {
        return 300;
    }
}
