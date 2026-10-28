package io.zenwave360.example.outbox;

import static org.assertj.core.api.Assertions.assertThat;

import io.zenwave360.example.core.outbound.events.dtos.CustomerEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.modulith.events.core.EventSerializer;

/**
 * Checks the Avro payload survives the trip through the {@code EventSerializer} that Spring Modulith uses to write and
 * read {@code event_publication.serialized_event}, with no broker involved.
 */
class EventSerializerRoundTripIntegrationTest extends AbstractOutboxIntegrationTest {

    @Autowired
    EventSerializer eventSerializer;

    @Test
    void avroMessageSurvivesSerializeDeserialize() throws Exception {
        Message<CustomerEvent> original = StoredEventFixtures.customerEventMessage();

        Object serialized = eventSerializer.serialize(original);
        recordFixtureIfRequested(serialized);

        Message<?> restored = eventSerializer.deserialize(serialized, Message.class);
        assertRestored(original, restored);
    }

    @ParameterizedTest(name = "stored by {0}")
    @MethodSource("storedFixtures")
    void eventStoredByPreviousReleaseCanStillBeDeserialized(String fixtureFile) {
        String stored = StoredEventFixtures.load(fixtureFile);

        Message<?> restored = eventSerializer.deserialize(stored, Message.class);

        assertRestored(StoredEventFixtures.customerEventMessage(), restored);
    }

    static List<String> storedFixtures() {
        return StoredEventFixtures.fixtureFiles();
    }

    static void assertRestored(Message<CustomerEvent> expected, Message<?> restored) {
        assertThat(restored.getPayload()).isInstanceOf(CustomerEvent.class);
        // SpecificRecord equality compares every field, nested Address / PaymentMethod / enum included.
        assertThat((CustomerEvent) restored.getPayload()).isEqualTo(expected.getPayload());
        // kafka_messageKey must come back as Long or the Kafka LongSerializer rejects it.
        assertThat(restored.getHeaders().get("kafka_messageKey")).isEqualTo(42L);
        expected.getHeaders().forEach((name, value) -> {
            if (!"id".equals(name) && !"timestamp".equals(name)) {
                assertThat(restored.getHeaders()).containsEntry(name, value);
            }
        });
    }

    /** Pass {@code -Doutbox.fixture.out=<file>} to record what the library version in the pom stores. */
    private static void recordFixtureIfRequested(Object serialized) throws Exception {
        String out = System.getProperty("outbox.fixture.out");
        if (out != null) {
            Files.writeString(Path.of(out), serialized.toString() + System.lineSeparator());
        }
    }
}
