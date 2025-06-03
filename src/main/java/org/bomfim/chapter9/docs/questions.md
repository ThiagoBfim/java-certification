1. A,F                                            ✓
2. C,F //15,16                                    ❌ //VACILO List.of(1,2).removeIf(i % 2 == 0) //Runtime exception, immutable list 
3. B                                              ✓
4. B,C,D,F ?                                      ❌ //List˂˃ list = new ArrayList˂String˃(); DOES NOT COMPILE the diamond operator is allowed only on the right side\
                                                     //List˂Object˃ values = new HashSet˂Object˃(); //DOES NOT COMPILE - TRICKY List != SET
5. F * line 4?                                    ❌ //REVIEW IT
6. B,F                                             ✓
7. B,F                                             ✓
8. B.                                             ❌ //TRICKY question There is no get(int) method defined in SequencedCollection
9. D                                              ❌ //VACILO
10. A,D                                           ❌
11. A,B,E,F ? //keySet or keys()                  ✓
12. B,C,E                                         ❌  List˂?˃ list = new ArrayList˂?˃() //COMPILE ERROR - the wildcard is not allowed to be on the right side of an assignment
13. E                                             ❌ // new TreeSet˂Integer˃(1); DOES NOT COMPILE
14. A ?                                           ✓
15. A,B ?                                         ✓
16. A,B,C *                                       ❌
17. E //containsKey or containsValue              ✓
18. B,F //49, 56                                  ❌ //VACILO
19. B * //TAKE NOTES                              ✓
20. F                                             ✓
21. B,D,F* //TAKE NOTES                           ✓
22. B                                             ✓
23. H //addFirst                                  ✓



Result:

* Correct: 13
* Mistake: 10
* Score: 56%
* Passing Score: 68%
* Took: 55min