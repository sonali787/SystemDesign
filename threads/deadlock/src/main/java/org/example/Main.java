package org.example;

class BankAccount {
    private final String name;
    private int balance;

    public BankAccount(String name, int balance) {
        this.balance = balance;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }
}

class TransferTask implements Runnable {

    private final BankAccount fromAccount;
    private final BankAccount toAccount;
    private final int amount;

    public TransferTask(BankAccount fromAccount, BankAccount toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        synchronized (fromAccount) {
            System.out.println(Thread.currentThread().getName() + " locked from account: " + fromAccount.getName());
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (toAccount) {
                System.out.println(Thread.currentThread().getName() + " locked to account: " + toAccount.getName());
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println(Thread.currentThread().getName() + " transferred " + amount + " from "
                        + fromAccount.getName() + " to " + toAccount.getName());
            }
        }
    }
}

class DeadlockSimulation {
    public static void main(String[] args) {
        BankAccount accountA = new BankAccount("Account-A", 1000);
        BankAccount accountB = new BankAccount("Account-B", 2000);

        Thread thread1 = new Thread(new TransferTask(accountA, accountB, 100), "Thread-1");
        Thread thread2 = new Thread(new TransferTask(accountB, accountA, 200), "Thread-2");

        thread1.start();
        thread2.start();
    }
}

public class Main {
    static void main() {

        IO.println(String.format("Hello and welcome!"));

    }
}
