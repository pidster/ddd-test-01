package com.insurance.service.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Interface for domain events in the system.
 * Domain events represent something significant that happened in the domain.
 * They are used for integration between bounded contexts and for event sourcing.
 */
public interface DomainEvent {

    /**
     * Gets the unique identifier of this event.
     *
     * @return The event's ID
     */
    UUID getEventId();

    /**
     * Gets the timestamp when this event occurred.
     *
     * @return The event timestamp
     */
    LocalDateTime getTimestamp();

    /**
     * Gets the type of this event.
     * This is typically the simple class name of the implementing class.
     *
     * @return The event type
     */
    String getEventType();

    /**
     * Gets the version of this event schema.
     * This is used for event schema evolution.
     *
     * @return The event schema version
     */
    String getVersion();
} 