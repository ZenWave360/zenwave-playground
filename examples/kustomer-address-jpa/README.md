# Kustomer Address JPA Example

Kotlin + Spring-Boot example project version of Customer Address JPA Example.


## Table of Contents


1. [Getting Started](#getting-started)
   1. [Prerequisites](#prerequisites)
2. [Technologies](#technologies)
3. [Project Structure](#project-structure)
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

You can build and run the project with either Gradle or Maven.

After cloning the project, you can start the project with the following command:

```bash
docker-compose up -d
./gradlew bootRun --args='--spring.profiles.active=local'
```

or

```bash
docker-compose up -d
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Prerequisites


* Java 24+
* Gradle 8.x (provided with ./gradlew)
* Docker/Docker Compose
* Git and Git-Bash
* JBang and ZenWave SDK (optional)
* SDKMAN! (optional but highly recommended)


## Technologies


* Spring Boot 3.5.x
* Spring Data JPA or MongoDB
* Spring Cloud Streams for Kafka
* Spring Security
* KarateDSL for API Testing
* ZenWave SDK for Domain Modeling and Code Generation (optional)


## Project Structure


### Traditional 3 Tier Architecture


```
📦 <basePackage>
   📦 web
       └─ RestControllers (spring mvc)
   📦 events
       └─ *EventListeners (spring-cloud-streams)
   📦 domain
       └─ (entities and aggregates)
   📦 service
       ├─ dtos/
       └─ ServiceInterface (inbound service interface)
       └─ 📦 implementation
           ├─ mappers/
           └─ ServiceImplementation (inbound service implementation)
   📦 repositories
       ├─ mongodb
       |   └─ CustomRepositoryImpl (spring-data custom implementation)
       └─ jpa
           └─ CustomRepositoryImpl (spring-data custom implementation)
```

### Authentication and Authorization


The configuration files are `SecurityConfiguration` (or `SecurityConfigurationAuthServer`), `OneTimeTokenConfiguration`, `UserManagementConfig`.


#### Login


The project comes configured with **LoginForm Authentication** except for SwaggerUI which is configured for Basic Authentication for convenience.

However, it has been configured for authentication exception handling with an entry point that sends a **"401 UNAUTHORIZED"** status code. This is convenient for REST API clients but has the consequence that SpringSecurity will not generate the HTML login form when a request requiring authentication is made and the user is not authenticated.

To address this inconvenience, the project includes a `src/main/resources/public/apis/login-openapi.yml` file with the necessary requests for both sending a LoginForm Authentication request and requesting and using a One-Time Authentication Token.

Default user `admin/password`


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


