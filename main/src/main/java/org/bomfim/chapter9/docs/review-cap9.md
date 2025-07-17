1. A,F
2. C,G
3. B
4. A,B,F* //HashSet˂Number˃ hs = new HashSet˂Integer˃(); DOES NOT COMPILE
5. D*
```
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
```
6. B,D,F*
7. B,E,F
8. B - Trick
9. B *  Trick
10. A,B,D*
11. A,B,D,F //map.keySet()
12. C,D,E*

List˂Exception˃ list = new LinkedList˂java.io.IOException˃() //DOES NOT COMPILE
ArrayList˂? super Date˃ list = new ArrayList˂Date˃() //COMPILE
ArrayList ˂? extends Number˃ list = new ArrayList ˂Integer˃() //COMPILE
ArrayList<? extends Number> list = new ArrayList <Number>(); //COMPILE
ArrayList<? super Number> list = new ArrayList <Object>(); //COMPILE


13. D - very Trick
14. A
15. A,B
16. A,B,C* C?
17. E
18. C,E //VACILO
19. B
20. F*
21. B,D,F*
22. B
id = snow
23. H
