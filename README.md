# Java Database Management Systems

This repository contains two complete Java-based database management systems with MySQL backend.

---

## 📚 1. Student DBMS

Java-Based Student Database Management System.

## Features
- Add, View, Update, Delete student records
- Search by Roll No, Department, Year, CGPA range
- Bulk upload from CSV
- Statistics dashboard
- HTML frontend demo

## Technologies
- Java (JDK 17)
- MySQL
- JDBC
- HTML/CSS/JavaScript

## Setup
1. Import as Eclipse project
2. Run `sql/create_table.sql` in MySQL
3. Update DB credentials in `DBConnection.java`
4. Run `Main.java`

## 📋 2. Bus Reservation System

Console-based Bus Reservation System with MVC architecture.

### Features
- View and search available buses
- Add new buses (Admin)
- Register passengers
- Make seat reservations
- Cancel reservations
- Payment status tracking
- Generate reports

### Project Structure
```
BusReservationSystem/
├── src/bus/
│   ├── Main.java              # Entry point with menu
│   ├── DBConnection.java      # Database connection
│   ├── ReservationController.java  # MVC Controller
│   ├── Bus.java               # Model: Bus entity
│   ├── Passenger.java         # Model: Passenger entity
│   └── Reservation.java       # Model: Reservation entity
└── sql/
    └── bus_reservation_schema.sql  # Database setup
```

### Setup Bus System
1. Run `BusReservationSystem/sql/bus_reservation_schema.sql` in MySQL
2. Update DB credentials in `BusReservationSystem/src/bus/DBConnection.java`
3. Run `bus.Main` from the BusReservationSystem folder

### Bus System Menu
1. View All Buses
2. Search Buses (by route/date)
3. Add New Bus
4. Register Passenger
5. View Passengers
6. Make Reservation
7. View Reservations
8. Cancel Reservation
9. Update Payment Status
10. Generate Report

---

## 🛠 Technologies Used
- Java (JDK 17)
- MySQL
- JDBC
- HTML/CSS/JavaScript (for Student DBMS frontend)

## 👤 Author
G-Saraniya
