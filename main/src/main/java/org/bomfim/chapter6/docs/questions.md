1. E.                            ✓
2. A,B,F                         ✓
3. B,C *                         ✓
4. F                             ✓
5. E*                            ✓ //super.numSpots = numSpots; //COMPILES
6. D,E                           ✓
7. A*                            ✓
8. D                             ✓
9. A,B,E                         ❌
10. A,C                          ✓
11. C                            ✓
12. B*                           ❌ //No constructor. Override static method without static does not work
13. A,G                          ✓
14. B,D,E,F*                     ❌ //Vacilo
15. C.                           ✓
16. D                            ✓
17. C,E,F                        ❌  Option E is tricky. The default constructor is written by the compiler only if no user‐defined constructors were provided. And this() can only be called from a constructor in the same class
18. D,E,                         ❌ The dance() method in Mammal is correctly overloaded in the Monkey class because the signatures are not the same, making option E incorrect and option F correct
19. B                            ❌ //Vacilo
20. B, //9,14                    ❌ //The overriden method must have the same or more restrictive type. Example: If the superclass returns Number the subclass can return Integer, the opposite is not true.
21. C,E,F * //G.                 ❌ //ENGLISH
22. B                            ❌ //Tricky + vacilo
23. B                            ✓
24. C                            ✓
25. B,C ??? E                    ✓ //A concrete subclass only needs to override the inherited abstract method, not match the declaration exactly. For example, a covariant return type can be used. For this reason, option E is incorrect.
26. B                            ❌ //Vacilo


Result:

* Correct: 16
* Mistake: 10
* Score: 61%
* Passing Score: 68%
* Took: 50min