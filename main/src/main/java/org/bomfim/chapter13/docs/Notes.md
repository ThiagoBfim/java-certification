# Chapter 13

## Topics

* Daemon thread: Thread that will not prevent the JVM from exiting when the program finishes
* Carrier thread: System thread that runs virtual threads when they are not blocked Daemon thread
* Platform thread: Thread that is scheduled by the operating system
* Process: Group of associated threads that execute in the same shared environment
* System thread: Thread created by the JVM that runs in the background of the application, such as the garbage collector
* Task: Single unit of work performed by a thread Thread Smallest unit of execution that can be scheduled
* User-defined thread: Thread created by the application developer to accomplish a specific task
* Virtual thread: Lightweight thread that is mapped to a carrier thread when needed to run

### Platform vs Virtual Threads

> The property of executing multiple threads and processes at the same time is referred to as concurrency

It is possible to configure a thread priority

        var thread2 = new Thread(() -> System.out.print("Less Important"));
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread2.start();

> Note that thread priority is just a suggestion and the JVM does not guarantee the order that the threads will execute.

Creating Threads:

        Runnable runnable = () -> {};
        var builder = Thread.ofPlatform();
        Thread thread = builder.start(runnable);
        var virtualBuilder = Thread.ofVirtual();
        Thread virtualThread = virtualBuilder.start(runnable);
        Thread thread2 = new Thread(runnable);

Prior to Java 21, it was advisable to create a thread with the new Thread() constructor call,
in which the task is defined when the thread is created. Starting with Java 21,
the factory method is preferable since it is clearer which type of thread you are getting.

> The priority for virtual threads is always 5 and cannot be changed.

### Daemon Threads

A daemon thread is one that will not prevent the JVM from exiting when the program finishes.

If the user creates a daemon thread it can be ignored in case the programs ends.

    var job = Thread.ofPlatform().daemon(true).start(Zoo::pause);

In this case the method pause can be ignored and never executed.

> Virtual threads are always daemons. Platform threads default to non-daemon but can be changed.

### Thread state

Managing a Thread’s Life Cycle After a Thread object has been created, it is in one of six states.
You can query a thread’s state by calling getState() on the thread object.
Every thread is initialized with a NEW state. As soon as start() is called, the thread is moved to a RUNNABLE state.
Does that mean it is actually running? Not exactly: it may be running, or it may not be.
The RUNNABLE state just means the thread is able to be run.
Once the work for the thread is completed or an uncaught exception is thrown, the thread state becomes TERMINATED, and
no more work is performed.
While in a RUNNABLE state, the thread may transition to one of three states where it pauses its work: BLOCKED, WAITING,
or TIMED_WAITING.
In these states, a thread is not using any CPU resources (other than keeping track of a timer for TIMED_WAITING).

> Some thread-related methods—such as wait()and notify()—are beyond the scope of the exam.

### Concurrency API

#### ExecutorService methods

| Method name                                                               | Description                                                                                                                                             |
|:--------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------|
| `void execute(Runnable command)`                                          | Executes Runnable task at some point in future.                                                                                                         |
| `Future<?> submit(Runnable task)`                                         | Executes Runnable task at some point in future and returns Future representing task.                                                                    |
| `<T> Future<T> submit( Callable<T> task)`                                 | Executes Callable task at some point in future and returns Future representing pending results of task.                                                 |
| `<T> List<Future<T>> invokeAll( Collection<? extends Callable<T>> tasks)` | Executes given tasks and waits for all tasks to complete. Returns List of Future instances in the same order in which they were in original collection. |
| `<T> T invokeAny( Collection<? extends Callable<T>> tasks)`               | Executes given tasks and waits for at least one to complete.                                                                                            |

> The Callable interface is often preferable over Runnable, since it allows more details to be retrieved easily from the
> task after it is completed.


ExecutorService states

| Scenario        | Description                                                       | More tasks allowed | `isShutdown()` | `isTerminated()`                                          |
|:----------------|:------------------------------------------------------------------|:-------------------|:---------------|:----------------------------------------------------------|
| Active          | Accepts tasks                                                     | Yes                | `false`        | `false`                                                   |
| `shutdown()`    | Runs waiting tasks to completion, but doesn’t accept more         | No                 | `true`         | `false` while tasks running<br>`true` when tasks complete |
| `close()`       | Calls `shutdown()` and then awaits termination of executing tasks | No                 | `true`         | `true`                                                    |
| `shutdownNow()` | Stops executing tasks and cancels waiting tasks                   | No                 | `true`         | `false` while tasks running<br>`true` when tasks complete |

Executors factory methods

