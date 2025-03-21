package com.insurance.service.infrastructure.messaging;

import com.insurance.service.domain.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Publisher for domain events to Kafka topics.
 * Handles the infrastructure concerns of event publishing.
 */
@Component
public class EventPublisher {
    
    private static final Logger log = LoggerFactory.getLogger(EventPublisher.class);
    
    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    public EventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    /**
     * Publishes a domain event to the appropriate Kafka topic.
     * The topic is determined by the event type.
     *
     * @param event The domain event to publish
     * @param <T> The type of the domain event
     * @return A CompletableFuture for the send operation
     */
    public <T extends DomainEvent> CompletableFuture<SendResult<String, Object>> publishEvent(T event) {
        String topic = determineTopicForEvent(event);
        String key = event.getEventId().toString();
        
        log.info("Publishing event of type {} with ID {} to topic {}", 
                event.getEventType(), event.getEventId(), topic);
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, event);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Event published successfully to topic {}, offset {}", 
                        result.getRecordMetadata().topic(), result.getRecordMetadata().offset());
            } else {
                log.error("Failed to publish event {} to topic {}", event.getEventId(), topic, ex);
            }
        });
        
        return future;
    }
    
    /**
     * Determines the appropriate Kafka topic for a given event type.
     * Convention: [application-name].[event-type-plural] (e.g., claims.domain-events)
     *
     * @param event The domain event
     * @return The topic name
     */
    private <T extends DomainEvent> String determineTopicForEvent(T event) {
        return "domain-events"; // Default topic
    }
} 