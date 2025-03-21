package com.insurance.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Main Spring Boot application class for the service.
 * Enables Kafka for event-driven communication and Feign clients for inter-service communication.
 */
@SpringBootApplication
@EnableKafka
@EnableFeignClients
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
} 