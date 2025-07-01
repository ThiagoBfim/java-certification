package org.bomfim.chapter10;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exercises {

    public static void main(String[] args) {
        optional();
        stream();
        reduce();
        collect();
        primitiveStream();
        System.out.println("\nCollector to map");
        System.out.println("The exam creators like asking about groupingBy() and partitioningBy(), so make sure you understand these sections very well.");
        System.out.println("Note that the function you call in groupingBy() cannot return null. It does not allow null keys.");
        Map<Integer, String> map = Stream.of("lion", "cat", "tiger")
                .collect(Collectors.toMap(String::length, s -> s)); //Duplicate key 3
        System.out.println(map);
//        Map<String, Integer> map2 = Stream.of("lion", "tiger", "dog", "dog")
//                .collect(Collectors.toMap(s -> s, s -> s.length())); //java.lang.IllegalStateException: Duplicate key dog

        TreeMap<Integer, String> map2 = Stream.of("lion", "tiger", "dog", "dog")
                .collect(Collectors.toMap(s -> s.length(), //key
                        s -> s, //value
                        (a, b) -> a + "/" + b, //merge function
                        TreeMap::new) //Type of Element
                );
        System.out.println(map2); //{3=dog/dog, 4=lion, 5=tiger}


        Map<String, Long> groupBy = Stream.of("lion", "tiger", "dog", "dog")
                .collect(Collectors.groupingBy(s -> s,
                        Collectors.counting())
                );
        System.out.println(groupBy);
        Map<String, Long> collect = Stream.of("lion", "tiger", "dog", "dog")
                .collect(Collectors.groupingBy(s -> s,
                        new Collector<String, Long, Long>() {
                            @Override
                            public Supplier<Long> supplier() {
                                return () -> 1L;
                            }

                            @Override
                            public BiConsumer<Long, String> accumulator() {
                                return (a, b) -> {
                                };
                            }

                            @Override
                            public BinaryOperator<Long> combiner() {
                                return new BinaryOperator<Long>() {
                                    @Override
                                    public Long apply(Long aLong, Long aLong2) {
                                        return null;
                                    }
                                };
                            }

                            @Override
                            public Function<Long, Long> finisher() {
                                return new Function<Long, Long>() {
                                    @Override
                                    public Long apply(Long aLong) {
                                        return null;
                                    }
                                };
                            }

                            @Override
                            public Set<Characteristics> characteristics() {
                                return Set.of();
                            }
                        }
                ));
        System.out.println(collect); //{tiger=1, dog=2, lion=1}

        System.out.println("\nPartitioning");
        System.out.println("""
                Partitioning is a special case of grouping. With partitioning, there are only two possible groups: true and false.
                 Partitioning is like splitting a list into two parts.
                """);

        Map<Boolean, Long> partition = Stream.of("lion", "tiger", "dog", "dog")
                .collect(Collectors.partitioningBy(s -> s.length() > 3, Collectors.counting()));
        System.out.println(partition); //{false=2, true=2}
        System.out.println("\nCollectors.mapping");
        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map5 = ohMy.collect(
                Collectors
                        .groupingBy(String::length,
                                Collectors.mapping(s -> s.charAt(0),
                                        Collectors.minBy((a, b) -> a - b))));
        System.out.println(map5); // {5=Optional[b], 6=Optional[t]}

        System.out.println("\nCollectors Teeing");
        System.out.println("""
                Suppose you want to return two things. As we’ve learned, this is problematic with streams because you only get one pass. 
                The summary statistics are good when you want those operations.
                 Luckily, you can use teeing() to return multiple values of your own.
                """);
        var list = List.of("x", "y", "z");
        Separations result = list.stream()
                .collect(Collectors.teeing(Collectors.joining(" "),
                        Collectors.joining(","),
                        (s, c) -> new Separations(s, c)));
        System.out.println(result);

        System.out.println("\nCollectors.spliterator");
        System.out.println("""
                It starts with a Collection or a stream—that is your bag of food. 
                You call trySplit() to take some food out of the bag. 
                The rest of the food stays in the original Spliterator object.
                
                IMPORTANT: The spliterator change the elementsRemaining, and if the
                 forEachRemaining is called then the second time it does not print anything.
                
                """);
        var foods = List.of("Cheese-", "Chicken-", "Coffee-", "Cake-", "Meat-");
        Spliterator<String> spliterator = foods.spliterator();
        Spliterator<String> halfRight = spliterator.trySplit();
        halfRight.forEachRemaining(System.out::print); //Cheese-Chicken-
        halfRight.forEachRemaining(System.out::print); //Nothing
        System.out.println();
        spliterator.forEachRemaining(System.out::print); //Coffee-Cake-Meat-
        Spliterator<String> halfRight2 = spliterator.trySplit(); //null
//        halfRight2.forEachRemaining(System.out::print); //NPE

        System.out.println();
        var originalBag = Stream.iterate("a", n -> "ab").spliterator();
        Spliterator<String> newBag = originalBag.trySplit();
        newBag.tryAdvance(System.out::print); //a
        System.out.println();
        newBag.forEachRemaining(System.out::print); //works a big value



    }

    public record Separations(String s, String c) {
    }


    private static void stream() {
        System.out.println("\nStream");
        System.out.println("""
                Creating Stream:
                Stream.empty();
                Stream.of(1,2,3);
                Stream.iterate(0,i->i+1); //infinite
                Stream.generate(()->""); //infinite
                
                """);

        Stream.empty();
        Stream.of(1, 2, 3);
        int sum = Stream.iterate(0, i -> i + 1)
//                .peek(System.out::println)
                .limit(10)
                .mapToInt(i -> i).sum(); //0+1+2+3+4+5+6+7+8+9
        System.out.println(sum);
        int sum2 = Stream.iterate(2, i -> i % 2 == 0, i -> i + 1)
//                .peek(System.out::println) //2,3,3,3,3,3,3,3...
                .limit(10)
                .mapToInt(i -> i).sum(); //2
        System.out.println(sum2);
        String collect = Stream.generate(() -> "a").limit(10).collect(Collectors.joining(""));
        System.out.println(collect);

        System.out.println("""
                Be careful 
                Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
                 System.out.println(minEmpty.isPresent()); // false, The optional is empty
                """);

        Optional<?> minEmpty = Stream.of(123).min((s1, s2) -> {
            if (true) {
                throw new RuntimeException(); //Never called, it will be called when the get method is called.
            }
            return 0;
        });
        System.out.println(minEmpty.isPresent()); // false

        var list = List.of("monkey", "2", "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
        System.out.println(list.stream().anyMatch(pred)); // true
        System.out.println(list.stream().allMatch(pred)); // false
        System.out.println(list.stream().noneMatch(pred)); // false
        System.out.println(Stream.generate(() -> "chimp").anyMatch(pred)); // true
        System.out.println(Stream.generate(() -> "chimp").noneMatch(pred)); // false
//        System.out.println(Stream.generate(() -> "chimp").allMatch(pred)); // run forever
        Stream<String> infinite = Stream.generate(() -> "chimp");
        infinite.limit(1).forEach(System.out::println);
//        infinite.count(); //ava.lang.IllegalStateException: stream has already been operated upon or closed
    }

    private static void primitiveStream() {
        System.out.println("\nPrimitive Stream");
        System.out.println("""
                               * IntStream: Used for the primitive types int, short, byte, and char 
                               * LongStream: Used for the primitive type long 
                               * DoubleStream: Used for the primitive types double and float
                
                               IntStream.rangeClosed(1,10).average().getAsDouble()
                //               IntStream.rangeClosed(1,10).average().get() //does not compile
                
                """);

        System.out.println(IntStream.range(1, 10).max());
        System.out.println(IntStream.rangeClosed(1, 10).max());
        IntStream.rangeClosed(1, 10).average().orElseGet(() -> Float.valueOf(1));

        System.out.println("\nSummaryStatistics");
        System.out.println("""
                * getCount(): Returns a long representing the number of values. 
                * getAverage(): Returns a double representing the average. If the stream is empty, returns 0.0. 
                * getSum(): Returns the sum as a double for DoubleSummaryStatistics, and long for IntSummaryStatistics and LongSummaryStastistics. 
                * getMin(): Returns the smallest number (minimum) as a double, int, or long, depending on the type of the stream. If the stream is empty, returns the largest numeric value based on the type. 
                * getMax(): Returns the largest number (maximum) as a double, int, or long depending on the type of the stream. If the stream is empty, returns the smallest numeric value based on the type.
                """);
        IntSummaryStatistics intSummaryStatistics = IntStream.rangeClosed(1, 10).summaryStatistics();
        System.out.println(intSummaryStatistics);
        System.out.println(intSummaryStatistics.getMax()); //int
        System.out.println(intSummaryStatistics.getMin()); //int
        System.out.println(intSummaryStatistics.getSum()); //long
        System.out.println(intSummaryStatistics.getCount()); //long
        System.out.println(intSummaryStatistics.getAverage()); //double
    }

    private static void collect() {
        System.out.println("\nCollect");
        System.out.println("""
                public <R> R collect(Supplier<R> supplier,  
                BiConsumer<R, ? super T> accumulator, 
                BiConsumer<R, R> combiner) 
                public <R,A> R collect(Collector<? super T, A,R> collector)
                """);

        Stream<String> stream = Stream.of("w", "o", "l", "f");
        StringBuilder word = stream.collect(StringBuilder::new, //supplier, <U> with the object that will store
                StringBuilder::append, //Accumulator <U, T>, where T is the element from the stream
                StringBuilder::append); //Combiner <U,U> where U is the same type from the supplier
        System.out.println(word); // wolf

        Stream.of("1", "3", "2", "b")
//                .sorted(Comparator::reverseOrder) //DOES NOT COMPILE
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
//        Stream.of(new Person("A"), new Person("C"), new Person("B"), new Person("D"))
//                .sorted() //ClassCastException // cannot be cast to class java.lang.Comparable
//                .forEach(System.out::println);

        Stream.of(1).forEach(a -> x = x + a);

        System.out.println("""
                        Stream.generate(() -> "Elsa")
                                .filter(n -> n.length() == 4)
                                .sorted()
                                .limit(2)
                                .forEach(System.out::println); //RUN FOREVER, because sorted will wait the stream end to pass to the next instruction
                
                        Stream.generate(() -> "Elsa")
                                .filter(n -> n.length() == 4)
                                .limit(2)
                                .sorted()
                                .forEach(System.out::println); //Elsa\nElsa
                
                        Stream.generate(() -> "Elsa")
                                .filter(n -> n.length() != 4)
                                .limit(2)
                                .sorted()
                                .forEach(System.out::println); //RUN FOREVER, because filter does not find any element
                """);
    }

    static int x = 10;

    record Person(String name) {
    }

    private static void reduce() {
        System.out.println("\nReduce");
        System.out.println("""
                public T reduce(T identity, BinaryOperator<T> accumulator) 
                public Optional<T> reduce(BinaryOperator<T> accumulator) 
                public <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
                """);
        String word = Stream.of("w", "o", "l", "f").reduce("", (s, c) -> s + c);
        String word2 = Stream.of("w", "o", "l", "f").collect(Collectors.joining());
        System.out.println(word); // wolf
        System.out.println(word2); // wolf
        System.out.println(Stream.of(1, 2, 3, 4).reduce(1, (a, b) -> a + b));//11 =1+1+2+3+4
        System.out.println(Stream.of(1, 2, 3, 4).reduce((a, b) -> a + b));//10 =1+2+3+4
        Stream.of(1, 2).reduce((a, b) -> a + b).isPresent();
        var x = Stream.of("1", "22").reduce(1,  //Initializer <U>, where U is integer
                (a, b) -> a + b.length(), //accumulator <U,T> where <Integer, String>
                (a, b) -> a + b); //combinator <U,U> where <Integer, Integer>
        System.out.println(x); //4
        var x2 = Stream.of("1", "22").reduce("1",  //Initializer <U>
                (a, b) -> a + b.length(), //accumulator <U,T>
                (a, b) -> a + b); //combinator <U,U>
        System.out.println(x2);//112
//        Stream.of(1,2).reduce(1, (a, b) -> a+b).isPresent(); //DOES NOT COMPILE -> Return a integer
    }

    private static void optional() {
        System.out.println("Optional");
        System.out.println(Optional.of(45));
//        System.out.println(Optional.of(null)); //NPE
        System.out.println(Optional.ofNullable(null));
//        System.out.println(Optional.ofNullable(null).get()); //NoSuchElementException
        System.out.println(Optional.ofNullable(null).orElse(getValue()));
//        System.out.println(Optional.ofNullable("a").orElse(getValue())); //DOES NOT COMPILE, orElse must return a String

        Optional.of("a")
                .map(s -> s + "b")
                .flatMap(s -> Optional.ofNullable(s + "c"))
                .ifPresent(System.out::println);
    }

    private static Object getValue() {
        System.out.println("Not lazy");
        return "a";
    }
}
