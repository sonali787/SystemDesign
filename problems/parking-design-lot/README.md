# Parking Lot System Design

This project implements a multi-floor, multi-spot-type, thread-safe Parking Lot System in Java 25.

## Functional Requirements

### 1. Entry Flow
- **Vehicle arrives at gate:** Entrance gates represent checkpoints for vehicles.
- **Assign slot based on vehicle type:** Finds a matching vacant parking spot (e.g., Motorcycle, Compact, Large).
- **Generate ticket:** Create a unique ticket detailing vehicle info, gate info, allocated spot, and entry time.
- **Mark slot as occupied:** Change the slot status to prevent other vehicles from booking it.
- **Return entry response:** Return an entry response containing status (Success/Failure) and the generated ticket.

### 2. Exit Flow
- **Present ticket:** Retrieve the ticket detail.
- **Calculate fee using pricing rules:** Calculate the parking fee using either a **Flat** or **Hourly** pricing strategy.
- **Process payment:** Take a payment method, process the transaction, and record the payment.
- **Release slot:** Mark the occupied parking spot as vacant again.
- **Return exit response with receipt:** Return an exit response containing status and the receipt with check-out details.

### 3. Admin Flow
- **Add/Edit/Delete floors and slots:** Manage parking lot structure dynamically.
- **Define/update pricing rules:** Change the pricing strategy at runtime (Flat vs Hourly).
- **View parking lot status:** Query and render a comprehensive dashboard of current occupancies, floor states, and vacant slots.
---

## Steps to Approach LLD Interview

### Step 1: Clarify the requirements as much as you can
### Step 2: Identify the core entities

---

## Thread Safety & ConcurrentHashMap

In a multi-threaded system design (like a parking lot with concurrent entrance/exit gates), `ConcurrentHashMap` is used to store in-memory states (like tickets or vehicles) thread-safely:
- **Fine-Grained Locking:** Instead of locking the entire map (which degrades performance), it locks at the bucket level.
- **Concurrent Execution:** This means **Thread A can write to Bucket 1 at the exact same time Thread B is writing to Bucket 2, and Thread C is reading from Bucket 3 without blocking each other!**
