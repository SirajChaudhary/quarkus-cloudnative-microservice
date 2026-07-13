# Property Finder Service

A modern **Property Finder Microservice** built using **Java 21**, **Quarkus**, **Hibernate ORM with Panache**, and **PostgreSQL**.

The project demonstrates how to build production-ready RESTful microservices using clean architecture, enterprise coding standards and cloud-native development practices.

Rather than focusing only on CRUD operations, this project showcases several essential backend concepts including dynamic searching, filtering, sorting, pagination, validation, caching, health checks, Docker containerization and GraalVM Native Image support.

Although the project currently implements a single microservice, its architecture follows enterprise design principles and provides a solid foundation for evolving into a larger microservices-based real estate platform.

---

# Project Objectives

This project was developed to:

- Learn Quarkus from beginner to advanced level.
- Understand cloud-native application development.
- Build production-quality REST APIs.
- Learn Hibernate ORM with Panache.
- Understand Repository, Service and DTO patterns.
- Implement dynamic searching, filtering, sorting and pagination.
- Integrate PostgreSQL using Hibernate ORM.
- Implement centralized exception handling.
- Apply Bean Validation using Jakarta Validation.
- Improve application performance using caching.
- Package applications using Docker.
- Build GraalVM Native Images.
- Follow clean architecture and enterprise development practices.

---

# Project Features

The application provides several features commonly found in enterprise backend applications.

### Property Management

Complete CRUD operations for property listings.

Features:

- Create new properties.
- Retrieve property details.
- Update existing properties.
- Delete property listings.

### Dynamic Search

A single API supports searching using multiple optional filters.

Supported filters:

- Keyword
- City
- State
- Listing Type
- Property Type
- Minimum Price
- Maximum Price

Benefits:

- No need to create multiple search APIs.
- Supports combining multiple filters.
- Easily extensible when new filters are added.

### Dynamic Sorting

Allows clients to choose both the sorting field and sorting direction.

Supported options:

- Ascending order
- Descending order
- Multiple sortable fields

Example:

```text
GET /api/v1/properties?sortBy=price&sortDirection=desc
```

### Pagination

Returns only the required subset of records instead of loading the entire dataset.

Benefits:

- Faster API responses.
- Reduced memory usage.
- Better database performance.
- Improved scalability.

Example:

```text
GET /api/v1/properties?page=0&size=10
```

### Bean Validation

Incoming requests are automatically validated before reaching the business layer.

Current validations include:

- Mandatory fields
- Positive numeric values
- Required enums
- Invalid request detection

Benefits:

- Cleaner business logic.
- Consistent validation errors.
- Better API reliability.

### Global Exception Handling

All application exceptions are handled from a single location.

Handled exceptions include:

- Resource Not Found
- Duplicate Resource
- Validation Errors
- Illegal Arguments
- Unexpected Server Errors

Benefits:

- Consistent error responses.
- Better debugging.
- Easier client integration.

### Generic API Responses

Every successful API returns a standardized response format.

Benefits:

- Consistent API contract.
- Easier frontend integration.
- Simplified client development.

### Generic Error Responses

All failures return a common error structure.

Benefits:

- Predictable responses.
- Easier troubleshooting.
- Better developer experience.

### Application Caching

Frequently accessed data is stored in memory using Quarkus Cache.

Current implementation:

- Property By Id Cache
- Property Search Cache

Cache is automatically refreshed whenever:

- A property is created.
- A property is updated.
- A property is deleted.

Benefits:

- Faster response times.
- Reduced database queries.
- Improved application performance.

### Database Health Checks

The application continuously verifies PostgreSQL connectivity using SmallRye Health.

Available endpoints:

```text
GET /health
GET /health/live
GET /health/ready
```

Benefits:

- Detect database availability.
- Support container orchestration.
- Improve application reliability.

### OpenAPI & Swagger UI

API documentation is generated automatically.

Features:

- Interactive API documentation.
- Request and response models.
- Direct API testing from the browser.
- OpenAPI specification generation.

### Docker Support

The project can be packaged and executed as a Docker container.

Quarkus automatically generates multiple Dockerfiles for different deployment strategies.

Supported Dockerfiles:

- Dockerfile.jvm
- Dockerfile.legacy-jar
- Dockerfile.native
- Dockerfile.native-micro

Benefits:

- Simplified containerization.
- Consistent deployment.
- Cloud-ready packaging.

### GraalVM Native Image

The application can be compiled into a native executable.

Benefits:

- Extremely fast startup.
- Lower memory consumption.
- Smaller runtime footprint.
- Excellent cloud-native performance.
- Better serverless deployments.

---

# Project High-Level Architecture

```text
                    Client
                       │
                       ▼
             REST Resource Layer (RESTful APIs)
                       │
                       ▼
               Service Layer
                       │
                       ▼
             Repository Layer
                       │
                       ▼
      Hibernate ORM with Panache
                       │
                       ▼
               PostgreSQL Database
```

Each incoming request follows this flow before a response is returned to the client.

### Request Processing Flow

The following diagram shows how a typical HTTP request is processed.

```text
HTTP Request
      │
      ▼
PropertyResource
      │
      ▼
Bean Validation
      │
      ▼
PropertyService
      │
      ▼
PropertyRepository
      │
      ▼
Hibernate ORM + Panache
      │
      ▼
PostgreSQL Database
      │
      ▼
PropertyResponse
      │
      ▼
ApiResponse
      │
      ▼
HTTP Response
```

Every layer performs only its own responsibility.

---

# Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.sirajchaudhary.propertyfinder
│   │       ├── constants
│   │       ├── dto
│   │       │   ├── request
│   │       │   └── response
│   │       ├── entity
│   │       ├── enums
│   │       ├── exception
│   │       ├── health
│   │       ├── mapper
│   │       ├── repository
│   │       ├── resource
│   │       ├── service
│   │       │   └── impl
│   │       └── util
│   │
│   └── resources
│       ├── application.properties
│       └── import.sql
│
└── test
```

**Note:** The initial project structure was generated using the official Quarkus Project Generator: https://code.quarkus.io

---

# Technology Stack

| Category | Technology                 |
|-----------|----------------------------|
| Programming Language | Java 21                    |
| Framework | **Quarkus 3**              |
| REST Framework | RESTEasy Reactive          |
| ORM | Hibernate ORM with Panache |
| Database | PostgreSQL                 |
| Dependency Injection | CDI (Arc)                  |
| Validation | Hibernate Validator        |
| Cache | Quarkus Cache              |
| Health Checks | SmallRye Health            |
| API Documentation | OpenAPI & Swagger UI       |
| Build Tool | Maven                      |
| Containerization | Docker                     |
| Native Compilation | GraalVM Native Image       |
| Boilerplate Reduction | Lombok                     |

---

# What You'll Learn

This project demonstrates many concepts used in modern enterprise backend development.

### Java

- Java 21
- Builder Pattern
- Records
- Enums
- Exception Handling
- Generics

### Quarkus

- REST APIs
- Dependency Injection
- Hibernate ORM
- Panache
- Bean Validation
- Application Caching
- Health Checks
- OpenAPI
- Docker
- GraalVM Native Image

### Database

- PostgreSQL
- JPQL
- Dynamic Queries
- Searching
- Filtering
- Sorting
- Pagination

### Software Design

- Layered Architecture
- Repository Pattern
- Service Pattern
- DTO Pattern
- Mapper Pattern
- Generic Response Pattern
- Separation of Concerns
- Constructor Injection

---

# What is Quarkus?


Quarkus is an open-source Java framework designed specifically for building modern, cloud-native applications.

Unlike traditional Java frameworks, Quarkus performs much of its work during the build phase instead of application startup. This significantly reduces startup time and memory consumption, making it an excellent choice for containers, Kubernetes and serverless platforms.

Quarkus is built on top of well-known Java standards and integrates seamlessly with popular libraries such as Hibernate ORM, RESTEasy, CDI, Jakarta Validation and many others.

Official Website

```text
https://quarkus.io
```

### History of Quarkus


Quarkus was created to modernize Java development for cloud environments.

Important milestones:

- Developed by **Red Hat**.
- First announced in **March 2019**.
- Built specifically for **Kubernetes** and **containerized applications**.
- Designed to solve Java startup and memory challenges in cloud deployments.
- Supports both traditional JVM execution and GraalVM Native Image.
- Has become one of the most popular cloud-native Java frameworks.

### Why Was Quarkus Created?

Traditional enterprise Java applications work extremely well for long-running systems, but cloud environments introduced new challenges.

Common challenges:

- Slower application startup.
- Higher memory consumption.
- Larger deployment artifacts.
- Increased infrastructure costs.
- Less suitable for serverless computing.

Quarkus addresses these challenges by optimizing applications during the build process instead of the runtime.

As a result, applications become:

- Faster to start.
- Smaller in size.
- More memory efficient.
- Better suited for containers.
- Easier to deploy in cloud environments.

---

# Why Choose Quarkus?

Quarkus offers several advantages for modern backend development.

### Fast Startup

How it works:

- Performs framework initialization during build time.
- Reduces runtime initialization.
- Loads only required components.

Benefits:

- Faster application startup.
- Faster container startup.
- Better serverless execution.
- Faster deployments.

### Low Memory Consumption

How it works:

- Eliminates unnecessary runtime processing.
- Optimizes dependency loading.
- Reduces JVM overhead wherever possible.

Benefits:

- Lower cloud costs.
- Better container density.
- Improved scalability.

### Build-Time Optimization

Unlike traditional frameworks, Quarkus performs many tasks before the application starts.

Examples:

- Bean discovery.
- Dependency analysis.
- Annotation processing.
- Configuration optimization.
- Extension initialization.

Benefits:

- Smaller runtime footprint.
- Better performance.
- Reduced startup time.

### Live Coding

Quarkus provides one of the best development experiences available for Java.

Command:

```bash
mvn quarkus:dev
```

Features:

- Automatic recompilation.
- Instant reload.
- No server restart.
- Immediate feedback.

Benefits:

- Faster development.
- Increased productivity.
- Better developer experience.

### Developer UI

Quarkus includes a built-in Developer UI for development mode.

Default URL:

```text
http://localhost:8080/q/dev
```

Provides information about:

- Installed extensions.
- REST endpoints.
- CDI beans.
- Configuration.
- Health checks.
- OpenAPI documentation.

Benefits:

- Easier debugging.
- Faster learning.
- Better visibility into the application.

### Extension-Based Architecture

Quarkus follows a modular architecture based on extensions.

Instead of including every feature by default, developers add only the extensions they need.

Benefits:

- Smaller applications.
- Faster builds.
- Better modularity.
- Easier maintenance.
- Lower memory usage.

### Docker Support

Quarkus provides built-in support for containerization.

Automatically generates Dockerfiles for different deployment strategies.

Available Dockerfiles:

- Dockerfile.jvm
- Dockerfile.legacy-jar
- Dockerfile.native
- Dockerfile.native-micro

Benefits:

- Minimal Docker configuration.
- Faster container adoption.
- Consistent deployments.

### Native Image Support

Quarkus integrates with GraalVM Native Image.

Instead of running on the JVM, the application can be compiled into a native executable.

Benefits:

- Near-instant startup.
- Very low memory usage.
- Smaller deployment size.
- Excellent serverless performance.

### Kubernetes Ready

Quarkus was designed with Kubernetes in mind.

Provides excellent support for:

- Docker containers.
- Kubernetes deployments.
- OpenShift.
- Cloud-native infrastructure.

Benefits:

- Easier deployment.
- Better scalability.
- Faster recovery.
- Cloud-first architecture.

### Standards Based

Quarkus builds upon existing Java standards rather than introducing proprietary APIs.

Supports:

- Jakarta REST (JAX-RS)
- CDI
- Jakarta Persistence (JPA)
- Hibernate ORM
- Jakarta Validation
- MicroProfile Specifications

This allows developers familiar with Java EE or Jakarta EE to adopt Quarkus quickly.

👉 Quarkus is an excellent choice for backend systems that require high performance, fast startup and cloud-native deployment.  Quarkus is optimized primarily for backend services and cloud-native workloads.

---

# Quarkus vs Spring Boot


Both Quarkus and Spring Boot are excellent Java frameworks.

The choice depends on project requirements rather than which framework is "better".

| Feature | Quarkus | Spring Boot |
|----------|----------|-------------|
| Startup Time | Excellent | Good |
| Memory Usage | Low | Moderate |
| Native Image Support | Excellent | Good |
| Container Deployments | Excellent | Excellent |
| Kubernetes Support | Excellent | Excellent |
| Enterprise Adoption | Growing | Very High |
| Learning Resources | Good | Extensive |
| Ecosystem | Growing | Very Large |

### Choose Quarkus when:

- Building new cloud-native applications.
- Developing microservices.
- Deploying on Kubernetes.
- Startup time is important.
- Memory usage must be minimized.
- Native Image support is required.

### Choose Spring Boot when:

- Working in an existing Spring ecosystem.
- Using Spring-specific modules.
- Maintaining legacy Spring applications.
- Native compilation is not a requirement.

---

# Quarkus Extensions

### What are Quarkus Extensions?

Quarkus follows a modular architecture based on extensions.

Instead of shipping every feature as part of the core framework, Quarkus allows developers to include only the functionality required by the application.

Each extension adds support for a specific capability such as REST APIs, database access, validation, caching or health checks.

This approach keeps applications lightweight while reducing startup time and memory usage.

Benefits:

- Smaller applications.
- Faster startup.
- Lower memory consumption.
- Better modularity.
- Reduced dependencies.
- Easier maintenance.
- Better build-time optimization.

Extensions can be added using the Quarkus CLI, Maven or the Quarkus Project Generator.

Example:

```bash
mvn quarkus:add-extension -Dextensions="hibernate-orm-panache"
```

### Extensions Used in This Project

The following Quarkus extensions are used in this application.

| Extension | Purpose |
|------------|---------|
| RESTEasy Reactive | Build REST APIs |
| Hibernate ORM with Panache | Database operations |
| PostgreSQL JDBC | PostgreSQL connectivity |
| Hibernate Validator | Request validation |
| SmallRye OpenAPI | OpenAPI & Swagger UI |
| SmallRye Health | Health endpoints |
| Quarkus Cache | In-memory caching |
| Arc (CDI) | Dependency Injection |
| REST Client | REST client support (future integrations) |
| Fault Tolerance | Retry, Timeout and Circuit Breaker support (future enhancements) |


### How These Extensions Work Together in This Project

The following diagram illustrates how the major extensions interact within this project.

```text
                 Client
                    │
                    ▼
          RESTEasy Reactive
                    │
                    ▼
            Resource Layer
                    │
                    ▼
             Arc (CDI Injection)
                    │
                    ▼
             Service Layer
                    │
         ┌──────────┴──────────┐
         │                     │
         ▼                     ▼
 Quarkus Cache          Hibernate Validator
         │                     │
         └──────────┬──────────┘
                    ▼
          Hibernate ORM + Panache
                    │
                    ▼
            PostgreSQL JDBC
                    │
                    ▼
             PostgreSQL Database

                    ▲
                    │
           SmallRye Health

                    ▲
                    │
         SmallRye OpenAPI
