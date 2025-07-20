package org.bomfim.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test8 {

    public static void main(String[] args) {
        List<Integer> x = List.of(1, 2, 3);
//        x.sort((a,b) -> a.compareTo(b)); //.UnsupportedOperationException
        Object[] array = x.toArray();

        char[][] ca = {{'a', 'b', 'c'}, {'d', 'e', 'f'}};
        System.out.println(Arrays.deepToString(ca));

//        List<? extends Number> numbers = new ArrayList<>();
//        numbers.add(5);

        List<? super Number> numbers2 = new ArrayList<>();
        numbers2.add(5);
        numbers2.add(5.5);

        try {
            Integer.parseInt("1.25");
        } catch (NumberFormatException e) {
            System.out.println("Error parse int: " + e.getMessage());
        }

        System.out.println(Y.x);

        int k = 2;
        k += (k = 4) * (k + 2); //4*6
        System.out.println(k); //26
        k = (k = 4) * (k + 2); //4*6
        System.out.println(k); //24



        var list = new ArrayList<>();
        list.add(new Y());
        print(1,2);
        print(1,2.0);
    }

    public static void print(int x, double b){
        System.out.println(x + b);
    }
    public static void print(int x, float b){
        System.out.println(x + b);
    }

    static class Y implements X{ }

    static interface X {
       static final int x = 5;
    }
}
