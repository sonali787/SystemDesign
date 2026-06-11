# 🏭 System Design - Abstract Factory Pattern Example

## 📌 Overview

This repository demonstrates the need for and implementation of the **Abstract Factory Design Pattern** in Java.

The purpose of this project is to understand how the Abstract Factory pattern groups related object creation into a single factory interface, ensuring that the products created together are compatible — without the client knowing the concrete classes.

---

## 🧠 What is the Abstract Factory Pattern?

The **Abstract Factory Pattern** is a creational design pattern that:

- ✅ Provides an interface for creating **families of related objects**
- ✅ Ensures **product compatibility** (e.g., a payment gateway and invoice type that belong to the same region)
- ✅ Hides concrete implementations from the client
- ✅ Follows the **Open-Closed Principle** and **Dependency Inversion Principle**

Instead of calling constructors directly or using a single factory method, the client uses an **Abstract Factory** that groups creation of related products together.

---

## 🏗️ The Problem: Without Abstract Factory Pattern

In this project, we have a checkout system where based on the region (e.g., India), we need to:
1. Create the appropriate **Payment Gateway** (e.g., Razorpay, PayU)
2. Create the appropriate **Invoice** type (e.g., GST Invoice)

Without an Abstract Factory, `CheckoutService` would be littered with `if-else` blocks for both concerns:

```java
public class CheckoutService {
    public void checkout(String region, String gatewayType, Float amount) {
        // Tightly coupled — must handle both products manually
        if (region.equals("India")) {
            if (gatewayType.equals("razorpay")) {
                PaymentGateway pg = new RazorpayPayment(); // tightly coupled
                pg.processPayment(amount);
            }
            Invoice invoice = new GSTInvoice(); // tightly coupled
            invoice.generateInvoice();
        }
        // Adding a new region means modifying this class again and again...
    }
}
```

### 🚨 Why is this a problem?

1. **Tight Coupling:** `CheckoutService` directly depends on `RazorpayPayment`, `GSTInvoice`, etc.
2. **Violation of Open-Closed Principle:** Adding a new region (e.g., USA with Stripe + VAT Invoice) forces changes to `CheckoutService`.
3. **Product Mismatch Risk:** Nothing prevents accidentally pairing a `RazorpayPayment` (India) with a `VATInvoice` (USA) — incompatible products.
4. **Mixed Responsibilities:** The service handles both **creation** and **usage** of objects.

---

## 🏭 The Solution: Abstract Factory Pattern

We introduce `IndiaFactory` as a concrete factory that groups the creation of both related products — `PaymentGateway` and `Invoice` — for the India region.

`CheckoutService` only talks to the factory and is completely decoupled from concrete implementations:

```java
public class CheckoutService {
    private String gatewayType;

    public CheckoutService(String gatewayType) {
        this.gatewayType = gatewayType;
    }

    public void checkout(Float amount) {
        PaymentGateway paymentGateway = IndiaFactory.createPaymentGateway(gatewayType);
        IndiaFactory.createInvoice();
    }
}
```

The `IndiaFactory` centralizes the creation of all India-specific products:

```java
public class IndiaFactory {

    public static PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equals("razorpay")) {
            return new RazorpayPayment();
        } else if (gatewayType.equals("PayUGateway")) {
            return new PayUGatewayPayment();
        } else {
            throw new IllegalArgumentException("Invalid gateway type");
        }
    }

    public static void createInvoice() {
        Invoice invoice = new GSTInvoice();
        invoice.generateInvoice();
    }
}
```

To add a new region (e.g., USA), you simply add a `USAFactory` — **zero changes** to `CheckoutService`.

---

## 🧩 Project Structure

```
src/main/java/org/example/
├── PaymentGateway.java        # Interface for payment product
├── Invoice.java               # Interface for invoice product
├── RazorpayPayment.java       # Concrete payment: Razorpay (India)
├── PayUGatewayPayment.java    # Concrete payment: PayU (India)
├── GSTInvoice.java            # Concrete invoice: GST (India)
├── IndiaFactory.java          # Abstract Factory for India region
├── CheckoutService.java       # Client — uses factory, not concrete classes
└── Main.java                  # Entry point of the application
```

| Component             | Role                                          |
|-----------------------|-----------------------------------------------|
| `PaymentGateway`      | Product interface (payment)                   |
| `Invoice`             | Product interface (invoice)                   |
| `RazorpayPayment`     | Concrete product — Razorpay payment           |
| `PayUGatewayPayment`  | Concrete product — PayU payment               |
| `GSTInvoice`          | Concrete product — GST Invoice (India)        |
| `IndiaFactory`        | Concrete factory — creates India-specific products |
| `CheckoutService`     | Client — decoupled from concrete classes      |

---

## 🔑 Abstract Factory vs Factory Pattern

| Feature                     | Factory Pattern                          | Abstract Factory Pattern                          |
|-----------------------------|------------------------------------------|---------------------------------------------------|
| Creates                     | One product                              | Families of related products                      |
| Focus                       | Single object creation                   | Product compatibility across a family             |
| Adding new variant          | Add new product class                    | Add new factory class (e.g., `USAFactory`)        |
| Client awareness            | Knows one product interface              | Knows multiple product interfaces                 |

---

## 🚀 How to Run

1. Open the project in your IDE (**IntelliJ IDEA** / **VS Code**)
2. Navigate to `src/main/java/org/example/Main.java`
3. Run the `Main` class to observe the checkout flow using `IndiaFactory`.

---

## 🎯 Why This Pattern is Useful

- 🔒 Client code does **not** depend on concrete product classes.
- 🤝 Ensures **compatible products** are always created together (no mismatched region/gateway/invoice combos).
- ➕ Easy to **add new regions** (like `USAFactory`) without touching existing service code.
- 🏗️ **Centralized** and **grouped** creation logic per product family.
- 🔧 Improves **maintainability** and **scalability**.

---

## 📚 Learning Outcome

Through this project code, you can observe:

- Why using `new` directly inside services leads to **tight coupling** and **product mismatch risk**.
- How grouping related product creation into a factory guarantees **compatibility**.
- The difference between **Factory Pattern** (one product) and **Abstract Factory Pattern** (family of products).

---

## 🔜 Next Steps

- [x] Implement `IndiaFactory` with `PaymentGateway` and `Invoice` product families
- [x] Decouple `CheckoutService` using the Abstract Factory
- [ ] Add `USAFactory` with `StripePayment` and `VATInvoice` to verify Open-Closed Principle
- [ ] Add **Prototype Pattern**
- [ ] Add **Strategy Pattern**

---

## 👩‍💻 Author

**Sonali Yadav**
