# Pizzeria API

## Description

This project is a RESTful API for managing a pizzeria, built with Java, Spring Boot, and PostgreSQL. The API allows administrators to manage pizzas, customers to place orders, and generates invoices for completed orders. The architecture follows the clean architecture pattern with well-defined DTOs, entities, repositories, services, and controllers.



| Dependency                  | Scope                | Purpose                                                |
|-----------------------------|----------------------|--------------------------------------------------------|
| spring-boot-starter-data-jpa | implementation       | JPA for database access                                |
| spring-boot-starter-security | implementation       | Security features                                      |
| spring-boot-starter-web      | implementation       | Building web applications with Spring MVC              |
| java-dotenv                  | implementation       | Manage environment variables from a `.env` file        |
| lombok (compileOnly)         | compileOnly &        | Reduce boilerplate code (getters, setters, etc.)       |                                                        |
| spring-boot-devtools         | developmentOnly      | Automatic application restart on code changes          |
| postgresql                   | runtimeOnly          | JDBC driver for connecting to PostgreSQL databases     |
| spring-boot-starter-test     | testImplementation   | Unit testing libraries                                 |
| spring-security-test         | testImplementation   | Testing Spring Security features                       |
| junit-platform-launcher      | testRuntimeOnly      | Runtime dependency for executing JUnit tests           |

## Dependencies

- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- Lombok
- Spring Boot DevTools
- Java-dotenv

## Features

- **Pizza Management**: Only administrators can add, update, and delete pizzas.
- **Pizza Orders**: Customers can order one or more pizzas with up to 3 free ingredients; additional ingredients are charged $5 USD each.
- **Invoice Generation**: A detailed invoice is generated for each order placed.

## Requirements

- JDK 21 (Eclipse Temurin 21)
- Gradle
- Docker (for PostgreSQL)

## Setup

1. **Configure PostgreSQL using Docker:**

```yaml

version: '3.8'
services:
  db:
    image: postgres:15
    container_name: pizzeria-db
    environment:
      POSTGRES_USER: ${SPRING_DATASOURCE_USERNAME}
      POSTGRES_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}
      POSTGRES_DB: ${SPRING_DATASOURCE_DB_NAME}
    ports:
      - "5432:5432"
    networks:
      - pizzeria-network

networks:
  pizzeria-network:
    driver: bridge

```

2.**Database Configuration:**

Configure the database connection in src/main/resources/application.properties

```properties

spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect


```

3. **Run Docker Container:**

```bash
docker-compose up -d
```

# API usage

## Endpoints

### Pizza Management

- **GET /api/pizzas:** Retrieves a list of all pizzas.
- **GET /api/pizzas/{id}:** Gets the details of a specific pizza by its ID.
- **POST /api/pizzas:** Creates a new pizza (admin only).
- **PUT /api/pizzas/{id}:** Updates an existing pizza (admin only).
- **DELETE /api/pizzas/{id}:** Deletes a pizza (admin only).

### Orders

- **POST /api/orders:** Places a new order. Allows up to 3 free additional toppings per pizza; each extra topping costs $5 USD.
- **GET /api/orders/{id}:** Gets the details of a specific order by its ID.

### Billing

- **GET /api/invoices/{orderId}:** Generates an invoice for a specific order.
  
 

