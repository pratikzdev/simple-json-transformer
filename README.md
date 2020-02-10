Coding Challenge
===========================

This solution has been implemented in Java.

Prerequisites-
-------------
- JDK 11+ installed on your machine
- Basic Maven installation for your platform (see installation section below)

Installation-
-------------

- Java 11+
Find some basic info for all Systems in the [install installation][javainstall]
Set the environment variable `JAVA_HOME` and add the bin directory to you `PATH` environment variable;

    ```
    export JAVA_HOME=<your path to jdk directory>
    export PATH=$PATH:$JAVA_HOME/bin
    ````

- Maven
Maven installed on your machine
Find some basic info for all systems in the [install instructions][mvninstall].
The official way to install Maven is to download and unpack the binary ZIP file to a common directory, set the environment variable `M2_HOME` and add the bin directory to your `PATH` environment variable.

    ```
    export M2_HOME=<your path to maven directory>
    export PATH=$PATH:$M2_HOME/bin
    ```
    After installing both make sure you check its installed properly

    ```
    java --version
    mvn --version
    ```
Both of above commands should give proper output without any errors.

#### References
Following reference will help in installation of JDK and maven -
- [JDK Installation][javainstall]
- [Maven Installation][mvninstall]


# Run the program
-------------

- Clone the git repo from [github][gitrepo]

- Navigate to root of repo in your local directory.

- Run the following command, you can set the -Djava.io.tmpdir to custom path if, this is the path where the jar will be exported.

- Execute  `mvn clean install -Djava.io.tmpdir=/tmp`

- I have a basic test.json created at root level you can run -
`cat test.json | java -jar /tmp/simple-json-transformer.jar`

You should see the output in the console.


# Assumptions
-------------

The input will be a JSON object
All keys named in the original object will be simple strings without ‘.’ characters
The input JSON will not contain arrays


[javainstall]: https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A
[mvninstall]: http://books.sonatype.com/mvnex-book/reference/installation-sect-maven-install.html
[gitrepo]: https://github.com/pratikzdev/simple-json-transformer
