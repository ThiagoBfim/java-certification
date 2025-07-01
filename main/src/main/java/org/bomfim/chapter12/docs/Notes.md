# Chapter 12

* Modules
* Service Locator

## Topics

A module is a group of one or more packages plus a special file called module-info.java.

There are a few key differences between a module declaration and a regular Java class declaration:

* The module-info.java file must be in the root directory of your module.  Regular Java classes should be in packages. 
* The module declaration must use the keyword module instead of class, interface, or enum.
* The module name follows the naming rules for package names. It often includes periods (.) in its name.
Regular class and package names are not allowed to have dashes (-). Module names follow the same rule.

To compile a java module

    javac --module-path mods \
    -d feeding  \
    feeding/zoo/animal/feeding/*.java feeding/module-info.java

* The "--module-path" can be replaced with "-p". 

Same result: `javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java`

* The "-d" specifies the directory
* The end of the command is a list of the .java files to compile

> Be sure to memorize the module command syntax. You will be tested on it on the exam.

To run the java module

    java --module-path feeding --module zoo.animal.feeding/zoo.animal.feeding.Task

Ou

    java -p feeding -m zoo.animal.feeding/zoo.animal.feeding.Task

#### Packaging 

    jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .

This command is packaging everything under the feeding directory and storing it in a JAR file named zoo.animal.feeding.jar

### Module commands

* exports: used to export a specific class
* requires: used to allow the usage of this class from another module

> You do not need to know the output of the commands in this section. You do, however, need to know the syntax of the commands and what they do.

### Exporting a package to a specifc module

Example:

    module task {
         exports org.bomfim.task to sub.module;
    }

### Exported Types 

When `exports` is used, all the public classes, interfaces, enums, and records are exported. 

Further, any public and protected fields and methods in those files are visible.

Fields and methods that are private are not visible because they are not accessible outside the class. 

Similarly, package fields and methods are not visible because they are not accessible outside the package. 

#### Access control with modules

| Level       | Within module code                             | Outside module                                       |
|:------------|:-----------------------------------------------|:-----------------------------------------------------|
| `private`   | Available only within class                    | No access                                            |
| `Package`   | Available only within package                  | No access                                            |
| `protected` | Available only within package or to subclasses | Accessible to subclasses only if package is exported |
| `public`    | Available to all classes                       | Accessible only if package is exported               |


### Requires transitive

Requires transitive means that every module that uses the following module can also use the transitive modules.

In the case bellow the module that uses the sub.module can also use the module task.

    module sub.module {
        requires transitive task;
        exports org.bomfim.animal;
    }

### Opens

The opens directive is used to enable reflection of a package within a module. 
You only need to be aware that the opens directive exists rather than understanding it in detail for the exam.


    module sub.module {
        requires transitive task;
        exports org.bomfim.animal;
        opens org.bomfim.animal;
    }

This works and allow any kind of reflection to module org.bomfim.animal.

### Be careful

#### Transitive and requires together

Requires transitive and requires together do not compile

    module sub.module {
        requires transitive task;
        requires task;
        exports org.bomfim.animal;
    }

Java doesn’t allow you to repeat the same module in a requires clause.

Note the module that requires sub.module can requires task module too.

#### Open and opens module

The following code does not compile

    open module sub.module {
        requires transitive task;
        exports org.bomfim.animal;
        opens org.bomfim.animal;
    }

We cannot use opens and open to a module.
To fix it we can remove the opens for a specific package

    open module sub.module {
        requires transitive task;
        exports org.bomfim.animal;
    }

**Note:** open is used to the entire module\
opens is used to a specifc package.

## Creating a Service

> A service provider “interface” can be an abstract class rather than an actual interface. Since

### Service Locator

A service locator can find any classes that implement a service provider interface.

    ServiceLoader<Tour> loader = ServiceLoader.load(Tour.class);

Important, to use a ServiceLoader from another module it is required to add the uses in the module-info.java

    module main {
        uses org.bomfim.animal.AnimalService;
        requires sub.module;
    }

The module that creates the implementation from the Service should provides the module implementation.

    module sub.module {
        provides org.bomfim.service.AnimalService with org.bomfim.service.AnimalServiceImpl;
    }

> The ServiceLoader call is relatively expensive. If you are writing a real application, it is best to cache the result.

### Reviewing services

| Artifact                   | Part of the service | Directives required           |
|:---------------------------|:--------------------|:------------------------------|
| Service provider interface | Yes                 | `exports`                     |
| Service provider           | No                  | `requires`, `provides`        |
| Service locator            | Yes                 | `exports`, `requires`, `uses` |
| Consumer                   | No                  | `requires`                    |

### Reviewing directives

| Directive                                             | Description                               |
|:------------------------------------------------------|:------------------------------------------|
| `exports package;` <br/> `exports package to module;` | Makes package available outside module    |
| `requires module;`<br/> `requires transitive module;` | Specifies another module as dependency    |
| `opens package;` <br/> `opens package to module;`     | Allows package to be used with reflection |
| `provides serviceInterface with implName;`            | Makes service available                   |
| `uses serviceInterface;`                              | References service                        |

### Discovering Modules

#### Common modules

| Module name    | What it contains                                | Coverage in book                           |
| :------------- | :---------------------------------------------- | :----------------------------------------- |
| `java.base`    | Collections, math, IO, NIO.2, concurrency, etc. | Most of this book                          |
| `java.desktop` | Abstract Windows Toolkit (AWT) and Swing        | Not on exam beyond module name             |
| `java.logging` | Logging                                         | Not on exam beyond module name             |
| `java.sql`     | JDBC                                            | Not on exam beyond module name             |
| `java.xml`     | Extensible Markup Language (XML)                | Not on exam beyond module name             |

####  Java modules prefixed with java

|                       |                      |                     |                       |
|:----------------------|:---------------------|:--------------------|:----------------------|
| `java.base`           | `java.naming`        | `java.smartcardio`  | `java.compiler`       |
| `java.net.http`       | `java.sql`           | `java.datatransfer` | `java.prefs`          |
| `java.sql.rowset`     | `java.desktop`       | `java.rmi`          | `java.transaction.xa` |
| `java.instrument`     | `java.scripting`     | `java.xml`          | `java.logging`        |
| `java.se`             | `java.xml.crypto`    | `java.management`   | `java.security.jgss`  |
| `java.management.rmi` | `java.security.sasl` |                     |                       |


> We recommend reviewing this right before the exam to increase the chances of them sounding familiar. Remember that you don’t have to memorize them.

DK modules prefixed with jdk

|                     |                       |                      |                        |
|:--------------------|:----------------------|:---------------------|:-----------------------|
| `jdk.attach`        | `jdk.jconsole`        | `jdk.management.jfr` | `jdk.charsets`         |
| `jdk.jdeps`         | `jdk.naming.dns`      | `jdk.compiler`       | `jdk.jdi`              |
| `jdk.naming.rmi`    | `jdk.crypto.cryptoki` | `jdk.jdwp.agent`     | `jdk.net`              |
| `jdk.crypto.ec`     | `jdk.jfr`             | `jdk.nio.mapmode`    | `jdk.dynalink`         |
| `jdk.jlink`         | `jdk.sctp`            | `jdk.editpad`        | `jdk.jpackage`         |
| `jdk.security.auth` | `jdk.hotspot.agent`   | `jdk.jshell`         | `jdk.security.jgss`    |
| `jdk.httpserver`    | `jdk.jsobject`        | `jdk.xml.dom`        | `jdk.incubator.vector` |
| `jdk.jstatd`        | `jdk.zipfs`           | `jdk.jartool`        | `jdk.localedata`       |
| `jdk.javadoc`       | `jdk.management`      |                      |                        |


#### Describe module command line

These commands describe the java module. It should be executed in the jar file. The describe module parameter is the jar file,
the -p parameter is the path to the folder which has the jar file.

    java -p mods -d zoo.animal.feeding
    java -p mods --describe-module zoo.animal.feeding

Output:

    java -p mods --describe-module zoo.animal.feeding
    zoo.animal.feeding file:///Users/thiagobomfim/Desktop/2025/sybex-1Z0-830-chapter-12/mods/zoo.animal.feeding.jar
    exports zoo.animal.feeding
    requires java.base mandated

> Remember, the java.base module is special. It is automatically added as a dependency to all modules.

#### List module command line

    java --list-modules

The output is a listing of all the modules that come with Java and their version numbers.

We can also execute this command with a specifc folder with some jar files

    java -p mods --list-modules

The output of this command will be the list of all java modules plus the modules inside the folder mods.

#### Show module resolution command linen

    java --show-module-resolution -p feeding -m zoo.animal.feeding/zoo.animal.feeding.Task

Luckily, you don’t need to understand this output.

It starts by listing the root module. 
That’s the one we are running: zoo.animal.feeding. 
Then it lists many lines of packages included by the mandatory java.base module. 
After a while, it lists modules that have dependencies.
Finally, it outputs the result of the program: All fed!.

#### Describe jar command line

    jar -f mods/zoo.animal.feeding.jar -d
    jar --file mods/zoo.animal.feeding.jar --describe-module

Output:

    jar --file mods/zoo.animal.feeding.jar --describe-module
    zoo.animal.feeding jar:file:///Users/thiagobomfim/Desktop/2025/sybex-1Z0-830-chapter-12/mods/zoo.animal.feeding.jar/!module-info.class
    exports zoo.animal.feeding
    requires java.base mandated

> You don’t need to know this difference. You do need to know that both commands can describe a module.

### jdeps

You are expected to understand how to use jdeps with projects that have not yet been modularized to assist in identifying dependencies and problems.

The following command show all the dependencies

    jdeps zoo.dino.jar

The following command show all the dependencies in a summary way.

    jdeps -s zoo.dino.jar
    jdeps --summary zoo.dino.jar

Output example:
    
    jdeps -s zoo.dino.jar
    zoo.dino.jar -> java.base
    zoo.dino.jar -> jdk.unsupported

Show internal details

    jdeps --jdk-internals zoo.dino.jar 
    jdeps -jdkinternals zoo.dino.jar 

Output: 

    zoo.dino.jar -> jdk.unsupported
    zoo.dinos.Animatronic                              -> sun.misc.Unsafe                                    JDK internal API (jdk.unsupported)

    JDK Internal API                         Suggested Replacement
    ----------------                         ---------------------
    sun.misc.Unsafe                          See http://openjdk.java.net/jeps/260

### jmod

> JMOD files are recommended only when you have native libraries or something that can’t go inside a JAR file.

Modes using jmod

| Operation  | Description                                         |
|:-----------|:----------------------------------------------------|
| `create`   | Creates JMOD file.                                  |
| `extract`  | Extracts all files from JMOD. Works like unzipping. |
| `describe` | Prints module details such as requires.             |
| `list`     | Lists all files in JMOD file.                       |
| `hash`     | Prints or records hashes.                           |

> Conveniently, you don’t have to memorize the syntax for jmod.

### jlink

    jlink --module-path mods --add-modules zoo.animal.talks --output zooApp

First we specify where to find the custom modules with -p or --module-path. 
Then we specify our module names with --add-modules
Finally, we specify the folder name of our smaller JDK with --output.

Jlink creates a folder that contains multiple folders, libraries, and executables.
It is not packaged as a single “image file”, differnt from jpackage that creates a single executable package.

### jpackage

Unlike jlink which can only create a runtime image, the jpackage command can create an application image.

Besides output formats, a big difference between jlink and jpackage is that jpackage can package both modular 
and non-modular applications while jlink is limited to modular applications.

    jpackage --name feedingTask --module-path mods --module zoo.animal.feeding/zoo.animal.feeding.Task

First --name or -n gives the name of the application in the created filename. 
You can pass --app-version if you want a version other than 1.0, the default.
The --module-apth (or -p) and --module (or -m) parameters should look familiar from running a modular application. 
You can even call --add-modules like in jlink to add extra modules that aren't in your module-info file.

> You can create a run the jpackage command against a non-modular jar.

### Review

TABLE 12.10 Comparing command-line operations

| Description                              | Syntax                                                                                      |
|:-----------------------------------------|:--------------------------------------------------------------------------------------------|
| Compile nonmodular code                  | `javac -cp classpath -d directory classesToCompile`                                         |
|                                          | `javac --class-path classpath -d directory classesToCompile`                                |
|                                          | `javac -classpath classpath -d directory classesToCompile`                                  |
| Run nonmodular code                      | `java -cp classpath package.className`                                                      |
|                                          | `java -classpath classpath package.className`                                               |
|                                          | `java --class-path classpath package.className`                                             |
| Compile module                           | `javac -p moduleFolderName -d directory classesToCompileIncludingModuleInfo`                |
|                                          | `javac --module-path moduleFolderName -d directory classesToCompileIncludingModuleInfo`     |
| Run module                               | `java -p moduleFolderName -m moduleName/package.className`                                  |
|                                          | `java --module-path moduleFolderName --module moduleName/package.className`                 |
| Describe module                          | `java -p moduleFolderName -d moduleName`                                                    |
|                                          | `java --module-path moduleFolderName --describe-module moduleName`                          |
|                                          | `jar --file jarName --describe-module`                                                      |
|                                          | `jar -f jarName -d`                                                                         |
| List available modules                   | `java --module-path moduleFolderName --list-modules`                                        |
|                                          | `java -p moduleFolderName --list-modules`                                                   |
|                                          | `java --list-modules`                                                                       |
| View dependencies                        | `jdeps -summary --module-path moduleFolderName jarName`                                     |
|                                          | `jdeps -s --module-path moduleFolderName jarName`                                           |
|                                          | `jdeps --jdk-internals jarName`                                                             |
|                                          | `jdeps -jdkinternals jarName`                                                               |
| Show module resolution                   | `java --show-module-resolution -p moduleFolderName -m moduleName`                           |
|                                          | `java --show-module-resolution --module-path moduleFolderName --module moduleName`          |
| Create runtime JAR                       | `jlink -p moduleFolderName --add-modules moduleName --output zooApp`                        |
|                                          | `jlink --module-path moduleFolderName --add-modules moduleName --output zooApp`             |
| Create a self-contained Java application | `jpackage -n name -p moduleFolderName -m moduleName/package.className`                      |
|                                          | `jpackage --name name --module-path moduleFolderName --module moduleName/package.className` |
|                                          | `jpackage -n myApp -i myDir --main-class package.className --main-jar appJar.jar`           |
|                                          | `jpackage --name myApp --input myDir --main-class package.className --main-jar appJar.jar`  |

### Short & Easy Way to Remember Command-Line Options

#### General Rule:

General Rule for Paths:

* Non-Modular Java: Use . or -- variants:
    * `-cp` (short for `-classpath`)
    * `--class-path`

* Modular Java (finding modules):
  * `-p` (short for -`--module-path`)
  * `--module-path`

Tools Breakdown:
1. javac (Java Compiler)
   * Purpose: Compiles Java source code (.java files) into bytecode (.class files).
    * Key Flags:
   
        * `javac -cp <classpath> -d <directory> <classes> `(Non-modular compile)
        * `javac -p <moduleFolder> -d <directory> <moduleInfoAndClasses>` (Modular compile)

Memory Hook: "Java compiler needs a path (-cp/-p) to direct where to save."

2. java (Java Launcher/Runtime)
* Purpose: Executes compiled Java applications. 
* Key Flags:
  * java -cp <classpath> <mainClass> (Run non-modular)
  * java -p <moduleFolder> -m <moduleName>/<mainClass> (Run modular)
  * java -p <moduleFolder> --module <moduleName>/<mainClass> (Run modular)
  * java -p <moduleFolder> --d <moduleName> (Inspect a module)
  * java -p <moduleFolder> --describe-module <moduleName> (Inspect a module)
  * java --list-modules (See all available modules)
  * java --show-module-resolution -p <moduleFolder> -m <moduleName> (Debug module loading)

Memory Hook: "Java actually runs; it can use paths, modules, or describe/list/show module details."

3. jar (JAR Tool)
* Purpose: Creates and manages Java Archive (.jar) files.
* Key Flag (for modules):
  * -c / --create: To create a new JAR file.
  * -f / --file <jarName>: Specifies the JAR filename.
  * -C <dir\>: Specifies the directory from which to take files for creating the JAR.
  * -v / --verbose: Prints details when performing operations (verbose output).
  * -d / --describe-module: Describes module details (if the JAR contains a module).
  * jar --file <jarName> --describe-module (Describe module inside a JAR)

Memory Hook: "Jar is for files; it can describe its own module."

4. jdeps (Java Dependency Analyzer)
* Purpose: Analyzes class and module dependencies.
* Key Flags:
  * -s / --summary: Provides a summary of dependencies.
  * -p / --module-path <moduleFolder>: Specifies where to find modules when analyzing.
  * --jdk-internals / -jdkinternals: Checks for dependencies on internal JDK APIs.
  * jdeps -s -p <moduleFolder> <jarName> (Summary of dependencies)
  * jdeps --jdk-internals <jarName> (Check for internal JDK API usage)

Memory Hook: "Just deps finds paths for a summary (and internal JDK stuff)."

5. jlink (Java Linker)
* Purpose: Creates custom, lightweight Java Runtime Images (containing only necessary modules).
* Key Flags:
  * jlink -p <moduleFolder> --add-modules <moduleName> --output <appName>

Memory Hook: "Jlink links paths, adds modules, outputs a small app."

6. jpackage (Java Packager)
* Purpose: Creates self-contained Java application installers (e.g., .exe, .dmg, .deb).
* Key Flags:
  * jpackage -n <appName> -p <moduleFolder> -m <moduleName>/<mainClass> (Modular app installer)
  *  jpackage -n <appName> -i <inputDir> --main-class <mainClass> --main-jar <appJar.jar> (Non-modular app installer)

Memory Hook: "Jpackage for packages; needs a name, finds paths/modules (or input jars), makes installer."


### Type of Modules

#### Named Modules

A named module is one containing a module-info.java file.\
Named modules appear on the module path rather than the classpath.\
A named module has the name inside the module-info.java file and is on the module path.

`java -p mods -m my.app/com.example.MainApp`

#### Automatic Modules

An automatic module appears on the module path but does not contain a module-info.java file.

The Module name will be the name configured in the MANIFEST.MF or if does not exist, it will be inferred from the jar file name.

The rule is:

1. Check Manifest First: If the MANIFEST.MF does specify an Automatic-Module-Name, that name is used, and no further rules are applied.
2. Remove File Extension: The .jar (or other file extension) is removed from the end of the JAR's filename.
3. Remove Version Information: Any version string (digits and dots, potentially with extra text like -1.0.0 or -1.0-RC) is removed from the end of the name.
4. Replace Non-Alphanumeric with Dots: All characters that are not letters (a-z, A-Z) or numbers (0-9) are replaced with a single dot (.).
5. Collapse Multiple Dots: Any sequences of two or more consecutive dots are replaced with a single dot.
6. Remove Leading/Trailing Dots: If the resulting name starts or ends with a dot, that dot is removed.

Example: 

| # | Description                                                         | Example 1                       | Example 2       |
|:--|:--------------------------------------------------------------------|:--------------------------------|:----------------|
| 1 | Beginning JAR name                                                  | `commons2-x-1.0.0-SNAPSHOT.jar` | `mod_$-1.0.jar` |
| 2 | Remove file extension                                               | `commons2-x-1.0.0-SNAPSHOT`     | `mod_$-1.0`     |
| 3 | Remove version information                                          | `commons2-x`                    | `mod_$`         |
| 4 | Replace special characters                                          | `commons2.x`                    | `mod..`         |
| 5 | Replace sequence of dots                                            | `commons2.x`                    | `mod.`          |
| 6 | Remove leading/trailing dots (results in the automatic module name) | `commons2.x`                    | `mod`           |

`java -p mods -m my.app/com.example.MainApp`

#### Unnamed Modules

An unnamed module appears on the classpath. Like an automatic module, it is a regular JAR.
Unlike an automatic module, it is on the classpath rather than the module path.

This is the "default" module for any code that is placed on the classpath (using -cp or --class-path) and is not part of an explicitly defined named module.

`java -cp out com.example.MyApplication
`

### Differences Between Modulepath and Classpath

Classpath: It consists of a collection of directories, JAR, and ZIP files that contain compiled Java bytecode (.class files) and associated resources such as configuration files, property files, and other assets.

Modulepath: It is a collection of directories, JAR files, and modules that contain compiled module files (.mod files) and their associated dependencies.



## Tricks

* All modules must have a module-info.java file


## Review Questions Notes