```

---

# Hibernate ORM with Panache (Quarkus Extension)

### What is Hibernate ORM?

Hibernate ORM (Object Relational Mapping) is one of the most popular Java persistence frameworks.

It maps Java objects to database tables, allowing developers to work with Java objects instead of writing SQL for every database operation.

Responsibilities:

- Object-to-table mapping.
- SQL generation.
- CRUD operations.
- Transaction management.
- Relationship management.
- Query execution.

Without Hibernate, developers need to write JDBC code manually for most database operations.

### What is Panache?

Panache is a persistence library provided by Quarkus that simplifies Hibernate ORM.

It reduces boilerplate code by providing built-in CRUD operations, pagination, sorting and query support.

Instead of writing large repository implementations, developers can focus on business-specific queries.

Benefits:

- Less boilerplate code.
- Cleaner repositories.
- Simpler CRUD operations.
- Easier query creation.
- Better readability.
- Faster development.

### Why Use Panache?

Compared to traditional JPA development, Panache significantly reduces boilerplate code.

Advantages:

- Minimal repository implementation.
- Built-in CRUD operations.
- Simple pagination.
- Simple sorting.
- Easy JPQL queries.
- Cleaner code.
- Better readability.
- Faster development.
- Easier maintenance.

For applications using Quarkus, Panache provides a simple yet powerful abstraction over Hibernate ORM while preserving the flexibility of JPA.

### PanacheRepository

This project uses the **Repository Pattern** by extending `PanacheRepository`.

Example:

```java
@ApplicationScoped
public class PropertyRepository
        implements PanacheRepository<Property> {

}
```

Once a repository extends `PanacheRepository`, Quarkus automatically provides many commonly used database operations.

No implementation is required for basic CRUD operations.

### Panache Built-in CRUD Operations

Panache provides many ready-to-use methods.

Insert

```
persist(property);
```

Find By Id

```
findById(id);
```

Retrieve All Records

```
listAll();
```

or

```
findAll().list();
```

Count Records

```
count();
```

Delete Entity

```
delete(property);
```

Delete By Id

```
deleteById(id);
```

Find Using JPQL

```
find("city", city);
```

Check Record Existence

```
count("city", city) > 0
```

These operations are available without writing any SQL or repository implementation.

### Why Create a Custom Repository?

Although Panache provides CRUD operations, enterprise applications usually require custom database queries.

Examples include:

- Duplicate validation.
- Searching.
- Filtering.
- Reporting.
- Dynamic JPQL.
- Business-specific queries.

For this reason, this project includes a dedicated `PropertyRepository`.

Responsibilities:

- Duplicate property validation.
- Dynamic search query generation.
- Database access.
- Pagination.
- Sorting.
- Filtering.

Business logic remains inside the service layer.

### Dynamic Search

Instead of creating multiple repository methods for every search combination, this project implements a single reusable search method.

Current method:

```text
searchProperties()
```

Supported filters:

- Keyword
- City
- State
- Listing Type
- Property Type
- Minimum Price
- Maximum Price

Only the filters supplied by the client are included in the generated JPQL query.

Benefits:

- Cleaner implementation.
- Easier maintenance.
- Easily extensible.
- Fewer repository methods.

### Keyword Search

The keyword search performs a case-insensitive search across multiple columns.

Current searchable fields:

- Title
- Description
- Locality

Example:

```text
GET /api/v1/properties?keyword=luxury
```

JPQL uses the SQL `LIKE` operator together with `LOWER()` to provide case-insensitive searching.

### Filtering

Filtering allows clients to narrow search results.

Current filters:

| Filter | Description |
|----------|-------------|
| city | Search by city |
| state | Search by state |
| listingType | SALE or RENT |
| propertyType | Apartment, House, Villa, Land |
| minPrice | Minimum property price |
| maxPrice | Maximum property price |

Filters can be combined within the same request.

Example:

```text
GET /api/v1/properties?city=Hyderabad&propertyType=VILLA&listingType=SALE
```

### Sorting

Sorting determines the order in which records are returned.

The client specifies:

- sortBy
- sortDirection

Example:

```text
GET /api/v1/properties?sortBy=price&sortDirection=desc
```

The service converts these parameters into a Panache `Sort` object before executing the query.

Supported sort directions:

- asc
- desc

Benefits:

- Runtime sorting.
- No additional repository methods.
- Flexible API.

### Pagination

Pagination retrieves only a subset of records instead of the complete dataset.

The client specifies:

- page
- size

Example:

```text
GET /api/v1/properties?page=0&size=10
```

The service creates a Panache `Page` object and applies it to the query.

Benefits:

- Faster database queries.
- Lower memory consumption.
- Reduced network traffic.
- Better scalability.

### Duplicate Property Validation

Before creating a new property, the application checks whether a property with the same title and address already exists.

Repository method:

```
existsByTitleAndAddressIgnoreCase()
```

Benefits:

- Prevents duplicate listings.
- Maintains data integrity.
- Improves data quality.

### Why JPQL Instead of SQL?

JPQL (Java Persistence Query Language) operates on Java entities instead of database tables.

Example:

JPQL

```
FROM Property
```

SQL

```sql
SELECT * FROM properties
```

Benefits:

- Database independent.
- Object-oriented syntax.
- Easier maintenance.
- Better portability.

Hibernate automatically converts JPQL into database-specific SQL.

Repository Flow

The repository is responsible only for interacting with the database.

```text
PropertyService
       │
       ▼
