package org.bomfim.review;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test5 {

    public static void main(String[] args) {
        Double collect = Stream.of(1, 2, 3)
                .collect(Collectors.averagingInt(y -> y));

        String sa[] = {"a ", " b", "c"};
//        String[ ] sa = new String[3]{ "a", "b", "c"};

        System.out.println(List.of("a", "b", "c").subList(2, 3)); //c
        try (var x = new Closed()) {
            throw new RuntimeException("a");
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            System.out.println("finally");
        }


        List<Integer> integers = List.of(-1, 1, 2, 3, 4, 5, 6);
        System.out.println(integers.stream().max((a, b) -> a > b ? a : b));
        System.out.println(integers.stream().max((a, b) -> a > b ? 1 : -1));
        Integer value = List.of(1, 2, 3).stream().max(Comparator.comparing(a -> a)).get(); //3
        Integer value2 = List.of(1, 2, 3).stream().max(Comparator.comparing(Integer::intValue)).get(); //3
        Integer value3 = List.of(1, 2, 3).stream().max(Comparator.comparingInt(Integer::intValue)).get(); //3

        int x = 0;
        switch (x) {
            case 1: {
                var b = 1;
                break;
            }
            case 2:
                var b = 2;
                break;
            case 3:
//                    System.out.println(b); //does not compile
                b = 5; //compiles
//                    var b = 3; does not compile
                break;
            default:
//                System.out.println(b); //does not compile
        }

        dateTest();

    }

    private static void dateTest() {

        var date = LocalDate.of(2028, Month.MARCH, 12);
        var time = LocalTime.of(1, 30);
        var zone = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime1 = ZonedDateTime.of(date, time, zone);
        ZonedDateTime dateTime2 = dateTime1.plusHours(1);

        System.out.println(dateTime1); //2028-03-12T01:30-05:00[US/Eastern]
        System.out.println(dateTime2); //2028-03-12T03:30-04:00[US/Eastern]
        long diff = ChronoUnit.HOURS.between(dateTime1, dateTime2); //1
        int diffHours = dateTime1.getHour() - dateTime2.getHour(); //2
        System.out.println(dateTime1.getHour());
        System.out.println(dateTime2.getHour());
        System.out.println(diff);
        System.out.println(diffHours);

        System.out.println(TYPE.TB.type);
    }

    interface TestI{
        int x = 10;
        static final int y = 11;
//        private static final int y = 11; does not compile
//        private static final int x2 = 10; //DOES NOT COMPILE
    }

    enum TYPE {
        TA("A"), TB("B");

        private String type;

        TYPE(String b) {
            type = "bananas";
        }
    }

    public static class A {
        public void foo() {
        }
    }

    public static class B extends A {
//        @Override
//        public void foo() throws IOException {}
    }

    interface I {

        public default void test() {
            System.out.println("test");
        }
    }

    interface I2 {

        public abstract void test();
    }

    public class Impl implements I2 {
        @Override
        public void test() {

        }
    }
//
//    public static class A {
//        public void foo() throws IOException {
//        }
//    }
//
//    public static class B extends A {
//        public void foo() {
//        }
//    }

    public static class Closed implements AutoCloseable {

        @Override
        public void close() throws Exception {
            System.out.println("close");
        }
    }

    static String prefix = "I am ";

    enum Pets {
        DOG("D"), CAT("C"), FISH("F");
        static String prefix2 = "I am ";

        String name;

        Pets(String s) {
            name = prefix + s;
//            name += prefix2 //DOES NOT COMPILE
        }

        public String getData() {
            return name;
        }
    }
}
