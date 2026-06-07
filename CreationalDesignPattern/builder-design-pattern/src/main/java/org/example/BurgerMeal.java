package org.example;

/**
 * ════════════════════════════════════════════════════════════════
 * PROBLEM: Telescoping Constructor Anti-Pattern
 * ════════════════════════════════════════════════════════════════
 *
 * A BurgerMeal has MANY optional ingredients:
 * - Some customers want cheese, some don't.
 * - Some want extra patty, some don't.
 * - Some want lettuce + tomato but NO onion.
 *
 * To handle every combination, we end up creating a CHAIN of
 * constructors where each one adds one more parameter.
 * This is called the "Telescoping Constructor" anti-pattern.
 *
 * Problems this causes:
 * 1. Too many constructors — hard to maintain.
 * 2. Positional confusion — what does `true, false, true` mean?
 * 3. No readability — caller can't tell which param is which.
 * 4. Fragile code — adding a new topping means editing ALL constructors.
 */
public class BurgerMeal {

    // ─── Required fields ───
    private String burgerType; // "Veg" or "Non-Veg" (always required)
    private String size; // "Small", "Medium", "Large" (always required)

    // ─── Optional fields (toppings & extras) ───
    private boolean cheese;
    private boolean lettuce;
    private boolean tomato;
    private boolean onion;
    private boolean extraPatty;
    private boolean pepperoni;
    private String drink; // null means no drink

    // ══════════════════════════════════════════════════════════════
    // TELESCOPING CONSTRUCTORS — this is the PROBLEM
    // ══════════════════════════════════════════════════════════════

    // Constructor 1: Only required fields
    public BurgerMeal(String burgerType, String size) {
        this(burgerType, size, false, false, false, false, false, false, null);
    }

    // Constructor 2: required + cheese
    public BurgerMeal(String burgerType, String size, boolean cheese) {
        this(burgerType, size, cheese, false, false, false, false, false, null);
    }

    // Constructor 3: required + cheese + lettuce
    public BurgerMeal(String burgerType, String size, boolean cheese, boolean lettuce) {
        this(burgerType, size, cheese, lettuce, false, false, false, false, null);
    }

    // Constructor 4: required + cheese + lettuce + tomato
    public BurgerMeal(String burgerType, String size, boolean cheese, boolean lettuce, boolean tomato) {
        this(burgerType, size, cheese, lettuce, tomato, false, false, false, null);
    }

    // Constructor 5: required + cheese + lettuce + tomato + onion
    public BurgerMeal(String burgerType, String size, boolean cheese, boolean lettuce,
            boolean tomato, boolean onion) {
        this(burgerType, size, cheese, lettuce, tomato, onion, false, false, null);
    }

    // Constructor 6: required + cheese + lettuce + tomato + onion + extraPatty
    public BurgerMeal(String burgerType, String size, boolean cheese, boolean lettuce,
            boolean tomato, boolean onion, boolean extraPatty) {
        this(burgerType, size, cheese, lettuce, tomato, onion, extraPatty, false, null);
    }

    // Constructor 7: required + cheese + lettuce + tomato + onion + extraPatty +
    // pepperoni
    public BurgerMeal(String burgerType, String size, boolean cheese, boolean lettuce,
            boolean tomato, boolean onion, boolean extraPatty, boolean pepperoni) {
        this(burgerType, size, cheese, lettuce, tomato, onion, extraPatty, pepperoni, null);
    }

    // Constructor 8: THE FULL MONSTER — all fields
    public BurgerMeal(String burgerType, String size, boolean cheese, boolean lettuce,
            boolean tomato, boolean onion, boolean extraPatty, boolean pepperoni,
            String drink) {
        this.burgerType = burgerType;
        this.size = size;
        this.cheese = cheese;
        this.lettuce = lettuce;
        this.tomato = tomato;
        this.onion = onion;
        this.extraPatty = extraPatty;
        this.pepperoni = pepperoni;
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "🍔 BurgerMeal {" +
                "\n   burgerType = '" + burgerType + '\'' +
                ",\n   size       = '" + size + '\'' +
                ",\n   cheese     = " + cheese +
                ",\n   lettuce    = " + lettuce +
                ",\n   tomato     = " + tomato +
                ",\n   onion      = " + onion +
                ",\n   extraPatty = " + extraPatty +
                ",\n   pepperoni  = " + pepperoni +
                ",\n   drink      = '" + drink + '\'' +
                "\n}";
    }
}
