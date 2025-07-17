1. C?                                                  ✓
2. A,E,F                                               ❌ //The delete() method returns false if the file or directory cannot be deleted.
3. B,D ?                                               ✓ //If the console is unavailable, System.console() will return null
4. C                                                   ❌ // Files.deleteIfExists() declares the checked IOException that must be handled or declared
5. C //walk vs find                                    ✓ Files.walk() returns a Stream<Path> Files.find() does also return a Stream<Path>, but it receives a BiPredicate<Path, BasicFileAttributes>
6. F                                                   ❌ //subclass can call a `this.name` with an attribute of super class
7. A,C ?                                               ❌ Tricky
8. F *                                                 ❌ //The readAllLines() method returns a List, not a Stream.

Files.lines() returns Stream<String>
Files.readAllLines returns List<String>

9. C,F,H ?                                             ❌
10. C,D,E *                                            ✓ // Path.get("cats","lynx.txt") Does not exist. It shoudl be Paths.get("cats","lynx.txt")
11. A,E ?                                              ✓
12. C,E ?                                              ✓
13. A,G                                                ✓
14. B,D,E *                                            ✓ Good question to review tricky questions
15. B,F*                                               ❌//Works: new File(new File("/weather/winter"), "snow.dat")
16. A,D,F*                                             ❌the character stream classes, which inherit from Reader or Writer.
17. B,C,F?                                             ❌//Files.readAttributes() is often more performant since it reads multiple attributes rather than accessing individual attributes.
18. D                                                  ❌ Vacilo
19. C *                                                ✓
20. D ?                                                ❌Vacilo
21. A ?                                                ❌Vacilo

Files.find(f, 0, (p,a) -˃ a.isSymbolicLink()))

This code will only check the root folder, because of the depth parameter "0"

22. G                                                 ❌
23. A,C,D                                             ❌ // Files.walk() does not follow symbolic links by default. Only if the FOLLOW_LINKS option is provided and a cycle is encountered will the exception be thrown.
24. B * CreateDirectories return a Path?              ✓ // Path createDirectories(Path dir) 
25. A,B ?


Result:

* Correct: 11
* Mistake: 14
* Score: 44%
* Passing Score: 68%
* Took: 40min