package org.bomfim.chapter13;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadLock {

    public static void main(String[] args) {
        // Create participants and resources
        var foxy = new Fox("Foxy");
        var tails = new Fox("Tails");
        var food = new Food(); // The shared food resource
        var water = new Water(); // The shared water resource

        // Process data using an ExecutorService
        // Using a fixed thread pool is suitable for this demo,
        // as we only need two threads for Foxy and Tails.
        try (var service = Executors.newFixedThreadPool(2)) {
            // Foxy tries to eat then drink
            service.submit(() -> foxy.eatAndDrink(food, water));

            // Tails tries to drink then eat
            service.submit(() -> tails.drinkAndEat(food, water));

            // Give some time for the deadlock to occur and observe the output
            // You might need to adjust this depending on your system's speed.
            try {
                System.out.println("Main: Waiting for 5 seconds to observe deadlock...");
                service.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.err.println("Main thread interrupted while waiting for termination.");
                Thread.currentThread().interrupt();
            }

        } finally {
            // In a real application, you'd ensure the service is properly shut down.
            // For this deadlock demo, threads will be blocked indefinitely if deadlock occurs.
            System.out.println("Main: ExecutorService closed. If output stopped, a deadlock likely occurred.");
        }
    }

    // Represents the food resource
    static class Food {}

    // Represents the water resource
    static class Water {}

    // Represents a fox that can eat and drink
    public record Fox(String name) {

        // Fox's preferred order: food then water
        public void eatAndDrink(Food food, Water water) {
            synchronized (food) { // Acquire lock on food first
                System.out.println(name() + " Got Food!");
                move(); // Simulate time to move

                synchronized (water) { // Then try to acquire lock on water
                    System.out.println(name() + " Got Water!");
                    System.out.println(name() + " Finished eating and drinking!");
                }
            }
        }

        // Fox's preferred order: water then food
        public void drinkAndEat(Food food, Water water) {
            synchronized (water) { // Acquire lock on water first
                System.out.println(name() + " Got Water!");
                move(); // Simulate time to move

                synchronized (food) { // Then try to acquire lock on food
                    System.out.println(name() + " Got Food!");
                    System.out.println(name() + " Finished eating and drinking!");
                }
            }
        }

        // Simulates the time taken to move between resources
        public void move() {
            try {
                Thread.sleep(100); // Sleep for 100 milliseconds
            } catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
                System.err.println(name() + " was interrupted while moving.");
            }
        }

    }
}
