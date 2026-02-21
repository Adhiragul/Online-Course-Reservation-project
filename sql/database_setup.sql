-- ============================================================
--  Online Course Reservation System — Database Setup Script
-- ============================================================
--  Run this script in MySQL to create the database and tables.
--  Command:  mysql -u root -p < database_setup.sql
-- ============================================================

-- Create database
CREATE DATABASE IF NOT EXISTS course_reservation_db;
USE course_reservation_db;

-- ─────────────────────────────────────────────────
--  Students Table
-- ─────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS students (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)        NOT NULL,
    email       VARCHAR(150)        NOT NULL UNIQUE,
    password    VARCHAR(255)        NOT NULL,
    created_at  TIMESTAMP           DEFAULT CURRENT_TIMESTAMP
);

-- ─────────────────────────────────────────────────
--  Courses Table
-- ─────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS courses (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(200)    NOT NULL,
    instructor      VARCHAR(100)    NOT NULL,
    capacity        INT             NOT NULL DEFAULT 30,
    enrolled_count  INT             NOT NULL DEFAULT 0
);

-- ─────────────────────────────────────────────────
--  Reservations Table
-- ─────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS reservations (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    student_id        INT         NOT NULL,
    course_id         INT         NOT NULL,
    reservation_date  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id)  REFERENCES courses(id)  ON DELETE CASCADE,
    UNIQUE KEY unique_reservation (student_id, course_id)
);

-- ─────────────────────────────────────────────────
--  Seed Data — Sample Courses
-- ─────────────────────────────────────────────────
INSERT INTO courses (title, instructor, capacity, enrolled_count) VALUES
    ('Introduction to Java',           'Dr. Iron Man', 40, 12),
    ('Data Structures & Algorithms',   'Dr. Johnwick', 35, 20),
    ('Web Development with Spring',    'Ms. Black widow', 30,  5),
    ('Database Management Systems',    'Dr. Captain America', 25, 10),
    ('Machine Learning Fundamentals',  'Prof. Andrew Tate', 50, 45),
    ('Cloud Computing with AWS',       'Mr. Jeff Buzzoff', 30,  8);

SELECT 'Database setup complete! 6 sample courses inserted.' AS Status;

