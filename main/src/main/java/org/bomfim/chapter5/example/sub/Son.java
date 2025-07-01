package org.bomfim.chapter5.example.sub;

import org.bomfim.chapter5.example.Father;

public class Son extends Father {
    protected void getSuperName() {
        super.getName();
        System.out.println(x);
    }

//    @Override
//    public void getName() { //COMPILES
//        super.getName();
//    }

    public void createParent() {
//        new Father().getName(); //DOES NOT COMPILE
    }

    public void createSonToParent() {
        Father x = new Son();
//        x.getName(); //DOES NOT COMPILE
    }

    public void createSon() {
        Son x = new Son();
        x.getName(); //DOES NOT COMPILE
    }
}
