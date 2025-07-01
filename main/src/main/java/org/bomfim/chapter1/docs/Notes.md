# Chapter 1

## Topics

- Handling date, time, text, numeric and boolean values
- Using O.O Connects in Java

### Javac / java

| Option                             | Description                                       |
|------------------------------------|---------------------------------------------------|
| -cp<br/>-classpath<br/>-class-path | Location of classes need to compile the program   |
| -d <dir>                           | Directory in which to place generated class files |

Example:
* `javac -d classes -cp classes packageb.ClassB `
* `java -cp classes packageb.ClassB `


## Tricks

* Be aware that could have questions with "false" constructor

```java
public class Chick {
  public void Chick() {
    System.out.println("Not a constructor");
  }
}
```

### Primitive types

* double x = _1000.00 //DOES NOT COMPILE
* double x = 1000.00_ //DOES NOT COMPILE
* double x = 1000_.00 //DOES NOT COMPILE
* double x = 1_000.0_0 //COMPILE
* double x = 1_0 //COMPILE

Float and Double does not require decimal case to works. In case 

        short a = 14;
        float b = 13.0; //DOES NOT COMPILE
        float b2 = 13; //Compile
        double c = 30; 
        var d = a * b / c;
        System.out.println("a*b/c = " + d);

Long does require l at the end.

        short a = 14;
        Long b = 13; //DOES NOT COMPILE
        long c = 13; //COMPILE
        var d = a * b / c;
        System.out.println("a*b/c = " + d);

### Variables

Variables must begin with a letter, a currency symbol or a _ symbol.
$, ¥ and €.

The name cannot start with number.

Be careful with VARS

* int x, var z = 3; //DOES NOT COMPILE
* int x, int z = 3; //DOES NOT COMPILE
* var x, z = 3; //DOES NOT COMPILE
* var a = (Integer) null; //COMPILE

## Tips

* This exam does not have questions focus on invalid package names
* Instance and class variables do not require you to initialize them.
* The exam can have many trick questions about var
* The exam can have many questions about scope variables, trying to add compile erros in a trick question

## Tricks

* int num, String values; // Does not compile
* var cannot be used for instance variables

## Review Questions Notes

* Warning: The X variable will not be used on the following code.

```java
int x;
String x = """
       " a " + x 
        """
```

* Warning, var can be associated with primite types

```java
  var a = 1;
  a = null; //DOES NOT COMPILE
```

* FALSE: Garbage collection runs on a set schedule
* TRUE: An object may be elibible for garbage collection but never removed from the heap - This can happen if the program exit
* int a4 = 9_2.1_2; //DOES NOT COMPILE
* int a4 = 9_2.1_2; //DOES NOT COMPILE
* Classes in the same package does not require import
* Private attributes are accessible if you are inside the class.

