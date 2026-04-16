package dbms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Student - Handles all database operations for student management
 * Includes CRUD operations, search, print, and bulk upload functionality
 */
public class Student {
    
    private Connection connection;
    private Scanner scanner;
    
    public Student() {
        this.connection = DBConnection.getConnection();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Add a new student to the database
     */
    public void addStudent() {
        try {
            System.out.println("\n--- Add New Student ---");
            
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Roll Number: ");
            String rollno = scanner.nextLine();
            
            System.out.print("Enter Department: ");
            String dept = scanner.nextLine();
            
            System.out.print("Enter Section: ");
            String section = scanner.nextLine();
            
            System.out.print("Enter Year: ");
            int year = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter College: ");
            String college = scanner.nextLine();
            
            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();
            
            System.out.print("Enter Place: ");
            String place = scanner.nextLine();
            
            System.out.print("Enter CGPA: ");
            float cgpa = Float.parseFloat(scanner.nextLine());
            
            String sql = "INSERT INTO students (name, rollno, dept, section, year, college, phone, place, cgpa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, rollno);
                pstmt.setString(3, dept);
                pstmt.setString(4, section);
                pstmt.setInt(5, year);
                pstmt.setString(6, college);
                pstmt.setString(7, phone);
                pstmt.setString(8, place);
                pstmt.setFloat(9, cgpa);
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student added successfully!");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Error: Roll number already exists!");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format!");
        }
    }
    
    /**
     * View all students in the database
     */
    public void viewAllStudents() {
        try {
            String sql = "SELECT * FROM students";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                System.out.println("\n--- All Students ---");
                displayResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error viewing students: " + e.getMessage());
        }
    }
    
