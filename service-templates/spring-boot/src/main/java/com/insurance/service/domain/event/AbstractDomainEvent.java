package com.insurance.service.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Abstract base class for domain events.
 * Provides common implementation for all domain events.
 */
public abstract class AbstractDomainEvent implements DomainEvent {

    private final UUID eventId;
    private final LocalDateTime timestamp;
    private final String eventType;
    private final String version;

    /**
     * Creates a new domain event with a random ID and the current timestamp.
     * The event type is derived from the implementing class.
     *
     * @param version The schema version of this event
     */
    protected AbstractDomainEvent(String version) {
        this(UUID.randomUUID(), LocalDateTime.now(), version);
    }

    /**
     * Creates a new domain event with the specified ID and timestamp.
     * The event type is derived from the implementing class.
     *
     * @param eventId The unique identifier for this event
     * @param timestamp The timestamp when this event occurred
     * @param version The schema version of this event
     */
    protected AbstractDomainEvent(UUID eventId, LocalDateTime timestamp, String version) {
        this.eventId = eventId;
        this.timestamp = timestamp;
        this.eventType = deriveEventType();
        this.version = version;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    @Override
    public String getVersion() {
        return version;
    }

    /**
     * Derives the event type from the implementing class name.
     *
     * @return The event type
     */
    private String deriveEventType() {
        return this.getClass().getSimpleName();
    }
} 