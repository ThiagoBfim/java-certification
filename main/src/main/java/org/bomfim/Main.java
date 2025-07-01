package org.bomfim;

public class Main {

    public static void main(String[] args) {

        Integer x = 0;
        String result = switch (x){
            case 1 -> "ok";
            default -> "error";
//            case null -> "null"; //COMPILE error
        };
        System.out.println(result);

        var obj = new Object();
        Object result2 = getResult2("a");

        System.out.println(result2);
    }

    private static Object getResult2(Object obj) {
        return switch (obj) {
            case null -> null;
            case String s -> s.length();
            case int[] ia -> ia.length;
            default -> "obj is something else";
        };
    }
}