## Wiley - Test 1

1. E - out is closed

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

2. Review

```java
   public class Ball˂X˃ {
      public static ˂T˃ void catchBall(T t, X x) {} //DOES NOT COMPILE
      public ˂T˃ void dribbleBall(T t, X x) {}
      public ˂X˃ static void fetchBall(X t, X x) {} //DOES NOT COMPILE
      public ˂X˃ void inflateBall(X t, X x) {}
      public ˂T˃ static void spinBall(T t, X x) {}
      public static ˂X˃ void throwBall(X t, X x) {}
   }
```
3. B,D,E* - E vs F
4. C 
5. B,C,E
6. A,B,C,G - //TODO REVIEW
7. E,G*
8. C               //Trick VACILO
X 5     4 3 2 1
Y = 3   2 1 0 2
9. C - Boolean.valueOf("1")

   private boolean containsIce = Boolean.valueOf("1"); //false
   private boolean containsIce2 = Boolean.valueOf("ab"); //false

10. C,D,E,F* - //TODO REVIEW
```java
        System.out.println(Math.pow(Math.pow(a, 2) + Math.pow(b, 2), .5)); //2.82..
        System.out.println(Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 0.5)); //2.82..
        System.out.println(Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 1 / 2)); //1.0
        System.out.println(((double) 1 / 2) * (a * b)); //2.0
        System.out.println((1 / 2) * (a * b)); //0
        System.out.println((double) (1 / 2) * (a * b)); //0.0
```

The operations are applied first casting to int and then it transforms to double.

        System.out.println(IntStream.range(1, 10).max()); //OptionalInt[9]
        System.out.println(IntStream.rangeClosed(1, 10).max()); //OptionalInt[10]

11. A,B,E * -  C vs E 
12. D,E ?
A - 7
B - 6
C - 7
D - 9
E - 9
F - 7

        LocalDate plus = LocalDate.of(2025, Month.AUGUST, 4)
                .plus(Period.ofYears(-10).ofMonths(-1));
        System.out.println(plus); //2025-07-04

Period.ofYears(10).ofMonths(1) only applies the last one.

13. B,C,F
14. E - 20 Compile error

```java

    static interface Instrument {
    }

    public static class Microscope implements Instrument {
        public static void main(String[] resolution) {
            var v = new Microscope();
            System.out.println(switch (v) {
                case null -> "0";
                case Instrument i -> "1"; 
                case Microscope m -> "2"; //DOES NOT COMPILE 
                default -> "3"; //DOES NOT COMPILE
            });
        }
    }
    
```

The default operator cannot exist when all the options are reachable.

15. B

> enum constructors are implicitly private

      enum Score {
        FAST, SLOW;
        public Score() {} //DOES NOT COMPILE
      }


16. A,D,G
a=1 - A | b=1 - B
a=3 - A | b=4 - B
a=5 - A | b=7 - B
a=7 - A | 
a=9 - A | 
17. A - Executors.execute(Runnable or Callable)?
   

      Executors
         .newSingleThreadExecutor()
         .execute(Runnable)

18. A,C
19. B*

```java
   Locale.setDefault(Locale.of("en", "US"));
   var b = ResourceBundle.getBundle("Shark");
   System.out.println(b.getString("name"));
```
Shark_US.properties
Shark.properties

Java will get the information from Shar.properties.
It looks for these files and get from the first match.

* Shark_en_US.properties
* Shark_en.properties
* Shark.properties

20. D
21. B,C,E
numbers = 00246800
total = 0+4+-1
ch = 4
lenght = 8

------

        String numbers = "2468";
        numbers = numbers.indent(2);
        System.out.print(numbers); //"  2468\n"

indent adds a break line.

22. C
throw invalid image size
34
23. G - this() first statement
24. B,E
25. C


      Collectors.groupingBy(Function)
      Collectors.partitioningBy(Predicate)

26. B - missing this.color
27. B,F

IMPORTS:

The class can import itself

      1: package lilypad;
      2: import lilypad.Tadpole; //COMPILES
      3: public class Tadpole {
      4:    private Frog parent;
      5: }

