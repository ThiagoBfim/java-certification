package org.bomfim.review;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test9 {

    public static void main(String[] args) throws IOException {

        print(1, 2);
        try {
            print(1, 2.0);
//        }catch (IOException EX){ //NOT possible}
        } catch (Exception exception) {
        }
        int i = tryCatch();
        System.out.println(i);

        var x = new CustomThread();

        x.start();

        System.out.println(5 / 4); //1 performs integer division because both 5 and 4 are integers, resulting in the value 1.
        System.out.println(5 / 4.f); //1.25
//        for(;;Math.random()<0.5){
//            System.out.println("true");
//        }
//        for(int a = 5;false;a++){} //DOES NOT COMPILE
//        for(int a = 5;TRUE;a++){} //INFINITE LOOP
        for (; Math.random() < 0.5; Math.random()) {

        }
        List<String> s = new ArrayList();

        for (; Math.random() < 0.5; ) {
            System.out.println("true");
        }
//        for(;;Math.random()){System.out.println("true");}  INFINITE LOOP


        List<String> list = new ArrayList();
        boolean a = list.remove("a");
        System.out.println(a);
//        String remove = list.remove(2);
        byte b = 127;
        int r = switch (b) {
            case -2 -> b;
            case 3,4 -> b;
//            case5, default -> b; //DOES NOT compile
            default -> b;
        };

        var r2 = 100;
        r2 =+ 100;
        System.out.println(r2);

        float f = 10;
        System.out.println(Paths.get("C:\\Users\\Desktop\\test9\\out.txt")
                .relativize(Paths.get("client.dat"))); //../client.dat

        System.out.println(Paths.get("test/abc/aa/out.txt")
                .relativize(Paths.get("client.dat"))); //../client.dat
    }

    public int x (){
        try {
            print(1, 2.0);
        } finally {
            return 5;
        }
//        return 2; //UNREACHABLE

    }

    public static int tryCatch() {
        try {
            print(1, 2.0);
            return 2;
        } catch (Exception exception) {
            return 6;
        } finally {
            return 5;
        }
//        return 2;



    }


    public static class CustomThread extends Thread {
        @Override
        public void run() {
            System.out.println("RUN");
        }

        @Override
        public void start() {
            System.out.println("START");
            super.start();
        }
    }

    public static void print(int x, double b) {
        System.out.println(x + b);
    }

    public static void print(int x, float b) throws IOException {
        System.out.println(x + b);
    }


    public void usePrintWriter(PrintWriter pw) throws IOException {
        boolean bval = true;
        pw.print(bval);
    }


    public static class ClassList extends ArrayList<Class> {
        public boolean add(Integer s) {
            return true;
        }

        public boolean add(String s) {
            return true;
        }

        @Override
        public boolean add(Class aClass) {
            return super.add(aClass);
        }

        //        public boolean add(Object s){} //DOES NOT COMPILE
    }

    public static class Class {
        public boolean add(Integer s) {
            return true;
        }

        public boolean add(String s) {
            return true;
        }

        public boolean add(List<String> s) {
            return true;
        }
    }

    public static class Class2 extends Class {

        public boolean add(Object s) {
            return true;
        }

        public boolean add(List s) {
            return true;
        }
    }

    interface Drink {
        public static void test() {
        }

        private static void test2() {
        }

        private void test3() {
        }
    }


}
