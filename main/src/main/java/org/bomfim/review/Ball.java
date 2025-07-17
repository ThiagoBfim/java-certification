package org.bomfim.review;

public class Ball<X> {
//    public static <T> void catchBall(T t, X x) {} //DOES NOT COMPILE
    public <T> void dribbleBall(T t, X x) {}
//    public <X> static void fetchBall(X t, X x) {} //DOES NOT COMPILE
    public <X> void inflateBall(X t, X x) {}
//    public <T> static void spinBall(T t, X x) {}
    public static <X> void throwBall(X t, X x) {}
//    public <X> static void throwBall(X t, X x) {}
}