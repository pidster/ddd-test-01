package com.insurance.service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for OpenAPI documentation.
 * Sets up Swagger UI and API documentation.
 */
@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * Configures the OpenAPI documentation for the service.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(servers())
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt", securityScheme()))
                .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"));
    }

    /**
     * Defines the API information including title, description, and version.
     */
    private Info apiInfo() {
        return new Info()
                .title(applicationName.toUpperCase() + " API")
                .description("REST API for the " + applicationName + " microservice")
                .version("1.0.0")
                .contact(new Contact()
                        .name("Insurance Team")
                        .email("team@insurance.com")
                        .url("https://insurance.com"))
                .license(new License()
                        .name("Proprietary")
                        .url("https://insurance.com/terms"));
    }

    /**
     * Defines the server configurations for different environments.
     */
    private List<Server> servers() {
        Server devServer = new Server()
                .url("http://localhost:8080")
                .description("Development server");

        Server stagingServer = new Server()
                .url("https://staging-api.insurance.com")
                .description("Staging server");

        Server prodServer = new Server()
                .url("https://api.insurance.com")
                .description("Production server");

        return Arrays.asList(devServer, stagingServer, prodServer);
    }

    /**
     * Defines the security scheme for JWT authentication.
     */
    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT Authorization header using the Bearer scheme. Example: \"Authorization: Bearer {token}\"");
    }
} 