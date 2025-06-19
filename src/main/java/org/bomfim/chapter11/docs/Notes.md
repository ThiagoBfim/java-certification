# Chapter 11

* Handling Exceptions
* Implementing Localization
* Datetime, currency
* ResourceBundle
* Properties

## Topics

## Exceptions

### Checked Exception

* A checked exception is an exception that must be declared or handled by the application code where it is thrown.
* In Java, checked exceptions all inherit Exception but not RuntimeException

Checked Exception
* FileNotFoundException
* IOException
* NotSerializableException
* ParseException

You need to know that FileNotFoundException and NotSerializableException are subclasses of IOException


### Unchecked Exception

* An unchecked exception is any exception that does not need to be declared or handled by the application code where it is thrown.
* In Java, unchecked exceptions all inherit RuntimeException or Error.

### Error and Throwable

* Error means something went so horribly wrong that your program should not attempt to recover from it. For example, the disk drive “disappeared” or the program ran out of memory.
* For the exam, the only thing you need to know about Throwable is that it’s the parent class of all exceptions, including the Error class.

Error
* ExceptionInInitializerError
* StackOverflowError
* NoClassDefFoundError

### Suppressed exceptions

This kind of exception happens when the close method throws an exception 

        public static class TurkeyCage implements AutoCloseable {
            public void close() {
                throw new IllegalStateException("Turkeys ran off");
            }
        }

        try (TurkeyCage t = new TurkeyCage()) {
            throw new IllegalStateException("Turkeys ran off internal");
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed())
                System.out.println("Suppressed: " + t.getMessage());
        } //Caught: Turkeys ran off internal Suppressed: Turkeys ran off

Note: In case the close method throws a checked exception, it should be handled by the try block.

Note2: In case an exception is thrown by the finally block, it suppresses the exception from the try and throws the exception from the finally block. 

### Careful

**throw vs throws**

    Be careful throw vs throws
    void myMethod(String x) throw RuntimeException {} //DOES NOT COMPILE throw
    throws new RuntimeException(); //DOES NOT COMPILE throws
    throw RuntimeException(); //DOES NOT COMPILE missing the world new
    throw new RuntimeException(); //COMPILE

**handling checked exceptions**

    Be careful with handling checked exceptions
    When you see a checked exception declared inside a catch block on the exam,
    make sure the code in the associated try block is capable of throwing the exception or a subclass
    of the exception. If not, the code is unreachable and does not compile.
    Remember that this rule does not extend to unchecked exceptions or exceptions declared in a method signature.

