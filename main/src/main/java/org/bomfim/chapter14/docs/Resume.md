# Java I/O and NIO.2 Study Guide

This document summarizes key concepts and APIs for Java I/O Streams and NIO.2, focusing on topics relevant to the exam.

## Chapter 14: Key Topics

- Read and write console and file data using I/O Streams.
- Serialize and deserialize Java objects.
- Construct, traverse, create, read, and write path objects and their properties using the java.nio.file API (NIO.2).

## I/O Streams

### Overview


- Byte I/O Streams: Used for binary data (e.g., images, executable files). Class names end in InputStream or OutputStream.
- Character I/O Streams: Used for text data. Class names end in Reader or Writer.
- Low-Level Streams: Connect directly to the data source (e.g., FileInputStream, FileReader).
- High-Level Streams: Wrappers around low-level streams, providing enhanced functionality and performance (e.g., BufferedInputStream, BufferedReader).

### Abstract Stream Base Classes (java.io)

|              |                                                 |
|--------------|-------------------------------------------------|
| Class Name   | Description                                     |
| InputStream  | Abstract class for all input byte streams       |
| OutputStream | Abstract class for all output byte streams      |
| Reader       | Abstract class for all input character streams  |
| Writer       | Abstract class for all output character streams |

### Concrete I/O Stream Classes (java.io)

|                      |                |                                                                                                         |
|----------------------|----------------|---------------------------------------------------------------------------------------------------------|
| Class Name           | Low/High Level | Description                                                                                             |
| FileInputStream      | Low            | Reads file data as bytes                                                                                |
| FileOutputStream     | Low            | Writes file data as bytes                                                                               |
| FileReader           | Low            | Reads file data as characters                                                                           |
| FileWriter           | Low            | Writes file data as characters                                                                          |
| BufferedInputStream  | High           | Reads byte data from existing InputStream in buffered manner, which improves efficiency and performance |
| BufferedOutputStream | High           | Writes byte data to existing OutputStream in buffered manner, which improves efficiency and performance |
| BufferedReader       | High           | Reads character data from existing Reader in buffered manner, which improves efficiency and performance |
| BufferedWriter       | High           | Writes character data to existing Writer in buffered manner, which improves efficiency and performance  |
| ObjectInputStream    | High           | Deserializes primitive Java datatypes and graphs of Java objects from existing InputStream              |
| ObjectOutputStream   | High           | Serializes primitive Java data types and graphs of Java objects to existing OutputStream                |
| PrintStream          | High           | Writes formatted representations of Java objects to binary stream                                       |
| PrintWriter          | High           | Writes formatted representations of Java objects to character stream                                    |



### Common I/O Stream Methods

|                     |                                              |                                                                                                                                                                               |
|---------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Class               | Method Name                                  | Description                                                                                                                                                                   |
| All input streams   | int read()                                   | Reads single byte or returns -1 if no bytes available.                                                                                                                        |
| InputStream         | int read(byte[] b)                           | Reads values into buffer and returns number of bytes or characters read.                                                                                                      |
| Reader              | int read(char[] c)                           | Reads values into buffer and returns number of bytes or characters read.                                                                                                      |
| InputStream         | int read(byte[] b, int offset, int length)   | Reads up to length values into buffer starting from position offset and returns number of bytes or characters read.                                                           |
| Reader              | int read(char[] c, int offset, int length)   | Reads up to length values into buffer starting from position offset and returns number of bytes or characters read.                                                           |
| All output streams  | void write(int b)                            | Writes single byte.                                                                                                                                                           |
| OutputStream        | void write(byte[] b)                         | Writes array of values into stream.                                                                                                                                           |
| Writer              | void write(char[] c)                         | Writes array of values into stream.                                                                                                                                           |
| OutputStream        | void write(byte[] b, int offset, int length) | Writes length values from array into stream, starting with offset index.                                                                                                      |
| Writer              | void write(char[] c, int offset, int length) | Writes length values from array into stream, starting with offset index.                                                                                                      |
| InputStream         | byte[] readAllBytes()                        | Reads data in bytes.                                                                                                                                                          |
| BufferedReader      | String readLine()                            | Reads line of data.                                                                                                                                                           |
| Writer              | void write(String line)                      | Writes line of data.                                                                                                                                                          |
| BufferedWriter      | void newLine()                               | Writes new line.                                                                                                                                                              |
| All output streams  | void flush()                                 | Flushes buffered data through stream.                                                                                                                                         |
| All streams         | void close()                                 | Closes stream and releases resources.                                                                                                                                         |
| InputStream, Reader | boolean markSupported()                      | Returns true if stream class supports mark() and reset().                                                                                                                     |
| InputStream, Reader | void mark(int readLimit)                     | Marks current position in stream, allowing reset() to return to this point. readLimit specifies the maximum number of bytes that can be read before the mark becomes invalid. |
| InputStream, Reader | void reset()                                 | Attempts to reset stream to the position where mark() was last called.                                                                                                        |
| InputStream, Reader | long skip(long n)                            | Reads and discards specified number of characters (for Reader) or bytes (for InputStream).                                                                                    |

