# ADR 0002: Adopt Hexagonal Architecture for Backend Services

## Status

Accepted

## Context

Our insurance claim processing system consists of multiple microservices that need to interact with various external systems, databases, message brokers, and UI components. As we implement these services, we need an architectural pattern that:

1. Allows for clear separation of business logic from technical concerns
2. Facilitates testability of core domain logic in isolation
3. Makes it easier to adapt to changing technical requirements without modifying domain logic
4. Aligns well with Domain-Driven Design principles
5. Provides flexibility to evolve our infrastructure choices over time

The system will evolve over time, with potential changes in:
- Database technologies
- Message broker implementations
- Integration protocols with external systems
- API technologies (REST, gRPC, GraphQL)
- User interface approaches

## Decision

We will adopt the Hexagonal Architecture (also known as Ports and Adapters) pattern for all backend services. This architecture will:

1. Place domain logic at the core, isolated from external concerns
2. Define ports (interfaces) for all interactions with the outside world
3. Implement adapters that connect these ports to specific technologies
4. Use dependency inversion to ensure the domain depends only on abstractions

Our implementation will follow this structure:
- `domain`: Core domain model (entities, value objects, aggregates)
- `application`: Use cases/application services that orchestrate domain operations
- `ports`: Interfaces defining how the application interacts with external systems
  - `in`: Inbound ports (API contracts)
  - `out`: Outbound ports (repository interfaces, event publishing interfaces)
- `adapters`: Implementations of the ports for specific technologies
  - `in`: Controllers, message listeners
  - `out`: Repository implementations, message producers, external service clients
- `infrastructure`: Cross-cutting concerns like security, logging, configuration

## Consequences

### Positive

- Business logic remains isolated and pure, focused on domain concepts
- Testing becomes simpler, with the ability to mock external dependencies
- Changing technical implementations (e.g., switching databases) requires only adapter changes
- New interfaces (e.g., adding a REST API alongside a gRPC one) can be added with minimal impact
- Clear boundaries help maintain separation of concerns
- Forces developers to think in terms of domain concepts rather than technical details
- Naturally aligns with DDD bounded contexts and aggregates

### Negative

- More initial boilerplate code compared to simpler architectures
- Additional complexity for developers new to the pattern
- Potential overhead in small services where the indirection might not be necessary
- Risk of over-engineering if applied too rigidly to simple use cases

### Mitigations

- Create starter templates that encapsulate the boilerplate code
- Provide comprehensive documentation and training for developers
- Allow flexibility in implementation for simpler services
- Regular architecture reviews to ensure the pattern is applied appropriately

## Alternatives Considered

1. **Traditional layered architecture**: Simpler to understand but less effective at isolating domain logic and more prone to leaky abstractions.

2. **Clean Architecture**: Similar to Hexagonal but with more prescribed layers; considered too prescriptive for our needs.

3. **CQRS with separate read/write models**: Valuable pattern but better applied as a complement to hexagonal architecture rather than a replacement.

4. **Service-oriented architecture without clear internal structure**: Too unstructured, leading to inconsistent implementations across teams.

## References

- Alistair Cockburn on Hexagonal Architecture: https://alistair.cockburn.us/hexagonal-architecture/
- Ports and Adapters Pattern: https://jmgarridopaz.github.io/content/hexagonalarchitecture.html
- Domain-Driven Design: https://domainlanguage.com/ddd/
- Spring Hexagonal Architecture example: https://reflectoring.io/spring-hexagonal/ 