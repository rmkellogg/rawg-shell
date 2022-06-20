# rawg-shell
[![CI/CD](https://github.com/rmkellogg/rawg-shell/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/rmkellogg/rawg-shell/actions/workflows/build.yml)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Command Line interface to the [RAWG Video Games Database API](https://rawg.io/apidocs).

## Installation and Configuration

### Prerequisites
* [JDK 11 or higher](https://www.oracle.com/technetwork/java/javase/downloads)
* RAWG API key obtained from [RAWG](https://rawg.io/apidocs).

### API Key Configuration

Once ontained, set an OS Environment variable RAWG_API_KEY with the value.  If missing an exception will be thrown and the process will terminate.

Linux
```
export RAWG_API_KEY={yourapikey}
```

Windows
```
SET RAWG_API_KEY={yourapikey}
```
or using the Windows Advanced System Settings -> Environments Variables interface.

### Download latest release

Navigate to the following [Releases Page](https://github.com/rmkellogg/rawg-shell/releases/latest).  Download the latest **rawg-shell.jar** file to your computer.

### Running executable jar locally

Launch command prompt and change directory to target with JAR file.

```
java -jar rawg-shell.jar
```

## Commands

**game describe** {Title or ID}

Retrieve full game information based on RAWG ID or title.

**game export** -i {input-filename} -o {output-filename}

Retrieve game information on multiple titles.  The input-file should have one game title per line.  The output-filename will contain results in comma-seperated-value format.

**game search** {query}

Search for games using full query syntax.  Results shown on console.

## Core Technology Stack

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=flat-square&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=flat-square&logo=githubactions&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=flat-square&logo=Apache%20Maven&logoColor=white)

| Technology | Description | Use |
| -- | -- | -- |
| [Spring Boot](https://spring.io/projects/spring-boot) | REST Microservices Framework | Runtime |
| [Spring Framework](https://spring.io/projects/spring-framework) | Core Framework | Runtime |
| [Spring Shell](rawg-shell) | Command Shell Framework | Runtime |
| [Feign](https://github.com/OpenFeign/feign) | Feign REST Client | Runtime |
| [Jayway JsonPath](https://github.com/json-path/JsonPath) | Json Path Expression Library | Runtime |

## Building from Source

rawg-shell uses a [Apache Maven](https://maven.apache.org/)-based build system.

In the instructions below, `mvnw` is invoked from the root of the source tree and serves as
a cross-platform, self-contained bootstrap mechanism for the build.

### Prerequisites
[Git](https://help.github.com/set-up-git-redirect) and the [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads).

Be sure that your `JAVA_HOME` environment variable points to the `jdk-11` folder extracted from the JDK download.

### Check out sources

```
git clone https://github.com/rmkellogg/rawd-shell.git
```

### Compile and test; build all jars, distribution zips, and docs

Linux
```
./mvnw clean package
```

Windows
```
mvnw clean package
```

### Compile and SKIP tests; build all jars, distribution zips, and docs

Linux
```
./mvnw clean package -DskipTests=true
```

Windows
```
mvnw clean package -DskipTests=true
```

## Installation and Configuration from Source Code

The target folder will contain [Javadocs](https://www.baeldung.com/javadoc), a Zip file with all source code and the executable JAR file.

**This project requires that you have an API key obtained from [RAWG](https://rawg.io/apidocs).  Once ontained, set an OS Environment variable RAWG_API_KEY with the value.  If missing an exception will be thrown and the process will terminate.**

### API Key Configuration

Linux
```
export RAWG_API_KEY={yourapikey}
```

Windows
```
SET RAWG_API_KEY={yourapikey}
```
or using the Windows Advanced System Settings -> Environments Variables interface.

### Running executable jar locally

```
java -jar rawg-shell.jar
```

## License

rawg-shell is Open Source software released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).
