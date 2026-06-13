package org.example.withoutDecorator;

import org.example.Pizza;

/**
 * Demonstrates the naive approach: one class per topping combination.
 *
 * With only 3 optional toppings on Margherita, we already need 8 combination classes.
 * Add a 4th topping → 16 classes. Add Pepperoni as a second base → double again.
 */
public class WithoutDecoratorDemo {

    public static void main(String[] args) {
        System.out.println("=== WITHOUT Decorator Pattern (naive subclasses) ===\n");

        Pizza plain = new PlainMargheritaPizza();
        printOrder("Customer A", plain);

        Pizza withCheese = new MargheritaWithExtraCheese();
        printOrder("Customer B", withCheese);

        Pizza withCheeseAndOlives = new MargheritaWithExtraCheeseAndOlives();
        printOrder("Customer C", withCheeseAndOlives);

        Pizza fullyLoaded = new MargheritaWithExtraCheeseOlivesAndJalapenos();
        printOrder("Customer D", fullyLoaded);

        System.out.println("\n--- The pain ---");
        System.out.println("3 toppings → 8 combination classes (+ 1 base class)");
        System.out.println("Want 'only Jalapenos'?           → MargheritaWithJalapenos");
        System.out.println("Want 'Cheese + Olives'?          → MargheritaWithExtraCheeseAndOlives");
        System.out.println("Want 'Cheese + Olives + Jalap'? → MargheritaWithExtraCheeseOlivesAndJalapenos");
        System.out.println("Add 1 new topping (e.g. Mushroom) → create 8 NEW classes for Margherita alone");
        System.out.println("Change base price from 200 → 220  → update EVERY combination class manually");
    }

    private static void printOrder(String customer, Pizza pizza) {
        System.out.println(customer + ": " + pizza.getDescription() + " — Rs. " + pizza.getCost());
    }
}
