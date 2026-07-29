package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class TicketBooking {
    private int availableSeats = 1;
    private final ReentrantLock lock = new ReentrantLock();

    public void bookTicket(String user) {
        System.out.println(user + " is trying to book...");
        lock.lock();
        try {
            System.out.println(user + " Lock Acquired!");
            if (availableSeats > 0) {
                System.out.println(user + " Successfully boooked the ticket.");
                availableSeats--;
            } else {
                System.out.println(user + " Could not able to book the seat ... Sorry, No Seats Left!");
            }
        } finally {
            lock.unlock();
            System.out.println(user + " Released Lock!");
        }
    }
}

class ExpiringReentrantLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private volatile boolean isLocked = false;

    public boolean tryLockWithExpiry(long expiryDuration) {
        boolean acquired = lock.tryLock();
        if (acquired) {

            isLocked = true;

            scheduler.schedule(() -> {
                if (lock.isHeldByCurrentThread() || isLocked) {
                    System.out.println(" Auto release lock after timeout");
                    unlockSafey();
                    isLocked = false;
                }
            }, expiryDuration, TimeUnit.MILLISECONDS);
            return acquired;
        }
        return false;

    }

    public void unlockSafey() {
        if (lock.isHeldByCurrentThread() || isLocked) {
            isLocked = false;
            if (lock.isHeldByCurrentThread()) {
                System.out.println("Releasing lock");
                lock.unlock();
            }
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }

}

class LockMain {

    public static void main(String[] args) {

        ExpiringReentrantLock lock = new ExpiringReentrantLock();

        Thread user1 = new Thread(() -> {
            if (lock.tryLockWithExpiry(1000)) {
                System.out.println("User 1 Lock Acquired!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlockSafey();
            }
        });

        Thread user2 = new Thread(() -> {
            if (lock.tryLockWithExpiry(1000)) {
                System.out.println("User 2 Lock Acquired!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlockSafey();
            }
        });

        user1.start();
        user2.start();

        try {
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.shutdown();
    }

}

class TicketBookingTryOutWithTime {

    private int availableSeats = 1;
    private final ReentrantLock lock = new ReentrantLock();

    public void bookTicket(String user) {
        System.out.println(user + " Trying to book ..... ");

        boolean lockAcquired = false;

        try {
            lockAcquired = lock.tryLock(2, TimeUnit.MILLISECONDS);
            if (lockAcquired) {
                System.out.println(user + " Lock Acquired!");

                if (availableSeats > 0) {
                    System.out.println(user + " Successfully boooked the ticket.");
                    availableSeats--;
                } else {
                    System.out.println(user + " Could not able to book the seat ... Sorry, No Seats Left!");
                }
            } else {
                System.out.println(user + " Could not able to book the seat ... Sorry, Lock is held by someone else!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(user + " Error in booking!");
        } finally {
            if (lockAcquired) {
                System.out.println(user + " Releasing lock");
                lock.unlock();
            }
        }

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
