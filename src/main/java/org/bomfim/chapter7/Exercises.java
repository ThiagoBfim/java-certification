package org.bomfim.chapter7;


public class Exercises {

    public static void main(String[] args) {
        System.out.println("Annotations");
        System.out.println("@Deprecated -> trigger a compile warning");
        System.out.println("@SuppressWarning -> ignore a warning");
        System.out.println("@SafeVarargs -> Lets other developers know that a method does not perform any potencial unsafe operations on its vararg parameters");
        System.out.println("\nInterfaces");
        System.out.println("""
                abstract interface WalkOnLegs { }
                final abstract interface WalkOnLegs { } //DOES NOT COMPILE because it is final
                abstract interface WalkOnLegs extends WalkOnLegs2 { }
                abstract interface WalkOnLegs implements WalkOnLegs2 { } //DOES NOT COMPILE should be extends
                """);
        System.out.println("""
                * Interfaces are implicitly abstract
                * Interface variables are implicitly public static and final
                * Interface variables and methods without body cannot be private or protected
                * Interface methods without a body are implicitly abstract
                * Interface methods without the private modifier are implicitly public
                * Default method is public for interfaces
                * Default method cannot be abstract, final or static
                
                * If the class inherits two or more default methods with the same method signature, then the class must override the method.
                * Static method can be private or public for interfaces
                """);

        System.out.printf("\nEnums");
        System.out.println("Enums cannot be extended");
        System.out.println("""
                    enum Season{
                        SUMMER,
                        WINTER,
                        SPRING,
                        AUTUMN;
                    }
                    Season.valueOf("Summer"); //IllegalArgumentException
                    Season.valueOf("SUMMER"); //OK)
                """);
        System.out.println("The constructor of an enum is private");
        System.out.println("An enum can contain static and instance methods and implements an interface");
        System.out.println("\nSealed Class");
        System.out.println("A Sealed class is a class that restricts the inheritance of its subclasses");
        System.out.println("""
                sealed class SealedClass permits SubClassSealed, SubClassNonSealed, SubClassRecord {
                    public void method() {}
                }
                final class SubClassSealed extends SealedClass {
                    public void method2() {}
                }
                non-sealed class SubClassNonSealed extends SealedClass {
                    public void method2() {}
                }
                record SubClassRecord extends SealedClass { } //DOES NOT COMPILE
                """);
        System.out.println("""
                Rules:
                * A sealed class cannot be final  -> final sealed class SubClassSealed {} //DOES NOT COMPILE 
                * All Sealed class must have "permits" if it is on different packages, otherwise it does not need it.
                    abstract sealed  class SealedClass { //COMPILE
                        public void method() {}
                    }
                
                    final class SubClassSealed extends SealedClass {
                        public void method2() {}
                    }
                * The code above omits the "permits" because they are in the same package
                * Be careful with nested subclasses
                 abstract sealed class SealedClass1 { //COMPILE
                        final class SubClassSealed extends SealedClass1 {
                            public void method2() {
                            }
                        }
                    }
                
                    abstract sealed class SealedClass2 permits SealedClass2.SubClassSealed { //COMPILE
                        final class SubClassSealed extends SealedClass2 {
                            public void method2() {
                            }
                        }
                    }
                
                    abstract sealed class SealedClass3 permits SubClassSealed { //DOES NOT COMPILE
                        final class SubClassSealed extends SealedClass3 {
                            public void method2() {
                            }
                        }
                    }
                
                When all of your subclasses are nested, we strongly recommend omitting the permits class.
                
                * All classes on the permits must be a subclass of the Sealed class, otherwise the code will not compile because of the sealed class
                * Every class that extends a sealed class must be: final, sealed or non-sealed.
                * Sealed classes are declared with the sealed and permits modifiers.
                * Sealed classes must be declared in the same package or named module as their direct subclasses.
                * Direct subclasses of sealed classes must be marked final, sealed, or non-sealed. 
                * For interfaces that extend a sealed interface, only sealed and non-sealed modifiers are permitted. 
                * The permits clause is optional if the sealed class and its direct subclasses are declared within the same file or the subclasses are nested within the sealed class. 
                * Interfaces can be sealed to limit the classes that implement them or the interfaces that extend them.
                """);
        System.out.println("Selling interfaces");
        System.out.println("""
                    Sealed interfaces allow for records.
                
                    public sealed interface Swims permits Swims.Duck {
                
                        record Duck() implements Swims {
                        }
                    }
                """);
        System.out.println("WARNING: If the sealed class is not abstract, the pattern matching requires a default clause");
        System.out.println("\nRecords");
        System.out.println("Records are final and static classes by default");
        System.out.println("Because records are static they cannot access instance variables from another class. Example:");
        System.out.println("""
                    public class ClassA {
                             private int x = 0;
                
                             public record Record(int id) {
                                 public Record {
                                     System.out.println(id); //COMPILE
                     //                System.out.println(x); //DOES NOT COMPILE
                                 }
                             }
                         }
                """);
        System.out.println("Compact Constructors: is a special type of constructor used for records to process validation and transformations");
        System.out.println("Compact Constructors cannot modify the instance variables");
        System.out.println("""
                    public record Record(String name, int age) {
                         public Record(String a) { //Overloaded constructors
                                this(a, 0);
                         }
                        public Record {
                            System.out.println("Compact Constructor: " + name + " " + age);
                            //this.name = "Name"; //DOES NOT COMPILE
                        }
                    }
                """);
        System.out.println(new Record("abc", 1));
        System.out.println(new Record("abc2"));

        System.out.println("\nPattern Matching for InstanceOf");
        System.out.println("Pattern matching for InstanceOf allows inheritance, but does not support numeric promotion and casting");
        patternMatchingInstanceOf(new Record("a", 1));
        System.out.println("\nNesting Records Patterns");
        System.out.println("""
                public record Records(Record record, String id){}
                var records = new Records(new Record("a", 1), "1");
                if(records instanceof Records(Record record, String id)){} //COMPILE
                if(records instanceof Records(Record(String name, int age), String id)){} //COMPILE
                // if(records instanceof Records(Record(String id, int age), String id)){} //DOES NOT COMPILE, variable with same name
                """);
        System.out.println("Be careful with this case, where breaking the record should have different attribute names ");
        System.out.println("""
                Pattern Matching allows generic, however there are cases where it does not compile
                    if(c instanceof Couple(Bear(var n, List<> f),   var b)) {} //DOES NOT COMPILE List<> 
                    if(c instanceof Couple(Bear(var n, List<Object> f), var b)) {} //DOES NOT COMPILE List<Object> 
                    if(c instanceof Couple(Bear(var n, Object f),         var b)) {} //COMPILE
                    if(c instanceof Couple(Bear(var n, List f),          var b)) {} //COMPILE
                     if(c instanceof Couple(Bear(var n, List<?> f),        var b)) {} //COMPILE
                """);

        switchPatternMatchingRecords(new A("A"));
        switchPatternMatchingRecords(new B(2));
        System.out.println("""
                      switch (object) { //COMPILE
                            case B(Integer b) -> System.out.println(b);
                            case B(Object b) -> System.out.println(b);
                            case Object obj -> System.out.println(obj);
                      }
                      switch (object) {
                          case B(Object b) -> System.out.println(b);
                          case B(Integer b) -> System.out.println(b); //DOES NOT COMPILE
                          case Object obj -> System.out.println(obj);
                      }
                """);
        System.out.println("""
                Things the exam could ask about records:
                * Overloaded and compact constructors
                * Instance methods including overriding any provided methods (accessors, equals(), hashCode(), toString()) 
                * Nested classes, interfaces, annotations, enums, and records
                Warning: While you can add methods, static fields, and other data types, you cannot add instance fields outside the record declaration, even if they are private and final
                public record A(String a) {
                    String name; //DOES NOT COMPILE
                    private final int age = 5; //DOES NOT COMPILE  
                    static {System.out.print("Hello Bird!"); } 
                    {System.out.print("Goodbye Bird!"); }// DOES NOT COMPILE
                }
                """);
        System.out.println("\nInner class");
        System.out.println("""
                        public class A2 {}
                        public static class A3 {}
                        new Exercises.A("A");
                        Exercises exercises = new Exercises();
                        var x = exercises.new A2();
                        var x2 = exercises.new A3(); //DOES NOT COMPILE the A3 is static
                """);
        new Exercises.A("A");
        Exercises exercises = new Exercises();
        var x = exercises.new A2();
//        var x2 = exercises.new A3(); //DOES NOT COMPILE
        localClass();
        System.out.println("\nLocal Class");
        System.out.println("""
                                  public static void localClass() {
                                        int x = 0;
                                        int y = 1;
                                        class LocalClass {
                                            public void method() {
                //                                System.out.println(x); //DOES NOT COMPILE x is not effective final
                                                System.out.println(y);
                                            }
                                        } //DOES NOT HAVE SEMICOLON ;
                                        LocalClass localClass = new LocalClass();
                                        localClass.method();
                                        x = 5;
                                    }
                                WARNING: Local Class are classes created inside a method. These classes can access only final and effective final variables
                """);
        System.out.println("""
                Warning: The compiler disallowed casts to unrelated types
                Example: 
                String name = "ab";
                Integer value = (Integer) name; //DOES NOT COMPILE
                """);
        System.out.println("OBS: This compile error does not occur with interfaces");
        System.out.println("With interfaces, the compiler has limited ability to enforce many rules because even though a reference type may not implement an interface, one of its subclasses could.");
        System.out.println("""
                Test test = new Test();
                Interface a = (Interface)test; //COMPILE
                """);
        System.out.println("Polymorphism’s ability to replace methods at runtime via overriding is one of the most important properties of Java");
        Test test = new Test();
//        Interface a = (Interface)test; //COMPILE throws java.lang.ClassCastException

        var stripes = exercises.new Zebra(). new Stripes(){};
        stripes.print();
    }

