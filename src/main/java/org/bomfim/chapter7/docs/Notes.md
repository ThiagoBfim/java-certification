# Chapter 7

## Topics

* Interfaces
* Enums
* Records
* Sealed Classes
* Encapsulation
* Polymorphic inheritance
* Annotations

-> A Java class may have at most one public top-level with the same name as the file.\
-> Top level type can only be declared with public or package access.

## Tricks


## Review Questions Notes

* public constructor for enum && When an enum contains any other members, such as a constructor or variable, the semicolon is required
* Be careful with overloaded method instead of overriding
* Be careful with extends instead of implements
* Be careful with interface override, it must be public
* Be careful with switch decomposing record, variables with same name does not compile
* Private method does not have override, so it can change the signature
* A sealed interface restricts which subinterfaces may extend it.
  A sealed class can be extended by an abstract class.
  A sealed interface restricts which subclasses may implement it.
* Interface can have public static methods
* Interface can have public variable, it is static by default
* Interface can have private method
* In case of constructor with {} for records, it must call the this() at the first line.
* Local class can be abstract. It is possible to create an abstract class inside a method

