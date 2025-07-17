# Review

## Cap 01

### Var

**var is only allowed as a type for local variable.**

    var x = (Integer i) -> {return i == 5;}; //does not compile, Functional Interface cannot be associated with var

```java
    public class Var {
        var var = 3; //DOES NOT COMPILE - instance variable
        Var case = new Var(); //DOES NOT COMPILE - variable with special word "case"   
        void var() {}
        int Var() { var _ = 7; return _;}  //DOES NOT COMPILE  - variable with special word "_"   
        String new = "var";  //DOES NOT COMPILE - variable with special word "new"   
        var var() { return null; } //DOES NOT COMPILE  - return cannot be var
        void method(){
            int a, var b = 3; //DOES NOT COMPILE
            int a, int b = 3; //DOES NOT COMPILE
            int a, b = 3;//DOES COMPILE
            var a, b = 3;//DOES COMPILE
            var a = (Integer) null;
            var x = () -> new Var(); //DOES NOT COMPILE - var cannot be used with Functional Interface
        }
    }
```
```java
var evening = 2; evening = null; //DOES NOT COMPILE -  evening is int
var morning = ""; morning = null;
```

### Default

* An instance variable of type float defaults to 0.0.
* A local variable does not have a default;

      public static float code; //default to 0.0
      public float code2; //default to 0.0
      public final float code3; //compile error must be initialized

### Constructor

BE CAREFUL WITH FALSE CONSTRUCTOR

```java
    public class Box {
    
        static {System.out.println("Static block");}
        {System.out.println("Block");}
        public void Box() { //fake constructor
            System.out.println("OK");
        }

        public Box(){
          System.out.println("CONSTRUCTOR");
        }
    
        public static void main(String[] args) {
            System.out.println("Before");
            Box box = new Box();
            System.out.println("After");
        }
    }
```

The output for this class is:

    Static block
    Before
    CONSTRUCTOR
    Block
    After

* First, static statements/blocks are called IN THE ORDER they are defined.
* Next, instance initializer statements/blocks are called IN THE ORDER they are defined. 
* Finally, the constructor is called.

### Variables

```java
    var x = 3_1;
    var x5 = 9___6;
    var x3 = 2_234.0_0;
    var x4 = 2_234_.0_0; //DOES NOT COMPILE -> _.
    var x2 = 3_13.0_; //DOES NOT COMPILE -> ends with _
    var x6 = _1_3_5_0; //DOES NOT COMPILE -> start with _
    
    short numPets = 5L; //DOES NOT COMPILE 
    int numGrains = 2.0; //DOES NOT COMPILE

    int amount = 0xE;
    int amount = 0b101;
    int a4 = 9_2.1_2; //does not compile
    double a5 = 9_2.1_2;
```

### Garbage Collector

FALSE: Garbage collection runs on a set schedule.

### Imports

If we have a class that requires the import, the import will be the first specific import, or if there is an asterisk, it
will consider the package.

Example:

    import java.awt.*;
    import java.util.*;
    class TestList{
        List x; //DOES NOT COMPILE
    }

    import java.awt.*;
    import java.util.List;
    class TestList{
        List x; //COMPILE
    } 

    import java.util.List;
    class TestList{
        List x; //COMPILE
    }

    import java.util.*;
    class TestList{
        List x; //COMPILE
    }

## Cap 02

### Casting

```java
    int x2 = 5;
    x2 = x2 + x2 % -5;
    System.out.println(x2); //5;

    int monday = 3 + 2.0; //does not compile -> wrong casting
    int monday += 2.0; //does not compile  -> Monday has not been intialized
    int monday = 5;
    monday += 2.0;

    Long getValue() { return 2; //DOES NOT COMPILE }

    System.out.println(2+""); //Autoboxing  //If the other operand is a numeric primitive type, then it is first converted to a reference type using the boxing conversion and then the boxed reference is used to produce a String.
    Integer i = 10; //Autoboxing
```

```java
private boolean containsIce = Boolean.valueOf("1"); //false
private boolean containsIce2 = Boolean.valueOf("ab"); //false
```

```java
String x = "";
Object a = x;
String b = (String)a; //works
```

### Math

#### Division by zero

    System.out.println(1/0); //ArithmeticException: / by zero
    System.out.println(1/0.0); //Infinity

