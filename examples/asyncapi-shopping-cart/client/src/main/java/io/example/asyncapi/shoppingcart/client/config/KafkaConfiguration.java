package io.example.asyncapi.shoppingcart.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.retrytopic.RetryTopicConfiguration;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka Configuration for Shopping Cart Client
 * Configures Kafka consumers and producers with Avro serialization and Schema Registry support.
 * Includes RetryableTopic configuration with 5 retry attempts before sending to DLT.
 */
@Configuration
public class KafkaConfiguration {

    private static final Logger log = LoggerFactory.getLogger(KafkaConfiguration.class);

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, Object> shoppingCartRetryableContainerFactory(
            ConsumerFactory<Long, Object> defaultConsumerFactory) {
        // Create custom consumer factory with TopicRecordNameStrategy
        Map<String, Object> props = new HashMap<>(defaultConsumerFactory.getConfigurationProperties());
        props.put("value.subject.name.strategy", "io.confluent.kafka.serializers.subject.TopicRecordNameStrategy");
        ConsumerFactory<Long, Object> customConsumerFactory = new DefaultKafkaConsumerFactory<>(props);

        // Create container factory with custom consumer factory
        ConcurrentKafkaListenerContainerFactory<Long, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(customConsumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setConcurrency(3);
        return factory;
    }

    /**
     * Retry topic configuration for shopping-cart-channel
     * - 5 retry attempts with fixed backoff of 3 seconds between attempts
     * - Custom naming strategy: {topic}-retry-{attempt} and {topic}-dlt
     * - Includes all exceptions for retry
     */
    @Bean
    public RetryTopicConfiguration shoppingCartRetryTopicConfiguration(ProducerFactory<Long, Object> defaultProducerFactory) {

        Map<String, Object> props = new HashMap<>(defaultProducerFactory.getConfigurationProperties());
        props.put("value.subject.name.strategy", "io.confluent.kafka.serializers.subject.TopicRecordNameStrategy");
        ProducerFactory<Long, Object> customProducerFactory = new DefaultKafkaProducerFactory<>(props);
        var kafkaTemplate = new KafkaTemplate<>(customProducerFactory);

        return RetryTopicConfigurationBuilder
                .newInstance()
                .maxAttempts(5) // 5 retry attempts before DLT
                .fixedBackOff(3000) // 3 seconds between retry attempts
                .retryTopicSuffix("-retry") // Custom retry topic suffix
                .dltSuffix("-dlt") // Custom DLT topic suffix
                .setTopicSuffixingStrategy(TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE) // Adds attempt number
                .includeTopic("shopping-cart") // Apply to shopping-cart topic
                .doNotRetryOnDltFailure() // Don't retry if DLT processing fails
//                .dltHandlerMethod("shoppingCartDltHandler", "handleDlt")
                .create(kafkaTemplate); // Use custom template
    }
}

