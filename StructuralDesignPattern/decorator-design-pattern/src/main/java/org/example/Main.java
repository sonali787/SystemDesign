package org.example;

import org.example.withoutDecorator.WithoutDecoratorDemo;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Decorator Pattern ---\n");

        Pizza plain = new MarghrietaPizza();
        printOrder("Customer A", plain);

        Pizza withCheese = new ExtraCheese(new MarghrietaPizza());
        printOrder("Customer B", withCheese);

        // Same combo as naive MargheritaWithExtraCheeseAndOlives — composed at runtime
        // (add Olives decorator when you implement it; for now, double cheese shows stacking)
        Pizza withDoubleCheese = new ExtraCheese(new ExtraCheese(new MarghrietaPizza()));
        printOrder("Customer C", withDoubleCheese);

        System.out.println("\n3 toppings with decorator → 3 decorator classes + 1 base (not 8 combo classes)");
        System.out.println("Compose any order at runtime: new ToppingB(new ToppingA(new MarghrietaPizza()))");

        System.out.println("\n" + "=".repeat(55));
        WithoutDecoratorDemo.main(args);
    }

    private static void printOrder(String customer, Pizza pizza) {
        System.out.println(customer + ": " + pizza.getDescription() + " — Rs. " + pizza.getCost());
    }
}
