# Decorator Design Pattern

## What is the Decorator Pattern?

The **Decorator** pattern is a structural design pattern that lets you attach new behaviors to an object dynamically by wrapping it in a decorator object. Decorators implement the same interface as the object they wrap, so you can stack multiple decorators without changing the original class.

Think of it like ordering a pizza — you start with a base (Margherita), then add toppings one at a time. Each topping wraps the pizza you already have and adds its own cost and description on top.

---

## The Problem: Subclass Explosion

Imagine a pizza shop with a base pizza and optional add-ons: **Extra Cheese**, **Olives**, **Jalapeños**.

### The naive approach — one subclass per combination

Without the decorator pattern, you create a new class for every possible combination:

```
MargheritaPizza
MargheritaWithExtraCheese
MargheritaWithOlives
MargheritaWithJalapenos
MargheritaWithExtraCheeseAndOlives
MargheritaWithExtraCheeseAndJalapenos
MargheritaWithOlivesAndJalapenos
MargheritaWithExtraCheeseAndOlivesAndJalapenos
... and so on
```

With **3 toppings**, you need **2³ = 8** subclasses for one base pizza.  
With **5 toppings**, you need **2⁵ = 32** subclasses.  
Add a second base pizza (Pepperoni, Farmhouse, etc.) and the number multiplies again.

```
                    Pizza (concept)
                         |
        _________________|_________________
       |                 |                |
 MargheritaPizza   PepperoniPizza    FarmhousePizza
       |
       |___ MargheritaWithExtraCheese
       |___ MargheritaWithOlives
       |___ MargheritaWithExtraCheeseAndOlives
       |___ ... (combinatorial explosion)
```

### Why this breaks down

| Issue | What happens |
|-------|--------------|
| **Combinatorial explosion** | Every new topping doubles the number of classes |
| **Rigid at compile time** | Customer wants cheese + olives? You need a class for that exact combo |
| **Code duplication** | `getCost()` and `getDescription()` logic is copy-pasted across subclasses |
| **Hard to extend** | Adding one new topping forces you to create many new combination classes |

**The real problem:** you need to add responsibilities to objects **at runtime**, in **any combination**, without creating a subclass for every possibility.

---

## The Solution: Wrap and Stack

Instead of baking every combination into a subclass, treat each topping as a **wrapper** around an existing `Pizza`.

```
Customer order: Margherita + Extra Cheese + Olives

Pizza order = new Olives(new ExtraCheese(new MargheritaPizza()));
```

Each decorator:

1. Implements the same `Pizza` interface
2. Holds a reference to another `Pizza` (the one it wraps)
3. Delegates to the wrapped pizza, then adds its own behavior

```
┌─────────────────────────────────────────────────────────┐
│  Olives                                                 │
│  ┌───────────────────────────────────────────────────┐  │
│  │  ExtraCheese                                      │  │
│  │  ┌─────────────────────────────────────────────┐  │  │
│  │  │  MargheritaPizza  (base)                    │  │  │
│  │  │  cost: 200, desc: "Margarita Pizza"         │  │  │
│  │  └─────────────────────────────────────────────┘  │  │
│  │  adds: +50, ", Extra Cheese"                      │  │
│  └───────────────────────────────────────────────────┘  │
│  adds: +30, ", Olives"                                  │
└─────────────────────────────────────────────────────────┘

Final: cost = 280, description = "Margarita Pizza, Extra Cheese, Olives"
```

**3 toppings → 3 decorator classes + 1 base class**, not 8 combination subclasses.  
Add a 4th topping? Write **one** new decorator — no need to touch existing classes.

---

## Class Diagram

```
         <<interface>>
            Pizza
        + getDescription()
        + getCost()
              |
     _________|___________________________
    |                                    |
MargheritaPizza                  <<abstract>>
(base component)                 PizzaDecorator
                                 - pizza: Pizza
                                 + PizzaDecorator(pizza)
                                       |
                                       |
                                  ExtraCheese
                                  + getDescription()
                                  + getCost()
```

---

## Components

