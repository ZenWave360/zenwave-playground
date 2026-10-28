package io.zenwave360.example.outbox;

import static org.assertj.core.api.Assertions.assertThat;

import io.zenwave360.example.core.outbound.events.dtos.CustomerEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;

/**
 * Creates a customer through the HTTP port and asserts that Spring Modulith externalizes the CustomerEvent to Kafka
 * through Spring Cloud Stream, as an Avro record with the AsyncAPI-defined headers.
 */
class CustomerOutboxIntegrationTest extends AbstractOutboxIntegrationTest {

    @Test
    void createCustomerPublishesCustomerEventToKafka() throws Exception {
        String email = uniqueEmail();
        long id = createCustomer(email);

        ConsumerRecord<Long, CustomerEvent> record = awaitCustomerEvents(email, 1).get(0);
        assertThat(record.key()).isEqualTo(id);
        assertThat(record.value().getId()).isEqualTo(id);
        assertThat(record.value().getName().toString()).isEqualTo("John Doe");
        // The Kafka header mapper rewrites CloudEvents hyphenated names (ce-type) to ce_type.
        assertThat(header(record, "ce_type")).isEqualTo("CustomerEvent");
        assertThat(header(record, "ce_source")).isEqualTo("Customer");
        assertThat(header(record, "ce_specversion")).isEqualTo("1.0");
        assertThat(header(record, "ce_id")).isEqualTo(Long.toString(id));

        awaitPublicationCompleted(email);
    }
}
