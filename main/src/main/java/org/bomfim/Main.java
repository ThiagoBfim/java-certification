package org.bomfim;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.bomfim.Main;

public class Main {

    static char x;

    public static void method() {
        enum Enum {A, B, C}
        ;
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(1/0); //ArithmeticException: / by zero
        System.out.println(1 / 0.0); //Infinity

        var p1 = Path.of("/zoo/./bear", "../food.txt");
        p1.normalize().relativize(Path.of("/lion"));
        System.out.println(p1.normalize().relativize(Path.of("/lion"))); // ../../lion
        System.out.println(p1); // /zoo/food.txt

        var p2 = Path.of("/zoo/animals/bear/koala/food.txt");
        System.out.println(p2.subpath(1, 3).getName(1)); //bear
        System.out.println(p2.subpath(1, 2).getName(0)); //bear
        System.out.println(p2.subpath(0, 2)); //zoo/animals
        System.out.println(p2.subpath(1, 2)); //animals

        int[] array = {6, 9, 8};
        System.out.println("B" + Arrays.binarySearch(array, 9)); //B-1 Array is not sorted
        System.out.println("C" + Arrays.compare(array, new int[]{6, 9, 8})); //C0
        System.out.println("C" + Arrays.compare(array, new int[]{6, 9, 8, 9, 10, 11})); //C-3
        System.out.println("M" + Arrays.mismatch(array, new int[]{6, 9, 8})); //M-1
        System.out.println("M" + Arrays.mismatch(array, new int[]{6, 6, 8})); //M-1

        stringBuilder();

        System.out.println(Math.pow(Math.pow(2, 2) + Math.pow(2, 2), .5)); //2.82..
        System.out.println(Math.pow(Math.pow(2, 2) + Math.pow(2, 2), 0.5)); //2.82..
        System.out.println(Math.pow(Math.pow(2, 2) + Math.pow(2, 2), 1 / 2)); //1.0
        System.out.println(((double) 1 / 2) * (2 * 2)); //2.0
        System.out.println((1 / 2) * (2 * 2)); //0
        System.out.println((double) (1 / 2) * (2 * 2)); //0.0
        double one = Math.pow(1, 2);

        int x2 = 5;
        x2 = x2 + x2 % -5;
        System.out.println(x2); //5;

        Path path1 = Path.of("/cats/../panther");
        System.out.println(path1.normalize()); // - /cats

        System.out.println(3 / 2);
        System.out.println("Map");
        NavigableMap<Integer, String> map = new TreeMap<>(Map.of(1, "A", 2, "B", 3, "C"));
        System.out.println(map.tailMap(1)); //{1=A, 2=B, 3=C}
        System.out.println(map.tailMap(1, false)); //{2=B, 3=C}
        NavigableMap<Integer, String> tailMap = map.tailMap(2, false);
        System.out.println(tailMap); //{3=C}
        System.out.println(map.size()); //3
        System.out.println(tailMap.pollFirstEntry()); //3=C
        System.out.println(map.size()); //2

        System.out.println("End map");

        String String =
                """ 
                           Str 
                            Str 
                        """;
        System.out.println("String: " + String);
        System.out.println(String.charAt(4));
        x = 5;
//        while (false) { x=3; }
        if (false) {
            x = 3;
        }
        if (true) {
            x = 3;
        }
//        List list = new ArrayList();
//        list.add(2, "a"); //java.lang.IndexOutOfBoundsException: Index: 2, Size: 0
//
        Journal abc1 = new Journal(1, "abc");
        System.out.println("ID: " + abc1.id()); //2

        var INT1 = 1;
        var INT2 = 3;
        int i = INT1;
        do {
            System.out.println(i);
        } while (++i < INT2);

//        Locale.setDefault();

//        int a, b = 3;
        System.out.print(x);
        System.out.print(" " + x + "");
        System.out.println();
        "abc".lines();
        "abc".isEmpty();
        "abc".isBlank();
        var abc = new StringBuilder("abc");
        abc.ensureCapacity(1);
        System.out.println(abc); //abc
        var abc2 = new StringBuilder("abc");
        abc2.setLength(1);
        System.out.println(abc2); //a

//        System.out.println("12345".charAt(6)); //throw an IndexOutOfBoundsException
        LocalDate plus = LocalDate.of(2025, Month.AUGUST, 4)
                .plus(Period.ofYears(-10).ofMonths(-1));
        System.out.println(plus); //2025-07-04
        Integer x = 0;
        String result = switch (x) {
            case 1 -> "ok";
            default -> "error";
//            case null -> "null"; //COMPILE error
        };
        System.out.println(result);

        var obj = new Object();
        Object result2 = getResult2("a");

        System.out.println(result2);
//        printData();
        System.out.println("END");

        boolean containsIce = Boolean.valueOf("1"); //false
        boolean containsIce2 = Boolean.valueOf("ab"); //false
        System.out.println(containsIce);
        System.out.println(containsIce2);

        calculate(2, 2);
        testString();
        test2();
        test3();
        files();
        date();

        Person bc = new Person("bc");
        System.out.println(bc.name());
    }

    private static void stringBuilder() {
        new StringBuilder("12345").replace(2, 7, "6"); //126
        new StringBuilder("12345").replace(2, 5, "6"); //126
        var x = new StringBuilder("12345").replace(2, 4, "6"); //1265
        x.charAt(3); //5
    }

