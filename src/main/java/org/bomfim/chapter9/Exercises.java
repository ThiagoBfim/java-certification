package org.bomfim.chapter9;

import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Exercises {

    public static void main(String[] args) {
        list();
        hashSet();
        linkedHashSet();
        treeSet();
        queueAndDeque();
        map();
        comparable();
        sequencedCollectionsAndMap();
        generics();
    }

    private static void generics() {
        System.out.println("\nGenerics");
        System.out.println("""
                Be careful, we cannot Overload a generic method, it does not compile.
                public class Animal {
                //protected void run(List<Object> list) {} //does not compile
                //protected void run(List<String> list) {} //does not compile
                  protected void run(List<Double> list) {}
                  protected void run(ArrayList<Double> list) {}
                }
                For the exam, it might be helpful for you to apply type erasure to questions involving generics to ensure that they compile properly.
                We cannot override a generic method changing the TYPE.
                
                public class Mammal {
                        public List<CharSequence> play() {
                            return List.of("Squeak", "Roar");
                        }
                    }
                
                    public class Monkey extends Mammal {
                        public ArrayList<CharSequence> play() {
                            return new ArrayList<>();
                        }
                    }
                
                    public class Goat extends Mammal {
                //        public List<String> play() {}// DOES NOT COMPILE
                    }
                Generic methods cannot be overloaded by changing the generic parameter type only.
                """);
        More<String> stringMore = new More<>();
        System.out.println(stringMore.getValue("A"));
        System.out.println(stringMore.getValue2(1));
        List<CharSequence> list = List.of("a", "b", "c");
//        printList(list); //DOES NOT COMPILE - generic type cannot be converted to another type
        printList2(list);
        printList3(list);
        printList4(list);
//        List<Object> list2 = list; //DOES NOT COMPILE - generic type cannot be converted to another type

        List<? extends Bird> birds = new ArrayList<>();
//        birds.add(new Sparrow()); //COMPILE ERROR
//        birds.add(new Bird());//COMPILE ERROR
        List<? extends Bird> birds2 = List.of(new Sparrow()); //COMPILES
        List<? extends Bird> birds3 = List.of(new Bird()); //COMPILES

        List<Bird> birds1 = new ArrayList<>(List.of(new Bird()));
        List<Object> objects = new ArrayList<>(List.of(new Object()));
        List<String> strings = new ArrayList<>(List.of("A"));
        add(birds1);
        add(objects);
        add(new ArrayList<>(List.of("A"))); //COMPILE
//        add(strings); //DOES NOT COMPILE

        List<?> list1 = new ArrayList<A>();
//       list1.add(new A()); //DOES NOT COMPILE
//       list1.add(new B()); //DOES NOT COMPILE
//       list1.add(new C()); //DOES NOT COMPILE
        List<? extends A> list2 = new ArrayList<A>();
//       list2.add(new A()); //DOES NOT COMPILE
//       list2.add(new B()); //DOES NOT COMPILE
//       list2.add(new C()); //DOES NOT COMPILE
        List<? super A> list3 = new ArrayList<A>();
        list3.add(new A());
        list3.add(new B());
        list3.add(new C());
//        List<? extends B> list4 = new ArrayList<A>(); // DOES NOT COMPILE
//        List<? super B> list5 = new ArrayList<A>();
//        List<?> list6 = new ArrayList<? extends A>(); // DOES NOT COMPILE
    }

    //    <B extends A> B third(List<B> list) { return new B();} // DOES NOT COMPILE
    <D extends A> B third(List<B> list) { return new B();} //COMPILE
//    <? super A> B thirdA(List<B> list) { return new B();} //DOES NOT COMPILE - Uses type with super
    <A> B thirdA(List<? super B> list) { return new B();}
//    <A> B thirdC(List<A super B> list) { return new B();} //DOES NOT COMPILE because it tries to mix a method-specific type parameter with a wildcard.
//    <A> B thirdC(List<A extends B> list) { return new B();} //DOES NOT COMPILE

    static class A {
    }

    static class B extends A {
    }

    static class C extends B {
    }

    public static void add(List<? super Bird> obejcts) {
        obejcts.add(new Bird());
        System.out.println(obejcts);
    }

    static class Sparrow extends Bird {
    }

    private static class Bird {
    }

    public static void printList(List<Object> objects) {
        System.out.println(objects);
    }

    public static void printList2(List<? extends Object> objects) {
        System.out.println(objects);
    }

    public static void printList3(List<? super String> objects) {
        System.out.println(objects);
    }

    public static void printList4(List<?> objects) {
        System.out.println(objects);
    }

    public static class More<T> {
        public static <T> void sink(T t) {
        }

        public static <T> T identity(T t) {
            return t;
        }

        public T getValue(T t) {
            return t;
        }

        public <T> T getValue2(T t) {
            return t;
        }


//        public static T noGood(T t) {return t;} // DOES NOT COMPILE
    }

    public class Mammal {
        public List<CharSequence> play() {
            return List.of("Squeak", "Roar");
        }

    }

    public class Monkey extends Mammal {
        public ArrayList<CharSequence> play() {
            return new ArrayList<>();
        }
    }

    public class Goat extends Mammal {
//        public List<String> play() {}// DOES NOT COMPILE
    }

    public static class Animal {
        //        protected void run(List<Object> list) {} //does not compile
//        protected void run(List<Integer> list) {} //does not compile
        protected void run(List<Double> list) {
        }

        protected void run(ArrayList<Double> list) {
        }
    }

    private static void sequencedCollectionsAndMap() {
        System.out.println("\nSequenced Collections");
        System.out.println("""
                Be careful: TreeSet does not support addLast or addFirst methods.
                treeSet.addFirst(); // Runtime Exception
                * If the list is empty the removeLast and removeFirst methods throw an exception.
                """);
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(2);
//        treeSet.addFirst(1); //UnsupportedOperationException
//        treeSet.addLast(3); //UnsupportedOperationException
        treeSet.removeFirst();
        treeSet.removeLast();
//        treeSet.removeLast(); //java.util.NoSuchElementException if the element does not exist
        System.out.println("""
                HashMap and HashSet does not implement SequencedCollection or SequencedMap
                """);

        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);
        list.add("c");
        System.out.println(unmodifiableList);

        System.out.println("For sorted sets, that means null is not permitted; and for sorted maps, this means null keys are not permitted.");
    }

    private static void comparable() {
        System.out.println("\nComparable");
        System.out.println("""
                public interface Comparable<T> {
                  int compareTo(T o);
                }
                TreeSet and TreeMap classes have to implement the Comparable, or pass a Comparator to the constructor.
                public interface Comparator<T> {
                  int compare(T o1, T o2);  //0 is equal to / 1 is bigger then / -1 is smaller then
                }
                BE CAREFUL: Comparator method is compare()
                Comparable method is compareTo()
                """);

        List<MyClass> classes = new ArrayList<>();
        classes.add(new MyClass("a"));
        classes.add(new MyClass("b"));
//        Collections.sort(classes); //DOES NOT COMPILE -> MyClass does not implements Comparable
        Collections.sort(classes, new Comparator<MyClass>() {
            @Override
            public int compare(MyClass o1, MyClass o2) {
                return 0;
            }
        });
        Collections.sort(classes, Comparator.comparing(MyClass::getName));

        Comparator.comparing(MyClass::getName);
        Comparator.comparingDouble(new ToDoubleFunction<Object>() {
            @Override
            public double applyAsDouble(Object value) {
                return 0;
            }
        });
        Comparator.comparingInt(new ToIntFunction<Object>() {
            @Override
            public int applyAsInt(Object value) {
                return 0;
            }
        });
        Comparator.comparingLong(new ToLongFunction<Object>() {
            @Override
            public long applyAsLong(Object value) {
                return 0;
            }
        });


        List<Integer> list = Arrays.asList(6, 9, 1, 8);
        Collections.sort(list); // [1, 6, 8, 9]
        System.out.println(Collections.binarySearch(list, 1)); // 0
        System.out.println(Collections.binarySearch(list, 6, Comparator.reverseOrder())); // 1 - Coincidence it is sorted in the reserve order
        System.out.println(Collections.binarySearch(list, 1, Comparator.reverseOrder())); // -5 - It is not in the ascending order
        System.out.println(Collections.binarySearch(list, 3)); // -2
        System.out.println(Collections.binarySearch(list, 4)); // -2
        System.out.println(Collections.binarySearch(list, 7)); // -3

        System.out.println("\nAnother list");
        List<String> names = Arrays.asList("Fluffy", "Hoppy");
        Comparator<String> c = Comparator.reverseOrder();
        System.out.println(Collections.binarySearch(names, "Hoppy", c));//-1
    }

    private static void map() {
        System.out.println("\nMAP");
        System.out.println("""
                HashMap -> Does not Keep order
                LinkedHashMap -> Keeps order
                TreeMap -> is sorted
                """);
        System.out.println("""
                Be careful:
                * map.contains("lion") // DOES NOT COMPILE -> Map does not have contains method
                * map.merge(K,V, BiFunction<V,V>) -> If the previous values is null the BiFunction is not called.
                * map.merge with a null value causes a NPE
                * map.merge with a null BiFunction causes a NPE
                """);
        System.out.println("""
                Map<String, String> favorites = new HashMap<>();
                favorites.put("Jenny", "Bus Tour");
                favorites.put("Tom", "Bus Tour");
                favorites.put("Tom2", null);
                //  favorites.merge("Jenny", "Skyride", null); //NPE
                //  favorites.merge("Tom2", null,  (v1, v2) -> null); //NPE
                favorites.merge("Tom", "Skyride",  (v1, v2) -> null);
                favorites.merge("Sam", "Skyride",  (v1, v2) -> null);
                System.out.println(favorites); // {Tom2=null, Jenny=Bus Tour, Sam=Skyride}
                """);

        HashMap map = new HashMap<String, String>();
        map.put(1, 1); //COMPILES and works
        map.put(null, null); //COMPILES and works
        map.put(1, 1); //COMPILES and works
        map.put("a", "a");
        System.out.println(map);
        TreeMap<String, String> sortedMap = new TreeMap<>();
        sortedMap.put("b", "b");
        sortedMap.put("c", "c");
        sortedMap.put("a", "a");
//        sortedMap.put(null, "a"); //NPE
        sortedMap.merge("a", "x", (a, b) -> a + b); //a=ax
        String s = sortedMap.putIfAbsent("d", "d"); //s = null
        String s2 = sortedMap.putIfAbsent("d", "d100"); //s2 = d
        System.out.println("Result after first putIfAbsent: " + s);
        System.out.println("Result after second putIfAbsent: " + s2);
        String oldValue = sortedMap.replace("d", "d2"); //oldValue = d
        System.out.println("Result after replace method: " + oldValue);
        boolean replace = sortedMap.replace("d", "d2", "d3"); //true
        System.out.println("sortedMap.replace(\"d\", \"d2\", \"d3\"): result = " + replace);
        sortedMap.merge("e", "e", (a, b) -> {
            if (true) {
                throw new RuntimeException("ac"); //NOT executed, Key "e" does not exist on the map
            }
            return a + b;
        });
//        sortedMap.merge("a", null, (oldV,newV) -> null); //NPE
//        sortedMap.merge("a", null, (oldV,newV) -> oldV); //NPE
        System.out.println(sortedMap);


        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Bus Tour");
        favorites.put("Tom2", null);
//        favorites.merge("Jenny", "Skyride", null); //NPE
//        favorites.merge("Tom2", null,  (v1, v2) -> null); //NPE
        favorites.merge("Tom", "Skyride", (v1, v2) -> null);
        favorites.merge("Sam", "Skyride", (v1, v2) -> null);
        System.out.println(favorites); // {Tom2=null, Jenny=Bus Tour, Sam=Skyride}
    }

    private static void queueAndDeque() {
        System.out.println("\nFIFO: First In First Out");
        System.out.println("""
                Queue is a collection that holds elements in a FIFO (first-in-first-out) order. 
                boolean add(E e); //add an element to the end of the queue
                boolean offer(E e); //add an element to the end of the queue
                E element() //Read the element at the head of the queue
                E peek()  //Read the element at the head of the queue
                E remove() //Remove and return the element at the head of the queue
                E poll() //Remove and return the element at the head of the queue or null if the queue is empty
                """);
        Queue<Integer> q = new LinkedList<>();
        q.add(10);
        q.add(4);
        q.peek();
        q.remove(11);
        q.remove(10);
        q.poll();
        q.poll();
        System.out.println(q); //[]

        System.out.println("\nLIFO: Last In First Out");
        System.out.println("""
                Deque is a collection that supports LIFO and FILO
                void addFirst(E e); //add an element to the beginning of the queue
                boolean offerFirst(E e); //add an element to the  beginning of the queue
                void addLast(E e); //add an element to the end of the queue
                boolean offerLast(E e); //add an element to the end of the queue
                E getFirst(); //Read the element at the head of the queue
                E peekFirst();  //Read the element at the head of the queue
                E getLast();  //Read the element at the tail of the queue
                E peekLast();  //Read the element at the tail of the queue
                E removeFirst(); //Remove and return the element at the head of the queue
                E pollFirst(); //Remove and return the element at the head of the queue or null if the queue is empty
                E removeLast(); //Remove and return the element at the tail of the queue
                E poolLast(); //Remove and return the element at the tail of the queue
                """);

        Deque<Integer> dq = new LinkedList<>();
        dq.addFirst(10); //[10]
        dq.addFirst(11);  //[11,10]
        dq.addLast(3); //[11,10,3]
        dq.addLast(4); //[11,10,3,4]
        dq.peekFirst(); //11
        dq.removeFirst(); //[10,3,4]
        dq.removeLast(); //[10,3]
        dq.pollLast(); //[10,3]
        System.out.println(dq); //10

        System.out.println("""
                Using Deque as a stack
                void push(E e); //add an element to the top of the stack
                E pop(); //remove and return the element at the top of the stack
                E peek(); //Read the element at the top of the stack
                """);
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3); //[3,2,1]
        stack.pop();
        stack.peek(); //2
        System.out.println(stack); //[2,1]
    }

    private static void linkedHashSet() {
        System.out.println("\nLinkedHashSet");
        Set<String> x = new LinkedHashSet<>();
        System.out.println(x.add("b")); //true
        System.out.println(x.add("a")); //true
        System.out.println(x.add("a")); //false
        System.out.println(x); //[b,a]

        Set<MyClass> y = new LinkedHashSet<>(); //DOES not implement hashcode and equals

        System.out.println(y.add(new MyClass("a"))); //true
        System.out.println(y.add(new MyClass("b"))); //true
        System.out.println(y.add(new MyClass("a"))); //true
        System.out.println(y); //[a,b,a]
    }

    private static void treeSet() {
        System.out.println("\nTreeSet");
        Set<String> x = new TreeSet<>();
//        System.out.println(x.add(null)); //ClassCastException cannot be cast to class java.lang.Comparable
        System.out.println(x.add("b")); //true
        System.out.println(x.add("a")); //true
        System.out.println(x.add("a")); //false
        System.out.println(x); //[a,b]

        Set<MyClass> y = new TreeSet<MyClass>(); //DOES not implement hashcode and equals
//        System.out.println(y.add(new MyClass("a")));//ClassCastException cannot be cast to class java.lang.Comparable

        System.out.println("""
                In case a TreeSet is from an object that does not extends Comprable, it thrown an exception:
                ClassCastException cannot be cast to class java.lang.Comparable
                Set<MyClass> y = new TreeSet<MyClass>(); //COMPILE
                y.add(new MyClass("a")); //ClassCastException cannot be cast to class java.lang.Comparable
                """);
    }

    private static void hashSet() {
        System.out.println("\nHashSet");
        Set<String> x = new HashSet<>();
        System.out.println(x.add("b")); //true
        System.out.println(x.add("a")); //true
        System.out.println(x.add("a")); //false
        System.out.println(x); //[a,b] ?

        Set<MyClass> y = new HashSet<>(); //DOES not implement hashcode and equals

        System.out.println(y.add(new MyClass("a"))); //true
        System.out.println(y.add(new MyClass("b"))); //true
        System.out.println(y.add(new MyClass("a"))); //true
        System.out.println(y); //[a,b,a]
    }

    static class MyClass {
        private final String name;

        MyClass(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static void list() {
        var list = new ArrayList<>();
        System.out.println(list.add(1));
        System.out.println(list.add(1));
        System.out.println(list.add("a"));
        var set = new HashSet<>();
        System.out.println(set.add(1));
        System.out.println(set.add(1)); //false, don't insert the duplicated item
        System.out.println(set.add("a"));
        var x = new ArrayList<>();

        System.out.println("""
                Be careful, adding null to a list and getting this element causes a NPE
                var heights = new ArrayList<Integer>();
                heights.add(null);
                int h = heights.get(0); // NullPointerException
                """);
        var heights = new ArrayList<Integer>();
        heights.add(null);
//        int h = heights.get(0); // NullPointerException

        System.out.println("""
                The main benefits of a LinkedList are that you can access, add to, and remove from the beginning and end of the list in constant time. 
                The trade-off is that dealing with an arbitrary index takes linear time.
                This makes a LinkedList a good choice when you’ll be using it as Deque.
                """);

        List<Integer> list2 = new LinkedList<Integer>();
        list2.add(3);
        list2.add(2);
        list2.add(1);
        list2.remove(2); //1
        list2.remove(Integer.valueOf(2));
        list2.remove(Integer.valueOf(100)); //false
//        list.remove(100); IndexOfBoundException
        System.out.println(list2); //3
    }
}
