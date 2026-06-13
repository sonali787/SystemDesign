package org.example.withoutDecorator;

import org.example.Pizza;

public class MargheritaWithExtraCheeseAndJalapenos implements Pizza {

    @Override
    public String getDescription() {
        return "Margarita Pizza, Extra Cheese, Jalapenos";
    }

    @Override
    public double getCost() {
        return 270;
    }
}
