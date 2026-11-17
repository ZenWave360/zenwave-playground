# ZenWave Java Archetype Baseapp

#[[
A Java + Spring Boot service created from the ZenWave archetype. Its structure is generated from a ZenWave domain model (ZDL) with the ZenWave SDK, and API contracts (OpenAPI, AsyncAPI) are turned into code on every Maven build.

1. [Getting Started](#getting-started)
2. [Prerequisites](#prerequisites)
3. [Technologies and Versions](#technologies-and-versions)
4. [Common Commands](#common-commands)
5. [Project Structure](#project-structure)
6. [Install the ZenWave tooling](#install-the-zenwave-tooling)
7. [Code Generation](#code-generation)
   1. [From the domain model: zenwave-scripts.zw](#from-the-domain-model-zenwave-scriptszw)
   2. [At build time: OpenAPI and AsyncAPI (Maven)](#at-build-time-openapi-and-asyncapi-maven)
8. [Spec-Driven Development with zenwave-sdd](#spec-driven-development-with-zenwave-sdd)
9. [Testing](#testing)
10. [Code Formatting](#code-formatting)

## Getting Started

```bash
docker-compose up -d     # local infrastructure (database, kafka...)
mvn spring-boot:run
```

* SwaggerUI: http://localhost:8080/swagger-ui/index.html
* Actuator: http://localhost:8080/actuator

## Prerequisites

* Java 25+
* Maven 3.6+
* Docker / Docker Compose (local infrastructure and integration tests)
* Git and Git Bash (all commands below are Git Bash)
* JBang and the ZenWave CLI (`jbang zw`), see [Install the ZenWave tooling](#install-the-zenwave-tooling)
* IntelliJ IDEA (or VS Code) with the ZenWave plugin, for editing `.zdl` / `.zw` files and running the generators
* SDKMAN! (optional, recommended for Java and Maven)

## Technologies and Versions

Versions are managed in `pom.xml` (`<properties>` and the Spring Boot parent); this is a snapshot at generation time.

| Area | Technology | Version |
|---|---|---|
| Language | Java | 25 |
| Framework | Spring Boot | 4.1.x |
| | Spring Cloud (Stream) | 2025.1.x |
| Persistence | Spring Data JPA + PostgreSQL, or Spring Data MongoDB | (Boot managed) |
| Messaging | Spring Cloud Stream for Kafka + Schema Registry client (optional) | (Cloud managed) |
| Modular monolith | Spring Modulith + transactional outbox (`spring-modulith-events-scs`) (optional) | 2.1.x |
| DDD / Architecture | jMolecules + ArchUnit (optional) | 2025.0.x |
| Security | Spring Security | (Boot managed) |
| API docs | springdoc-openapi (SwaggerUI) | 2.8.x |
| Mapping / boilerplate | MapStruct, Lombok | 1.6.x |
| Code generation | ZenWave SDK, openapi-generator-maven-plugin | 2.8.x, 7.17.x |
| Testing | JUnit 5, Spring Boot Test, Testcontainers, ArchUnit, JaCoCo | |
| Formatting | Spotless + Palantir Java Format, spring-javaformat | |

## Common Commands

| Command | What it does |
|---|---|
| `docker-compose up -d` / `docker-compose down` | start / stop local infrastructure |
| `mvn spring-boot:run` | run the application (default profile, against docker-compose services) |
| `mvn clean compile` | compile, including OpenAPI / AsyncAPI code generation |
| `mvn -DskipTests clean test-compile` | regenerate API code and compile everything, no tests |
| `mvn test` | unit tests (`*Test`) |
| `mvn verify` | unit + integration tests (`*IT`, `*IntegrationTest`) and JaCoCo report |
| `mvn spotless:apply` | format the code |
| `jbang zw --help list` | list ZenWave SDK plugins |

## Project Structure

Files at the project root:

| Path | Purpose |
|---|---|
| `zenwave-model.zdl` | the domain model (ZDL): entities, aggregates, services, events. A sample: replace it with your own |
| `zenwave-scripts.zw` | the ZenWave SDK plugins to run against the model, with their configuration |
| `pom.xml` | build, dependencies and the OpenAPI / AsyncAPI generators; base packages in `<properties>` |
| `docker-compose.yml` | local infrastructure (PostgreSQL or MongoDB; Kafka, Zookeeper, Schema Registry) |
| `src/main/java/<package>/config` | Spring configuration (security, async, database, web, exception translation...) |
| `src/main/resources/application*.yml` | application configuration (`application-default.yml` for local runs) |
| `src/main/resources/public/apis` | `openapi.yml` and `asyncapi.yml`, the API contracts |
| `src/test/resources/application-test.yml` | test configuration |
| `target/generated-sources/{openapi,zenwave}` | code generated on every build, never edited by hand |

The rest of the code (domain, services, repositories, controllers, events, tests) is generated from the model. Its packaging depends on the `layout` setting in `zenwave-model.zdl`:

| Layout | Packaging |
|---|---|
| `LayeredProjectLayout` (default) | traditional 3-tier: web, service, domain model, repositories |
| `DefaultProjectLayout` / `HexagonalProjectLayout` / `CleanHexagonalProjectLayout` | hexagonal: `core` (domain, inbound, outbound, implementation), `adapters`, `infrastructure` |
| `CleanArchitectureProjectLayout` | clean architecture rings |
| `SimpleDomainProjectLayout` | one package per aggregate; small services or modulith modules |

Keep the `layout.*` packages in the ZDL matching the `openApi*` / `asyncApi*` package properties in `pom.xml`.

## Install the ZenWave tooling

Follow **[ZenWave Getting Started](https://www.zenwave360.io/docs/getting-started/)**. It covers installing the ZenWave CLI with JBang and the IDE plugins for editing `.zdl` and `.zw` files and running the generators. Quick reference for the CLI:

```bash
jbang alias add --fresh --force --name=zw release@zenwave360/zenwave-sdk
jbang zw --help list
```

IntelliJ plugin: [ZenWave Domain Model Editor for ZDL](https://plugins.jetbrains.com/plugin/22858-zenwave-domain-model-editor-for-zdl).

## Code Generation

Code comes from two places: the ZenWave SDK, run from `zenwave-scripts.zw` when the domain model changes, and Maven plugins that run on every build for the API contracts.

### From the domain model: zenwave-scripts.zw

The plugins in `zenwave-scripts.zw`, in the order to run them:

| Plugin | Generates |
|---|---|
| `ZDLToOpenAPIPlugin` | `src/main/resources/public/apis/openapi.yml` from the ZDL services |
| `ZDLToAsyncAPIPlugin` | `src/main/resources/public/apis/asyncapi.yml` from the ZDL events |
| `BackendApplicationDefaultPlugin` | domain entities, repositories, services and their implementations, DTOs, mappers, events, listeners, in-memory test configs and tests |
| `OpenAPIControllersPlugin` | Spring MVC controllers implementing the OpenAPI interfaces |
| `SpringWebTestClientPlugin` | API tests (per operation, and per business flow) |

**How to run them:** open `zenwave-scripts.zw` in the IDE with the ZenWave plugin and use the play button next to each plugin. Hovering the button shows the equivalent command line (`jbang zw -p <Plugin> --deps <...> key=value ...`), which you can copy and run from a terminal. `jbang zw` doesn't take a `.zw` file directly: each plugin block is one command.

After generating:

```bash
mvn -DskipTests clean test-compile    # also runs the Maven generators below
git add -A && git commit -m "feat: generate code from zenwave-model.zdl"
```

**Regenerating safely.** Some files are *generated once* and then belong to you: service implementations, event listeners and their MapStruct mappers, tests, `src/test/resources/data*` and `DockerComposeInitializer`. A normal run doesn't touch them. A run with `--force` / `-f` overwrites them, so check the command the IDE generates, and run `git diff` after every regeneration.

### At build time: OpenAPI and AsyncAPI (Maven)

These run in `generate-sources` on every `mvn compile`, from the API files the plugins above wrote. Their output goes to `target/` and is never edited by hand.

**OpenAPI** (`openapi-generator-maven-plugin`, generator `spring`):
* reads `src/main/resources/public/apis/openapi.yml`;
* generates the Spring MVC API interfaces and the `*DTO` models into `target/generated-sources/openapi` (`interfaceOnly`: the controllers from `OpenAPIControllersPlugin` implement them);
* packages are set by the `openApiApiPackage` / `openApiModelPackage` properties in `pom.xml`.

**AsyncAPI** (`zenwave-sdk-maven-plugin`, generator `AsyncAPIGenerator`, profile `asyncapi-code-generation`):
* the profile **activates only when `src/main/resources/public/apis/asyncapi.yml` exists**. Without the file, nothing is generated;
* generates, as the API `provider`, the message DTOs and the Spring Cloud Stream producers and consumer interfaces into `target/generated-sources/zenwave`;
* with Spring Modulith, it also wires the transactional outbox (`transactionalOutbox=modulith`) and the application-event listener;
* packages are set by the `asyncApiModelPackage`, `asyncApiProducerApiPackage` and `asyncApiConsumerApiPackage` properties. See the [AsyncAPI generator docs](https://www.zenwave360.io/zenwave-sdk/plugins/asyncapi-generator/).

To serve more API files in SwaggerUI, add them to `application.yml` (`springdoc.swagger-ui.urls`, URLs relative to `src/main/resources/public`).

## Spec-Driven Development with zenwave-sdd

The generator creates the structure: entities, services, events, mappers and test skeletons, and leaves the behavior as `TODO`s. To complete it with AI coding agents (Claude Code, Codex, Grok...) and GitHub Spec Kit, follow **[zenwave-sdd](https://github.com/ZenWave360/zenwave-sdd)**: installing the skills, configuring the project overlay, and the full workflow in the [zenwave-sdd manual](https://github.com/ZenWave360/zenwave-sdd/blob/main/MANUAL.md).

The rule: **models own structure.** If a behavior needs a field, event, command or state that the ZDL doesn't have, fix the ZDL and regenerate. Never hand-add it to code.

## Testing

* JUnit 5 and Spring Boot Test
* `WebTestClient` API tests, generated by `SpringWebTestClientPlugin`
* In-memory implementations of repositories for fast unit tests (generated by ZenWave SDK)
* Testcontainers with docker-compose for integration tests
* ArchUnit (and jMolecules, when enabled) for architecture rules; Spring Modulith tests for module boundaries
* JaCoCo coverage report on `mvn verify`

Naming: `*Test` runs with Surefire (`mvn test`); `*IT` and `*IntegrationTest` run with Failsafe (`mvn verify`).

Rules of thumb:

* Favor functional, vertical (acceptance) tests over unit tests where every dependency is mocked.
  * Use `@SpringBootTest` for the most important use cases.
  * Use the in-memory dependencies for all use cases.
* Adapters that connect to external resources **must** be tested against real infrastructure (Testcontainers), even if it doesn't add coverage.
* Mockito is a last resort; prefer in-memory repositories and WireMock.

## Code Formatting

[Spotless](https://github.com/diffplug/spotless) with Palantir Java Format:

```bash
mvn spotless:apply     # format
mvn spotless:check     # verify (e.g. in CI)
```

IDE plugins: [palantir-java-format for IntelliJ](https://plugins.jetbrains.com/plugin/13180-palantir-java-format) (enable it per project in `Settings → palantir-java-format Settings`) or the [Eclipse plugin](https://github.com/palantir/palantir-java-format/tree/develop/eclipse_plugin). Keep a consistent code style from the beginning of the project.

Happy Coding!! 🚀🚀🚀
]]#
