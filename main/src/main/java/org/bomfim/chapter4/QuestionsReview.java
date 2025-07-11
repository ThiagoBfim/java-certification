package org.bomfim.chapter4;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class QuestionsReview {

    public static void main(String[] args) {
//        int[][] java = new int[][]; //Does not compile


        int[][] java = new int[0][];
        System.out.println("int[][] java = new int[][]; //DOES NOT COMPILE");
        System.out.println("int[][] java = new int[0][]; //COMPILE");

        // int random = Math.random();
        // long random = Math.random();
        double random = Math.random();
        long one = Math.min(5L, 3);
        double two = Math.min(5L, 3.0);
        var string = "12345";
        var builder = new StringBuilder("12345");
        var x = builder.replace(2, 4, "6"); //1265
        System.out.println(x.charAt(3)); //
        String a = "a";
        a += 1;
        System.out.println("a1".equals(a));
        System.out.println("a1" == a);
//        int two = Math.round(1.0f);
        long two2 = Math.round(1.0);

        LocalDate date = LocalDate.now();
        date.withMonth(5);
        date.atTime(LocalTime.now());
        System.out.println(LocalTime.of(1, 20).getHour());
        System.out.println(LocalTime.of(1, 30).getHour());


        System.out.println("Arrays.binarySearch(new int[]{1, 2, 3, 4, 6}, 2) - " + Arrays.binarySearch(new int[]{1, 2, 3, 4, 6}, 2)); //1
        System.out.println("Arrays.binarySearch(new int[]{1, 2, 3, 4, 6}, 2) - " + Arrays.binarySearch(new int[]{1, 2, 3, 4, 6}, 5)); //-5
        System.out.println("Arrays.binarySearch(new int[]{1, 2, 3, 4, 6}, 2) - " + Arrays.binarySearch(new int[]{1, 2, 4, 6}, 3)); //-3
        test();

        System.out.println("arrays\n");
        String[] s1 = {"Camel", "Peacock", "Llama"};
        String[] s2 = {"Camel", "Llama", "Peacock"};
        String[] s3 = {"Camel"};
        String[] s4 = {"Camel", null};
        System.out.println(Arrays.compare(s1, s2)); //P-L = 4
        System.out.println(Arrays.compare(s3, s4)); //-1
        System.out.println(Arrays.compare(s4, s3)); //1
        System.out.println(Arrays.compare(s4, s4)); //0
        System.out.println(Arrays.mismatch(s1, s2)); //1
        System.out.println(Arrays.mismatch(s3, s4)); //1
        System.out.println(Arrays.mismatch(s4, s4)); //-1
        dateTest();
    }

    private static void dateTest() {
        var date = LocalDate.of(2028, Month.MARCH, 12);
        var time = LocalTime.of(1, 30);
        var zone = ZoneId.of("US/Eastern");
        var dateTime1 = ZonedDateTime.of(date, time, zone);
        var dateTime2 = dateTime1.plus(1, ChronoUnit.HOURS);

        System.out.println(dateTime1); //2028-03-12T01:30-05:00[US/Eastern]
        System.out.println(dateTime2); //2028-03-12T03:30-04:00[US/Eastern]
        long diff = ChronoUnit.HOURS.between(dateTime1, dateTime2); //1
        int diffHours = dateTime1.getHour() - dateTime2.getHour(); //2
        System.out.println(diffHours);
        int hour = dateTime2.getHour();
        boolean offset = dateTime1.getOffset()
                == dateTime2.getOffset();
        System.out.println("diff = " + diff);
        System.out.println("hour = " + hour);
        System.out.println("offset = " + offset);

    }

    public static void test() {
        var arr = new String[]{"PIG", "pig", "123"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.binarySearch(arr, "Pippa"));
        System.out.println(Arrays.binarySearch(arr, "pippa"));
        System.out.println(Arrays.binarySearch(arr, "PIG"));
        System.out.println(Arrays.binarySearch(arr, "11"));
    }
}
