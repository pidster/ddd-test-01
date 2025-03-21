# Project Structure

This document outlines the organization of our DDD insurance claim processing project repository.

```
insurance-claim-system/
├── docs/                                # Documentation
│   ├── architecture/                    # Architecture documents
│   │   ├── ARCHITECTURE.md              # Main architecture document
│   │   ├── adr/                         # Architectural Decision Records
│   │   │   ├── 001-microservices.md     # Decision to use microservices
│   │   │   ├── 002-event-driven.md      # Decision to use event-driven architecture
│   │   │   └── template.md              # ADR template
│   │   └── diagrams/                    # Architecture diagrams
│   ├── ddd/                             # Domain-Driven Design documents
│   │   ├── ddd-domain-model.md          # Domain model
│   │   ├── ddd-bounded-contexts.md      # Bounded contexts
│   │   └── ddd-insurance-claim-process.md # Process flow
│   ├── engineering/                     # Engineering guides
│   │   ├── onboarding.md                # Developer onboarding guide
│   │   ├── workflow.md                  # Git workflow
│   │   ├── code-review.md               # Code review guidelines
│   │   └── testing-strategy.md          # Testing strategy
│   └── templates/                       # Document templates
│       ├── tdd-template.md              # Technical Design Document template
│       └── pr-template.md               # Pull Request template
├── infrastructure/                      # Infrastructure as Code
│   ├── kubernetes/                      # Kubernetes manifests
│   │   ├── claims-service/              # Claims service K8s configs
│   │   ├── payment-service/             # Payment service K8s configs
│   │   ├── notification-service/        # Notification service K8s configs
│   │   └── policy-service/              # Policy service K8s configs
│   ├── docker/                          # Docker configurations
│   │   ├── Dockerfile.service           # Service Dockerfile
│   │   └── docker-compose.yml           # Local development setup
│   └── monitoring/                      # Monitoring configurations
│       ├── prometheus/                  # Prometheus configs
│       ├── grafana/                     # Grafana dashboards
│       └── elastic/                     # ELK stack configs
├── service-templates/                   # Service templates
│   ├── spring-boot/                     # Spring Boot service template
│   │   ├── src/                         # Source code with DDD structure
│   │   └── pom.xml                      # Maven dependencies
│   └── react/                           # React component templates
│       └── src/                         # React component structure
├── api-contracts/                       # API contracts
│   ├── openapi/                         # OpenAPI specifications
│   │   ├── claims-api.yaml              # Claims service API
│   │   ├── payment-api.yaml             # Payment service API
│   │   └── notification-api.yaml        # Notification service API
│   └── events/                          # Event schemas
│       ├── claim-events.json            # Claim event schemas
│       ├── payment-events.json          # Payment event schemas
│       └── notification-events.json     # Notification event schemas
├── ci-cd/                               # CI/CD configurations
│   ├── .github/                         # GitHub Actions
│   │   └── workflows/                   # GitHub workflow definitions
│   └── jenkins/                         # Jenkins pipelines
└── README.md                            # Project overview
```

## Key Components

- **Documentation**: All project documentation including architecture, ADRs, and engineering guides
- **Infrastructure**: Infrastructure as Code configurations for deployment and operations
- **Service Templates**: Starter templates for creating new microservices and UI components
- **API Contracts**: API and event schema definitions for service communication
- **CI/CD**: Continuous Integration and Delivery pipeline configurations

## Getting Started

1. Start by reviewing the documentation in the `docs/` directory
2. Set up your development environment using the onboarding guide
3. Use the service templates when creating new services or components
4. Follow the engineering workflow for branching, PRs, and code reviews 