# Chapter 3

## Topics

* Controlling Program Flow (If/Else/Switch/Loop)
* Using OOP

### IF/ELSE

The exam can have some trick questions with if without identification

```
boolean isTrue = true;
if(isTrue)
 isTrue = false;
 System.out.println(isTrue);
else  //DOES NOT COMPILE
  System.out.println(false);
```
### Flow Scoping

The pattern mathing uses flow scoping.

Flow scoping means the variable is only in scope when the compiler can definitively determine its type.

The following example does not compile, because we cannot use a OR (||) operation with a variable from the pattern matching 
```
Number data = 10;
if(data instanceof Integer result || result.compareTo(5) > 0) //DOES NOT COMPILE
```
The variable **result** on the OR statement does not exist.

### Switch Statement

Switch does allow arrow function, however, it does not allow both

```
        String name = "Hello World";
        switch (name) {
            case "Hello World":
                System.out.println("Hello World");
                break;
//            case "World" -> System.out.println("World"); //DOES NOT COMPILE    
        }
```

**IMPORTANT: semicolon (;) is required in the switch statement**

We can use switch with pattern matching, but we must be careful not to have different types in the comparison.
```
Number number = 8;
switch (number){
    case Double d -> System.out.println("Double");
    case Integer i -> System.out.println("Integer");
//            case 10 -> System.out.println("10"); //DOES NOT COMPILE : NUMBER != Integer 
default -> throw new IllegalStateException("Unexpected value: " + number); //REQUIRED
}
```

* **IMPORTANT: boolean, long, float and double are not supported in switch statement**
* **IMPORTANT: ALL the values from the switch must be final/constant values, it is evaluated in the compile time**

### LABEL

Label usually is not used, however it can appear in some questions.
Example:
```
int x = 0;
OUT_LOOP: for (long i = 0; i < 10; i++) {x++;
    INNER_LOOP: for (long j = 0; j < 10; j++) {x++;}
}
System.out.println(x); //110
```

IMPORTANT: It is possible to break the parent loop

```
int x = 0;
OUT_LOOP: for (long i = 0; i < 10; i++) {x++;
    INNER_LOOP: for (long j = 0; j < 10; j++) {
      x++;
      //continue OUT_LOOP; //COMPILES
      break OUT_LOOP;
    }
}
System.out.println(x); //2
```

## Tips

* Loops with a decrement operator usually is to validate the knowledge of loop operations.
* Be careful with infinite loop
```
for (;;) //COMPILES - INFINITE LOOP
System.out.println("a");

for (int i =0, j = 1; i < 10 && j <2; i++, j++) //COMPILE
```


## Tricks

* Be careful with if statement that does not compile
```
int isTrue = 1;
if(isTrue) { //DOES NOT COMPILE
}
```

* Be careful with pattern matching/flow scoping with negate statement

The following code compiles, because the variable will be created for the following operations outside the if block.
```
    if(!(a instanceof Integer a3)) {
        //System.out.println(a3); DOES NOT COMPILE
        return;
    }
    System.out.println(a3);
```

* Switch statement does required the braces {} and the parentheses

```
    var x = 10;
    switch x //DOES NOT COMPILE
      case 10 -> ...
    switch (x) //DOES NOT COMPILE
      case 10 ->   
```

* Switch statement with a new instance must end with a semicolon (;)

```

Integer x = 10; 
String value = switch (x) {
//            case null -> System.out.println("Hello World");  DOES NOT COMPILE, require a return data
case 10 -> "10";
case 9 -> "9";
default -> throw new IllegalStateException("Unexpected value: " + x); //REQUIRED

}; //VERY IMPORTANT ;
```

* The DEFAULT expression can be in any order.
```
switch(5) {
    default -> System.out.println("TEST");
    case 1 -> System.out.println(1);
    case 5 -> System.out.println(5);
}
//RESULT = 5;
```

```
switch(5) {
    default -> System.out.println("TEST");

   // null -> System.out.println&#40;"TEST"&#41;; //DOES NOT COMPILE
    case 1 -> System.out.println(1);
    case 5 -> System.out.println(5);
}
//RESULT = 5;
```


* Be careful with Trick with for-each

```
List<String> sloths = new ArrayList<>();
for (int x: sloths) //DOES NOT COMPILE, Wrong type
 System.out.println(x)
```

```
    private static void forArray(String[] args) {
        for (String arg : args) { //COMPILE
            System.out.println(arg);
        }
    }

    private static void testLoopWithVarargs(String... names) {
        for (String name : names) { //COMPILE
            System.out.println(name);
        }
    }
```

## Review Questions Notes
