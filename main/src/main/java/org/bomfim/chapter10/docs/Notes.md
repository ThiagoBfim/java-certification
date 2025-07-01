# Chapter 10

* Stream
* Lambda
* Functional Interface
* Optional

## Topics

### Final methods

| Method      | Terminal?            | Short-Circuiting? | Varies? | Return value | Reduction |
|-------------|----------------------|-------------------|---------|--------------|-----------|
| count()     | Does not terminate   |                   |         | long         | Yes       |
| min()       | Does not terminate   |                   |         | Optional<T>  | Yes       |
| max()       | Does not terminate   |                   |         | Optional<T>  | Yes       |
| findAny()   | Terminates           |                   |         | Optional<T>  | No        |
| findFirst() | Terminates           |                   |         | Optional<T>  | No        |
| allMatch()  | Sometimes terminates |                   |         | boolean      | No        |
| anyMatch()  | Sometimes terminates |                   |         | boolean      | No        |
| noneMatch() | Sometimes terminates |                   |         | boolean      | No        |
| forEach()   | Does not terminate   |                   |         | void         | No        |
| reduce()    | Does not terminate   |                   | Yes     | Varies       | Yes       |
| collect()   | No                   | N/A               | Yes     |              |           |

### Primitive Stream

| Method                      | Primitive stream | Description                                                                          |
|-----------------------------|------------------|--------------------------------------------------------------------------------------|
| average()                   | IntStream        | Arithmetic mean of elements                                                          |
|                             | LongStream       |                                                                                      |
|                             | DoubleStream     |                                                                                      |
| boxed()                     | IntStream        | Stream<T> where T is wrapper class associated with primitive value                   |
|                             | LongStream       |                                                                                      |
|                             | DoubleStream     |                                                                                      |
| max()                       | IntStream        | Maximum element of stream                                                            |
|                             | LongStream       |                                                                                      |
|                             | DoubleStream     |                                                                                      |
| min()                       | IntStream        | Minimum element of stream                                                            |
|                             | LongStream       |                                                                                      |
|                             | DoubleStream     |                                                                                      |
| range(int a, int b)         | IntStream        | Returns primitive stream from a (inclusive) to b (exclusive)                         |
| range(long a, long b)       | LongStream       |                                                                                      |
| rangeClosed(int a, int b)   | IntStream        | Returns primitive stream from a (inclusive) to b (inclusive)                         |
| rangeClosed(long a, long b) | LongStream       |                                                                                      |
| sum()                       | IntStream        | Returns sum of elements in stream                                                    |
|                             | LongStream       |                                                                                      |
|                             | DoubleStream     |                                                                                      |
| summaryStatistics()         | IntStream        | Returns object containing numerous stream statistics such as average, min, max, etc. |
|                             | LongStream       |                                                                                      |
|                             | DoubleStream     |                                                                                      |

## Tricks

* anyMatch runs until find a match or ends the stream
* allMatch runs until return false or ends the stream
* noneMatch runs until return true or ends the stream

Example:

        Stream.generate(() ->1).anyMatch(i -> i > 4); //runs forever 
        Stream.generate(() ->1).anyMatch(i -> i < 4); //return true stop in the first element 
        Stream.generate(() ->1).allMatch(i -> i > 4); //return false and stop in the first element 
        Stream.generate(() ->1).allMatch(i -> i < 4); //run forever 
        Stream.generate(() ->1).noneMatch(i -> i > 4); //run forever 
        Stream.generate(() ->1).noneMatch(i -> i < 4); //return false and stop in the first element

* LongStream.of(1, 2, 3).map(n -˃ n * 10).findFirst().getAsLong()
* LongStream.of(1, 2, 3).map(n -˃ n * 10).boxed().findFirst().get()

## Review Questions Notes

* Stream.concat(Stream.of(1), Stream.of(2)) //COMPILES
* Stream.concat(Stream.of(1), Stream.of(2), Stream.of(3)) //DOES NOT COMPILE - Stream.concat() takes two parameters
* Optional.empty().orElseThrow(() -> throw new Exception()); //DOES NOT COMILE, cannot throw Checked exception