| Class / Interface   | Role                    | Description                                                        |
|---------------------|-------------------------|--------------------------------------------------------------------|
| `Pizza`             | Component Interface     | Common interface for base pizzas and all decorators                |
| `MargheritaPizza`   | Concrete Component      | The base object — a plain pizza with no toppings                   |
| `PizzaDecorator`    | Abstract Decorator      | Holds a `Pizza` reference and passes it through the constructor    |
| `ExtraCheese`       | Concrete Decorator      | Wraps any `Pizza` and adds extra cheese cost and description       |

---

## Code Walkthrough

### Component Interface

```java
public interface Pizza {
    String getDescription();
    double getCost();
}
```

### Concrete Component (base pizza)

```java
public class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margarita Pizza";
    }

    @Override
    public double getCost() {
        return 200;
    }
}
```

### Abstract Decorator

```java
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}
```

The constructor stores the wrapped pizza. Every concrete decorator **must** call `super(pizza)` because `PizzaDecorator` has no default no-arg constructor — once you define any constructor, Java stops generating one automatically.

### Concrete Decorator

```java
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
```

`ExtraCheese` does not replace the inner pizza — it **delegates** to `pizza` and **adds** on top. That is the core of the pattern.

---

## Usage

```java
// Plain Margherita
Pizza plain = new MargheritaPizza();
// cost: 200, description: "Margarita Pizza"

// Margherita + Extra Cheese
Pizza withCheese = new ExtraCheese(new MargheritaPizza());
// cost: 250, description: "Margarita Pizza, Extra Cheese"

// Stack decorators in any order at runtime
Pizza fullyLoaded = new ExtraCheese(new ExtraCheese(new MargheritaPizza()));
// cost: 300, description: "Margarita Pizza, Extra Cheese, Extra Cheese"
```

The client always works with the `Pizza` interface. It does not care whether the object is a base pizza or a stack of decorators.

---

## How Object Creation Works

When you write:

```java
Pizza order = new ExtraCheese(new MargheritaPizza());
```

```
1. new MargheritaPizza()           → base pizza created
2. new ExtraCheese(margherita)     → ExtraCheese constructor runs
3. super(pizza)                    → calls PizzaDecorator(pizza)
4. this.pizza = pizza              → margherita stored inside decorator
5. order.getCost()                 → pizza.getCost() + 50  →  200 + 50 = 250
```

Every subclass constructor must call a parent constructor first. Because `PizzaDecorator` only has `PizzaDecorator(Pizza pizza)`, `ExtraCheese` must explicitly forward the wrapped pizza via `super(pizza)`.

---

## Naive vs Decorator — Side by Side

| Scenario | Without Decorator | With Decorator |
|----------|-------------------|----------------|
| 1 base + 3 toppings | Up to 8 combination subclasses | 1 base + 3 decorator classes |
| Customer picks toppings at order time | Need a class per combo | Compose decorators at runtime |
| Add a new topping | Create many new combo classes | Add one new decorator class |
| Change base pizza behavior | Update every combo subclass | Change only the base class |

---

## When to Use

- You need to add responsibilities to individual objects dynamically, without affecting other objects
- Extension by subclassing is impractical (too many combinations)
- You want to add or remove behavior at runtime
- You want to follow the **Open/Closed Principle** — open for extension, closed for modification

## Benefits

- **Flexible composition** — mix and match behaviors at runtime
- **Single Responsibility** — each decorator handles one add-on
- **No subclass explosion** — one decorator per topping, not per combination
- **Open/Closed Principle** — add new decorators without changing existing code

## Trade-offs

- Many small objects can make debugging harder (deep wrapper chains)
- Order of decorators can matter if behaviors interact
- Type identity checks (`instanceof`) become less useful since everything is wrapped

---

## Project Structure

```
src/main/java/org/example/
├── Pizza.java              # Component interface
├── MargheritaPizza.java    # Concrete component (base pizza)
├── PizzaDecorator.java     # Abstract decorator
├── ExtraCheese.java        # Concrete decorator (topping)
└── Main.java               # Entry point
```

## Run

```bash
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

Or run `Main.java` directly from your IDE.
