package org.bomfim.chapter14;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.stream.Stream;

public class Exercises {

    public static void main(String[] args) throws IOException {
        System.out.println(Files.mismatch(Path.of("./text.txt"), Path.of("./text-2.txt"))); //2
        System.out.println(Files.mismatch(Path.of("./text.txt"), Path.of("./text.txt"))); //-1
        System.out.println("\n");
        methodToCreateFile();


//        Path path1 = Path.of(".");
//        Files.createDirectories(path1);
        File file = new File("./text.txt");
        File directory = new File("./target");
        file.createNewFile();
        io(file);
        System.out.println("\nDirectory");
        io(directory);

        System.out.println("\nNIO");
        nio(file.toPath());

        Path path = Path.of("schedule.xml");
        boolean exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);


        pathImmutable();
        printPath();
        printSubPath();

        parentAndRoot();

        resolveAndNormalize();
        relativize();
        toRealPath();
        copyFile();
        mismatch();

        System.out.println("\nRead and Write operations I/O operations");

//        new BufferedInputStream(new FileInputStream("z.txt"));
//        new BufferedWriter(new FileWriter("z.txt"));
//        new ObjectInputStream(new FileInputStream("z.txt"));
//        new BufferedInputStream(new FileInputStream(path.toFile()));

//        new InputStreamReader(new FileInputStream("./test.txt"), "UTF-8");
//        new OutputStreamWriter(new FileOutputStream("./test.txt"), "utf-8");

        File fileLion = new File("./lion.txt");
        saveToFile(new MyLion("Lion", 10, "Mamal"), fileLion);
        System.out.println("\nRead object:");
        readFromFile(fileLion);
        System.out.println("""
                Mamal constructor is called because it is not Serializable.
                For the exam, make sure you understand that the constructor and any instance initializations defined in the serialized class are ignored during the deserialization process. 
                Java only calls the constructor of the first non-serializable parent class in the class hierarchy.
                """);

//        Console console = new Console(); //DOES NOT COMPILE
        Console console = System.console();
        if (console != null) {
            char[] password = console.readPassword("Enter password: ");
            String userInput = console.readLine();
            console.writer().println("Welcome to Our Zoo!");
            console.format("It has %d animals and employs %d people", 391, 25); //It has 391 animals and employs 25 people
            console.writer().println();
            console.printf("The zoo spans %5.1f acres", 128.91);
            console.flush();
            console.flush();
        } else {
            System.out.println("No console available");
        }


        readData(new FileInputStream("./text.txt"));

        System.out.println(Files.isDirectory(Path.of("./text.txt"))); //false
        System.out.println(Files.isSymbolicLink(Path.of("./text.txt"))); //false
        System.out.println(Files.isRegularFile(Path.of("./text.txt"))); //true

        System.out.print(Files.isHidden(Path.of("/walrus.txt"))); //false as this file does not exist
        System.out.print(Files.isReadable(Path.of("/seal/baby.png"))); //false as this file does not exist
        System.out.print(Files.isWritable(Path.of("dolphin.txt"))); //false as this file does not exist
        System.out.print(Files.isExecutable(Path.of("whale.png"))); //false as this file does not exist

        System.out.println("\nAttributes");
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

        BasicFileAttributeView view = Files.getFileAttributeView(Path.of("./text.txt"), BasicFileAttributeView.class);
        BasicFileAttributes attributes = view.readAttributes();
        FileTime fileTime = FileTime.fromMillis(attributes.lastModifiedTime().toMillis() + 30_000_000);
        view.setTimes(fileTime, null, null);

        long pathSize = getPathSize(Path.of("./target"));
        System.out.format("Total Size: %.2f megabytes", (pathSize / 1000000.0));

