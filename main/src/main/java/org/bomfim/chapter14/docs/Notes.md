# Chapter 14

## Topics

* Read and write console and file data using I/O Streams
* Serialize and de-serialize Java objects
* Construct, traverse, create, read and write path objects and their properties using the java.nio.file API.

## Notes

> The exam covers NIO version 2. There was a version 1 that covered channels, but it is not on the exam.

Same result:

- main/src/main/java/org/bomfim/chapter1/../chapter2/
- main/src/main/java/org/bomfim/chapter2/

> Using legacy I/O, this is the java.io.File class, whereas with NIO.2, it is the java.nio.file.Path interface.

#### Common File and Path operations

| Description                                         | I/O `File` instance method | NIO.2 `Path` instance method |
|:----------------------------------------------------|:---------------------------|:-----------------------------|
| Gets name of file/directory                         | `getName()`                | `getFileName()`              |
| Retrieves parent directory or null if there is none | `getParent()`              | `getParent()`                |
| Checks if file/directory is absolute path           | `isAbsolute()`             | `isAbsolute()`               |
| Retrieves absolute path of file/directory           | `getAbsolutePath()`        | `toAbsolutePath()`           |

#### Common File and Files operations

| Description                                                    | I/O `File` instance method | NIO.2 `Files` static method                                      |
|:---------------------------------------------------------------|:---------------------------|:-----------------------------------------------------------------|
| Deletes file/directory                                         | `delete()`                 | `deleteIfExists(Path p) throws IOException`                      |
| Checks if file/directory exists                                | `exists()`                 | `exists(Path p, LinkOption… o)`                                  |
| Checks if resource is directory                                | `isDirectory()`            | `isDirectory(Path p, LinkOption… o)`                             |
| Checks if resource is file                                     | `isFile()`                 | `isRegularFile(Path p, LinkOption… o)`                           |
| Returns the time the file was last modified                    | `lastModified()`           | `getLastModifiedTime(Path p, LinkOption… o) throws IOException`  |
| Retrieves number of bytes in file                              | `length()`                 | `size(Path p) throws IOException`                                |
| Lists contents of directory                                    | `listFiles()`              | `list(Path p) throws IOException`                                |
| Creates directory                                              | `mkdir()`                  | `createDirectory(Path p, FileAttribute… a) throws IOException`   |
| Creates directory including any nonexistent parent directories | `mkdirs()`                 | `createDirectories(Path p, FileAttribute… a) throws IOException` |
| Renames file/directory                                         | `renameTo(File dest)`      | `move(Path src, Path dest, CopyOption… o) throws IOException`    |

Many of the methods presented in this chapter declare IOException.
Common causes of a method throwing this exception include the following: 

* Loss of communication to the underlying file system. 
* File or directory exists but cannot be accessed or modified.
* File exists but cannot be overwritten.
* File or directory is required but does not exist.

### Common NIO.2 method arguments

`Files.move(source, target, LinkOption.NOFOLLOW_LINKS, StandardCopyOption.ATOMIC_MOVE);`

| Enum type            | Interface inherited | Enum value          | Details                                                                |
|:---------------------|:--------------------|:--------------------|:-----------------------------------------------------------------------|
| `LinkOption`         | `CopyOption`        | `NOFOLLOW_LINKS`    | Do not follow symbolic links.                                          |
| `StandardCopyOption` | `CopyOption`        | `ATOMIC_MOVE`       | Move file as atomic file system operation.                             |
|                      |                     | `COPY_ATTRIBUTES`   | Copy existing attributes to new file.                                  |
|                      |                     | `REPLACE_EXISTING`  | Overwrite file if it already exists.                                   |
| `StandardOpenOption` | `OpenOption`        | `APPEND`            | If file is already open for write, append to the end.                  |
|                      |                     | `CREATE`            | Create new file if it does not exist.                                  |
|                      |                     | `CREATE_NEW`        | Create new file only if it does not exist; fail otherwise.             |
|                      |                     | `READ`              | Open for read access.                                                  |
|                      |                     | `TRUNCATE_EXISTING` | If file is already open for write, erase file and append to beginning. |
|                      |                     | `WRITE`             | Open for write access.                                                 |
| `FileVisitOption`    | N/A                 | `FOLLOW_LINKS`      | Follow symbolic links.                                                 |


