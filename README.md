# demo-crum project

The only thing better than a Maven archetype is a repo you can fork with everything already setup. Skip the documentation and just fork-and-code.

Delete the sample code, replace with your own and you’re good to go.

## Built With

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Quarkus](https://quarkus.io/) - Framework to ease the bootstrapping and development of new Quarkus Applications
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* 	[OpenAPI](https://swagger.io/specification/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.
* 	[JUnit](https://junit.org/) - Testing framework for Java
* 	[mockito](https://swagger.io/specification/) - Mocking framework for unit tests in Java.
*   [JWT](https://jwt.io/) - JSon Web Tokens Library for token Singing/Verification


## External Tools Used

* 	[Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)

## To-Do

* 	[x] Create Quarkus project
*   [x] CRUD accounts, hospitals and institutions on Postgresql
* 	[x] Retrieve accounts, hospital and institutions
* 	[x] Log in anf log out
* 	[x] Use JWT for authorization and antentication

## Running the application locally

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `demo-crum-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/demo-crum-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/demo-crum-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

### PostgreSQL database 

to view the CRUD operations implemented

|  Username     |  Password |
|---------------|-----------|
|`sa`   | password  |

|                    Account         |     |   |
|----------------------------------------------|
|id_account | name | password | rfc | username |


### Testing on postman

### Web Page URLs

|  URL |  Method |
|----------|--------------|
|`http://localhost:8080/account/save`       | POST |
|`http://localhost:8080/account/delete/id`         | DELETE |
|`http://localhost:8080/account/update` | PUT |
|`http://localhost:8080/account/getAll` | GET |
|`http://localhost:8080/account/getById/id` | GET |
|--------------------|-----------------------|
|`http://localhost:8080/instituciones/data/` | POST |
|`http://localhost:8080/instituciones/data/` | GET |
|`http://localhost:8080/instituciones/data/id` | GET |
|--------------------|-----------------------|
|`http://localhost:8080/hospitales/data/` | POST |
|`http://localhost:8080/hospitales/data/` | GET |
|`http://localhost:8080/hospitales/data/nameSection` | GET |
|`http://localhost:8080/hospitales/data/id` | GET |

to save one user with the 'POST' operation please use the following on the body, select `raw` radio button and `JSON` from the dropdown menu at the left

###Package structure

## Files and Directories

```text
.
├── Sample_Optum
├── src
│   └── main
│       └── java
│           ├── com.optum.optumsample.controller
│           ├── com.optum.optumsample.dto
│           ├── com.optum.optumsample.model
│           ├── com.optum.optumsample.persistence
│           ├── com.optum.optumsample.security
│           └── com.optum.optumsample.security.DTO
│           └── com.optum.optumsample.security.emuns
│           └── com.optum.optumsample.service
│           
├── src
│   └── main
│       └── META-INF
│           │   └── resources
│           ├── application.properties
│           ├── csr.pem
│           └── privatekey.pem
│           └── publickey.pem
│           
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```