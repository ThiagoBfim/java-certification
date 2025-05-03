# Chapter 5

## Topics

* Create classes, records and define and use instance and static fields and methods constructors and instance and static initializers
* inheritance, abstract and sealed types.

## Tricks

## Review Questions Notes

* Overridden methods must have the same signature. 
* Hidden methods must have the same signature.
* FALSE: Overloaded methods must have the same signature.
* FALSE: Overridden methods must have the same return type.
* Overloaded methods can have different return types, while overridden and hidden methods can have covariant return types
* FALSE: An overridden method must contain method parameters that are the same or covariant with the method parameters in the inherited method.
* There is no such thing as a covariant signature. An overridden method must not declare any new checked exceptions or a checked exception that is broader than the inherited method.
* Overriden a statics method must follow the overriden rules. It must be static too, and it is considered a hiden method
* The overriden method must have the same or more restrictive type. Example: If the superclass returns Number the subclass can return Integer, the opposite is not true. 

* A class can implement any number of interfaces.
* If class A extends the class B, then B is a superclass of A.
* If class C implements interface D, then C is a subtype of D.
* Multiple inheritance is the property of a class to have multiple direct superclasses.
