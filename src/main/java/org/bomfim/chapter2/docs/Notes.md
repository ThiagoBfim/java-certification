# Chapter 2

## Topics

* Handling Date, Time, Text, Numeric and Boolean Values
* Math API, conversions, casting

> Java operator is a special symbol that can be applied to a set of variables, values or literals

### Operators

#### Bitwise negation operator (~) increment 1 digit if positive, decrement 1 value if negative

    var x = 100; 
    System.out.println(~x); //-101
    System.out.println(~-100); //99

#### Modules Operator

    System.out.println("2 % 5 = " + 2 % 5); //2
    System.out.println("2 % -5 = " + 2 % -5); //2
    System.out.println("-2 % -5 = " + -2 % -5); //-2
    System.out.println("-2 % 5 = " + -2 % 5); //-2

### Numeric Promotion

* If 2 values has different data types, Java will promote one of the values to the larger of the two data types

        int x = 100;
        long y = x; //COMPILE
        var  y2 = x + 1L; //COMPILE y2 becomes a long
        x = y //DOES NOT COMPILE
* Java will automatically promote the integral value to the floating point value
* After all promotion has occurred and the operands have the same data type, the resulting value will have the same data
  type as its promoted operands

### Assignment operators

Assignment operator is a binary oeprator that modifies or assigns the variable on the left side of the operator with the
result of the value on the right side of the equation.

Example: int hard = 1;

We cannot assign a big number to a small one.

    int i0 =  5*2L; //DOES NOT COMPILE
    int i0 =  5*2; //COMPILE
    short s0 = 10000 + 30000; //DOES NOT COMPILE
    short s01 = (short)(10000 + 30000); //COMPILE
    short s1 = 10000 + 22767; //COMPILE
    long l1 = 1000000000000000 //DOES NOT COMPILE
    long l1 = (long)1000000000000000 //DOES NOT COMPILE
    long l1 = 1000000000000000L //COMPILE

### Compound operator

The compound operador does to casting automatically

    long l = 10;
    int i = 5;
    i = i * l; //OES NOT COMPILE
    i *= l; //COMPILE

### Logical Operators

| Name                 | Example | Description                         |
|----------------------|---------|-------------------------------------|
| Logical AND          | a & b   | True only if both are true          |
| Logical inclusive OR | a \|  b | True if at least one is true        |
| Logical exclusive OR | a ^ b   | True only if only one value is true |

    true & true = true
    true & false = false
    false & false = false
    true | false = true
    true | true = true
    false | false = false
    true ^ false = true
    true ^ true = false

### Bitwise Operator

#### 1. AND (&)

  ```
  int a = 5;     // 0101
  int b = 3;     // 0011
  int result = a & b;  // 0001 = 1
  System.out.println(result); // Output: 1
  ```

#### 2. OR (|)

  ```
  int a = 5;     // 0101
  int b = 3;     // 0011
  int result = a | b;  // 0111 = 7
  System.out.println(result); // Output: 7
  ```

#### 3. XOR (^)

  ```
  int a = 5;     // 0101
  int b = 3;     // 0011
  int result = a ^ b;  // 0110 = 6
  System.out.println(result); // Output: 6
  ```

#### 4. NOT (~)

  ```
int a = 5;     // 00000000 00000000 00000000 00000101
int result = ~a;
// Output: 11111111 11111111 11111111 11111010 (which is -6 in 2's complement)
System.out.println(result); // Output: -6
  ```

## Tips

* Be careful with expressions using 0 and 1 for boolean
  boolean x = 0; //DOES NOT COMPILE
* Assignment operators appear in nearly every question

## Tricks

* -(-x)) = x
* int price = 10*2/9; //COMPILE, price = 2
* int price = 10*2/9 + 5.1; //DOES NOT COMPILE
* Be careful with missing parentheses: int x = (9+2) + 3) * (2 -1 // DOES NOT COMPILE
* Be careful with assignments

  ```
    short s = 2+1; //COMPILE
    short s2 = 2+1+s; //DOES NOT COMPILE
  ```
* Be careful with assignment inline
  ```
    boolean x = true;
    if(x = false)
      System.out.println("false");
    else 
        System.out.println(x);
    //This code will print false
  ```

  ```
    boolean x = true;
    if(x == false)
      System.out.println("false");
    else 
        System.out.println(x);
    //This code will print true
  ```
* Be careful: Using equality operators cannot be mixed with different types

  ```
     boolean b = true == false; //COMPILE
     boolean b1 = true == 3; //DOES NOT COMPILE
     false != "grape"; //DOES NOT COMPILE
     10.2 != "grape"; //DOES NOT COMPILE
  ```
* System.out.println(null == null); //true
* instanceOf with object that cannot be true causes compile error

  ```
     Number x = 10;
     if(x instanceof String) //DOES NOT COMPILE
     if(null instanceof Integer){} //COMPILE, return false
  ```

## Review Questions Notes