    public class Zebra {
        int x = 5;

        abstract class Stripes {
            private int x = 0;

            public void print() {
                System.out.print(Zebra.this.x);
            }
        }
    }

    interface Interface {
        static double size = 2.0f;

        public static void eatGrass() {
        }

        private static void eatGrass2() {
        }

    }

    static class Test2 implements Interface {
    }

    static class Test {
    }

    static int z = 5;

    public static void localClass() {
        int x = 0;
        int y = 1;
        z = 8;
        class LocalClass {
            public void method() {
//                System.out.println(x); //DOES NOT COMPILE
                System.out.println(y);
                System.out.println(z);
            }
        }
        LocalClass localClass = new LocalClass();
        localClass.method();
        x = 5;
    }

    public class ClassA {
        private int x = 0;
        private static int y = 1;

        public record Record(int id) {
            public Record {
                System.out.println(id); //COMPILE
                System.out.println(y); //COMPILE
//                System.out.println(x); //DOES NOT COMPILE
            }
        }
    }

    public class A2 {
    }

    public static class A3 {
    }

    private static void switchPatternMatchingRecords(Object object) {
        switch (object) {
            case A(String a) -> System.out.println(a);
            case B(Integer b) -> System.out.println(b);
            case B(Object b) -> System.out.println(b);
            case C(double b) -> System.out.println(b);
            case Object obj -> System.out.println(obj);
        }

        switch (object) {
            case B(Object b) when (b instanceof Integer i) -> System.out.println(i.describeConstable());
            case B(Integer b) when b > 3 -> System.out.println(b);
            case Object obj -> System.out.println(obj);
        }
    }

