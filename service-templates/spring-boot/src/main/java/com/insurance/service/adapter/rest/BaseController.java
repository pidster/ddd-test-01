package com.insurance.service.adapter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 * Base class for REST controllers.
 * Provides common functionality for all controllers.
 */
public abstract class BaseController {
    
    protected final Logger log = LoggerFactory.getLogger(getClass());
    
    /**
     * Wraps a response entity for successful operations.
     *
     * @param body The response body
     * @param <T> The type of the response body
     * @return A ResponseEntity with HTTP status 200 OK
     */
    protected <T> ResponseEntity<ApiResponse<T>> ok(T body) {
        return ResponseEntity.ok(new ApiResponse<>(true, body, null));
    }
    
    /**
     * Wraps a response entity for created resources.
     *
     * @param body The created resource
     * @param <T> The type of the created resource
     * @return A ResponseEntity with HTTP status 201 Created
     */
    protected <T> ResponseEntity<ApiResponse<T>> created(T body) {
        return ResponseEntity.status(201).body(new ApiResponse<>(true, body, null));
    }
    
    /**
     * Standard API response wrapper for consistent response format.
     *
     * @param <T> The type of the data
     */
    public static class ApiResponse<T> {
        private final boolean success;
        private final T data;
        private final String error;
        
        public ApiResponse(boolean success, T data, String error) {
            this.success = success;
            this.data = data;
            this.error = error;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public T getData() {
            return data;
        }
        
        public String getError() {
            return error;
        }
    }
} 