### Resolve and Normalize

        Path path1 = Path.of("/cats/../panther");
        Path path2 = Path.of("food");
        System.out.println(path1.resolve(path2)); // -  /cats/../panther/food
        System.out.println(path1.resolve(path2).normalize()); // - /panther/food

In case both are absolute path the last still.

    Path path3 = Path.of("/turkey/food"); 
    System.out.println(path3.resolve("/tiger/cage")); // - /tiger/cage

> On the exam, when you see resolve(), think concatenation.

Be careful, normalize a path with only ../../file.txt does nothing.

        var p3 = Path.of("../../fish.txt");
        System.out.println(p3.normalize()); // ../../fish.txt

With normalize the equals can return true.

    var p1 = Path.of("/pony/../weather.txt"); 
    var p2 = Path.of("/weather.txt");
    System.out.println(p1.equals(p2)); // false
    System.out.println(p1.normalize().equals(p2.normalize())); // true


### Relativizing

The idea is this: if you are pointed at a path in the file system, what steps would you need to take to reach the other path?
The relativize() method requires both paths to be absolute or relative and throws an exception if the types are mixed.

Think that way:

`Path.of("/test/schedule.xml").relativize(Path.of("/test/text.txt");
`1. Check if the both root or relative.
2. Remove the relative "./"
3. In case is the same bath, remove it.
4. Concat both schedule.xml/text.txt
5. Solve it to the last path. In this case ../text.txt

       Path.of("./testA/schedule.xml").relativize(Path.of("./testB/../text.txt");
       testA/schedule.xml/testB/../text.txt
       ../../testB/../text.txt
                 
Note: On Windows-based systems, it also requires that if absolute paths are used, both paths must have the same root directory or drive letter.
       
    var path3 = Path.of("C:\primate\chimpanzee"); 
    var path4 = Path.of("D:\storage\bananas.txt"); 
    path3.relativize(path4); // IllegalArgumentException

### ToRealPath

This method returns the complete path from the root. 

Note: If the path does not exist it throws an exception.

        System.out.println(Path.of("./main/src/main/..").toRealPath()); // /Users/path/java-21-cert/main/src
        System.out.println(Path.of("./main/src/").toRealPath()); // /Users/path/java-21-cert/main/src

### Recap

| Description                                       | `Path` instance method                       |
|:--------------------------------------------------|:---------------------------------------------|
| File path as string                               | `String toString()`                          |
| Single segment                                    | `Path getName(int index)`                    |
| Number of segments                                | `int getNameCount()`                         |
| Segments in range                                 | `Path subpath(int beginIndex, int endIndex)` |
| Final segment                                     | `Path getFileName()`                         |
| Immediate parent                                  | `Path getParent()`                           |
| Top-level segment                                 | `Path getRoot()`                             |
| Concatenate paths                                 | `Path resolve(String p)`                     |
|                                                   | `Path resolve(Path p)`                       |
| Construct path to one provided                    | `Path relativize(Path p)`                    |
| Remove redundant parts of path                    | `Path normalize()`                           |
| Follow symbolic links to find path on file system | `Path toRealPath()`                          |

### Be careful

Path is immutable

        Path path = Path.of("./test");
        path.resolve("test.txt");
        System.out.println(path); //./test

Like getNameCount() and getName(), subpath() is zero-indexed and does not include the root.

        Path path2 = Paths.get("/");
        System.out.println(path2.getNameCount()); //0
        //System.out.println(path2.getName(0)); //IllegalArgumentException
        var p = Path.of("/mammal/omnivore/raccoon.image");
        //var q = p.subpath(0, 4);  //IllegalArgumentException - path does not exist
        //var x = p.subpath(1, 1); // IllegalArgumentException - start and end index cannot be the same.

### Creating, Moving, and Deleting Files and Directories

#### Create directory

    public static Path createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException
    public static Path createDirectories(Path dir, FileAttribute<?>... attrs) throws IOException

Example:

    Files.createDirectory(Path.of("/bison/field"));
    Files.createDirectories(Path.of("/bison/field/pasture/green"));

#### Copying files

    public static Path copy(Path source, Path target,  CopyOption… options) throws IOException

Example:

    Files.copy(Path.of("/panda/bamboo.txt"),Path.of("/panda-save/bamboo.txt"));
    Files.copy(Path.of("/turtle"), Path.of("/turtleCopy"));

Note: When directories are copied, the copy is shallow. A shallow copy means that the files and subdirectories within the directory are not copied.

### Copying and Replacing Files

By default, if the target already exists, the copy() method will throw an exception.

You can change this behavior by providing the StandardCopyOption enum value REPLACE_EXISTING to the method.

Example:

    Files.copy(Path.of("book.txt"), 
        Path.of("movie.txt"),
        StandardCopyOption.REPLACE_EXISTING);

> For the exam, you need to know that without the REPLACE_EXISTING option, this method will throw an exception if the file already exists.


### Copying Files with I/O Streams

The Files class includes two copy() methods that operate with I/O streams.

    public static long copy(InputStream in, Path target, CopyOption… options) throws IOException 
    public static long copy(Path source, OutputStream out)throws IOException

Example:

    try (var is = new FileInputStream("source-data.txt")) { 
        // Write I/O stream data to a file 
        Files.copy(is, Path.of("/mammals/wolf.txt")); 
    } 
    
    Files.copy(Path.of("/fish/clown.xsl"), System.out);

### Moving or Renaming Paths with move()

      public static Path move(Path source, Path target, CopyOption… options) throws IOException

Example:

      Files.move(Path.of("C:\\zoo"), Path.of("C:\\zoo-new"));
      Files.move(Path.of("C:\\user\\addresses.txt"), 
         Path.of("C:\\zoo-new\\addresses2.txt"));

The first example renames the zoo directory to a zoo-new directory,
keeping all of the original contents from the source directory. 
The second example moves the addresses.txt file from the directory
user to the directory zoo-new and renames it addresses2.txt.

### Similarities between move() and copy()

Like copy(), move() requires REPLACE_EXISTING to overwrite the target if it exists; otherwise, it will throw an exception.

### Performing an Atomic Move 

Another enum value that you need to know for the exam when working with the move() method is the StandardCopyOption value ATOMIC_MOVE. 

      Files.move(Path.of("mouse.txt"), Path.of("gerbil.txt"), StandardCopyOption.ATOMIC_MOVE);

An atomic move is one in which a file is moved within the file system as a single indivisible operation.
Put another way, any process monitoring the file system never sees an incomplete 
or partially written file. If the file system does not support this feature, 
an AtomicMoveNotSupportedException will be thrown.

> Note that while ATOMIC_MOVE is available as a member of the StandardCopyOption type, it will likely throw an exception if passed to a copy() method.

### Deleting a File with delete() and deleteIfExists()

      public static void delete(Path path) throws IOException
      public static boolean deleteIfExists(Path path) throws IOException

      Files.delete(Path.of("/vulture/feathers.txt")); //Error if file does not exist
      Files.deleteIfExists(Path.of("/pigeon")); //Return true or false in case it is able to delete the file.

Note: To delete a directory, it must be empty. Both of these methods throw an exception if operated on a nonempty directory.

In addition, if the path is a symbolic link, the symbolic link will be deleted, not the path that the symbolic link points to.

### Comparing Files with isSameFile() and mismatch()

isSameFile() check if two paths refers to the same file/folder.
This can be used with relative path or symbolic link and return true if it is the same path.

Example:

      System.out.println(Files.isSameFile( 
      Path.of("/animals/cobra"), 
      Path.of("/animals/snake")));

This code can return true if snake has a link to cobra.

The mismatch() method was introduced in Java 12 to help checking the content from two files.

Example:

        System.out.println(Files.mismatch(Path.of("./text.txt"), Path.of("./test-2.txt"))); //-1
        System.out.println(Files.mismatch(Path.of("./text.txt"), Path.of("./text.txt"))); //-1
        System.out.println(Files.mismatch(Path.of("./text.txt"), Path.of("./README.md"))); //0 Fail on the first character



### Be careful

      var file = Path.of("food.txt"); 
      var directory = Path.of("/enclosure"); 
      Files.copy(file, directory);

If the directory /enclosure already exist, it throws an exception.
On the other hand, if the directory did not exist, the process would create a new file with the contents of food.txt, but the file would be called /enclosure.

> This behavior applies to both the copy() and move() methods,


### Introducing I/O Streams

Differences Between Byte and Character I/O Streams 

* Byte I/O streams read/write binary data (0s and 1s) and have class names that end in InputStream or OutputStream. 
* Character I/O streams read/write text data and have class names that end in Reader or Writer.

The byte I/O streams are primarily used to work with binary data, such as an image or executable file, while character I/O streams are used to work with text files.

#### Low-Level vs. High-Level Streams

* The Low-Level APIs connects direclty with the source of the data
* The High-Level are wrappers that gives more power and enhance performance.

Example:

      try (var br = new BufferedReader(new FileReader("zoo-data.txt"))) { 
          System.out.println(br.readLine());
      }

`FileReader` is a low-level and `BufferedReader` is a high level.

> For the exam, the only low-level stream classes you need to be familiar with are the ones that operate on files. The rest of the nonabstract stream classes are all high-level streams.

The java.io library defines four abstract classes that are the parents of all I/O stream classes defined within the API: `InputStream, OutputStream, Reader, and Writer.`


#### The java.io abstract stream base classes

| Class name     | Description                                     |
|:---------------|:------------------------------------------------|
| `InputStream`  | Abstract class for all input byte streams       |
| `OutputStream` | Abstract class for all output byte streams      |
| `Reader`       | Abstract class for all input character streams  |
| `Writer`       | Abstract class for all output character streams |

#### The java.io concrete I/O stream classes

| Class name             | Low/High level | Description                                                                                               |
|:-----------------------|:---------------|:----------------------------------------------------------------------------------------------------------|
| `FileInputStream`      | Low            | Reads file data as bytes                                                                                  |
| `FileOutputStream`     | Low            | Writes file data as bytes                                                                                 |
| `FileReader`           | Low            | Reads file data as characters                                                                             |
| `FileWriter`           | Low            | Writes file data as characters                                                                            |
| `BufferedInputStream`  | High           | Reads byte data from existing `InputStream` in buffered manner, which improves efficiency and performance |
| `BufferedOutputStream` | High           | Writes byte data to existing `OutputStream` in buffered manner, which improves efficiency and performance |
| `BufferedReader`       | High           | Reads character data from existing `Reader` in buffered manner, which improves efficiency and performance |
| `BufferedWriter`       | High           | Writes character data to existing `Writer` in buffered manner, which improves efficiency and performance  |
| `ObjectInputStream`    | High           | Deserializes primitive Java datatypes and graphs of Java objects from existing `InputStream`              |
| `ObjectOutputStream`   | High           | Serializes primitive Java data types and graphs of Java objects to existing `OutputStream`                |
| `PrintStream`          | High           | Writes formatted representations of Java objects to binary stream                                         |
| `PrintWriter`          | High           | Writes formatted representations of Java objects to character stream                                      |


      private void copyPathAsString (Path input, Path output) throws IOException {
         String string = Files.readString(input);
         Files.writeString(output, string);
      }
      private void copyPathAsBytes (Path input, Path output) throws IOException {
         byte[] bytes = Files.readAllBytes(input);
         Files.write(output, bytes);
      }
      private void copyPathAsLines (Path input, Path output) throws IOException {
         List<String> lines = Files.readAllLines(input);
         Files.write(output, lines);
      }

#### Files.readAllLines() vs. Files.lines()

      Files.readAllLines(Path.of("birds.txt")).forEach(System.out::println);  
      Files.lines(Path.of("birds.txt")).forEach(System.out::println);

The first line reads the entire file into memory.\
The second line reads lazily each line.

#### Review Common Read and Write Methods

Common I/O read and write instance methods

| Class              | Method name                                    | Description                                                                                                         |
|:-------------------|:-----------------------------------------------|:--------------------------------------------------------------------------------------------------------------------|
| All input streams  | `int read()`                                   | Reads single byte or returns -1 if no bytes available.                                                              |
| `InputStream`      | `int read(byte[] b)`                           | Reads values into buffer and returns number of bytes or characters read.                                            |
| `Reader`           | `int read(char[] c)`                           |                                                                                                                     |
| `InputStream`      | `int read(byte[] b, int offset, int length)`   | Reads up to length values into buffer starting from position offset and returns number of bytes or characters read. |
| `Reader`           | `int read(char[] c, int offset, int length)`   |                                                                                                                     |
| All output streams | `void write(int b)`                            | Writes single byte.                                                                                                 |
| `OutputStream`     | `void write(byte[] b)`                         | Writes array of values into stream.                                                                                 |
| `Writer`           | `void write(char[] c)`                         |                                                                                                                     |
| `OutputStream`     | `void write(byte[] b, int offset, int length)` | Writes length values from array into stream, starting with offset index.                                            |
| `Writer`           | `void write(char[] c, int offset, int length)` |                                                                                                                     |
| `InputStream`      | `byte[] readAllBytes()`                        | Reads data in bytes.                                                                                                |
| `BufferedReader`   | `String readLine()`                            | Reads line of data.                                                                                                 |
| `Writer`           | `void write(String line)`                      | Writes line of data.                                                                                                |
| `BufferedWriter`   | `void newLine()`                               | Writes new line.                                                                                                    |
| All output streams | `void flush()`                                 | Flushes buffered data through stream.                                                                               |
| All streams        | `void close()`                                 | Closes stream and releases resources.                                                                               |

Common Files NIO.2 read and write static methods

| Method Name                                  | Description                                                                                                   |
|:---------------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| `byte[] readAllBytes(Path path)`             | Reads all data as bytes                                                                                       |
| `String readString(Path path)`               | Reads all data into String                                                                                    |
| `List<String> readAllLines(Path path)`       | Read all data into List                                                                                       |
| `Stream<String> lines(Path path)`            | Lazily reads data                                                                                             |
| `void write(Path path, byte[] bytes)`        | Writes array of bytes                                                                                         |
| `void writeString(Path path, String string)` | Writes String                                                                                                 |
| `void write(Path path, List<String> list)`   | Writes list of lines (technically, an Iterable of CharSequence, but you don’t need to know that for the exam) |


### Be careful

The exam tries to trick with wrappers that does not exist.
      
      new BufferedInputStream(new FileReader("z.txt")); // DOES NOT COMPILE Should be FileInputStream
      new BufferedWriter(new FileOutputStream("z.txt")); // DOES NOT COMPILE Should be FileWriter
      new ObjectInputStream(new FileOutputStream("z.txt"))        // DOES NOT COMPILE Should use FileInputStream
      new BufferedInputStream(new InputStream());    // DOES NOT COMPILE Should use FileInputStream

The InputStreamReader and OutputStreamWriter receiveis FileInputStream and FileOutputStream.

        new InputStreamReader(new FileInputStream("./test.txt"), "UTF-8");
        new OutputStreamWriter(new FileOutputStream("./test.txt"), "utf-8");

> The PrintWriter class is also special in that it can take an OutputStream or a Writer

### Serializing Data

Serialization is the process of converting an in-memory object to a byte stream.
Likewise, deserialization is the process of converting from a byte stream into an object.

`import java.io.Serializable;`

> Marking static fields transient has little effect on serialization. Other than the serialVersionUID, only the instance members of a class are serialized. A static variable keeps it state as long as the JVM is running, so transient is ignored.

#### How to Make a Class Serializable

* The class must be marked Serializable.
* Every instance member of the class must be serializable, marked transient, or have a null value at the time of serialization.

Note: A record follows the same rules as other types of classes with respect to whether it can be serialized.

      record Record(String name) implements Serializable {}


#### Storing Data with ObjectOutputStream and ObjectInputStream

      // ObjectInputStream 
      public Object readObject() throws IOException, ClassNotFoundException 
      // ObjectOutputStream 
      public void writeObject(Object obj) throws IOException


#### For the exam, you need to understand how a deserialized object is created.

Java will call the no-arg constructor of the first nonserializable parent class it can find in the class hierarchy.
In our Gorilla example, this would just be the no-arg constructor of Object.

### Advanced IO operations

      // InputStream and Reader 
      public boolean markSupported() 
      public void mark(int readLimit) 
      public void reset() throws IOException
      public long skip(long n) throws IOException

Common I/O stream methods

| Method name                | Description                                                                                                                                                                       |
|:---------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `boolean markSupported()`  | Returns `true` if stream class supports `mark()` and `reset()`.                                                                                                                   |
| `void mark(int readLimit)` | Marks current position in stream, allowing `reset()` to return to this point. `readLimit` specifies the maximum number of bytes that can be read before the mark becomes invalid. |
| `void reset()`             | Attempts to reset stream to the position where `mark()` was last called.                                                                                                          |
| `long skip(long n)`        | Reads and discards specified number of characters (for `Reader`) or bytes (for `InputStream`).                                                                                    |


Attributes and view types

| Attributes interface  | View interface           | Description                                                                                         |
|:----------------------|:-------------------------|:----------------------------------------------------------------------------------------------------|
| `BasicFileAttributes` | `BasicFileAttributeView` | Basic set of attributes supported by all file systems                                               |
| `DosFileAttributes`   | `DosFileAttributeView`   | Basic set of attributes along with those supported by DOS/Windows-based systems                     |
| `PosixFileAttributes` | `PosixFileAttributeView` | Basic set of attributes along with those supported by POSIX systems, such as Unix, Linux, Mac, etc. |

      public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, 
            LinkOption… options) throws IOException


        BasicFileAttributes data = Files.readAttributes(Path.of("./text.txt"), BasicFileAttributes.class);
        System.out.println("\n--- BasicFileAttributes Information ---");
        System.out.println("Is a directory? " + data.isDirectory()); //false
        System.out.println("Is a regular file? " + data.isRegularFile()); //true
        System.out.println("Is a symbolic link? " + data.isSymbolicLink()); //false
        System.out.println("Size (in bytes): " + data.size()); //13
        System.out.println("Last modified: " + data.lastModifiedTime()); // Returns a FileTime object
        System.out.println("Creation time: " + data.creationTime());
        System.out.println("Last access time: " + data.lastAccessTime());
        System.out.println("File key: " + data.fileKey()); // Unique identifier for the file (if supported)