    public record A(String a) {
        //        String name; //DOES NOT COMPILE
//        private final int age = 5; //DOES NOT COMPILE
        static {
            System.out.print("Hello Bird!");
        }
//        {System.out.print("Goodbye Bird!"); }// DOES NOT COMPILE
    }

    public record B(Integer b) {
    }

    public record C(double d) {
    }

    public record Records(Record record, String id) {
    }

    public static void patternMatchingInstanceOf(Record record) {
        if (record instanceof Record(String name, int age)) {
            System.out.println("if(record instanceof  Record(String name, int age)) //COMPILE");
        }
        if (record instanceof Record(Object name, int age)) {
            System.out.println("if(record instanceof  Record(Object name, int age)) //COMPILE");
        }

//        if(record instanceof  Record(Object name, long age)){
        System.out.println("if(record instanceof  Record(Object name, long age)) //DOES NOT COMPILE");
//        }


//        if(record instanceof  Record(Object name, Integer age)){
        System.out.println("if(record instanceof  Record(Object name, Integer age)) //DOES NOT COMPILE");
//        }
    }

    public static record Record(String name, int age) {

        public Record(String a) {
            this(a, 1);
            System.out.println("Overloaded Constructor: " + name + " " + age);
        }

        public Record {
            System.out.println("Compact Constructor: " + name + " " + age);
//            this.name = "Name"; //DOES NOT COMPILE
        }
    }

