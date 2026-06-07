# 🍔 Builder Design Pattern — BurgerMeal

> **Category:** Creational Design Pattern  
> **Intent:** Separate the construction of a complex object from its representation, so the same construction process can create different representations.

---

## 📖 Table of Contents

1. [What is the Builder Pattern?](#-what-is-the-builder-pattern)
2. [Real-World Analogy](#-real-world-analogy)
3. [The Problem — Telescoping Constructors](#-the-problem--telescoping-constructors)
4. [The Solution — Builder Pattern](#-the-solution--builder-pattern)
5. [Project Structure](#-project-structure)
6. [Class Breakdown](#-class-breakdown)
7. [How to Run](#-how-to-run)
8. [Key Takeaways](#-key-takeaways)

---

## 🧠 What is the Builder Pattern?

The Builder Pattern is a **creational design pattern** that lets you construct complex objects **step by step**.

Instead of dumping everything into one giant constructor, you use a **separate Builder class** that lets the caller pick exactly which fields to set — in any order, with full readability.

```
                    ┌──────────────────────┐
                    │      Client Code     │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │    BurgerBuilder      │   ← step-by-step configuration
                    │  .withCheese(true)    │
                    │  .withTomato(true)    │
                    │  .withDrink("Coke")   │
                    └──────────┬───────────┘
                               │  .build()
                               ▼
                    ┌──────────────────────┐
                    │   BugerMealBuilder   │   ← fully constructed object
                    │   (immutable meal)    │
                    └──────────────────────┘
```

---

## 🌍 Real-World Analogy

Think of ordering at a **burger counter**:

| Without Builder (Telescoping) | With Builder |
|------|------|
| "I want a Non-Veg, Large, YES, NO, YES, NO, YES, NO, Coke" | "I want a Non-Veg Large burger **with** cheese, **with** tomato, **with** extra patty, **and** a Coke" |
| Cashier: "Wait… was that cheese YES or lettuce YES?" 😵 | Cashier: "Got it!" ✅ |

The Builder Pattern lets you **name what you're adding**, instead of relying on the **position** of parameters.

---

## ❌ The Problem — Telescoping Constructors

### What is a BurgerMeal?

A `BurgerMeal` is an object with:
- **2 required fields** → `burgerType` (Veg/Non-Veg), `size` (Small/Medium/Large)
- **7 optional fields** → `cheese`, `lettuce`, `tomato`, `onion`, `extraPatty`, `pepperoni`, `drink`

Every customer orders a **different combination**. To handle this without a Builder, we create a **chain of constructors** — each adding one more parameter:

### The Telescoping Chain

```java
// Constructor 1: BurgerMeal(type, size)
// Constructor 2: BurgerMeal(type, size, cheese)
// Constructor 3: BurgerMeal(type, size, cheese, lettuce)
// Constructor 4: BurgerMeal(type, size, cheese, lettuce, tomato)
// Constructor 5: BurgerMeal(type, size, cheese, lettuce, tomato, onion)
// Constructor 6: BurgerMeal(type, size, cheese, lettuce, tomato, onion, extraPatty)
// Constructor 7: BurgerMeal(type, size, cheese, lettuce, tomato, onion, extraPatty, pepperoni)
// Constructor 8: BurgerMeal(type, size, cheese, lettuce, tomato, onion, extraPatty, pepperoni, drink)
```

**8 constructors** for just 7 optional fields. This is the **Telescoping Constructor Anti-Pattern**.

### 🔴 5 Problems This Causes

#### Problem 1: Constructor Explosion
With just 7 optional fields we already have **8 constructors**. Add one more topping (`jalapeno`) → modify **ALL 8** constructors + add a 9th. This does **NOT** scale.

#### Problem 2: Unreadable Client Code
```java
// ❌ What does true, false, true, false, true, false mean?!
new BurgerMeal("Non-Veg", "Large", true, false, true, false, true, false, "Coke");
```
Without reading the constructor signature, it's **impossible** to know which `boolean` maps to which topping.

#### Problem 3: Silent Bugs (Swapped Parameters)
```java
// Customer wanted: cheese=YES, lettuce=NO
// Developer wrote: cheese=NO,  lettuce=YES  ← SWAPPED!
new BurgerMeal("Veg", "Small", false, true, false, true, false, false, "Pepsi");
```
Java compiles this **without any error**. The bug is invisible. Wrong burger gets delivered. 🐛

#### Problem 4: Fragile Maintenance
Adding a new optional field (e.g., `jalapeno`) means:
1. Add the field to the class
2. **Modify ALL existing constructors**
3. **Update ALL callers** across the codebase
4. Pray nothing breaks 🙏

#### Problem 5: No Constraint Enforcement
There's no clean way to say _"Veg burgers can't have pepperoni"_ — the constructor blindly accepts any combination.

---

## ✅ The Solution — Builder Pattern

### How It Works

Instead of telescoping constructors, we use a **static inner `BurgerBuilder` class** that:

1. Takes **required fields** in its constructor (can't skip them)
2. Provides **fluent setter methods** for optional fields (`.withCheese()`, `.withTomato()`, etc.)
3. Returns `this` from each setter → enables **method chaining**
4. Has a `.build()` method that creates the final `BugerMealBuilder` object

### Before vs After — Same Order, Two Approaches

```java
// ❌ BEFORE (Telescoping Constructor) — What does true/false mean?
BurgerMeal burger = new BurgerMeal("Non-Veg", "Large", true, false, true, false, true, false, "Coke");

// ✅ AFTER (Builder Pattern) — Self-documenting, readable, clean
BugerMealBuilder burger = new BugerMealBuilder.BurgerBuilder("Non-Veg", "Large")
        .withCheese(true)
        .withTomato(true)
        .withExtraPatty(true)
        .withDrink("Coke")
        .build();
```

### Every Problem → Solved

| # | Problem (Before) | Solution (After) |
|---|---|---|
| 1 | 8 constructors for 7 optional fields | **1 builder** handles any combination |
| 2 | `true, false, true` — unreadable | `.withCheese(true).withTomato(true)` — self-documenting |
| 3 | Swapped booleans = silent bugs | Each field has its **own named method** — can't swap |
| 4 | Adding new field = modify ALL constructors | Just add **one new method** in the Builder |
| 5 | No constraint enforcement | Can add validation logic inside `.build()` |

### Builder Usage Examples from Main.java

```java
// Order 1: Just the basics — only required fields
BugerMealBuilder burger = new BugerMealBuilder.BurgerBuilder("wheat", "small")
        .build();

// Order 2: With lettuce and tomato
BugerMealBuilder burger2 = new BugerMealBuilder.BurgerBuilder("wheat", "medium")
        .withLettuce(true)
        .withTomato(true)
        .build();

// Order 3: Lettuce + tomato + cheese
BugerMealBuilder burger3 = new BugerMealBuilder.BurgerBuilder("wheat", "medium")
        .withLettuce(true)
        .withTomato(true)
        .withCheese(true)
        .build();

// Order 4: The loaded burger — add extra patty too
BugerMealBuilder burger4 = new BugerMealBuilder.BurgerBuilder("wheat", "medium")
        .withLettuce(true)
        .withTomato(true)
        .withCheese(true)
        .withExtraPatty(true)
        .build();
```

Notice how **each order is perfectly readable** — no positional guessing, no boolean confusion.

---

## 📂 Project Structure

```
builderDesignPattern/
├── src/main/java/org/example/
│   ├── BurgerMeal.java          ← THE PROBLEM — Telescoping constructors (8 constructors!)
│   ├── BugerMealBuilder.java    ← THE SOLUTION — Builder pattern with static inner class
│   └── Main.java                ← Client code showing both approaches side by side
├── pom.xml
└── readme.md                    ← You are here
```

---

## 🔍 Class Breakdown

### 1. `BurgerMeal.java` — The Problem Class

| What | Details |
|------|---------|
| **Role** | Demonstrates the Telescoping Constructor anti-pattern |
| **Required fields** | `burgerType`, `size` |
| **Optional fields** | `cheese`, `lettuce`, `tomato`, `onion`, `extraPatty`, `pepperoni`, `drink` |
| **Constructors** | **8 constructors** — each adding one more parameter |
| **Problem** | Unreadable, fragile, bug-prone object creation |

### 2. `BugerMealBuilder.java` — The Solution Class

| What | Details |
|------|---------|
| **Role** | Implements the Builder Pattern with a static inner `BurgerBuilder` class |
| **Outer class** | `BugerMealBuilder` — the product (the burger meal object) |
| **Inner class** | `BurgerBuilder` — the builder that constructs the product step by step |
| **Required fields** | Enforced via `BurgerBuilder(String burgerType, String size)` constructor |
| **Optional fields** | Set via fluent methods: `.withCheese()`, `.withLettuce()`, `.withTomato()`, etc. |
| **Build method** | `.build()` → creates and returns the final `BugerMealBuilder` object |
| **Method chaining** | Every setter returns `this` → enables `.withX().withY().build()` |

### 3. `Main.java` — Client Code

| What | Details |
|------|---------|
| **Role** | Demonstrates both approaches side by side |
| **Part 1** | 4 orders using telescoping constructors (showing the problems) |
| **Part 2** | 4 orders using the Builder pattern (showing the solution) |

---

## ▶️ How to Run

```bash
cd builderDesignPattern
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

---

## 💡 Key Takeaways

1. **Use Builder when an object has many optional parameters** — especially when there are more than 3-4 optional fields.

2. **The Builder separates construction from representation** — the client doesn't need to know the internal structure of the object.

3. **Method chaining makes code self-documenting** — `.withCheese(true).withTomato(true)` is instantly understandable.

4. **Required fields go in the Builder's constructor** — this ensures they can never be skipped.

5. **Optional fields get individual setter methods** — set only what you need, in any order.

6. **Adding new fields is easy** — just add one new method in the Builder. No existing code breaks.

7. **The Builder Pattern is used extensively in Java libraries:**
   - `StringBuilder` — building strings step by step
   - `Stream.Builder` — building streams
   - `HttpRequest.newBuilder()` — Java 11 HTTP client
   - `Lombok @Builder` — auto-generates the pattern

---

## 🔗 When to Use vs When NOT to Use

| ✅ Use Builder When | ❌ Don't Use Builder When |
|---|---|
| Object has 4+ optional parameters | Object has only 1-2 fields |
| Many possible combinations of fields | All fields are always required |
| Object should be immutable after creation | Object needs to be mutable |
| You want readable, self-documenting code | Simplicity is more important |
| You need validation before construction | No validation needed |

---

> **Design Pattern Series:** [Singleton](../CreationalDesignPattern) → [Factory](../FactoryDesignPattern) → **Builder** (you are here)
