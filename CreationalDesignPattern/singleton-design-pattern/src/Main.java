// PROBLEM: Without Singleton
class JudgeAnalytics {
    private int run = 0;
    private int submit = 0;

    public void countRun() { run++; }
    public void countSubmit() { submit++; }
    public int getRun() { return run; }
    public int getSubmit() { return submit; }
}

// SOLUTION: With Singleton
class SingletonJudgeAnalytics {
    // 1. Create a private static instance of the class
    private static SingletonJudgeAnalytics instance;

    private int run = 0;
    private int submit = 0;

    // 2. Make the constructor private so no one else can instantiate it
    private SingletonJudgeAnalytics() {
    }

    // 3. Provide a public static method to get the instance
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

// SOLUTION 2: With Eager Loading Singleton
class EagerSingletonJudgeAnalytics {
    // 1. Create the instance immediately (Eager Loading)
    private static final EagerSingletonJudgeAnalytics instance = new EagerSingletonJudgeAnalytics();

    private int run = 0;
    private int submit = 0;

    // 2. Private constructor
    private EagerSingletonJudgeAnalytics() {
    }

    // 3. Global access point
    public static EagerSingletonJudgeAnalytics getInstance() {
        return instance; 
    }

    public void countRun() { run++; }
    public void countSubmit() { submit++; }
    public int getRun() { return run; }
    public int getSubmit() { return submit; }
}

// SOLUTION 3: With Thread-Safe Lazy Loading (Synchronized)
class ThreadSafeJudgeAnalytics {
    private static ThreadSafeJudgeAnalytics instance;
    private int run = 0;
    private int submit = 0;

    private ThreadSafeJudgeAnalytics() { }

    // Adding 'synchronized' ensures only one thread can execute this method at a time
    public static synchronized ThreadSafeJudgeAnalytics getInstance() {
        if (instance == null) {
            instance = new ThreadSafeJudgeAnalytics();
        }
        return instance;
    }

    public void countRun() { run++; }
    public void countSubmit() { submit++; }
    public int getRun() { return run; }
    public int getSubmit() { return submit; }
}

// SOLUTION 4: With Double-Checked Locking (Optimized Thread-Safe)
class DoubleCheckJudgeAnalytics {
    // 1. volatile keyword ensures visibility of changes to variables across threads
    private static volatile DoubleCheckJudgeAnalytics instance;
    
    private int run = 0;
    private int submit = 0;

    private DoubleCheckJudgeAnalytics() { }

    // No synchronized on the method itself to save performance!
    public static DoubleCheckJudgeAnalytics getInstance() {
        if (instance == null) { // 1st check (without lock)
            synchronized (DoubleCheckJudgeAnalytics.class) { // Lock is acquired only if instance is null
                if (instance == null) { // 2nd check (with lock)
                    instance = new DoubleCheckJudgeAnalytics();
                }
            }
        }
        return instance;
    }

    public void countRun() { run++; }
    public void countSubmit() { submit++; }
    public int getRun() { return run; }
    public int getSubmit() { return submit; }
}

// SOLUTION 5: With Bill Pugh Singleton (The Best Practice approach in Java)
class BillPughJudgeAnalytics {
    private int run = 0;
    private int submit = 0;

    private BillPughJudgeAnalytics() { }

    private static class SingletonHelper {
        private static final BillPughJudgeAnalytics INSTANCE = new BillPughJudgeAnalytics();
    }

    public static BillPughJudgeAnalytics getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void countRun() { run++; }
    public void countSubmit() { submit++; }
    public int getRun() { return run; }
    public int getSubmit() { return submit; }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- The Problem Statement ---");
        // Creating multiple instances leads to inconsistent state/counts.
        JudgeAnalytics analytics1 = new JudgeAnalytics();
        analytics1.countRun();
        analytics1.countSubmit();

        JudgeAnalytics analytics2 = new JudgeAnalytics();
        analytics2.countRun();
        analytics2.countSubmit();

