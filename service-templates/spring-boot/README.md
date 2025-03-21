# Spring Boot Service Template

This template provides a starting point for creating new microservices within the Insurance Claim Processing System, following Domain-Driven Design principles.

## Features

- **Hexagonal Architecture**: Clear separation between domain, application, and infrastructure layers
- **Domain-Driven Design**: Support for DDD concepts like aggregates, entities, value objects, and domain events
- **Event-Driven**: Kafka integration for publishing and consuming domain events
- **Database**: PostgreSQL with Flyway migrations for schema management
- **API Documentation**: OpenAPI/Swagger integration
- **Security**: OAuth2/JWT-based authentication and authorization
- **Monitoring**: Spring Boot Actuator and Prometheus metrics
- **Testing**: Comprehensive testing setup with JUnit, Mockito, and TestContainers

## Project Structure

```
src/main/java/com/insurance/service/
├── adapter/
│   └── rest/           # REST controllers (primary adapters)
├── application/        # Application services, DTOs, and command/query handlers
├── config/             # Application configuration
├── domain/             # Domain model and business logic
│   ├── model/          # Aggregates, entities, and value objects
│   ├── service/        # Domain services
│   ├── event/          # Domain events
│   └── exception/      # Domain exceptions
└── infrastructure/     # Infrastructure concerns (secondary adapters)
    ├── persistence/    # Repository implementations
    ├── messaging/      # Event publishing/subscription
    └── client/         # Client code for other services
```

## Getting Started

1. **Clone the template**:
   ```
   cp -r service-templates/spring-boot services/my-new-service
   ```

2. **Update package and service names**:
   - Rename the package from `com.insurance.service` to `com.insurance.myservice`
   - Update application name in `application.yml`
   - Update service details in `pom.xml`

3. **Define your domain model**:
   - Create aggregates in `domain/model/`
   - Define domain events in `domain/event/`
   - Implement domain services in `domain/service/`

4. **Implement repositories**:
   - Create repository interfaces in `domain/model/`
   - Implement them in `infrastructure/persistence/`

5. **Create application services**:
   - Implement application services in `application/`
   - Define DTOs for API requests and responses

6. **Add API endpoints**:
   - Create REST controllers in `adapter/rest/`
   - Document APIs with OpenAPI annotations

## Development Guidelines

### Domain Layer

- Keep the domain model clean and free from infrastructure concerns
- Use ubiquitous language from the domain in your code
- Encapsulate business logic within aggregates and domain services
- Make aggregates responsible for maintaining their invariants

### Application Layer

- Application services should orchestrate domain objects and handle infrastructure concerns
- Use DTOs for communication with external layers
- Keep transaction management at this level
- Publish domain events after successful transactions

### Infrastructure Layer

- Implement repositories, event publishers, and external clients
- Keep infrastructure details isolated from the domain
- Use dependency injection to provide implementations to the application layer

## Testing

- **Unit Tests**: Test domain logic in isolation
- **Integration Tests**: Test repositories and infrastructure
- **API Tests**: Test REST controllers and serialization
- **End-to-End Tests**: Test complete flows

Run tests with:
```
./mvnw test
```

Run integration tests with:
```
./mvnw verify -P integration-tests
```

## Running Locally

1. Start infrastructure dependencies:
   ```
   docker-compose -f ../../infrastructure/docker/docker-compose.yml up -d
   ```

2. Run the application:
   ```
   ./mvnw spring-boot:run
   ```

3. Access the API documentation:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## Build and Deployment

Build a Docker image:
```
./mvnw spring-boot:build-image
```

See the main documentation for details on deploying to Kubernetes. 