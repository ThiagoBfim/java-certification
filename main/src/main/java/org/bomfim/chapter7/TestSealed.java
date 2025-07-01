package org.bomfim.chapter7;

public class TestSealed {

    public static void main(String[] args) {
        Penguim p = new Emperor2();
        print(p);
    }

    private static void print(Penguim p) {
        System.out.println("If Penguim is not abstract it must be on the swithc clause");
        switch (p) {
            case Emperor2 e -> System.out.println(e);
            case Emperor e -> System.out.println(e);
        }
    }

    public sealed static abstract class Penguim permits Emperor, Emperor2 {
    }


    public final class AnimalX extends Emperor {
    }

    public static final class Emperor2 extends Penguim {
    }


    public sealed class Emperor extends Penguim permits AnimalX {
    }
}
