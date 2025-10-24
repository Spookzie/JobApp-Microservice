# JobApp Microservice (Java + Spring Cloud)

A microservice-based application for a job portal, built with **Java 21**, **Spring Boot**, and **Spring Cloud**. This project demonstrates a distributed system with a service registry, API gateway, and separate, containerized services for core functionalities.

---

## 🛠 Tech Stack

-   Java 21
-   Spring Boot 3
-   Spring Cloud
-   Spring Cloud Gateway (API Gateway)
-   Spring Cloud Eureka (Service Registry)
-   Spring Data JPA
-   PostgreSQL (One database per service)
-   Docker & Docker Compose
-   Maven

---

## 🔐 Features

-   **Microservice Architecture:** Decoupled services for Companies, Jobs, and Reviews.
-   **Service Discovery:** Uses **Eureka Service Registry** for dynamic service registration and discovery.
-   **Centralized Entry Point:** **Spring Cloud Gateway** routes all client requests to the appropriate downstream service.
-   **Database per Service:** Each microservice has its own dedicated PostgreSQL database (`company_db`, `job_db`, `review_db`) to ensure loose coupling.
-   **RESTful APIs:** Clear API routes for all CRUD (Create, Read, Update, Delete) operations.
-   **Inter-service Communication:** Services communicate with each other (e.g., Job service fetching Company data).
-   **Dockerized Environment:** Full `docker-compose.yml` for building and running all services and databases with a single command.
-   **Distributed Tracing with Zipkin:** This project uses **Zipkin** to trace requests across microservices. It helps **debug, monitor performance, and identify bottlenecks** by showing the path of requests from the API Gateway through Company, Job, and Review services.

---

## 🐳 Docker Support

The entire application (all microservices + 3 databases) can be run easily using Docker. Make sure Docker is installed and running.

From the root directory, run:

```bash
docker-compose up -d --build
```

---

## 🤫 Environment Variables

Before running, you may need to set environment variables for your database credentials.

The default values are set in the `docker-compose.yml` and `application.properties` files (e.g., `DB_USER=postgres`, `DB_PASSWORD=password`).

If you run manually, ensure your `application.properties` in each service points to the correct database URL, user, and password.

---

## 🚀 Running the Services Manually

If you choose not to use Docker, you must start the services in the correct order:

1. **Run PostgreSQL Server** (and create the 3 databases: `company_db`, `job_db`, `review_db`)  
2. **Service Registry** (`service-registry`)  
3. **Company Service** (`company-service`)  
4. **Job Service** (`job-service`)  
5. **Review Service** (`review-service`)  
6. **API Gateway** (`api-gateway`)  

**Notes:**  

- **API Gateway:** [http://localhost:8080/](http://localhost:8080/) — main entry point for all API requests  
- **Eureka Dashboard:** [http://localhost:8761/](http://localhost:8761/) — shows the status of all registered services

### 🧠 Key Concepts Demonstrated

- Service registration and discovery
- API routing via gateway
- Database-per-service pattern
- Inter-service communication
- Docker containerization for microservices
- Spring Cloud integration for distributed systems
- Request tracing using Zipkin

---

### 📸 Example

Here’s a high-level architecture of the system:

```text
             +-----------------+
             |     CLIENT      |
             +--------+--------+
                      |
                      v
             +-----------------+
             |  API GATEWAY    |
             +--------+--------+
                      |
        +-------------+-------------+
        |             |             |
        v             v             v
+---------------+ +---------------+ +---------------+
| COMPANY-SERVICE| |  JOB-SERVICE  | | REVIEW-SERVICE|
+-------+-------+ +-------+-------+ +-------+-------+
        |                 |                 |
        v                 v                 v
+-------+-------+ +-------+-------+ +-------+-------+
| company_db    | | job_db        | | review_db     |
+---------------+ +---------------+ +---------------+

        +--------------------------------+
        |      EUREKA SERVICE REGISTRY   |
        +--------------------------------+
