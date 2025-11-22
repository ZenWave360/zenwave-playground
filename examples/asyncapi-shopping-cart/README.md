# AsyncAPI Shopping Cart Example

This example demonstrates how to use AsyncAPI with [ZenWaveSDK AsyncAPI Generator](https://www.zenwave360.io/zenwave-sdk/plugins/asyncapi-generator) to build a simple shopping cart application.

It showcases how you can use AsyncAPI+Avro definitions to generate complete SDKs including Avro DTOs, Kafka producer and consumers using Spring Cloud Stream or Spring Kafka.

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

The application is composed of tree modules which in real life would be in different repositories with independent lifecycles.

- `apis`: contains the AsyncAPI+Avro definition files which can be packed and distributed as a maven jar artifact.
- `shopping-cart`: contains the provider application that exposes a REST API and produces events to a Kafka topic.
- `client`: contains a simple consumer application that consumes events from the Kafka topic.

### AsyncAPI+Avro Definition Files

The `apis` module contains the AsyncAPI+Avro definition files:

- [apis/asyncapi.yml](apis/asyncapi.yml): 
  - contains one channel `ShoppingCartChannel` containing 5 messages in avro format, referencing *.avsc files located in `avro` folder.
  - also contains 5 different operations each sending one particular message to the `ShoppingCartChannel`: `onShoppingCartCreated`, `onShoppingCartItemAdded`, `onShoppingCartItemRemoved`, `onShoppingCartItemUpdated`, `onShoppingCartCheckedOut`.
- `avro/*.avsc`: Avro schema definition files.
  - It contains all the 5 avsc files referenced from the `asyncapi.yml` file.
  - It also contains two supporting avsc files that are referenced from the main avsc files: `Item.avsc` and `ShoppingCart.avsc`.

This showcases how you can send multiple Avro subjects to the same Kafka topic, when you need strict ordering of events of different types. 

It's not the same a sequence like "ItemAdded, ItemUpdated, ItemRemoved" than the sequence "ItemRemoved, ItemUpdated, ItemAdded".

### Shopping Cart Provider Application

The shopping cart provider application is a simple Spring Boot application that exposes a REST API and produces events to a Kafka topic.

It uses AsyncAPI+Avro definition files to generate a complete SDK to produce events to the Kafka topic:

- Avro DTOs for all the messages defined in the AsyncAPI definition.
- A `ShoppingCartEventsProducer` interface with one method per operation defined in the AsyncAPI definition.
- A `DefaultShoppingCartEventsProducer` implementation of the `ShoppingCartEventsProducer` as a Spring `@Component`.
- A `InMemoryShoppingCartEventsProducer` implementation that can be used for unit testing purposes.
- A `EventsProducerInMemoryContext` so it's easier to interact with the `InMemoryShoppingCartEventsProducer` in unit tests.

All this code is generated each time as part of your maven build, so you can focus on your business logic.

Because generated source is recreated on each build, the way to change the generated code is by changing the AsyncAPI+Avro definition files first and then build the project. ZenWaveSDK can source your _current_ AsyncAPI+Avro definitions from local files, classpath resources, or remote HTTP resources. Preventing API-Drift.

Maven configuration requires a lot of xml boilerplate code you can find in [shopping-cart/pom.xml#L60-L120](shopping-cart/pom.xml#L60-L120) file.

But the real interesting parts are just this few properties:

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

**NOTE:** `asyncapi.avro.imports` requires pointing to each avsc file individually, because it's not possible scan folders/packages from remote HTTP resources.

**NOTE 2:** You don't need to worry about ordering `asyncapi.avro.imports` even if you are using Avro previous to 1.12.0, because ZenWaveSDK will sort them for you.

This is how using generated code looks like in your application code in [ShoppingCartServiceImpl](shopping-cart/src/main/java/io/example/asyncapi/shoppingcart/ShoppingCartServiceImpl.java#L46-L56):

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

### Shopping Cart Client Application

This is a very simple Spring Boot application that consumes events from the Kafka topic.

It uses a similar configuration to generate all required code to consume events from the Kafka topic.

The only difference in ZenWaveSDK AsyncAPI Generator is that it uses `<role>client</role>` to reverse how code is generated: generating a consumer where the API defines a producer and vice versa.

Because it uses the same `operationIds` to generate Java code we recommend to follow this naming convention so naming is consistent in both the provider and the client:

- use prefix `on*` for operations that produce Event messages (`onShoppingCartItemAdded`)
- use prefix `do*` for operations that consume Command messages (`doCheckoutShoppingCart`)

After code is generated you can focus on your business logic [ShoppingCartChannelConsumerService.java](client/src/main/java/io/example/asyncapi/shoppingcart/client/events/ShoppingCartChannelConsumerService.java):

```java
@Component
public class ShoppingCartChannelConsumerService implements IShoppingCartChannelConsumerService {
    
    @Override public void onShoppingCartItemAdded(
            ShoppingCartItemAdded payload,
            ShoppingCartItemAddedHeaders headers) {
        log.info("onShoppingCartItemAdded: {}", payload);
    }
}
```

## Building and Running the Example

### Prerequisites

* JDK 21+
* Maven 3.8.+
* Docker Compose: in case you don't have Docker-Compose installed in your machine, you can install [Rancher Desktop](https://rancherdesktop.io/) and configure `dockerd` as engine (instead of `containerd`), this will include `docker` and `docker-compose` commands in your PATH.
* Your favorite IDE

### Packaging AsyncAPI+Avro Definition Files

ZenWaveSDK AsyncAPI generator can source AsyncAPI+Avro definition files from local files, classpath resources, or remote HTTP resources.

Run the following command to build and install the `apis` module in your local maven repository, simulating what would happen in the CI/CD pipeline.

```bash
mvn clean install -f apis/pom.xml
```

Alternatively, you can append the following maven profile to each of the following maven commands to source the AsyncAPI+Avro definition files from local files from GitHub http urls: `-Pasyncapi-http-files`.

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

Navigate to [Swagger UI](http://localhost:8080/swagger-ui/index.html) to interact with the shopping cart provider application: create, update, and checkout shopping carts.

NOTE: use "Basic Authentication" with username `admin` and password `password` to authenticate.

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

You should see the client application logging the events produced by the shopping cart provider application.
```log
2025-11-22T11:58:29.112+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartCreated: {"customerId": 1}
2025-11-22T11:58:29.116+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartItemAdded: {"customerId": 1, "shoppingCart": {"id": 1, "customerId": 1, "items": [{"name": "string", "quantity": 0}]}, "item": {"name": "string", "quantity": 0}}
2025-11-22T11:58:29.124+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartCreated: {"customerId": 7}
2025-11-22T11:58:29.126+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartItemAdded: {"customerId": 7, "shoppingCart": {"id": 2, "customerId": 7, "items": [{"name": "Item Name", "quantity": 10}]}, "item": {"name": "Item Name", "quantity": 10}}
2025-11-22T11:58:29.128+01:00  INFO 10652 --- [ntainer#0-0-C-1] s.c.e.ShoppingCartChannelConsumerService : onShoppingCartItemUpdated: {"customerId": 7, "shoppingCart": {"id": 2, "customerId": 7, "items": [{"name": "Item Name", "quantity": 10}]}, "item": {"name": "Item Name", "quantity": 10}, "previousItem": {"name": "Item Name", "quantity": 10}}
```
