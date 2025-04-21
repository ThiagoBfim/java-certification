package org.bomfim.chapter4;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;

public class Exercises {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        options();
        int num = in.nextInt();

        do {
            switch (num) {
                case -1:
                    options();
                    break;
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    stringConcatenation();
                    break;
                case 2:
                    chartAt();
                    break;
                case 3:
                    codePoint();
                    break;
                case 4:
                    substring();
                    break;
                case 5:
                    indexOf();
                    break;
                case 6:
                    startEndsWithAndContains();
                    break;
                case 7:
                    replace();
                    break;
                case 8:
                    trimAndStrip();
                    break;
                case 9:
                    indent();
                    break;
                case 10:
                    formatAndFormatted();
                    break;
                case 11:
                    stringBuilder();
                    break;
                case 12:
                    stringBuilderInsertAndDelete();
                    break;
                case 13:
                    stringBuilderReplace();
                    break;
                case 14:
                    equalsAndEquality();
                    break;
                case 15:
                    arrays();
                    break;
                case 16:
                    sort();
                    break;
                case 17:
                    binarySearch();
                    break;
                case 18:
                    compare();
                    break;
                case 19:
                    mismatch();
                    break;
                case 20:
                    mathApi();
                    break;
                case 21:
                    bigDecimal();
                    break;
                case 22:
                    dateTime();
                    break;
                case 23:
                    instant();
                    break;
                case 24:
                    summerTime();
                    break;
                case 99:
                    stringConcatenation();
                    chartAt();
                    codePoint();
                    substring();
                    indexOf();
                    startEndsWithAndContains();
                    replace();
                    trimAndStrip();
                    indent();
                    formatAndFormatted();
                    stringBuilder();
                    stringBuilderInsertAndDelete();
                    stringBuilderReplace();
                    equalsAndEquality();
                    arrays();
                    sort();
                    binarySearch();
                    compare();
                    mismatch();
                    mathApi();
                    bigDecimal();
                    dateTime();
                    instant();
                    summerTime();
                    break;
            }
            System.out.println("\nChoose another option. Between: 0 - 24. Exit: 0. Full: 99, Menu: -1");
            num = in.nextInt();

        } while (num != 0);
    }

    private static void options() {
        System.out.println("-1 - Options");
        System.out.println("0 - Exit");
        System.out.println("1 - StringConcatenation");
        System.out.println("2 - ChartAt");
        System.out.println("3 - codePoint();");
        System.out.println("4 - substring();");
        System.out.println("5 - indexOf();");
        System.out.println("6 - startEndsWithAndContains();");
        System.out.println("7 - replace();");
        System.out.println("8 - trimAndStrip();");
        System.out.println("9 - idendent();");
        System.out.println("10 - formatAndFormatted();");
        System.out.println("11 - stringBuilder();");
        System.out.println("12 - stringBuilderInsertAndDelete();");
        System.out.println("13 - stringBuilderReplace();");
        System.out.println("14 - equalsAndEquality();");
        System.out.println("15 - arrays();");
        System.out.println("16 - sort();");
        System.out.println("17 - binarySearch();");
        System.out.println("18 - compare();");
        System.out.println("19 - mismatch();");
        System.out.println("20 - mathApi();");
        System.out.println("21 - bigDecimal();");
        System.out.println("22 - dateTime();");
        System.out.println("23 - instant();");
        System.out.println("24 - summerTime();");
        System.out.println("99 - Full");
    }

    private static void summerTime() {
        System.out.println("\n Summer time");
        System.out.println("BE CAREFUL, DATE DOES SUPPORT 0 until day 07");
        System.out.println("""
                LocalDate.of(2025, Month.MARCH, 07); //COMPILE. Day 07
                LocalDate.of(2025, Month.MARCH, 08); //DOES NOT COMPILE. Day 08
                """);
        System.out.println("LocalDate.of(2025, 03, 9).atTime(1, 30, 0).atZone(ZoneId.of(\"US/Eastern\")); // " + LocalDate.of(2025, 03, 9).atTime(1, 30, 0).atZone(ZoneId.of("US/Eastern")));
        System.out.println("LocalDate.of(2025, 03, 9).atTime(1, 30, 0).plusHours(1).atZone(ZoneId.of(\"US/Eastern\")); // " + LocalDate.of(2025, 03, 9).atTime(1, 30, 0).plusHours(1).atZone(ZoneId.of("US/Eastern")));
        System.out.println("LocalDate.of(2025, 03, 9).atTime(1, 30, 0).atZone(ZoneId.of(\"US/Eastern\")).plusHours(1); // " + LocalDate.of(2025, 03, 9).atTime(1, 30, 0).atZone(ZoneId.of("US/Eastern")).plusHours(1));
        System.out.println("LocalDate.of(2025, 11, 2).atTime(1, 30, 0).atZone(ZoneId.of(\"US/Eastern\")); // " + LocalDate.of(2025, 11, 2).atTime(1, 30, 0).atZone(ZoneId.of("US/Eastern")));
        System.out.println("LocalDate.of(2025, 11, 2).atTime(1, 30, 0).atZone(ZoneId.of(\"US/Eastern\")).plusHours(1); // " + LocalDate.of(2025, 11, 2).atTime(1, 30, 0).atZone(ZoneId.of("US/Eastern")).plusHours(1));
        System.out.println("LocalDate.of(2025, 11, 2).atTime(1, 30, 0).plusHours(1).atZone(ZoneId.of(\"US/Eastern\")); // " + LocalDate.of(2025, 11, 2).atTime(1, 30, 0).plusHours(1).atZone(ZoneId.of("US/Eastern")));
    }

    private static void instant() {
        System.out.println("\n Instant");
        System.out.println("The instant solves the Zone time to GMT-0");
        System.out.println("LocalDateTime cannot be converted to Instant, it does not have time zone");
        System.out.println("Instant.now() //" + Instant.now());
        System.out.println("ZonedDateTime.of(LocalDate.of(2025, Month.JANUARY, 20),\n" +
                "                LocalTime.of(10, 10, 10), ZoneId.of(\"US/Eastern\")) //" +
                ZonedDateTime.of(LocalDate.of(2025, Month.JANUARY, 20),
                        LocalTime.of(10, 10, 10), ZoneId.of("US/Eastern")));
        System.out.println("ZonedDateTime.of(LocalDate.of(2025, Month.JANUARY, 20),\n" +
                "                LocalTime.of(10, 10, 10), ZoneId.of(\"US/Eastern\")).toInstant() //" +
                ZonedDateTime.of(LocalDate.of(2025, Month.JANUARY, 20),
                        LocalTime.of(10, 10, 10), ZoneId.of("US/Eastern")).toInstant());
    }

    private static void dateTime() {
        System.out.println("\n Date & Time");

        System.out.println("LocalDate date = LocalDate.of(2025, Month.JANUARY, 20);");
        LocalDate date = LocalDate.of(2025, Month.JANUARY, 20);
        System.out.println("date //" + date);
        System.out.println("date.plusDays(2); //This method does not apply the sum to the current date, it returns a new date, like String.");
        System.out.println("date.plusMinutes(1); //DOES NOT COMPILE, this method only exist for LocalDateTime and LocalTime");
        System.out.println("date //" + date);
        System.out.println("date.atTime(5, 30) //" + date.atTime(5, 30));
        System.out.println("date.withDayOfYear(1) //" + date.withDayOfYear(1));
        System.out.println("LocalTime.of(15, 15, 30) //" + LocalTime.of(15, 15, 30));
        System.out.println("System.out.println(LocalTime.of(26,15,30)); //java.time.DateTimeException 26 hours not supported");
        System.out.println("\nZonedDateTime");
        System.out.println("ZonedDateTime.of(2025, 1, 1, 6, 20, 50, 200, ZoneId.of(\"US/Eastern\")) //" + ZonedDateTime.of(2025, 1, 1, 6, 20, 50, 200, ZoneId.of("US/Eastern")));
        System.out.println("ZonedDateTime.of(2025, Month.JANUARY, 1, 6, 20, 50, 200, ZoneId.of(\"US/Eastern\")); //DOES NOT COMPILE  -> Month.JANUARY is not supported");

        System.out.println("\nPeriod");
        System.out.println("Period.between(LocalDate.now().minusYears(1), LocalDate.now()) //" + Period.between(LocalDate.now().minusYears(1), LocalDate.now()));
        System.out.println("Period.between(LocalDate.now(), LocalDate.now().minusYears(1)) //" + Period.between(LocalDate.now(), LocalDate.now().minusYears(1)));
        System.out.println("LocalDate.of(2025, 1, 1).plus(Period.ofMonths(1)) //" + LocalDate.of(2025, 1, 1).plus(Period.ofMonths(1)));
        System.out.println("LocalDate.of(2025, 1, 1).plus(Period.ofMonths(1)).plus(Period.ofYears(1)); //" + LocalDate.of(2025, 1, 1).plus(Period.ofMonths(1)).plus(Period.ofYears(1))); //2026-02-01
        System.out.println("LocalDate.of(2025, 1, 1).plus(Period.ofMonths(1).ofYears(1)); //" + LocalDate.of(2025, 1, 1).plus(Period.ofMonths(1).ofYears(1))); //2026-01-01
        System.out.println("Period.ofMonths(1); //" + Period.ofMonths(1));
        System.out.println("Period.ofMonths(1).ofYears(1); //" + Period.ofMonths(1).ofYears(1));
        System.out.println("Period.ofMonths(1).plusYears(1).plusDays(1); //" + Period.ofMonths(1).plusYears(1).plusDays(1));

        System.out.println("LocalTime.of(20,10,30).plus(Period.ofMonths(1)); //Exception");
        System.out.println("LocalTime.of(20, 10, 30).plus(Duration.ofDays(1)); //" + LocalTime.of(20, 10, 30).plus(Duration.ofDays(1)));
        System.out.println("System.out.println(LocalDate.of(2025,1,1).plus(Duration.ofDays(1))); //Exception");
        System.out.println("\nDuration");
        System.out.println("Duration.ofDays(1).ofSeconds(10); //" + Duration.ofDays(1).ofSeconds(10));
        System.out.println("Duration.ofMinutes(90); //" + Duration.ofMinutes(90));
        System.out.println("Duration.of(90, ChronoUnit.DAYS); //" + Duration.of(90, ChronoUnit.DAYS));
        System.out.println("System.out.println(LocalDate.of(2025, 1, 1).plus(Duration.of(90, ChronoUnit.DAYS))); //Exception cannot add Duration to LocalDate");
        System.out.println("LocalTime.of(20, 10, 30).plus(Duration.of(2, ChronoUnit.DAYS)) //" + LocalTime.of(20, 10, 30).plus(Duration.of(2, ChronoUnit.DAYS)));
        System.out.println("System.out.println(LocalTime.of(20, 10, 30).plus(Duration.of(2, ChronoUnit.MONTHS))); //Exception cannot add Duration to LocalTime");
        System.out.println("Duration.ofDays(1).plusSeconds(10) //" + Duration.ofDays(1).plusSeconds(10));
    }

    private static void bigDecimal() {
        System.out.println("\n BIG DECIMAL");
        System.out.println("64.1 * 100 - " + (64.1 * 100));
        System.out.println("BigDecimal.valueOf(64.1).multiply(BigDecimal.valueOf(100)) - " + BigDecimal.valueOf(64.1).multiply(BigDecimal.valueOf(100)));
    }

    private static void mathApi() {
        System.out.println("\n MATH API");
        System.out.println("Remember to watch return types on math operations");

        System.out.println("Math.min() can return int, long, fload and double");
        System.out.println("Math.max() can return int, long, fload and double");
        System.out.println("Math.round() return int or long");
        System.out.println("Math.random() return double");
        System.out.println("Math.ceil(), Math.floor() and Math.pow() return double");


        System.out.println("Math.min(1, 2) - " + Math.min(1, 2));
        System.out.println("Math.min(2, 2) - " + Math.min(2, 2));
        System.out.println("Math.min(3, 2) - " + Math.min(3, 2));
        System.out.println();
        System.out.println("Math.max(1, 2) - " + Math.max(1, 2));
        System.out.println("Math.max(2, 2) - " + Math.max(2, 2));
        System.out.println("Math.max(3, 2) - " + Math.max(3, 2));
        System.out.println();
        System.out.println("Math.round(3.6) - " + Math.round(3.6));
        System.out.println("Math.round(3.50) - " + Math.round(3.50));
        System.out.println("Math.round(3.49) - " + Math.round(3.49));
        System.out.println();
        System.out.println("Math.ceil(3.6) - " + Math.ceil(3.6));
        System.out.println("Math.ceil(3.50) - " + Math.ceil(3.50));
        System.out.println("Math.ceil(3.49) - " + Math.ceil(3.49));
        System.out.println();
        System.out.println("Math.floor(3.6) - " + Math.floor(3.6));
        System.out.println("Math.floor(3.50) - " + Math.floor(3.50));
        System.out.println("Math.floor(3.49) - " + Math.floor(3.49));
        System.out.println();
        System.out.println("Math.pow(3, 2) - " + Math.pow(3, 2));
        System.out.println("Math.pow(3, 3) - " + Math.pow(3, 3));
        System.out.println();

        System.out.println("Math.random() - " + Math.random());
    }

    private static void mismatch() {
        System.out.println("\n mismatch()");
        System.out.println("If the arrays are equal mismatch() returns -1, otherwise it returns the first index where they differ");
        System.out.println("Arrays.mismatch(new int[]{1}, new int[]{1}) - " + Arrays.mismatch(new int[]{1}, new int[]{1})); //-1
        System.out.println("Arrays.mismatch(new int[]{1}, new int[]{2}) - " + Arrays.mismatch(new int[]{1}, new int[]{2})); //0
        System.out.println("Arrays.mismatch(new int[]{1, 2}, new int[]{2}) - " + Arrays.mismatch(new int[]{1, 2}, new int[]{2})); //0
        System.out.println("Arrays.mismatch(new int[]{1, 5}, new int[]{2, 1}) - " + Arrays.mismatch(new int[]{1, 5}, new int[]{2, 1})); //0
        System.out.println("Arrays.mismatch(new int[]{2, 3}, new int[]{2, 1}) - " + Arrays.mismatch(new int[]{2, 3}, new int[]{2, 1})); //1
        System.out.println("Arrays.mismatch(new int[]{2, 3}, new int[]{2, 3, 1}) - " + Arrays.mismatch(new int[]{2, 3}, new int[]{2, 3, 1})); //2
        System.out.println("Arrays.mismatch(new int[]{2, 3, 1}, new int[]{2, 3}) - " + Arrays.mismatch(new int[]{2, 3, 1}, new int[]{2, 3})); //2
        System.out.println("Arrays.mismatch(new int[]{1, 1, 1, 2}, new int[]{1, 1, 1}) - " + Arrays.mismatch(new int[]{1, 1, 1, 2}, new int[]{1, 1, 1})); //3
        System.out.println("Arrays.mismatch(new Integer[]{1, 1, null, 2}, new Integer[]{1, 1, 1}) - " + Arrays.mismatch(new Integer[]{1, 1, null, 2}, new Integer[]{1, 1, 1})); //2
    }

    private static void compare() {
        System.out.println("\n Compare");
        System.out.println("Negative number means small -1");
        System.out.println("Zero means equals 0");
        System.out.println("Positive number means bigger 1");
        System.out.println("Comparing array it compares each element in the order and return if it is -1 or 1");
        System.out.println("Integer.valueOf(5).compareTo(Integer.valueOf(5)) - " + Integer.valueOf(5).compareTo(Integer.valueOf(5)));
        System.out.println("Integer.valueOf(5).compareTo(Integer.valueOf(6)) - " + Integer.valueOf(5).compareTo(Integer.valueOf(6)));
        System.out.println("Integer.valueOf(5).compareTo(Integer.valueOf(4)) - " + Integer.valueOf(5).compareTo(Integer.valueOf(4)));
        System.out.println("Integer.valueOf(5).compareTo(Integer.valueOf(0)) - " + Integer.valueOf(5).compareTo(Integer.valueOf(0)));
        System.out.println("Arrays.compare(new int[]{1}, new int[]{2}) - " + Arrays.compare(new int[]{1}, new int[]{2}));
        System.out.println("Arrays.compare(new int[]{1, 2}, new int[]{2}) - " + Arrays.compare(new int[]{1, 2}, new int[]{2}));
        System.out.println("Arrays.compare(new int[]{1, 5}, new int[]{2, 1}) - " + Arrays.compare(new int[]{1, 5}, new int[]{2, 1}));
        System.out.println("Arrays.compare(new int[]{1, 3}, new int[]{1, 1}) - " + Arrays.compare(new int[]{1, 3}, new int[]{1, 1}));
        System.out.println("Arrays.compare(new int[]{2, 3}, new int[]{2, 3, 1}) - " + Arrays.compare(new int[]{2, 3}, new int[]{2, 3, 1})); //-1
        System.out.println("Arrays.compare(new int[]{2, 3, 1}, new int[]{2, 3}) - " + Arrays.compare(new int[]{2, 3, 1}, new int[]{2, 3})); //1
        System.out.println("Arrays.compare(new Integer[]{null, 3, 1}, new Integer[]{2, 3}) - " + Arrays.compare(new Integer[]{null, 3, 1}, new Integer[]{2, 3})); //-1
        System.out.println("Arrays.compare(new String[]{\"a\", \"b\"}, new String[]{\"A\", \"b\"}) - " + Arrays.compare(new String[]{"a", "b"}, new String[]{"A", "b"})); //32
        System.out.println("Arrays.compare(new String[]{\"A\", \"b\"}, new String[]{\"a\", \"b\"}) - " + Arrays.compare(new String[]{"A", "b"}, new String[]{"a", "b"})); //-32
    }

    private static void binarySearch() {
        System.out.println("\n Binary Search");
        System.out.println("\n Binary Search only works for sorted arrays");
        System.out.println("Arrays.binarySearch(new int[]{1, 2, 3, 4}, 2) - " + Arrays.binarySearch(new int[]{1, 2, 3, 4}, 2)); //1
        System.out.println("Arrays.binarySearch(new int[]{1, 2, 3, 4}, 5) - " + Arrays.binarySearch(new int[]{1, 2, 3, 4}, 5)); //-5 //NEXT position
        System.out.println("Arrays.binarySearch(new int[]{1, 2, 4, 6}, 3) - " + Arrays.binarySearch(new int[]{1, 2, 4, 6}, 3)); //-3 //NEXT position
        System.out.println("Arrays.binarySearch(new int[]{1, 2, 5, 3, 4, 2}, 2) - " + Arrays.binarySearch(new int[]{1, 2, 5, 3, 4, 2}, 2)); //1
        System.out.println("Arrays.binarySearch(new int[]{1, 2, 5, 3, 4, 2}, 4) - " + Arrays.binarySearch(new int[]{1, 2, 5, 3, 4, 2}, 4)); //-3
        System.out.println("Arrays.binarySearch(new int[]{1, 2, 5, 3, 4, 2}, 6) - " + Arrays.binarySearch(new int[]{1, 2, 5, 3, 4, 2}, 6)); //-1
        System.out.println("Arrays.binarySearch(new String[]{\"a\", \"b\", \"c\", \"d\"}, \"c\") - " + Arrays.binarySearch(new String[]{"a", "b", "c", "d"}, "c")); //2
    }

    private static void sort() {
        System.out.println("\n Sort");
        System.out.println("The sort order is always first numbers, than UPPER case, than lower case for array");
        System.out.println(" Arrays.sort(\"A\", 1, \"2\", \"B\", \"b\", \"a\"}); //java.lang.ClassCastException All the objects must be the same type");
        Object[] valuesSort = {"A", "1", "2", "B", "b", "a"};

        System.out.print("""
                Object[] valuesSort = {"A", "1", "2", "B", "b", "a"};
                Arrays.sort(valuesSort);
                """);
        Arrays.sort(valuesSort);
        System.out.println("Arrays.toString(valuesSort) - " + Arrays.toString(valuesSort)); // [A, 1, 2, B, b, a]
    }

    private static void arrays() {
        System.out.println("\n Arrays");

        System.out.println("int[][] java = new int[][]; //DOES NOT COMPILE");
        System.out.println("int[][] java = new int[0][]; //COMPILE");

        System.out.println("""
                double one = Math.pow(1, 2);
                long two = Math.round(1.0);
                double three = Math.random();
                var doubles = new double[] {one, two, three}; //COMPILES
                var doubles = new Double[] {one, two, three}; //DOES NOT COMPILE
                """);
        double one = Math.pow(1, 2);
        long two = Math.round(1.0);
        double three = Math.random();
        var doubles = new double[] {one, two, three};

        int[] x = {5, 3, 4};
        int[] y = new int[]{5, 3, 4};
        int[] y2 = new int[]{5, 3, 4};
        int[] y3 = new int[]{5, 3, 4};
        int z[] = new int[]{5, 3, 4};
        int z2[] = new int[]{5, 3, 4};
        System.out.print("""
                int[] x = {5, 3, 4};
                int[] y = new int[]{5, 3, 4};
                int[] y2 = new int[]{5, 3, 4};
                int[] y3 = new int[]{5, 3, 4};
                int z[] = new int[]{5, 3, 4};
                int z2[] = new int[]{5, 3, 4};
                """);
        System.out.println("x.length - " + x.length);
        System.out.println("y.length - " + y.length);
        System.out.println("y2.length - " + y2.length);
        System.out.println("y3.length - " + y3.length);
        System.out.println("z.length - " + z.length);
        System.out.println("z2.length - " + z2.length);

        System.out.println();
        int[] w1, w2;
        w1 = new int[]{5, 3, 4};
        w2 = new int[]{5, 3, 4};
        int w3[], w4;
        w3 = new int[]{5, 3, 4};
        w4 = 5;
        System.out.print("""
                int[] w1, w2;
                w1 = new int[]{5, 3, 4};
                w2 = new int[]{5, 3, 4};
                int w3[], w4;
                w3 = new int[]{5, 3, 4};
                w4 = 5;
                """);
        System.out.println("NOTE: int[] ids, types IS DIFFERENT FROM int ids[], types");

        System.out.println();

        System.out.println("int varx[], otherArray[][]; // varx is 1D array, otherArray is 2D array");
        System.out.println("int[] varx[], otherArray[][]; // varx is 2D array, otherArray is 3D array");
        System.out.println();

        System.out.println("""
                Integer[] values = {1, 2, 3};
                var valuesInt = {1,2,3}; //DOES NOT COMPILE
                int[] valuesInt = {1, 2, 3}; //DOES NOT COMPILE
                Object[] objects = valuesInt; //DOES NOT COMPILE
                Object[] objects = values;
                Long[] xdas = values; //DOES NOT COMPILE
                objects[0] = 5L; //ArrayStoreException
                objects[0] = Short.valueOf("2"); //ArrayStoreException
                """);
        Integer[] values = {1, 2, 3};
//        var valuesInt = {1,2,3}; //DOES NOT COMPILE
        int[] valuesInt = {1, 2, 3}; //DOES NOT COMPILE
//        Object[] objects = valuesInt; //DOES NOT COMPILE
        Object[] objects = values;
//        Long[] xdas = values; //DOES NOT COMPILE
//        objects[0] = 5L; //ArrayStoreException
//        objects[0] = Short.valueOf("2"); //ArrayStoreException
    }

    private static void equalsAndEquality() {
        System.out.println("\n Equality - Equals - ==");
        System.out.println("new StringBuilder(\"ab\").equals(new StringBuilder(\"ab\")) - " + new StringBuilder("ab").equals(new StringBuilder("ab")));
        System.out.println("new String(\"ab\").equals(new String(\"ab\")) - " + (new String("ab").equals(new String("ab"))));
        System.out.println("new String(\"ab\") == (new String(\"ab\")) - " + (new String("ab") == (new String("ab"))));
        System.out.println("new String(\"ab\").intern() == (new String(\"ab\").intern()) - " + (new String("ab").intern() == (new String("ab").intern())));
        System.out.println("\"ab\" == \"ab\" - " + ("ab" == "ab"));
        System.out.println("\"ab\".concat(\"1\") == (\"ab\" + 1) - " + ("ab".concat("1") == ("ab" + 1)));
        System.out.println("\"ab\".concat(\"1\").intern() == (\"ab\" + 1) - " + ("ab".concat("1").intern() == ("ab" + 1)));
        System.out.println("(\"a\" + \"b\") == (\"a\" + new String(\"b\")) - " + (("a" + "b") == ("a" + new String("b"))));
        System.out.println(" (\"a\" + \"b\") == (\"a\" + new String(\"b\")).intern() - " + (("a" + "b") == ("a" + new String("b")).intern()));
    }

    private static void stringBuilderReplace() {
        System.out.println("\n StringBuilder - Replace");
        System.out.println("The replace from StringBuilder first remove the characters from the range and then it adds the new text to the index");
        System.out.println("new StringBuilder(\"ab\").replace(0, 0, \"AB\") - " + new StringBuilder("ab").replace(0, 0, "AB"));
        System.out.println("new StringBuilder(\"ab\").replace(0, 1, \"A\") - " + new StringBuilder("ab").replace(0, 1, "A"));
        System.out.println("new StringBuilder(\"ab\").replace(1, 1, \"A\") - " + new StringBuilder("ab").replace(1, 1, "A"));
        System.out.println("new StringBuilder(\"ab\").replace(1, 2, \"A\") - " + new StringBuilder("ab").replace(1, 2, "A"));
        System.out.println("new StringBuilder(\"ab\").replace(1, 10,\"A\") - " + new StringBuilder("ab").replace(1, 10, "A"));
        System.out.println("new StringBuilder(\"ab\").replace(3,3, \"A\") //Exception");
//        System.out.println(new StringBuilder("ab").replace(3,3, "A")); //Exception
    }

    private static void stringBuilderInsertAndDelete() {
        System.out.println("\n StringBuilder - Insert");
        System.out.println("new StringBuilder(\"ab\").insert(0, \"-\") - " + new StringBuilder("ab").insert(0, "-"));
        System.out.println("new StringBuilder(\"ab\").insert(1, \"-\") - " + new StringBuilder("ab").insert(1, "-"));
        System.out.println("new StringBuilder(\"ab\").insert(2, \"-\") - " + new StringBuilder("ab").insert(2, "-"));
        System.out.println("new StringBuilder(\"ab\").insert(3, \"-\") //Exception");
//        System.out.println(new StringBuilder("ab").insert(3, "-")); //Exception

        System.out.println("\n StringBuilder - Delete");
        System.out.println("new StringBuilder(\"ab\").delete(0, 0) - " + new StringBuilder("ab").delete(0, 0));
        System.out.println("new StringBuilder(\"ab\").delete(0, 1) - " + new StringBuilder("ab").delete(0, 1));
        System.out.println("new StringBuilder(\"ab\").delete(1, 5) - " + new StringBuilder("ab").delete(1, 5));
        System.out.println("new StringBuilder(\"ab\").delete(3,4)) //Exception");
        System.out.println("new StringBuilder(\"ab\").deleteCharAt(5)) //Exception");
//        System.out.println(new StringBuilder("ab").delete(3,4)); //Exception
//        System.out.println(new StringBuilder("ab").deleteCharAt(5)); //Exception
    }

    private static void stringBuilder() {
        System.out.println("\n StringBuilder");

        System.out.print("""
                StringBuilder a = new StringBuilder();
                a.append("a").append("b");
                StringBuilder b = a.append("cd");
                b.append("e");
                """
        );
        StringBuilder a = new StringBuilder();
        a.append("a").append("b");
        StringBuilder b = a.append("cd");
        b.append("e");
        System.out.println("System.out.println(a); //" + a); //abcde
        System.out.println("System.out.println(b); //" + b); //abcde
        //Only one StringBuilder was created.

        System.out.println(
                """
                        new StringBuilder().appendCodePoint(87).append(",")
                                        .append((char) 87).append(",")
                                        .append(87).append(",")
                                        .appendCodePoint(222214))
                        """ +
                        new StringBuilder().appendCodePoint(87).append(",")
                                .append((char) 87).append(",")
                                .append(87).append(",")
                                .appendCodePoint(222214));
    }

    private static void formatAndFormatted() {
        System.out.println("\nFORMAT and FORMATTED");
        System.out.println("%s applys to any type, commonly String values");
        System.out.println("%d applies to integer values like int and long");
        System.out.println("%f applies to floating-point values like float and double");
        System.out.println("%n inserts a line break using the system-dependent line separator");
        System.out.println("\"%s - %d - %f - %n end\".formatted(\"a\", 1, 2.0) - " + "%s - %d - %f - %n end".formatted("a", 1, 2.0));
        System.out.println("String.format(\"%s - %d - %f - %n end\", \"a\", 1, 2.0) - " + String.format("%s - %d - %f - %n end", "a", 1, 2.0));
        System.out.print("System.out.printf(\"%s - %d - %2.2f - %n end%n\", \"a\", 1, 2.0) - ");
        System.out.printf("%s - %d - %2.2f - %n end%n", "a", 1, 2.0);
        System.out.println();
        System.out.println("""
                System.out.printf("%s - %d - %f - %n end%n", "a", 1, "a") //IllegalFormatConversionException);
                """);
    }

    private static void indent() {
        System.out.println("\nINDENT");
        System.out.print("ident() adds a break line at the end if it does not exist");
        System.out.print("\"|teste|\\n\".indent(-1) - " + "|teste|\n".indent(-1));
        System.out.print("\"|teste|\\n\".indent(0) - " + "|teste|\n".indent(0));
        System.out.print("\" |teste|\\n\".indent(5) - " + " |teste|\n".indent(5));
        System.out.print("\"|teste|\".indent(5) - " + "|teste|".indent(5));
        System.out.println("\"teste\".indent(5).indent(-6) - Compile error");
        System.out.print("\"|teste|\".indent(5).indent(-3) - " + "|teste|".indent(5).indent(-3));
        System.out.print("\"| teste |\".indent(-3) - " + "| teste |".indent(-3));


        var textBlock = """
                a
                 b
                c""";
        var concat = " a\n" + "  b\n" + " c";
        System.out.println("\nIdent text block : " + textBlock);
        System.out.println("\nConcat variable : " + concat);
        System.out.println("textBlock.length() - " + textBlock.length());
        System.out.print("textBlock.indent(1).length() - " + textBlock.indent(1).length()); //Add one extra space for each line (3) + /n at the end
        System.out.println(" //Result is 10, because it adds one extra space for each line (3) + /n at the end");
        System.out.println("concat.length() - " + concat.length());
        System.out.print("concat.indent(-1).length() - " + concat.indent(-1).length()); //Remove spacer from a,b and c and add /n at the end
        System.out.println(" //Remove spacer from a,b and c and add /n at the end");
    }

    private static void trimAndStrip() {
        System.out.println("\nTRIM, STRIP");
        System.out.println("STRIP does everything trim does, and also supports Unicode");
        System.out.println("STRIP and trim removes extra space, and break line");
        System.out.println("\" animal \\n\\t\\r\"\".trim() //" + (" animal \n\t\r".trim()));
        System.out.println("\" animal \\n\\t\\r\"\".strip() //" + (" animal \n\t\r".strip()));
        System.out.println("\" animal \\u2000\"\".trim() //" + (" animal \u2000".trim()));
        System.out.println("\" animal \\u2000\"\".strip() //" + (" animal \u2000".strip()));

        System.out.println("\"  animal  \".stripLeading() //" + "  animal  ".stripLeading());
        System.out.println("\"\\t animal \\t\".stripLeading() //" + "\t animal \t".stripLeading());
        System.out.println("\"  animal  \".stripTrailing() //" + "  animal  ".stripTrailing());
        System.out.println("\"\\t animal \\t\".stripTrailing() //" + "\t animal \t".stripTrailing());
    }

    private static void replace() {
        System.out.println("\nREPLACE");
        System.out.println("\"animal\".replace(\"a\", \"b\") //" + "animal".replace("a", "b"));
    }

    private static void startEndsWithAndContains() {
        System.out.println("\nStartsWith, endsWith, contains");
        System.out.println("\"animal\".startsWith(\"a\") //" + "animal".startsWith("a"));
        System.out.println("\"animal\".startsWith(\"a\", 1) //" + "animal".startsWith("a", 1));
        System.out.println("\"animal\".endsWith(\"a\") //" + "animal".endsWith("a"));
        System.out.println("\"animal\".contains(\"a\") //" + "animal".contains("a"));
        System.out.println("\"animal\".contains(\"A\") //" + "animal".contains("A"));
    }

    private static void indexOf() {
        System.out.println("\nINDEXOF");
        System.out.println("\"animal\".indexOf(\"a\") //" + "animal".indexOf("a"));
        System.out.println("\"animal\".indexOf(\"al\") //" + "animal".indexOf("al"));
        System.out.println("\"animal\".indexOf(\"al\", 4) //" + "animal".indexOf("al", 4));
        System.out.println("\"animal\".indexOf(\"a\", 2,4) //" + "animal".indexOf("a", 2, 4));
        System.out.println("\"animal\".indexOf(\"a\", 2,6) //" + "animal".indexOf("a", 2, 6));
        System.out.println("\"animal\".indexOf(\"ni\", 2,3) //" + "animal".indexOf("ni", 2, 3));
        System.out.println("\"animal\".indexOf(\"ni\", 1,3) //" + "animal".indexOf("ni", 1, 3));
        System.out.println("\"animal\".indexOf(\"ni\", 1,2) //" + "animal".indexOf("ni", 1, 2));
    }

    private static void substring() {
        System.out.println("\nSUBSTRING");
        System.out.println("\"testea\".substring(0, 2) //" + "testea".substring(0, 2)); //te
        System.out.println("\"testea\".substring(2) //" + "testea".substring(2)); //stea
        System.out.println("\"testea\".substring(3,3) //" + "testea".substring(3, 3)); //
        System.out.println("\"testea\".substring(3,6) //" + "testea".substring(3, 6)); //
        System.out.println("\"testea\".substring(3,7) // throw exception");
        System.out.println("\"testea\".substring(3,2) // throw exception");
    }

    private static void codePoint() {
        System.out.println("\nCODE POINT");
        System.out.println("\"a\".codePointAt(0) //" + "a".codePointAt(0)); //97
        System.out.println("\"a\".codePointAt(1) // throw exception");
        System.out.println("\"a\".codePointBefore(1) //" + "a".codePointBefore(1)); //97
        System.out.println("\"a\".codePointBefore(0) //throw exception");
        System.out.println("\"a\".codePointCount(0,1) //" + "a".codePointCount(0, 1)); //1
    }

    private static void chartAt() {
        System.out.println("\nCHART AT");
        System.out.println("\"ab\".charAt(0) //" + "ab".charAt(0)); //a
        System.out.println("\"ab\".charAt(2) //StringIndexOutOfBoundsException");
//        System.out.println("ab".charAt(2)); //StringIndexOutOfBoundsException
    }

    private static void stringConcatenation() {
        System.out.println("\nString concatenation");

        System.out.println("1 + 2 + \"c\" //" + (1 + 2 + "c")); //3c
        System.out.println("\"c\" + 1 + 2 //" + ("c" + 1 + 2)); //c12
        System.out.println("\"c\" + 1 * 2 //" + ("c" + 1 * 2)); //c2
//        System.out.println("c" + 1 - 2); //DOES NOT COMPILE
    }
}
