package org.bomfim.chapter1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Exercises {


    public static float code;
    public float code2;
    void test(){
        System.out.println(code);
        System.out.println(code2);
    }
    public static void main(String[] args) {
        System.out.println(code);
        double x = 1_2;
        printValueOf();
        doubleValue();
        printTextBlock();

//        int a, var b = 3; //DOES NOT COMPILE

        var a = (Integer) null; //COMPILE
        String s1 = "a", s2;
//        double d1, double d2;

        List<String> foo = new ArrayList<String>();
        foo = new LinkedList<>();
        testa(foo);
    }

    private static void testa(List<String> foo) {

    }

    public void initializeValue(boolean check){
        int a;
        if(check){
            a = 5;
        }
//        System.out.println(a); //compile error
    }
    private static void printTextBlock() {
        System.out.print("""
                *
                **
                ***
                """);

        System.out.println("""
                *
               ***
              *****
                """);

        System.out.println("""
                a \
                b
                """); //a b
//        String block = """ x """; //Does not compile
    }

    private static void doubleValue() {
        Double x = Double.valueOf("200.99");
        System.out.println(x.byteValue()); //-56
        System.out.println(x.intValue()); // 200
        System.out.println(x.doubleValue()); //200.99
    }

    public static void printValueOf(){
        System.out.println(Boolean.valueOf("true")); //true
        System.out.println(Boolean.valueOf("TRuE")); //true
        System.out.println(Boolean.valueOf("false")); //false
        System.out.println(Boolean.valueOf("falsee")); //false
        System.out.println(Boolean.valueOf("x")); //false
        System.out.println(Boolean.valueOf(null)); //false


        System.out.println(Integer.valueOf("5",16)); //5
        System.out.println(Integer.valueOf("a",16)); //10
        System.out.println(Integer.valueOf("F",16)); //15

    }


}
