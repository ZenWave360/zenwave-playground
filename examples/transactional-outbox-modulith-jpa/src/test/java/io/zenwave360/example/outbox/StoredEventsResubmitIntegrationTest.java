package io.zenwave360.example.outbox;

import static org.assertj.core.api.Assertions.assertThat;

import io.zenwave360.example.core.outbound.events.dtos.CustomerEvent;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.events.IncompleteEventPublications;

/**
 * Forces Spring Modulith to read stored events back from {@code event_publication} and externalize them again, which
 * is what happens after a broker outage or an application restart. The first publication of an event uses the
 * in-memory object, so only a resubmit proves the stored JSON can be turned back into the Avro payload.
 *
 * <p>The application also resubmits on a schedule, so a record may legitimately be published more than once: the tests
 * only assert on the last record, never on an exact count.
 */
class StoredEventsResubmitIntegrationTest extends AbstractOutboxIntegrationTest {

    @Autowired
    IncompleteEventPublications incompleteEventPublications;

    @Test
    void incompletePublicationIsResubmittedToKafkaWithTheSameAvroPayload() throws Exception {
        String email = uniqueEmail();
        long id = createCustomer(email);
        ConsumerRecord<Long, CustomerEvent> first = awaitCustomerEvents(email, 1).get(0);
        awaitPublicationCompleted(email);

        markIncomplete(email);
        incompleteEventPublications.resubmitIncompletePublications(publication -> true);

        List<ConsumerRecord<Long, CustomerEvent>> records = awaitCustomerEvents(email, 2);
        ConsumerRecord<Long, CustomerEvent> republished = records.get(records.size() - 1);
        assertThat(republished.value()).isEqualTo(first.value());
        assertThat(republished.key()).isEqualTo(id);
        assertThat(header(republished, "ce_type")).isEqualTo(header(first, "ce_type"));
        assertThat(header(republished, "ce_id")).isEqualTo(header(first, "ce_id"));
        awaitPublicationCompleted(email);
    }

    @ParameterizedTest(name = "stored by {0}")
    @MethodSource("storedFixtures")
    void eventStoredByPreviousReleaseIsPublishedToKafka(String fixtureFile) throws Exception {
        // Reuse a real row for listener_id / event_type, and swap in the recorded payload.
        String realEmail = uniqueEmail();
        createCustomer(realEmail);
        awaitCustomerEvents(realEmail, 1);
        awaitPublicationCompleted(realEmail);

        // A unique email lets us find this record among the ones left on the topic by previous runs.
        String email = uniqueEmail();
        String stored = StoredEventFixtures.load(fixtureFile).replace(StoredEventFixtures.FIXTURE_EMAIL, email);
        int updated = update(
                "update event_publication set serialized_event = ?, completion_date = null where serialized_event like ?",
                stored,
                "%" + realEmail + "%");
        assertThat(updated).isEqualTo(1);

        incompleteEventPublications.resubmitIncompletePublications(publication -> true);

        ConsumerRecord<Long, CustomerEvent> record =
                awaitCustomerEvents(email, 1).get(0);
        CustomerEvent expected = StoredEventFixtures.customerEvent();
        expected.setEmail(email);
        assertThat(record.value()).isEqualTo(expected);
        assertThat(record.key()).isEqualTo(42L);
        assertThat(header(record, "ce_type")).isEqualTo("CustomerEvent");
        assertThat(header(record, "ce_id")).isEqualTo("42");
        awaitPublicationCompleted(email);
    }

    static List<String> storedFixtures() {
        return StoredEventFixtures.fixtureFiles();
    }

    private void markIncomplete(String email) {
        int updated = update(
                "update event_publication set completion_date = null where serialized_event like ?",
                "%" + email + "%");
        assertThat(updated).isEqualTo(1);
    }
}
