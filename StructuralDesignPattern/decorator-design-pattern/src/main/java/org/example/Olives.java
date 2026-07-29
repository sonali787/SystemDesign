package org.example;

/** Concrete Decorator — wraps any {@link Pizza} and adds olives (+30). */
public class Olives extends PizzaDecorator {

    public Olives(Pizza pizza){
        super(pizza);
    }
    @Override
    public String getDescription() {
        return pizza.getDescription() + " , Extra Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 30;
    }
}
