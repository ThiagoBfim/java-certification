package org.bomfim.chapter1;

public class Box {

    static {System.out.println("Static block");}
    {System.out.println("Block");}
    public void Box() {
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
