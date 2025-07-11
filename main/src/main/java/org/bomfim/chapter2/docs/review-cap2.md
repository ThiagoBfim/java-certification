
To review:
3

4 -  line 3 and 5
5 - add this note

8 - LINE 5 ?

10
13
16

sun - t
r - f
s - t
goStore = t & f ^ t
goZoo = t && true
home = ! (goStore && goZoo)
t-t-f

int ticketsSold = 5;
ticketsSold = (long) 1; //does not compile
ticketsSold += (long)1; //compiles

7
4
4+8


### Assignment operators

    long ear = 10;
    int hearing = 2 * ear; //does not compile ->  ear is automatically promoted to long and cannot be automatically stored in hearing
    int note = 1 * 2 + (long)3; //DOES NOT COMPILE -> The value 3 is cast to long. The 1 * 2 value is evaluated as int but promoted to long when added to the 3

    int pig = (short)4;
    pig = pig++;
    long goat = (int)2;
    goat -= 1.0; //works because it uses the += operator
    goat = 1.0; //DOES NOT COMPILE
    System.out.print(pig + " - " + goat); //4 - 1 

    int a = 5;
    a = (a++);
    System.out.print(a); //5 


### Logical Operators

    boolean canine = true, wolf = true;
    int teeth = 20;
    canine = (teeth != 10) ^ (wolf=false);
    System.out.println(canine+", "+teeth+", "+wolf); //true, 20, false


    boolean sunny = true, raining = false, sunday = true;
    boolean goingToTheStore = sunny & raining ^ sunday;
    boolean goingToTheZoo = sunday && !raining;
    boolean stayingHome = !(goingToTheStore && goingToTheZoo);
    System.out.println(goingToTheStore + "-" + goingToTheZoo + "-" +stayingHome); //true-true-false

### Ranked operators:

In Java, operator precedence dictates the order of evaluation, from postfix (`++`, `--`), 
to prefix (`++`, `--`, `+`, `-`, `~`, `!`), 
then multiplicative (`*`, `/`, `%`),
additive (`+`, `-`),
shift (`<<`, `>>`, `>>>`), 
relational (`<`, `<=`, `>`, `>=`, `instanceof`), 
equality (`==`, `!=`), followed by bitwise AND (`&`), XOR (`^`), and OR (`|`), then logical AND (`&&`), logical OR (`||`), the ternary conditional (`? :`), 
and finally assignment operators (`=`, `+=`, etc.).

> Unary operators are always executed before any surrounding numeric binary or ternary operators.


| Precedence | Operator Type | Operators                                      | Associativity                  |
|------------|---------------|------------------------------------------------|--------------------------------|
| 1          | Unary Postfix | `expr++`, `expr--`                             | Left-to-right                  |
| 2          | Unary Prefix  | `++expr`, `--expr`, `+expr`, `-expr`, `~`, `!` | Right-to-left                  |
| 3          | Binary        | `*`, `/`, `%`                                  | Left-to-right                  |
| 4          | Binary        | `+`, `-`                                       | Left-to-right                  |
| 5          | Binary        | `<<`, `>>`, `>>>`                              | Left-to-right                  |
| 6          | Binary        | `<`, `<=`, `>`, `>=`, `instanceof`             | Left-to-right                  |
| 7          | Binary        | `==`, `!=`                                     | Left-to-right                  |
| 8          | Binary        | `&`                                            | Left-to-right                  |
| 9          | Binary        | `^`                                            | Left-to-right                  |
| 10         | Binary        | `\|`                                           | Left-to-right                  |
| 11         | Binary        | `&&`                                           | Left-to-right                  |
| 12         | Binary        | `\|\|`                                         | Left-to-right                  |
| 13         | Ternary       | `? :`                                          | Right-to-left                  |
| 14         | Binary        | `=`, `+=`, `-=`, `*=`, `/=`, `%=`, `&=`, `     | =`, `^=`, `<<=`, `>>=`, `>>>=` | Right-to-left   |

### Math

    int sample1 = (2 * 4) % 3; // 8 / 3 -> Rest is 2
    int sample2 = 3 * 2 % -3;  // -> 6/3 -> Rest is 0
    int sample3 = 5 * (1 % 2); // 5 * 1 -> 1/2 = 0.5 | 1 % 2 = 1
    System.out.println(sample1 + ", " + sample2 + ", " + sample3);

    System.out.println(8%2); //0 
    System.out.println(8%3); //2 -> 2,66.. = 8-2*3 = 2
    System.out.println(8%5); //3 -> 1,6 = 8-5 = 3
    System.out.println(8%7); //1 -> 1,14 = 8-7 = 1
    System.out.println(8%10); //8 -> 0,8 = 8-0 = 8


## Result:

* Correct: 11
* Mistake: 20
* Score: 55%
* Passing Score: 68%
* Took: 25 Minutes
