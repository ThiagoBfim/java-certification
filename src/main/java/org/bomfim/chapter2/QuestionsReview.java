package org.bomfim.chapter2;

public class QuestionsReview {

    public static void main(String[] args) {
        int b = 5, c = b++;
        System.out.println(b + " - " + c);
//        short zebra = (byte) weight * (byte) height; //DOES NOT COMPILE
        short zebra = (byte) (double) b;
        System.out.println("9 % 1 = " + 9 % 1);
    }
}