PropertyRepository
       │
       ▼
Panache Query
       │
       ▼
Hibernate ORM
       │
       ▼
PostgreSQL
```

The repository never contains business rules.

Business validation remains inside the service layer.

---

# REST API Documentation

### API Overview

The Property Finder Service exposes RESTful APIs for managing property listings.

The APIs follow REST principles and exchange data in JSON format.

Base URL

```text
http://localhost:8080/api/v1/properties
```

All successful responses return a standardized `ApiResponse<T>`.

All failures return a standardized `ErrorResponse`.

### Available APIs

| Method | Endpoint | Description |
|----------|----------|-------------|
| POST | `/api/v1/properties` | Create a new property |
| GET | `/api/v1/properties/{id}` | Retrieve property by id |
| GET | `/api/v1/properties` | Search properties |
| PUT | `/api/v1/properties/{id}` | Update existing property |
| DELETE | `/api/v1/properties/{id}` | Delete property |

### Create Property API

Creates a new property listing.

Endpoint

```http
POST /api/v1/properties
```

Request Body

```json
{
  "title": "Luxury Villa",
  "description": "Premium villa in Gachibowli",
  "listingType": "SALE",
  "propertyType": "VILLA",
  "price": 25000000,
  "city": "Hyderabad",
  "state": "Telangana",
  "country": "India",
  "locality": "Gachibowli",
  "address": "Road No. 12",
  "bedrooms": 4,
  "bathrooms": 4,
  "balconies": 2,
  "areaSqFt": 3500,
  "floor": 2,
  "totalFloors": 2,
  "furnishingStatus": "FULLY_FURNISHED",
  "parkingAvailable": true,
  "petFriendly": true,
  "availableFrom": "2026-08-01",
  "ownerName": "John",
  "ownerContact": "9999999999",
  "featured": true
}
```

Response

```text
HTTP/1.1 201 Created
```

Processing Steps

- Validate request body.
- Check for duplicate property.
- Generate property code.
- Save property into PostgreSQL.
- Return created property details.

### Retrieve Property APIs

Retrieves a property using its unique identifier.

Endpoint

```http
GET /api/v1/properties/{id}
```

Example

```http
GET /api/v1/properties/1
```

Response

```text
HTTP/1.1 200 OK
```

Processing Steps

- Validate id.
- Check cache.
- Retrieve property from database if required.
- Convert entity into response DTO.
- Return property details.

### Search Properties API (Important)

Retrieves property listings using dynamic search criteria.

Endpoint

```http
GET /api/v1/properties
```

Unlike many applications that create multiple search APIs, this project uses a single endpoint capable of handling searching, filtering, sorting and pagination.

Supported Query Parameters

| Parameter | Description | Required |
|------------|-------------|----------|
| page | Page number | No |
| size | Records per page | No |
| sortBy | Sorting field | No |
| sortDirection | asc or desc | No |
| keyword | Search title, description and locality | No |
| city | Filter by city | No |
| state | Filter by state | No |
| listingType | SALE or RENT | No |
| propertyType | APARTMENT, HOUSE, VILLA, LAND | No |
| minPrice | Minimum price | No |
| maxPrice | Maximum price | No |

Search Examples

Retrieve all properties

```http
GET /api/v1/properties
```

Retrieve first page

```http
GET /api/v1/properties?page=0&size=10
```

Retrieve second page

```http
GET /api/v1/properties?page=1&size=10
```

Sort by price

```http
GET /api/v1/properties?sortBy=price&sortDirection=desc
```

Search by keyword

```http
GET /api/v1/properties?keyword=luxury
```

Search by city

```http
GET /api/v1/properties?city=Hyderabad
```

Search by property type

```http
GET /api/v1/properties?propertyType=VILLA
```

Search by listing type

```http
GET /api/v1/properties?listingType=SALE
```

Search by price range

```http
GET /api/v1/properties?minPrice=10000000&maxPrice=30000000
```

Search using multiple filters

```http
GET /api/v1/properties?page=0
                        &size=10
                        &city=Hyderabad
                        &listingType=SALE
                        &propertyType=VILLA
                        &minPrice=10000000
                        &maxPrice=30000000
                        &sortBy=price
                        &sortDirection=desc
