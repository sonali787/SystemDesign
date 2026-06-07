package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("--- 🚨 WITHOUT Factory Pattern ---");
        // The client creates LogisticService which internally
        // has the tight coupling and object creation logic.
        LogisticService service = new LogisticService("AIR");
        
        System.out.println("\n--- ✅ WITH Factory Pattern ---");
        // The client creates the new service, which delegates 
        // the creation logic to LogisticsFactory.
        LogisticServiceWithFactory factoryService1 = new LogisticServiceWithFactory("ROAD");
        LogisticServiceWithFactory factoryService2 = new LogisticServiceWithFactory("TRAIN");
    }
}
