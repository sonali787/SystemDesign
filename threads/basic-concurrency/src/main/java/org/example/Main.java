package org.example;

import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private int count = 0;

    public void increment() {
        count = count + 1;
    }

    public int getCount() {
        return count;
    }
}

// class Task implements Runnable{

// Counter counter = new Counter();

// @Override
// public void run() {
// for(int i=0;i<100;i++){
// counter.increment();
// }
// }
// }

class RaceConditionDemo {
    public static void main() throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println(counter.getCount());
    }
}

class SyncCounter {
    private int count = 0;

    // syncronized methods

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class SyncCounterBlock {

    private int count = 0;

    public void increment() {

        // sycronized block (critical section)
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

class SyncronizedBlockDemo {
    public static void main(String[] args) throws InterruptedException {

        SyncCounterBlock syncCounterBlock = new SyncCounterBlock();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                syncCounterBlock.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println(syncCounterBlock.getCount());
    }
}

class SyncronizedDemo {
    public static void main(String[] args) throws InterruptedException {

        SyncCounter syncCounter = new SyncCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                syncCounter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println(syncCounter.getCount());

    }
}

class VolatileCounter {
    private volatile int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class VolatileCounterDemo {
    public static void main(String[] args) throws InterruptedException {

        VolatileCounter volatileCounter = new VolatileCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                volatileCounter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println(volatileCounter.getCount());
    }
}

class AtomicCounter {
    private AtomicInteger likes = new AtomicInteger(0);

    public void increment() {
        int prev, next;
        do {
            prev = likes.get();
            next = prev + 1;
        } while (!likes.compareAndSet(prev, next));

    }

    public int getCount() {
        return likes.get();
    }
}

class AtomicCounterDemo {
    public static void main(String[] args) throws InterruptedException {

        AtomicCounter atomicCounter = new AtomicCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                atomicCounter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println(atomicCounter.getCount());
    }
}

public class Main {
    static void main() {

        IO.println(String.format("Hello and welcome!"));
    }
}
