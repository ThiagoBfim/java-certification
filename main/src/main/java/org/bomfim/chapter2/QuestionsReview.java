package org.bomfim.chapter2;

public class QuestionsReview {

    public static void main(String[] args) {
        int b = 5, c = b++;
        System.out.println(b + " - " + c);
//        short zebra = (byte) weight * (byte) height; //DOES NOT COMPILE
        short zebra = (byte) (double) b;
        System.out.println("9 % 1 = " + 9 % 1);

        long goat = 5;
//        goat = 2.0; //DOES NOT COMPILE
        goat -= 2.0;

        int a = 5;
        a = (a++);
        System.out.print(a); //5

        int sample1 = (2 * 4) % 3; //2
        int sample2 = 3 * 2 % -3;  //0
        int sample3 = 5 * (1 % 2);
        System.out.println(sample1 + ", " + sample2 + ", " + sample3);
        System.out.println(8%2); //0
        System.out.println(8%3); //2
        System.out.println(8%5); //3
        System.out.println(8%7); //1
        System.out.println(8%10); //8
        System.out.println(8%20);
        System.out.println(8%10);
    }

    public void test(){
        short height = 1, weight = 3;
//        short zebra = (byte) weight * (byte) height;
        double ox = 1 + height * 2 + weight;
        long giraffe = 1 + 9 % height + 1;
//        System.out.println(zebra);
        System.out.println(ox);
        System.out.println(giraffe);
    }
}