```

Processing Steps

- Read query parameters.
- Build `PropertySearchRequest`.
- Create Panache `Sort`.
- Build dynamic JPQL.
- Execute query.
- Apply pagination.
- Return paginated response.

### Update Property API

Updates an existing property.

Endpoint

```http
PUT /api/v1/properties/{id}
```

Example

```http
PUT /api/v1/properties/1
```

Request Body

Use the same JSON structure as the Create Property API.

Response

```text
HTTP/1.1 200 OK
```

Processing Steps

- Validate request.
- Verify property exists.
- Update editable fields.
- Save changes.
- Refresh cache.
- Return updated property.

### Delete Property API

Deletes an existing property.

Endpoint

```http
DELETE /api/v1/properties/{id}
```

Example

```http
DELETE /api/v1/properties/1
```

Response

```text
HTTP/1.1 200 OK
```

Processing Steps

- Verify property exists.
- Delete property.
- Clear cache.
- Return success response.

### Success Response Format

Every successful API returns the following structure.

```json
{
  "timestamp": "2026-07-13T10:20:30",
  "status": 200,
  "message": "Operation completed successfully.",
  "data": {}
}
```

Response Fields

| Field | Description |
|---------|-------------|
| timestamp | Response generation time |
| status | HTTP status code |
| message | Success message |
| data | Response payload |

### Error Response Format

All failures return a consistent error structure.

```json
{
  "timestamp": "2026-07-13T10:25:15",
  "status": 404,
  "error": "Not Found",
  "message": "Property not found with id: 100",
  "path": "/api/v1/properties/100",
  "errors": []
}
```

Response Fields

| Field | Description |
|---------|-------------|
| timestamp | Error timestamp |
| status | HTTP status code |
| error | Error category |
| message | Error description |
| path | Requested endpoint |
| errors | Validation errors, if any |

### HTTP Status Codes

| Status Code | Description |
|--------------|-------------|
| 200 | Request processed successfully |
| 201 | Resource created successfully |
| 400 | Invalid request |
| 404 | Resource not found |
| 409 | Duplicate resource |
| 500 | Internal server error |

### API Best Practices Followed

This project follows several REST API best practices.

- Resource-oriented endpoints.
- Proper HTTP methods.
- Meaningful HTTP status codes.
- JSON request and response bodies.
- Standardized response structure.
- Standardized error responses.
- Bean Validation for request validation.
- Pagination for large datasets.
- Dynamic filtering.
- Dynamic sorting.
- Clean URL design.
- Consistent naming conventions.

---

# How to Run This Application

This section provides a step-by-step guide for setting up, building and running the application in different environments.

Supported options:

- Run in Development Mode
- Run as a JVM Application
- Run using Docker
- Run as a GraalVM Native Executable
- Run as a Native Docker Container

### Prerequisites

Ensure the following software is installed.

| Software | Recommended Version |
|-----------|---------------------|
| Java | 21 or later |
| Maven | 3.9 or later |
| PostgreSQL | 14 or later |
| Docker | Latest |
| GraalVM | Optional (Native Image) |

Verify the installation.

```bash
java -version
mvn -version
docker --version
psql --version
```

### Step 1 - Clone the Repository

Clone the project.

```bash
git clone https://github.com/sirajchaudhary/quarkus-cloudnative-microservice.git
```

Navigate to the project directory.

```bash
cd quarkus-cloudnative-microservice\property-finder-service
```

### Step 2 - Create PostgreSQL Database

Create a new database.

```sql
CREATE DATABASE propertyfinderdb;
```

Run the script to create table and sample data

```sql
src/main/resources/db/property-finder-db.sql
```

### Step 3 - Configure Database Connection

Open

```text
src/main/resources/application.properties
```

Update the datasource configuration.

```properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=postgres
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/propertyfinderdb
```

Replace the username, password and port according to your local PostgreSQL installation.

### Step 4 - Run the Application in Development Mode

Start Quarkus Development Mode.

```bash
mvn quarkus:dev
```

Quarkus automatically enables several development features:

- Live Coding
- Automatic Restart
- Swagger UI
- OpenAPI
- Developer UI
- Health Endpoints

<img width="3420" height="1458" alt="image" src="https://github.com/user-attachments/assets/e8a626eb-6fca-4f93-bf85-01a29886400f" />

The application starts on:

```text
http://localhost:8080
```

Once the application is running, verify the following endpoints.

#### Swagger UI

Interactive interface for exploring and testing REST APIs.

```text
http://localhost:8080/q/swagger-ui
```

<img width="3584" height="2144" alt="image" src="https://github.com/user-attachments/assets/82acfca8-7872-469a-a3f3-34e40afb88fb" />

#### OpenAPI Specification

Generates the OpenAPI specification for the application.

```text
http://localhost:8080/q/openapi
```

#### Developer UI

The **Quarkus Developer UI** is a built-in dashboard available only in development mode. It provides quick access to application configuration, installed extensions, CDI beans, REST endpoints, health checks, logs and other development tools.

```text
http://localhost:8080/q/dev
```

<img width="3584" height="2094" alt="image" src="https://github.com/user-attachments/assets/9bff6b89-4420-4528-aab7-a0c6807919ce" />
<br /><br />
<img width="3584" height="2120" alt="image" src="https://github.com/user-attachments/assets/ec47bc50-99bb-436e-9da0-19906ec9fe05" />
<br /><br />
<img width="3584" height="2130" alt="image" src="https://github.com/user-attachments/assets/24204d80-15d2-4c47-966b-8a5f5876f577" />
<br /><br />
<img width="3584" height="2064" alt="image" src="https://github.com/user-attachments/assets/64a9b44a-a4eb-42d4-a2c1-474e7f23e4d3" />
<br /><br />
<img width="3584" height="2124" alt="image" src="https://github.com/user-attachments/assets/efc2d277-a53a-4638-8197-cefcbc558ce0" />

#### Health Endpoints

Application Health

```text
http://localhost:8080/health
```

<img width="3584" height="844" alt="image" src="https://github.com/user-attachments/assets/745b310d-f101-41d3-8e81-ad11370c68d5" />

Readiness Check

```text
http://localhost:8080/health/ready
```

Liveness Check

```text
http://localhost:8080/health/live
```

Press **Ctrl + C** to stop the running application.

### Step 5 - Run the APIs

**Search Properties API** (This API demonstrates dynamic searching, filtering, sorting and pagination. Try different query parameters to see how the results change.)
<br /><br />
<img width="1438" height="2000" alt="image" src="https://github.com/user-attachments/assets/fef62ba7-384e-4c66-a1b5-9a2b3be06966" />
<br /><br />
**Create (New) Property API**
<br /><br />
<img width="1430" height="1888" alt="image" src="https://github.com/user-attachments/assets/50f861e9-cc48-4cb2-ab7f-e7255e16b1af" />
<br /><br />
**Get Property By Id API**
<br /><br />
<img width="1438" height="1488" alt="image" src="https://github.com/user-attachments/assets/bb28765f-6e0f-4ed9-b0f2-783871286811" />
<br /><br />
**Update Property By Id API**
<br /><br />
<img width="1438" height="1992" alt="image" src="https://github.com/user-attachments/assets/dd1b884c-6be8-4d45-b102-62ad6f1813a1" />
<br /><br />
**Delete Property By Id API**
<br /><br />
<img width="1436" height="1196" alt="image" src="https://github.com/user-attachments/assets/1e7e42dd-ca66-4838-862f-c595d78b4716" />
<br /><br />

Press CTRL + C to stop the running application in development mode.

### Step 6 - Build and Run the JVM Application (Executable JAR)

Package the application.

```bash
mvn clean package
```

The packaged application is generated under:

```text
target/quarkus-app/app/property-finder-service-1.0.0-SNAPSHOT.jar
```

Run the application.

```bash
java -jar target/quarkus-app/quarkus-run.jar
```

Explanation:

- Compiles the source code.
- Executes unit tests.
- Packages the application as a runnable Quarkus JVM application.
- Runs the application on the Java Virtual Machine (JVM).
- Suitable for local development, testing and traditional Java deployments.

Access the application.

```text
http://localhost:8080/q/swagger-ui/
```

Press **Ctrl + C** to stop the running application.

<img width="3408" height="1774" alt="image" src="https://github.com/user-attachments/assets/1526977b-f89a-4a88-966f-ad601e3b4070" />
<br />
<img width="3584" height="2136" alt="image" src="https://github.com/user-attachments/assets/c8d40287-3c52-4a7b-9fda-8e29f19aa7f9" />

---

## JVM Deployment vs Native Deployment

Quarkus applications can be deployed in two ways:

### JVM Deployment

The application runs on the Java Virtual Machine (JVM) inside a Docker container.

Advantages:

- Fast build time.
- Easier debugging.
- Ideal for local development.
- Suitable for most enterprise applications.

Recommended for:

- Development
- Testing
- Traditional Java deployments

### Native Deployment

The application is compiled into a platform-specific native executable using GraalVM.

Advantages:

- Extremely fast startup.
- Lower memory consumption.
- No JVM required at runtime.
- Small runtime footprint.
- Excellent for cloud-native and serverless environments.

Recommended for:

- Kubernetes
- OpenShift
- Serverless
- Production environments requiring fast startup and low memory usage

---

### Step 7 - Build and Run the JVM Docker Container

Quarkus provides several Dockerfiles under:

```text
src/main/docker
```

Build the JVM Docker image.

```bash
docker build -f src/main/docker/Dockerfile.jvm -t property-finder-service:latest .
```

Verify the image.

```bash
docker images
```

Run the Docker container.

```bash
docker run -d \
--name property-finder-service \
-p 2026:8080 \
-e QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://host.docker.internal:5432/propertyfinderdb \
property-finder-service:latest
```

Explanation:

- Builds a Docker image containing the Quarkus JVM application.
- Runs the application inside a Docker container.
- The application listens on port **8080** inside the container.
- Docker maps container port **8080** to host port **2026**.
- `host.docker.internal` allows the container to access PostgreSQL running on the host machine.

Access the application.

```text
http://localhost:2026/q/swagger-ui/
```

<img width="3406" height="1288" alt="image" src="https://github.com/user-attachments/assets/ba888823-fedf-404b-8e77-366fde24f313" />
<br /><br />
<img width="3584" height="2150" alt="image" src="https://github.com/user-attachments/assets/4ae0c055-56d2-4715-bf2f-d830e0c2eaea" />
<br /><br />

### Step 8 - Build and Run the GraalVM Native Executable (Its executable and not container)

Unlike the JVM application, this approach **does not generate a JAR file**. Instead, GraalVM compiles the application into a **platform-specific native executable** that runs **directly on the operating system** without requiring a JVM.

Build the native executable.

```bash
mvn clean package -Dnative
```

The generated native executable is available under:

```text
target/property-finder-service-1.0.0-SNAPSHOT-runner
```

Run the native executable.

Linux / macOS

```bash
./target/property-finder-service-1.0.0-SNAPSHOT-runner
```

Windows

```cmd
target\property-finder-service-1.0.0-SNAPSHOT-runner.exe
```

Access the application.

```text
http://localhost:8080/q/swagger-ui/
```

Advantages:

- Compiles the Java application into a platform-specific native executable.
- Runs directly on the operating system.
- Does not require a JVM at runtime.
- Starts significantly faster than the JVM application.
- Consumes considerably less memory.
- Well suited for cloud-native and serverless applications.

> **Note:** Building a native executable locally requires GraalVM with Native Image support installed.

### Step 9 - Build and Run the GraalVM Native Docker Container

This approach builds the native executable **inside a Docker container** and then packages it into a **native Docker image**. Unlike the previous step, the executable runs **inside a Docker container** instead of directly on the operating system.

Build the native executable.

```bash
mvn clean package -Dnative -Dquarkus.native.container-build=true
```

Build the native Docker image.

```bash
docker build -f src/main/docker/Dockerfile.native -t property-finder-service-native:latest .
```

Verify the image.

```bash
docker images
```

Run the native Docker container.

```bash
docker run -d \
  --name property-finder-service-native \
  -p 2026:8080 \
  -e QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://host.docker.internal:5432/propertyfinderdb \
  property-finder-service-native:latest
