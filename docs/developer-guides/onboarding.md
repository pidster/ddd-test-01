# Developer Onboarding Guide

Welcome to the Insurance Claims System development team! This guide will help you get started with the project, understand our development workflow, and provide you with the knowledge needed to contribute effectively.

## Initial Setup

### 1. Prerequisites

Ensure you have the following tools installed:

- JDK 17 or later
- Maven 3.8.x or later
- Node.js 18.x or later and npm
- Docker and Docker Compose
- Git
- IDE of your choice (recommended: IntelliJ IDEA, VS Code)

### 2. Repository Access

1. Request access to the main repository from your team lead
2. Clone the repository:
   ```bash
   git clone https://github.com/your-org/insurance-claims-system.git
   cd insurance-claims-system
   ```

### 3. Local Environment Setup

1. Build the project:
   ```bash
   ./mvnw clean install -DskipTests
   ```

2. Start the infrastructure services:
   ```bash
   cd infrastructure
   docker-compose up -d
   ```

3. Run the application:
   ```bash
   cd service-templates/spring-boot
   ./mvnw spring-boot:run
   ```

4. For the frontend application:
   ```bash
   cd service-templates/react
   npm install
   npm start
   ```

## Domain Knowledge Transfer

### 1. Understanding the Domain

Review the following documentation to understand the insurance claims domain:

- [Domain Model](../../ddd-domain-model.md): Core domain concepts
- [Bounded Contexts](../../ddd-bounded-contexts.md): System boundaries
- [Insurance Claim Process](../../ddd-insurance-claim-process.md): End-to-end process

### 2. System Architecture

Review the [Architecture Overview](../../ARCHITECTURE.md) to understand:

- Microservices architecture
- Event-driven communication
- Integration patterns
- Deployment strategy

### 3. Technical Documentation

Explore the technical design documents to understand specific implementation details:

- [ADRs](../adr/): Architectural decisions
- [Technical Design Documents](../templates/technical-design-document-template.md): Service design

## Development Workflow

### 1. Git Workflow

We follow a Git flow workflow:

1. Create a feature branch from the `develop` branch:
   ```bash
   git checkout develop
   git pull
   git checkout -b feature/your-feature-name
   ```

2. Make changes, commit frequently with meaningful messages:
   ```bash
   git add .
   git commit -m "feat: Add claim validation functionality"
   ```

3. Push your branch and create a pull request against `develop`:
   ```bash
   git push -u origin feature/your-feature-name
   ```

### 2. Pull Request Process

1. Use the [PR template](../pull-request-template.md) when creating a PR
2. Ensure all CI checks pass
3. Request reviews from team members
4. Address all comments and feedback
5. PR will be merged by the reviewer after approval

### 3. Release Process

1. Feature PRs are merged into `develop`
2. For releases, `develop` is merged into `main`
3. Tags are created for each release
4. CI/CD pipeline automatically deploys from `main`

## Coding Standards

### 1. Backend (Java)

- Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- Use meaningful and descriptive names
- Write unit tests for all business logic
- Follow DDD principles and patterns
- Document public APIs with Javadoc

### 2. Frontend (TypeScript/React)

- Follow the [Airbnb JavaScript Style Guide](https://github.com/airbnb/javascript)
- Use functional components with hooks
- Maintain type safety with proper TypeScript types
- Implement component tests for UI components
- Follow the project's component structure

### 3. Testing Strategy

- Unit tests: Test individual components in isolation
- Integration tests: Test interactions between components
- End-to-end tests: Test complete user flows
- Aim for high test coverage, especially for domain logic

## Common Tasks

### 1. Creating a New Microservice

1. Copy the Spring Boot template:
   ```bash
   cp -r service-templates/spring-boot services/your-service-name
   ```

2. Update package names and configurations
3. Define your domain model
4. Implement use cases and APIs
5. Write tests
6. Update the CI/CD configuration

### 2. Creating a New UI Module

1. Copy the React template:
   ```bash
   cp -r service-templates/react frontend/your-module-name
   ```

2. Update package name and configurations
3. Define components, services, and pages
4. Connect to backend APIs
5. Write tests

### 3. Adding a New API Endpoint

1. Define the request/response DTOs
2. Create or update the controller
3. Implement the application service
4. Update the OpenAPI documentation
5. Write tests

## Troubleshooting

### 1. Common Issues

- **Database connection issues**: Ensure PostgreSQL is running and credentials are correct
- **Kafka connection issues**: Check if Kafka and Zookeeper are running
- **Build failures**: Ensure you have the right JDK version and Maven configuration

### 2. Getting Help

- Check the project wiki for FAQs
- Ask questions in the #dev-help Slack channel
- Reach out to senior team members for guidance

## Resources

### 1. Tools and Technologies

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [React Documentation](https://reactjs.org/docs/getting-started.html)
- [DDD Resources](https://github.com/ddd-crew)

### 2. Team Information

- Engineering Manager: [Name]
- Tech Lead: [Name]
- Slack Channels: #team-insurance, #dev-insurance, #support-insurance
- Regular Meetings: 
  - Daily Stand-up: 10:00 AM (EST)
  - Sprint Planning: Every other Monday at 11:00 AM (EST)
  - Retrospective: Every other Friday at 3:00 PM (EST)

Welcome to the team! We're excited to have you onboard. 