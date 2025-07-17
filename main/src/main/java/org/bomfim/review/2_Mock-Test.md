Enthuware - Foundation Test 1

### String
        System.out.println("12345".charAt(6)); //throw an IndexOutOfBoundsException
        "abc".lines(); //return a Stream<String> separated by line terminators
        "abc".isEmpty(); //false
        "abc".isBlank(); //false

> method length() of String class is a final method. String class itself is final and so all of its methods are implicitly final.


#### StringBuilder

    new StringBuilder().trim() //DOES NOT COMPILE - trim does not exist
    var abc = new StringBuilder("abc");
    abc.ensureCapacity(1);
    System.out.println(abc); //abc
    var abc2 = new StringBuilder("abc");
    abc2.setLength(1);
    System.out.println(abc2); //a

> StringBuilder is a final class.

### Casting

    Long getValue() { return 2; //DOES NOT COMPILE }

    System.out.println(2+""); //Autoboxing  //If the other operand is a numeric primitive type, then it is first converted to a reference type using the boxing conversion and then the boxed reference is used to produce a String.
    Integer i = 10; //Autoboxing

### Arrays

```java
int[] i, j; //here i and j are both array of integers. 
int i[], j; //here only i is an array of integers. j is just an integer.
int[10] IA; //DOES NOT COMPILE
```

### Modules

> If a package is defined in both a named module and the unnamed module then the package in the unnamed module is ignored.
> If a request is made from an automatic module to load a type whose package is not defined in any known module then the module system will attempt to load it from the classpath


#### Botton-up vs top-down migration

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

Print service interface defined in PrintServiceAPI module using com.abc.PrintImpl class, then this is how its module-info.java should look: 
module abc.print { 
    requires PrintServiceAPI; //required //because this module defines the service interface org.printing.Print provides
    org.printing.Print with com.abc.PrintImpl; 
} 

A module named customer that uses Print service may look like this: 
module customer {
    requires PrintServiceAPI; //required //because this module defines the service interface org.printing.Print
    uses org.printing.Print; //specifies that this module uses this service //observe that abc.print module is not required.
}





### List

    List students = new ArrayList();

The reference type is ArrayList and the object type is List

### Callable vs Runnable

Callable - V call() throws Exception;
Runnable - void run();

### IO 


#### Console

> When the JVM is started from an interactive command line without redirecting the standard input and output streams

The declared type of System.in is java.io.InputStream. It refers to an object of type java.io.BufferedInputStream.

> The System.in variable cannot be reassigned to any other stream directly.

Although in is a final variable and so, it cannot be reassigned directly. However, System class has a public static void setIn(InputStream in) method that can be used to change in to refer to any other InputStream.



### Date

Thread-safe: 

* java.time.Instant
* java.time.Duration

Old Date: java.util.Date\
New Date: java.time.LocalDate


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


#### Anonymous class

You cannot pass parameters when you implement an interface by an anonymous class.

    new ComplexInterface(x) { //DOES NOT COMPILE 
     // valid code 
    }

    new ComplexInterface() { //COMPILE 
     // valid code 
    }


### Locale

    Locale(String language)  Construct a locale from a language code. 
    Locale(String language, String country)  Construct a locale from language, country. 
    Locale(String language, String country, String variant)  Construct a locale from language, country, variant.


### Function Interface

It takes int primitive as an argument. It can be parameterized to return any type. For example,
`IntFunction<String> f = x->""+x;` returns a String.


