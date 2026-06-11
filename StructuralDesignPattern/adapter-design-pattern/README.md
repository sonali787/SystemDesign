# Adapter Design Pattern

## What is the Adapter Pattern?

The **Adapter** pattern is a structural design pattern that allows objects with incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping one of the objects to make it compatible with the other.

Think of it like a power adapter — a US plug cannot fit into a UK socket directly, but an adapter in between makes it work without changing either the plug or the socket.

---

## Problem

You have an existing system built around a specific interface, and you want to integrate a third-party library or legacy class that has a different interface — without modifying either side.

---

## Solution

Create an **Adapter** class that:
- Implements the interface your system expects
- Internally delegates calls to the incompatible class by translating method signatures

---

## Example: Payment Gateway Integration

This implementation models a checkout system that supports multiple payment gateways. The system is designed around a `PaymentGateway` interface, but `RazorPay` exposes its own API (`RazorPayApi`) with a different method signature.

### Class Diagram

```
         <<interface>>
        PaymentGateway
        + pay(orderId, amount)
              |
     _________|__________
    |                    |
PayUGateway       RazorPayAdapter      RazorPayApi
+ pay(...)        + pay(...)   ------> + makePayment(...)
```

### Components

| Class / Interface   | Role                  | Description                                                                 |
|---------------------|-----------------------|-----------------------------------------------------------------------------|
| `PaymentGateway`    | Target Interface      | The interface `CheckoutService` depends on                                  |
| `PayUGateway`       | Concrete Target       | Native implementation — already compatible with `PaymentGateway`            |
| `RazorPayApi`       | Adaptee               | Third-party class with an incompatible method `makePayment(invoiceId, amount)` |
| `RazorPayAdapter`   | Adapter               | Implements `PaymentGateway`, wraps `RazorPayApi`, translates the call       |
| `CheckoutService`   | Client                | Uses `PaymentGateway` — unaware of which gateway is plugged in              |

---

## Code Walkthrough

### Target Interface

```java
public interface PaymentGateway {
    void pay(String orderId, double amount);
}
```

### Native Implementation (no adapter needed)

```java
public class PayUGateway implements PaymentGateway {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid Rs. " + amount + " Using PayU for order " + orderId);
    }
}
```

### Adaptee (incompatible third-party API)

```java
public class RazorPayApi {
    public void makePayment(String invoiceId, double amount) {
        System.out.println("Paid Rs. " + amount + " Using RazorPay for order " + invoiceId);
    }
}
```

### Adapter (bridges the gap)

```java
public class RazorPayAdapter implements PaymentGateway {
    @Override
    public void pay(String orderId, double amount) {
        RazorPayApi razorPayApi = new RazorPayApi();
        razorPayApi.makePayment(orderId, amount);  // translates the call
    }
}
```

### Client

```java
public class CheckoutService {
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount) {
        paymentGateway.pay(orderId, amount);
    }
}
```

### Usage

```java
CheckoutService service1 = new CheckoutService(new PayUGateway());
service1.checkout("1", 18000);
// Output: Paid Rs. 18000.0 Using PayU for order 1

CheckoutService service2 = new CheckoutService(new RazorPayAdapter());
service2.checkout("2", 4000);
// Output: Paid Rs. 4000.0 Using RazorPay for order 2
```

---

## When to Use

- Integrating a third-party library whose interface doesn't match what your system expects
- Working with legacy code that can't be modified
- Making classes work together that weren't designed to

## Benefits

- **Open/Closed Principle**: Extend behavior without modifying existing code
- **Single Responsibility**: Keeps translation logic isolated in the adapter
- **Interchangeability**: Client code doesn't need to change when switching implementations
