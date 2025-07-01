1. D,F                                                 ✓
2. A,D?                                                ✓ when tryLock() returns false at least once), the unlock() method on line 10 will throw an IllegalMonitorStateException at runtime
3. B,C,E,F ? Checked exception                         ❌ Only Callable is capable of throwing checked exceptions
4. F ExecutorService ?                                 ❌ 
5. D?                                                  ❌ // final long[] value2 = {0}; ++value2[0]; Works 
6. C,E                                                 ✓
7. D                                                   ✓
8. C *                                                 ❌ Be wary of run() vs. start() on the exam! The method looks like it executes a task concurrently, but it runs synchronously.

Thread.run() //Sync
Thread.start() // Async

9. B,D                                                 ✓ //f a task is submitted when no threads are available, the executor adds the task to an internal queue and completes when there is an available thread.
10. G                                                  ❌ //ConcurrentSkipListSet avoid infinite loop when adding a new element in the list
11. F                                                  ❌ //findAny() is free to select any element
12. B,E*                                               ❌ //service.awaitTermination(2, TimeUnit.SECONDS); ends the service without throwing exception
13. F                                                  ❌ Tricky question with Stream.reduce
14. C,E                                                ✓ 
15. B                                                  ❌ groupingByConcurrent
16. A,B,D,E *                                          ❌  //Does not exist = Executors.newPlatformThreadPool()
17. E                                                  ❌ Tricky
18. C,D,G *                                            ❌ //Works: Callable<?> x = () -> "The" + "Zoo";

        Callable<?> x = () -> "The" + "Zoo";
        Callable<?> x2 = () -> {return x;};
        Callable<?> x3 = () -> {return null;};

19. A,E,G                                              ❌
20. C                                                  ✓

Executor.submit() //returns Future
Executor.execute() //returns void

21. A,D *                                              ✓ //The findFirst() method guarantees the first element in the stream will be returned, whether it is serial or parallel,
22. B ? Single Thread                                  ✓
23. F.                                                 ✓
24. A,D,F                                              ❌ synchronized in a static and a not static method are on different objects
25. E,F submit vs execute ?                            ✓


Result:

* Correct: 12
* Mistake: 13
* Score: 48%
* Passing Score: 68%
* Took: 47min