    sealed interface Member extends Serializable permits Person {
        String role();
    }

//    public static sealed abstract class Person21 permits Student2 {
//    }
//
//    static record Student2(int id) extends Person21 {
//    }

    private record Person(String name) implements Member {

        Person{
            name = "an";
        }
//        public Person(String person){}
        @Override
        public String role() {
            return "";
        }
    }

    enum Coffee {
        COFFEE("Coffee");

        private Coffee(String coffee) {

        }
    }

    public static void date() {
//        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm"))); //June 01, Party's at 01:01

        var x = DateTimeFormatter.ofPattern("'It''s 'h' hours past midnight, and 'mm' minutes'");
        String format = LocalTime.of(4, 5).format(x);
        System.out.println(format);
    }

    public static void files() {
        var path = Path.of("/storage", "toys").resolve("bird"); // n1
        System.out.println(path);
//        Files.find(path, (p, a) -> a.isDirectory())  // n2
//                .map(p -> p.toRealPath().toString()) // n3
//                .map(p -> p.normalize()) // n4
//                .forEach(System.out::print);
    }


    public static void test3() {
        System.out.println(Integer.valueOf(128) == Integer.valueOf(128));
        System.out.println(Integer.valueOf(127) == Integer.valueOf(127));
        System.out.println(Integer.valueOf(10) == 10);
        var s = new HashSet<Integer>();
        s.add(Integer.valueOf(15));
        s.add(Integer.valueOf("F", 16));
        var u = Collections.unmodifiableSet(s);
        var y = 3000;
        s.add(y);
        s.add(y);
        s.removeIf(x -> x == 3000);
        System.out.println(u.size() + " " + u.contains(15));
    }

    public static void test2() {
        String puzzler = """
                   One " \
                   Two ""\n
                   Three \"\"\"
                """;
        System.out.print(puzzler);
    }

    public static void testStream() {
        Stream<String> s = Stream.empty();
        Stream<String> s2 = Stream.empty();
        Predicate<String> condition = b -> b.startsWith("c");
        Map<Boolean, List<String>> p = s.collect(
                Collectors.partitioningBy(condition));
        Map<Boolean, List<String>> g = s2.collect(
                Collectors.groupingBy((a) -> true)); //DOES NOT COMPILE
        System.out.println(p + " " + g);
    }

    public static void testString() {
        String numbers = "2468";
        numbers = numbers.indent(2);
        System.out.print(numbers); //"  2468\n"

        numbers += "00";
        numbers.replace("8", "9");
        int total = 0;
        total += numbers.indexOf("6");
        total += numbers.indexOf("9");
        char ch = numbers.charAt(3);
        int length = numbers.length();
        System.out.println(numbers);
        System.out.println(total);
        System.out.println(ch);
        System.out.println(length);
    }

    static interface Instrument {
    }

    public static class Microscope implements Instrument {
        public static void main(String[] resolution) {
            var v = new Microscope();
            System.out.println(switch (v) {
                case null -> "0";
                case Instrument i -> "1";
//                case Microscope m -> "2";
//                default -> "3";
            });
        }
    }

    private static Object getResult2(Object obj) {
        return switch (obj) {
            case null -> null;
            case String s -> s.length();
            case int[] ia -> ia.length;
            default -> "obj is something else";
        };
    }

    public static void printData() throws IOException {
        var w = new StringBuilder();
        try (var o = new BufferedOutputStream(System.out)) {
            w.append("abc");
        }
        System.out.println(w);
    }


    public static void calculate(int a, int b) {
        System.out.println(Math.pow(Math.pow(a, 2) + Math.pow(b, 2), .5)); //2.82..
        System.out.println(Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 0.5)); //2.82..
        System.out.println(Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 1 / 2)); //1.0
        System.out.println(((double) 1 / 2) * (a * b)); //2.0
        System.out.println((1 / 2) * (a * b)); //0
        System.out.println((double) (1 / 2) * (a * b)); //0.0

        OptionalDouble average = DoubleStream.of(1, 2, 3).average();
    }


    public record Journal(int id, String name) {

        //        public Journal(int id, String name) {
//            this.id = id;
//        }
        public Journal {
            id = id + 1;
            System.out.println("this.name: " + this.name()); //null
            System.out.println("name: " + name); //abc
            System.out.println("id: " + id); //abc
//            System.out.println(this.name); //DOES NOT COMPILE
        }
    }

    public record Journal2(int id, String name) {
        public Journal2(int id, String name) {
            this.id = id;
            this.name = name;
            System.out.println(name);
        }

        //public Journal2(int id) { //does not compile
        //    this.id = id;
        //    this.name = "ab";
        //}
        public Journal2(int id) {
            this(id, "abc");
        }

        public Journal2() {
            this(0, "Journal");
        }
    }


    static void testing() {
//        var ia[][] = { {1,2}, null};
//        var ia[][] = new int[][]{ {1,2}, null};
        var ia = new int[][]{{1, 2}, null};
    }

//    Long getValue() { return 2; //DOES NOT COMPILE }

//    interface I1 {
//        public default void m1() {
//            System.out.println("in I1.m1");
//        }
//    }
//
//    interface I2 {
//        public default void m1() {
//            System.out.println("in I2.m1");
//        }
//    }
//
//    class CI implements I1, I2 { //This class will not compile.
//    }
//
//    //This class will compile because it provides its own implementation of m1.
//    class C2 implements I1, I2 {
//        public void m1() {
//            System.out.println("in C2.m1");
//        }
//    }

    long getValue() {
        return 2; //COMPILE
    }
}