1.  F                ✓
2.  A,B,C,D          ❌ SWITCH DOES NOT SUPPORT LONG
3.  B                ✓
4.  A,D,F  *         ✓
5.  F *              ✓ //compile error? 
6.  F *              ❌ for (Object value : new int[10]) {} //COMPILES
7.  C,D              ❌ for (int i=0; i<=v.length-1; i++) TEM O MESMO RESULTADO QUE for (int i=0; i<=v.length-1; ++i)
8.  G                ✓ 
9.  B,D,E            ❌ hard/vacilo
10. C                ❌ hard/vacilo
11. A *              ✓
12. C                ✓
13. E *              ❌ Switch case clause uses the logical complement operator (!), which is not permitted with pattern matching
14. G ?              ✓ WHILE requires () //do {} while true //DOES NOT COMPILE
15. A,B, D,F *       ❌ LEITURA DO ENUNCIADO
16. F                ✓. "CASE `B`:`C`:"
17. A,B,D            ✓
18. B,D              ❌ medium/vacilo
19. E                ✓
20. A,E              ✓
21. C*               ❌ trick //{yield "Lisa";}; DOES NOT COMPILE
22. E *.             ✓
23. F *.             ✓
24. E //25.          ❌ vacilo
25. C*               ❌
26. F*               ✓
27. D* take notes    ✓
28. B *              ❌ !(fish instanceof String guppy) && !(fish instanceof String guppy) does not compile same variable name
29. C *              ✓
30. B                ❌ 

Result:

* Correct: 17
* Mistake: 13
* Score: 56,6%
* Passing Score: 68%
* Took: 45min