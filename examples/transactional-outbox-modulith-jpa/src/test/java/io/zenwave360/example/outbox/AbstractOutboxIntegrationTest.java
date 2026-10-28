package io.zenwave360.example.outbox;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.zenwave360.example.config.DockerComposeInitializer;
import io.zenwave360.example.core.outbound.events.dtos.CustomerEvent;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Shared plumbing for tests that check the AsyncAPI + Spring Cloud Stream + Spring Modulith integration against real
 * Postgres and Kafka: creating customers over HTTP, reading the Avro records back from the topic and inspecting the
 * Spring Modulith {@code event_publication} table.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DockerComposeInitializer.EnableDockerCompose
public abstract class AbstractOutboxIntegrationTest {

    protected static final String TOPIC = "customer.events.avro";
    protected static final Duration KAFKA_TIMEOUT = Duration.ofSeconds(30);

    @LocalServerPort
    private int port;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Value("${KAFKA_BOOTSTRAP_SERVERS}")
    private String bootstrapServers;

    @Value("${SCHEMA_REGISTRY_URL}")
    private String schemaRegistryUrl;

    protected WebTestClient webTestClient;

    @BeforeEach
    void setUpWebTestClient() {
        webTestClient = WebTestClient.bindToServer(new JdkClientHttpConnector())
                .baseUrl("http://localhost:" + port)
                .responseTimeout(Duration.ofSeconds(15))
                .build();
    }

    protected static String uniqueEmail() {
        return "outbox-" + UUID.randomUUID() + "@example.com";
    }

    /** Creates a customer (with one address and one payment method) and returns its id. */
    protected long createCustomer(String email) {
        AtomicLong id = new AtomicLong();
        webTestClient
                .post()
                .uri("/api/customers")
                .headers(headers -> headers.setBasicAuth("user", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                        """
                        {
                          "name": "John Doe",
                          "email": "%s",
                          "addresses": [
                            {
                              "street": "Rue del Percebe 13",
                              "city": "Aqui"
                            }
                          ],
                          "paymentMethods": [
                            {
                              "type": "VISA",
                              "cardNumber": "4111111111111111"
                            }
                          ]
                        }
                        """
                                .formatted(email))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.email")
                .isEqualTo(email)
                .jsonPath("$.id")
                .value(value -> id.set(((Number) value).longValue()));
        return id.get();
    }

    /** Waits until at least {@code minCount} CustomerEvents for {@code email} are on the topic; returns all of them. */
    protected List<ConsumerRecord<Long, CustomerEvent>> awaitCustomerEvents(String email, int minCount) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "customer-outbox-" + UUID.randomUUID());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        props.put("specific.avro.reader", true);
        props.put("schema.registry.url", schemaRegistryUrl);

        List<ConsumerRecord<Long, CustomerEvent>> found = new ArrayList<>();
        long deadline = System.nanoTime() + KAFKA_TIMEOUT.toNanos();
        try (KafkaConsumer<Long, CustomerEvent> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(List.of(TOPIC));
            while (System.nanoTime() < deadline && found.size() < minCount) {
                for (ConsumerRecord<Long, CustomerEvent> record : consumer.poll(Duration.ofMillis(500))) {
                    CustomerEvent event = record.value();
                    if (event != null && email.equals(String.valueOf(event.getEmail()))) {
                        found.add(record);
                    }
                }
            }
        }
        if (found.size() < minCount) {
            throw new AssertionError("Expected at least " + minCount + " CustomerEvent(s) for " + email + " on " + TOPIC
                    + " but found " + found.size());
        }
        return found;
    }

    /** Waits until no {@code event_publication} row containing {@code email} is left incomplete. */
    protected void awaitPublicationCompleted(String email) throws InterruptedException {
        long deadline = System.nanoTime() + Duration.ofSeconds(10).toNanos();
        while (System.nanoTime() < deadline) {
            if (count(email, true) > 0 && count(email, false) == 0) {
                return;
            }
            Thread.sleep(200);
        }
        throw new AssertionError("event_publication row for " + email + " was not completed");
    }

    /** Hikari runs with auto-commit disabled, so writes outside a transaction are silently rolled back. */
    protected int update(String sql, Object... args) {
        return transactionTemplate.execute(status -> jdbcTemplate.update(sql, args));
    }

    private int count(String email, boolean completed) {
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from event_publication where serialized_event like ? and completion_date is "
                        + (completed ? "not null" : "null"),
                Integer.class,
                "%" + email + "%");
        return count == null ? 0 : count;
    }

    protected static String header(ConsumerRecord<?, ?> record, String name) {
        Header header = record.headers().lastHeader(name);
        if (header == null) {
            String present = Arrays.stream(record.headers().toArray())
                    .map(candidate -> candidate.key() + "=" + new String(candidate.value(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining(", "));
            throw new AssertionError("Missing Kafka header " + name + ". Present headers: [" + present + "]");
        }
        return new String(header.value(), StandardCharsets.UTF_8);
    }
}
