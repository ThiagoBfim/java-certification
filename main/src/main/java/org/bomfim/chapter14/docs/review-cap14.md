1. C
2. A,F
3. A,B,F
4. C
5. C ?
walk vs find
walk.filter((p,a))
6.  C *
7. B,C
8. F*
9. D,G,H
10. C,D,E*
11. B,E*
12. C,E*
13. A,G
14. A,D,E*


var p1 = Path.of("/zoo/./bear","../food.txt");
p1.normalize().relativize(Path.of("/lion"));
System.out.println(p1); ///zoo/./bear/../food.txt

var p2 = Path.of("/zoo/animals/bear/koala/food.txt");
System.out.println(p2.subpath(1,3).getName(1)); //bear

var p3 = Path.of("/pets/../cat.txt");
var p4 = Path.of("./dog.txt");
System.out.println(p4.resolve(p3)); //  /pets/../cat.txt

/zoo/food.txt
p1 = ../../lion

15. B,E,F
16. A,D*
17. C,D,E*
18. C*

PEOE

19. C
T -
F -
-1
20. C
21. C*
22. G - stripes
23. E,F*
24. B
25. A,C
BufferedInputStream
BufferredReader?
FileInputStream