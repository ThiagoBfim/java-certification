package org.bomfim.chapter5.example;

//import static java.util.Arrays; //DOES NOT COMPILE
import static java.util.Arrays.asList;
//static import java.util.Arrays.asList; //DOES NOT COMPILE
//static import java.util.Arrays; //DOES NOT COMPILE

public class Static {

    static int count = 0;
    public static void main(String[] args) {
        var s = new Static();
        s.count++;
        s = null;
        System.out.println(s.count);


        Static x = null;
        System.out.println(x.count);

        asList("a", "b");
    }
}
