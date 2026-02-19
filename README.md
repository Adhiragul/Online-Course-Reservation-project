# Online Course Reservation System

A console-based Java application that allows students to register, browse courses, reserve seats, and manage their enrollments.

## Features

- **Student Registration & Login** — Create an account and log in with email/password
- **Browse Courses** — View all available courses with seat availability
- **Reserve a Course** — Book a seat with automatic duplicate and capacity checks
- **My Reservations** — View all your active reservations
- **Cancel Reservation** — Cancel a booking and free up the seat

## Tech Stack

| Component    | Technology            |
|-------------|----------------------|
| Language     | Java 11+             |
| Database     | MySQL 8.0+           |
| Connectivity | JDBC (mysql-connector-j) |
| Architecture | Model → DAO → Service → Console UI |

## Project Structure

```
online/
├── src/
│   ├── model/          ← POJOs (Course, Student, Reservation)
│   ├── dao/            ← Data Access Objects + DB connection
│   ├── service/        ← Business logic layer
│   └── app/            ← Main.java (entry point)
├── sql/
│   └── database_setup.sql
├── lib/                ← Place JDBC driver jar here
└── README.md
```

## Setup Instructions

### 1. Prerequisites

- **Java JDK 11** or higher — [Download](https://adoptium.net/)
- **MySQL 8.0** or higher — [Download](https://dev.mysql.com/downloads/)

### 2. Download MySQL JDBC Driver

1. Go to [MySQL Connector/J Downloads](https://dev.mysql.com/downloads/connector/j/)
2. Download the **Platform Independent** ZIP/TAR
3. Extract and copy `mysql-connector-j-X.X.X.jar` into the `lib/` folder

### 3. Setup Database

```bash
mysql -u root -p < sql/database_setup.sql
```

This will create the `course_reservation_db` database with `students`, `courses`, and `reservations` tables, plus 6 sample courses.

### 4. Configure Database Credentials

Open `src/dao/DBConnection.java` and update these constants if needed:

```java
private static final String URL  = "jdbc:mysql://localhost:3306/course_reservation_db";
private static final String USER = "root";
private static final String PASS = "root";   // ← change to your MySQL password
```

### 5. Compile

```bash
javac -cp "lib/*" -d out src/model/*.java src/dao/*.java src/service/*.java src/app/*.java
```

### 6. Run

```bash
java -cp "out;lib/*" app.Main
```

> **Note:** On Linux/macOS, use `:` instead of `;` as the classpath separator:
> ```bash
> java -cp "out:lib/*" app.Main
> ```

## Usage

1. **Register** a new student account
2. **Login** with your email and password
3. **View Courses** to see available courses and seats
4. **Reserve** a course by entering its ID
5. **View / Cancel** your reservations from the dashboard

---

*Built with ❤️ in Java*
