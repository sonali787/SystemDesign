package org.example;

/** Concrete Decorator — wraps any {@link Pizza} and adds extra cheese (+50). */
public class ExtraCheese extends PizzaDecorator {

    public ExtraCheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 50;
    }
}