    /**
     * Update student information based on roll number
     */
    public void updateStudent() {
        try {
            System.out.println("\n--- Update Student ---");
            System.out.print("Enter Roll Number to update: ");
            String rollno = scanner.nextLine();
            
            // Check if student exists
            String checkSql = "SELECT * FROM students WHERE rollno = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                checkStmt.setString(1, rollno);
                ResultSet rs = checkStmt.executeQuery();
                
                if (!rs.next()) {
                    System.out.println("Student with roll number " + rollno + " not found!");
                    return;
                }
            }
            
            System.out.println("\nEnter new details (press Enter to keep existing value):");
            
            System.out.print("New Name: ");
            String name = scanner.nextLine();
            
            System.out.print("New Department: ");
            String dept = scanner.nextLine();
            
            System.out.print("New Section: ");
            String section = scanner.nextLine();
            
            System.out.print("New Year: ");
            String yearStr = scanner.nextLine();
            
            System.out.print("New College: ");
            String college = scanner.nextLine();
            
            System.out.print("New Phone: ");
            String phone = scanner.nextLine();
            
            System.out.print("New Place: ");
            String place = scanner.nextLine();
            
            System.out.print("New CGPA: ");
            String cgpaStr = scanner.nextLine();
            
            // Build dynamic update query
            List<String> updates = new ArrayList<>();
            List<Object> values = new ArrayList<>();
            
            if (!name.isEmpty()) { updates.add("name = ?"); values.add(name); }
            if (!dept.isEmpty()) { updates.add("dept = ?"); values.add(dept); }
            if (!section.isEmpty()) { updates.add("section = ?"); values.add(section); }
            if (!yearStr.isEmpty()) { updates.add("year = ?"); values.add(Integer.parseInt(yearStr)); }
            if (!college.isEmpty()) { updates.add("college = ?"); values.add(college); }
            if (!phone.isEmpty()) { updates.add("phone = ?"); values.add(phone); }
            if (!place.isEmpty()) { updates.add("place = ?"); values.add(place); }
            if (!cgpaStr.isEmpty()) { updates.add("cgpa = ?"); values.add(Float.parseFloat(cgpaStr)); }
            
            if (updates.isEmpty()) {
                System.out.println("No changes made.");
                return;
            }
            
            String sql = "UPDATE students SET " + String.join(", ", updates) + " WHERE rollno = ?";
            
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                for (int i = 0; i < values.size(); i++) {
                    pstmt.setObject(i + 1, values.get(i));
                }
                pstmt.setString(values.size() + 1, rollno);
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student updated successfully!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format!");
        }
    }
    
    /**
     * Delete student by roll number
     */
    public void deleteStudent() {
        try {
            System.out.println("\n--- Delete Student ---");
            System.out.print("Enter Roll Number to delete: ");
            String rollno = scanner.nextLine();
            
            String sql = "DELETE FROM students WHERE rollno = ?";
            
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, rollno);
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Student with roll number " + rollno + " not found!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }
    
    /**
     * Search students with various filters
     */
    public void searchStudent() {
        try {
            System.out.println("\n--- Search Student ---");
            System.out.println("1. Search by Roll Number");
            System.out.println("2. Search by Department");
            System.out.println("3. Search by Year");
            System.out.println("4. Search by CGPA Range");
            System.out.print("Enter choice: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            String sql = "";
            PreparedStatement pstmt = null;
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    String rollno = scanner.nextLine();
                    sql = "SELECT * FROM students WHERE rollno = ?";
                    pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, rollno);
                    break;
                    
                case 2:
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();
                    sql = "SELECT * FROM students WHERE dept LIKE ?";
                    pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, "%" + dept + "%");
                    break;
                    
                case 3:
                    System.out.print("Enter Year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    sql = "SELECT * FROM students WHERE year = ?";
                    pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(1, year);
                    break;
                    
                case 4:
                    System.out.print("Enter Minimum CGPA: ");
                    float minCgpa = Float.parseFloat(scanner.nextLine());
                    System.out.print("Enter Maximum CGPA: ");
                    float maxCgpa = Float.parseFloat(scanner.nextLine());
                    sql = "SELECT * FROM students WHERE cgpa BETWEEN ? AND ?";
                    pstmt = connection.prepareStatement(sql);
                    pstmt.setFloat(1, minCgpa);
                    pstmt.setFloat(2, maxCgpa);
                    break;
                    
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
            
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\n--- Search Results ---");
            displayResultSet(rs);
            pstmt.close();
            
        } catch (SQLException e) {
            System.err.println("Error searching student: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format!");
        }
    }
    
    /**
     * Print all students in formatted output
     */
    public void printStudents() {
        try {
            String sql = "SELECT * FROM students ORDER BY rollno";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                System.out.println("\n" + "=".repeat(120));
                System.out.println("STUDENT DATABASE REPORT");
                System.out.println("=".repeat(120));
                System.out.printf("%-5s %-20s %-15s %-15s %-10s %-6s %-20s %-15s %-15s %-8s%n",
                    "ID", "Name", "Roll No", "Department", "Section", "Year", "College", "Phone", "Place", "CGPA");
                System.out.println("-".repeat(120));
                
                while (rs.next()) {
                    System.out.printf("%-5d %-20s %-15s %-15s %-10s %-6d %-20s %-15s %-15s %-8.2f%n",
                        rs.getInt("id"),
                        truncate(rs.getString("name"), 20),
                        rs.getString("rollno"),
                        truncate(rs.getString("dept"), 15),
                        rs.getString("section"),
                        rs.getInt("year"),
                        truncate(rs.getString("college"), 20),
                        rs.getString("phone"),
                        truncate(rs.getString("place"), 15),
                        rs.getFloat("cgpa"));
                }
                System.out.println("=".repeat(120));
            }
        } catch (SQLException e) {
            System.err.println("Error printing students: " + e.getMessage());
        }
    }
    
    /**
     * Delete all records from database (Admin feature)
     */
    public void deleteDatabase() {
        try {
            System.out.println("\n--- WARNING: Delete Database ---");
            System.out.print("Are you sure you want to delete ALL records? (yes/no): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("yes")) {
                String sql = "TRUNCATE TABLE students";
                try (Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate(sql);
                    System.out.println("All records deleted successfully!");
                }
            } else {
                System.out.println("Operation cancelled.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting database: " + e.getMessage());
        }
    }
    
    /**
     * Bulk upload students from file
     */
    public void bulkUploadFromFile() {
        System.out.println("\n--- Bulk Upload from File ---");
        System.out.print("Enter file path (CSV format): ");
        String filePath = scanner.nextLine();
        
        FileHandler fileHandler = new FileHandler();
        List<String[]> records = fileHandler.readCSVFile(filePath);
        
        if (records.isEmpty()) {
            System.out.println("No records found or file error!");
            return;
        }
        
        int successCount = 0;
        int failCount = 0;
        
        String sql = "INSERT INTO students (name, rollno, dept, section, year, college, phone, place, cgpa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (String[] record : records) {
                if (record.length < 9) {
                    System.out.println("Skipping invalid record: " + String.join(",", record));
                    failCount++;
                    continue;
                }
                
                try {
                    pstmt.setString(1, record[0]); // name
                    pstmt.setString(2, record[1]); // rollno
                    pstmt.setString(3, record[2]); // dept
                    pstmt.setString(4, record[3]); // section
                    pstmt.setInt(5, Integer.parseInt(record[4])); // year
                    pstmt.setString(6, record[5]); // college
                    pstmt.setString(7, record[6]); // phone
                    pstmt.setString(8, record[7]); // place
                    pstmt.setFloat(9, Float.parseFloat(record[8])); // cgpa
                    
                    pstmt.executeUpdate();
                    successCount++;
                } catch (Exception e) {
                    System.out.println("Error inserting record: " + String.join(",", record) + " - " + e.getMessage());
                    failCount++;
                }
            }
            
            System.out.println("\nBulk upload completed!");
            System.out.println("Successfully inserted: " + successCount);
            System.out.println("Failed: " + failCount);
            
        } catch (SQLException e) {
            System.err.println("Database error during bulk upload: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to display ResultSet in formatted way
     */
    private void displayResultSet(ResultSet rs) throws SQLException {
        boolean hasData = false;
        
        while (rs.next()) {
            hasData = true;
            System.out.println("\n----------------------------------------");
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Roll No: " + rs.getString("rollno"));
            System.out.println("Department: " + rs.getString("dept"));
            System.out.println("Section: " + rs.getString("section"));
            System.out.println("Year: " + rs.getInt("year"));
            System.out.println("College: " + rs.getString("college"));
            System.out.println("Phone: " + rs.getString("phone"));
            System.out.println("Place: " + rs.getString("place"));
            System.out.println("CGPA: " + rs.getFloat("cgpa"));
        }
        
        if (!hasData) {
            System.out.println("No records found.");
        }
    }
    
    /**
     * Helper method to truncate long strings
     */
    private String truncate(String str, int length) {
        if (str == null) return "";
        return str.length() > length ? str.substring(0, length - 3) + "..." : str;
    }
}
