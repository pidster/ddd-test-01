package com.insurance.service.domain.exception;

/**
 * Base class for all domain-specific exceptions in the application.
 * Extends RuntimeException to be unchecked, as domain exceptions represent
 * exceptional states in the business logic that often cannot be recovered from.
 */
public class DomainException extends RuntimeException {

    /**
     * Creates a new domain exception with the specified message.
     *
     * @param message The detail message
     */
    public DomainException(String message) {
        super(message);
    }

    /**
     * Creates a new domain exception with the specified message and cause.
     *
     * @param message The detail message
     * @param cause The cause of the exception
     */
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
} 