| Method name                                                   | Description                                                                                                                                                                          |
|:--------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `ExecutorService newSingleThreadExecutor()`                   | Creates single-threaded executor that uses single worker platform thread operating off unbounded queue. Results are processed sequentially in the order in which they are submitted. |
| `ScheduledExecutorService newSingleThreadScheduledExecutor()` | Creates single-threaded executor for platform threads that can schedule commands to run after given delay or to execute periodically.                                                |
| `ExecutorService newCachedThreadPool()`                       | Creates platform thread pool that creates new threads as needed but reuses previously constructed threads when they are available.                                                   |
| `ExecutorService newFixedThreadPool(int)`                     | Creates platform thread pool that reuses fixed number of threads operating off shared unbounded queue.                                                                               |
| `ScheduledExecutorService newScheduledThreadPool(int)`        | Creates platform thread pool that can schedule commands to run after given delay or execute periodically.                                                                            |
| `ExecutorService newVirtualThreadPerTaskExecutor()`           | Creates thread pool that creates a new virtual thread for each task.                                                                                                                 |                              

## Be careful

### Calling run() Instead of start()

The run method blocks the current thread and wait until the response.

### Executors thread should be done with a try-with-resources or call shutdown, otherwise the application never ends.

        Executors.newCachedThreadPool().submit(() -> {});//Get stuck forever

The close() method was only added to this API in Java 19. In older code, you’d see an explicit shutdown() call.
Or perhaps you want to tell tasks to end with shutdownNow()

### Thread Safe

Atomic is the property of an operation to be carried out as a single unit of execution without any interference from
another thread.

A thread-safe atomic version of the increment operator would perform the read and write of the variable as a single
operation, not allowing any other threads to access the variable during the operation.

| Class name      | Description                                    |
|:----------------|:-----------------------------------------------|
| `AtomicBoolean` | A boolean value that may be updated atomically |
| `AtomicInteger` | An int value that may be updated atomically    |
| `AtomicLong`    | A long value that may be updated atomically    |

#### Synchronized block

    void sing2() {
        synchronized (this) {
            System.out.print("La la la!");
        }
    }
    synchronized void sing() {
        System.out.print("La la la!");
    }

The first uses a synchronized block, whereas the second uses the synchronized method modifier.

#### Synchronized for static

    static void dance2() {
        synchronized (Exercises.class) {
            System.out.print("Time to dance!");
        }
    }

    static synchronized void dance() {
        System.out.print("Time to dance!");
    }

### Lock Framework

The ReentrantLock class ensures that once a thread has called lock() and obtained the lock, all other threads that call
lock() will wait until the first thread calls unlock()

#### Lock methods

| Method name                                    | Description                                                                                                                               |
|:-----------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------------|
| `void lock()`                                  | Requests lock and blocks until lock is acquired.                                                                                          |
| `void unlock()`                                | Releases lock.                                                                                                                            |
| `boolean tryLock()`                            | Requests lock and returns immediately. Returns boolean indicating whether lock was successfully acquired.                                 |
| `boolean tryLock(long timeout, TimeUnit unit)` | Requests lock and blocks for specified time or until lock is acquired. Returns boolean indicating whether lock was successfully acquired. |

> It is critical that you release a lock the same number of times it is acquired! For calls with tryLock(), you need to
> call unlock() only if the method returned true.

#### ReentrantReadWriteLock

ReentrantReadWriteLock separate locks from reading and writing data.\
At runtime, only one thread can hold the write lock at a time, but many threads can hold the read lock.

        var readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock().lock();
        readWriteLock.readLock().lock();
        System.out.println(readWriteLock.isWriteLocked());   // true
        System.out.println(readWriteLock.getReadLockCount()); // 1

> On the exam, you are likely to see it used within a single class. You need to know that you can trivially get a read
> lock after acquiring a write lock because reading is a subset of write. However, if you attempt to get the read lock
> first, the code will hang as you can’t upgrade to a write lock.

### Be careful

The ReentrantReadWriteLock has a order. It should never do a `readLock` if there is a `writeLock` waiting to be
unlocked, the code get stuck.

        readWriteLock.writeLock().lock();
        readWriteLock.readLock().lock();
        System.out.println(readWriteLock.isWriteLocked());   // true
        System.out.println(readWriteLock.getReadLockCount()); // 1

        readWriteLock.writeLock().unlock();
        System.out.println(readWriteLock.isWriteLocked()); //false
        System.out.println(readWriteLock.getReadLockCount()); //1

        readWriteLock.readLock().lock();
        readWriteLock.writeLock().lock();
        System.out.println("Never get there");

We can't have a readLock and then call a write lock. It stuck the code.

### Orchestrating Tasks with a CyclicBarrier

The CyclicBarrier receives the number of execution. While this number is not reached the thread will stay stuck waiting.

> parties – the number of threads that must invoke await before the barrier is tripped

            CyclicBarrier c2 = new CyclicBarrier(4);
            c2.awai(); //Wait until 4 threads reach here.
            CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("Do second action"));

After a CyclicBarrier limit is reached (aka the barrier is broken),
all threads are released, and the number of threads waiting on the CyclicBarrier goes back to zero.
At this point, the CyclicBarrier may be used again for a new set of waiting threads.

