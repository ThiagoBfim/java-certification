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
            var a = (Integer) null;
            var x = () -> new Var(); //DOES NOT COMPILE - var cannot be used with Functional Interface
        }
    }
```

## Cap 02

### Casting

    int x 5;
    x = x + amountToAdd % -5;
    System.out.println(x) //5;

    int monday = 3 + 2.0; //does not compile

## Cap 03

## Cap 04

### Arrays

```java
    int[] array = {6, 9, 8};
    System.out.println("B" + Arrays.binarySearch(array,9)); //B-1 Array is not sorted
    System.out.println("C" + Arrays.compare(array, new int[] {6, 9, 8})); //C0
    System.out.println("M" + Arrays.mismatch(array, new int[] {6, 9, 8})); //M-1
```

* Arrays Mismatch returns -1 if the arrays are equal
* Arrays Mismatch returns index of first mismatch otherwise.

### String

```java
    var pooh = """
    "Oh, bother." -Pooh
    """.indent(1);
    System.out.print(pooh); //" \"Oh, bother.\" -Pooh\n"
```

## Cap 05

## Cap 06

## Cap 07

### Record

```java
    record Music(List<String> tempo) {
         final int score = 10; //DOES NOT COMPILE
    }
```
* Record cannot have an instance variable


### Sealed and Switch and Record

```java
record Music(List<String> tempo) { 
   final int score = 10; //DOES NOT COMPILE
}
record Song(String lyrics, Music m) {
   Song {
      this.lyrics = lyrics + "Never gonna give you up"; //DOES NOT COMPILE
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

## Cap 08

### Functional Interface

```java
        (i) -> i == 5
        (i) -> {return i == 5;}
        (Integer i) -> {return i == 5;}
        (int i) -> {return i == 5;} //DOES NOT COMPILE it cannot be primitive 
```

## Cap 09

### Generic

```java
    Set<? extends RuntimeException> mySet = new TreeSet<RuntimeException>();
    Set<? extends RuntimeException> mySet = new TreeSet<NullPointerException>();
    Set<? extends RuntimeException> mySet = new LinkedHashSet<>();
    Set<? extends RuntimeException> mySet = new LinkedHashSet<?>(); //DOES NOT COMPILE
    Set<? extends RuntimeException> mySet = new HashSet<Exception>(); //DOES NOT COMPILE
```

## Cap 10

## Cap 11

## Cap 12

### Modules

Be careful with public module file `module-info.java`

```java
    public module com.vet {
        exports com.vet;
    }
```

* Does not works, module does not have the `public` access modifier

----

* A **named** module always contains a module-info.java file, while an **automatic** module always exports all its packages to other modules.
* An unnamed module is on the classpath, while a named and automatic module is on the module path.

## Cap 13

## Cap 14

### Files

```java
System.out.println(Files.mismatch(path1,path2)); 
```

* If the file does not exist, it throws an exception
* It returns the index of the last non-match value
* If the files match it returns -1

