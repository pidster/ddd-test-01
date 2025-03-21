package com.insurance.service.domain.model;

import com.insurance.service.domain.event.DomainEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Base class for all aggregate roots in the domain model.
 * Provides common functionality for identity, versioning, and event registration.
 */
@MappedSuperclass
public abstract class BaseAggregateRoot {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "version")
    private int version;

    @Transient
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    /**
     * Default constructor.
     * Initializes the aggregate with a new random UUID and sets creation and update timestamps.
     */
    protected BaseAggregateRoot() {
        this(UUID.randomUUID());
    }

    /**
     * Constructor with ID.
     * Initializes the aggregate with the given ID and sets creation and update timestamps.
     *
     * @param id The ID to assign to this aggregate
     */
    protected BaseAggregateRoot(UUID id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.version = 0;
    }

    /**
     * Gets the unique identifier of this aggregate.
     *
     * @return The aggregate's ID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the creation timestamp of this aggregate.
     *
     * @return The creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the last update timestamp of this aggregate.
     *
     * @return The last update timestamp
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Gets the version of this aggregate.
     * The version is incremented each time the aggregate is persisted.
     *
     * @return The aggregate's version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Gets an unmodifiable view of the domain events registered by this aggregate.
     *
     * @return The list of domain events
     */
    public List<DomainEvent> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    /**
     * Registers a domain event that occurred in this aggregate.
     * The event will be published when the aggregate is persisted.
     *
     * @param event The domain event to register
     */
    protected void registerEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    /**
     * Clears all registered domain events.
     * This should be called after events have been published.
     */
    public void clearEvents() {
        domainEvents.clear();
    }

    /**
     * Marks this aggregate as updated.
     * Updates the last modified timestamp and increments the version.
     */
    protected void markUpdated() {
        this.updatedAt = LocalDateTime.now();
        this.version++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseAggregateRoot)) return false;

        BaseAggregateRoot that = (BaseAggregateRoot) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
} 