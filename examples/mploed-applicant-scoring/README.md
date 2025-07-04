# Project Name

Project Description


## Table of Contents


1. [Getting Started](#getting-started)
   1. [Prerequisites](#prerequisites)
2. [Technologies](#technologies)
3. [Project Structure](#project-structure)
   1. [Clean/Hexagonal Architecture](#clean-hexagonal-architecture)
   2. [Traditional 3-Tier Architecture](#traditional-3-tier-architecture)
   3. [Simple Domain Packaging](#simple-domain-packaging)
4. [Authentication and Authorization](#authentication-and-authorization)
   1. [Login](#login)
   2. [Authentication and Session Management](#authentication-and-session-management)
   3. [User Management](#user-management)
   4. [OneTimeToken Configuration](#onetimetoken-configuration)
5. [API First](#api-first)
   1. [OpenAPI / SwaggerUI](#openapi--swaggerui)
      1. [Customization](#customization)
   2. [AsyncAPI / ZenWave SDK](#asyncapi--zenwave-sdk)
6. [Domain Modeling and Code Generation with ZenWave SDK](#domain-modeling-and-code-generation-with-zenwave-sdk)
   1. [Installing ZenWave SDK](#installing-zenwave-sdk)
   2. [Modeling and Generating Code](#modeling-and-generating-code)
7. [Testing](#testing)
   1. [Rules of thumb for Testing](#rules-of-thumb-for-testing)
8. [Code Formatting](#code-formatting)


## Getting Started


After cloning the project, you can start the project with the following command:

```bash
docker-compose up -d
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Prerequisites


* Java 21+
* Maven 3.6+
* Docker/Docker Compose
* Git and Git-Bash
* JBang and ZenWave SDK (optional)
* SDKMAN! (optional but highly recommended)

## Technologies


* Spring Boot 3.3.x
* Spring Data JPA or MongoDB
* Spring Data Elasticsearch
* Spring Cloud Streams for Kafka, RabbitMQ, or other Message Brokers
* Spring Security
* KarateDSL for API Testing
* ZenWave SDK for Domain Modeling and Code Generation (optional)



## API First

### OpenAPI / SwaggerUI


The project uses `openapi-generator-maven-plugin` (see pom.xml) to generate SpringMVC interfaces and DTOs from the `src/main/resources/public/apis/openapi.yml` file.

Generated sources are placed in `target/generated-sources/openapi` which becomes a source folder for the project. To implement the API, you can create a new `@RestController` and implement the generated interface.


#### Customization


You can customize generated code with this properties in `pom.xml` or directly in the plugin `openapi-generator-maven-plugin`: 
```xml
<openApiApiPackage>${basePackage}.adapters.web</openApiApiPackage>
<openApiModelPackage>${basePackage}.adapters.web.model</openApiModelPackage>
```

SwaggerUI is available at http://localhost:8080/swagger-ui/index.html. If you need to add more OpenAPI files, you can customize SwaggerUI in `application.yml`:

```yaml
springdoc.swagger-ui.urls:
   - name: Project Name
     url: /apis/openapi.yml
```

URL is relative to 'src/main/resources/public'.


### AsyncAPI / ZenWave SDK


This project also uses `zenwave-sdk-maven-plugin` to generate interfaces, DTOs and producers/consumers using Spring Cloud Streams from the `src/main/resources/public/apis/asyncapi.yml` file.

If this file does not exist, code generation is skipped. Generated sources are placed in `target/generated-sources/zenwave` which becomes a source folder for the project.


#### Customization


You can customize generated code with this properties in `pom.xml` or directly in the plugin `zenwave-sdk-maven-plugin`:

```xml
<asyncApiModelPackage>${basePackage}.events.dtos</asyncApiModelPackage>
<asyncApiProducerApiPackage>${basePackage}.events.producer</asyncApiProducerApiPackage>
<asyncApiConsumerApiPackage>${basePackage}.events.consumer</asyncApiConsumerApiPackage>
```

See https://www.zenwave360.io/zenwave-sdk/plugins/asyncapi-spring-cloud-streams3/ for more information about API-First with AsyncAPI and ZenWave SDK.



## Domain Modeling and Code Generation with ZenWave SDK


ZenWave SDK allows you to focus on Domain Modeling and Analysis using a Domain Specific Language (ZenWave Domain Language or ZDL) to define your domain model. 

Then you can use ZenWave SDK command line or IntelliJ IDEA plugin to generate code from your analysed domain model.


### Installing ZenWave SDK


Use JBang to install a self-updating evergreen version of ZenWave SDK Command Line.

```bash
jbang alias add --fresh --force --name=zw release@zenwave360/zenwave-sdk
```

This will give you access to the `jbang zw` command line tool. You can use `jbang zw --help` to see all available commands.

```bash
jbang zw --help list
```

Install [ZenWave Domain Editor for IntelliJ IDEA](https://plugins.jetbrains.com/plugin/22858-zenwave-domain-model-editor-for-zdl) from the marketplace.

See https://www.zenwave360.io/docs/getting-started/ for more information about ZenWave SDK.


### Modeling and Generating Code


The base project comes with to files:

* `zenwave-scripts.zdl`: sample scripts you can use from IntelliJ to run ZenWave commands and generate different software artifacts.
* `zenwave-model.zdl`: sample model you can use to generate a sample application and use it as a reference for your own modeling and analysis.


## Code Formating


This project is configured to use [Spotless](https://github.com/diffplug/spotless) with Palantir Java Format as code formatter. You can apply code formatting from the command line with the following command:

```bash
mvn spotless:apply
```

You can also configure your IDE for code automatic code formating with the following plugins:

- https://plugins.jetbrains.com/plugin/13180-palantir-java-format
- https://github.com/palantir/palantir-java-format/tree/develop/eclipse_plugin

Keep a consistent code style from the beginning of the project.


### Palantir IntelliJ plugin


The plugin will be disabled by default on new projects. To manually enable it in the current project, go to `File→Settings...→palantir-java-format Settings` (or `IntelliJ IDEA→Preferences...→Other Settings→palantir-java-format Settings` on macOS) and check the Enable palantir-java-format checkbox.

To enable it by default in new projects, use `File→Other Settings→Default Settings...`.

When enabled, it will replace the normal Reformat Code action, which can be triggered from the Code menu or with the Ctrl-Alt-L (by default) keyboard shortcut.


Happy Coding!! 🚀🚀🚀


