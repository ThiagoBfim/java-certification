1. C,D                                                  ❌ //A method that declares an exception isn’t required to throw one
2. F // METHOD signature **throws** Exception           ✓
3. A,D,E                                                ✓
4. E //Specific exception must be handled first         ✓
5. C,D,F ?? What is the default CompactNumberInstnace   ❌ //When a CompactNumberFormat instance is requested without a style, it uses the SHORT style by default
6. E -> what is the default for iso? yyyy-mm-dd??       ✓ The default for ISO_LOCAL_DATE is "2025-04-30"
7. D //12,17                                            ❌ Tricky question with variable names
8. C                                                    ✓
9. D ??                                                 ✓
10. B                                                   ✓
11. B,E                                                 ❌ //class Peach extends Throwable {} MUST BE HANDLED
12. B,C                                                 ✓
13. A,B                                                 ✓
14. B ?                                                 ❌ Tricky, Case both variable are null, the second one is the first cause of NPE, it is executed first. huey.substring(dewey.intValue())
15. A,B,C,D ?                                           ❌ Tricky
16. F // runtime exception                               ✓
17. D,E,F                                               ❌ Tricky
18. C                                                    ✓
19. D                                                    ✓ 
20. G                                                    ✓
21. C                                                    ✓
22. A                                                    ❌ //VACILO, m = minute, M = month
23. A,E                                                  ✓
24. C ??                                                 ❌Tricky, variable inside try-with-resource must be final or effectively final
25. A ?? //Locale.Builder().setRegion("us").build()      ✓
26. C,D,F?                                               ❌


    try{} catch (RuntimeException | Error e) {}
    //        try{} catch (RuntimeException | Throwable e) {} //DOES NOT COMPILE
    //        try{} catch (RuntimeException | Exception e) {} //DOES NOT COMPILE

Result:

* Correct: 16
* Mistake: 10
* Score: 61%
* Passing Score: 68%
* Took: 32min
