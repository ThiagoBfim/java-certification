1. A,E                              ✓
2. B,C                              ✓
3. A,D                              ✓
4. A,B,C,E                          ✓
5. A,C,D                            ✓
6. A,B,F                            ✓
7. D,F                              ✓
8. D                                ✓
9. B,C,D,F                          ✓
10. B *                             ✓ //import static rope.Rope.*; COMPILES
11. B,E  - line 10                  ✓
12. B  - giraffe, name              ✓
13. D *                             ✓ initialize block is called after new instance of the object, before the constructor
14. E * - 4,8,11,12                 ✓
15. B                               ✓
16. D *                             ❌
17. E - line 5                      ❌
18. B,D,E                           ✓
19. B,C,E                           ✓
20. A,E                             ✓
21. B,D                             ✓


Result:

* Correct: 19
* Mistake: 2
* Score: 90%
* Passing Score: 68%
* Took: 30min