package org.example;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class StockData {
    private double price = 100.0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void updatePrice(double newPrice) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Updating price to " + newPrice);
            this.price = newPrice;
            System.out.println(Thread.currentThread().getName() + " Price updated to " + newPrice);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public double getPrice() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Getting price " + this.price);
            return this.price;
        } finally {
            lock.readLock().unlock();
        }
    }

}

class StockMarketSimulation {
    public static void main(String[] args) {
        StockData stockData = new StockData();

        Thread reader1 = new Thread(() -> stockData.getPrice(), "Reader-1");
        Thread reader2 = new Thread(() -> stockData.getPrice(), "Reader-2");
        Thread writer1 = new Thread(() -> stockData.updatePrice(150.0), "Writer-1");
        Thread reader3 = new Thread(() -> stockData.getPrice(), "Reader-3");

        reader1.start();
        reader2.start();
        writer1.start();
        reader3.start();
    }

}

public class Main {
    static void main() {
        // TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the
        // highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));

    }
}
