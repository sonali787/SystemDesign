package org.example;

public class BugerMealBuilder {

    private String burgerType; // "Veg" or "Non-Veg" (always required)
    private String size; // "Small", "Medium", "Large" (always required)

    // ─── Optional fields (toppings & extras) ───
    private boolean cheese;
    private boolean lettuce;
    private boolean tomato;
    private boolean onion;
    private boolean extraPatty;
    private boolean pepperoni;
    private String drink; //

    public BugerMealBuilder(BurgerBuilder burger) {
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

    public static class BurgerBuilder {

        // ----Required field
        private String burgerType; // "Veg" or "Non-Veg" (always required)
        private String size; // "Small", "Medium", "Large" (always required)

        // ─── Optional fields (toppings & extras) ───
        private boolean cheese;
        private boolean lettuce;
        private boolean tomato;
        private boolean onion;
        private boolean extraPatty;
        private boolean pepperoni;
        private String drink; //

        public BurgerBuilder(String burgerType, String size) {
            this.burgerType = burgerType;
            this.size = size;
        }

        public BurgerBuilder withCheese(boolean hasCheese) {
            this.cheese = hasCheese;
            return this;
        }

        public BurgerBuilder withLettuce(boolean withLettuce) {
            this.lettuce = withLettuce;
            return this;
        }

        public BurgerBuilder withTomato(boolean hasTomato) {
            this.tomato = hasTomato;
            return this;
        }

        public BurgerBuilder withOnion(boolean hasOnion) {
            this.onion = hasOnion;
            return this;
        }

        public BurgerBuilder withExtraPatty(boolean hasExtraPatty) {
            this.extraPatty = hasExtraPatty;
            return this;
        }

        public BurgerBuilder withPepperoni(boolean hasPepperoni) {
            this.pepperoni = hasPepperoni;
            return this;
        }

        public BurgerBuilder withDrink(String drink) {
            this.drink = drink;
            return this;
        }

        public BugerMealBuilder build() {
            return new BugerMealBuilder(this);
        }

    }
}