#### Pow

    System.out.println(Math.pow(Math.pow(2, 2) + Math.pow(2, 2), .5)); //2.82..
    System.out.println(Math.pow(Math.pow(2, 2) + Math.pow(2, 2), 0.5)); //2.82..
    System.out.println(Math.pow(Math.pow(2, 2) + Math.pow(2, 2), 1 / 2)); //1.0
    System.out.println(((double) 1 / 2) * (2 * 2)); //2.0
    System.out.println((1 / 2) * (2 * 2)); //0
    System.out.println((double) (1 / 2) * (2 * 2)); //0.0
    double one = Math.pow(1, 2);

#### Min    
    int one1 = Math.min(5, 3);
    long one1 = Math.min(5L, 3);
    double two1 = Math.min(5L, 3.0);

#### Round

    int two = Math.round(1.0f); 
    int two1 = Math.round(1.0); //DOES NOT COMPILE
    long two2 = Math.round(1.0);

#### Random

    float three = Math.random(); //DOES NOT COMPILE
    int random = Math.random();  //DOES NOT COMPILE
    long random = Math.random(); //DOES NOT COMPILE
    double random = Math.random();

## Cap 03

### Loop

```java
var x = 5;
while (false) { x=3; } //DOES NOT COMPILE
if(false){x=3;} //WORKS
if(true){x=3;}
```

if(false) works, although the body of the condition is unreachable, this is not an error because the JLS explicitly defines this as an exception to the rule. It allows this construct to support optimizations through the conditional compilation

## Cap 04

### Arrays

```java
//var ia[][] = { {1,2}, null}; //DOES NOT COMPILE
//var ia[][] = new int[][]{ {1,2}, null}; //DOES NOT COMPILE
var ia = new int[][]{ {1,2}, null};
String[ ] sa = new String[3]{ "a", "b", "c"}; //DOES NOT COMPILE
String[ ] sa = new String[]{ "a", "b", "c"};
String sa[ ] = { "a ", " b", "c"};
String sa = new String[ ]{"a", "b", "c"}; //DOES NOT COMPILE
```
```java
    int[] array = {6, 9, 8};
    System.out.println("B" + Arrays.binarySearch(array, 9)); //B-1 Array is not sorted
    System.out.println("C" + Arrays.compare(array, new int[] {6, 9, 8})); //C0
    System.out.println("M" + Arrays.mismatch(array, new int[] {6, 9, 8})); //M-1
    System.out.println("M" + Arrays.mismatch(array, new int[] {6, 6, 8})); //M 1
```

* Arrays Mismatch returns -1 if the arrays are equal
* Arrays Mismatch returns index of first mismatch otherwise.

```java
int[] i, j; //here i and j are both array of integers. 
int i[], j; //here only i is an array of integers. j is just an integer.
int[10] IA; //DOES NOT COMPILE
```

### String

```java
    var pooh = """
    "Oh, bother." -Pooh
    """.indent(1);
    System.out.print(pooh); //" \"Oh, bother.\" -Pooh\n"
```

```java
        System.out.println("12345".charAt(6)); //throw an IndexOutOfBoundsException
        "abc".lines(); //return a Stream<String> separated by line terminators
        "abc".isEmpty(); //false
        "abc".isBlank(); //false
```
> method length() of String class is a final method. String class itself is final and so all of its methods are implicitly final.


```java
        new StringBuilder("12345").replace(2, 7, "6"); //126
        new StringBuilder("12345").replace(2, 5, "6"); //126
        var x = new StringBuilder("12345").replace(2, 4, "6"); //1265
        x.charAt(3); //5
```

### StringBuilder

```java
        var fullPhoneNumber = "ddd-ddd-dddd"
        new StringBuilder(fullPhoneNumber).substring(0, 8)+"xxxx"; //ddd-ddd-xxxx
        return new StringBuilder("xxxx").insert(0, fullPhoneNumber, 0, 8).toString(); //ddd-ddd-xxxx
```
```java
    new StringBuilder().trim() //DOES NOT COMPILE - trim does not exist
    var abc = new StringBuilder("abc");
    abc.ensureCapacity(1);
    System.out.println(abc); //abc
    var abc2 = new StringBuilder("abc");
    abc2.setLength(1);
    System.out.println(abc2); //a
```
> StringBuilder is a final class.

