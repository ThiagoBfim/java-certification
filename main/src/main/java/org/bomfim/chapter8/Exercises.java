package org.bomfim.chapter8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exercises {

    public static void main(String[] args) {

        System.out.println("Lambda ");
        System.out.println("""
                a -> true //works
                (a) -> true //works
                (Animal a) -> true //works
                (Animal a) -> {return true;} //works
                (Animal a) -> {return true} //COMPILE ERROR, missing semicolon
                test(new Animal(), (Animal a) -> {true;}); //COMPILE ERROR, missing return
                Validator x = (a) -> false;
                var x2 = (a) -> false; //DOES NOT COMPILE, it is not possible to assign lambda to var
                """);
        System.out.println("The parentheses around the lambda parameters can be omitted only if there is a single parameter and its type is not explicitly stated.");
        System.out.println("\nFunctional Interface");
        System.out.println("A functional interface is an interface that contains a single abstract method.");
        System.out.println("""
                If a functional interface includes an abstract method with the same signature as a public method found in Object,
                those methods do not count toward the single abstract method test.
                Object method signatures:
                * public String toString()
                * public boolean equals(Object)
                * public int hashCode()
                IMPORTANT: declaring an abstract method int toString() in an interface would not compile since Object’s version of the method returns a String
                """);
        System.out.println("\nMethod reference");
        System.out.println("Method reference is a way to refer to a method by its name and the class in which it is declared.");
        System.out.println("Supplier<Animal> ab = Animal::new;");
        System.out.println("""
                There are four formats for method references.
                * static methods
                * Instance methods on a particular object
                * Instance methods on a parameter to be determined at runtime 
                * Constructors
                """);

        System.out.println("\nSupplier");
        System.out.println("""
                Supplier<ArrayList<String>> s3 = ArrayList::new;\s
                ArrayList<String> a1 = s3.get();
                System.out.println(a1);
                """);

//        Supplier<List<String>> s3 = ArrayList::new;
//        ArrayList<String> a1 = s3.get(); //DOES NOT COMPILE List cannot cast to ArrayList
//        System.out.println(a1);

        Validator x = (a) -> false;
//        var x2 = (a) -> false; //DOES NOT COMPILE, it is not possible to assign lambda to var
        test(new Animal(), (a) -> false);
        test(new Animal(), (Animal a) -> false);
        test(new Animal(), (Animal a) -> {
            return true;
        });
//        test(new Animal(), (Animal a) -> {return true}); //COMPILE ERROR, missing semicolon
//        test(new Animal(), (Animal a) -> {true;}); //COMPILE ERROR

        Supplier<Animal> ab = Animal::new;

        System.out.println("""
                5: (var x, y) -> "Hello" // DOES NOT COMPILE 
                6: (var x, Integer y) -> true // DOES NOT COMPILE 
                7: (String x, var y, Integer z) -> true // DOES NOT COMPILE 
                8: (Integer x, y) -> "goodbye" // DOES NOT COMPILE 
                
                Lines 5 needs to remove var from x or add it to y. 
                Next, lines 6 and 7 need to use the type or var consistently. 
                Finally, line 8 needs to remove Integer from x or add a type to y.
                """);
        System.out.println("""
                (a, b) -> { int a = 0; return 5; } // DOES NOT COMPILE
                Variable "a" is declared twice.
                """);

        System.out.println("WARNING: While you don’t normally have to look for missing semicolons, lambdas are tricky in this space, so beware!");

        Function<Integer, Integer> s = a -> a + 4;
        Function<Integer, Integer> t = a -> a * 3;
        Function<Integer, Integer> c = s.compose(t);
        System.out.print(c.apply(1));

    }
    public void counts(List<Integer> list) {
        list.sort((final var x, @Deprecated var y) -> x.compareTo(y));
    }
    public void counts2(List<Integer> list) {
        list.sort(Comparator.naturalOrder());
    }
    public static class Test implements ObjectInterface {
        @Override
        public String toString() {
            return "Test";
        }

        @Override
        public boolean equals(String o) {
            return false;
        }
    }

    @FunctionalInterface
    public interface ObjectInterface {
        public String toString();

        public boolean equals(Object o);

        public int hashCode();

        public boolean equals(String o);
//        public void test2(); //Breaks the functional interface
    }

    public interface WrongObjectInterface {
        //        public CharSequence toString(); //DOES NOT COMPILE
//        public String equals(Object o); //DOES NOT COMPILE
        public boolean equals(String o); //COMPILE
//        public long hashCode(); //DOES NOT COMPILE
//        public short hashCode(); //DOES NOT COMPILE
//        public Integer hashCode(); //DOES NOT COMPILE
    }

    public static void test(Animal a, Validator validator) {
        validator.validate(a);
    }

    static class Animal {


    }

    @FunctionalInterface
    public static interface Validator {
        public boolean validate(Animal a);
    }
}
