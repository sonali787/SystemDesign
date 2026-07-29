package org.example;

/** Abstract Decorator — base class for all concrete decorators. */
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;

    }
}
