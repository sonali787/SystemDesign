package org.example;

class CoffeeMachine {
    private boolean isCoffeeReady = false;

    public synchronized void makeCoffee() throws InterruptedException {

        while (isCoffeeReady) {
            wait();
        }

        System.out.println("Coffee is begin Prepared ");
        Thread.sleep(1000);
        isCoffeeReady = true;
        System.out.println("Coffee is ready ");
        notify();

    }

    public synchronized void drinkCoffee() throws InterruptedException {
        while (isCoffeeReady == false) {
            wait();
        }

        System.out.println("Coffee is begin Consumed ");
        Thread.sleep(1000);
        isCoffeeReady = false;
        System.out.println("Coffee is consumed ");
        notify();
    }
}

class Producer implements Runnable {
    private CoffeeMachine coffeeMachine;

    public Producer(CoffeeMachine coffeeMachine) {

        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void run() {
        try {
            coffeeMachine.makeCoffee();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private CoffeeMachine coffeeMachine;

    public Consumer(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void run() {
        try {
            coffeeMachine.drinkCoffee();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CoffeeMachineStore {

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Producer producer = new Producer(coffeeMachine);
        Consumer consumer = new Consumer(coffeeMachine);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }

}

public class Main {
    static void main() {

        IO.println(String.format("Hello and welcome!"));

        for (int i = 1; i <= 5; i++) {
            IO.println("i = " + i);
        }
    }
}