28. E,G *
29. A,E ? private is overlaod?


      public void buzz() { }
      
      private void buzz(String sound) { } //OVERLOAD
      public static void buzz(int... time) { } //OVERLOAD
      public void buzz(boolean softly) { } //OVERLOAD
      public final void buzz() { } //OVERRIDE


30. H - LINE 9
31. C,D

If the class is not final, but has the private constructor it is considerable as immutable

Example:

      public class Toad { //Immutable
         private final String name;
         private Toad(String name) { this.name = name; }
      }

32. E - 5/2 - 2,x 5-2*2=1
33. A,D,E * F,G?


      s -˃ System.out.println(s) 
      (final s) -˃ System.out.println(s) //DOES NOT COMPILE
      (final String s) -˃ System.out.println(s)
      (final var s) -˃ System.out.println(s)
      (var s) -˃ System.out.println(s)

34. A,F ?   Tricky

35. A,C,G,H * - Essential vs incidental whitespace
One" Two ""

Three \"\"\"


----

        String puzzler = """
                   One " \
                   Two ""\n
                   Three \"\"\"
                """;
        System.out.print(puzzler); 

Output:

One "    Two ""

Three """

- The \ can be removed.
- Two blank lines are printed
- There is essential whitespace
- There is NO incidental whitespace

The last line is at the beginning of a line.
This means there is only essential whitespace.

        Incidental whitespace Espaço que é ignorado na hora de imprimir.
        Essential whitespace: Espaço intencional

36. F - 3, 14
37. E - var b not accessible
38. B,C, E * - Thread.start vs Thread.run

      Thread.run() //Sync
      Thread.start() // Async

39. C,D,E,F ?

Not provided:
- java.swing;
- jdk.nashorn;

40. D

        System.out.println(Integer.valueOf(128) == Integer.valueOf(128)); //false
        System.out.println(Integer.valueOf(127) == Integer.valueOf(127)); //true
        System.out.println(Integer.valueOf(10) == 10); //true

41. D,E,G


/storage/toys - resolve - Must be same in root or relative.
find vs walk
n4 - Variable is string

    TAKE NOTES:
Differnte between find vs walk
What does resolve does
    toRealPath() declares IOException, cannot be used inside a labda without catch

    var path = Path.of("/storage","toys").resolve("bird"); // n1
    Files.find(path, (p,a) -˃ a.isDirectory())  // n2
    .map(p -˃ p.toRealPath().toString()) // n3
    .map(p -˃ p.normalize()) // n4
    .forEach(System.out::print);



42. A
w = 2 - 4*3 = 12 - 4 - 5 
g = 4
43. B


    If we want to print a single quotes (') we need to use it inside two quotes ' ' ';
    System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm"))); //June 01, Party's at 01:01
    "'It''s 'h' hours past midnight, and 'mm' minutes'" //WORKS, must have two '' It's 4 hours past midnight, and 05 minutes 
    "'It's 'h' hours past midnight, and 'mm' minutes'" //DOES NOT WORK

44. A,B,D,F*
45. B - Trick

Variable inside the switch is the sabe from the outside

    21: record Item(String name) {}
    22: record Box(Item a, Item b) {}
    23: public class Shipping {
    24:    public static void main(String[] args) {
    25:       var b = new Box(new Item("Study Guide"), new Item("Pencil"));
    26:       System.out.print(switch(b) {
    27:          case null -˃ 0;
    28:          case Box(var x, var y) when x.name() != null -˃ 1;  
    29:          case Box(Item(var q), Item(var r))
    30:             when r.toUpperCase().equals("PENCIL") -˃ 2;
    31:          case Box(Item(CharSequence w), Item(String v))
    32:             when v.toLowerCase().equals("pencil") -˃ 3;
    33:          case Box(Item(String a), Item(String b)) -˃ 4; //DOES NOT COMPILE, variable b declared twice
    34:          default -˃ 5;
    35:       });
    36:    } }

46. C,D
47. A,C,D
48. A,E
49. B
50. B,E,F
R,W,T,X,S,Y,Z


* Correct: 25
* Mistake: 25
* Score: 50%
* Passing Score: 68%
* Took: 1:40h
