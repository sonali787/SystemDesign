# Singleton Design Pattern - Concepts & Reference

*Note: Answers to be filled in during study.*

### 1. What is this? Why do we need this?

**What is this?**
The Singleton pattern ensures that the class has only one instance throughout the application's lifecycle and provides a global access point to the instance.

**Why do we need this?**
- Should exist only once due to global state, resource constraints, and global reasoning.
- Needs to be accessed from different parts of the system.

---

### 2. Prime Example
- Database connections
- Logging
- Analytics (to some extent)

---

### 3. Working of Sigleton pattern

- Private constructor : prevents instantiation from outside
- Static variable : hold single instance
- public static method : provides global access


### 4. How can we implement Singleton?


- **Eager Loading**: The Singleton instance is created immediately when the class is loaded into memory by the JVM. It is simple and automatically thread-safe, but it can waste memory if the instance is never actually used by the application.
- **Lazy Loading**: The Singleton instance is created only when it is requested for the very first time. This saves memory and resources, but it requires careful handling in multi-threaded environments to ensure only one instance is created.

---

### 4. Eager Loading Implementation

```java
public class EagerSingleton {
    // 1. Create the instance immediately when the class is loaded (Eager Loading)
    private static final EagerSingleton instance = new EagerSingleton();

    // 2. Private constructor to prevent instantiation from outside
    private EagerSingleton() {
        // Initialization code here
    }

    // 3. Global access point
    public static EagerSingleton getInstance() {
        return instance; // Simply return the already-created instance
    }
}
```

---

### 4.5. Lazy Loading Implementation

```java
public class LazySingleton {
    // 1. Declare the instance variable but DO NOT initialize it yet
    private static LazySingleton instance;

    // 2. Private constructor to prevent instantiation from outside
    private LazySingleton() {
        // Initialization code here
    }

    // 3. Global access point with lazy initialization
    public static LazySingleton getInstance() {
        if (instance == null) {
            // The object is ONLY created when this method is called for the first time
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

---

### 5. Thread Safety
**The Problem:**
In a multi-threaded application using Lazy Loading, two separate threads might reach the `if (instance == null)` line at the exact same millisecond. 
Because neither thread has created the object yet, both threads will see `null` and both will execute `new SingletonJudgeAnalytics()`.
This completely breaks the pattern because you now have two separate objects floating in memory!

**The Solution:**
We must make the `getInstance()` method "thread-safe" so that only one thread can enter the creation block at a time.

---

### 6. Synchronized Keyword Implementation
To achieve Thread Safety, we can add the `synchronized` keyword to the `getInstance()` method. This acts as a lock. If Thread A is inside the method, Thread B must wait outside until Thread A is completely finished.

```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
        // Initialization code here
    }

    // Adding 'synchronized' ensures only one thread can execute this at a time
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
```
*Pros:* It guarantees that only one instance is created.
*Cons:* It makes the application slower. Every single time a thread calls `getInstance()`, it has to acquire the lock and wait in line, even long after the object has already been created.

---

### 7. Double-Checked Locking
**The Problem with the `synchronized` method:**
If we put `synchronized` on the `getInstance()` method, *every single time* a thread tries to get the instance, it has to wait in line. But we only really need the lock for the very first time (when the object is actually being created). After it's created, getting the instance should be fast!

**The Solution:**
Double-Checked Locking! We remove `synchronized` from the method and put it *inside* the method, wrapped in two `if (instance == null)` checks.

```java
public class DoubleCheckSingleton {
    // 'volatile' ensures that multiple threads handle the instance variable correctly
    private static volatile DoubleCheckSingleton instance;

    private DoubleCheckSingleton() { }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) { // 1st Check (Fast, no locks!)
            synchronized (DoubleCheckSingleton.class) { // Lock is acquired ONLY if null
                if (instance == null) { // 2nd Check (Inside the lock to prevent duplicates)
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
```
*Why double check?* Because by the time Thread A acquires the lock, Thread B might have already created the instance while Thread A was waiting in line. The second check guarantees safety.

*(Note: Always use the `volatile` keyword when doing double-checked locking in Java to prevent memory visibility issues!)*

---

### 8. Bill Pugh Singleton (Inner Static Helper Class)
**The Concept:**
Double-Checked Locking works, but it's complex and easy to get wrong (e.g., forgetting `volatile`). Bill Pugh created a better way that uses the Java ClassLoader to handle the thread-safety for us, completely avoiding `synchronized` and `volatile`.

**The Solution:**
We create a `private static class` inside our Singleton class. Because of how Java works, this inner class is not loaded into memory until someone actually calls the `getInstance()` method.

```java
public class BillPughSingleton {
    
    private BillPughSingleton() { }

    // 1. The Inner Static Helper Class
    private static class SingletonHelper {
        // The instance is created here!
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    // 2. Global access point
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE; // This triggers the loading of the inner class!
    }
}
```
*Why is this the best approach?* 
- **Lazy Loaded:** The `SingletonHelper` class isn't loaded until `getInstance()` is called.
- **100% Thread Safe:** The JVM guarantees that static variables are initialized safely, so no two threads can ever create the object.
- **Fast:** There is no `synchronized` keyword anywhere, so it runs at maximum speed!

---

### 9. Pros and Cons

**Pros:**
1. **Guaranteed Uniqueness:** You are 100% certain that only one instance of the class exists across the entire application.
2. **Global Access Point:** You can easily access the object from anywhere without having to pass it around as a parameter.
3. **Memory Savings:** It prevents the unnecessary creation of duplicate objects that all do the exact same thing (like logging or database connections).
4. **Lazy Initialization:** The object is only created when it is actually needed, saving resources if it's never called.

**Cons:**
1. **Global State is Dangerous:** Because it acts like a global variable, any part of the application can change its state, making debugging difficult. If Thread A changes a setting, Thread B might crash because it wasn't expecting the change.
2. **Hard to Unit Test:** Singletons carry their state between unit tests. If Test 1 modifies the Singleton, Test 2 will inherit that modified state, leading to flaky tests.
3. **Hidden Dependencies:** When a class uses a Singleton, you can't tell just by looking at the constructor. It secretly relies on the Singleton pattern deep in its code.
4. **Multi-threading Nightmares:** As seen in this document, implementing a truly safe Singleton in a multi-threaded environment is surprisingly difficult and requires deep knowledge of locks, `volatile`, and JVM class loading.
