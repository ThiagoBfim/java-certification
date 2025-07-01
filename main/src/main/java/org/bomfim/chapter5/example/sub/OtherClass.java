package org.bomfim.chapter5.example.sub;

import org.bomfim.chapter5.example.Father;

public class OtherClass {

    int x = 5;
    {
        x = 10;
        System.out.println("initialize block");
    }
    OtherClass() {
        x = 11;
        System.out.println("Constructor");
    }
    public static void main(String[] args) {
        var son = new Son();
//        son.getName(); //DOES NOT COMPILE
        son.getSuperName(); // COMPILE

//        son.x;
        Father f = new Son();
//        f.getName() //DOES NOT COMPILE

//        Father.x;
        System.out.println("before initialize");
        OtherClass otherClass = new OtherClass();
        System.out.println(otherClass.x);
    }
}
