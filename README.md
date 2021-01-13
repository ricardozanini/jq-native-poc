# Jackson JQ with Native Image

In this simple demo we show how to run [Jackson JQ](https://github.com/eiiches/jackson-jq)
on [native image](https://www.graalvm.org/).

The Jackson JQ library call is wrapped around a REST endpoint that you can call
from your terminal after running the application:

## Caveats

- `jq.json` configuration file, and the classes used to marshal it needed to be manually added to the native build configuration
- For the `ServiceLoader` to work we also needed to turn `quarkus.native.auto-service-loader-registration` property to true

Having a Quarkus extension could overcome these "issues", since it would be handled by the extension.
Client applications would just use the extension and everything would work fine.

```shell
curl  -X POST -H 'Content-Type:application/json' -H 'Accept:application/json' -d '{"expression": "join(\"-\")", "document": ["1", 2]}'  http://localhost:8080/parser 
```

Click in the button below for the instructions of how to run and build locally using 
GraalVM.

<details><summary>Running the Application</summary>
<p>

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `jq-native-poc-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/jq-native-poc-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/jq-native-poc-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

Guide: [https://quarkus.io/guides/rest-json](https://quarkus.io/guides/rest-json)

</p>
</details>

## References

- [ Java Native Interface (JNI) in Native Image](https://www.graalvm.org/reference-manual/native-image/JNI/)
- [Quarkus - Building a Native Executable](https://quarkus.io/guides/building-native-image)
- [jackson-jq](https://github.com/eiiches/jackson-jq)