### Date

Thread-safe:

* java.time.Instant
* java.time.Duration

Old Date: java.util.Date\
New Date: java.time.LocalDate

```java
        LocalDate plus = LocalDate.of(2025, Month.AUGUST, 4)
                .plus(Period.ofYears(-10).ofMonths(-1));
        System.out.println(plus); //2025-07-04
```

```java
    If we want to print a single quotes (') we need to use it inside two quotes ' ' ';
    System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm"))); //June 01, Party's at 01:01
    "'It''s 'h' hours past midnight, and 'mm' minutes'" //WORKS, must have two '' It's 4 hours past midnight, and 05 minutes 
    "'It's 'h' hours past midnight, and 'mm' minutes'" //DOES NOT WORK
```


> BE CAREFUL, DATE DOES SUPPORT 0 until day 07

    LocalDate.of(2025, Month.MARCH, 07); //COMPILE. Day 07
    LocalDate.of(2025, Month.MARCH, 08); //DOES NOT COMPILE. Day 08
    LocalDate.of(2025, Month.MARCH, 8); //COMPILE

BE CAREFUL with DateTime with Date variable


```java
    1. var date = LocalDate.of(2025, 4, 3);
    2. date.plusDays(2);
    3. date.plusHours(3); //DOES NOT COMPILE, LocalTime vs LocalDate
    4. System.out.println(date.getYear() + " " + date.getMonth() + " " + date.getDayOfMonth());
```

```java
    var result = LocalDate.of(2025, Month.OCTOBER, 31)
        .plusYears(1)
        .plusMonths(-5)
        .plusMonths(1)
        .withYear(2026)
        .atTime(LocalTime.of(13, 4));
    System.out.println(result); //2026-06-30T13:04
```

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
    long diff = ChronoUnit.HOURS.between(dateTime2, dateTime1); //-2
    int diffHours = dateTime1.getHour() - dateTime2.getHour(); //2

## Cap 05

## Cap 06

Works:
```java
    public static class A {
        public void foo() throws IOException {}
    }
    public static class B extends A {
        public void foo() {}
    }
```
## Cap 07

### enum

* Enum cannot have public constructor

```java
  public static void method(){
    enum Enum{A,B,C}; //WORKS
  }
```

```java
public class Test5 {

    static String prefix = "I am ";
    enum Pets {
//        static String prefix2 = "I am "; //DOES NOT COMPILE
        DOG("D"), CAT("C"), FISH("F");

        static String prefix2 = "I am ";
        String name;

        Pets(String s) {
            name = prefix + s;
//            name += prefix2 //DOES NOT COMPILE
}

        public String getData() {
            return name;
        }
    }
}
```

- Static varaible in enum cannot be used in the constructor;
- Any variable, including static must be before the enum values.


### Interfaces

> Interfaces allow multiple implementation inheritance through default methods

```java
    interface I1 {
        public default void m1() {
            System.out.println("in I1.m1");
        }
    }

    interface I2 {
        public default void m1() {
            System.out.println("in I2.m1");
        }
    }

    class CI implements I1, I2 { //This class will not compile.
    }

    //This class will compile because it provides its own implementation of m1. 
    class C2 implements I1, I2 {
        public void m1() {
            System.out.println("in C2.m1");
        }
    }
```

> All fields of an interface are public, static, and final. Therefore, volatile, transient, and synchronized do not make sense for such fields.

### Record

```java
    record Music(List<String> tempo) {
         final int score = 10; //DOES NOT COMPILE -> Must be static for records
    }
```
* Record cannot have an instance variable


### Sealed and Switch and Record

```java
record Music(List<String> tempo) { 
   final int score = 10; //DOES NOT COMPILE must be static
}
record Song(String lyrics, Music m) {
   Song {
      this.lyrics = lyrics + "Never gonna give you up"; //DOES NOT COMPILE cannot be initialized in this block
   } }
sealed class Dance {} //permits clause is optional for a sealed class if the associated classes are in the same file
record March() {
   int roll(Song s) {
      return switch (s) {
         case null -> 2;
         case Song(var q, Music(List d)) -> 1; 
         default -> 3;
      };
   }
}
sealed class Ballet extends Dance permits NewDance { //DOES NOT COMPILE - permits to a non sealed, sealed or final class
   Ballet { //DOES NOT COMPILE Missing Parentheses
      var d = LocalDate.of(2025, Month.OCTOBER, 20);
      if (d.isAfter(LocalDate.now()))
         System.out.print("say goodbye");
   }
}
abstract class NewDance extends Ballet {} //DOES NOT COMPILE - abstract class
```

