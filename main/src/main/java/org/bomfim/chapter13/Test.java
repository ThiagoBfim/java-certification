package org.bomfim.chapter13;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class Test {

    public static void main(String[] args)  {
        try {
            shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void shutdown() throws InterruptedException {
        var service = Executors.newFixedThreadPool(4);
        try {
            service.execute(() -> takeNap(5000));
            service.execute(() -> takeNap(5000));
            service.submit(() -> takeNap(5000));
            Future<Integer> submit = service.submit(() -> takeNap(5000));
            System.out.println(submit);
        } finally {
            service.shutdown();
        }
        service.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("DONE!");
        service.shutdownNow();
    }

    public void refactored() {
        try (var service = Executors.newFixedThreadPool(4)) {
            service.execute(() -> takeNap(5000));
            service.execute(() -> takeNap(5000));
            service.execute(() -> takeNap(5000));
        }
        System.out.println("DONE!");
    }


    public static int takeNap(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("end");
        }
        return 5;
    }
}
