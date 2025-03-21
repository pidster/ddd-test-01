# Spring Boot Service Template for Insurance Claims System

This template provides a starting point for creating microservices within the Insurance Claims System, following Domain-Driven Design principles and best practices.

## Features

- **Hexagonal Architecture**: Clear separation between domain, application, and infrastructure layers
- **Domain-Driven Design**: Entity, Value Object, Aggregate, Repository, and Domain Service patterns
- **Event-Driven Architecture**: Domain events and Kafka integration
- **Spring Boot**: Modern Spring Boot application with Spring Data JPA, Spring Security, and more
- **API Documentation**: OpenAPI/Swagger integration
- **Monitoring**: Actuator endpoints and Prometheus integration
- **Testing**: Comprehensive testing with JUnit, Mockito, and TestContainers
- **Database Migration**: Flyway for database schema management
- **Security**: OAuth2/JWT authentication and authorization

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── insurance/
│   │           └── service/
│   │               ├── adapter/           # Adapters for external systems
│   │               │   └── rest/          # REST API controllers
│   │               ├── application/       # Application services
│   │               ├── config/            # Configuration classes
│   │               ├── domain/            # Domain model
│   │               │   ├── event/         # Domain events
│   │               │   ├── exception/     # Domain exceptions
│   │               │   ├── model/         # Entities, value objects, aggregates
│   │               │   └── service/       # Domain services
│   │               ├── infrastructure/    # Infrastructure components
│   │               │   ├── client/        # External service clients
│   │               │   ├── messaging/     # Messaging infrastructure
│   │               │   └── persistence/   # Repository implementations
│   │               └── ServiceApplication.java
│   └── resources/
│       ├── application.yml                # Application configuration
│       └── db/
│           └── migration/                 # Database migrations
└── test/
    ├── java/                              # Test classes
    └── resources/                         # Test resources
```

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.8.x or later
- Docker and Docker Compose (for local development)

### Building the Project

To build the project, run:

```bash
mvn clean install
```

To build without running tests:

```bash
mvn clean install -DskipTests
```

### Running the Application

To run the application locally:

```bash
mvn spring-boot:run
```

Or with a specific profile:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Building a Docker Image

A Dockerfile is included to build a container image:

```bash
docker build -t insurance-service:latest .
```

### Running with Docker Compose

A Docker Compose file is provided to run the service with its dependencies:

```bash
docker-compose up -d
```

## Development Guidelines

### Domain Model

- Keep the domain model clean and free from infrastructure concerns
- Use value objects for concepts that don't have identity
- Aggregate roots manage consistency boundaries
- Domain events represent significant occurrences in the domain

### Application Services

- Orchestrate domain objects to fulfill use cases
- Handle transaction boundaries
- Manage security concerns
- Publish domain events

### REST API

- Use RESTful principles
- Version APIs appropriately
- Document all endpoints with OpenAPI

### Database Access

- Use repositories to access data
- Use Spring Data JPA for standard CRUD operations
- Define custom queries for complex operations
- Use specification pattern for dynamic queries

### Messaging

- Use Kafka for publishing and consuming events
- Ensure proper error handling and retries
- Use outbox pattern for reliable event publishing

## Testing

### Unit Testing

- Test domain logic in isolation
- Use mocks for external dependencies
- Focus on behavior, not implementation details

### Integration Testing

- Test interactions between components
- Use TestContainers for database and Kafka integration tests
- Test REST API endpoints with MockMvc

## Logging

- Use SLF4J for logging
- Use appropriate log levels
- Include context information in logs

## Monitoring

- Use Spring Boot Actuator for health checks and metrics
- Expose Prometheus metrics
- Set up alerts for critical service metrics

## Additional Resources

- [Architecture Overview](../../ARCHITECTURE.md)
- [Domain Model](../../ddd-domain-model.md)
- [Bounded Contexts](../../ddd-bounded-contexts.md)
- [Technical Design Document](../../docs/templates/technical-design-document-template.md)
- [Code Style Guide](../../docs/code-style-guide.md) 