## Serialization

- Serialization: The process of converting an in-memory object to a byte stream.
- Deserialization: The process of converting from a byte stream into an object.
- To make a class serializable, it must implement java.io.Serializable.
- All instance members of a serializable class must be serializable, marked transient, or have a null value at the time of serialization.
- static fields are not serialized; marking them transient has little effect.
- When deserializing, Java calls the no-argument constructor of the first non-serializable parent class it can find in the class hierarchy. Constructors of the serialized class itself are ignored during deserialization.
- Example: If MyLion extends Mamal (which is not serializable) and MyLion is serializable, then Mamal's no-arg constructor will be called during MyLion deserialization.


## NIO.2 (java.nio.file)

The exam covers NIO version 2. There was a version 1 that covered channels, but it is not on the exam.

### Path and File Operations

|                                           |                          |                            |
|-------------------------------------------|--------------------------|----------------------------|
| Description                               | I/O File Instance Method | NIO.2 Path Instance Method |
| Gets name of file/directory               | getName()                | getFileName()              |
| Retrieves parent directory or null        | getParent()              | getParent()                |
| Checks if file/directory is absolute path | isAbsolute()             | isAbsolute()               |
| Retrieves absolute path of file/directory | getAbsolutePath()        | toAbsolutePath()           |

### File and Files Operations

|                                                 |                          |                                                                |
|-------------------------------------------------|--------------------------|----------------------------------------------------------------|
| Description                                     | I/O File Instance Method | NIO.2 Files Static Method                                      |
| Deletes file/directory                          | delete()                 | deleteIfExists(Path p) throws IOException                      |
| Checks if file/directory exists                 | exists()                 | exists(Path p, LinkOption… o)                                  |
| Checks if resource is directory                 | isDirectory()            | isDirectory(Path p, LinkOption… o)                             |
| Checks if resource is file                      | isFile()                 | isRegularFile(Path p, LinkOption… o)                           |
| Returns last modified time                      | lastModified()           | getLastModifiedTime(Path p, LinkOption… o) throws IOException  |
| Retrieves number of bytes in file               | length()                 | size(Path p) throws IOException                                |
| Lists contents of directory                     | listFiles()              | list(Path p) throws IOException                                |
| Creates directory                               | mkdir()                  | createDirectory(Path p, FileAttribute… a) throws IOException   |
| Creates directory including nonexistent parents | mkdirs()                 | createDirectories(Path p, FileAttribute… a) throws IOException |
| Renames file/directory                          | renameTo(File dest)      | move(Path src, Path dest, CopyOption… o) throws IOException    |

### Common NIO.2 Method Arguments (Enums)

|                    |                     |                   |                                                                        |
|--------------------|---------------------|-------------------|------------------------------------------------------------------------|
| Enum Type          | Interface Inherited | Enum Value        | Details                                                                |
| LinkOption         | CopyOption          | NOFOLLOW_LINKS    | Do not follow symbolic links.                                          |
| StandardCopyOption | CopyOption          | ATOMIC_MOVE       | Move file as atomic file system operation.                             |
|                    |                     | COPY_ATTRIBUTES   | Copy existing attributes to new file.                                  |
|                    |                     | REPLACE_EXISTING  | Overwrite file if it already exists.                                   |
| StandardOpenOption | OpenOption          | APPEND            | If file is already open for write, append to the end.                  |
|                    |                     | CREATE            | Create new file if it does not exist.                                  |
|                    |                     | CREATE_NEW        | Create new file only if it does not exist; fail otherwise.             |
|                    |                     | READ              | Open for read access.                                                  |
|                    |                     | TRUNCATE_EXISTING | If file is already open for write, erase file and append to beginning. |
|                    |                     | WRITE             | Open for write access.                                                 |
| FileVisitOption    | N/A                 | FOLLOW_LINKS      | Follow symbolic links.                                                 |