    public void patternMatchingSealed(Swims swims) {
        switch (swims) {
            case Swims.Duck duck -> {
            }
        }
    }

    public void patternMatchingSealed1(SealedClass1 swims) {
        switch (swims) {
            case SealedClass1.SubClassSealed subClassSealed -> {
            }
        }
    }

    public void patternMatchingSealed2(SealedClass2 swims) {
        switch (swims) {
            case SealedClass2.SubClassSealed subClassSealed -> {
            }
            default -> throw new IllegalStateException("Unexpected value: " + swims);
        }
    }

    public sealed interface Swims permits Swims.Duck {

        record Duck() implements Swims {
        }
    }

    abstract sealed class SealedClass1 { //COMPILE
        final class SubClassSealed extends SealedClass1 {
            public void method2() {
            }
        }
    }

    sealed class SealedClass2 permits SealedClass2.SubClassSealed { //COMPILE
        final class SubClassSealed extends SealedClass2 {
            public void method2() {
            }
        }
    }


    abstract sealed class SealedClass {
        public void method() {
        }
    }

    final class SubClassSealed extends SealedClass {
        public void method2() {
        }
    }

    non-sealed class SubClassNonSealed extends SealedClass {
        public void method2() {
        }
    }
//    record SubClassRecord extends SealedClass { } //DOES NOT COMPILE

    enum Complex {
        STRING(""),
        DOUBLE(2.0);

        Complex(Object value) {
        }
//        public Complex(Object value) {} //DOES NOT COMPILE
//        protected Complex(Object value) {} //DOES NOT COMPILE
    }

    enum Season {
        SUMMER,
        WINTER,
        SPRING,
        AUTUMN;
    }

    abstract interface WalkOnLegs extends WalkOnLegs2 {
        //        public void walkLegs(); //DOES NOT COMPILE
        public int walkLegs();

        private int xaa() {
            return 0;
        }

        //        protected int x(); //DOES NOT COMPILE
//      private int x(); //DOES NOT COMPILE
//      private int x = 5; //DOES NOT COMPILE
//        protected int x2 = 5; //DOES NOT COMPILE
        int x = 5;

        default void method() {
        }
//        default void walkLegs2() {} //DOES NOT COMPILE

        private static void _static() {
        }

        ;
    }

    abstract interface WalkOnLegs2 {
        public int walkLegs();

        default int walkLegs2() {
            return 2;
        }

    }

    private static class Animal implements WalkOnLegs, WalkOnLegs2 {
        @Override
        public int walkLegs() {
            return 0;
        }


        @Override
        public void method() {
            WalkOnLegs.super.method();
        }
    }

}
