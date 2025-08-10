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

## License
This project is licensed under the MIT License.