### Path Manipulation


- resolve(): Concatenates paths. If the argument is an absolute path, it returns the argument itself.
  - Example: Path.of("/cats/../panther").resolve(Path.of("food")) results in /cats/../panther/food.
  - Example: Path.of("/turkey/food").resolve("/tiger/cage") results in /tiger/cage.
- normalize(): Removes redundant path elements like . and ...
  - Example: Path.of("/cats/../panther/food").normalize() results in /panther/food.
- Path.of("../../fish.txt").normalize() results in ../../fish.txt (no change if only parent directories are present).
- Can make equals() return true for logically same paths: Path.of("/pony/../weather.txt").normalize().equals(Path.of("/weather.txt").normalize()) is true.
- relativize(): Constructs a path to one provided, representing the steps needed to reach the target path from the source.
  - Requires both paths to be absolute or relative. Throws IllegalArgumentException if types are mixed.
  - On Windows, for absolute paths, both must have the same root directory or drive letter.
  - Example: Path.of("/test/schedule.xml").relativize(Path.of("/test/text.txt")) results in ../text.txt.
- toRealPath(): Returns the complete path from the root, resolving symbolic links and redundant elements.
  - Throws IOException if the path does not exist.
  - Example: Path.of("./main/src/main/..").toRealPath() returns /Users/path/java-21-cert/main/src (example output).

### Creating, Moving, and Deleting


- Files.createDirectory(Path dir): Creates a single directory. Throws an exception if parent directories don't exist.
- Files.createDirectories(Path dir): Creates a directory, including any nonexistent parent directories.
- Files.copy(Path source, Path target, CopyOption... options): Copies a file or a shallow copy of a directory.
  - By default, throws FileAlreadyExistsException if the target exists.
  - Use StandardCopyOption.REPLACE_EXISTING to overwrite.
  - When copying a file to an existing directory without specifying a target filename, the file will be renamed to the directory name.
- Files.move(Path source, Path target, CopyOption... options): Moves or renames a file or directory.
  - Requires REPLACE_EXISTING to overwrite an existing target if it exists.
  - StandardCopyOption.ATOMIC_MOVE: Performs the move as a single, indivisible file system operation. Throws AtomicMoveNotSupportedException if not supported by the file system.
  - ATOMIC_MOVE is generally not supported for copy() methods.
- Files.delete(Path path): Deletes a file or an empty directory. Throws IOException if the file does not exist or the directory is not empty.
- Files.deleteIfExists(Path path): Deletes a file or an empty directory. Returns true or false in case it is able to delete the file. Does not throw an exception if the file does not exist.
  - If the path is a symbolic link, the symbolic link will be deleted, not the path that the symbolic link points to.

    
### Comparing Files

- Files.isSameFile(Path path1, Path path2): Checks if two paths refer to the same file or directory. This can be used with relative path or symbolic link and return true if it is the same path.
- Files.mismatch(Path path1, Path path2): Compares the content of two files. Returns -1 if files are identical, or the position of the first byte mismatch.

### Reading and Writing with Files (NIO.2 Static Methods)

|                                            |                                                                                                               |
|--------------------------------------------|---------------------------------------------------------------------------------------------------------------|
| Method Name                                | Description                                                                                                   |
| byte[] readAllBytes(Path path)             | Reads all data as bytes                                                                                       |
| String readString(Path path)               | Reads all data into String                                                                                    |
| List<String> readAllLines(Path path)       | Reads all data into List. Reads the entire file into memory.                                                  |
| Stream<String> lines(Path path)            | Lazily reads data, returning a Stream<String>.                                                                |
| void write(Path path, byte[] bytes)        | Writes array of bytes                                                                                         |
| void writeString(Path path, String string) | Writes String                                                                                                 |
| void write(Path path, List<String> list)   | Writes list of lines (technically, an Iterable of CharSequence, but you don’t need to know that for the exam) |



### File Attributes

