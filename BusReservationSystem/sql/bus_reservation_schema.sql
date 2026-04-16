-- Bus Reservation System - Database Schema
-- Run this script to create the database and tables

CREATE DATABASE IF NOT EXISTS bus_reservation;
USE bus_reservation;

-- Buses table
CREATE TABLE buses (
    bus_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_number VARCHAR(20) NOT NULL UNIQUE,
    bus_name VARCHAR(100) NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    fare DECIMAL(10, 2) NOT NULL,
    bus_type VARCHAR(50) DEFAULT 'Standard',
    travel_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'Active'
);

-- Passengers table
CREATE TABLE passengers (
    passenger_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    age INT,
    gender VARCHAR(10),
    address VARCHAR(200)
);

-- Reservations table
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_id INT NOT NULL,
    passenger_id INT NOT NULL,
    seat_numbers VARCHAR(100) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    travel_date DATE NOT NULL,
    total_fare DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'Confirmed',
    payment_status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (bus_id) REFERENCES buses(bus_id),
    FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id)
);

-- Sample data for buses
INSERT INTO buses (bus_number, bus_name, source, destination, departure_time, arrival_time, total_seats, available_seats, fare, bus_type, travel_date) VALUES
('BUS001', 'City Express', 'New York', 'Boston', '08:00:00', '12:00:00', 40, 40, 45.00, 'AC Sleeper', '2026-04-20'),
('BUS002', 'Metro Link', 'Boston', 'New York', '14:00:00', '18:00:00', 40, 40, 45.00, 'AC Sleeper', '2026-04-20'),
('BUS003', 'Royal Travels', 'Chicago', 'Detroit', '09:30:00', '15:30:00', 35, 35, 55.00, 'Luxury', '2026-04-21'),
('BUS004', 'Swift Ride', 'Los Angeles', 'San Francisco', '07:00:00', '13:00:00', 50, 50, 65.00, 'Super Luxury', '2026-04-21'),
('BUS005', 'Comfort Line', 'Miami', 'Orlando', '10:00:00', '14:00:00', 45, 45, 35.00, 'Standard', '2026-04-22');

-- Sample data for passengers
INSERT INTO passengers (name, email, phone, age, gender, address) VALUES
('John Smith', 'john.smith@email.com', '9876543210', 32, 'Male', '123 Main St, New York'),
('Sarah Johnson', 'sarah.j@email.com', '9876543211', 28, 'Female', '456 Oak Ave, Boston'),
('Michael Brown', 'michael.b@email.com', '9876543212', 45, 'Male', '789 Pine Rd, Chicago'),
('Emily Davis', 'emily.d@email.com', '9876543213', 25, 'Female', '321 Elm St, Miami');

-- Sample reservations
INSERT INTO reservations (bus_id, passenger_id, seat_numbers, travel_date, total_fare, status, payment_status) VALUES
(1, 1, '5,6', '2026-04-20', 90.00, 'Confirmed', 'Paid'),
(2, 2, '12', '2026-04-20', 45.00, 'Confirmed', 'Paid'),
(3, 3, '8,9,10', '2026-04-21', 165.00, 'Confirmed', 'Pending');
