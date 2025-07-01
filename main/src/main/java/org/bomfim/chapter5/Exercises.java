package org.bomfim.chapter5;

public class Exercises {

    public static void main(String[] args) {

        System.out.println("Access Modifiers");
        System.out.println("Note: All the access modifiers must all appear before the return type.");
        System.out.println("""
                class Java {
                    public void test() {}
                    default void test() {} //DOES NOT COMPILE
                    void public test() {} //DOES NOT COMPILE
                    final public void compiles() {} 
                }
                
                class Override {
                    public void test() {}
                    default void test() {} //DOES NOT COMPILE
                    void public test() {} //DOES NOT COMPILE
                    final public void compiles() {} 
                }
                
                """);
        System.out.println("FINAL variables does not have a default value.");
        System.out.println("\nVarargs");
        System.out.println("Varargs must be the last parameter");
        System.out.println("A method can have only one varargs argument");


        System.out.println("\nAccess Modifiers");
        System.out.println("""
                Careful: protected methods can be accessed by the subclasses, however, if we instantiate the super class we cannot use the override method, it causes compile error." +
                Example: 
                package example;
                public class Father {
                 protected void getName() {}
                }
                package example.sub;
                import example.Father;
                public class Son {
                 protected void getSuperName() {
                    super.getName();
                 }
                 public void createParent(){
                   new Father().getName(); //DOES NOT COMPILE
                 }
                 public void createSoonToParent(){
                   Father x = new Son(); 
                   x.getName(); //DOES NOT COMPILE
                 }
                }
                """);

        System.out.println("\nStatic");
        System.out.println("WARNING: The exam can have questions with NPE for static methods that is false, it is a trick question.");
        System.out.println("""
                Be careful, static methods can be used even if we set to null the variable.
                
                public class Static {
                
                    static int count = 0;
                    public static void main(String[] args) {
                        var s = new Static();
                        s.count++;
                        s = null;
                        System.out.println(s.count); //COMPILES
                
                        Static x = null;
                        System.out.println(x.count); //COMPILES
                
                        Static x2;
                        System.out.println(x2.count); //DOES NOT COMPILE, must be initialized
                    }
                }
                
                """);

        System.out.println("""
                    import static java.util.Arrays; //DOES NOT COMPILE
                    import static java.util.Arrays.asList;
                    static import java.util.Arrays.asList; //DOES NOT COMPILE
                    static import java.util.Arrays; //DOES NOT COMPILE
                """);

        System.out.println("\nBoxing and Unboxing");
        System.out.println("Java does not cast and autobox at the same time");
        System.out.println("""
                public static class Gorilla {
                    public void rest(Float l) {
                        System.out.println(l);
                    }
                
                    public void rest(long l) {
                        System.out.println(l);
                    }
                
                    public static void main(String[] args) {
                        var g = new Gorilla();
                        g.rest(1.0); //DOES NOT COMPILE
                        g.rest(1.0f); //COMPILE
                        g.rest(1); //COMPILE
                    }
                }
                """);
        System.out.println("\nOverloading Methods");
        System.out.println("Overloading is when the method have the same name, but different method signatures, which means they use different parameters.");
        System.out.println("Java picks the most specific method when there are many overloading");

        fly(1L); //primitive
        fly(Long.valueOf(1)); //Long
        fly(1); //primitive
        fly(new int[]{1, 2, 3});
        fly(new Integer[]{1, 2, 3});
//        fly(123.5); //DOES not compile
        square(2);
    }

    public static long square(int a) {
        var i = a * a;
        return i;
    }
    public static long square(float a) {
        var i = a * a;
//        return i; //DOES NOT COMPILE
//        return null; //DOES NOT COMPILE
        return 0;
    }

    public static void fly(float i) {
        System.out.println("Long");
    }

    public static void fly(int[] values) {
        System.out.println("int[]");
    }

    public static void fly(Integer[] values) {
        System.out.println("Integer[]");
    }
//    public static void fly(int... values) { } //DOES NOT COMPILE

    public static void fly(Long i) {
        System.out.println("Long");
    }

    public static void fly(long i) {
        System.out.println("primitive");
    }

//    public int fly(int i) { //DOES NOT COMPILE
//        return 0;
//    }

    public int fly(char i) {
        return 0;
    }

    public void fly(String i) {
    }

    public void fly(short i) {

    }

    public static class Gorilla {
        public void rest(Float l) {
            System.out.println(l);
        }

        public void rest(long l) {
            System.out.println(l);
        }

        public static void main(String[] args) {
            var g = new Gorilla();
//            g.rest(1.0); //DOES NOT COMPILE
//            g.rest(1.0f); //COMPILE
            g.rest(1); //COMPILE
        }
    }


//    final int a; //DOES NO COMPILE - Needs to be initialized, because it is an instance variable. It should be initialized with the constructor, value or a block

    public String x(String str) {
        if (1 < 2) return "a";
        return "b";
    }

    public void method() {
        final int x;
        final int y;
        x = 5;
//        System.out.println(y); //ODES NOT COMPILE, y must be initialized
    }

    class Override {
        public void same(String name, Integer id) {
        }

        ;

        public void same(String name, Integer id, String name2) {
        }

        ;
//      public void same(String name, Integer id){}; //DOES NOT COMPILE DUPLICATED METHOD
    }
}
