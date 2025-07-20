package org.bomfim.chapter13;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exercises {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        threadPriority();
        creatingThreads();
        executorService();
        threadPool();

        threadSafe();
        lock();
        cyclicBarrier();

        concurrentCollection();

        ThreadLocalRandom.current().ints();

//        List.of(1,2,3).parallelStream();
//        List.of(1,2,3).stream().parallel();

        Thread.ofPlatform().daemon(true).start(() -> {
            System.out.println("Daemon started");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon end");
        });

        ThreadLocalRandom
                .current()
//                .ints(lowestInclusive, highestExclusive)
//                .ints(numbersIntsToInclude)
//                .ints(numberIntsToInclude, lowestInclusive, highestExclusive)
//                .nextInt(highestExclusive)
//                .nextInt(lowestInclusive, highestExclusive)
                .ints()
                .limit(5)
                .forEach(System.out::println); // Prints 5 random ints

        System.out.println("Parallel Stream");
        Stream.generate(ThreadLocalRandom::current)
                .parallel()
                .map(ThreadLocalRandom::nextInt)
                .limit(5)
                .forEach(System.out::println);

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(value -> value % 2 == 0)
                .forEach(System.out::print); //246810

        System.out.println();
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .parallel()
                .filter(value -> value % 2 == 0)
                .forEach(System.out::print); //610428

        System.out.println();
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .parallel()
                .filter(value -> value % 2 == 0)
                .forEachOrdered(System.out::print); //246810

        System.out.println("\nSorted is not guaranteed in Parallel Stream");
        IntStream.range(1, 100).parallel().sorted().forEach(System.out::print); //The sorted is not guaranteed
        System.out.println("\nFind first is guaranteed in Parallel Stream");
        System.out.println(IntStream.range(1, 100).skip(5).limit(2).findFirst().getAsInt()); //6
        System.out.println("\nFind first is guaranteed in Parallel Stream");
        System.out.println(IntStream.range(1, 100).parallel().skip(5).limit(2).findFirst().getAsInt());  //6
        System.out.println("\nFind first is guaranteed in Parallel Stream");

        System.out.println(IntStream.range(1, 100).parallel().skip(5).limit(2).unordered().findFirst().getAsInt()); //7

        System.out.println("\nReduce method");
        System.out.println("""
                In a serial stream, wolf is built one character at a time. In a parallel stream, the intermediate values wo and lf are created and then combined.
                With parallel streams, we now have to be concerned about order. 
                What if the elements of a string are combined in the wrong order to produce wlfo or flwo?
                The Stream API prevents this problem while still allowing streams to be processed in parallel, as long as you follow one simple rule:
                make sure that the accumulator and combiner produce the same result regardless of the order they are called in.
                """);
        System.out.println(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .parallelStream() //-5
//              .stream()//-55
                .reduce(0, (a, b) -> (a - b))); // PROBLEMATIC ACCUMULATOR

        System.out.println(List.of("w", "o", "l", "f")
                .parallelStream()
                .reduce("X", String::concat)); // XwXoXlXf


        System.out.println(List.of("w", "o", "l", "f", "o", "l", "d")
                .parallelStream()
                .reduce("", String::concat)); // wolfold

        System.out.println(List.of("w", "o", "l", "f", "o", "l", "d")
                .parallelStream()
                .reduce("",
                        (s1, c) -> s1 + c,
                        (s2, s3) -> s2 + s3)); // wolfold

        TreeSet<Object> collect = Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(100)
                .collect(
                        TreeSet::new,
//                ConcurrentSkipListSet::new,
                        Set::add,
                        Set::addAll);
        System.out.println(collect);

        Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
        Map<Integer, String> map = ohMy.collect(Collectors
                .toConcurrentMap(String::length,
                        k -> k,
                        (s1, s2) -> s1 + "," + s2));
        System.out.println(map); //{5=lions,bears, 6=tigers}

    }

    private static void concurrentCollection() {
        var foods = new HashSet<String>();
        foods.add("Banana");
        foods.add("Tomato");
        for (String food : foods) {
//            foods.add("Orange"); //java.util.ConcurrentModificationException
        }

        var foods2 = new ConcurrentLinkedDeque<String>();
        foods2.add("Banana");
        foods2.add("Tomato");
        for (String food : foods2) {
            if (!foods2.contains("Orange")) { //Otherwise infinite loop
                foods2.add("Orange");
            }
        }

        List<Integer> integers = List.of(1, 2, 3, 4);
        List<Integer> integersConcurrent = new CopyOnWriteArrayList<>(integers);
        for (Integer integer : integersConcurrent) {
            integersConcurrent.add(5);
        }
        System.out.println(integersConcurrent); //1, 2, 3, 4, 5, 5, 5, 5
        System.out.println(integers);

        System.out.println("\nConcurrentSkipListSet");

        Set<Integer> integersConcurrent2 = new ConcurrentSkipListSet<>(integers);
        for (Integer integer : integersConcurrent2) {
            integersConcurrent2.add(6);
        }
        System.out.println(integersConcurrent2); //1, 2, 3, 4, 6
        System.out.println(integers); //[1, 2, 3, 4]


//        Collections.synchronizedCollection();
    }


    private static void cyclicBarrier() {
        try (var exec = Executors.newFixedThreadPool(4)) {
            CyclicBarrier c1 = new CyclicBarrier(4, () -> System.out.println("Do first action"));
            CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("Do second action"));
            for (int i = 0; i < 4; i++) {
                exec.submit(() -> {
                    try {
                        System.out.println("Starting task");
                        c1.await();
                        c2.await();
                        System.out.println("Ending task");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
//            c2.await(); //Compile error, must have try-catch.
//            c2.await(); //With try-catch it get stuck, because it should run 4 times.
        }
    }

    private static void lock() throws InterruptedException {
        System.out.println("Lock");
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.lock();
        lock.lock();
        System.out.println("Locked: " + lock.isLocked());
        lock.unlock();
        System.out.println("Locked: " + lock.isLocked());
        boolean result = lock.tryLock();
        System.out.println("Lock try: " + result);
        System.out.println("Lock try with tmeout: " + lock.tryLock(10, TimeUnit.SECONDS));
//        lock.unlock(); //java.lang.IllegalMonitorStateException

        var readWriteLock = new ReentrantReadWriteLock();
//        readWriteLock.writeLock().lock();
        readWriteLock.writeLock().lock();
        readWriteLock.readLock().lock();
        System.out.println(readWriteLock.isWriteLocked());   // true
        System.out.println(readWriteLock.getReadLockCount()); // 1

        readWriteLock.readLock().unlock();
        System.out.println(readWriteLock.isWriteLocked()); //false
        System.out.println(readWriteLock.getReadLockCount()); //1

//        readWriteLock.readLock().lock();
//        readWriteLock.writeLock().lock();
//        System.out.println("Never get there");
    }

    private static void threadSafe() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndAccumulate(1, (a, b) -> a + b);
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.getAndDecrement());
        System.out.println(atomicInteger.decrementAndGet());
        System.out.println(atomicInteger.incrementAndGet());
        atomicInteger.set(5);
        System.out.println(atomicInteger.get());

        System.out.println("Thread Safe");
        AtomicLong atomicLong = new AtomicLong();
        AtomicLong atomicSyncLong = new AtomicLong();
        AtomicLong atomicSyncLong2 = new AtomicLong();
        try (ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20)) {
            for (int i = 0; i < 20; i++) {
                scheduledExecutorService.execute(() -> {
                    System.out.print(atomicLong.incrementAndGet() + " "); //2 1 3 6 5 4 7 8 9 10 11 12 13 14 15 16 17 18 19 20  Random result
                });
            }
            System.out.println();
            for (int i = 0; i < 20; i++) {
                scheduledExecutorService.execute(() -> {
                    increment(atomicSyncLong); //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
                });
            }
            System.out.println();
            for (int i = 0; i < 20; i++) {
                scheduledExecutorService.execute(() -> {
                    incrementSyncronized(atomicSyncLong2); //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
                });
            }
        }


        AtomicReference<String> result = new AtomicReference<>();
        result.get();
    }

    void sing2() {
        synchronized (this) {
            System.out.print("La la la!");
        }
    }

    synchronized void sing() {
        System.out.print("La la la!");
    }

    static void dance2() {
        synchronized (Exercises.class) {
            System.out.print("Time to dance!");
        }
    }

    static synchronized void dance() {
        System.out.print("Time to dance!");
    }

    private synchronized static void incrementSyncronized(AtomicLong atomicLong) {
        System.out.print(atomicLong.incrementAndGet() + " ");
    }

    private static void increment(AtomicLong atomicLong) {
        synchronized (atomicLong) {
            System.out.print(atomicLong.incrementAndGet() + " ");
        }
    }

    private static void threadPool() throws InterruptedException, ExecutionException {
        try (ExecutorService executorService = Executors.newScheduledThreadPool(1)) {
//            ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(() -> 10, 5, TimeUnit.MILLISECONDS); //DOES NOT COMPILE
        }
        try (ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1)) {
            ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(() -> 10, 5, TimeUnit.MILLISECONDS);
            System.out.println(schedule.get());
            scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Schedule2"), 5, 5, TimeUnit.MILLISECONDS);
            scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("Schedule3"), 5, 5, TimeUnit.MILLISECONDS);
            scheduledExecutorService.schedule(() -> System.out.println("Schedule1"), 1000, TimeUnit.MILLISECONDS);
            Thread.sleep(10);
            //scheduledExecutorService.scheduleWithFixedDelay = Creates and executes Runnable task after given initial delay and subsequently with given delay between termination of one execution and commencement of next
        }
    }

    private static void executorService() throws InterruptedException, ExecutionException {
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            System.out.println("start");
            executorService.execute(() -> System.out.println("first"));
            executorService.execute(() -> System.out.println("second"));
            System.out.println("end"); //The output is unpredictable
            Future<?> third = executorService.submit(() -> System.out.println("third"));
            System.out.println(third.get());
        }

        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            Future<?> f1 = executorService.submit(() -> System.out.println("Executor"));//Runnable
            Future f2 = executorService.submit(() -> 5); //Callable
            Future<Long> f3 = executorService.submit(() -> {
                if(true) {
                    throw new IOException("A");
                }
                return 1L;
            }); //Callable

             Future f4 = executorService.submit(new Runnable() {
                 @Override
                 public void run() {
//                     if(true) {
//                         throw new IOException("A"); //DOES NOT WORK
//                     }
                     System.out.println(Thread.currentThread().getName());
                 }
             });
            Object o = f1.get();
            System.out.println(o); //null
            System.out.println(f2.get()); //5
        }

