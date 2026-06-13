package org.example;

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
