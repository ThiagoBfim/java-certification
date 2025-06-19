package org.bomfim.chapter11;

import java.io.FileInputStream;
import java.io.IOException;

public class Exceptions {

    public static void main(String[] args) {
        System.out.println("""
                Be careful throw vs throws
                void myMethod(String x) throw RuntimeException {} //DOES NOT COMPILE throw
                throws new RuntimeException(); //DOES NOT COMPILE throws
                throw RuntimeException(); //DOES NOT COMPILE missing the world new
                throw new RuntimeException(); //COMPILE
                """);
        System.out.println("""
                Be careful with handling checked exceptions
                When you see a checked exception declared inside a catch block on the exam, 
                make sure the code in the associated try block is capable of throwing the exception or a subclass 
                of the exception. If not, the code is unreachable and does not compile. 
                Remember that this rule does not extend to unchecked exceptions or exceptions declared in a method signature.
                """);

//        int answer = 11 / 0; //throws java.lang.ArithmeticException
        int[] count = new int[3];
//        count[-1] //DOES NOT COMPILE
//        int i = count[-1]; //throws ArrayIndexOfBoundException
//        int x = (int)new String("1") //DOES NOT COMPILD
        String s = new String("1");
//        Integer x = (Integer) s; //DOES NOT COMPILE
        Object s2 = s;
//        Integer x = (Integer) s2; //throws ClassCastException

        Integer y = null;
//        y.toString(); //throws NPE

        System.out.println("\nIllegalArgumentException");
        System.out.println("""
                Thrown by programmer to indicate that method has been passed illegal or inappropriate argument.
                For the exam, you need to know that NumberFormatException is a subclass of IllegalArgumentException
                
                try {  } catch (IllegalArgumentException e){  } catch (NumberFormatException e){ // DOES NOT COMPILE  }
                """);

        System.out.println("""
                Checked Exception
                * FileNotFoundException
                * IOException
                * NotSerializableException
                * ParseException
                You need to know that FileNotFoundException and NotSerializableException are subclasses of IOException
                """);

        System.out.println("""
                Error
                * ExceptionInInitializerError
                * StackOverflowError
                * NoClassDefFoundError
                """);

        System.out.println("""
                Be careful 
                
                try {  }
                 catch (NumberFormatException e1) {  
                  System.out.println(e1);
                  } 
                 catch (IllegalArgumentException e2) {  
                  System.out.println(e1); // DOES NOT COMPILE  variable from previous catch block
                }
                
                """);

        try {
        } catch (RuntimeException ex) {
        } catch (Exception ex) {
        }
        try {
        } catch (Exception ex) {
        }
        try {
        } catch (Exception ex) {
        } finally {
        }
        try {
        } finally {
        }
//        try {} //DOES NOT COMPILE


//       try {} catch(RuntimeException ex | Exception ex){}  // DOES NOT COMPILE, the variable should be instanced once
//       try {} catch(RuntimeException e1 | Exception e2){} // DOES NOT COMPILE, the variable should be instanced once
//       try {} catch(RuntimeException | Exception  ex){} // DOES NOT COMPILE, cannot catch exception from the same parent
//       try {} catch(FileNotFoundException | ParseException ex){} // DOES NOT COMPILE, Exception 'java.io.FileNotFoundException' is never thrown in the corresponding try block
        try {
        } catch (NumberFormatException | NullPointerException ex) {
        }

//        Integer.parseInt("abc"); throws NumberFormatException

        int i = goHome(); //13
        System.out.println(i); //-3

//        goHomeException(); //Exception in thread "main" java.lang.RuntimeException: finally

        System.out.println("\nTry-with-resources");
        System.out.println("""
                Example: 
                        try (FileInputStream is = new FileInputStream("myfile.txt");
                            var out = FileOutputStream.create(new File("myfile.txt"));) {
                            // Read file data\s
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                NOTE: The out is closed first. The resources are closed in reverse order.
                Try-with-resources does not requires catch or finally blocks.
                
                Only objects that implements AutoCloseable or Closeable can use the try-with-resources.
                """);


        try (MyFileClass x = new MyFileClass(1);
             MyFileClass x2 = new MyFileClass(2)) {
            System.out.println("try block");
        } finally {
            System.out.println("finnaly");
        }//try block, Closing 2, Closingn 1,finally

        MyFileClass y2 = new MyFileClass(1);
        MyFileClass y3 = new MyFileClass(1);
        try (y2;
//            y3; //DOES NOT COMPILE, MUST BE final or effectively final
             MyFileClass x2 = new MyFileClass(2)) {
            System.out.println("try block");
        } finally {
            System.out.println("finnaly");
        }//try block, Closing 2, Closingn 1,finally
        y3 = new MyFileClass(2);

        System.out.println("""
                Be careful with elements that is used after the try-with-resources.
                Example:
                var writer = Files.newBufferedWriter("path");
                writer.append("This write is permitted but a really bad idea!");
                try (writer){
                    writer.append("Welcome to the zoo!");
                   } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                writer.append("This write will fail!"); // IOException
                
                """);

        System.out.println("\nSuppressed exceptions");
        try (TurkeyCage t = new TurkeyCage()) {
            throw new IllegalStateException("Turkeys ran off internal");
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed())
                System.out.println("Suppressed: " + t.getMessage());
        } //Caught: Turkeys ran off internal Suppressed: Turkeys ran off
        System.out.println("""
                The close method can throw a checked exception, which should be handled by the try block.
                void close() throws Exception
                """);