> Sealed can be used with interface and abstract class

> Non-Sealed can be used with interface and abstract class

```java
sealed interface Member permits Person {
    String role();
}

 record Person(String name) implements Member{

     @Override
     public String role() {
         return "";
     }
 }
```

```java
    public record Journal(int id, String name) {
        //    public Journal(int id, String name) { //DOES NOT COMPILE -> two constructor
        //        this.id = id;
        //    }
      public Journal {
            id = id+1;
             System.out.println("this.name: " + this.name()); //null
             System.out.println("name: " + name); //abc
             System.out.println("id: " + id); //2
             //System.out.println(this.name); //DOES NOT COMPILE
      }
    }

    public record Journal2(int id, String name) {
        public Journal2(int id, String name) {
            this.id = id;
            this.name = name;
            System.out.println(name);
        }
    }

    Journal abc1 = new Journal(1, "abc");
    System.out.println("ID: " + abc1.id()); //2
```

Output: 
- this.name: null
- name: abc
- id: 2
- ID: 2

### Anonymous class

You cannot pass parameters when you implement an interface by an anonymous class.

    new ComplexInterface(x) { //DOES NOT COMPILE 
     // valid code 
    }

    new ComplexInterface() { //COMPILE 
     // valid code 
    }

## Cap 08

### Functional Interface

```java
        (i) -> i == 5
        (i) -> {return i == 5;}
        (Integer i) -> {return i == 5;}
        (int i) -> {return i == 5;} //DOES NOT COMPILE it cannot be primitive 
```

It takes int primitive as an argument. It can be parameterized to return any type. For example,
`IntFunction<String> f = x->""+x;` returns a String.

## Cap 09

### Collections

```java
    List students = new ArrayList();
    List list = new ArrayList();
    list.add(2, "a"); //java.lang.IndexOutOfBoundsException: Index: 2, Size: 0
    System.out.println(List.of("a", "b", "c").subList(2,3)); //c
    System.out.println(List.of("a", "b", "c").subList(2,4)); //java.lang.IndexOutOfBoundsException
```
The reference type is ArrayList and the object type is List

#### Map

`NavigableMap<K,V> tailMap(K fromKey, boolean inclusive)`
- Returns a view of the portion of this map whose keys are greater than (or equal to, if inclusive is true) fromKey.

```java
        NavigableMap<Integer, String> map = new TreeMap<>(Map.of(1, "A", 2, "B", 3, "C"));
        System.out.println(map.tailMap(1)); //{1=A, 2=B, 3=C}
        System.out.println(map.tailMap(1, false)); //{2=B, 3=C}
        NavigableMap<Integer, String> tailMap = map.tailMap(2, false);
        System.out.println(tailMap); //{3=C}
        System.out.println(map.size()); //3
        System.out.println(tailMap.pollFirstEntry()); //3=C
        System.out.println(map.size()); //2
```

### Generic

```java
    Set<? extends RuntimeException> mySet = new TreeSet<RuntimeException>();
    Set<? extends RuntimeException> mySet = new TreeSet<NullPointerException>();
    Set<? extends RuntimeException> mySet = new LinkedHashSet<>();
    Set<? extends RuntimeException> mySet = new LinkedHashSet<?>(); //DOES NOT COMPILE
    Set<? extends RuntimeException> mySet = new HashSet<Exception>(); //DOES NOT COMPILE

    HashSet<Number> hs = new HashSet<Integer>(); //DOES NOT COMPILE
    List<Exception> list = new LinkedList<java.io.IOException>() //DOES NOT COMPILE
    ArrayList<? super Date> list = new ArrayList<Date>() 
    ArrayList <? extends Number> list = new ArrayList <Integer>() 
    ArrayList<? extends Number> list = new ArrayList <Number>(); 
    ArrayList<? super Number> list = new ArrayList <Object>(); 

```

