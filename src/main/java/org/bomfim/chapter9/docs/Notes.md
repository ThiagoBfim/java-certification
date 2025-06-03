# Chapter 9

## Topics

* Collections
* List: A list is an ordered collection of elements that allows duplicate entries. Elements in a list can be accessed by an int index.
* Set: A set is a collection that does not allow duplicate entries.
* Queue: A queue is a collection that orders its elements in a specific order for processing.
  A Deque is a subinterface of Queue that allows access at both ends.
* Map: A map is a collection that maps keys to values, with no duplicate keys allowed. The elements in a map are key/value pairs.

### Working with List Methods

| Method                                       | Description                                                                                           |
|----------------------------------------------|-------------------------------------------------------------------------------------------------------|
| boolean add(E element)                       | Adds element to end (available on all Collection APIs).                                               |
| void add(int index, E element)               | Adds element at index and moves the rest toward the end.                                              |
| E get(int index)                             | Returns element at index.                                                                             |
| int indexOf(Object o)                        | Returns the index of the first matching element or -1 if not found.                                   |
| int lastIndexOf(Object o)                    | Returns the index of the last matching element or -1 if not found.                                    |
| E remove(int index)                          | Removes element at index and moves the rest toward the front.                                         |
| default void replaceAll(UnaryOperator<E> op) | Replaces each element in list with the result of operator.                                            |
| E set(int index, E e)                        | Replaces element at index and returns original. Throws IndexOutOfBoundsException if index is invalid. |
| default void sort(Comparator<? super E> c)   | Sorts list. We cover this later in the chapter in the “Sorting Data” section.                         |

### Working with Map Methods

| Method                                            | Description                                                                                               |
|---------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| void clear()                                      | Removes all keys and values from map.                                                                     |
| boolean containsKey(Object key)                   | Returns whether key is in map.                                                                            |
| boolean containsValue(Object value)               | Returns whether value is in map.                                                                          |
| Set<Map.Entry<K,V>> entrySet()                    | Returns Set of key/value pairs.                                                                           |
| void forEach(BiConsumer<K, V> action)             | Loops through each key/value pair.                                                                        |
| V get(Object key)                                 | Returns value mapped by key or null if none is mapped.                                                    |
| V getOrDefault(Object key, V defaultValue)        | Returns value mapped by key or default value if none is mapped.                                           |
| boolean isEmpty()                                 | Returns whether map is empty.                                                                             |
| Set<K> keySet()                                   | Returns set of all keys.                                                                                  |
| V merge(K key, V value, BiFunction<V, V, V> func) | Sets value if key not set. Runs function if key is set, to determine new value. Removes if value is null. |
| V put(K key, V value)                             | Adds or replaces key/value pair. Returns previous value or null.                                          |
| V putIfAbsent(K key, V value)                     | Adds value if key not present and returns null. Otherwise, returns existing value.                        |
| V remove(Object key)                              | Removes and returns value mapped to key. Returns null if none.                                            |
| V replace(K key, V value)                         | Replaces value for given key if key is set. Returns original value or null if none.                       |
| void replaceAll(BiFunction<K, V, V> func)         | Replaces each value with results of function.                                                             |
| int size()                                        | Returns number of entries (key/value pairs) in map.                                                       |
| Collection<V> values()                            | Returns Collection of all values.                                                                         |

### Sequenced Collection methods

| Method        | Description                                         |
|---------------|-----------------------------------------------------|
| addFirst(E e) | Adds element as the first element in the collection |
| addLast(E e)  | Adds element as the last element in the collection  |
| getFirst()    | Retrieves the first element in the collection       |
| getLast()     | Retrieves the last element in the collection        |
| removeFirst() | Removes the first element in the collection         |
| removeLast()  | Removes the last element in the collection          |
| reversed()    | Returns a reverse-ordered view of the collection    |

### Sequenced Map methods

| Method             | Description                                               |
|--------------------|-----------------------------------------------------------|
| firstEntry()       | Retrieves the first key/value pair in the map             |
| lastEntry()        | Retrieves the last key/value pair in the map              |
| pollFirstEntry()   | Removes and retrieves the first key/value pair in the map |
| pollLastEntry()    | Removes and retrieves the last key/value pair in the map  |
| putFirst(K k, V v) | Adds the key/value pair as the first element in the map   |
| putLast(K k, V v)  | Adds the key/value pair as the last element in the map    |
| reversed()         | Returns a reverse-ordered view of the map                 |

### Generics

| TABLE 9.15 Types of bounds | Syntax           | Example                                                            |
|:---------------------------|:-----------------|:-------------------------------------------------------------------|
| Unbounded wildcard         | `?`              | `List<?> a = new ArrayList<String>();`                             |
| Wildcard with upper bound  | `? extends type` | `List<? extends Exception> a = new ArrayList<RuntimeException>();` |
| Wildcard with lower bound  | `? super type`   | `List<? super Exception> a = new ArrayList<Object>();`             |

## Tricks


## Review Questions Notes

## Tricks


## Review Questions Notes


## Extra

# Naming Conventions for Generics

A type parameter can be named anything you want. The convention is to use single uppercase letters to make it obvious that they aren’t real class names. The following are common letters to use:

- **E**: for an element
- **K**: for a map key
- **V**: for a map value
- **N**: for a number
- **T**: for a generic data type
- **S, U, V,** and so forth: for multiple generic types  