//        Executors.newCachedThreadPool().submit(() -> {});//Get stuck forever
    }

    private static void creatingThreads() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        };
        var builder = Thread.ofPlatform();
        Thread thread = builder.start(runnable);
        var virtualBuilder = Thread.ofVirtual();
        Thread virtualThread = virtualBuilder.start(runnable);
//        virtualThread.start(); //Exception in thread "main" java.lang.IllegalThreadStateException: Already started
        Thread thread2 = new Thread(runnable);
        thread2.start();


        new Thread(() -> System.out.println("Executing")).start();
        new Thread(() -> System.out.println("Executing2")).run();
        new Thread(() -> System.out.println("Executing")).start();
        new Thread(() -> System.out.println("Executing2")).run();
        Thread.sleep(50);
        new Thread(() -> System.out.println("Executing")).start();
        new Thread(() -> System.out.println("Executing2")).run();
        new Thread(() -> System.out.println("Executing")).start();
        new Thread(() -> System.out.println("Executing2")).run();

        var threadInterrupt = Thread.ofPlatform().start(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted !");
            }
        });
        threadInterrupt.interrupt();
    }

    private static void threadPriority() {
        var thread2 = new Thread(() -> System.out.println("Less Important"));
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread2.start();

        var thread1 = new Thread(() -> System.out.println("Super Important"));
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
    }
}
