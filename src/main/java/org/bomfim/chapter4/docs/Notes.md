# Chapter 4

## Topics

* Handling Date, Time, Text, Numeric and boolean values
* Working with Arrays and Collections

### String methods

####  length()
```
System.out.println("ab".length()); //2
```

####  chartAt()
```
System.out.println("ab".charAt(0)); //a
System.out.println("ab".charAt(2)); //StringIndexOutOfBoundsException
```

#### codePoint

We do not need to memorize the ASCII code.

        System.out.println("\"a\".codePointAt(0) - " + "a".codePointAt(0)); //97
        System.out.println("\"a\".codePointAt(1) - throw exception");
        System.out.println("\"a\".codePointBefore(1) - " + "a".codePointBefore(1)); //97
        System.out.println("\"a\".codePointBefore(0) - throw exception");
        System.out.println("\"a\".codePointCount(0,1) - " + "a".codePointCount(0, 1)); //1

#### Substring

    "testea".substring(0, 2) - te
    "testea".substring(2) - stea
    "testea".substring(3,3) -
    "testea".substring(3,6) - tea
    "testea".substring(3,7) - throw exception
    "testea".substring(3,2) - throw exception

#### indexOf

indexOf returns -1 if does not found.

    "animal".indexOf("a") - 0
    "animal".indexOf("al") - 4
    "animal".indexOf("al", 4) -4
    "animal".indexOf("a", 2,4) - -1
    "animal".indexOf("a", 2,6) - 4
    "animal".indexOf("ni", 2,3) - -1
    "animal".indexOf("ni", 1,3) - 1
    "animal".indexOf("ni", 1,2) - -1

#### toUpperCase && toLowerCase

    "Animal".toUpperCase(); // ANIMAL
    "Animal".toLowerCase(); // animal


### Date && Time

|               | Can use Period? | Can use Duration? |
|---------------|-----------------|-------------------|
| LocalDate     | Yes             | No                |
| LocalTime     | No              | Yes               |
| LocalDateTime | Yes             | Yes               |
| ZonedDateTime | Yes             | Yes               | 
## Tips

* Be careful with String concatenation with numbers

```
System.out.println("a" + 1 + 2); //a12
System.out.println(1 + 2 + "a"); //3a
```

* The substring() method is the trickiest String method on the exam.

        System.out.println("testea".substring(3,3)); // Empty
        System.out.println("testea".substring(3,2)); //Exception


## Tricks

## Review Questions Notes
