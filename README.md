# Testing REST API Client Automation

This repository demonstrates how to automate the process of creating a client for testing a REST API. The steps outlined here will guide you through the process, making it easy to set up and start testing your API.

## Quick Start in 3 Simple Steps

### 1. View the OpenAPI Definition

To get started, you'll need to view the OpenAPI definition of your REST API. We use the Gradle wrapper to run the project locally. Open your terminal and run the following command:

```shell
./gradlew bootrun
```

This will start the application locally. You can now view the OpenAPI definition by accessing the following URL in your web browser:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

### 2. Build the Swagger File

Now, let's build the Swagger file from the OpenAPI definition. Run the following Gradle command in your terminal:

```shell
./gradlew openApiGeneratorTest
```

This command generates the Swagger file, which is necessary for generating the client.

### 3. Generate the Client

With the Swagger file in place, you can proceed to generate the client. Run the following command:

```shell
./gradlew generateClientSDK
```

This command generates the client code, which can be used to interact with your REST API.

Now, your testing client is ready to use. You can run integration tests by executing the following Gradle command:

```shell
./gradlew integrationTest
```

This command will run integration tests using the generated client, allowing you to thoroughly test your REST API.

Congratulations! You've successfully automated the process of creating a client for testing your REST API. You can now efficiently test your API endpoints and ensure they work as expected.