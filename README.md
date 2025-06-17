# lets-play

A secure REST API built with Spring Boot, featuring JWT authentication, MongoDB integration, and comprehensive user and product management capabilities.

## 🚀 Features

- **JWT Authentication**: Secure token-based authentication system
- **User Management**: Complete CRUD operations for users with role-based access control
- **Product Management**: Full product lifecycle management with ownership validation
- **MongoDB Integration**: NoSQL database with automatic indexing
- **Role-Based Security**: ADMIN and USER roles with different permissions
- **Input Validation**: Comprehensive request validation with custom error handling
- **SSL/HTTPS Support**: Secure communication with SSL certificates
- **Route Protection**: Custom filters for unknown endpoints and authentication

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.4.4
- **Security**: Spring Security with JWT
- **Database**: MongoDB with Spring Data MongoDB
- **Authentication**: Auth0 JWT library
- **Validation**: Jakarta Validation API
- **Build Tool**: Maven
- **Java Version**: 21

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- MongoDB Atlas account (or local MongoDB instance)

## 🔧 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd lets-play
   ```

2. **Configure MongoDB**
   - Update `src/main/resources/application.properties` with your MongoDB connection string
   - The current configuration uses MongoDB Atlas

3. **SSL Certificate** (Optional)
   - The application includes SSL support with a keystore file
   - Modify SSL settings in `application.properties` if needed

4. **Build the project**
   ```bash
   ./mvnw clean install
   ```

5. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

The API will be available at `https://localhost:8080` (HTTPS enabled by default)

## 🔐 Authentication

### Default Users
The application creates default users on startup:
- **Admin**: `cherifmbaye02@gmail.com` / `123456`
- **User**: `zayd@gmail.com` / `password123`

### Login Process
1. **POST** `/api/auth/login`
2. Use the returned JWT token in subsequent requests:
   ```
   Authorization: Bearer <your-jwt-token>
   ```

## 📚 API Endpoints

### Authentication
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| POST | `/api/auth/login` | User login | Public |
| POST | `/api/auth/register` | User registration | Public |

### Users
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| GET | `/api/users` | Get all users | Authenticated |
| POST | `/api/users` | Create user | Admin only |
| GET | `/api/users/{id}` | Get user by ID | Authenticated |
| PUT | `/api/users/{id}` | Update user | Owner or Admin |
| DELETE | `/api/users/{id}` | Delete user | Owner or Admin |
| GET | `/api/users/{id}/products` | Get user's products | Authenticated |
| PUT | `/api/users/{id}/role` | Update user role | Admin only |

### Products
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| GET | `/api/products` | Get all products | Public |
| POST | `/api/products` | Create product | Authenticated |
| GET | `/api/products/{id}` | Get product by ID | Authenticated |
| PUT | `/api/products/{id}` | Update product | Owner or Admin |
| DELETE | `/api/products/{id}` | Delete product | Owner or Admin |

## 📝 Request Body Examples

### Login
```json
{
  "email": "momo@gmail.com",
  "password": "casca"
}
```

### Register User
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

### Create Product
```json
{
  "name": "Dragon's killer",
  "description": "The sword of Guts",
  "price": 100
}
```

### Update User
```json
{
  "name": "Guts",
  "email": "momo@gmail.com",
  "password": "casca"
}
```

### Update User Role (Admin only)
```json
{
  "role": "ADMIN"
}
```

## 🔒 Security Features

- **JWT Token Authentication**: Stateless authentication with configurable expiration
- **Password Encryption**: BCrypt password hashing
- **Role-Based Access Control**: ADMIN and USER roles with method-level security
- **CORS Configuration**: Configurable cross-origin resource sharing
- **Input Validation**: Comprehensive request validation with custom error responses
- **Route Protection**: Custom filters to handle unknown endpoints
- **SSL/HTTPS**: Secure communication with SSL certificates

## 🏗️ Project Structure

```
src/main/java/sn/zone/bakcup_api/
├── configs/                 # Configuration classes
│   ├── SecurityConfig.java  # Security configuration
│   ├── GlobalExceptionHandler.java
│   └── ...
├── data/
│   ├── entities/           # MongoDB entities
│   ├── enums/              # Enums (Role)
│   └── repositories/       # Data repositories
├── security/               # JWT utilities and filters
├── services/               # Business logic layer
├── web/
│   ├── controllers/        # REST controllers
│   └── dto/               # Data Transfer Objects
└── BakcupApiApplication.java
```

## ⚙️ Configuration

Key configuration properties in `application.properties`:

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb+srv://...
spring.data.mongodb.database=z01

# SSL Configuration
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.jks
server.ssl.key-store-password=qwerty

# Security Logging
logging.level.org.springframework.security=DEBUG
```

## 🧪 Testing

Run tests with:
```bash
./mvnw test
```

---

**Note**: This API uses HTTPS by default. Make sure to use `https://` in your requests when running locally.