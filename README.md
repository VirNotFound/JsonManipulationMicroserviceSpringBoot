## JSON Processing Spring Boot Service

A simple Spring Boot application demonstrating dynamic JSON handling using Jackson's `JsonNode`. This project includes endpoints to process and transform incoming JSON payloads in various ways, showcasing basic to advanced use cases.

---

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- Spring Boot 2.7+
- (Optional) IDE: IntelliJ IDEA, VS Code, or Spring Tool Suite

---

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/virnotfound/JsonManipulationMicroserviceSpringBoot.git
   cd JsonManipulationMicroserviceSpringBoot
   ```
2. **Build the project**
   ```bash
   mvn clean package
   ```
3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   The service starts at `http://localhost:8080/`.

---

### API Endpoints

| Path              | Method | Description                                   |
|-------------------|--------|-----------------------------------------------|
| `/json/simpleInO`    | POST   | Echoes name & age with a custom message       |
| `/json/complexOutput`| POST   | Constructs `status` + nested hobbies array    |
| `/json/complexInput` | POST   | Flattens nested user/transaction data + array |

#### 1. `POST /json/simpleInO`
- **Request Body**
  ```json
  {
    "name": "John",
    "age": 20
  }
  ```
- **Response**
  ```json
  {
    "name": "John",
    "age": 20,
    "message": "You are John, and you are 20 years old."
  }
  ```

#### 2. `POST /json/complexOutput`
- **Request Body**
  ```json
  {
    "name": "john",
    "age": 30,
    "hobby1": "Riding Bike",
    "hobby2": "Reading",
    "hobby3": "Playing Guitar"
  }
  ```
- **Response**
  ```json
  {
    "status": "success",
    "data": {
      "name": "john",
      "age": 30,
      "hobbies": [
        "Riding Bike",
        "Reading",
        "Playing Guitar"
      ]
    }
  }
  ```

#### 3. `POST /json/complexInput`
- **Request Body**
  ```json
  {
    "user": {
      "name": "Jane Doe",
      "contact": {
        "email": "jane.doe@example.com",
        "phone": "123-456-7890"
      },
      "activities": ["Logging", "Development", "Finance"]
    },
    "transaction": {
      "id": "abc123",
      "amount": 250.75
    }
  }
  ```
- **Response**
  ```json
  {
    "fullName": "Jane Doe",
    "email": "jane.doe@example.com",
    "transactionId": "abc123",
    "amount": 250.75,
    "activities": [
      "Logging",
      "Development",
      "Finance"
    ]
  }
  ```

---

### Error Handling

- Returns **400 Bad Request** for malformed JSON or missing required fields.
- Returns **500 Internal Server Error** with an `{ "error": "..." }` payload for unexpected exceptions.

---

*Created as a learning exercise for handling JSON dynamically in Spring Boot.*

