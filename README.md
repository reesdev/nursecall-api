# Nurse Call System API

A simple **Spring Boot REST API** that simulates a hospital nurse call workflow.
Patients can request assistance, nurses can acknowledge the call, handle the request, escalate it if needed, and finally resolve the call.

This project demonstrates **backend workflow handling, status management, and exception handling** using Spring Boot and MySQL.

---

# Features

• Create nurse call request
• Acknowledge nurse call
• Start handling patient request
• Escalate call if needed
• Resolve nurse call
• Filter calls by status
• Structured error handling

---

# Workflow

The system uses a workflow-based status model:

```
CALLING
↓
ACKNOWLEDGED
↓
IN_PROGRESS
↓
ESCALATED (optional)
↓
RESOLVED
```

Example scenario:

Patient presses call button → Nurse acknowledges → Nurse handles request → Call resolved.

---

# Tech Stack

Java
Spring Boot
Spring Data JPA
MySQL
Lombok
Maven

---

# API Endpoints

### Create Nurse Call

POST `/calls`

Example Request

```
{
  "roomNumber": "101",
  "patientName": "Budi"
}
```

---

### Get All Calls

GET `/calls`

---

### Acknowledge Call

PUT `/calls/{id}/acknowledge?nurseName=Siti`

---

### Start Handling

PUT `/calls/{id}/start`

---

### Escalate Call

PUT `/calls/{id}/escalate`

---

### Resolve Call

PUT `/calls/{id}/resolve`

---

### Get Calls By Status

GET `/calls/status/{status}`

Example:

```
/calls/status/CALLING
/calls/status/IN_PROGRESS
/calls/status/RESOLVED
```

---

# Database Setup (Local)

This project uses **MySQL running locally**.

Create the database first:

```
CREATE DATABASE nursecall;
```

Update the database configuration in `application.properties` if needed:

```
spring.datasource.url=jdbc:mysql://localhost:3306/nursecall
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# Run the Project

Clone the repository

```
git clone <your-repository-url>
```

Go to the project directory

```
cd nursecall
```

Run Spring Boot

```
./mvnw spring-boot:run
```

The API will run on:

```
http://localhost:8080
```

---

# Example Testing Flow (Postman)

1 Create call

```
POST /calls
```

2 Nurse acknowledge call

```
PUT /calls/1/acknowledge?nurseName=Siti
```

3 Start handling

```
PUT /calls/1/start
```

4 Resolve call

```
PUT /calls/1/resolve
```

---

# Project Structure

```
controller
service
repository
entity
dto
exception
enums
```

---

# Author

Backend learning project built with Spring Boot.
