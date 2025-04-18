package org.bomfim.chapter3;

public class Exercises {

    public static void main(String[] args) {
        trickQuestionIf();
        patternMatching();
        switchExample();
        switchFinal();
        switchWithYield();
        switchPatternMatching();

        int x = 0;
        for (long i = 0; x < 10; x++) {
            System.out.println(x);
        }

        byte a = 0;
        switch (a) {
            case 0:
                break;
            case 1:
                break;
        }

        forArray(args);
        testLoopWithVarargs();

        labels();
        int[] v = new int[]{1,2,3,4,5};

        for (int i=0; i<=v.length-1; i++){
            System.out.print(v[i]);
        }

//        do {}
//        while true //DOES NOT COMPILE

        int[] values = new int[10];
        for (long value : values) {}
        for (Integer value : values) {}
        for (Object value : values) {}
        for (var value : values) {}
//        for (Long value : values) {} //DOES NOT COMPILE

        Integer[] values2 = new Integer[10];
        for (int value : values2) {
        } //DOES NOT COMPILE
    }

    private static void labels() {
        int x = 0;
        OUT_LOOP:
        for (long i = 0; i < 10; i++) {
            x++;
            INNER_LOOP:
            for (long j = 0; j < 10; j++) {
                x++;
                break OUT_LOOP;
            }
        }
        System.out.println(x);

    }

    private static void forArray(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    private static void testLoopWithVarargs(String... names) {
        for (String name : names) {
            System.out.println(name);
        }
    }

    private static void switchPatternMatching() {
        System.out.println("Pattern matching MUST NOT have unreachable clauses");
        Number x = 10;
        final Number value10 = 10;
        switch (x) {
//            case value10 -> System.out.println(10); DOES NOT COMPILE
            case Number n -> System.out.println(n);
//            case Integer i -> System.out.println(i); //DOES NOT COMPILE
        }
        switch (x) {
            case null -> System.out.println(x);
            case Integer n when n > 10 -> System.out.println(n);
            case Integer n when n >= 10 -> System.out.println(n);
            case Integer n when n < 10 -> System.out.println(n);
//            case Integer n when n = 10 -> System.out.println(n); //DOES NOT COMPILE
            default -> throw new IllegalStateException("Unexpected value: " + x);
        }
    }

    private static void switchWithYield() {
        Integer fish = 1;
        final int  value10 = 10;
        var value = switch (fish) {
//            default -> throw new IllegalStateException("Unexpected value: " + fish); //DOES NOT COMPILE, IT MUST BE AFTER NULL CLAUSE
            case null -> fish;
//            default -> throw new IllegalStateException("Unexpected value: " + fish); //DOES THROW EXCEPTION
            case 1 -> "FISH 1";
            case value10 -> "10";
            case 2 -> {
                yield "FISH 2";
                //MUST NOT HAVE A SEMICOLON
            }
            default -> throw new IllegalStateException("Unexpected value: " + fish); //REQUIRED
        };

        System.out.println(value);
    }

    static final int getCookies() {
        return 4;
    }

    private static void switchFinal() {
        final int a = 4;
        int b = 2;
        int c = getCookies();
        final int d = getCookies();
        int switchValue = 3;
        switch (switchValue) {
//            case b: {} //DOES NOT COMPILE
//              case b -> {} //DOES NOT COMPILE
            default -> System.out.println("TEST");
            case a -> System.out.println("final");
            case 5 + 1 -> System.out.println("final");
//            case d -> System.out.println("final"); //DOES NOT COMPILE
        }

        switch (5) {
            default -> System.out.println("TEST");
            case 1 -> System.out.println(1);
            case 5 -> System.out.println(5);
        }
    }

    private static void switchExample() {
        String name = "Hello World";
        switch (name) {
            case "Hello World":
                System.out.println("Hello World");
                break;
//            case "World" -> System.out.println("World"); //DOES NOT COMPILE
        }

        switch (name) {
            case "Hello World" -> System.out.println("Hello World");
            case "World" -> System.out.println("Hello World");
        }

        Integer x = 10;
        String value = switch (x) {
//            case null -> System.out.println("Hello World");  DOES NOT COMPILE
            case 10 -> "10";
            case 9 -> "9";
            default -> throw new IllegalStateException("Unexpected value: " + x);
        };

        Number number = 8;
        switch (number) {
            case Double d -> System.out.println("Double");
            case Integer i -> System.out.println("Integer");
//            case 10 -> System.out.println("10"); //DOES NOT COMPILE
            default -> throw new IllegalStateException("Unexpected value: " + number); //REQUIRED
        }

        int z = 10;
        double y = 8;
        switch (z) {
            case 10 -> System.out.println("10");
        }
//        switch (y) { //DOES NOT COMPILE
//            case 10 -> System.out.println("10");
//        }
    }

    private static void patternMatching() {
        Number x = 10;
        if (x instanceof Integer i && i.compareTo(5) > 0) {
//            i = 10.5 //DOES NOT COMPILE
            i = 20;
            System.out.println(i);
            System.out.println(x);
        }
//        if (x instanceof String A) //DOES NOT COMMPILE

        Number data = 10;
//        if(data instanceof Integer result || result.compareTo(5) > 0) //DOES NOT COMPILE
//        if(data instanceof Integer result & result.compareTo(5) > 0) //DOES NOT COMPILE
        if (data instanceof Integer result && result.compareTo(5) > 0) {
        }//COMPILE
//        if(data instanceof Integer data) //DOES NOT COMPILE

//        var a = 5;
//        if(a instanceof Integer a2) //DOES NOT COMPILE

        var a = Integer.valueOf(10);
        if (a instanceof Integer a2) {
        } //COMPILE


        if (!(a instanceof Integer a3)) {
//            System.out.println(a3);// DOES NOT COMPILE
            return;
        }
        System.out.println(a3);
    }

    private static void trickQuestionIf() {
//        boolean isTrue = true;
//        if (isTrue)
//            isTrue = false;
//            System.out.println(isTrue);
//        else  //DOES NOT COMPILE
//            System.out.println(false);
    }
}
