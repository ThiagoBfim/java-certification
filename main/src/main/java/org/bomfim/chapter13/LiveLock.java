package org.bomfim.chapter13;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class LiveLock {


    // These flags represent each thread's 'desire' to proceed
    private static AtomicBoolean aWantsToProceed = new AtomicBoolean(false);
    private static AtomicBoolean bWantsToProceed = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {

        // Thread A's logic
        Thread threadA = new Thread(() -> {
            System.out.println("Thread A started.");
            while (!Thread.currentThread().isInterrupted()) { // Loop endlessly until interrupted
                aWantsToProceed.set(true); // A signals it wants to proceed

                if (bWantsToProceed.get()) { // If B is *also* trying to proceed
                    System.out.println("A: Oh, B is trying too. I'll yield.");
                    aWantsToProceed.set(false); // A backs off
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    continue; // Retry the loop
                }

                // If B is NOT trying, A proceeds (makes "progress")
                System.out.println("A: Going! (B is not trying)");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } // Simulate work
                aWantsToProceed.set(false); // A finishes its turn, backs off for next cycle
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } // Yield again
            }
            System.out.println("Thread A stopped.");
        });

        // Thread B's logic (mirror image of Thread A)
        Thread threadB = new Thread(() -> {
            System.out.println("Thread B started.");
            while (!Thread.currentThread().isInterrupted()) {
                bWantsToProceed.set(true); // B signals it wants to proceed

                if (aWantsToProceed.get()) { // If A is *also* trying to proceed
                    System.out.println("B: Oh, A is trying too. I'll yield.");
                    bWantsToProceed.set(false); // B backs off
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    continue; // Retry the loop
                }

                // If A is NOT trying, B proceeds (makes "progress")
                System.out.println("B: Going! (A is not trying)");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } // Simulate work
                bWantsToProceed.set(false); // B finishes its turn, backs off for next cycle
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } // Yield again
            }
            System.out.println("Thread B stopped.");
        });

        System.out.println("Livelock simulation started. Observe the output.");
        System.out.println("It should show threads repeatedly yielding without completing a main task.");

        threadA.start(); // Start Thread A
        threadB.start(); // Start Thread B

        // Let them run for a short period to observe the livelock
        Thread.sleep(5000); // Run for 5 seconds

        System.out.println("\nStopping livelock simulation...");
        threadA.interrupt(); // Interrupt threads to stop their loops
        threadB.interrupt();

        threadA.join(); // Wait for threads to finish
        threadB.join();

        System.out.println("Simulation finished.");
    }
}