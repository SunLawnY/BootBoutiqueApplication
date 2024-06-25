# Boots Bootique

Boots Bootique is a Spring Boot application for managing an inventory of various types of boots. It provides RESTful endpoints to create, read, update, and delete boot records, as well as search for boots based on different criteria.

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Database Configuration](#database-configuration)
- [Building and Testing](#building-and-testing)
- [Authors](#authors)

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 11 or higher
- Maven
- An IDE or text editor of your choice

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/boots-bootique.git
    cd boots-bootique
    ```

2. **Build the project using Maven**:
    ```bash
    mvn clean install
    ```

## Running the Application

To run the application, execute the following command:

```bash
mvn spring-boot:run
```

The application will start on port 8080 by default. You can access it at http://localhost:8080.

## API Endpoints

### Get All Boots
- **URL**: `/api/v1/boots`
- **Method**: `GET`
- **Description**: Retrieves a list of all boots in the inventory.

### Get Boot Types
- **URL**: `/api/v1/boots/types`
- **Method**: `GET`
- **Description**: Retrieves a list of all boot types.

### Add a New Boot
- **URL**: `/api/v1/boots`
- **Method**: `POST`
- **Description**: Adds a new boot to the inventory.
- **Request Body**:
    ```json
    {
      "type": "CHELSEA",
      "material": "leather",
      "size": 10.5,
      "quantity": 6
    }
    ```

### Delete a Boot
- **URL**: `/api/v1/boots/{id}`
- **Method**: `DELETE`
- **Description**: Deletes a boot by its ID.

### Increment Boot Quantity
- **URL**: `/api/v1/boots/{id}/quantity/increment`
- **Method**: `PUT`
- **Description**: Increments the quantity of a boot by its ID.

### Decrement Boot Quantity
- **URL**: `/api/v1/boots/{id}/quantity/decrement`
- **Method**: `PUT`
- **Description**: Decrements the quantity of a boot by its ID.

### Search Boots
- **URL**: `/api/v1/boots/search`
- **Method**: `GET`
- **Description**: Searches for boots based on material, type, size, and/or quantity.
- **Query Parameters**:
    - `material` (optional): The material of the boots.
    - `type` (optional): The type of boots.
    - `size` (optional): The size of the boots.
    - `quantity` (optional): The minimum quantity of the boots.


## Database Configuration

The application uses H2, an in-memory database, for simplicity and ease of development. You can find the database configuration in `src/main/resources/application.properties`.

```properties
spring.application.name=BootBoutique
server.port=8080
spring.jackson.default-property-inclusion=NON_NULL
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.hibernate.hbm2ddl.auto=create
spring.jpa.hibernate.use-new-id-generator-mappings=false
spring.datasource.initialization-mode=always
spring.datasource.url=jdbc:h2:file:~/boots.db
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
```

You can also use a data.sql file to initialize the database with some sample data. Place the data.sql file in src/main/resources.

Building and Testing
You can build and run tests using Maven.

Build the project:

bash
```
mvn clean install
```

Run tests:
bash
```
mvn test
```
