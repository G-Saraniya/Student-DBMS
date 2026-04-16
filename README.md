# Student DBMS

Java-Based Student Database Management System with MySQL backend and HTML frontend.

## Features

### Student Management
- Add new students with complete details
- View all student records
- Update student information by roll number
- Delete students by roll number

### Search & Filter
- Search by Roll Number
- Search by Department
- Filter by Year
- Search by CGPA range (min-max)

### Data Operations
- Bulk upload from CSV files
- Print formatted student reports
- Delete entire database (Admin feature)

### Web Interface
- Interactive HTML frontend
- Tab-based navigation
- Statistics dashboard
- Export to CSV
- Local storage for demo data

## Architecture

| Layer | Files |
|-------|-------|
| **Model** | `Student.java` (contains DB operations) |
| **Controller** | `Main.java` (switch-case menu) |
| **Database** | `DBConnection.java`, `FileHandler.java` |
| **View (Web)** | `web/index.html` |

## Technologies
- Java (JDK 17)
- MySQL
- JDBC
- HTML/CSS/JavaScript
- Eclipse IDE

## Database Schema

### Table: students
| Field | Type | Description |
|-------|------|-------------|
| id | INT AUTO_INCREMENT | Primary Key |
| name | VARCHAR(100) | Student name |
| rollno | VARCHAR(20) UNIQUE | Roll number |
| dept | VARCHAR(50) | Department |
| section | VARCHAR(10) | Section (A/B/C/D) |
| year | INT | Year (1-4) |
| college | VARCHAR(100) | College name |
| phone | VARCHAR(15) | Phone number |
| place | VARCHAR(50) | City/Location |
| cgpa | FLOAT | CGPA (0-10) |

## Setup Instructions

1. **Create Database**
   ```sql
   -- Run the SQL script in MySQL
   mysql -u root -p < sql/create_table.sql
   ```

2. **Configure Database Connection**
   - Open `src/dbms/DBConnection.java`
   - Update line 16 with your MySQL password:
   ```java
   private static final String PASSWORD = "your_password";
   ```

3. **Import to Eclipse**
   - File → Import → Existing Projects into Workspace
   - Select the Student DBMS folder
   - Add MySQL Connector JAR to build path

4. **Run Application**
   - Run `dbms.Main`
   - Or open `web/index.html` in browser for frontend demo

## Menu Options

| Option | Feature |
|--------|---------|
| 1 | Add Student |
| 2 | View Students |
| 3 | Update Student |
| 4 | Delete Student |
| 5 | Search Student |
| 6 | Print Students |
| 7 | Delete Database (Admin) |
| 8 | Bulk Upload |
| 9 | Exit |

## Project Structure

```
Student DBMS/
├── src/dbms/
│   ├── Main.java                 # Entry point with menu
│   ├── DBConnection.java         # Database connection
│   ├── Student.java              # Model & CRUD operations
│   └── FileHandler.java          # CSV file handling
├── sql/
│   └── create_table.sql          # Database schema
├── data/
│   └── sample_students.csv       # Sample data for bulk upload
├── web/
│   └── index.html                # HTML frontend demo
├── .project                      # Eclipse project
├── .classpath                    # Eclipse classpath
└── README.md                     # This file
```

## Sample CSV Format
```csv
name,rollno,dept,section,year,college,phone,place,cgpa
John Doe,R101,Computer Science,A,3,MIT,9876543210,Boston,8.5
Jane Smith,R102,Electronics,B,2,Stanford,9876543211,California,9.0
```

## Author
G-Saraniya
