package org.example;

import java.util.concurrent.Semaphore;

class TUFPlusAccount {
    private final Semaphore deviceSlots;

    public TUFPlusAccount(int maxDevices) {
        this.deviceSlots = new Semaphore(maxDevices);
    }

    public boolean login(String user) {
        System.out.println(user + "  trying to login..... ");

        if (deviceSlots.tryAcquire()) {
            System.out.println(user + " : Login Successful");
            return true;
        } else {
            System.out.println(user + " : Login failed. Max devices already logged in");
            return false;
        }
    }

    public void logout(String user) {
        System.out.println(user + "  trying to logout");
        deviceSlots.release();
        System.out.println(user + "  has been logged out");
    }

}

class TUFPlusAccountSimulation {

    public static void main(String[] args) {
        TUFPlusAccount account = new TUFPlusAccount(2);
        String[] users = { "Alice", "Bob", "Charlie", "David", "Emily" };

        for (String user : users) {
            new Thread(() -> {
                boolean loggedIn = account.login(user);
                if (loggedIn) {
                    try {
                        // Simulate device usage session
                        Thread.sleep((long) (Math.random() * 2000));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    account.logout(user);
                }
            }).start(); // start() is required to run the thread
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TUFPlusAccountSimulation.main(args);
    }
}