### Concurrent Collections

The Skip classes means "sorted concurrent” collections.

The CopyOnWrite classes behave a little differently than the other concurrent data structures you have seen.
These classes create a copy of the collection any time a reference is added, removed,
or changed in the collection and then update the original collection reference to point to the copy. 
These classes are commonly used to ensure an iterator doesn’t see modifications to the collection

#### Concurrent collection classes

| Class name              | Java Collections interfaces                                                                   | Sorted? | Blocking? |
|:------------------------|:----------------------------------------------------------------------------------------------|:--------|:----------|
| `ConcurrentHashMap`     | `Map`, `ConcurrentMap`                                                                        | No      | No        |
| `ConcurrentLinkedQueue` | `Queue`                                                                                       | No      | No        |
| `ConcurrentSkipListMap` | `Map`, `SequencedMap`, `SortedMap`, `NavigableMap`, `ConcurrentMap`, `ConcurrentNavigableMap` | Yes     | No        |
| `ConcurrentSkipListSet` | `Set`, `SequencedSet`, `SortedSet`, `NavigableSet`                                            | Yes     | No        |
| `CopyOnWriteArrayList`  | `List`, `SequencedCollection`                                                                 | No      | No        |
| `CopyOnWriteArraySet`   | `Set`                                                                                         | No      | No        |
| `LinkedBlockingQueue`   | `Queue`, `BlockingQueue`                                                                      | No      | Yes       |

### Be careful

While the other concurrent collections execute the entire loop and may cause a infinite loop

        var foods2 = new ConcurrentLinkedDeque<String>();
        foods2.add("Banana");
        foods2.add("Tomato");
        for (String food : foods2) {
            if(!foods2.contains("Orange")) { //Otherwise infinite loop
                foods2.add("Orange");
            }
        }

The CopyOnWrite and ConcurrentSkipListSet collection can avoid it.

        List<Integer> integers = List.of(1, 2, 3, 4);
        List<Integer> integersConcurrent = new CopyOnWriteArrayList<>(integers);
        for (Integer integer : integersConcurrent) {
            integersConcurrent.add(5);
        }
        System.out.println(integersConcurrent); //1, 2, 3, 4, 5, 5, 5, 5
        System.out.println(integers); //[1, 2, 3, 4]


### Thread Problemns

#### Deadlock

A deadlock is a situation in concurrent programming or operating systems where two or more processes or threads 
are blocked indefinitely, each waiting for a resource that is currently held by another process or thread in the same set.

Common Deadlock "Triggers" in Exam Questions:

* Nested synchronized blocks: This is the classic setup for demonstrating deadlock.
* Multiple Lock.lock() calls: Similar to synchronized but with explicit Lock objects.
* Thread.sleep() inside synchronized/locked sections: This often creates the perfect timing window for the deadlock to occur, as it prolongs the "hold and wait" period.

#### Starvation

Starvation occurs when a single thread is perpetually denied access to a shared resource or lock. 
The thread is still active, but it is unable to complete its work as a result of other threads constantly
taking the resource that it is trying to access.

#### Livelock

Livelock occurs when two or more threads are conceptually blocked forever, 
although they are each still active and trying to complete their task.
Livelock is a special case of resource starvation in which two or more threads actively try to acquire a set of locks,
are unable to do so, and restart part of the process.

#### Race condition

A race condition is an undesirable result that occurs when two tasks that should be completed sequentially are completed at the same time.

### Parallel Streams

To generate random numbers in a multithreaded program, you use the ThreadLocalRandom class instead.

        ThreadLocalRandom
                .current()
        //       .ints(lowestInclusive, highestExclusive)
        //       .ints(numbersIntsToInclude)
        //       .ints(numberIntsToInclude, lowestInclusive, highestExclusive)
        //       .nextInt(highestExclusive)
        //       .nextInt(lowestInclusive, highestExclusive)
                .ints()
                .limit(5)
                .forEach(System.out::println); // Prints 5 random ints

Similar methods are available for doubles and longs, such doubles(), nextDouble(), longs(), nextLong(), etc.

### Be careful

**Sorted is not guaranteed**

        System.out.println("\nSorted is not guaranteed in Parallel Stream");
        IntStream.range(1,100).parallel().sorted().forEach(System.out::print); //The sorted is not guaranteed

**Find first is guaranteed**

        System.out.println("\nFind first is guaranteed in Parallel Stream");
        System.out.println(IntStream.range(1,100).skip(5).limit(2).findFirst().getAsInt()); //6
        System.out.println("\nFind first is guaranteed in Parallel Stream");
        System.out.println(IntStream.range(1,100).parallel().skip(5).limit(2).findFirst().getAsInt()); //6

unordered();

Unordered operation when used with parallel stream can improve the performance, as it will find the element by ignoring the order

        System.out.println(IntStream.range(1,100).parallel().skip(5).limit(2).unordered().findFirst().getAsInt()); //7