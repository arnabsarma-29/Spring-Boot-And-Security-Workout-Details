# Workout Management Application

A full-stack Spring Boot web application for managing workouts and fitness goals with role-based authentication and OAuth2 login support.

---

## Features

- User registration and login
- Role-based authentication (ADMIN / USER)
- OAuth2 login with GitHub
- Create and manage workouts (Admin only)
- Create and assign goals to users
- View personal workouts and goals (User-specific)
- Admin dashboard
- Session-based authentication
- Logout functionality

---

## How It Works

Users authenticate using form login or GitHub OAuth. Spring Security manages sessions and access control.

**Admin users can:**
- Manage users
- Create workouts
- Assign goals

**Normal users can:**
- View assigned workouts
- Track assigned goals

Role-based access ensures security.

---

## Tech Stack

- **Backend:** Java, Spring Boot, Spring MVC, Spring Security
- **Frontend:** JSP, JSTL
- **Build Tool:** Maven
- **Database:** PostgreSQL
- **Auth:** OAuth2 (GitHub)

---

## Project Structure

```
src/main/java/com/project2/workout_app/
    config/
    controller/
    service/
    repository/
    dao/
    entity/
    model/
    dto/
    exception/

Main Classes:
    WorkoutAppApplication.java
    ServletInitializer.java
```

---

## Authentication Endpoints

```
GET     /login                          - Show login page
POST    /perform_login                  - Login user
GET     /register                       - Show registration page
POST    /users/register                 - Register user
GET     /logout                         - Logout user
```

---

## OAuth2 Login

```
GET     /oauth2/authorization/github    - Login with GitHub
```

---

## User & Admin Endpoints

```
GET     /admin/dashboard                - Admin dashboard
GET     /user/dashboard                 - User dashboard
```

---

## User Management (Admin Only)

```
GET     /users/getWorkoutUsers          - List all users
POST    /users/updateUsername           - Update username
POST    /users/updatePassword           - Update password
POST    /users/deleteWorkoutUser        - Delete user
```

---

## Workout Endpoints

```
GET     /workout/getWorkouts            - Show all workouts
GET     /workout/saveWorkout            - Show workout form
POST    /workout/saveWorkout            - Save new workout
POST    /workout/deleteWorkout          - Delete workout
```

---

## Goal Endpoints

```
GET     /goals/getGoals                 - Show all goals
GET     /goals/saveGoal                 - Show goal form
POST    /goals/saveGoal                 - Save new goal
POST    /goals/deleteGoal               - Delete goal
```

---

## Installation

```bash
git clone https://github.com/arnabsarma-29/workout-app.git
cd workout-app
mvn clean install
```

---

## Run Application

```bash
mvn spring-boot:run
```

Open in browser:
[http://localhost:8080/](http://localhost:8080/)

---

## Key Concepts

- MVC architecture
- Role-based authorization
- Spring Security
- OAuth2 authentication
- DTO pattern
- Exception handling
- Layered architecture

---

## Notes

- Configure PostgreSQL in `application.properties`
- Admin role required for management features
- OAuth users are created automatically on first login
- CSRF protection is enabled
- Session required for all protected routes

---

## Author

[https://github.com/arnabsarma-29](https://github.com/arnabsarma-29)
