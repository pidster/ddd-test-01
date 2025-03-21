# Insurance Claim Processing - Domain Driven Design

## DDD Building Blocks

Key concepts from the DDD model:

| Color  | Concept      | Description                          |
|--------|-------------|--------------------------------------|
| Orange | Domain Event | Something that happened in the domain |
| Blue   | Command     | An action that changes state         |
| Yellow | Aggregate   | Cluster of domain objects treated as a unit |
| Pink   | Issue       | Problem or challenge in the domain   |
| Yellow with icon | User Role | Person who interacts with the system |

## Claim Processing Flow

```mermaid
flowchart LR
    %% Aggregates
    A1["Claim\nSubmission\nSelf-Service"]
    A2["Claim"]
    A3["Claim"]
    A4["Claim"]
    A5["Payment"]
    A6["Payment"]
    A7["Notification"]

    %% Commands
    C1["Submit claim\n(online or via\nmail)"]
    C2["Check\ndocumentation\n(completeness)"]
    C3["Check\ninsurance"]
    C4["Accept claim\nand schedule\npayment"]
    C5["Perform\npayment"]
    C6["Reject claim"]
    C7["Notify\ncustomer (by\nmail)"]

    %% Domain Events
    E1["Claim\nsubmitted"]
    E2["Claim\nregistered"]
    E3["Assessment\nperformed"]
    E4["Payment\nscheduled"]
    E5["Payment\nperformed"]
    E6["Claim\nrejected"]
    E7["Customer\nnotified"]

    %% User Roles
    R1["Customer"]
    R2["Administrator\nin charge"]
    R3["Responsible\nperson in\nclaims\ndepartment"]
    R4["Responsible\nperson in\nclaims\ndepartment"]
    R5["Responsible\nperson in\nclaims\ndepartment"]

    %% Issues
    I1["Realization in\nthe Policy\nManagement\nContext?"]
    I2["Or new\nBounded\nContext?"]

    %% Flow
    C1 -->|triggers| E1
    E1 -->|leads to| C2
    C2 -->|triggers| E2
    E2 -->|leads to| C3
    C3 -->|triggers| E3
    
    E3 -->|may lead to| C4
    E3 -->|may lead to| C6
    
    C4 -->|triggers| E4
    E4 -->|leads to| C5
    C5 -->|triggers| E5
    
    C6 -->|triggers| E6
    
    E5 -->|leads to| C7
    E6 -->|leads to| C7
    C7 -->|triggers| E7

    %% Attachments
    R1 -.->|initiates| C1
    R2 -.->|performs| C2
    R3 -.->|performs| C3
    R4 -.->|performs| C4
    R5 -.->|performs| C6
    
    A1 -.->|contains| C1
    A2 -.->|relates to| E1
    A3 -.->|relates to| E2
    A4 -.->|relates to| E3
    A5 -.->|relates to| E4
    A6 -.->|relates to| E5
    A7 -.->|relates to| E7

    %% Issues connection
    E7 -.->|relates to| I1
    I1 -.->|or| I2
```

## Bounded Contexts

The diagram suggests potential bounded contexts:

1. **Claim Processing Context** - Main process flow from submission to decision
2. **Payment Context** - Handling of payment scheduling and execution  
3. **Notification Context** - Customer communication
4. **Policy Management Context** - Referenced as a possible realization context

## Strategic Design Considerations

The diagram raises questions about where certain responsibilities should live:
- Should notification be part of the claim process or its own bounded context?
- Should payment be realized in the policy management context or a new bounded context?

These considerations help identify appropriate service boundaries in the system architecture. 