```java
   public class Ball<X> {
      public static <T> void catchBall(T t, X x) {} //DOES NOT COMPILE
      public <T> void dribbleBall(T t, X x) {}
      public <X> static void fetchBall(X t, X x) {} //DOES NOT COMPILE static must be before <X>
      public <X> void inflateBall(X t, X x) {}
      public <T> static void spinBall(T t, X x) {} //DOES NOT COMPILE - static must be before <X>
      public static <X> void throwBall(X t, X x) {}
      public <X> static void throwBall(X t, X x) {} //DOES NOT COMPILE - static must be before <X>
   }
```

```java
public List<? super Number> getList() {}

getList(new ArrayList<Number>());
getList(new ArrayList<Object>());
getList(new ArrayList<>());
getList(new ArrayList<Integer>()); //Does not compile
```

```java
    public record Hello<T>(T t) {
        public Hello(T t) {
            this.t = t;
        }
        private <T> void println(T message) {  //COMPILES
            System.out.print(t + "-" + message);
        }
        private static <T> void println2(T message) { //COMPILES
            System.out.print(message);
        }
    }
```
### Comparator

                public interface java.lang.Comparable<T> {
                  int compareTo(T o);
                }
                TreeSet and TreeMap classes have to implement the Comparable, or pass a Comparator to the constructor.
                public interface java.util.Comparator<T> {
                  int compare(T o1, T o2);  //0 is equal to / 1 is bigger then / -1 is smaller then
                }
                BE CAREFUL: Comparator method is compare()
                Comparable method is compareTo()

> The sort order is always first numbers, than UPPER case, than lower case for array


        Integer value = List.of(1,2,3).stream().max(Comparator.comparing(a->a)).get(); //3
        Integer value2 = List.of(1,2,3).stream().max(Comparator.comparing(Integer::intValue)).get(); //3
        Integer value3 = List.of(1,2,3).stream().max(Comparator.comparingInt(Integer::intValue)).get(); //3

Arrays.sort("A", 1, "2", "B", "b", "a"}); //java.lang.ClassCastException All the objects must be the same type
Arrays.sort("A", "1", "2", "B", "b", "a"}); //1,2,A,B,b,a

## Cap 10

## Cap 11

### Locale

    Locale(String language)  Construct a locale from a language code. 
    Locale(String language, String country)  Construct a locale from language, country. 
    Locale(String language, String country, String variant)  Construct a locale from language, country, variant.

    Locale.setDefault(Locale.Category category, Locale newLocale)
    Locale.setDefault(Locale newLocale)

## Cap 12

### Modules

Be careful with public module file `module-info.java`

```java
    public module com.vet {
        exports com.vet;
    }
```

* Does not works, module does not have the `public` access modifier



> Just add an empty module-info.java to the jar. - If you don't export a package then other modular jars cannot access classes from this jar.
> Every module must reside in a directory (or a jar) of its own.


----

* A **named** module always contains a module-info.java file, while an **automatic** module always exports all its packages to other modules.
* An unnamed module is on the classpath, while a named and automatic module is on the module path.


> If a package is defined in both a named module and the unnamed module then the package in the unnamed module is ignored.
> If a request is made from an automatic module to load a type whose package is not defined in any known module then the module system will attempt to load it from the classpath

#### Bottom-up vs top-down migration

While modularizing an app using the bottom-up approach, you need to convert lower level libraries i.e. dependencies into
modular jars before you can convert the higher level libraries. For example, if a class in A.jar directly uses a class
from B.jar, and a class in B.jar directly uses a class from C.jar, you need to first modularize C.jar and then B.jar
before you can modularize A.jar.

In bottom-up migration all classes and jars get moved to the module-path.

In top-down migration, classes that cannot be migrated or cannot be upgraded to automatic modules are left on the classpath and they become part of the unnamed module.
In top-down migration you start with the packages that you are most interested in and upgrade them as named modules,
without worrying about converting their dependencies to modules. You put the dependencies either on module-path (to make
them automatic modules) or on classpath (to make them part of the unnamed module).

classpath has no use in bottom-up migration because the lowest level of dependencies are migrated to modules first.
So, there are no non-modular classes/packages in this approach.

