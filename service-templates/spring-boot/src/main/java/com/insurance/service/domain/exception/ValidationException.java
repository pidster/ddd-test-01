package com.insurance.service.domain.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception thrown when domain validation fails.
 * Used to represent validation errors in the domain model.
 */
public class ValidationException extends DomainException {

    private final Map<String, String> validationErrors;

    /**
     * Creates a new validation exception with a single error message.
     *
     * @param message The detail message
     */
    public ValidationException(String message) {
        super(message);
        this.validationErrors = new HashMap<>();
    }

    /**
     * Creates a new validation exception with a map of field-specific validation errors.
     *
     * @param message The detail message
     * @param validationErrors A map of field names to error messages
     */
    public ValidationException(String message, Map<String, String> validationErrors) {
        super(message);
        this.validationErrors = new HashMap<>(validationErrors);
    }

    /**
     * Creates a new validation exception with a single field error.
     *
     * @param fieldName The name of the field that failed validation
     * @param errorMessage The validation error message
     */
    public ValidationException(String fieldName, String errorMessage) {
        super("Validation failed for field: " + fieldName);
        this.validationErrors = new HashMap<>();
        this.validationErrors.put(fieldName, errorMessage);
    }

    /**
     * Gets the validation errors as a map of field names to error messages.
     *
     * @return The validation errors
     */
    public Map<String, String> getValidationErrors() {
        return new HashMap<>(validationErrors);
    }

    /**
     * Checks if there are any validation errors.
     *
     * @return true if there are validation errors, false otherwise
     */
    public boolean hasErrors() {
        return !validationErrors.isEmpty();
    }
} 