        try (TurkeyCage t = new TurkeyCage()) {
            throw new RuntimeException("Turkeys ran off internal");
        } catch (IllegalStateException e) {
            System.out.println("Caught IllegalStateException: " + e.getMessage());
            for (Throwable t : e.getSuppressed())
                System.out.println("Suppressed: " + t.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed())
                System.out.println("Suppressed: " + t.getMessage());
        } //Caught: Turkeys ran off Suppressed: Turkeys ran off

        try{} catch (RuntimeException | Error e) {}
//        try{} catch (RuntimeException | Throwable e) {} //DOES NOT COMPILE
//        try{} catch (RuntimeException | Exception e) {} //DOES NOT COMPILE
        try {
            try (TurkeyCage t = new TurkeyCage()) {
                throw new IllegalStateException("Turkeys ran off");
            } finally {
                throw new RuntimeException("and we couldn't find them");
            }
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage()); //Caught: and we couldn't find them
            System.out.println("""
                    This has always been and continues to be bad programming practice. 
                    We don’t want to lose exceptions! Although out of scope for the exam,
                    the reason for this has to do with backward compatibility.
                    This behavior existed before automatic resource management was added.
                    """);
        }
    }

    public void test() throws IOException{

    }
    public static class TurkeyCage implements AutoCloseable {
        public void close() {
            throw new IllegalStateException("Turkeys ran off");
        }
    }

    public static class MyFileClass implements AutoCloseable {
        private final int num;

        public MyFileClass(int num) {
            this.num = num;
        }

        @Override
        public void close() {
            System.out.println("Closing: " + num);
        }
    }

    static void tryWithResources() throws IOException {
        try (FileInputStream is = new FileInputStream("myfile.txt")) {
            // Read file data
        }
    }


    static int goHomeException() {
        try {
            // Optionally throw an exception here
            System.out.print("1");
            return -1;
        } catch (Exception e) {
            System.out.print("2");
            return -2;
        } finally {
            System.out.print("3");
            throw new RuntimeException("finally");
        }
    }

    static int goHome() {
        try {
            // Optionally throw an exception here
            System.out.print("1");
            return -1;
        } catch (Exception e) {
            System.out.print("2");
            return -2;
        } finally {
            System.out.print("3");
            return -3;
        }
    }

    class Parent {
        void method() {
        }

        void method2() {
        }
    }

    class Child extends Parent {
        @Override
        void method() throws RuntimeException {
        }

//        @Override
//        void method2() throws Exception {} //DOES NOT COMPILE, cant add checked exception that does not contain in the parent
    }

    void handleCheckedException() {
        try {
            myMethod("myMethod");
            ;
        } catch (NullPointerException e) {
        } catch (Exception e) {
        }
//        catch (IOException ex) {} //DOES NOT COMPILE the parent does not throw this checked expcetion
//        catch (RuntimeException e) {} //DOES NOT COMPILE, never reach this code

    }

    //    void myMethod(String x) throw RuntimeException {} //DOES NOT COMPILE throw
    void myMethod(String x) throws RuntimeException {
    }

    void myMethod2(String x) throws RuntimeException {
//        throws new RuntimeException(); //DOES NOT COMPILE throws
        throw new RuntimeException();
    }
}
