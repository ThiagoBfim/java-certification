1. D                                 ✓
2. F                                 ✓
3. E                                 ✓
4. A,B                               ✓
5. D,F*                              ❌ // Since groupingBy() is creating a Collection, we need a nonprimitive Stream.
6. A ?                               ✓ 
7. F                                 ✓
8. D,E ?                             ✓
9. B,D ? map vs mapTo...             ✓  //LongStream.of(1, 2, 3).map(n -˃ n * 10).findFirst().getAsLong()
10. F.                               ✓
11. B,C,E ??                         ✓
12. E                                ❌  // Stream.concat() takes two parameters
13. F //Stream<List<Integer>>        ✓
14. B,D.                             ✓
15. A,B ?                            ❌
16. B,C ?                            ✓
17. D.                               ✓
18. C ?                              ❌ //todo review it
19. B                                ✓
20. C,E,F ??                         ✓ //opt.orElseThrow(() -˃ throw new Exception()); DOES NOT COMPILE
21. B                                ✓

Result:

* Correct: 17
* Mistake: 4
* Score: 80%
* Passing Score: 68%
* Took: 33min