**NumberFormatException is a subclass of IllegalArgumentException**

    IllegalArgumentException
    Thrown by programmer to indicate that method has been passed illegal or inappropriate argument.
    For the exam, you need to know that NumberFormatException is a subclass of IllegalArgumentException

    try {  } catch (IllegalArgumentException e){  } catch (NumberFormatException e){ // DOES NOT COMPILE  }


**Be careful with variables**

    try {  }
    catch (NumberFormatException e1) {
    System.out.println(e1);
    }
    catch (IllegalArgumentException e2) {
    System.out.println(e1); // DOES NOT COMPILE  variable from previous catch block
    }

**Try-with-resources**

        try (FileInputStream is = new FileInputStream("myfile.txt");
            var out = FileOutputStream.create(new File("myfile.txt"));) {
            // Read file data 
        } catch (IOException e) {
            e.printStackTrace();
        }

        NOTE: The out is closed first. The resources are closed in reverse order.
        Try-with-resources does not requires catch or finally blocks.
        Only objects that implements AutoCloseable or Closeable can use the try-with-resources.

**Be careful with elements that is used after the try-with-resources.**

```
var writer = Files.newBufferedWriter("path");
writer.append("This write is permitted but a really bad idea!");
try (writer){
    writer.append("Welcome to the zoo!");
   } catch (IOException e) {
    throw new RuntimeException(e);
}
writer.append("This write will fail!"); // IOException

```

## Format

### TABLE 11.6 Common date/time symbols

| Symbol | Meaning          | Examples                   |
|:-------|:-----------------|:---------------------------|
| `y`    | Year             | 25, 2025                   |
| `M`    | Month            | 1, 01, Jan, January        |
| `d`    | Day              | 5, 05                      |
| `H`    | 24 Hour          | 15                         |
| `h`    | 12 Hour          | 9, 09                      |
| `m`    | Minute           | 45                         |
| `s`    | Second           | 52                         |
| `a`    | a.m./p.m.        | AM, PM                     |
| `z`    | Time zone name   | Eastern Standard Time, EST |
| `Z`    | Time zone offset | -0400                      |


### TABLE 11.7 Supported date/time symbols

| Symbol | LocalDate | LocalTime | LocalDateTime | ZonedDateTime |
|:-------|:----------|:----------|:--------------|:--------------|
| `y`    | √         |           | √             | √             |
| `M`    | √         |           | √             | √             |
| `d`    | √         |           | √             | √             |
| `h`    |           | √         | √             | √             |
| `m`    |           | √         | √             | √             |
| `s`    |           | √         | √             | √             |
| `a`    |           | √         | √             | √             |
| `z`    |           |           |               | √             |
| `Z`    |           |           |               | √             |

#### TABLE 11.8 Factory methods to get a NumberFormat

| Description                             | Using default Locale                      | Using a specified Locale                                                                |
|:----------------------------------------|:------------------------------------------|:----------------------------------------------------------------------------------------|
| General-purpose formatter               | `NumberFormat.getInstance()`              | `NumberFormat.getInstance(Locale locale)`                                               |
| Same as getInstance                     | `NumberFormat.getNumberInstance()`        | `NumberFormat.getNumberInstance(Locale locale)`                                         |
| For formatting monetary amounts         | `NumberFormat.getCurrencyInstance()`      | `NumberFormat.getCurrencyInstance(Locale locale)`                                       |
| For formatting percentages              | `NumberFormat.getPercentInstance()`       | `NumberFormat.getPercentInstance(Locale locale)`                                        |
| Rounds decimal values before displaying | `NumberFormat.getIntegerInstance()`       | `NumberFormat.getIntegerInstance(Locale locale)`                                        |
| Returns compact number formatter        | `NumberFormat.getCompactNumberInstance()` | `NumberFormat.getCompactNumberInstance( Locale locale, NumberFormat.Style formatStyle)` |

### Be careful

**DecimalFormat**

Symbol # omit position if no digit exists for it
Symbom 0 put 0 in position if no digit exists for it.

    
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

**Formatting date**

Be careful, date time format with "z" must use a ZoneDateTime

    LocalDateTime dateTime = LocalDateTime.of(2025, 06, 01, 01, 01, 01);
    System.out.println(dateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"))); //01:01:01
    //  System.out.println(dateTime.format(DateTimeFormatter.ofPattern("hh:mm:z"))); //DateTimeException
    //  System.out.println(dateTime.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("hh:mm:z"))); //DateTimeException
    ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.of("UTC"));
    System.out.println(zonedDateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss:z"))); //01:01:01:UTC

Be careful, both works

     System.out.println(DateTimeFormatter.ofPattern("hh:mm:ss").format(LocalTime.now())); //03:54:51
     System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))); //03:54:51


