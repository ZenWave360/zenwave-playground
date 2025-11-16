package io.example.asyncapi.shoppingcart.client.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Dead Letter Topic (DLT) Handler for Shopping Cart events
 * Handles messages that failed after all retry attempts
 */
@Component("shoppingCartDltHandler")
public class ShoppingCartDltHandler {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartDltHandler.class);

    /**
     * Handles messages sent to the Dead Letter Topic after all retry attempts have been exhausted
     *
     * @param payload The message payload that failed processing
     * @param topic The original topic name
     * @param partition The partition number
     * @param offset The message offset
     * @param exception The exception that caused the failure
     */
    @DltHandler
    public void handleDlt(
            @Payload Object payload,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset,
            @Header(value = KafkaHeaders.EXCEPTION_MESSAGE, required = false) String exception) {

        log.error("Message sent to DLT after all retry attempts failed. " +
                "Topic: {}, Partition: {}, Offset: {}, Payload: {}, Exception: {}",
                topic, partition, offset, payload, exception);

        // Here you can implement custom logic for DLT messages:
        // - Store in a database for manual review
        // - Send alerts/notifications
        // - Log to a monitoring system
        // - Archive for later analysis
    }
}

