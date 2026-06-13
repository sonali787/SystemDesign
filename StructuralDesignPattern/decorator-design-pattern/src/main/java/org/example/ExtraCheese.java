package org.example;

public class ExtraCheese extends PizzaDecorator{


    public ExtraCheese(Pizza pizza){
        super(pizza);
    }
    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public double getCost() {
        return 0;
    }
}
