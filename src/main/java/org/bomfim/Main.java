package org.bomfim;

import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Integer x = 0;
        String result = switch (x){
            case 1 -> "ok";
            default -> "error";
//            case null -> "null"; //COMPILE error
        };
        System.out.println(result);
    }
}