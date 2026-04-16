-- Student DBMS - Database Schema
-- Run this script to create the students table

CREATE DATABASE IF NOT EXISTS student_dbms;
USE student_dbms;

DROP TABLE IF EXISTS students;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rollno VARCHAR(20) NOT NULL UNIQUE,
    dept VARCHAR(50) NOT NULL,
    section VARCHAR(10) NOT NULL,
    year INT NOT NULL,
    college VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    place VARCHAR(50) NOT NULL,
    cgpa FLOAT NOT NULL
);

-- Sample data for testing
INSERT INTO students (name, rollno, dept, section, year, college, phone, place, cgpa) VALUES
('John Doe', 'R001', 'Computer Science', 'A', 3, 'MIT', '9876543210', 'New York', 8.5),
('Jane Smith', 'R002', 'Electronics', 'B', 2, 'Stanford', '9876543211', 'California', 9.0),
('Bob Johnson', 'R003', 'Mechanical', 'A', 4, 'Harvard', '9876543212', 'Boston', 7.8),
('Alice Williams', 'R004', 'Computer Science', 'A', 3, 'MIT', '9876543213', 'New York', 8.9),
('Charlie Brown', 'R005', 'Electrical', 'C', 2, 'Stanford', '9876543214', 'California', 8.2);
