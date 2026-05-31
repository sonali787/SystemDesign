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
    }
}
