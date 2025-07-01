package org.bomfim.chapter10;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Questions {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> collect = LongStream.of(6L, 8L, 10L)
                .mapToInt(x -> (int) x)
                .boxed()
                .collect(Collectors.groupingBy(x -> x));
        IntStream intStream = LongStream.of(6L, 8L, 10L)
                .mapToInt(x -> (int) x);
//        IntStream intStream2 = LongStream.of(6L, 8L, 10L)
//                .mapToInt(x ->  x); //DOES NOT COMPILE needs casting
//        intStream.collect(Collectors.groupingBy(x -> x)); //DOES NOT COMPILE  Since groupingBy() is creating a Collection, we need a nonprimitive Stream.
        System.out.println(collect);


//        Stream.generate(() ->1).anyMatch(i -> i > 4); //runs forever
        Stream.generate(() -> 1).anyMatch(i -> i < 4); //return true stop in the first element
        Stream.generate(() -> 1).allMatch(i -> i > 4); //return false and stop in the first element
//        Stream.generate(() ->1).allMatch(i -> i < 4); //run forever
//        Stream.generate(() ->1).noneMatch(i -> i > 4); //run forever
        Stream.generate(() -> 1).noneMatch(i -> i < 4); //return false and stop in the first element

        System.out.println("End");

        LongStream.of(1, 2, 3).map(n -> n * 10).findFirst().getAsLong();
        LongStream.of(1, 2, 3).map(n -> n * 10).boxed().findFirst().get();

//        Optional.empty().orElseThrow(() -> throw new Exception()); //DOES NOT COMILE
    }
}