Remember that only packages on automatic modules can read packages present on the classpath.
If you migrate a package as a regular explicitly named module and put it on
module-path, it can no longer access classes on classpath.


#### Uses a service

```java

        java.util.ServiceLoader<Blogger> loader = java.util.ServiceLoader.load(Blogger.class);//may throw a java.util.ServiceConfigurationError
        
        try {
            Iterator<Blogger> bi = loader.iterator();
            while (bi.hasNext()) {
                Blogger b = bi.next();
                b.blog(data);
            }
        } catch (ServiceConfigurationError e) {
        }
        
        for (Blogger b : loader){ 
          b.blog("Hello from textbook"); 
        }

        Optional<Blogger> b1 = loader.findFirst(); 
        b1.ifPresent(b->b.method()); 
```

Print service interface defined in PrintServiceAPI module using com.abc.PrintImpl class, then this is how its module-info.java should look:
```
module abc.print {
  requires PrintServiceAPI; //required //because this module defines the service interface org.printing.Print provides
  org.printing.Print with com.abc.PrintImpl;
}
```


A module named customer that uses Print service may look like this:
```
module customer {
  requires PrintServiceAPI; //required //because this module defines the service interface org.printing.Print
  uses org.printing.Print; //specifies that this module uses this service //observe that abc.print module is not required.
}
```

> The foundational APIs of the Java SE platform are found in java.base module.

> The modular JDK is divided of two kinds of modules - the standard modules and the non-standard modules.

> JDK is divided into a set of modules that can be combined at compile time, build time, and run time into a variety of configurations.



## Cap 13

### Virtual Thread

- Creating millions of virtual threads is possible and even normal for a Java program.
- A Virtual thread executes code on top of a platform thread.

### Callable vs Runnable

* Callable - V call() throws Exception; 
* Runnable - void run();

      Thread.run() //Sync
      Thread.start() // Async

      Executors
         .newSingleThreadExecutor()
         .execute(Runnable)

```java

   Executor {
     void execute(Runnable command);
   }
   
   ExecutorSerivce {
     Future<?> submit(Runnable task);
     <T> Future<T> submit(Callable<T> task);
   }

```


```
  List.of(1).stream()
  List.of(1).parallelStream()
  List.of(1).stream().parallel();
```

```java
        try (ExecutorService executorService = Executors.newScheduledThreadPool(1)) {
            ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(() -> 10, 5, TimeUnit.MILLISECONDS); //DOES NOT COMPILE
        }
        
        try (ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1)) {
            ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(() -> 10, 5, TimeUnit.MILLISECONDS);
            System.out.println(schedule.get());
            scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Schedule2"), 5, 5, TimeUnit.MILLISECONDS);
            scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("Schedule3"), 5, 5, TimeUnit.MILLISECONDS);
            scheduledExecutorService.schedule(() -> System.out.println("Schedule1"), 1000, TimeUnit.MILLISECONDS);
            Thread.sleep(10);
            //scheduledExecutorService.scheduleWithFixedDelay = Creates and executes Runnable task after given initial delay and subsequently with given delay between termination of one execution and commencement of next
        }
```

### Thread

    Thread.interrupt();

InterruptedException will be thrown only if the interrupted thread is blocked in an invocation of the sleep, wait, 
or of the join methods. In the given code, calling a.interrupt() will just set the interrupted flag to true. 
But this is not checked by the A thread and so it will keep executing the while loop.

## Cap 14

### Try-with-resources

    Closeable extends AutoCloseable {}

    AutoClosable {
      public void close() throws IOException;
    }

> With Try-with-resources the closed method is called before the Catch block

```java
    public static void main(String[] args) {
        try(var x = new Closed()){
            throw new RuntimeException("a");
        }catch (Exception e){
            System.out.println("Error");
        }finally {
            System.out.println("finally");
        }
    }

    public static class Closed implements AutoCloseable {

        @Override
        public void close() throws Exception {
            System.out.println("close");
        }
    }
```

Output: 
- close
- Error
- finally

### Output and Input Stream

        var w = new StringBuilder();
        try(in;
            var o = new BufferedOutputStream(System.out)) {
            w.append((char) in.read());
            in.skip(1);
            in.read();
            in.skip(0);
            w.append((char)in.read());
            o.flush();
        }
        System.out.println(w); //System.out is closed, so it does not print anything.

