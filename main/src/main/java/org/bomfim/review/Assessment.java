package org.bomfim.review;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Assessment {

    public static void main(String[] args) {
        //        var x = (Integer i) -> {return i == 5;}; //DOES NOT COMIPLE
        Function<Integer, Integer> sum = (x) -> x + 2;
        Function<Integer, Integer> sum2 = (Integer x) -> x + 2;
//        Function<Integer, Integer> sum3 = (int x) -> x + 2; //DOES NOT COMPILE

//        magic(Stream.iterate(5, (i) -> 10)); //RUN FOREVER
    }

    public class Var {
        //    var var = 3; //DOES NOT COMPILE - instance variable
//    Var case = new Var(); //DOES NOT COMPILE - variable with special word "case"
        void var() {
        }
//    int Var() { var _ = 7; return _;}  //DOES NOT COMPILE  - variable with special word "_"
//    String new = "var";  //DOES NOT COMPILE - variable with special word "new"
//    var var() { return null; } //DOES NOT COMPILE  - return cannot be var
//    void var2(){ var x = () -> new Var();} //DOES NOT COMPILE - var cannot be used with Functional Interface
    }

    private static void magic(Stream<Integer> integerStream) {
        Optional o = integerStream
                .filter(x -> x < 5)
                .limit(3)
                .max((x, y) -> x - y);
        System.out.println(o.get());
    }

    sealed class Dance { }

    sealed class Ballet extends Dance permits NewDance {
        Ballet() {
            var d = LocalDate.of(2025, Month.OCTOBER, 20);
            if (d.isAfter(LocalDate.now()))
                System.out.print("say goodbye");
        }
    }

    abstract non-sealed class NewDance extends Ballet { }


}
