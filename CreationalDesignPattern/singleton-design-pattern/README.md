# Singleton Design Pattern

## Introduction
The **Singleton Pattern** is a Creational Design Pattern that ensures a class has only **one instance** and provides a global point of access to it.

It deals with the problem of object creation time—specifically when multiple parts of your application need to share the exact same state or resources.

---

## The Problem Statement 🚨
Imagine we are building a judge system for an online coding platform, and we need to keep track of the total number of times the **"Run"** and **"Submit"** actions were triggered across the entire application.

If we simply create a new `JudgeAnalytics` object every time we need to log an action, our count will be completely incorrect! Every object will have its own isolated counters (starting from 0). 

### Without Singleton (Problem)
```java
class JudgeAnalytics {
    private int run = 0;
    private int submit = 0;

    public void countRun() { run++; }
    public void countSubmit() { submit++; }
    public int getRun() { return run; }
    public int getSubmit() { return submit; }
}

// Usage
JudgeAnalytics analytics1 = new JudgeAnalytics();
analytics1.countRun();    // analytics1 run = 1

JudgeAnalytics analytics2 = new JudgeAnalytics();
analytics2.countRun();    // analytics2 run = 1 

// The total run count across our app should be 2, but both return 1!
```

---

## The Solution ✅
To solve this, we can use the **Singleton Pattern**. We restrict the instantiation of the `SingletonJudgeAnalytics` class and ensure that only one instance of the class exists in the Java Virtual Machine.

### How to Implement Singleton:
1. **Private Static Instance**: Create a private static variable of the class type to hold the single instance.
2. **Private Constructor**: Make the constructor private to prevent anyone else from instantiating it with the `new` keyword.
3. **Public Static Method**: Create a public static method (usually named `getInstance()`) that returns the single instance. It creates the instance if it doesn't exist yet, or returns the existing one.

### With Singleton (Solution)
```java
class SingletonJudgeAnalytics {
    // 1. Create a private static instance of the class
    private static SingletonJudgeAnalytics instance;

    private int run = 0;
    private int submit = 0;

    // 2. Make the constructor private
    private SingletonJudgeAnalytics() { }

    // 3. Provide a global point of access
    public static SingletonJudgeAnalytics getInstance() {
        if (instance == null) {
            instance = new SingletonJudgeAnalytics();
        }
        return instance;
    }

    public void countRun() { run++; }
    public void countSubmit() { submit++; }
    public int getRun() { return run; }
    public int getSubmit() { return submit; }
}

// Usage
SingletonJudgeAnalytics singleton1 = SingletonJudgeAnalytics.getInstance();
singleton1.countRun();   // Total run = 1

SingletonJudgeAnalytics singleton2 = SingletonJudgeAnalytics.getInstance();
singleton2.countRun();   // Total run = 2!

System.out.println(singleton1 == singleton2); // true (They point to the exact same object)
```

## How to Run This Code
1. Navigate into the `singleton-design-pattern/src/` directory.
2. Compile the Java file:
   ```bash
   javac Main.java
   ```
3. Run the compiled class:
   ```bash
   java Main
   ```

You will see the output demonstrating how the non-singleton approach fails to keep a global count, while the Singleton approach successfully maintains a single state!