We can change the lastUpdate from a file:

        BasicFileAttributeView view = Files.getFileAttributeView(Path.of("./text.txt"), BasicFileAttributeView.class);
        BasicFileAttributes attributes = view.readAttributes();
        FileTime fileTime = FileTime.fromMillis(attributes.lastModifiedTime().toMillis() + 30_000_000); //+500 minutes
        view.setTimes(fileTime, null, null);

### Traversing a Directory Tree

Two common strategies are associated with walking a directory tree: a depth-first search and a breadth-first search.

A depth-first search traverses the structure from the root to an arbitrary leaf and then navigates back up toward the
root, traversing fully any paths it skipped along the way. The search depth is the distance from the root to current
node. To prevent endless searching, Java includes a search depth that is used to limit how many levels (or hops) from
the root the search is allowed to go. 

Alternatively, a breadth-first search starts at the root and processes all
elements of each particular depth before proceeding to the next depth level. The results are ordered by depth, with all
nodes at depth 1 read before all nodes at depth 2, and so on. While a breadth-first search tends to be balanced and
predictable, it also requires more memory since a list of visited nodes must be maintained. For the exam, you don’t have
to understand the details of each search strategy that Java employs; you just need to be aware that the NIO.2 Stream API
methods use depth-first searching with a depth limit, which can be optionally changed.


    public static Stream<Path> walk(Path start,  FileVisitOption… options) throws IOException
    public static Stream<Path> walk(Path start, int maxDepth,  FileVisitOption… options) throws IOException