- BasicFileAttributes: Basic set of attributes supported by all file systems.
- DosFileAttributes: Basic set of attributes along with those supported by DOS/Windows-based systems.
- PosixFileAttributes: Basic set of attributes along with those supported by POSIX systems, such as Unix, Linux, Mac, etc.


- Files.readAttributes(Path path, Class\<A> type, LinkOption... options): Reads file attributes.
- Files.getFileAttributeView(Path path, Class\<V> type): Gets a file attribute view to modify attributes.


### Traversing a Directory Tree

- Files.walk(Path start, int maxDepth, FileVisitOption... options): Performs a depth-first search of a directory tree.
  - maxDepth limits the levels.
- FileVisitOption.FOLLOW_LINKS: Follows symbolic links. If a cycle is detected, FileSystemLoopException is thrown.
- Files.find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitOption... options): Similar to walk(), but it returns a Stream based on a given matcher BiPredicate.

## Be Careful! Tricky Questions


- NIO.2 Version: The exam focuses on NIO version 2, not version 1 (which covered channels).
- Path Immutability: Path objects are immutable. Methods like resolve() return a new Path object; they do not modify the original.
  - Example:
  
        Path path = Path.of("./test"); 
        path.resolve("test.txt"); 
        System.out.println(path); //will still print ./test. 
  - The result of resolve must be assigned to a variable.


- subpath() Indexing: subpath() is zero-indexed and does not include the root. It throws IllegalArgumentException if beginIndex and endIndex are the same, or if the range is out of bounds.
    - Example: `Path.of("/mammal/omnivore/raccoon.image").subpath(1, 1)` will throw an exception.


- getNameCount() for Root: For a root path (e.g., /), getNameCount() returns 0. 
    - **Accessing getName(0) on a root path will throw IllegalArgumentException.**
- resolve() with Absolute Paths: When resolve() is called with an absolute path as an argument, the argument path is returned, effectively ignoring the original path.
  - Example: `Path.of("/turkey/food").resolve("/tiger/cage")` results in /tiger/cage.
- normalize() and Relative Paths: normalize() on a path like ../../file.txt does nothing, as there are no redundant elements to remove.
- relativize() Mixed Path Types: relativize() throws an IllegalArgumentException if one path is absolute and the other is relative.
- toRealPath() Existence: toRealPath() throws an exception if the path does not exist.
- Files.copy() and Files.move() Target Existence: By default, Files.copy() and Files.move() throw an Exception if the target file/directory already exists. 
Use StandardCopyOption.REPLACE_EXISTING to overwrite.
- Files.copy(file, directory) Behavior: If the directory /enclosure already exist, it throws an exception. 
On the other hand, if the directory did not exist, the process would create a new file with the contents of food.txt, but the file would be called /enclosure.
This behavior applies to both the copy() and move() methods.
- Atomic Move Compatibility: ATOMIC_MOVE is available as a member of the StandardCopyOption type, but it will likely throw an exception if passed to a copy() method.
- Deleting Non-Empty Directories: Both Files.delete() and Files.deleteIfExists() throw an IOException if operated on a nonempty directory.
- Stream Wrappers: Be cautious of incorrect stream wrapper combinations. For example:


    new BufferedInputStream(new FileReader("z.txt")); - DOES NOT COMPILE (Should be FileInputStream).
    new BufferedWriter(new FileOutputStream("z.txt")); - DOES NOT COMPILE (Should be FileWriter).
    new ObjectInputStream(new FileOutputStream("z.txt")) - DOES NOT COMPILE (Should use FileInputStream).
    new BufferedInputStream(new InputStream()); - DOES NOT COMPILE (Should use FileInputStream).


- InputStreamReader and OutputStreamWriter: These bridge byte and character streams. They receive FileInputStream and FileOutputStream respectively.
  - Example: `new InputStreamReader(new FileInputStream("./test.txt"), "UTF-8");`
- PrintWriter Flexibility: The PrintWriter class is special in that it can take an OutputStream or a Writer.
- Serialization of static and transient fields: Marking static fields transient has little effect on serialization. 
Other than the serialVersionUID, only the instance members of a class are serialized. 
A static variable keeps it state as long as the JVM is running, so transient is ignored.
- Deserialization Constructor Call: Java will call the no-arg constructor of the first nonserializable parent class it can find in the class hierarchy.
Constructors and any instance initializations defined in the serialized class are ignored during the deserialization process.


