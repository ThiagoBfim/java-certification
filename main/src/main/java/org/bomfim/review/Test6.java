package org.bomfim.review;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test6 {



        public static void main(String[] args) {
            int ia[][] = new int[5][0];

            var x = Math.pow(2, 2) + Math.pow(2, 2);
            System.out.println(Math.pow(x, 0.5));
            System.out.println(Math.pow(Math.pow(2, 2) + Math.pow(2, 2), .5)); //8.0^0.5 = 2.82..

        System.out.println(false ^ false); //false
        System.out.println(true ^ true); //false
        System.out.println(false ^ true); //true
//        int a, int b = 3;
        int a =2 , b = 3;
//        var a2 = 2, b2 = 3;
        Queue<String> container = new LinkedList<String>();

        Path p1 = Paths.get("/personal/./photos/../readme.txt");
        Path p2 = Paths.get("/personal/index.html");
        System.out.println(p1.relativize(p2)); //../index.html
        System.out.println(p1.normalize()); ///personal/readme.txt
        System.out.println(Path.of("./testA/abc/schedule.xml").relativize(Path.of("./testB/../text.txt"))); //../../../text.txt
        System.out.println(Path.of("./testA/schedule.xml").relativize(Path.of("./testB/../text.txt"))); //../../text.txt
        System.out.println(Path.of("./testA/schedule.xml").relativize(Path.of("./testB/text.txt")));  //../../testB/text.txt
        System.out.println(Path.of("./testA/schedule.xml").relativize(Path.of("text.txt")));  //../../text.txt


        LocalDateTime ld = LocalDateTime.of(2022, Month.OCTOBER, 31,10,0);
        ZonedDateTime date = ZonedDateTime.of(ld, ZoneId.of("US/Eastern"));
        System.out.println(date); //2022-10-31T10:00-04:00[US/Eastern]
        System.out.println(date.plus(Duration.ofDays(1))); //2022-11-01T10:00-04:00[US/Eastern]
        System.out.println(date.plus(Period.ofDays(1))); //2022-11-01T10:00-04:00[US/Eastern]

        LocalDate d1 = LocalDate.parse("2022-01-01", DateTimeFormatter.ISO_LOCAL_DATE); //2022-01-01
//        LocalDate d2 = LocalDate.parse("2022-01-01", DateTimeFormatter.ofPattern("hh:mm")); //DateTimeParseException
        System.out.println(d1);
    }

    Queue container2 = new PriorityQueue();

    public synchronized void add(String s) {
        container2.add(s);
    }

    public synchronized String get() {
        return (String) container2.poll();
    }


    interface IHello2 {
        //        private static String id = 5; //DOES NOT COMPILE
        private static void print() {
        }

        ;
    }

}