        System.out.println("Analytics 1 Run count: " + analytics1.getRun()); // Prints 1
        System.out.println("Analytics 2 Submit count: " + analytics2.getSubmit()); // Prints 1
        // The total run and submit count across the application should be 2, but each object has its own isolated state.

        System.out.println("\n--- The Singleton Solution ---");
        // We get the same instance every time
        SingletonJudgeAnalytics singleton1 = SingletonJudgeAnalytics.getInstance();
        singleton1.countRun();
        singleton1.countSubmit();

        SingletonJudgeAnalytics singleton2 = SingletonJudgeAnalytics.getInstance();
        singleton2.countRun();
        singleton2.countSubmit();

        System.out.println("Singleton 1 Run count: " + singleton1.getRun()); // Prints 2
        System.out.println("Singleton 2 Submit count: " + singleton2.getSubmit()); // Prints 2
        
        // Verify they are the exact same object in memory
        if (singleton1 == singleton2) {
            System.out.println("Both singleton objects are exactly the same instance in memory!");
        }
        System.out.println("\n--- The Eager Singleton Solution ---");
        EagerSingletonJudgeAnalytics eager1 = EagerSingletonJudgeAnalytics.getInstance();
        eager1.countRun();
        
        EagerSingletonJudgeAnalytics eager2 = EagerSingletonJudgeAnalytics.getInstance();
        eager2.countRun();

        System.out.println("Eager Singleton 1 Run count: " + eager1.getRun()); // Prints 2
        System.out.println("Eager Singleton 2 Run count: " + eager2.getRun()); // Prints 2
        if (eager1 == eager2) {
            System.out.println("Both eager singleton objects are exactly the same instance in memory!");
        }
        System.out.println("\n--- The Thread-Safe Singleton Solution ---");
        ThreadSafeJudgeAnalytics safe1 = ThreadSafeJudgeAnalytics.getInstance();
        safe1.countRun();
        
        ThreadSafeJudgeAnalytics safe2 = ThreadSafeJudgeAnalytics.getInstance();
        safe2.countRun();

        System.out.println("Thread-Safe Singleton 1 Run count: " + safe1.getRun()); // Prints 2
        System.out.println("Thread-Safe Singleton 2 Run count: " + safe2.getRun()); // Prints 2
        if (safe1 == safe2) {
            System.out.println("Both thread-safe singleton objects are exactly the same instance in memory!");
        }
        System.out.println("\n--- The Double-Checked Locking Solution ---");
        DoubleCheckJudgeAnalytics doubleCheck1 = DoubleCheckJudgeAnalytics.getInstance();
        doubleCheck1.countRun();
        
        DoubleCheckJudgeAnalytics doubleCheck2 = DoubleCheckJudgeAnalytics.getInstance();
        doubleCheck2.countRun();

        System.out.println("Double-Check Singleton 1 Run count: " + doubleCheck1.getRun()); // Prints 2
        System.out.println("Double-Check Singleton 2 Run count: " + doubleCheck2.getRun()); // Prints 2
        if (doubleCheck1 == doubleCheck2) {
            System.out.println("Both double-check singleton objects are exactly the same instance in memory!");
        }
        System.out.println("\n--- The Bill Pugh Singleton Solution ---");
        BillPughJudgeAnalytics bill1 = BillPughJudgeAnalytics.getInstance();
        bill1.countRun();
        
        BillPughJudgeAnalytics bill2 = BillPughJudgeAnalytics.getInstance();
        bill2.countRun();

        System.out.println("Bill Pugh Singleton 1 Run count: " + bill1.getRun()); // Prints 2
        System.out.println("Bill Pugh Singleton 2 Run count: " + bill2.getRun()); // Prints 2
        if (bill1 == bill2) {
            System.out.println("Both Bill Pugh singleton objects are exactly the same instance in memory!");
        }
    }
}
