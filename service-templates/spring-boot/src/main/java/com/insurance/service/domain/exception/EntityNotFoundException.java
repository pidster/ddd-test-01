package com.insurance.service.domain.exception;

/**
 * Exception thrown when an entity cannot be found.
 * Used to indicate that a requested resource does not exist in the system.
 */
public class EntityNotFoundException extends DomainException {

    /**
     * Creates a new entity not found exception.
     *
     * @param entityType The type of entity that was not found
     * @param id The identifier of the entity that was not found
     */
    public EntityNotFoundException(String entityType, Object id) {
        super(String.format("%s with id '%s' not found", entityType, id));
    }

    /**
     * Creates a new entity not found exception with a custom message.
     *
     * @param message The detail message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
} 