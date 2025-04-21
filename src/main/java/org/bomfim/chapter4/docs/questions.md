1. F - Line 5                     ✓
2. C,E                            ❌
3. A,C,D,E                        ❌  //Missing attention LocalDate.of(2029, 2, 29)
4. A,C,D                          ✓
5. B abbaccca                     ✓
6. D - 24,25,26                   ❌
7. A,E.                           ✓
8. A,B,F - E string.length() *.   ✓   string.length()
9. A,C,G                          ❌. //Calling equals() on two different arrays containing the same primitive values always returns false.
10. A - 0                         ✓
11. E.                            ✓ //LocalDate.plusHours
12. A,D,E                         ✓
13. B                             ✓
14. B,F *                         ✓
15. C,F.                          ❌  //VACILO
16. A,G.                          ✓
17. C,E, F ** E?                  ❌
18. A,B,D *                       ✓
19. A,C ??                        ❌
20. A,C                           ✓
21. A.                            ✓
22. E.                            ✓


Result:

* Correct: 15
* Mistake: 7
* Score: 68,1%
* Passing Score: 68%
* Took: 35min