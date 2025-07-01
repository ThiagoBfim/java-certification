package org.bomfim.chapter2;

public class Exercises {

    public static void main(String[] args) {
        bitwiseOperator();
        playAroundWithNegative();
        incrementAndDecrement();
        modules();
        numericPromotion();
        compoundOperator();
        trickQuestion();

        equalityOperators();

        System.out.println(null == null);
        instanceOf();
        logicalOperators();

        boolean canine = true, wolf = true;
        System.out.println("canine: " + canine);
        System.out.println("wolf: " + wolf);

        long x2 = (long)(01.6+2.6);
        System.out.println((short)(int)x2);
    }

    private static void logicalOperators() {
        System.out.println("true & true = " + (true & true));
        System.out.println("true & false = " + (true & false));
        System.out.println("false & false = " + (false & false));
        System.out.println("true | false = " + (true | false));
        System.out.println("true | true = " + (true | true));
        System.out.println("false | false = " + (false | false));
        System.out.println("true ^ false = " + (true ^ false));
        System.out.println("true ^ true = " + (true ^ true));
    }

    private static void instanceOf() {
        Number x = 10;
        if (x instanceof Integer) {
        }
        if (null instanceof Integer) {
        }
//        if(x instanceof String){} //COMPILE ERROR
    }

    private static void equalityOperators() {
        boolean b = true == false; //COMPILE
//        boolean b1 = true == 3; //DOES NOT COMPILE
//        false != "grape"; //DOES NOT COMPILE
//        10.2 != "grape"; //DOES NOT COMPILE
    }

    private static void trickQuestion() {
        boolean x = true;
        if (x = false)
            System.out.println("false");
        else
            System.out.println(x); // false
        boolean x2 = true;
        if (x2 == false)
            System.out.println("false");
        else
            System.out.println(x2); //true
    }

    private static void compoundOperator() {
        long l = 10;
        int i = 5;
//        i = i * l; //OES NOT COMPILE
        i *= l; //COMPILE
        System.out.println("i = i * l DOES NOT COMPILE");

        System.out.println("i *= l = " + i);
        Double l1 = 10.0 + l;
        int x = Integer.MAX_VALUE * Integer.MAX_VALUE;
    }

    private static void numericPromotion() {
        int x = 100;
        long y = x; //COMPILE
        var y2 = x + 1L; //COMPILE y2 becomes a long
//        x = y //DOES NOT COMPILE

        long l0 = 30;
//        Long l1 = 13; //DOES NOT COMPILE
        Long l2 = 30L;
        float f0 = 0;
//        float f1 = 0.0;  //DOES NOT COMPILE
        float f2 = 0.0f;

//        int i0 =  5*2L; //DOES NOT COMPILE
//        short s0 = 10000 + 30000; //DOES NOT COMPILE
        short s1 = 10000 + 22767; //COMPILE
        short a = 14;
        float b = 13;
        double c = 30;

        short s = 2 + 1;
//        short s2 = 2+1+s; //DOES NOT COMPILE

        var d = a * b / c;
        System.out.println("a*b/c = " + d);
    }

    private static void modules() {
        System.out.println("2 % 5 = " + 2 % 5); //2
        System.out.println("2 % -5 = " + 2 % -5); //2
        System.out.println("-2 % -5 = " + -2 % -5); //-2
        System.out.println("-2 % 5 = " + -2 % 5); //-2

    }

    private static void incrementAndDecrement() {
        var x = 10;
        System.out.println("Increment 10, with x++ = " + x++);
        System.out.println("Increment 11  with ++x =  " + ++x);
    }

    private static void playAroundWithNegative() {
        double x = 1.21;
        System.out.println(x = -x);
        System.out.println(-x);
        System.out.println("-(-x)) = " + -(-x));
    }

    private static void bitwiseOperator() {
        System.out.println("Bitwise negation operator (~) with value 100 = " + ~100);
        System.out.println("Bitwise negation operator (~) with value 101 = " + ~101);
        System.out.println("Bitwise negation operator (~) with value 102 = " + ~102);
        System.out.println("Bitwise negation operator (~) with value -200 = " + ~-200);

        //BITWISE AND -> Compares the bits of two numbers, returning a number that has a 1 in each digit in which both operands have a 1 and 0 otherwise
        //BITWISE OR -> Compares the bits of two numbers, returning a number that has a 1 in each digit in which either operands has a 1, and 0 otherwise
        //BITWISE EXCLUSIVE OR -> Compares the bits of two numbers, returning a number that has a 0 in each digit that matched, and 1 otherwise

        System.out.println("70 & 70 = " + (70 & 70)); //70
        System.out.println("70 | 70 = " + (70 | 70)); //70
        System.out.println("~70 = " + ~70); //-71
        System.out.println("70 & -62 = " + (70 & -62)); //66
        System.out.println("70 & -69 = " + (70 & -69)); //2
        System.out.println("70 & -70 = " + (70 & -70)); //2
        System.out.println("70 & -71 = " + (70 & -71)); //0
        System.out.println("70 & -72 = " + (70 & -72)); //0
        System.out.println("70 ^ 70 = " + (70 ^ 70)); //0
        System.out.println("70 ^ -70 = " + (70 ^ -70)); //-4
        System.out.println("70 ^ -71 = " + (70 ^ -71)); //-1
//        System.out.println("\"b\" ^ \"a\" = " + ("b" ^ "a")); //DOES NOT COMPILE


    }
}
