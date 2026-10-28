# Transactional OutBox With AsyncAPI, SpringModulith and ZenWaveSDK

Implementing a Transactional OutBox With AsyncAPI, SpringModulith and ZenWaveSDK.

> **Purpose of this project:** it is a reference application and an integration test bed for
> [`io.zenwave360.sdk:spring-modulith-events-scs`](https://github.com/ZenWave360/spring-modulith-events-spring-cloud-stream),
> which plugs Spring Cloud Stream (with Avro payloads generated from AsyncAPI) into the transactional outbox
> provided by Spring Modulith Events. The outbox itself is Spring Modulith's; what is tested here is the integration
> between AsyncAPI-generated code, Spring Cloud Stream and Spring Modulith. To check a new release, bump
> `zenwave-modulith-events-scs.version` (and/or the Spring Boot / Modulith / Cloud versions) in `pom.xml` and run
> `CustomerOutboxIntegrationTest`.
>
> Library dependencies used by this project:
>
> ```xml
> <dependency><!-- Spring Cloud Stream Externalization for Message<?> -->
>     <groupId>io.zenwave360.sdk</groupId>
>     <artifactId>spring-modulith-events-scs</artifactId>
>     <version>${zenwave-modulith-events-scs.version}</version>
> </dependency>
> <dependency><!-- Needed for serializing Avro payloads to db storage as json -->
>     <groupId>com.fasterxml.jackson.dataformat</groupId>
>     <artifactId>jackson-dataformat-avro</artifactId>
> </dependency>
> ```
>
> TODO: full instructions.

<picture>
    <source
        srcset="docs/TransactionalOutBoxWithAsyncAPIAndSpringModulith-light.png"
        media="(prefers-color-scheme: light)">
    <source
        srcset="docs/TransactionalOutBoxWithAsyncAPIAndSpringModulith-dark.png"
        media="(prefers-color-scheme: dark)">
    <img
        src="docs/TransactionalOutBoxWithAsyncAPIAndSpringModulith-light.png"
        alt="Transactional OutBox"
        style="max-width: 100%;">
</picture>

We’ll explore how we can implement a Transactional Outbox Pattern to:

- Persist data to a supported transactional database (e.g., SQL or MongoDB).
- Send events to an external message broker like Kafka or RabbitMQ using Spring Cloud Stream.
- Leverage Spring Modulith Events transactional features.
- Use ZenWaveSDK Code Generator for AsyncAPI so you don’t need to write a single line of boilerplate code for the transactional outbox and event publishing.

Follow detailed instructions at https://www.zenwave360.io/posts/TransactionalOutBoxWithAsyncAPIAndSpringModulith

The publication registry diagram is in [EventPublicationRegistry.excalidraw.svg](EventPublicationRegistry.excalidraw.svg).

## Requirements

* JDK 25+
* Maven 3.8.+
* Docker Compose: in case you don't have Docker-Compose installed in your machine, install [Rancher Desktop](https://rancherdesktop.io/) and configure `dockerd` as engine (instead of `containerd`), this will include `docker` and `docker-compose` commands in your PATH.
* Your favorite IDE

## Getting Started

Use the following commands to run the application or tests:

* Start docker dependencies:

```bash
docker-compose up -d
```

* Run the application:

```bash
mvn spring-boot:run
```

* Open [Swagger UI](http://localhost:8080/swagger-ui/index.html) in your browser.
Use "Basic Authentication" with username `user` and password `password` to authenticate.

* Running Unit Tests:

```bash
mvn clean test
```

* Running Unit and Integration Tests:

```bash
mvn clean verify
```

`CustomerOutboxIntegrationTest` boots the application against Postgres and Kafka (docker-compose or Testcontainers): it creates a customer through HTTP and waits for the Avro record on `customer.events.avro`.

The integration tests live in `src/test/java/io/zenwave360/example/outbox/`:

| Test | What it checks |
|------|----------------|
| `CustomerOutboxIntegrationTest` | HTTP create -> Kafka Avro record with key and CloudEvents headers -> `event_publication` completed |
| `EventSerializerRoundTripIntegrationTest` | The Avro `Message<CustomerEvent>` survives serialize/deserialize through the library's `EventSerializer`, including rows stored by previous library releases |
| `StoredEventsResubmitIntegrationTest` | Rows in `event_publication` are marked incomplete and resubmitted: the stored JSON is read back, rebuilt as Avro and published to Kafka again |

Recorded `serialized_event` values from earlier releases are in `src/test/resources/stored-events/<version>.json` and must keep working.
When a release intentionally changes the stored format, record a new fixture for it (never overwrite old ones):

```bash
mvn verify -Dit.test=EventSerializerRoundTripIntegrationTest#avroMessageSurvivesSerializeDeserialize   -Doutbox.fixture.out=src/test/resources/stored-events/<version>.json
```

* Testing Spring Modulith Events Externalizer for Spring Cloud Stream:

```bash
curl -X 'POST' \
  'http://localhost:8080/api/customers' \
  -H 'accept: application/json' \
  -H 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "John Doe",
  "email": "me@email.com",
  "addresses": [
    {
      "street": "Rue del Percebe 13",
      "city": "Aqui"
    }
  ],
  "paymentMethods": [
    {
      "type": "VISA",
      "cardNumber": "string"
    }
  ]
}'
```

* Reading Avro messages from Kafka:

```bash
docker-compose exec -T schema-registry bash -c "kafka-avro-console-consumer --bootstrap-server kafka:19093 --topic customer.events.avro --from-beginning --property schema.registry.url=http://schema-registry:8081"
```

* Stop docker dependencies:

```bash
docker-compose down
```
