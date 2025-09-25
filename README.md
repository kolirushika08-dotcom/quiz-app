# Full-stack Quiz Application
Author: Rushika Koli

This repository contains a full-stack quiz application scaffold:
- **Frontend:** React
- **Backend:** Spring Boot (Java, Maven)
- **Database:** MySQL (schema + sample data)

The project includes:
- Source code for frontend and backend
- Database schema and sample data (`db/schema.sql`)
- Instructions to run locally

## Quick Overview

### Backend
Location: `backend/`
- Spring Boot application using Spring Data JPA and Spring Web.
- Entities: User, Question, Option, QuizResult
- REST APIs for quiz operations and admin management.
- Configure DB connection in `backend/src/main/resources/application.properties`.

### Frontend
Location: `frontend/`
- React app (create-react-app style) with components:
  - `Quiz` — take quizzes
  - `Result` — view results
  - `Admin` — add/manage questions (basic UI; expects backend endpoints)

### Database
Location: `db/schema.sql`
- SQL script to create tables and sample questions/options.

## Local Setup (development)

### Prerequisites
- Java 17+ and Maven
- Node.js 16+
- MySQL server
- Git

### Backend
1. Create a MySQL database (example name: `quizdb`) and run `db/schema.sql`.
2. Edit `backend/src/main/resources/application.properties` to set MySQL URL, username, and password.
3. Build and run:
```bash
cd backend
mvn clean package
mvn spring-boot:run
```
The backend runs on port 8080 by default.

### Frontend
1. Install dependencies and run:
```bash
cd frontend
npm install
npm start
```
The frontend runs on port 3000 and proxies API calls to `http://localhost:8080` (see `package.json` proxy).

## Notes
- Security (auth) in this scaffold is minimal — you should add JWT or session-based auth for production.
- The admin panel UI is basic; backend endpoints are ready to be extended.
- This is a scaffold intended to be extended into a production-ready project.

Enjoy building! — Rushika Koli
