1. A,B,D,E                                      ❌ Vacilo
2. A,B,D,E                                      ✓
3. C                                            ❌ public constructor for enum && When an enum contains any other members, such as a constructor or variable, the semicolon is required
4. B                                            ❌ A class extending a sealed class must be marked final, sealed, or non-sealed.
5. C                                            ❌ Vacilo - Overloaded method instead of overriding
6. D,F -                                        ❌ Vacilo - Implements vs extends for interface
7. E -                                          ✓ override interface method should be public
8. B,D,E,G ?                                    ❌ Vacilo - Same variable name
9. A,E,F                                        ✓ 
10. A,B,C,E                                     ✓ private method is not inherited in any of its subtypes
11. B *                                         ✓
12. A,E,F,I*                                    ❌
13. G ?                                         ✓
14. A,D,E*                                      ❌ A sealed interface restricts which subinterfaces may extend it.
                                                   A sealed class can be extended by an abstract class.
                                                   A sealed interface restricts which subclasses may implement it.
15. F ?? final class with anonymous sub class?  ✓
16. C                                           ❌ Vacilo
17. D,E,G*                                      ❌ interface can have public static methods
18. E*                                          ✓
19. F                                           ✓
20. H?                                          ✓
21. C                                           ❌ In case of constructor with {} for records, it must call the this() at the first line.
22. C,D,F                                       ❌
23. F ?                                         ❌ //Works: Swim.super.perform()
24. B,E                                         ✓
25. F ? line 5                                  ❌ abstract class Stripes{} inside a method is allowed
26. C                                           ✓
27. B,C,D,G                                     ✓
28. B,D *                                       ❌ Vacilo
29. F - 5,12,15                                 ✓
30. C,E *                                       ✓

Result:

* Correct: 14
* Mistake: 16
* Score: 46,6%
* Passing Score: 68%
* Took: 51min