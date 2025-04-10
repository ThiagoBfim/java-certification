package org.bomfim.chapter1;

public class GCBear {

    private GCBear panda;

    private void roar(GCBear b) {
        System.out.println(b);
        panda = b;
    }

    static float x;
    public static void main(String[] args) {
        System.out.println(x);
        GCBear a = new GCBear();
        GCBear b = new GCBear();
        b.roar(a);
        System.out.println(a);
        a = null;
        b = null;
        System.gc();
    }
}