        try (var s = Files.find(Path.of("./target"), 10,
                (p, a) -> a.isRegularFile() && p.toString().endsWith(".class")
                        && a.size() > 10_000)) {
            s.forEach(System.out::println);
        }
    }

    private static long getSize(Path p) {
        try {
            return Files.size(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static long getPathSize(Path source) throws IOException {
        try (var s = Files.walk(source, 6)) {
//      try (var s = Files.walk(source,     FileVisitOption.FOLLOW_LINKS))
            return s.parallel().filter(p -> !Files.isDirectory(p)).mapToLong(Exercises::getSize).sum();
        }
    }

    public static void readData(InputStream is) throws IOException {
        System.out.print((char) is.read()); // Reads 'L'

        if (is.markSupported()) {
            System.out.println("Marked");
            is.mark(100); // Marks the current position. Reads up to 100 bytes after mark.
            // This means the stream guarantees that if you call reset()
            // within reading 100 bytes, it can return to this marked position.

            System.out.print((char) is.read()); // Reads 'I'
            System.out.print((char) is.read()); // Reads 'O'

            is.reset(); // Resets the stream to the position where mark() was called (after 'L')
            // So, the next read will be 'I' again.
        } else {
            System.out.println("\nMark/reset not supported by this InputStream.");
        }

        System.out.print((char) is.read()); // Reads 'I' (because of reset)
        long skip = is.skip(3);
        System.out.println("\n" + skip); //3
        System.out.print((char) is.read()); // Reads 'T'
        System.out.print((char) is.read()); // Reads 'H'
        System.out.print((char) is.read()); // Reads 'E'
        System.out.println(); // Add a newline for cleaner output
    }


    private static void readFromFile(File file) throws IOException {
        try (var in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(file)))
        ) {
            while (true) {
                var obj = in.readObject();
                if (obj instanceof MyLion lion) {
                    System.out.println(lion);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e) {
            // File end reached
            System.out.println("File end reached: EOFException");
        }

    }

    //    record Lion(String name, int age, transient String type) implements Serializable {} //DOES NOT COMPILE, transient not allowed

    private static void saveToFile(MyLion lion, File file) throws IOException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(lion);
        }

    }

    private void copyPathAsString(Path input, Path output) throws IOException {
        String string = Files.readString(input);
        Files.writeString(output, string);
    }

    private void copyPathAsBytes(Path input, Path output) throws IOException {
        byte[] bytes = Files.readAllBytes(input);
        Files.write(output, bytes);
    }

    private void copyPathAsLines(Path input, Path output) throws IOException {
        List<String> lines = Files.readAllLines(input);
        Files.write(output, lines);
    }

    private static void mismatch() throws IOException {
        System.out.println("\nMismatch");
        System.out.println(Files.mismatch(Path.of("./text.txt"), Path.of("./test-2.txt"))); //-1
        System.out.println(Files.mismatch(Path.of("./text.txt"), Path.of("./text.txt"))); //-1
        System.out.println(Files.mismatch(Path.of("./text.txt"), Path.of("./README.md"))); //0
    }

    private static void copyFile() throws IOException {
        try (var is = new FileInputStream("./text.txt")) {
            // Write I/O stream data to a file
            Files.copy(is, Path.of("./test-2.txt"), StandardCopyOption.REPLACE_EXISTING);
        }
        Files.copy(Path.of("./test-2.txt"), System.out); //abc

//        Files.copy(Path.of("./test-2.txt"), Path.of("./target")); //java.nio.file.FileAlreadyExistsException: ./target
    }

    private static void toRealPath() throws IOException {
        System.out.println(Path.of("./main/src/main/..").toRealPath()); // /Users/path/java-21-cert/main/src
        System.out.println(Path.of("./main/src/").toRealPath()); // /Users/path/java-21-cert/main/src
    }

    private static void relativize() {
        System.out.println("\nRelativize");
        System.out.println(Path.of("schedule.xml").relativize(Path.of("./test/text.txt"))); //../test/text.txt
        System.out.println(Path.of("./test/schedule.xml").relativize(Path.of("./test/text.txt"))); //../text.txt
//        System.out.println(Path.of("/test/schedule.xml").relativize(Path.of("./test/text.txt"))); //../text.txt //java.lang.IllegalArgumentException: 'other' is different type of Path
        System.out.println(Path.of("/test/schedule.xml").relativize(Path.of("/test/text.txt"))); //../text.txt
        System.out.println(Path.of("/test/schedule.xml").relativize(Path.of("/test/./text.txt"))); //../text.txt
        System.out.println(Path.of("/test/schedule.xml").relativize(Path.of("/test/../text.txt"))); //../../text.txt
        System.out.println(Path.of("./testA/schedule.xml").relativize(Path.of("./testB/../text.txt"))); //../../testB/../text.txt
        System.out.println("""
                   The idea is this: if you are pointed at a path in the file system, what steps would you need to take to reach the other path?
                   The relativize() method requires both paths to be absolute or relative and throws an exception if the types are mixed.
                
                   Think that way:
                
                   Path.of("/test/schedule.xml").relativize(Path.of("/test/text.txt");
                   1. Check if the both root or relative.
                   2. Remove the relative "./"
                   3. In case is the same bath, remove it.
                   4. Concat both schedule.xml/text.txt
                   5. Solve it to the last path. In this case ../text.txt
                
                   Path.of("./testA/schedule.xml").relativize(Path.of("./testB/../text.txt");
                   testA/schedule.xml/testB/../text.txt
                   ../../testB/../text.txt
                
                
                Note: On Windows-based systems, it also requires that if absolute paths are used, both paths must have the same root directory or drive letter.
                
                var path3 = Path.of("C:\\primate\\chimpanzee"); 
                var path4 = Path.of("D:\\storage\\bananas.txt"); 
                path3.relativize(path4); // IllegalArgumentException
                
                """);
    }

    private static void resolveAndNormalize() {
        Path path1 = Path.of("/cats/../panther");
        Path path2 = Path.of("food");
        System.out.println(path1.resolve(path2)); // -  /cats/../panther/food
        System.out.println(path1.resolve(path2).normalize()); // - /panther/food

        var p3 = Path.of("../../fish.txt");
        System.out.println(p3.normalize()); // ../../fish.txt

        Path path3 = Path.of("/turkey/food");
        System.out.println(path3.resolve("/tiger/cage")); // - /tiger/cage
    }

    private static void parentAndRoot() {
        Path path2 = Path.of("./abc/schedule.xml");
        System.out.println("Root from Path.of(\"./schedule.xml\"): " + path2.getRoot());
        System.out.println("Parent from Path.of(\"./schedule.xml\"): " + path2.getParent());
    }

    private static void printSubPath() {
        var p = Path.of("/mammal/omnivore/raccoon.image");
        System.out.println("Path is: " + p);
        for (int i = 0; i < p.getNameCount(); i++) {
            System.out.println("  Element " + i + " is: " + p.getName(i));
        }
        System.out.println();
        System.out.println("subpath(0,3): " + p.subpath(0, 3));
        System.out.println("subpath(1,2): " + p.subpath(1, 2));
        System.out.println("subpath(1,3): " + p.subpath(1, 3));
        System.out.println("Like getNameCount() and getName(), subpath() is zero-indexed and does not include the root.");
    }

    private static void printPath() {
        Path path = Paths.get("./target/schedule.xml");
        System.out.println(path); //./target/schedule.xml
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println("Element " + i + ": " + path.getName(i));
        }
        Path path2 = Paths.get("/");
        System.out.println(path2.getNameCount()); //0
//        System.out.println(path2.getName(0)); //IllegalArgumentException
    }

    private static void pathImmutable() {
        System.out.println("\nPath is immutable");
        Path path = Path.of("./test");
        path.resolve("test.txt");
        System.out.println(path); //./test
        System.out.println(path.resolve("test.txt")); //./test/test.txt
        System.out.println(path.resolve("/abc/../test.txt").normalize().toAbsolutePath()); ///test.txt
        System.out.println(path.resolve("/abc/../test.txt").toAbsolutePath()); ///abc/../test.txt
    }

    public static void nio(Path path) throws IOException {
        if (Files.exists(path)) {
            System.out.println("Absolute Path: " + path.toAbsolutePath());
            System.out.println("Is Directory: " + Files.isDirectory(path));
            System.out.println("Parent Path: " + path.getParent());
            if (Files.isRegularFile(path)) {
                System.out.println("Size: " + Files.size(path));
                System.out.println("Last Modified: " + Files.getLastModifiedTime(path));
            } else {
                try (Stream<Path> stream = Files.list(path)) {
                    stream.forEach(p ->
                            System.out.println("   " + p.getFileName()));
                }
            }
        } else {
            System.out.println("File or directory does not exist: " + path.toAbsolutePath());
        }
    }


    public static void io(File file) {
        if (file.exists()) {
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Is Directory: " + file.isDirectory());
            System.out.println("Parent Path: " + file.getParent());

            if (file.isFile()) {
                System.out.println("Size: " + file.length()); // Returns size in bytes
                System.out.println("Last Modified: " + file.lastModified()); // Returns time in milliseconds since epoch
            } else { // It's a directory
                File[] subfiles = file.listFiles();
                if (subfiles != null) {
                    for (File subfile : subfiles) {
                        System.out.println("   " + subfile.getName());
                    }
                } else {
                    System.out.println("   (Cannot list contents or it's not a directory)");
                }
            }
        } else {
            System.out.println("File or directory does not exist: " + file.getAbsolutePath());
        }
    }

    /**
     * This methods does not create the file
     *
     * @throws IOException
     */
    private static void methodToCreateFile() throws IOException {
        File zooFile1 = new File("stripes.txt");
//        File zooFile1 = new File("./files/tiger/data/stripes.txt");
        File zooFile2 = new File("./home/tiger", "data/stripes.txt");
        File parent = new File("./home/tiger");
        File zooFile3 = new File(parent, "data/stripes.txt");
        System.out.println(zooFile1.exists());

        Path zooPath1 = Path.of("/home/tiger/data/stripes.txt");
        Path zooPath2 = Path.of("/home", "tiger", "data", "stripes.txt");
        Path zooPathDeprecated = Paths.get("/home", "tiger", "data", "stripes.txt");
//        Path zooPath3 = Path.of(URI.create("https://www.selikoff.net"));
        System.out.println(Files.exists(zooPath1));
        Files.deleteIfExists(zooFile1.toPath());
        Files.deleteIfExists(zooFile2.toPath());
        Files.deleteIfExists(zooFile3.toPath());
    }
}
