# 🏭 System Design - Factory Pattern Example

## 📌 Overview

This repository contains an implementation of the **Factory Design Pattern** in Java.

The purpose of this project is to understand how object creation can be centralized and decoupled from client code using the Factory pattern.

---

## 🧠 What is the Factory Pattern?

The **Factory Pattern** is a creational design pattern that:

- ✅ Encapsulates object creation logic
- ✅ Reduces tight coupling
- ✅ Makes the system extensible
- ✅ Follows the **Open-Closed Principle**

Instead of creating objects directly using `new`, the client requests the object from a factory class.

---

## 🏗️ Problem Statement

We want to create different types of vehicles:
- 🚗 Car
- 🚲 Bike
- 🚛 Truck

**Without Factory Pattern** — direct instantiation creates tight coupling:

```java
Vehicle v = new Car(); // tightly coupled to Car class
```

---

## 🏭 Factory Pattern Usage

We use a Factory to create objects instead of directly instantiating them:

```java
Vehicle v = VehicleFactory.getVehicle("CAR");
```

The factory decides which object to create and return — the client doesn't need to know.

---

## 🧩 Project Structure

```
src/
├── Vehicle.java          # Interface
├── Car.java              # Concrete implementation
├── Bike.java             # Concrete implementation
├── Truck.java            # Concrete implementation
├── VehicleFactory.java   # Factory class (object creation logic)
└── Main.java             # Client class
```

| Component              | Role                            |
|------------------------|---------------------------------|
| `Vehicle`              | Interface                       |
| `Car`, `Bike`, `Truck` | Concrete implementations        |
| `VehicleFactory`       | Responsible for object creation |
| `Main`                 | Client class — uses the factory |

---

## 🚀 How to Run

1. **Clone the repository**
   ```bash
   git clone <repo-url>
   ```
2. Open the project in your IDE (**IntelliJ IDEA** / **VS Code**)
3. Run the `Main` class

---

## 🎯 Why This Pattern is Useful

- 🔒 Client code does **not** depend on concrete classes
- ➕ Easy to **add new vehicle types** without changing client code
- 🏗️ **Centralized** object creation logic
- 🔧 Improves **maintainability**
- 📈 Supports **scalability**

---

## 📚 Learning Outcome

Through this implementation, I understood:

- How Factory reduces **tight coupling**
- Difference between **direct instantiation** vs **factory-based creation**
- When to use Factory in **Low-Level Design (LLD) interviews**

---

## 🔜 Next Steps

- [ ] Add **Singleton Pattern**
- [ ] Add **Builder Pattern**
- [ ] Add **Strategy Pattern**
- [ ] Implement complete LLD problems like **Parking Lot**

---

## 👩‍💻 Author

**Sonali Yadav**