```

Access the application.

```text
http://localhost:2026/q/swagger-ui/
```

Advantages:

- Builds the native executable inside a Docker container.
- Packages the native executable into a lightweight Docker image.
- Does not include a JVM inside the container.
- Produces smaller Docker images.
- Starts significantly faster than a JVM-based container.
- Consumes considerably less memory.
- Recommended for Kubernetes, OpenShift and other cloud-native platforms.

> **Note:** `-Dquarkus.native.container-build=true` generates a **Linux native executable** inside a Docker container. The generated executable is intended for Docker and Linux environments and **cannot be run directly on macOS or Windows**.

### Step 10 - Useful Docker Commands

List running containers.

```bash
docker ps
```

List all containers.

```bash
docker ps -a
```

View container logs.

```bash
docker logs property-finder-service
```

Follow container logs.

```bash
docker logs -f property-finder-service
```

Stop a container.

```bash
docker stop property-finder-service
```

Start an existing container.

```bash
docker start property-finder-service
```

Restart a container.

```bash
docker restart property-finder-service
```

Remove a container.

```bash
docker rm property-finder-service
```

Remove a Docker image.

```bash
docker rmi property-finder-service:latest
```

### Build Commands Summary

| Task | Command |
|------|---------|
| Run Development Mode | `mvn quarkus:dev` |
| Build JVM Application | `mvn clean package` |
| Run JVM Application | `java -jar target/quarkus-app/quarkus-run.jar` |
| Build JVM Docker Image | `docker build -f src/main/docker/Dockerfile.jvm -t property-finder-service .` |
| Run JVM Docker Container | `docker run -d -p 2026:8080 property-finder-service` |
| Build Native Image | `mvn clean package -Dnative` |
| Build Native Docker Image | `docker build -f src/main/docker/Dockerfile.native -t property-finder-service-native .` |
| Run Native Docker Container | `docker run -d -p 2026:8080 property-finder-service-native` |

### 👉 Development Tips

For day-to-day development:

- Use `mvn quarkus:dev` for the fastest development cycle.
- Use Swagger UI to test REST APIs.
- Use Developer UI to inspect Quarkus internals.
- Use Health endpoints to verify application readiness.
- Use Docker to validate container deployments.
- Build Native Images before production deployment to evaluate startup time and memory usage.

---

# Future Enhancements

The current implementation focuses on the core concepts of Quarkus and backend development. However, the architecture is intentionally designed so that it can evolve into a complete enterprise-grade real estate platform similar to **99acres**, **MagicBricks**, **Housing.com**, or **NoBroker**.

As the application grows, individual business capabilities can be extracted into independent microservices.

### Proposed Microservices

### API Gateway

Responsibilities:

- Single entry point for all client requests.
- Request routing.
- Authentication.
- Rate limiting.
- Request logging.
- Load balancing.

Possible Technology:

- Spring Cloud Gateway
- Quarkus HTTP Gateway
- Kong API Gateway

### Authentication Service

Responsibilities:

- User Registration.
- User Login.
- JWT Token Generation.
- Password Reset.
- OAuth2 Authentication.
- Role Management.

Possible Integrations:

- Keycloak
- OAuth2
- OpenID Connect

### User Service

Responsibilities:

- Customer Profile
- Agent Profile
- Property Owner Profile
- User Preferences
- Address Management

### Property Service

Responsibilities:

- Create Property
- Update Property
- Delete Property
- Property Status
- Property Availability
- Property Verification

### Property Search Service

Responsibilities:

- Search Properties
- Filtering
- Pagination
- Sorting
- Search Suggestions
- Recent Searches

Future Improvements:

- Elasticsearch
- OpenSearch
- Full-text Search
- Fuzzy Search

### Property Recommendation Service

Responsibilities:

- Similar Properties
- Personalized Recommendations
- Trending Properties
- Recently Viewed Properties

Possible Technologies:

- Machine Learning
- AI Recommendation Engine

### Property Image Service

Responsibilities:

- Upload Images
- Delete Images
- Image Compression
- Thumbnail Generation
- Image Optimization

Storage Options:

- AWS S3
- Azure Blob Storage
- Google Cloud Storage

### Property Document Service

Responsibilities:

- Sale Agreement
- Ownership Documents
- Floor Plans
- Property Brochures
- Legal Documents

### Booking Service

Responsibilities:

- Schedule Property Visits
- Appointment Management
- Booking History
- Booking Cancellation

### Agent Service

Responsibilities:

- Agent Registration
- Property Assignment
- Lead Management
- Agent Availability

### Customer Service

Responsibilities:

- Customer Profile
- Saved Searches
- Favorites
- Recently Viewed Properties

### Wishlist Service

Responsibilities:

- Favorite Properties
- Saved Properties
- Compare Properties

### Review & Rating Service

Responsibilities:

- Property Reviews
- Property Ratings
- Agent Reviews
- Review Moderation

### Payment Service

Responsibilities:

- Subscription Payments
- Premium Listings
- Featured Properties
- Invoice Generation
- Payment History

Possible Integrations:

- Razorpay
- Stripe
- PayPal

### Notification Service

Responsibilities:

- Email Notifications
- SMS Notifications
- Push Notifications
- WhatsApp Notifications

Notification Examples:

- Property Approved
- Booking Confirmed
- Property Sold
- Price Reduced
- New Matching Property

### Analytics Service

Responsibilities:

- Property Views
- Popular Searches
- User Activity
- Dashboard Reports
- Business Analytics

### Reporting Service

Responsibilities:

- Sales Reports
- Revenue Reports
- Property Reports
- User Reports

### Audit Service

Responsibilities:

- User Activity Logs
- Property Changes
- Login History
- Security Audit

---

# Future Infrastructure Improvements

The platform can be enhanced with modern cloud-native infrastructure.

### Caching

- Redis
- Distributed Cache
- Cache Replication
- Cache Expiration Policies

### Messaging

Introduce asynchronous communication between services.

Possible Technologies:

- Apache Kafka
- RabbitMQ
- ActiveMQ

Example Events:

- Property Created
- Property Updated
- Booking Confirmed
- Payment Completed
- Notification Sent

### Monitoring

Monitor application health and performance.

Possible Technologies:

- Micrometer
- Prometheus
- Grafana

Monitor:

- CPU Usage
- Memory Usage
- Request Count
- Response Time
- Database Connections
- Cache Statistics

### Distributed Tracing

Track requests across multiple microservices.

Possible Technologies:

- OpenTelemetry
- Jaeger
- Zipkin

Benefits:

- Easier debugging.
- Performance analysis.
- End-to-end request visibility.

### Logging

Centralize application logs.

Possible Technologies:

- ELK Stack
- OpenSearch
- Loki

Benefits:

- Centralized logging.
- Faster troubleshooting.
- Better production monitoring.

### Security

Strengthen application security.

Possible Improvements:

- JWT Authentication
- OAuth2
- OpenID Connect
- Keycloak
- Role-Based Access Control (RBAC)
- API Keys
- HTTPS
- Password Encryption

### CI/CD

Automate build, testing and deployment.

Possible Platforms:

- GitHub Actions
- Jenkins
- GitLab CI
- Azure DevOps

### Container Orchestration

Deploy and manage services using Kubernetes.

Possible Platforms:

- Kubernetes
- OpenShift
- Amazon EKS
- Azure AKS
- Google GKE

Capabilities:

- Auto Scaling
- Rolling Updates
- Service Discovery
- Self Healing
- Load Balancing
- Configuration Management

### Cloud Deployment

The application can be deployed to public cloud providers.

Supported Platforms:

- Amazon Web Services (AWS)
- Microsoft Azure
- Google Cloud Platform (GCP)

Possible Services:

- Virtual Machines
- Kubernetes Clusters
- Managed PostgreSQL
- Object Storage
- Load Balancers
- Secret Management

### Potential Future Features

Additional business capabilities that can be implemented.

- Property Approval Workflow
- Property Verification
- Multi-language Support
- Multi-currency Support
- Multi-tenancy
- Advertisement Management
- Featured Listings
- Property Comparison
- Mortgage Calculator
- EMI Calculator
- Nearby Schools & Hospitals
- Google Maps Integration
- Location-based Search
- Saved Searches
- AI-powered Property Recommendations
- AI Chat Assistant
- Virtual Property Tours
- Video Uploads
- Property Availability Calendar
- Admin Dashboard
- Agent Dashboard
- Customer Dashboard

---

# Conclusion

This project demonstrates how to build a clean, maintainable and production-ready REST API using Quarkus and modern Java technologies.

It covers the complete development lifecycle, including API design, layered architecture, validation, persistence, dynamic querying, caching, health monitoring, containerization and native compilation.

Although the current implementation focuses on a single service, the architecture provides a solid foundation for evolving into a scalable microservices-based real estate platform capable of supporting enterprise workloads.

---

# References

- Quarkus: [https://quarkus.io](https://quarkus.io)
- Quarkus Guides: [https://quarkus.io/guides](https://quarkus.io/guides)
- Quarkus Project Generator: [https://code.quarkus.io](https://code.quarkus.io)
- Hibernate ORM: [https://hibernate.org/orm/](https://hibernate.org/orm/)
- PostgreSQL: [https://www.postgresql.org/docs/](https://www.postgresql.org/docs/)
- GraalVM: [https://www.graalvm.org/](https://www.graalvm.org/)
- Docker: [https://docs.docker.com/](https://docs.docker.com/)
- Jakarta EE: [https://jakarta.ee/](https://jakarta.ee/)

---


# Appendix

### Maven Build Lifecycle

During development, the following Maven commands are commonly used.

| Command | Description |
|----------|-------------|
| `mvn clean` | Removes previously generated build files |
| `mvn compile` | Compiles the source code |
| `mvn test` | Executes unit tests |
| `mvn package` | Creates the deployable application |
| `mvn verify` | Executes verification checks |
| `mvn install` | Installs the artifact into the local Maven repository |
| `mvn quarkus:dev` | Starts Quarkus Development Mode |

### Quarkus Generated Dockerfiles

Quarkus automatically generates multiple Dockerfiles to support different deployment strategies.

| Dockerfile | Purpose |
|------------|---------|
| Dockerfile.jvm | Standard JVM deployment |
| Dockerfile.legacy-jar | Runs using a traditional executable JAR |
| Dockerfile.native | Runs as a GraalVM Native Image |
| Dockerfile.native-micro | Optimized native image using a minimal runtime image |

For most production deployments:

- JVM deployment → `Dockerfile.jvm`
- Native deployment → `Dockerfile.native`

---

# License

Free software, [Siraj Chaudhary](https://www.linkedin.com/in/sirajchaudhary/)
