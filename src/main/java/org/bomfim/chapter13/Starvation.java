package org.bomfim.chapter13;


import java.util.concurrent.locks.ReentrantLock;


public class Starvation {

    // --- Raciocínio (Reasoning) ---
    // 1. Shared Resource: We use a ReentrantLock as the shared resource.
    // 2. Non-Fair Lock: Crucially, 'false' in `new ReentrantLock(false)` makes the lock *non-fair*.
    //    A non-fair lock doesn't guarantee FIFO access. It allows threads that
    //    just released the lock (or new contenders) to "jump the queue" and re-acquire it.
    // 3. Two Competing Threads: "GreedyWorker" and "StarvedWorker".
    // 4. Constant Contention: Both threads repeatedly try to acquire the same lock.
    //    Because the lock is non-fair, the JVM's scheduler might consistently favor
    //    one thread (the "greedy" one) over the other (the "starved" one) when
    //    the lock becomes available. The "greedy" thread often re-acquires immediately.
    // 5. Observation: We count how many times each thread successfully acquires the lock.
    //    You will typically see the "StarvedWorker" acquiring the lock significantly
    //    fewer times, even though both tried the same number of times.

    private static final ReentrantLock sharedLock = new ReentrantLock(false); // 'false' for NON-FAIR lock

    public static void main(String[] args) throws InterruptedException {

        final int NUM_ATTEMPTS = 500; // Each worker will attempt to acquire the lock this many times

        // Thread 1: The "Greedy" Worker
        Thread greedyWorker = new Thread(() -> {
            int acquiredCount = 0;
            for (int i = 0; i < NUM_ATTEMPTS; i++) {
                try {
                    sharedLock.lock(); // Blocks until the lock is acquired
                    acquiredCount++;
                    // No Thread.sleep() here to simulate quick release and immediate re-contention
                } finally {
                    sharedLock.unlock(); // Always release the lock
                }
            }
            System.out.println("GreedyWorker finished. Acquired lock " + acquiredCount + " times.");
        }, "GreedyWorker");

        // Thread 2: The "Starved" Worker
        Thread starvedWorker = new Thread(() -> {
            int acquiredCount = 0;
            for (int i = 0; i < NUM_ATTEMPTS; i++) {
                try {
                    sharedLock.lock(); // Blocks until the lock is acquired
                    acquiredCount++;
                } finally {
                    sharedLock.unlock();
                }
            }
            System.out.println("StarvedWorker finished. Acquired lock " + acquiredCount + " times.");
        }, "StarvedWorker");

        System.out.println("--- Starvation Example ---");
        System.out.println("Using a non-fair ReentrantLock. Expect 'StarvedWorker'" +
                "\nto acquire the lock much less often than 'GreedyWorker'.");

        greedyWorker.start(); // Start the greedy worker
        starvedWorker.start(); // Start the starved worker

        // Wait for both threads to complete their attempts
        greedyWorker.join();
        starvedWorker.join();

        System.out.println("\n--- Simulation finished ---");
    }
}
