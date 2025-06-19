package org.bomfim.chapter6;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Exercises {
    public static void main(String[] args) {
        System.out.println("Constructors");
        System.out.println("Be careful with sensitive case, and methods with name name as the constructor");
        System.out.println("""
                    public class Bunny {
                       \s
                //        public bunny() {} //DOES NOT COMPILE
                        public void Bunny() {} //METHOD
                    }
                """);
        System.out.println("WARNING: Be careful with this and this(), this() is used to call the constructor");
        System.out.println("this() and super() must be the first statement in the constructor, at least for Java 21");
        System.out.println("In case there is two constructor and a infinite loop using this() the code will not compile");
        System.out.println("\nInitializing Objects");
        System.out.println("""
                Order:
                - Initialize superclass
                - Process all static variable declarations in order in which they appear in the class
                - Process all static initializations in order in which they appear in the class
                """);
        System.out.println("By the time the constructor completes, all the final instance variables must be assigned a value exactly once");
        System.out.println("Example:");
        new Dog();

        System.out.println("We refer to the ability of an object to take on many different forms as POLYMORPHISM");
        System.out.println("\nOverriding");
        System.out.println("""
                Rules:
                - The method in the child class must have the same signature as the method in the parent class
                - The method in the child class must be at least as accessible as the method in the parent class.
                - The method in the child class may not declare a checked exception that is new or broader than the class of any exception declared in the parent class method.
                - If the method returns a value, it must be the same or a subtype of the method in the parent class, know as covariant return types.
                """);
        System.out.println("Be careful, the exception rules applies only for checked exceptions");
        System.out.println("If the method is private you can create the same method in the Child class, it is not considered override and it compiles");
        System.out.println("Hidden method occurs when a child class defines a static method with the same name and signature as an inherited static method. ");
        System.out.println("WARNING: Override method can be final, but cannot be static");
        System.out.println("WARNING: If there is a static method on the parent class and the child class override it without the static is causes COMPILE ERRORs");

        System.out.println("""
                    public static class Carnivore {
                        protected boolean hasFur = false;
                    }
                   \s
                    public static class Meerkat extends Carnivore {
                        protected boolean hasFur = true;
                    }
                    public static void main(String[] args) {
                        Meerkat meerkat = new Meerkat();
                        Carnivore c = meerkat;
                        System.out.println(meerkat.hasFur); //true
                        System.out.println(c.hasFur); //false
                   }
                """);
        Meerkat meerkat = new Meerkat();
        Carnivore c = meerkat;
        System.out.println(meerkat.hasFur); //true
        System.out.println(c.hasFur); //false
        System.out.println("\nAbstract");
        System.out.println("Be careful, check all the subclass of an abstract class, check if they extends the abstract methods");
        System.out.println("""
                    public abstract class Abstract {}
                //    public class abstract Abstract {} //DOES NOT COMPILE the order is important
                //    public final abstract class Abstract {} //DOES NOT COMPILE final is to allowed
                    abstract public class AbstractPublic {}
                    abstract static public class AbstractPublic2 {}
                """);
        System.out.println("WARNING: An Abstract method cannot be final, private or static");
        System.out.println("""
                    public abstract class Abstract {
                //        private abstract void x(); //DOES NOT COMPILE
                        abstract void x();
                //        static abstract void x(); //DOES NOT COMPILE
                //        final abstract void x(); //DOES NOT COMPILE
                    }
                """);
    }

    public class Mammal {
    private void eat() {}
    protected static void drink() {}
    public Number dance(String p) { return null; }
 }

 class Monkey extends Mammal {
    public static void drink() throws RuntimeException {}
    public Number dance(CharSequence p) { return null; }

     @Override
     public Integer dance(String p) {
        return null;
     }

     public int eat(String p) {
        return 1;
     }
 }

    public static class Carnivore {
        protected boolean hasFur = false;
    }

    public static class Meerkat extends Carnivore {
        protected boolean hasFur = true;
    }

    public abstract class Abstract {
//        private abstract void x(); //DOES NOT COMPILE
        abstract void x(); //DOES NOT COMPILE
//        static abstract void x(); //DOES NOT COMPILE
//        final abstract void x(); //DOES NOT COMPILE
    }
//    public final abstract class Abstract {} //DOES NOT COMPILE
//        public class abstract Abstract {} //DOES NOT COMPILE
    abstract public class AbstractPublic {}
    abstract static public class AbstractPublic2 {}



    public class SuperParent { }
    public class Parent extends SuperParent {

        public static void staticMethod() {}
        public static void staticMethod2() {}
        private final void getSize(){};
        public final void getSizeFinal(){};
        public Parent getParent(){return null;}
        public SuperParent getSuperParent(){return null;}
        public void test() throws IOException {}
        public void test2() throws FileNotFoundException {}
        public void test3() throws IllegalAccessException {}
    }

    public class Child extends Parent {

        public static void staticMethod() {} //HIDDEN METHOD
//        public void staticMethod2() {} //DOES NOT COMPILE without static
        public void getSize(){}; //DOES COMPILE, IT IS FINAL, BUT PRIVATE - NOT an OVERRIDE
//        public void getSizeFinal(){}; //DOES NOT COMPILE, the method is final
        @Override
        public final Child getSuperParent() { //WORKS WITH FINAL
            return new Child();
        }
//        public static Child getSuperParent() {...} //DOES NOT COMPILE Child < SuperParent
        @Override
        public Parent getParent() { //cannot change Parent with SuperParent
//            return new SuperParent();  //DOES NOT COMPILE
            return null;
        }

        @Override
        public void test() throws FileNotFoundException {} //DOES COMPILE FileNotFoundException < IOException

//        @Override
//        public void test2() throws IOException { } //DOES NOT COMPILE IOException > FileNotFoundException

        @Override
        public void test3() throws RuntimeException { //COMPILES, IT IS AN UNCHECKED EXCEPTION
        }
    }

    public static class Animal {
        final String name;
        static {
            System.out.println("Static Initialize - Animal");
        }

        public Animal() {
            System.out.println("Constructor - Animal");
        }
        {
            name = "Animal var";
            System.out.println("Block Initialize - Animal");
        }
    }

    public static class Dog extends Animal {
        static  {
            System.out.println("Static Initialize  - Dog");
        }
        {
            System.out.println("Block Initialize - Dog");
        }
        public Dog() {
            System.out.println("Constructor - Dog");
        }
    }

    public class Bunny {

//        public bunny() {} //DOES NOT COMPILE
        public void Bunny() {} //METHOD
    }
}
