# ADR-0002: Hexagonal Architecture for Microservices

## Status

Accepted

## Context

We are developing a distributed system for insurance claims processing with multiple services that need to interact with various external systems, databases, and user interfaces. As the system grows, we need to ensure:

1. **Domain Logic Isolation**: Business rules and domain logic should be isolated from infrastructure concerns.
2. **Adaptability**: The system should be able to adapt to changing external systems or technologies.
3. **Testability**: Core business logic should be easily testable without external dependencies.
4. **Flexibility**: The system should allow for different delivery mechanisms (REST, GraphQL, CLI, etc.) and persistence mechanisms.
5. **Maintainability**: New team members should be able to understand the architecture and make changes with confidence.

## Decision

We will adopt **Hexagonal Architecture** (also known as Ports and Adapters) for all microservices in the Insurance Claims System.

Key aspects of our implementation:

1. **Core Domain Model**:
   - At the center of each service will be the domain model, containing entities, value objects, and business logic.
   - The domain model will be independent of external concerns and infrastructure.

2. **Ports**:
   - We will define interfaces (ports) that specify how the domain model interacts with the outside world.
   - **Inbound Ports**: Application services and interfaces that handle requests from the outside world.
   - **Outbound Ports**: Repository interfaces, event publishers, and external service clients.

3. **Adapters**:
   - Adapters implement the ports to connect the domain model to external systems.
   - **Primary Adapters**: REST controllers, event listeners, and other entry points.
   - **Secondary Adapters**: Database repositories, message brokers, and external API clients.

4. **Dependency Flow**:
   - Dependencies will always point inward toward the domain model.
   - The domain model will not depend on any external frameworks or infrastructure.
   - Adapters will depend on ports, but ports will not depend on adapters.

5. **Package Structure**:
   - `com.insurance.service.domain`: Domain model and business logic
   - `com.insurance.service.application`: Application services and use cases
   - `com.insurance.service.adapter`: Primary and secondary adapters
   - `com.insurance.service.infrastructure`: Configuration and infrastructure concerns

## Consequences

### Positive

- **Domain-Centric Design**: The architecture puts the focus on the domain logic, aligning with our DDD approach.
- **Technology Independence**: The domain model is not tied to specific frameworks or technologies.
- **Testability**: Core business logic can be tested without infrastructure dependencies.
- **Flexibility**: We can easily change persistence mechanisms, messaging systems, or delivery mechanisms.
- **Maintainability**: Clear separation of concerns makes the code more maintainable.

### Negative

- **Indirection**: The architecture introduces extra layers and indirection, which can increase complexity.
- **Boilerplate**: More interfaces and adapters mean more code to write and maintain.
- **Learning Curve**: Developers may need time to understand the architecture and its benefits.

### Mitigations

- **Templates and Examples**: Provide clear templates and examples for each architectural layer.
- **Documentation**: Document the architecture patterns and their application in our context.
- **Code Reviews**: Ensure code reviews check for adherence to architectural principles.
- **Starter Projects**: Create starter projects that already have the architecture in place.

## Alternatives Considered

1. **Traditional Layered Architecture**: Simpler but often leads to tight coupling between layers and infrastructure concerns bleeding into domain logic.

2. **Clean Architecture**: Similar to Hexagonal Architecture but with more emphasis on use cases as the central organizing principle. We found Hexagonal Architecture to be more intuitive for our domain-focused approach.

3. **CQRS Architecture**: While we incorporate aspects of CQRS, using it as the primary architectural pattern would focus more on the separation of read and write concerns rather than domain isolation.

## References

- [Hexagonal Architecture by Alistair Cockburn](https://alistair.cockburn.us/hexagonal-architecture/)
- [Ports and Adapters Pattern](https://www.thinktocode.com/2018/07/19/ports-and-adapters-architecture/)
- [Hexagonal Architecture with Spring Boot](https://reflectoring.io/spring-hexagonal/)
- [Domain-Driven Design and Hexagonal Architecture](https://vaadin.com/blog/ddd-part-1-strategic-design)
