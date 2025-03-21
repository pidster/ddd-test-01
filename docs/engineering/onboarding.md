# Developer Onboarding Guide

Welcome to the Insurance Claim Processing System project! This guide will help you set up your development environment and understand our approach to Domain-Driven Design.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Development Environment Setup](#development-environment-setup)
3. [Project Structure](#project-structure)
4. [Domain-Driven Design Overview](#domain-driven-design-overview)
5. [Contributing Guidelines](#contributing-guidelines)
6. [Communication Channels](#communication-channels)

## Prerequisites

Before you begin, ensure you have the following installed:

- Java 17 or later
- Maven 3.8+
- Docker and Docker Compose
- Node.js 18+ and npm
- Git
- Your favorite IDE (we recommend IntelliJ IDEA or VS Code)
- Kafka Tools (optional but recommended)

## Development Environment Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/organization/insurance-claim-system.git
   cd insurance-claim-system
   ```

2. **Set Up Local Development Environment**
   ```bash
   # Start required infrastructure (Kafka, PostgreSQL, MongoDB)
   docker-compose -f infrastructure/docker/docker-compose.yml up -d
   
   # Build all services
   ./mvnw clean install
   ```

3. **Run the Application**
   ```bash
   # Start each service individually
   cd services/claims-service
   ./mvnw spring-boot:run
   
   # In another terminal
   cd services/payment-service
   ./mvnw spring-boot:run
   
   # And so on for other services
   ```

4. **Set Up Frontend Development**
   ```bash
   cd frontend
   npm install
   npm start
   ```

5. **Access the Applications**
   - Frontend: http://localhost:3000
   - Claims Service API: http://localhost:8081/swagger-ui.html
   - Payment Service API: http://localhost:8082/swagger-ui.html
   - Notification Service API: http://localhost:8083/swagger-ui.html
   - Policy Service API: http://localhost:8084/swagger-ui.html

## Project Structure

Our project follows a structure based on Domain-Driven Design principles:

```
insurance-claim-system/
├── docs/                      # Documentation
├── services/                  # Backend microservices
│   ├── claims-service/        # Claims bounded context
│   ├── payment-service/       # Payment bounded context
│   ├── notification-service/  # Notification bounded context
│   └── policy-service/        # Policy bounded context
├── frontend/                  # React application
└── infrastructure/            # Infrastructure configurations
```

Each service is structured following the hexagonal architecture pattern:

```
service/
├── src/main/java/com/insurance/service/
│   ├── domain/                # Domain model and logic
│   │   ├── model/             # Aggregates, entities, and value objects
│   │   ├── service/           # Domain services
│   │   └── event/             # Domain events
│   ├── application/           # Application services
│   ├── infrastructure/        # Infrastructure components
│   │   ├── persistence/       # Repository implementations
│   │   ├── messaging/         # Event publishing/subscription
│   │   └── client/            # Client code for other services
│   └── api/                   # API controllers
└── src/test/                  # Tests
```

## Domain-Driven Design Overview

Our system is modeled using Domain-Driven Design (DDD) concepts:

### Bounded Contexts

1. **Claims Processing Context**
   - Core domain responsible for claim submission, verification, and assessment
   - Key aggregates: Claim, ClaimSubmission, Assessment

2. **Payment Context**
   - Handles scheduling and execution of payments
   - Key aggregate: Payment

3. **Notification Context**
   - Manages communication with customers
   - Key aggregate: Notification

4. **Policy Context**
   - Provides policy data and coverage verification
   - Key aggregate: Policy

### Ubiquitous Language

We maintain a consistent language across the codebase. Some key terms:

- **Claim**: A formal request by a policyholder for coverage or compensation
- **Assessment**: Evaluation of a claim's validity and coverage
- **Payment**: Transfer of funds to compensate for a covered claim
- **Notification**: Communication to the customer regarding claim status

Refer to [Domain Model](../ddd/ddd-domain-model.md) for the complete glossary.

## Contributing Guidelines

1. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Follow Domain-Driven Design Principles**
   - Keep business logic in the domain layer
   - Use the ubiquitous language in code and comments
   - Follow aggregate boundaries

3. **Write Tests**
   - Domain logic: unit tests
   - APIs: integration tests
   - Event flow: end-to-end tests

4. **Submit a Pull Request**
   - Use the PR template
   - Ensure all tests pass
   - Get at least one review from a domain expert

## Communication Channels

- **Domain Questions**: Contact the respective domain expert
  - Claims: [name@example.com](mailto:name@example.com)
  - Payments: [name@example.com](mailto:name@example.com)
  - Notifications: [name@example.com](mailto:name@example.com)
  - Policy: [name@example.com](mailto:name@example.com)

- **Technical Questions**: Post in our Slack channel #insurance-claims-tech

- **Regular Meetings**
  - Domain walkthrough: Every Monday at 10 AM
  - Technical sync: Every Wednesday at 2 PM
  - Retrospective: Last Friday of the month at 1 PM 