### Console

> When the JVM is started from an interactive command line without redirecting the standard input and output streams

The declared type of System.in is java.io.InputStream. It refers to an object of type java.io.BufferedInputStream.

> The System.in variable cannot be reassigned to any other stream directly.

Although in is a final variable and so, it cannot be reassigned directly. However, System class has a public static void setIn(InputStream in) method that can be used to change in to refer to any other InputStream.

### Files

```java
Files.lines() returns Stream<String>
Files.readAllLines returns List<String>
```

```java
System.out.println(Files.mismatch(path1,path2)); 
```

* If the file does not exist, it throws an exception
* It returns the index of the last non-match value
* If the files match it returns -1

Thus, for example, If your Path is "c:\\code\\java\\PathTest.java",
* p1.getRoot() is c:\ ((For Unix based environments, the root is usually / ).
* p1.getName(0) is code
* p1.getName(1) is java
* p1.getName(2) is PathTest.java
* p1.getName(3) will cause IllegalArgumentException to be thrown.


#### Relativize

`Path.of("/test/schedule.xml").relativize(Path.of("/test/text.txt");`
1. Check if the both root or relative.
2. Remove the relative "./"
3. In case is the same bath, remove it.
4. Concat both schedule.xml/text.txt
5. Solve it to the last path. In this case ../text.txt

       Path.of("./testA/schedule.xml").relativize(Path.of("./testB/../text.txt");
       testA/schedule.xml/testB/../text.txt
       ../../testB/../text.txt

> The relativize() method requires both paths to be absolute or relative and throws an exception if the types are mixed.

#### Resolve

        Path path1 = Path.of("/cats/../panther");
        Path path2 = Path.of("food");
        System.out.println(path1.resolve(path2)); // -  /cats/../panther/food
        System.out.println(path1.resolve(path2).normalize()); // - /panther/food

In case both are absolute path the last still.

    Path path3 = Path.of("/turkey/food"); 
    System.out.println(path3.resolve("/tiger/cage")); // - /tiger/cage

#### Normalize


        Path path1 = Path.of("/cats/../panther");
        System.out.println(path1.normalize()); // - /panther

Be careful, normalize a path with only ../../file.txt does nothing.

        var p3 = Path.of("../../fish.txt");
        System.out.println(p3.normalize()); // ../../fish.txt


```java

var p1 = Path.of("/zoo/./bear","../food.txt");
p1.normalize().relativize(Path.of("/lion")); //DOES NOTHING
System.out.println(p1.normalize().relativize(Path.of("/lion"))); // ../../lion
System.out.println(p1); // /zoo/food.txt

var p2 = Path.of("/zoo/animals/bear/koala/food.txt");
System.out.println(p2.subpath(1,3).getName(1)); // bear
System.out.println(p2.subpath(0,2)); // zoo/animals

var p3 = Path.of("/pets/../cat.txt");
var p4 = Path.of("./dog.txt");
System.out.println(p4.resolve(p3)); //  /pets/../cat.txt

```

#### Walk vs Find

##### Walk
    public static Stream<Path> walk(Path start,  FileVisitOption… options) throws IOException
    public static Stream<Path> walk(Path start, int maxDepth,  FileVisitOption… options) throws IOException

Example:

```java
    public static long getPathSize(Path source) throws IOException {
        try (var s = Files.walk(source, 6)) {
      //      try (var s = Files.walk(source,     FileVisitOption.FOLLOW_LINKS))     
          return s.parallel().filter(p -> !Files.isDirectory(p)).mapToLong(Exercises::getSize).sum();
         }
    }
```

##### Find

**The find method works similar to the walk, but it contains a BiPredicate, it is more powerful**

      public static Stream<Path> find(Path start,  
        int maxDepth, 
        BiPredicate<Path, BasicFileAttributes> matcher, 
        FileVisitOption… options) throws IOException

Example:

```java
      try (var s = Files.find(path, 10,
          (p, a) -> a.isRegularFile() && p.toString().endsWith(".java")
          && a.size() > 1000)) {
          s.forEach(System.out::println);
      }
```

### Serialize


> The readObject method will be invoked before a Data object is deserialized

The readObject is private

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
