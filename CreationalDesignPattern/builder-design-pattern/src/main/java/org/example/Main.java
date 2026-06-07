package org.example;

/**
 * ════════════════════════════════════════════════════════════════
 * CLIENT CODE — See how UGLY and CONFUSING this gets!
 * ════════════════════════════════════════════════════════════════
 *
 * When creating a BurgerMeal, the caller has NO IDEA what each
 * boolean parameter means without going back to read the constructor.
 *
 * This is the core problem the Builder Pattern solves.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════");
        System.out.println("  THE PROBLEM: Telescoping Constructors");
        System.out.println("═══════════════════════════════════════════════\n");

        // ─────────────────────────────────────────────────────────
        // Order 1: Simple burger — this looks fine...
        // ─────────────────────────────────────────────────────────
        BurgerMeal simpleBurger = new BurgerMeal("Veg", "Medium");
        System.out.println("Order 1 (Simple):");
        System.out.println(simpleBurger);
        System.out.println();

        // ─────────────────────────────────────────────────────────
        // Order 2: Burger with cheese — still okay...
        // ─────────────────────────────────────────────────────────
        BurgerMeal cheeseBurger = new BurgerMeal("Non-Veg", "Large", true);
        System.out.println("Order 2 (Cheese Burger):");
        System.out.println(cheeseBurger);
        System.out.println();

        // ─────────────────────────────────────────────────────────
        // Order 3: NOW THE CHAOS BEGINS 💀
        //
        // Customer wants: Non-Veg, Large, cheese, NO lettuce,
        // tomato, NO onion, extra patty, NO pepperoni, Coke
        //
        // PROBLEM: Can you tell what true, false, true, false, true, false means?
        // What if you accidentally swap two booleans?
        // The compiler WON'T catch it — it's still valid Java!
        // ─────────────────────────────────────────────────────────
        BurgerMeal customBurger = new BurgerMeal(
                "Non-Veg", // burgerType
                "Large", // size
                true, // cheese? — is this cheese or lettuce? 🤔
                false, // lettuce? — wait, or is THIS cheese?
                true, // tomato? — or is it onion?
                false, // onion? — getting confused already...
                true, // extraPatty? — or pepperoni?
                false, // pepperoni? — who remembers the order?!
                "Coke" // drink
        );
        System.out.println("Order 3 (Custom Burger — THE UGLY WAY):");
        System.out.println(customBurger);
        System.out.println();

        // ─────────────────────────────────────────────────────────
        // Order 4: COMMON BUG — swapped parameters! 🐛
        //
        // Customer wanted: cheese=YES, lettuce=NO, tomato=YES
        // Developer wrote: cheese=NO, lettuce=YES, tomato=NO (SWAPPED!)
        //
        // Java compiles fine. No error. Wrong burger delivered.
        // Customer is ANGRY. 😡
        // ─────────────────────────────────────────────────────────
        BurgerMeal buggyBurger = new BurgerMeal(
                "Veg",
                "Small",
                false, // ← BUG! This should be true (cheese)
                true, // ← BUG! This should be false (lettuce)
                false, // ← BUG! This should be true (tomato)
                true, // onion
                false, // extraPatty
                false, // pepperoni
                "Pepsi" // drink
        );
        System.out.println("Order 4 (BUGGY Burger — swapped booleans!):");
        System.out.println(buggyBurger);
        System.out.println();

        // ─────────────────────────────────────────────────────────
        // WHAT IF WE ADD A NEW TOPPING TOMORROW?
        // e.g., "jalapeno" → We need to:
        // 1. Add field to BurgerMeal
        // 2. Modify ALL 8 constructors
        // 3. Update ALL existing callers
        // 4. Pray nothing breaks 🙏
        //
        // This is NOT scalable. This is NOT maintainable.
        // This is exactly what the BUILDER PATTERN fixes.
        // ─────────────────────────────────────────────────────────

        System.out.println("═══════════════════════════════════════════════");
        System.out.println("  PROBLEMS IDENTIFIED:");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("  1. 8 constructors for 6 optional fields — EXPLOSION!");
        System.out.println("  2. true/false/true — UNREADABLE parameter lists");
        System.out.println("  3. Swapping booleans = silent bugs, no compiler help");
        System.out.println("  4. Adding new fields = modifying EVERY constructor");
        System.out.println("  5. No way to enforce constraints (e.g., Veg can't have pepperoni)");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("  SOLUTION → Builder Design Pattern (Next Gist!)");
        System.out.println("═══════════════════════════════════════════════");


        BugerMealBuilder burger = new BugerMealBuilder.BurgerBuilder("wheat" , "small").build();
    }
}
