To review:

8 - G??
9 - Review what is the default of everything
10 - Tricky
13
14
15
17 -
20 - Be careful with false constructor
21 - main is executed before blocks in the code.
22

### Var

// var evening = 2; evening = null; does not compile
//var morning = ""; morning = null;

evening is int

### Default

* An instance variable of type float defaults to 0.0.
* A local variable of type double does not have a default;
* A local variable of type int does not have a default;

      public static float code; //default to 0.0
      public float code2; //default to 0.0
      public final float code3; //compile error must be initialized

### Constructor

BE CAREFUL WITH FALSE CONSTRUCTOR

    public class Box {
    
        static {System.out.println("Static block");}
        {System.out.println("Block");}
        public void Box() {
            System.out.println("OK");
        }
    
        public static void main(String[] args) {
            System.out.println("Before");
            Box box = new Box();
            System.out.println("After");
        }
    }

The output for this class is:

    Static block
    Before
    Block
    After


### Variables

```java
    var x = 3_1;
    var x5 = 9___6;
    var x3 = 2_234.0_0;
    var x4 = 2_234_.0_0; //DOES NOT COMPILE
    var x2 = 3_13.0_; //DOES NOT COMPILE
    var x6 = _1_3_5_0; //DOES NOT COMPILE
    
    short numPets = 5L; //DOES NOT COMPILE
    int numGrains = 2.0; //DOES NOT COMPILE

    int amount = 0xE;
    int amount = 0b101;
    int a4 = 9_2.1_2; //does not compile
    double a5 = 9_2.1_2;
```
### Garbage Collector

FALSE: Garbage collection runs on a set schedule.

### Imports

If we have a class that requires the import, the import will be the first specific import, or if there is a asterist, it
will consider the package.

Example:

    import java.awt.*;
    import java.util.*;
    class TestList{
        List x; //DOES NOT COMPILE
    }

    import java.awt.*;
    import java.util.List;
    class TestList{
        List x; //COMPILE
    } 

    import java.util.List;
    class TestList{
        List x; //COMPILE
    }

    import java.util.*;
    class TestList{
        List x; //COMPILE
    }

## Result:

* Correct: 18
* Mistake: 5
* Score: 78%
* Passing Score: 68%
* Took: 35 Minutes








