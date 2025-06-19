package org.bomfim.chapter11;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.stream.Stream;

public class Formatting {

    public static void main(String[] args) {

        decimalFormat();
        formattingDate();
        internalizationandLocale();
        compactNumberFormat();


    }

    private static void compactNumberFormat() {
        System.out.println("\nCompactNumberFormat");
        System.out.println("""
                CompactNumberFormat is similar to DecimalFormat, but it is designed to be used in places where print space may be limited.
                CompactNumberFormat round up the value and prints ont the first digit.
                Example: int x = 29_874
                System.out.println(
                     NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT)
                        .format(29_874)); //30K
                        
                The following summarizes the rules for CompactNumberFormat: 
                 * First it determines the highest range for the number, such as thousand (K), million (M), billion (B), or trillion (T). 
                 * It then returns up to the first three digits of that range, rounding the last digit as needed.
                 * Finally, it prints an identifier. If SHORT is used, a symbol is returned. If LONG is used, a space followed by a word is returned.
                """);

        var formatters = Stream.of(NumberFormat.getCompactNumberInstance(),
                NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT),
                NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.LONG));

        System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).format(29_874));
        formatters.map(f -> f.format(7_654_321))
                .forEach(System.out::println); //8M 8M 8 million

        try {
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1000")); //1000
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1K")); //1000
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1k")); //1
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1a")); //1
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1.000")); //1
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(dateTimeFormatter.withLocale(Locale.ITALY))); // 18/06/25

        System.out.println("""
                Be careful: For the exam, you should be familiar with the two enum
                DISPLAY: used for displaying data about locale
                FORMAT: used for formatting dates, numbers or currencies
                
                By default: 
                Locale.setDefault(Locale.US); //Configures the display and format to Locale.US
                Locale.setDefault(Locale.Category.DISPLAY,Locale.US); //Configures only the display
                Locale.setDefault(Locale.Category.FORMAT,Locale.US); //Configures only the formatter
                """);

        System.out.println(NumberFormat.getCurrencyInstance().format(1.99)); //€1.99
        System.out.println(Locale.of("es", "ES").getDisplayLanguage()); //Spanish
        System.out.println(Locale.of("en", "US").getDisplayLanguage()); //English
        Locale.setDefault(Locale.Category.DISPLAY, Locale.of("es", "ES"));
        System.out.println(Locale.of("es", "ES").getDisplayLanguage()); //español
        System.out.println(NumberFormat.getCurrencyInstance().format(1.99)); //€1.99
        Locale.setDefault(Locale.Category.FORMAT, Locale.of("es", "ES"));
        System.out.println(Locale.of("es", "ES").getDisplayLanguage()); //español
        System.out.println(NumberFormat.getCurrencyInstance().format(1.99)); //1,99 €
    }

    private static void internalizationandLocale() {
        System.out.println("\nInternalization and Locale");
        System.out.println("""
                Locale class is in the java.util package
                        java.util.Locale locale = java.util.Locale.US; //en_US
                
                """);
        Locale locale = Locale.US;
        System.out.println(locale); //en_US
        System.out.println(Locale.GERMAN); //de
        System.out.println(Locale.GERMANY); //de_DE
        System.out.println(Locale.of("ABCD")); //abcd
        System.out.println(Locale.of("ABCD_ef")); //abcd_ef
        System.out.println(Locale.of("xM")); //xm
        System.out.println(Locale.of("AB", "cd")); //ab_CD

        System.out.println("\nLocalizing Numbers");

        int money = 1_000_000 / 50;
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(money)); //$20,000.00
        System.out.println(NumberFormat.getInstance(Locale.US).format(money)); //20,000
        System.out.println(NumberFormat.getCurrencyInstance(Locale.GERMAN).format(money)); //20.000,00 ¤
        System.out.println(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(money)); //20.000,00 €
        System.out.println(NumberFormat.getInstance(Locale.GERMAN).format(money)); //20.000


        try {
            String s = "40.45A";
            var en = NumberFormat.getInstance(Locale.US);
            System.out.println(en.parse(s)); // 40.45
            var fr = NumberFormat.getInstance(Locale.FRANCE);
            System.out.println(fr.parse(s)); // 40
            System.out.println(NumberFormat.getCurrencyInstance(Locale.US).parse("$40.45")); //40.45
            System.out.println(NumberFormat.getCurrencyInstance(Locale.GERMANY).parse("$40.45")); // java.text.ParseException: Unparseable number: "$40.45"
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void formattingDate() {
        System.out.println("\nFormatting date");
        System.out.println("""
                | `y`    | Year             | 25, 2025                   |
                | `M`    | Month            | 1, 01, Jan, January, J     |
                | `d`    | Day              | 5, 05                      |
                | `H`    | 24 Hour          | 15                         |
                | `h`    | 12 Hour          | 9, 09                      |
                | `m`    | Minute           | 45                         |
                | `s`    | Second           | 52                         |
                | `a`    | a.m./p.m.        | AM, PM                     |
                | `z`    | Time zone name   | Eastern Standard Time, EST |
                | `Z`    | Time zone offset | -0400                      |
                """);

        LocalDate date = LocalDate.of(2025, 06, 01);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(date.format(formatter)); //01/06/2025
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMM"))); //Jun
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMMM"))); //June
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMMMM"))); //J
//        System.out.println(date.format( DateTimeFormatter.ofPattern("MMMMMM"))); //java.lang.IllegalArgumentException: Too many pattern letters: M
//        System.out.println(date.format( DateTimeFormatter.ofPattern("Mm"))); //Unsupported field: MinuteOfHour
//        System.out.println(date.format(DateTimeFormatter.ISO_DATE_TIME)); //UnsupportedTemporalTypeException: Unsupported field: HourOfDay
        System.out.println(date.format(DateTimeFormatter.ISO_DATE)); //2025-06-01

        System.out.println("""
                Be careful, date time format with "z" must use a ZoneDateTime
                LocalDateTime dateTime = LocalDateTime.of(2025, 06, 01, 01, 01, 01);
                System.out.println(dateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"))); //01:01:01
                //  System.out.println(dateTime.format(DateTimeFormatter.ofPattern("hh:mm:z"))); //DateTimeException
                //  System.out.println(dateTime.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("hh:mm:z"))); //DateTimeException
                ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.of("UTC"));
                System.out.println(zonedDateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss:z"))); //01:01:01:UTC
                """);
        LocalDateTime dateTime = LocalDateTime.of(2025, 06, 01, 01, 01, 01);
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"))); //01:01:01
//        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("hh:mm:z"))); //DateTimeException
//        System.out.println(dateTime.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("hh:mm:z"))); //DateTimeException
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.of("UTC"));
        System.out.println(zonedDateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss:z"))); //01:01:01:UTC

        System.out.println(DateTimeFormatter.ofPattern("hh:mm:ss").format(LocalTime.now())); //03:54:51
        System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))); //03:54:51
        System.out.println("""
                Be careful to print date with text.
                
                System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm"))); // June 01, 2025 at 01:01
                
                If we want to print a single quotes (') we need to use it inside two quotes ' ' '; 
                System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm"))); //June 01, Party's at 01:01
                
                Note: If we have one quote it is ignored
                If we have two quotes it prints the text inside it.
                If we have three quotes it throws an exception.Example:
                System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' ' hh:mm"))); // IllegalArgumentException: Pattern ends with an incomplete string literal: MMMM dd, yyyy 'at' ' hh:mm
                
                
                This works:         System.out.println(dateTime.format(DateTimeFormatter.ofPattern("'at'"))); //at
                """);
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("'at'"))); //at
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm"))); // June 01, 2025 at 01:01
//        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' ' hh:mm"))); // IllegalArgumentException: Pattern ends with an incomplete string literal: MMMM dd, yyyy 'at' ' hh:mm
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm"))); //June 01, Party's at 01:01
    }

    private static void decimalFormat() {
        System.out.println("\nDecimalFormat");
        System.out.println("""
                * Symbol # omit position if no digit exists for it
                * Symbom 0 put 0 in position if no digit exists for it.
                """);
        DecimalFormat df = new DecimalFormat("##.##");
        System.out.println(df.format(1.0)); //1
        System.out.println(df.format(10.0)); //10
        System.out.println(df.format(10.5)); //10.5
        System.out.println(df.format(9999.99)); //9999.99
        DecimalFormat df2 = new DecimalFormat("00.00");
        System.out.println(df2.format(1.0)); //01.00
        System.out.println(df2.format(10.0)); //10.00
        System.out.println(df2.format(10.5)); //10.50
        System.out.println(df2.format(9999.99)); //9999.99
    }
}
