# Insurance Claims System Documentation

This directory contains comprehensive documentation for the Insurance Claims System. These documents provide information about the system's architecture, domain model, technical design, development guidelines, and more.

## Core Documentation

- [Architecture Overview](../ARCHITECTURE.md) - System architecture and component interactions
- [Domain-Driven Design (DDD) Model](../ddd-domain-model.md) - Domain model definitions and relationships
- [Bounded Contexts](../ddd-bounded-contexts.md) - Bounded context definitions and relationships
- [Insurance Claim Process](../ddd-insurance-claim-process.md) - End-to-end insurance claim process

## Technical Design

- [Technical Design Document Template](./technical-design-template.md) - Template for creating technical design documents
- [Architectural Decision Records (ADRs)](./adr/) - Records of architectural decisions:
  - [ADR-0001: Use Event Sourcing for Claim Processing](./adr/0001-use-event-sourcing-for-claim-processing.md)
  - [ADR-0002: Hexagonal Architecture for Backend Services](./adr/0002-hexagonal-architecture-for-backend-services.md)
  - [ADR-0003: React Frontend Architecture](./adr/0003-react-frontend-architecture.md)

## Developer Guidelines

- [Onboarding Guide](./onboarding-guide.md) - Guide for new team members
- [Engineering Workflow](./engineering-workflow.md) - Development workflow and processes
- [Coding Standards](./coding-standards.md) - Language-specific coding standards
- [Testing Strategy](./testing-strategy.md) - Testing approach and guidelines

## Templates

- [Technical Design Document Template](./technical-design-template.md) - Template for technical design documents
- [Pull Request Template](./pull-request-template.md) - Template for pull requests
- [ADR Template](./adr/template.md) - Template for architectural decision records

## Service Templates

- [Spring Boot Service Template](../service-templates/spring-boot/) - Template for creating new Spring Boot microservices
- [React Component Template](../service-templates/react/) - Template for creating new React frontend applications

## How to Contribute to Documentation

1. Follow the same Git workflow as code changes
2. Use Markdown for all documentation
3. Place architecture diagrams in the `diagrams/` folder
4. Keep documentation up-to-date with code changes
5. Create ADRs for significant architectural decisions

## Documentation Standards

- Use clear, concise language
- Include diagrams where appropriate
- Link to relevant documents and external resources
- Structure documents with clear headings
- Include examples where helpful
- Keep documentation up-to-date with code changes

For questions about documentation, please contact the Platform team at platform@insurance-claims.com. 