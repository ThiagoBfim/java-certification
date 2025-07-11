package org.bomfim.chapter1;

public class QuestionsReview {

    public static void main(String[] args) {
        int x;
        System.out.println("""
                "a" + x"
                b
                c"""); //3 lines
        var String = "a";
        String = "b";
        var a = 1;
//        a = null; //DOES NOT COMPILE

        var z = 9__6;

        System.out.println("""
                    test \s
                    test2 \
                    test3""");

        int a1 = 0b11;
        int a2 = 0xE;
        int a3 = 0b101;
//        int a4 = 9_2.1_2; //does not compile
        double a4 = 9_2.1_2;
//        int a5 = 9_2.1_2;
//        int x2 = 9L;

    }
}
