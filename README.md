
# Authentication Backend

## Table of Contents
1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
   - [Prerequisites](#prerequisites)
   - [Installation](#installation)
   - [Running the Application](#running-the-application)
3. [Usage](#usage)
   - [Basic Usage](#basic-usage)
   - [Advanced Features](#advanced-features)
4. [API Documentation](#api-documentation)
   - [Authentication](#authentication)
   - [User Management](#user-management)
   - [Role Management](#role-management)
5. [Configuration](#configuration)
   - [Environment Variables](#environment-variables)
   - [Configuration Files](#configuration-files)
6. [Testing](#testing)
   - [Running Tests](#running-tests)
   - [Test Coverage](#test-coverage)
7. [License](#license)
8. [Acknowledgments](#acknowledgments)

## Introduction
This project is a secure authentication backend using JWT tokens for user management and role-based access control (RBAC). It provides robust APIs for user authentication, role management, and secure token generation.

## Getting Started

### Prerequisites
Before you begin, ensure you have met the following requirements:
- **Java**: Version 17 or higher
- **Maven**: Version 3.6 or higher
- **MySQL**: Installed and configured
- **Spring Boot**: Compatible with version 3.2.1 (as specified in `pom.xml`)

### Installation
To install the project, follow these steps:

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <project-directory>
   ```

2. Configure the database connection in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

### Running the Application
To run the application, use the following command:
   ```bash
   mvn spring-boot:run
   ```
The application will start on `http://localhost:8080`.

Local host index.html file in 3000 port, and voila! The application ui is good to go.
![Base UI](Snippets/Screenshot 2024-11-28 at 6.10.09 PM.png "Base UI" )

## Usage

### Basic Usage
Once the application is running, you can access the API endpoints. For example, to authenticate a user, send a POST request to `/api/auth/login` with the following JSON body:
```json
{
    "username": "test",
    "password": "test"
}
```

### Advanced Features
- **Token Refresh**: Refresh your JWT token by sending a POST request to `/api/auth/refresh`.
- **Role Management**: Admin users can manage roles through the `/api/roles` endpoint.

## API Documentation
![API Documentation](Snippets/Screenshot 2024-11-28 at 12.16.26 AM.png "API Documentation" )
### Authentication
- **POST** `/api/auth/login`
  - **Description**: Authenticates a user and returns a JWT token.
  - **Request Body**:
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```
  - **Response**:
    ```json
    {
      "token": "your_jwt_token"
    }
    ```

### User Management
- **GET** `/api/users`
  - **Description**: Retrieves a list of users.
  - **Response**:
    ```json
    [
      {
        "id": 1,
        "username": "user1",
        "roles": ["ROLE_USER"]
      }
    ]
    ```
![User Management](Snippets/Screenshot 2024-11-28 at 12.42.39 AM.png "User Management" )
### Role Management
- **POST** `/api/roles`
  - **Description**: Creates a new role.
  - **Request Body**:
    ```json
    {
      "name": "ROLE_ADMIN"
    }
    ```
  - **Response**:
    ```json
    {
      "id": 1,
      "name": "ROLE_ADMIN"
    }
    ```
![Role Management](Snippets/Screenshot 2024-11-28 at 4.41.04 PM.png "Role Management" )
## Configuration

### Environment Variables
Key environment variables:
- `JWT_SECRET`: Secret key for signing JWT tokens.
- `DATABASE_URL`: URL for the database.

### Configuration Files
The application uses `application.properties` for configuration. Key properties include:
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your_jwt_secret
```

## Testing

### Running Tests
Run tests using the following command:
```bash
mvn test
```

### Test Coverage
Generate a test coverage report with:
```bash
mvn jacoco:report
```
The report will be available in `target/site/jacoco/index.html`.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments
Special thanks to all contributors and the Spring Boot community for their support.
