package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class EmailService {

    public static final ExecutorService excecutor = Executors.newFixedThreadPool(10);

    public static void sendEmail(String recipient) {

        excecutor.execute(() -> {
            System.out.println("Sending email to " + recipient + " on " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Email sent to " + recipient);

        });

    }
}

class FutureExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future = executor.submit(() -> {
            System.out.println("Calculating sum on " + Thread.currentThread().getName());
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        });

        try {
            System.out.println("Sum = " + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }

}

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        for (int i = 1; i <= 20; i++) {
            EmailService.sendEmail("user" + i + "@gmail.com");
        }
        EmailService.excecutor.shutdown();
    }
}
