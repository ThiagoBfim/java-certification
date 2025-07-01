1. E                                                  ✓
2. B                                                  ✓
3. G - commadn incorret, otherwise F                  ✓
4. D                                                  ✓
5. E,F                                                ✓
6. A,F * use/uses                                     ✓ //The uses directive declares that an API is called.
7. A,B,C,E*                                           ❌ //emily_the_cat-1.0.0.jar - emily.the.cat
8. A,D                                                ✓
9. E* //provides magic.Magic using dragon.Dragon;     ❌    provides magic.Magic with dragon.Dragon;
10. B,D*                                              ❌

`module com.food.supplier {}`\
* No packages inside the module are automatically exported.
* A main method inside the module can be run.

11. D,G*                                              ❌ //Vacilo -  requires transitive
12. A,B,C,F                                           ✓
13. B *                                               ❌
14. C *                                               ✓ // java --module-path x-x -m n/c Note that x-x is legal because the module path is a folder name, so dashes are allowed.
15. B                                                 ✓
16. A ? Tricky?                                       ✓
17. E ? unnamed module                                ✓ //An unnamed module can access an automatic module
18. F                                                 ✓
19. E - why not A and D?                              ✓ //Tricky
```
A module definition uses the keyword module rather than class

class dragon {
exports com.dragon.fire;
exports com.dragon.scales to castle;
}
```

20. A ??                                               ✓
21. D                                                  ❌ //No modules need to specify requires on the service provider since that is the implementation
22. A,E                                                ❌ //An unnamed module exports no packages to named modules.
23. E //exports com.snake.fangs to bird;               ✓
24. A                                                  ✓
25. A,C*                                               ❌ Service locator must specify that it uses the service provider interface to look it up.

Result:

* Correct: 17
* Mistake: 8
* Score: 68%
* Passing Score: 68%
* Took: 27min