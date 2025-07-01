# Chapter 8

## Topics

* Functional Interface
* Method references
* Lambda expression

### Method references

| Method references Type                  | Before colon           | After colon | Example         |
|-----------------------------------------|------------------------|-------------|-----------------|
| static methods                          | Class name             | Method name | Math::random    |
| Instance methods on a particular object | Instance variable name | Method name | str::startsWith |
| Instance methods on a parameter         | Class name             | Method name | String::isEmpty |
| Constructor                             | Class name             | New         | String::new     |

### Common functional interfaces

| Functional interface | Return type | Method name  | # of parameters |
|----------------------|-------------|--------------|-----------------|
| Supplier<T>          | T           | get()        | 0               |
| Consumer<T>          | void        | accept(T)    | 1 (T)           |
| BiConsumer<T, U>     | void        | accept(T, U) | 2 (T, U)        |
| Predicate<T>         | boolean     | test(T)      | 1 (T)           |
| BiPredicate<T, U>    | boolean     | test(T, U)   | 2 (T, U)        |
| Function<T, R>       | R           | apply(T)     | 1 (T)           |
| BiFunction<T, U, R>  | R           | apply(T, U)  | 2 (T, U)        |
| UnaryOperator<T>     | T           | apply(T)     | 1 (T)           |
| BinaryOperator<T>    | T           | apply(T, T)  | 2 (T, T)        |

### Convenience methods

| Interface  | Instance method | Method return type | Method name | Method parameters |
|------------|-----------------|--------------------|-------------|-------------------|
| Consumer   | Consumer        | Consumer           | andThen()   | Consumer          |
| Function   | Function        | Function           | andThen()   | Function          |
| Function   | Function        | Function           | compose()   | Function          |
| Predicate  | Predicate       | Predicate          | and()       | Predicate         |
| Predicate  | Predicate       | Predicate          | negate()    | —                 |
| Predicate  | Predicate       | Predicate          | or()        | Predicate         |

### Common functional interfaces for primitives

| Functional interfaces | Return type | Single abstract method | # of parameters    |
|-----------------------|-------------|------------------------|--------------------|
| DoubleSupplier        | double      | getAsDouble            | 0                  |
| IntSupplier           | int         | getAsInt               | 0                  |
| LongSupplier          | long        | getAsLong              | 0                  |
| DoubleConsumer        | void        | accept                 | 1 (double)         |
| IntConsumer           | void        | accept                 | 1 (int)            |
| LongConsumer          | void        | accept                 | 1 (long)           |
| DoublePredicate       | boolean     | test                   | 1 (double)         |
| IntPredicate          | boolean     | test                   | 1 (int)            |
| LongPredicate         | boolean     | test                   | 1 (long)           |
| DoubleFunction<R>     | R           | apply                  | 1 (double)         |
| IntFunction<R>        | R           | apply                  | 1 (int)            |
| LongFunction<R>       | R           | apply                  | 1 (long)           |
| DoubleUnaryOperator   | double      | applyAsDouble          | 1 (double)         |
| IntUnaryOperator      | int         | applyAsInt             | 1 (int)            |
| LongUnaryOperator     | long        | applyAsLong            | 1 (long)           |
| DoubleBinaryOperator  | double      | applyAsDouble          | 2 (double, double) |
| IntBinaryOperator     | int         | applyAsInt             | 2 (int, int)       |
| LongBinaryOperator    | long        | applyAsLong            | 2 (long, long)     |

### Primitive-specific functional interface

| Functional interfaces    | Return type | Single abstract method | # of parameters |
|--------------------------|-------------|------------------------|-----------------|
| ToDoubleFunction<T>      | double      | applyAsDouble          | 1 (T)           |
| ToIntFunction<T>         | int         | applyAsInt             | 1 (T)           |
| ToLongFunction<T>        | long        | applyAsLong            | 1 (T)           |
| ToDoubleBiFunction<T, U> | double      | applyAsDouble          | 2 (T, U)        |
| ToIntBiFunction<T, U>    | int         | applyAsInt             | 2 (T, U)        |
| ToLongBiFunction<T, U>   | long        | applyAsLong            | 2 (T, U)        |
| DoubleToIntFunction      | int         | applyAsInt             | 1 (double)      |
| DoubleToLongFunction     | long        | applyAsLong            | 1 (double)      |
| IntToDoubleFunction      | double      | applyAsDouble          | 1 (int)         |
| IntToLongFunction        | long        | applyAsLong            | 1 (int)         |
| LongToDoubleFunction     | double      | applyAsDouble          | 1 (long)        |
| LongToIntFunction        | int         | applyAsInt             | 1 (long)        |
| ObjDoubleConsumer<T>     | void        | accept                 | 2 (T, double)   |
| ObjIntConsumer<T>        | void        | accept                 | 2 (T, int)      |
| ObjLongConsumer<T>       | void        | accept                 | 2 (T, long)     |


## Tricks

* The function that returns a Boolean and takes a String is a Function<String,Boolean>, the predicate return the primitive boolean.

The following code works:

    public void counts(List<Integer> list) { 
        list.sort((final var x, @Deprecated var y) -> x.compareTo(y)); 
    }

## Review Questions Notes