Be careful to print date with text.

    System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm"))); // June 01, 2025 at 01:01
    
    If we want to print a single quotes (') we need to use it inside two quotes ' ' '; 
    System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm"))); //June 01, Party's at 01:01
    
    Note: If we have one quote it is ignored
    If we have two quotes it prints the text inside it.
    If we have three quotes it throws an exception.
    System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' ' hh:mm"))); // IllegalArgumentException: Pattern ends with an incomplete string literal: MMMM dd, yyyy 'at' ' hh:mm
    This works:         System.out.println(dateTime.format(DateTimeFormatter.ofPattern("'at'"))); //at

## Locale

Locale class is in the java.util package

    java.util.Locale locale = java.util.Locale.US; //en_US
    System.out.println(Locale.of("ABCD")); //abcd
    System.out.println(Locale.of("ABCD_ef")); //abcd_ef

Localizing Numbers

        int money = 1_000_000 / 50;
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(money)); //$20,000.00
        System.out.println(NumberFormat.getInstance(Locale.US).format(money)); //20,000
        System.out.println(NumberFormat.getCurrencyInstance(Locale.GERMAN).format(money)); //20.000,00 ¤
        System.out.println(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(money)); //20.000,00 €
        System.out.println(NumberFormat.getInstance(Locale.GERMAN).format(money)); //20.000

CompactNumberFormat

CompactNumberFormat is similar to DecimalFormat, but it is designed to be used in places where print space may be limited.
CompactNumberFormat round up the value and prints ont the first digit.
Example:

    int x = 29_874
    System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).format(29_874)); //30K

The following summarizes the rules for CompactNumberFormat: 
 * First it determines the highest range for the number, such as thousand (K), million (M), billion (B), or trillion (T). 
 * It then returns up to the first three digits of that range, rounding the last digit as needed.
 * Finally, it prints an identifier. If SHORT is used, a symbol is returned. If LONG is used, a space followed by a word is returned.


### Be careful

NumberFormat.getCurrencyInstance throws checked exception, it should be handled

        try{
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).parse("$40.45")); //40.45
        System.out.println(NumberFormat.getCurrencyInstance(Locale.GERMANY).parse("$40.45")); // java.text.ParseException: Unparseable number: "$40.45"
        } catch (ParseException e) {}


NumberFormat.parse throws ParseException

        try {
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1000")); //1000
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1K")); //1000
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1k")); //1
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1a")); //1
            System.out.println(NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).parse("1.000")); //1
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Note: NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT).format(29_874) does not throws checked exception

For the exam, you should be familiar with the two enum

* DISPLAY: used for displaying data about locale
* FORMAT: used for formatting dates, numbers or currencies


    Locale.setDefault(Locale.US); //Configures the display and format to Locale.US
    Locale.setDefault(Locale.Category.DISPLAY,Locale.US); //Configures only the display
    Locale.setDefault(Locale.Category.FORMAT,Locale.US); //Configures only the formatter

    System.out.println(NumberFormat.getCurrencyInstance().format(1.99)); //€1.99
    System.out.println(Locale.of("es", "ES").getDisplayLanguage()); //Spanish
    System.out.println(Locale.of("en", "US").getDisplayLanguage()); //English
    Locale.setDefault(Locale.Category.DISPLAY, Locale.of("es", "ES"));
    System.out.println(Locale.of("es", "ES").getDisplayLanguage()); //español
    System.out.println(NumberFormat.getCurrencyInstance().format(1.99)); //€1.99
    Locale.setDefault(Locale.Category.FORMAT, Locale.of("es", "ES"));
    System.out.println(Locale.of("es", "ES").getDisplayLanguage()); //español
    System.out.println(NumberFormat.getCurrencyInstance().format(1.99)); //1,99 €

## Properties and ResourceBundle

The order is to get the file more specific and then the others.
Example:
1. Zoo_fr_FR.properties //Requested locale
2. Zoo_fr.properties //Language we requested with no country
3. Zoo.properties //default bundle

**BE CAREFUL: the order above is in case there is a match with the requested locale.**

In case there is not match, the ResourceBundle will try to get the config file following this order:

1. Zoo_en_US.properties //default locale
2. Zoo_en.properties //default locale's language with no country
3. Zoo.properties //default bundle
//Exception if not found

> Once a resource bundle has been selected, only properties along a single hierarchy will be used


## Tricks


## Review Questions Notes

