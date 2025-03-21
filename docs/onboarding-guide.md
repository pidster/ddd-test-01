# Insurance Claims System - Engineer Onboarding Guide

Welcome to the Insurance Claims System project! This guide will help you get up to speed with our codebase, development practices, and domain knowledge.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Domain Knowledge](#domain-knowledge)
3. [System Architecture](#system-architecture)
4. [Development Environment Setup](#development-environment-setup)
5. [Development Workflow](#development-workflow)
6. [Coding Standards](#coding-standards)
7. [Testing Approach](#testing-approach)
8. [Deployment Process](#deployment-process)
9. [Key Resources](#key-resources)
10. [Team Structure](#team-structure)
11. [Getting Help](#getting-help)

## Project Overview

The Insurance Claims System is a modern, microservices-based application designed to handle the entire lifecycle of insurance claims. It enables policyholders to submit claims, allows adjusters to process them, and facilitates payment disbursement.

### Key Features
- Claim submission and tracking
- Document upload and management
- Claim assessment and adjustment
- Payment processing
- Notifications
- Reporting and analytics
- Policy management integration

### Technology Stack
- **Backend**: Spring Boot, Java 17, Kafka, PostgreSQL, MongoDB
- **Frontend**: React, TypeScript, Material-UI
- **Infrastructure**: Kubernetes, Docker, Terraform
- **CI/CD**: GitHub Actions, SonarQube
- **Monitoring**: Prometheus, Grafana, ELK Stack

## Domain Knowledge

### Insurance Terms
- **Policy**: A contract between the insurer and policyholder that defines coverage
- **Claim**: A formal request for compensation for a covered loss
- **Premium**: The amount paid by the policyholder for insurance coverage
- **Deductible**: The amount the policyholder pays before insurance coverage begins
- **Adjuster**: Person who investigates claims and determines payment amounts
- **Underwriting**: Process of evaluating risk to determine policy issuance and pricing

### Domain Model
Our system follows Domain-Driven Design principles. Key bounded contexts include:
- **Claims Management**: Handles claim lifecycle from submission to settlement
- **Policy Management**: Manages policy details and coverage information
- **Customer Management**: Handles customer information and communication
- **Payment Processing**: Manages payment disbursement and financial transactions
- **Document Management**: Handles storage and retrieval of claim-related documents

For a detailed overview of our domain model, see the [DDD Domain Model](./ddd-domain-model.md) document.

## System Architecture

Our architecture follows microservices principles with bounded contexts as service boundaries. See [ARCHITECTURE.md](../ARCHITECTURE.md) for detailed information.

### Key Components
- **Claims Service**: Core service for claim processing
- **Policy Service**: Manages policy information
- **Customer Service**: Handles customer data
- **Payment Service**: Processes claim payments
- **Notification Service**: Manages communications
- **Document Service**: Handles document storage and retrieval
- **API Gateway**: Entry point for frontend and external integrations
- **Auth Service**: Handles authentication and authorization

### Communication Patterns
- **Synchronous**: REST APIs for direct service-to-service communication
- **Asynchronous**: Kafka for event-driven communication between services

## Development Environment Setup

### Prerequisites
- Java 17 (OpenJDK preferred)
- Docker and Docker Compose
- Node.js 18+ and npm/yarn
- Kubernetes CLI (kubectl)
- Git
- IDE (IntelliJ IDEA recommended for backend, VS Code for frontend)

### Setup Steps

1. **Clone the repositories**
   ```bash
   git clone https://github.com/insurance/claims-service.git
   git clone https://github.com/insurance/policy-service.git
   git clone https://github.com/insurance/payment-service.git
   git clone https://github.com/insurance/claims-ui.git
   # ... other repositories as needed
   ```

2. **Set up local infrastructure**
   ```bash
   cd infrastructure/local
   docker-compose up -d
   ```
   This starts PostgreSQL, MongoDB, Kafka, and other dependencies.

3. **Set up backend services**
   Each service follows a similar setup pattern:
   ```bash
   cd claims-service
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Set up frontend**
   ```bash
   cd claims-ui
   npm install
   npm start
   ```

5. **Access the application**
   - Backend services: Various ports starting at http://localhost:8080
   - Frontend: http://localhost:3000
   - API Documentation: http://localhost:8080/swagger-ui.html

### Configuration

Local development configurations are in `application-local.yml` files for each service. Environment variables to consider setting:

- `SPRING_PROFILES_ACTIVE=local`
- `DATABASE_HOST=localhost`
- `KAFKA_BOOTSTRAP_SERVERS=localhost:9092`

## Development Workflow

### Git Workflow

We follow a GitHub Flow-based workflow:

1. Create a feature branch from `main`: `git checkout -b feature/description`
2. Implement changes with regular commits
3. Write or update tests
4. Push branch and create a Pull Request
5. Address review comments
6. Merge after approval and successful CI checks

### Issue Tracking

We use Jira for tracking work. Each commit message should reference the relevant ticket:

```
feat(claims): Add document upload functionality 

CLAIM-123
```

### Pull Request Process

1. Create a PR with a descriptive title and detailed description
2. Link to relevant issues/tickets
3. Complete the PR template checklist
4. Request reviews from appropriate team members
5. Address feedback and ensure CI passes
6. Squash and merge when approved

## Coding Standards

### Backend (Java)

- Follow standard Java naming conventions
- Use Google Java Style Guide
- Organize code according to hexagonal architecture principles
- Write comprehensive Javadoc for public APIs
- Apply SOLID principles
- Implement comprehensive error handling

### Frontend (React/TypeScript)

- Follow TypeScript best practices with strong typing
- Use functional components with hooks
- Apply ESLint and Prettier rules
- Organize by feature then by type
- Use atomic design principles for components
- Implement proper error boundaries

### Testing

- Backend: JUnit 5, Mockito, TestContainers for integration tests
- Frontend: Jest, React Testing Library
- API: Postman collections, contract tests with Spring Cloud Contract
- End-to-end: Cypress

## Testing Approach

We follow a comprehensive testing strategy:

1. **Unit Tests**: Test individual components in isolation
2. **Integration Tests**: Test component interactions with external systems
3. **API Tests**: Validate API contracts
4. **UI Tests**: Test UI components and interactions
5. **End-to-End Tests**: Test full user journeys
6. **Performance Tests**: Validate system under load

See the [Testing Strategy](./testing-strategy.md) document for detailed guidelines.

## Deployment Process

Our system is deployed on Kubernetes using a CI/CD pipeline:

1. Code is merged to `main` branch
2. CI builds and tests the code
3. Docker images are built and tagged
4. Images are pushed to our container registry
5. CD pipeline deploys to staging environment
6. After verification, manual promotion to production

### Environments

- **Local**: Your development environment
- **Dev**: Shared development environment
- **Testing**: For QA and integration testing
- **Staging**: Production-like for final verification
- **Production**: Live environment

## Key Resources

### Documentation
- [Architecture Overview](../ARCHITECTURE.md)
- [API Documentation](https://api-docs.insurance-claims.com)
- [Domain Model](./ddd-domain-model.md)
- [Architectural Decision Records](./adr/)

### Tools
- [Jira Board](https://jira.insurance-claims.com)
- [CI/CD Dashboard](https://ci.insurance-claims.com)
- [Monitoring Dashboard](https://grafana.insurance-claims.com)

### Learning Resources
- [Domain-Driven Design by Eric Evans](https://domainlanguage.com/ddd/)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [React Documentation](https://reactjs.org/docs/getting-started.html)
- [Insurance Claims Processing Overview](https://internal-wiki.insurance-claims.com/claims-processing-101)

## Team Structure

Our team is organized into several cross-functional groups:

- **Claims Team**: Claims service, claim processing workflows
- **Policy Team**: Policy service, underwriting integration
- **Customer Experience Team**: UI, customer service, notifications
- **Platform Team**: Infrastructure, shared services, security
- **Data Team**: Reporting, analytics, data pipeline

## Getting Help

- **Technical Issues**: Reach out in the #tech-support Slack channel
- **Domain Questions**: Contact our domain experts in #domain-help
- **Process Questions**: Ask your team lead or check the wiki
- **Emergencies**: Call the on-call engineer (schedule in PagerDuty)

Your onboarding buddy is your first point of contact for any questions. Don't hesitate to reach out!

Welcome to the team! 🎉 