package com.insurance.service.application;

import com.insurance.service.domain.event.DomainEvent;
import com.insurance.service.infrastructure.messaging.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for application services.
 * Provides common functionality for transaction management and event publishing.
 */
public abstract class BaseApplicationService {
    
    private static final Logger log = LoggerFactory.getLogger(BaseApplicationService.class);
    
    private final EventPublisher eventPublisher;
    
    protected BaseApplicationService(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    
    /**
     * Publishes a domain event.
     * This method should be called after the transaction has been committed
     * to ensure the event is only published if the transaction succeeds.
     *
     * @param event The domain event to publish
     * @param <T> The type of the domain event
     */
    @Transactional(readOnly = true)
    protected <T extends DomainEvent> void publishEvent(T event) {
        try {
            eventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.error("Failed to publish event: {}", event.getEventId(), e);
            // Depending on the business requirements, you might want to:
            // 1. Rethrow the exception
            // 2. Store the event for retry
            // 3. Log and continue
        }
    }
} 