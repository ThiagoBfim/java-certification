package org.bomfim.review;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

public class Test7 {

    public static void main(String[] args) throws IOException {
//        char[] buffer = new char[5];
//        try (FileReader fr = new FileReader("c:\\temp\\license1.txt"); //01234567
//             FileWriter fw = new FileWriter("c:\\temp\\license2.txt")) {
//            while ((fr.read(buffer)) != -1) {
//                fw.write(buffer);
//            }
//        }
//        //LICENSE2.txt = //0123456734

//        float amount = 1000.0; //DOES NOT COMPILE
        float amount = 1000.0f;

        Stream bkStrm = List.of("AB").stream();
//        long count = bkStrm.peek((String x)->x.toUpperCase()).count(); //DOES NOT COMPILE

//        Runnable r = () -> 5; //DOES NOT COMPILE
        Runnable r2 = () -> test();

        System.out.println(Period.between(LocalDate.now(), LocalDate.now().plusDays(1))); //P1D
        System.out.println(Period.between(LocalDate.now(), LocalDate.now().minusDays(1))); //P-1D
        System.out.println(Duration.between(LocalDateTime.now(), LocalDateTime.now().plusDays(1))); //PT24H0.000008S
        System.out.println(Duration.between(LocalDateTime.now(), LocalDateTime.now().minusDays(1))); //PT-23H-59M-59.999993S

        System.out.println(Path.of("photos/../test/./a.txt").getNameCount()); //5
        System.out.println(Path.of("photos/../test/./a.txt").getName(4)); //a.txt
        System.out.println(Path.of("photos/../test/./a.txt").getName(3)); //.
        System.out.println(Path.of("../../test/./a.txt").normalize()); //../../test/a.txt
        System.out.println(Path.of("/../../test/./a.txt").normalize()); ///test/a.txt
        System.out.println(Path.of("c:\\..\\test\\a.txt").normalize()); //c:\..\test\a.txt

        A a = new B();
        System.out.println(a.x);
        a.print(); //A print 10

        Properties properties = System.getProperties();
        properties.entrySet(); //Set<Map.Entry<Object, Object>>
        properties.keySet(); //Set<Object>

        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock().lock();
        readWriteLock.readLock().lock();

        int x = 5;
        switch (x) {
            case 1:
            case 2, 3, 4:
            case 5:
                b:
                c:
                d:
                System.out.println("a");
            default:
        }

    }

    private static int test() {
        return 0;
    }

    static class A {
        public int x = 5;

        public void print() {
            System.out.println("A print " + x);
        }
    }

    static class B extends A {
        public int x = 10;

        public void print() {
            System.out.println("A print " + x);
        }

        public void print(Integer x) {
            super.print();
        }
    }
}
