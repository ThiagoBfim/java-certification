package org.bomfim.chapter4;

public class Exercises {

    public static void main(String[] args) {
        stringConcatenation();
    }

    private static void stringConcatenation() {
        System.out.println(1 + 2 + "c"); //3c
        System.out.println("c" + 1 + 2); //c12
        System.out.println("c" + 1 * 2); //c2
//        System.out.println("c" + 1 - 2); //DOES NOT COMPILE
    }
}
