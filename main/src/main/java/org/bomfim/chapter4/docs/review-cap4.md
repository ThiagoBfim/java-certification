To review

2
3

+1 daylight saving - March
-1 november 


6 - Tricky on line 24
int two = Math.round(1.0f); //COMPILE
long two2 = Math.round(1.0); //COMPILE
24,25

8
    12345
    1645
    165
    145

Review - 9

10 - 24
11 - Tricky, take note


15 -Numbers sort before letters, and uppercase sorts before lowercase


Review 18

19

diff = 2
hour = 3
offset = false


### Arrays

    int[][] types = new int[]; //DOES NOT COMPILE
    int[][] java = new int[][]; //DOES NOT COMPILE
    int[][] scores = new int[5][]; //COMPILE


    var arr = new String[] { "PIG", "pig", "123"};
    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr)); //123, PIG, pig
    System.out.println(Arrays.binarySearch(arr, "Pippa")); //-3 Negative +1 because it does not exist
    System.out.println(Arrays.binarySearch(arr, "11")); //-1
    System.out.println(Arrays.binarySearch(arr, "PIG")); //1

> Numbers sort before letters, and uppercase sorts before lowercase

    String[] s1 = { "Camel", "Peacock", "Llama"};
    String[] s2 = { "Camel", "Llama", "Peacock"};
    String[] s3 = { "Camel"};
    String[] s4 = { "Camel", null};
    System.out.println(Arrays.compare(s1, s2)); //P-L = 4
    System.out.println(Arrays.compare(s3, s4)); //-1
    System.out.println(Arrays.compare(s4, s3)); //1
    System.out.println(Arrays.compare(s4, s4)); //0
    System.out.println(Arrays.mismatch(s1, s2)); //1
    System.out.println(Arrays.mismatch(s3, s4)); //1
    System.out.println(Arrays.mismatch(s4, s4)); //-1

Comparing array it compares each element in the order and return if it is -1 or 1

### Date Time

> BE CAREFUL, DATE DOES SUPPORT 0 until day 07

    LocalDate.of(2025, Month.MARCH, 07); //COMPILE. Day 07
    LocalDate.of(2025, Month.MARCH, 08); //DOES NOT COMPILE. Day 08
    LocalDate.of(2025, Month.MARCH, 8); //COMPILE

BE CAREFUL with DateTime with Date variable


    1. var date = LocalDate.of(2025, 4, 3);
    2. date.plusDays(2);
    3. date.plusHours(3); //DOES NOT COMPILE, LocalTime vs LocalDate
    4. System.out.println(date.getYear() + " " + date.getMonth() + " " + date.getDayOfMonth());


    var result = LocalDate.of(2025, Month.OCTOBER, 31)
        .plusYears(1)
        .plusMonths(-5)
        .plusMonths(1)
        .withYear(2026)
        .atTime(LocalTime.of(13, 4));
    System.out.println(result); //2026-06-30T13:04

#### Summer Time

    LocalDate.of(2025, 03, 9).atTime(1, 30, 0).atZone(ZoneId.of("US/Eastern")); // 2025-03-09T01:30-05:00[US/Eastern]
    LocalDate.of(2025, 03, 9).atTime(1, 30, 0).plusHours(1).atZone(ZoneId.of("US/Eastern")); // 2025-03-09T03:30-04:00[US/Eastern]
    LocalDate.of(2025, 03, 9).atTime(1, 30, 0).atZone(ZoneId.of("US/Eastern")).plusHours(1); // 2025-03-09T03:30-04:00[US/Eastern]
    LocalDate.of(2025, 11, 2).atTime(1, 30, 0).atZone(ZoneId.of("US/Eastern")); // 2025-11-02T01:30-04:00[US/Eastern]
    LocalDate.of(2025, 11, 2).atTime(1, 30, 0).atZone(ZoneId.of("US/Eastern")).plusHours(1); // 2025-11-02T01:30-05:00[US/Eastern]
    LocalDate.of(2025, 11, 2).atTime(1, 30, 0).plusHours(1).atZone(ZoneId.of("US/Eastern")); // 2025-11-02T02:30-05:00[US/Eastern]

March +1
November -1

ChronoUnit.Hours.between(date1, date2); Calculates the difference between applying Zone Time conversion

    var date = LocalDate.of(2028, Month.MARCH, 12);
    var time = LocalTime.of(1, 30);
    var zone = ZoneId.of("US/Eastern");
    var dateTime1 = ZonedDateTime.of(date, time, zone);
    var dateTime2 = dateTime1.plus(1, ChronoUnit.HOURS);

    System.out.println(dateTime1); //2028-03-12T01:30-05:00[US/Eastern]
    System.out.println(dateTime2); //2028-03-12T03:30-04:00[US/Eastern]
    long diff = ChronoUnit.HOURS.between(dateTime1, dateTime2); //1
    int diffHours = dateTime1.getHour() - dateTime2.getHour(); //2


### Math

    int one1 = Math.min(5, 3);
    long one1 = Math.min(5L, 3);
    double two1 = Math.min(5L, 3.0);
    int two = Math.round(1.0f); 
    int two1 = Math.round(1.0); //DOES NOT COMPILE
    long two2 = Math.round(1.0); 
    double one = Math.pow(1, 2);
    float three = Math.random(); //DOES NOT COMPILE
    int random = Math.random();  //DOES NOT COMPILE
    long random = Math.random(); //DOES NOT COMPILE
    double random = Math.random();

### String

        var builder = new StringBuilder("12345");
        builder.replace(2, 5, "6"); //126
        var x = builder.replace(2, 4, "6"); //1265
        x.charAt(3); //5

Result:

* Correct: 12
* Mistake: 10
* Score: 54,5%
* Passing Score: 68%
* Took: 34min