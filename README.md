# Hexagonal Architecture - Spring Boot Order Management

A modular Spring Boot project using Hibernate/JPA to demonstrate a clean architecture for order management, with REST API endpoints and an H2 in-memory database.

## Features

- **Hexagonal (Ports & Adapters) architecture**
- **Spring Boot** REST API
- **Hibernate / JPA** for persistence
- **H2 in-memory database**
- CRUD operations for Orders
- Ready-to-use cURL commands for quick testing

## Technologies

- Java 21+
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- Maven

## Running the Project

### Prerequisites

- Java 21 or higher
- Maven 3.9+

### Steps

```bash
# Clone the repository
git clone https://github.com/your-username/hex-spring-modular.git
cd hex-spring-modular

# Build and run the application
mvn clean install
mvn -pl web spring-boot:run
```

The application will start at:  

```
http://localhost:8080
```

## API Endpoints

### 1. Get all orders

```powershell
curl http://localhost:8080/api/orders
```

**Response Example**

```json
[
  {
    "id": 1,
    "customerName": "Alice",
    "totalAmount": 99.99
  },
  {
    "id": 2,
    "customerName": "Bob",
    "totalAmount": 49.50
  }
]
```

### 2. Create a new order

```powershell
curl -X POST http://localhost:8080/api/orders `
     -H "Content-Type: application/json" `
     -d '{"customerName":"Charlie","totalAmount":12.34}'
```

**Response Example**

```json
{
  "id": 3,
  "customerName": "Charlie",
  "totalAmount": 12.34
}
```

### 3. Get a single order by ID

Replace `{id}` with the order ID returned from creation.

```powershell
curl http://localhost:8080/api/orders/{id}
```

**Response Example**

```json
{
  "id": 3,
  "customerName": "Charlie",
  "totalAmount": 12.34
}
```

## H2 Database Console

You can also view the data via the H2 console:

- URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: *(leave blank)*

---

## Project Structure

```
hex-spring-modular/
├── domain/                  # Core business logic (entities, domain services)
├── infrastructure-jpa/      # Persistence layer (repositories, adapters)
├── application/              # Application services (use cases)
└── web/                      # REST controllers, main Spring Boot app
```

### What is Hexagonal Architecture?

Hexagonal Architecture—also called **Ports and Adapters**—structures an application so that the **core domain** is independent from frameworks and I/O (web, DB, messaging).  
External technologies are plugged in via **adapters** defined by **ports** (interfaces).

### Key Concepts

- **Core (Domain):** Business rules, entities, value objects, and use cases—no framework dependencies.  
- **Ports:** Interfaces at the boundaries.  
  - **Inbound ports**: how external actors call into the app (use cases).  
  - **Outbound ports**: what the core needs from the outside (e.g., persistence).  
- **Adapters:** Implementations that connect real technologies to the ports.  
  - **Inbound adapters**: REST controllers, CLI, message consumers.  
  - **Outbound adapters**: JPA repositories, HTTP clients, SMTP senders.

### Mermaid Diagram (Ports & Adapters)

```mermaid
flowchart LR
  subgraph inbound[Inbound Adapters]
    A1[REST Controller]:::in
    A2[CLI / Scheduler]:::in
    A3[Message Consumer]:::in
  end

  subgraph core[Core Domain & Application]
    P_in[Inbound Ports (Use Cases)]:::port
    S[Application Service]:::core
    D[Domain Model]:::core
    P_out[Outbound Ports (Interfaces)]:::port
  end

  subgraph outbound[Outbound Adapters]
    B1[JPA Adapter]:::out
    B2[HTTP Client Adapter]:::out
    B3[Email/SMTP Adapter]:::out
  end

  A1 --> P_in --> S --> D
  A2 --> P_in
  A3 --> P_in
  S --> P_out --> B1
  S --> P_out --> B2
  S --> P_out --> B3

  classDef core fill:#f3f4f6,stroke:#111827,stroke-width:1px;
  classDef port fill:#e0f2fe,stroke:#0369a1,stroke-width:1px,color:#0c4a6e;
  classDef in fill:#ecfdf5,stroke:#065f46,color:#064e3b;
  classDef out fill:#fff7ed,stroke:#9a3412,color:#7c2d12;
```

### Mapping to This Project

- **domain** → Core domain objects and ports (`Order`, `OrderRepository`)  
- **application** → Use cases / services (`OrderService`)  
- **web** → Inbound adapter (REST controller)  
- **infrastructure-jpa** → Outbound adapter (JPA entity/repository + adapter implementation)

### Why Use It

- **Testability:** Unit-test the domain without DB/web servers.  
- **Replaceability:** Swap DB or transport without touching the domain.  
- **Maintainability:** Clear separation of business vs. infrastructure.

### Testing Strategy

- **Domain module:** pure unit tests for business rules.  
- **Application module:** service tests (mock/stub ports).  
- **Infrastructure-JPA module:** `@DataJpaTest` for the adapter slice.  
- **Web module:** `@WebMvcTest` for controller and full `@SpringBootTest` integration tests.

### Common Pitfalls

- Leaking framework annotations into domain.  
- Controllers directly depending on JPA types instead of domain types.  
- Skipping ports and calling adapters from controllers.

---
