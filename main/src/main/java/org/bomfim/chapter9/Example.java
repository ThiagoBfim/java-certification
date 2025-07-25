package org.bomfim.chapter9;

import java.util.*;

public class Example {

    public record Sorted(int num, String text)
            implements Comparable<Sorted>, Comparator<Sorted> {

        public String toString() {
            return "" + num;
        }

        public int compareTo(Sorted s) {
            return text.compareTo(s.text);
        }

        public int compare(Sorted s1, Sorted s2) {
            return s1.num - s2.num;
        }

        public static void main(String[] args) {
            var s1 = new Sorted(88, "a");
            var s2 = new Sorted(55, "b");
            SequencedSet<Sorted> t1 = new TreeSet<Sorted>();
            t1.add(s1);
            t1.add(s2);
//            var a = new TreeSet<String>("a");
            var t2 = new TreeSet<Sorted>(s1);
            t2.add(s1);
            t2.add(s2);
            System.out.println(t1 + " " + t2);
            ArrayList<? super Number> list = new ArrayList <Object>();
        }
    }

    public record Hello<T>(T t) {
        public Hello(T t) {
            this.t = t;
        }

        private <T> void println(T message) {
            System.out.print(t + "-" + message);
        }

        private static <T> void println2(T message) {
            System.out.print(message);
        }
    }

    public static class MyComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return b.toLowerCase().compareTo(a.toLowerCase());
        }

        public static void main(String[] args) {
            String[] values = {"123", "Abb", "aab"};
            Arrays.sort(values);
            System.out.println(Arrays.toString(values));
            Arrays.sort(values, new MyComparator());
            for (var s : values)
                System.out.print(s + " ");
        }
    }
}
