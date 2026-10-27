package io.zenwave360.example.outbox;

import io.zenwave360.example.core.outbound.events.dtos.Address;
import io.zenwave360.example.core.outbound.events.dtos.CustomerEvent;
import io.zenwave360.example.core.outbound.events.dtos.PaymentMethod;
import io.zenwave360.example.core.outbound.events.dtos.PaymentMethodType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * The event used to record and verify {@code event_publication.serialized_event} fixtures.
 *
 * <p>Files under {@code src/test/resources/stored-events/} hold the exact string a given
 * {@code spring-modulith-events-scs} release stored for {@link #customerEventMessage()}. They are never regenerated for
 * old versions: newer releases must keep reading them (see {@code StoredEventsResubmitIntegrationTest}).
 *
 * <p>To record a fixture for the version currently in the pom:
 * <pre>mvn verify -Dit.test=EventSerializerRoundTripIntegrationTest -Doutbox.fixture.out=src/test/resources/stored-events/&lt;version&gt;.json</pre>
 */
public final class StoredEventFixtures {

    public static final String FIXTURES_DIR = "stored-events/";
    public static final String FIXTURE_EMAIL = "stored-event-fixture@example.com";

    private StoredEventFixtures() {}

    public static CustomerEvent customerEvent() {
        return CustomerEvent.newBuilder()
                .setId(42L)
                .setName("Fixture Customer")
                .setEmail(FIXTURE_EMAIL)
                .setVersion(3)
                .setAddresses(List.of(
                        new Address("Rue del Percebe 13", "Aqui"), new Address("Calle Mayor 1", "Madrid")))
                .setPaymentMethods(List.of(new PaymentMethod(7L, PaymentMethodType.VISA, "4111111111111111")))
                .build();
    }

    /** Same shape as what the generated publisher sends: Avro payload, SCS destination binding, Long message key, CloudEvents headers. */
    public static Message<CustomerEvent> customerEventMessage() {
        return MessageBuilder.withPayload(customerEvent())
                .setHeader("spring.cloud.stream.sendto.destination", "on-customer-event-out")
                .setHeader("kafka_messageKey", 42L)
                .setHeader("ce-id", "42")
                .setHeader("ce-source", "Customer")
                .setHeader("ce-specversion", "1.0")
                .setHeader("ce-type", "CustomerEvent")
                .setHeader("ce-time", "2026-01-01T00:00:00Z")
                .build();
    }

    /** File names of every recorded fixture, one per library release (e.g. {@code 1.3.0.json}). */
    public static List<String> fixtureFiles() {
        try {
            return Arrays.stream(new PathMatchingResourcePatternResolver()
                            .getResources("classpath:" + FIXTURES_DIR + "*.json"))
                    .map(Resource::getFilename)
                    .sorted()
                    .toList();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String load(String fixtureFile) {
        try (InputStream in = StoredEventFixtures.class.getClassLoader().getResourceAsStream(FIXTURES_DIR + fixtureFile)) {
            if (in == null) {
                throw new IllegalArgumentException("Missing fixture " + FIXTURES_DIR + fixtureFile);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8).strip();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
