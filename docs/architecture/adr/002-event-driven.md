# ADR-002: Adoption of Event-Driven Architecture

## Status
Accepted

## Context
Our insurance claim processing system based on Domain-Driven Design has multiple bounded contexts (Claims, Payments, Notifications, Policy) implemented as microservices. We need to define how these services will communicate with each other in a way that:

- Preserves service autonomy
- Minimizes tight coupling
- Aligns with domain events identified in our DDD modeling
- Supports eventual consistency where appropriate
- Provides reliable communication between services

The claim processing workflow involves sequential steps where events need to flow from one context to another, such as a claim being accepted triggering payment scheduling, or a payment being performed triggering customer notification.

## Decision
We will adopt an event-driven architecture using:

1. **Apache Kafka** as the central event streaming platform
2. **Domain events** as the primary means of inter-service communication
3. **Event sourcing** for key aggregates where event history is important
4. **Command Query Responsibility Segregation (CQRS)** pattern where appropriate

Each service will:
- Publish domain events to Kafka when significant state changes occur
- Subscribe to relevant domain events from other services
- Maintain necessary read models derived from events

## Rationale
Event-driven architecture aligns naturally with Domain-Driven Design, which already emphasizes domain events as significant occurrences within the domain.

### Advantages
- **Loose Coupling**: Services only need to know about events, not about other services
- **Temporal Decoupling**: Services don't need to be available simultaneously
- **Natural DDD Alignment**: Domain events are a core DDD concept
- **Auditability**: Event streams provide a complete history of domain changes
- **Resilience**: Events can be replayed if processing fails
- **Scalability**: Event consumers can scale independently

### Disadvantages
- **Eventual Consistency**: Requires managing eventual consistency rather than immediate consistency
- **Complexity**: Event-driven systems can be harder to understand and debug
- **Operational Overhead**: Requires managing a message broker infrastructure

### Alternatives Considered
- **Synchronous REST API Calls**
  - Pros: Simpler to implement initially, immediate consistency
  - Cons: Tight coupling, requires services to be available simultaneously, can create complex dependency chains
- **Shared Database**
  - Pros: Simplifies data consistency, familiar pattern for developers
  - Cons: Violates microservice autonomy, creates schema coupling

## Consequences

### Positive
- Services can evolve independently as they only depend on event contracts
- System becomes more resilient as temporary service unavailability doesn't block others
- Domain events provide a clear audit trail of system changes
- Aligns perfectly with the domain events identified in our event storming sessions

### Negative
- Requires handling eventual consistency in the business logic
- Increases operational complexity with Kafka infrastructure
- Requires developers to think in an event-driven way, which has a learning curve
- Debugging complex event chains can be challenging

### Neutral
- Need to carefully design event schemas and ensure backward compatibility
- Will require building event monitoring capabilities
- Need to establish event versioning strategies

## Compliance
- All significant state changes must publish domain events
- Events must follow the established schema standards
- Services must be able to handle duplicate events (idempotence)
- Events must be versioned appropriately

## Related Decisions
- ADR-001: Microservices Architecture
- (Future) ADR: Event Schema Design
- (Future) ADR: CQRS Implementation

## Notes
Reference materials:
- "Implementing Domain-Driven Design" by Vaughn Vernon
- "Designing Event-Driven Systems" by Ben Stopford 