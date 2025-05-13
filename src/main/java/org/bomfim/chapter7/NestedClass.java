package org.bomfim.chapter7;

public class NestedClass {

    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B(); //Second level requires import or reference to the parent, A.B
        A.B.C c = b.new C();
        c.allTheX();
    }


    public static class A {
        private int x = 10;

        class B {
            private int x = 20;

            class C {
                private int x = 30;

                public void allTheX() {
                    System.out.println(x);   // 30
                    System.out.println(this.x); // 30
                    System.out.println(B.this.x);// 20
                    System.out.println(A.this.x);// 10

                }
            }
        }
    }

    public class F {
        public class D {
        }

        private class DPrivate {
        }

        public static void doesNotCompile(){
//            new DPrivate(); //DOES NOT COMPILE BECAUSE DPrivate is not static
        }

    }
    public class E {
        public void x() {
//          new F.D(); //DOES NOT COMPILE, D is not static
            new F().new D(); //COMPILE
            new F().new DPrivate(); //COMPILE same parent class
        }
    }
}