Example:

    public static long getPathSize(Path source) throws IOException {
        try (var s = Files.walk(source, 6)) {
      //      try (var s = Files.walk(source,     FileVisitOption.FOLLOW_LINKS))     
          return s.parallel().filter(p -> !Files.isDirectory(p)).mapToLong(Exercises::getSize).sum();
         }
    }

A symbolic link could lead to a cycle in which a path is visited repeatedly. A cycle is an infinite circular dependency in which an entry in a directory tree points to one of its ancestor directories.

> Be aware that when the FOLLOW_LINKS option is used, the walk() method will track all of the paths it has visited, throwing a FileSystemLoopException if a path is visited twice.


**The find method works similiar to the walk, but it returns a Stream.**

      public static Stream<Path> find(Path start,  
        int maxDepth, 
        BiPredicate<Path, BasicFileAttributes> matcher, 
        FileVisitOption… options) throws IOException

Example:

      try (var s = Files.find(path, 10,
          (p, a) -> a.isRegularFile() && p.toString().endsWith(".java")
          && a.size() > 1000)) {
          s.forEach(System.out::println);
      }


#### Recap

TABLE 14.12 Key I/O and NIO.2 APIs

| Class          | Purpose                                          |
|:---------------|:-------------------------------------------------|
| `File`         | I/O representation of location in file system    |
| `Files`        | Helper methods for working with `Path`           |
| `Path`         | NIO.2 representation of location in file system  |
| `Paths`        | Contains factory methods to get `Path` instances |
| `InputStream`  | Superclass for reading files based on bytes      |
| `OutputStream` | Superclass for writing files based on bytes      |
| `Reader`       | Superclass for reading files based on characters |
| `Writer`       | Superclass for writing files based on characters |