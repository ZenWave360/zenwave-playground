# AsyncAPI Shopping Cart Example

> Migrated to Spring Boot 4 and JDK 25.

This example demonstrates how to use AsyncAPI together with Avro schemas and the [ZenWaveSDK AsyncAPI Generator](https://www.zenwave360.io/zenwave-sdk/plugins/asyncapi-generator)  to build a complete event-driven shopping cart application.

You will learn how to:

- define a multi-message AsyncAPI contract using Avro schemas
- generate complete Kafka producers and consumers using ZenWaveSDK
- use generated code to produce and consume events in a Spring Boot application, focusing only on your business logic

Because ZenWaveSDK can source remote or versioned AsyncAPI+Avro files, and all generated code is recreated on each build, it helps you prevent API-Drift keeping your API definitions in sync with your implementation.

The example includes end-to-end code generation, event publishing, event consumption, and local execution using Docker, Kafka, and Schema Registry.

<!-- TOC -->
* [AsyncAPI Shopping Cart Example](#asyncapi-shopping-cart-example)
    * [Modules](#modules)
        * [AsyncAPI+Avro Definition Files](#asyncapiavro-definition-files)
        * [Shopping Cart Provider Application](#shopping-cart-provider-application)
        * [Shopping Cart Client Application](#shopping-cart-client-application)
    * [Building and Running the Example](#building-and-running-the-example)
        * [Prerequisites](#prerequisites)
        * [Packaging AsyncAPI+Avro Definition Files](#packaging-asyncapiavro-definition-files)
        * [Start Docker Containers](#start-docker-containers)
        * [Start the Shopping Cart Provider Application](#start-the-shopping-cart-provider-application)
        * [Interact with the Shopping Cart Provider Application](#interact-with-the-shopping-cart-provider-application)
        * [Logging Kafka Events](#logging-kafka-events)
        * [Start the Shopping Cart Client Application](#start-the-shopping-cart-client-application)
<!-- TOC -->

## Modules

The application is composed of three modules which in real life would be in different repositories with independent lifecycles.

- `apis`: contains the AsyncAPI+Avro definition files which can be packaged and distributed as a Maven JAR artifact.
- `shopping-cart`: contains the provider application that exposes a REST API and produces events to a Kafka topic.
- `client`: contains a simple consumer application that consumes events from the Kafka topic and logs them.

### AsyncAPI+Avro Definition Files

The `apis` module contains the AsyncAPI+Avro definition files:

- [apis/asyncapi.yml](apis/asyncapi.yml):
    - contains one channel `ShoppingCartChannel` with 5 messages in Avro format, referencing *.avsc files located in the `avro` folder.
    - also contains 5 different operations, each sending one particular message to the `ShoppingCartChannel`: `onShoppingCartCreated`, `onShoppingCartItemAdded`, `onShoppingCartItemRemoved`, `onShoppingCartItemUpdated`, `onShoppingCartCheckedOut`.
- `avro/*.avsc`: Avro schema definition files.
    - contains the 5 avsc files referenced from the `asyncapi.yml` file.
    - also contains two supporting avsc files referenced from the main avsc files: `Item.avsc` and `ShoppingCart.avsc`.

This showcases how you can send multiple Avro subjects to the same Kafka topic when you need strict ordering of events of different types.

It is not the same to have a sequence like "ItemAdded, ItemUpdated, ItemRemoved" as the sequence "ItemRemoved, ItemUpdated, ItemAdded".

### Shopping Cart Provider Application

The shopping cart provider application is a simple Spring Boot application that exposes a REST API and produces events to a Kafka topic.

It uses AsyncAPI+Avro definition files to generate a complete SDK to produce events to the Kafka topic:

- Avro DTOs for all messages defined in the AsyncAPI definition.
- A `ShoppingCartEventsProducer` interface with one method per operation defined in the AsyncAPI definition.
- A `DefaultShoppingCartEventsProducer` implementation of `ShoppingCartEventsProducer` as a Spring `@Component`.
- An `InMemoryShoppingCartEventsProducer` implementation that can be used for unit testing purposes.
- An `EventsProducerInMemoryContext` so it is easier to interact with the `InMemoryShoppingCartEventsProducer` in unit tests.

All this code is generated each time as part of your Maven build. Because it is not part of the project source code, it is not versioned in Git. You will need to build the project to see it in: `target/generated-sources/zenwave-sdk`.

Because generated source is recreated on each build, the way to change the generated code is by changing the AsyncAPI+Avro definition files first and then rebuilding the project. ZenWaveSDK can source your current AsyncAPI+Avro definitions from local files, classpath resources, or remote HTTP resources, preventing API drift.

Maven configuration requires a lot of XML boilerplate code which you can find in the [shopping-cart/pom.xml#L60-L120](shopping-cart/pom.xml#L60-L120) file.

But the really interesting parts are just these few properties:

```xml
<properties>
    <basePackage>io.example.asyncapi.shoppingcart</basePackage>

    <asyncapiPrefix>classpath:io/example/asyncapi/shoppingcart/apis</asyncapiPrefix>
    <asyncapi.inputSpec>${asyncapiPrefix}/asyncapi.yml</asyncapi.inputSpec>
    <asyncapi.avro.imports>
        ${asyncapiPrefix}/avro/
    </asyncapi.avro.imports>
    <zenwave.asyncapiGenerator.templates>SpringKafka</zenwave.asyncapiGenerator.templates>

    <asyncApiProducerApiPackage>${basePackage}.events</asyncApiProducerApiPackage>
    <asyncApiConsumerApiPackage>${basePackage}.commands</asyncApiConsumerApiPackage>
</properties>
```

Or for remote HTTP resources:

```xml
<profile>
    <id>asyncapi-http-files</id>
    <properties>
        <asyncapiPrefix>https://raw.githubusercontent.com/ZenWave360/zenwave-playground/refs/heads/main/examples/asyncapi-shopping-cart/apis</asyncapiPrefix>
        <asyncapi.inputSpec>${asyncapiPrefix}/asyncapi.yml</asyncapi.inputSpec>
        <asyncapi.avro.imports>
            ${asyncapiPrefix}/avro/Item.avsc,
            ${asyncapiPrefix}/avro/ShoppingCart.avsc,
        </asyncapi.avro.imports>
    </properties>
</profile>
```

**NOTE:** `asyncapi.avro.imports` requires pointing to each avsc file individually because it is not possible to scan folders or packages from remote HTTP resources.

**NOTE 2:** You do not need to worry about ordering `asyncapi.avro.imports` even if you are using Avro prior to 1.12.0 because ZenWaveSDK will sort them for you.

This is how using generated code looks in your application code in [ShoppingCartServiceImpl](shopping-cart/src/main/java/io/example/asyncapi/shoppingcart/ShoppingCartServiceImpl.java#L46-L56):

```java
@Service
@lombok.AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    // [...]
    private final ShoppingCartEventsProducer eventsProducer; // <-- this is generated code

    public ShoppingCart addItem(Long customerId, Item input) {
        log.debug("Request addItem: {} {}", customerId, input);

        var shoppingCart = loadShoppingCart(customerId);
        shoppingCart.getItems().add(input);
        shoppingCartRepository.save(shoppingCart);
        // emit events
        var shoppingCartItemAdded = eventsMapper.asShoppingCartItemAdded(shoppingCart, input);
        eventsProducer.onShoppingCartItemAdded(shoppingCartItemAdded);
        return shoppingCart;
    }
}
```

You can also use InMemory implementation of the events producer for unit testing purposes:

```java
class ShoppingCartServiceTest {

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    ShoppingCartServiceImpl shoppingCartService = context.shoppingCartService();

    @BeforeEach void setUp() {
        context.reloadTestData();
    }

    @Test void createShoppingCartTest() {
        Long customerId = 0L;  // non existing shopping cart
        var shoppingCart = shoppingCartService.loadShoppingCart(customerId);

        Assertions.assertNotNull(shoppingCart);
        var capturedMessages = context.getEventsProducerInMemoryContext().shoppingCartEventsProducer()
                .getOnShoppingCartCreatedCapturedMessages();
        Assertions.assertEquals(1, capturedMessages.size());
    }
}
```

### Shopping Cart Client Application

This is a very simple Spring Boot application that consumes events from the Kafka topic.

It uses similar configuration to generate all required code to consume events from the Kafka topic.

The only difference in the ZenWaveSDK AsyncAPI Generator is that it uses `<role>client</role>` to reverse how code is generated: generating a consumer where the API defines a producer and vice versa.

Because it uses the same `operationIds` to generate Java code, we recommend following this naming convention so naming is consistent in both the provider and the client:

- use prefix `on*` for operations that produce event messages (`onShoppingCartItemAdded`)
- use prefix `do*` for operations that consume command messages (`doCheckoutShoppingCart`)

After code is generated you can focus on your business logic in [ShoppingCartChannelConsumerService.java](client/src/main/java/io/example/asyncapi/shoppingcart/client/events/ShoppingCartChannelConsumerService.java):

```java
@Component
public class ShoppingCartChannelConsumerService implements IShoppingCartChannelConsumerService {
    
    @Override
    public void onShoppingCartItemAdded(
            ShoppingCartItemAdded payload,
            ShoppingCartItemAddedHeaders headers) {
        log.info("onShoppingCartItemAdded: {}", payload);
    }
}
```

## Building and Running the Example

### Prerequisites

* JDK 25+
* Maven 3.8+
* Docker Compose. If you do not have Docker Compose installed, you can install [Rancher Desktop](https://rancherdesktop.io/) and configure `dockerd` as the engine (instead of `containerd`). This will include `docker` and `docker-compose` commands in your PATH.
* Your favorite IDE

### Packaging AsyncAPI+Avro Definition Files

ZenWaveSDK AsyncAPI generator can source AsyncAPI+Avro definition files from local files, classpath resources, or remote HTTP resources.

Run the following command to build and install the `apis` module in your local Maven repository, simulating what would happen in a CI/CD pipeline.

```bash
mvn clean install -f apis/pom.xml
```

Alternatively, you can append the following Maven profile to each of the commands below to source the AsyncAPI+Avro definition files from GitHub HTTP URLs: `-Pasyncapi-http-files`.

### Start Docker Containers

```bash
docker-compose -f shopping-cart/docker-compose.yml up -d
```

### Start the Shopping Cart Provider Application

```bash
mvn clean spring-boot:run -f shopping-cart/pom.xml
```

NOTE: Append `-Pasyncapi-http-files` to the previous command if you prefer to source the AsyncAPI+Avro definition files from remote HTTP resources.

### Interact with the Shopping Cart Provider Application

Navigate to `http://localhost:8080/swagger-ui/index.html` to interact with the shopping cart provider application: create, update, and check out shopping carts.

NOTE: Use Basic Authentication with username `admin` and password `password` to authenticate.

### Logging Kafka Events

Use the following command to log the events produced by the shopping cart provider application:

```bash
docker-compose -f shopping-cart/docker-compose.yml \
  exec -T schema-registry bash -c "kafka-avro-console-consumer --bootstrap-server kafka:19093 \
  --topic shopping-cart \
  --from-beginning \
  --property schema.registry.url=http://schema-registry:8081"
```

You should see the events produced by the shopping cart provider application:

```log
{"customerId":7}
{"customerId":7,"shoppingCart":{"id":2,"customerId":7,"items":[{"name":"Item Name","quantity":10}]},"item":{"name":"Item Name","quantity":10}}
{"customerId":7,"shoppingCart":{"id":2,"customerId":7,"items":[{"name":"Item Name","quantity":10}]},"item":{"name":"Item Name","quantity":10},"previousItem":{"name":"Item Name","quantity":10}}
```

### Start the Shopping Cart Client Application

```bash
mvn clean spring-boot:run -f client/pom.xml
```

You should see the client application logging the events produced by the shopping cart provider application:

```log
2025-11-22T11:58:29.112+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartCreated: {"customerId": 1}
2025-11-22T11:58:29.116+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartItemAdded: {"customerId": 1, "shoppingCart": {"id": 1, "customerId": 1, "items": [{"name": "string", "quantity": 0}]}, "item": {"name": "string", "quantity": 0}}
2025-11-22T11:58:29.124+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartCreated: {"customerId": 7}
2025-11-22T11:58:29.126+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartItemAdded: {"customerId": 7, "shoppingCart": {"id": 2, "customerId": 7, "items": [{"name": "Item Name", "quantity": 10}]}, "item": {"name": "Item Name", "quantity": 10}}
2025-11-22T11:58:29.128+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartItemUpdated: {"customerId": 7, "shoppingCart": {"id": 2, "customerId": 7, "items": [{"name": "Item Name", "quantity": 10}]}, "item": {"name": "Item Name", "quantity": 10}, "previousItem": {"name": "Item Name", "quantity": 10}}
```
