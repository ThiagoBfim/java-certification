package org.bomfim.chapter4;

import java.time.LocalTime;

public class QuestionsReview {

    public static void main(String[] args) {
//        int[][] java = new int[][]; //Does not compile
        int[][] java = new int[0][];
        System.out.println("int[][] java = new int[][]; //DOES NOT COMPILE");
        System.out.println("int[][] java = new int[0][]; //COMPILE");

        String a = "a";
        a += 1;
        System.out.println("a1".equals(a));
        System.out.println("a1" == a);

        System.out.println(LocalTime.of(1,20).getHour());
        System.out.println(LocalTime.of(1,30).getHour());
    }
}
