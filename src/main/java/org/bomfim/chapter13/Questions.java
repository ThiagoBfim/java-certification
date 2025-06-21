package org.bomfim.chapter13;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Questions {

//    public static void main(String[] args) {
//        var s = Executors.newVirtualThreadPerTaskExecutor();
//        DoubleStream.of(3.14159, 2.71828)   // b1
//                .forEach(c -> s.submit(          // b2
//                        () -> System.out.println(10 * c))); // b3
//        s.execute(() -> System.out.println("Printed"));
//    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (ScheduledExecutorService service =         // w1
                     Executors.newSingleThreadScheduledExecutor()) {
            service.scheduleWithFixedDelay(() -> {
                System.out.println("Open Zoo");
//                return null;                     // w2
            }, 0, 1, TimeUnit.MINUTES);
            var result = service.submit(() ->  // w3
                    System.out.println("Wake Staff"));
            System.out.println(result.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        var value1 = new AtomicLong(0);
        final long[] value2 = {0};
        IntStream.iterate(1, i -> 1).limit(100).parallel()
                .forEach(i -> value1.incrementAndGet());
        IntStream.iterate(1, i -> 1).limit(100).parallel()
                .forEach(i -> ++value2[0]);
        System.out.println(value1 + " " + value2[0]);


        SequencedCollection<Integer> lions = new ArrayList<>(List.of(1, 2, 3));
        SequencedCollection<Integer> tigers = new CopyOnWriteArrayList<>(lions);
        Collection<Integer> bears = new ConcurrentSkipListSet<>();
        bears.addAll(lions);
        for (Integer item: tigers) tigers.add(4); // x1
        for (Integer item: bears) bears.add(5);   // x2
        System.out.println(lions.size() + " " + tigers.size()
                + " " + bears.size());

//        System.out.print(List.of("duck","flamingo","pelican")
//                        .parallelStream().parallel()   // q1
//                        .reduce(0,
//                                (c1, c2) -> c1.length() + c2.length(),  // q2
//                (s1, s2) -> s1 + s2));      // q3

        Callable<?> x = () -> "The" + "Zoo";
        Callable<?> x2 = () -> {return x;};
        Callable<?> x3 = () -> {return null;};

        var s = Executors.newVirtualThreadPerTaskExecutor();
        DoubleStream.of(3.14159, 2.71828)   // b1
                .forEach(c -> s.submit(          // b2
                        () -> System.out.println(10 * c))); // b3
        s.execute(() -> System.out.println("Printed"));

        Future<?> abc = s.submit(() -> System.out.println("abc"));
        Future<?> five = s.submit(() -> 5);
        System.out.println(abc.get()); //null
        System.out.println(five.get()); //5
        System.out.println("END");


    }
}
