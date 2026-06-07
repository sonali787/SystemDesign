# 🏭 System Design - Factory Pattern Example

## 📌 Overview

This repository demonstrates the need for and implementation of the **Factory Design Pattern** in Java.

The purpose of this project is to understand the pitfalls of tight coupling and how centralizing object creation using the Factory pattern solves this problem. 

---

## 🧠 What is the Factory Pattern?

The **Factory Pattern** is a creational design pattern that:

- ✅ Encapsulates object creation logic
- ✅ Reduces tight coupling
- ✅ Makes the system extensible
- ✅ Follows the **Open-Closed Principle**

Instead of creating objects directly using `new` combined with complex conditional statements (`if-else`), the client requests the object from a factory class.

---

## 🏗️ The Problem: Without Factory Pattern

In this project, we have a logistics system where we send shipments via different modes like **Air** and **Roads**.

Currently, the `LogisticService` directly manages object creation and usage within its constructor:

```java
public class LogisticService {
    public LogisticService(String mode){
        if (mode.equals("AIR")){
            Logistics logistics = new Air(); // Tightly coupled
            logistics.send();
        } else if (mode.equals("Road")){
            Logistics logistics = new Roads(); // Tightly coupled
            logistics.send();
        } else {
            System.out.println("Mode is not supported : " + mode);
        }
    }
}
```

### 🚨 Why is this a problem?
1. **Tight Coupling:** The `LogisticService` class is tightly coupled to the specific implementations (`Air`, `Roads`).
2. **Violation of Open-Closed Principle:** If we want to add a new mode of transport (e.g., `Sea`), we are forced to modify the existing `LogisticService` code to add another `else if` branch. As the application grows, this conditional logic becomes messy and hard to maintain.
3. **Mixed Responsibilities:** `LogisticService` is responsible for both **creating** the objects and **using** them (calling `send()`).

---

## 🏭 The Solution: Factory Pattern

To solve the problem, we use a **Factory** to delegate the object creation logic away from the service.

The `LogisticService` will no longer instantiate objects directly. Instead, we use `LogisticServiceWithFactory` paired with a `LogisticsFactory`:

```java
public class LogisticServiceWithFactory {
    public LogisticServiceWithFactory(String mode) {
        // We use the factory to get the object. The service is now DECOUPLED.
        Logistics logistics = LogisticsFactory.createLogistics(mode);
        
        if (logistics != null) {
            logistics.send();
        }
    }
}
```

The factory decides which object to create and return — the service doesn't need to know the details and won't need to be modified when new logistics modes (like `Trains`) are added.

---

## 🧩 Project Structure

```
src/main/java/org/example/
├── Logistics.java                 # Interface defining the send() behavior
├── Air.java                       # Concrete implementation for Air
├── Roads.java                     # Concrete implementation for Roads
├── Trains.java                    # Concrete implementation for Trains
├── LogisticsFactory.java          # Factory class (centralized object creation)
├── LogisticService.java           # Client class (Demonstrating the problem)
├── LogisticServiceWithFactory.java# Client class (Demonstrating the solution)
└── Main.java                      # Entry point of the application
```

| Component                     | Role                            |
|-------------------------------|---------------------------------|
| `Logistics`                   | Interface                       |
| `Air`, `Roads`, `Trains`      | Concrete implementations        |
| `LogisticsFactory`            | Responsible for object creation |
| `LogisticService`             | Service tightly coupled         |
| `LogisticServiceWithFactory`  | Service decoupled using Factory |

---

## 🚀 How to Run

1. Open the project in your IDE (**IntelliJ IDEA** / **VS Code**)
2. Navigate to `src/main/java/org/example/Main.java`
3. Run the `Main` class to observe the current behavior.

---

## 🎯 Why This Pattern is Useful

- 🔒 Client code does **not** depend on concrete classes.
- ➕ Easy to **add new logistics types** (like `Sea` or `Rail`) without changing the `LogisticService` logic.
- 🏗️ **Centralized** object creation logic inside a dedicated Factory class.
- 🔧 Improves **maintainability** and **readability**.

---

## 📚 Learning Outcome

Through this project code, you can observe:

- Why multiple `if-else` checks for object creation lead to **tight coupling**.
- The exact **problem** that necessitates the Factory pattern.
- The path from **direct instantiation** to **factory-based creation**.

---

## 🔜 Next Steps

- [x] Refactor `LogisticService` by introducing `LogisticsFactory` class
- [x] Add new `Trains` logistics mode to verify Open-Closed Principle
- [ ] Add **Singleton Pattern**
- [ ] Add **Builder Pattern**
- [ ] Add **Strategy Pattern**

---

## 👩‍💻 Author

**Sonali Yadav**
