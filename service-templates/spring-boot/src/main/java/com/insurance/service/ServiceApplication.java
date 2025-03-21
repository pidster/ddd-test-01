package com.insurance.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main entry point for the Insurance Service application.
 */
@SpringBootApplication
@EnableJpaAuditing
public class ServiceApplication {

    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
} 