package bus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ReservationController - Handles bus operations, reservations, and passenger management
 * MVC Controller Layer
 */
public class ReservationController {
    
    private Connection connection;
    private Scanner scanner;
    
    public ReservationController() {
        this.connection = DBConnection.getConnection();
        this.scanner = new Scanner(System.in);
    }
    
    // ==================== BUS OPERATIONS ====================
    
    public void viewAllBuses() {
        try {
            String sql = "SELECT * FROM buses WHERE status = 'Active' ORDER BY travel_date, departure_time";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                System.out.println("\n--- AVAILABLE BUSES ---");
                System.out.printf("%-4s | %-8s | %-15s | %-12s → %-12s | %-8s | %-5s | %-6s | %-8s%n",
                        "ID", "Bus No", "Name", "From", "To", "Time", "Seats", "Fare", "Type");
                System.out.println("-".repeat(100));
                
                while (rs.next()) {
                    System.out.printf("%-4d | %-8s | %-15s | %-12s → %-12s | %s | %d/%d | $%5.2f | %-8s%n",
                            rs.getInt("bus_id"),
                            rs.getString("bus_number"),
                            truncate(rs.getString("bus_name"), 15),
                            truncate(rs.getString("source"), 12),
                            truncate(rs.getString("destination"), 12),
                            rs.getString("departure_time").substring(0, 5),
                            rs.getInt("available_seats"),
                            rs.getInt("total_seats"),
                            rs.getDouble("fare"),
                            rs.getString("bus_type"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error viewing buses: " + e.getMessage());
        }
    }
    
    public void searchBuses() {
        try {
            System.out.println("\n--- Search Buses ---");
            System.out.print("Source City: ");
            String source = scanner.nextLine().trim();
            System.out.print("Destination City: ");
            String destination = scanner.nextLine().trim();
            System.out.print("Travel Date (YYYY-MM-DD): ");
            String date = scanner.nextLine().trim();
            
            String sql = "SELECT * FROM buses WHERE source LIKE ? AND destination LIKE ? AND travel_date = ? AND status = 'Active'";
            
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, "%" + source + "%");
                pstmt.setString(2, "%" + destination + "%");
                pstmt.setString(3, date);
                ResultSet rs = pstmt.executeQuery();
                
                System.out.println("\n--- Search Results ---");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Bus ID: " + rs.getInt("bus_id"));
                    System.out.println("Bus: " + rs.getString("bus_number") + " - " + rs.getString("bus_name"));
                    System.out.println("Route: " + rs.getString("source") + " → " + rs.getString("destination"));
                    System.out.println("Seats: " + rs.getInt("available_seats") + "/" + rs.getInt("total_seats"));
                    System.out.println("Fare: $" + rs.getDouble("fare"));
                    System.out.println("-".repeat(50));
                }
                if (!found) System.out.println("No buses found.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void addBus() {
        try {
            System.out.println("\n--- Add New Bus ---");
            System.out.print("Bus Number: ");
            String busNumber = scanner.nextLine();
            System.out.print("Bus Name: ");
            String busName = scanner.nextLine();
            System.out.print("Source: ");
            String source = scanner.nextLine();
            System.out.print("Destination: ");
            String destination = scanner.nextLine();
            System.out.print("Departure Time (HH:MM): ");
            String depTime = scanner.nextLine();
            System.out.print("Arrival Time (HH:MM): ");
            String arrTime = scanner.nextLine();
            System.out.print("Total Seats: ");
            int totalSeats = Integer.parseInt(scanner.nextLine());
            System.out.print("Fare: $");
            double fare = Double.parseDouble(scanner.nextLine());
            System.out.print("Bus Type: ");
            String busType = scanner.nextLine();
            System.out.print("Travel Date (YYYY-MM-DD): ");
            String travelDate = scanner.nextLine();
            
            String sql = "INSERT INTO buses (bus_number, bus_name, source, destination, departure_time, arrival_time, total_seats, available_seats, fare, bus_type, travel_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, busNumber);
                pstmt.setString(2, busName);
                pstmt.setString(3, source);
                pstmt.setString(4, destination);
                pstmt.setString(5, depTime + ":00");
                pstmt.setString(6, arrTime + ":00");
                pstmt.setInt(7, totalSeats);
                pstmt.setInt(8, totalSeats);
                pstmt.setDouble(9, fare);
                pstmt.setString(10, busType);
                pstmt.setString(11, travelDate);
                pstmt.executeUpdate();
                System.out.println("Bus added successfully!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    // ==================== PASSENGER OPERATIONS ====================
    
    public void addPassenger() {
        try {
            System.out.println("\n--- Register Passenger ---");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Phone: ");
            String phone = scanner.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            
            String sql = "INSERT INTO passengers (name, email, phone, age, gender, address) VALUES (?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, phone);
                pstmt.setInt(4, age);
                pstmt.setString(5, gender);
                pstmt.setString(6, address);
                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Passenger registered! ID: " + rs.getInt(1));
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void viewAllPassengers() {
        try {
            String sql = "SELECT * FROM passengers ORDER BY passenger_id";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                System.out.println("\n--- PASSENGERS ---");
                System.out.printf("%-4s | %-20s | %-25s | %-12s | %-4s | %-8s%n",
                        "ID", "Name", "Email", "Phone", "Age", "Gender");
                System.out.println("-".repeat(100));
                
                while (rs.next()) {
                    System.out.printf("%-4d | %-20s | %-25s | %-12s | %-4d | %-8s%n",
                            rs.getInt("passenger_id"),
                            truncate(rs.getString("name"), 20),
                            truncate(rs.getString("email"), 25),
                            rs.getString("phone"),
                            rs.getInt("age"),
                            rs.getString("gender"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    // ==================== RESERVATION OPERATIONS ====================
    
    public void makeReservation() {
        try {
            System.out.println("\n--- Make Reservation ---");
            viewAllBuses();
            
            System.out.print("\nBus ID: ");
            int busId = Integer.parseInt(scanner.nextLine());
            
            // Check bus
            String busSql = "SELECT * FROM buses WHERE bus_id = ? AND available_seats > 0";
            try (PreparedStatement busStmt = connection.prepareStatement(busSql)) {
                busStmt.setInt(1, busId);
                ResultSet busRs = busStmt.executeQuery();
                
                if (!busRs.next()) {
                    System.out.println("Bus not available or full!");
                    return;
                }
                
                double fare = busRs.getDouble("fare");
                int availableSeats = busRs.getInt("available_seats");
                
                // Show passengers
                viewAllPassengers();
                System.out.print("\nPassenger ID: ");
                int passengerId = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Number of seats: ");
                int numSeats = Integer.parseInt(scanner.nextLine());
                
                if (numSeats > availableSeats) {
                    System.out.println("Not enough seats available!");
                    return;
                }
                
                System.out.print("Seat numbers (comma-separated, e.g., 5,6,7): ");
                String seatNumbers = scanner.nextLine();
                
                String travelDate = busRs.getString("travel_date");
                double totalFare = fare * numSeats;
                
                // Insert reservation
                String resSql = "INSERT INTO reservations (bus_id, passenger_id, seat_numbers, travel_date, total_fare, status, payment_status) VALUES (?, ?, ?, ?, ?, 'Confirmed', 'Pending')";
                
                try (PreparedStatement resStmt = connection.prepareStatement(resSql, Statement.RETURN_GENERATED_KEYS)) {
                    resStmt.setInt(1, busId);
                    resStmt.setInt(2, passengerId);
                    resStmt.setString(3, seatNumbers);
                    resStmt.setString(4, travelDate);
                    resStmt.setDouble(5, totalFare);
                    resStmt.executeUpdate();
                    
                    // Update available seats
                    String updateSql = "UPDATE buses SET available_seats = available_seats - ? WHERE bus_id = ?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, numSeats);
                        updateStmt.setInt(2, busId);
                        updateStmt.executeUpdate();
                    }
                    
                    ResultSet rs = resStmt.getGeneratedKeys();
                    if (rs.next()) {
                        System.out.println("Reservation confirmed! ID: " + rs.getInt(1));
                        System.out.println("Total Fare: $" + totalFare);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void viewAllReservations() {
        try {
            String sql = "SELECT r.*, p.name as passenger_name, b.bus_number, b.source, b.destination " +
                        "FROM reservations r JOIN passengers p ON r.passenger_id = p.passenger_id " +
                        "JOIN buses b ON r.bus_id = b.bus_id ORDER BY r.reservation_id";
            
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                System.out.println("\n--- RESERVATIONS ---");
                System.out.printf("%-4s | %-10s | %-20s | %-8s | %-12s → %-12s | %-8s | %-10s | %-8s%n",
                        "ID", "Bus", "Passenger", "Seats", "From", "To", "Date", "Fare", "Status");
                System.out.println("-".repeat(120));
                
                while (rs.next()) {
                    System.out.printf("%-4d | %-10s | %-20s | %-8s | %-12s → %-12s | %-8s | $%-7.2f | %-8s%n",
                            rs.getInt("reservation_id"),
                            rs.getString("bus_number"),
                            truncate(rs.getString("passenger_name"), 20),
                            rs.getString("seat_numbers"),
                            truncate(rs.getString("source"), 12),
                            truncate(rs.getString("destination"), 12),
                            rs.getString("travel_date"),
                            rs.getDouble("total_fare"),
                            rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void cancelReservation() {
        try {
            viewAllReservations();
            System.out.print("\nEnter Reservation ID to cancel: ");
            int resId = Integer.parseInt(scanner.nextLine());
            
            // Get reservation details
            String getSql = "SELECT * FROM reservations WHERE reservation_id = ? AND status = 'Confirmed'";
            try (PreparedStatement getStmt = connection.prepareStatement(getSql)) {
                getStmt.setInt(1, resId);
                ResultSet rs = getStmt.executeQuery();
                
                if (!rs.next()) {
                    System.out.println("Reservation not found or already cancelled!");
                    return;
                }
                
                int busId = rs.getInt("bus_id");
                String seats = rs.getString("seat_numbers");
                int numSeats = seats.split(",").length;
                
                // Cancel reservation
                String cancelSql = "UPDATE reservations SET status = 'Cancelled' WHERE reservation_id = ?";
                try (PreparedStatement cancelStmt = connection.prepareStatement(cancelSql)) {
                    cancelStmt.setInt(1, resId);
                    cancelStmt.executeUpdate();
                }
                
                // Restore seats
                String updateSql = "UPDATE buses SET available_seats = available_seats + ? WHERE bus_id = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, numSeats);
                    updateStmt.setInt(2, busId);
                    updateStmt.executeUpdate();
                }
                
                System.out.println("Reservation cancelled successfully!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void updatePaymentStatus() {
        try {
            viewAllReservations();
            System.out.print("\nReservation ID: ");
            int resId = Integer.parseInt(scanner.nextLine());
            System.out.print("Payment Status (Paid/Pending): ");
            String status = scanner.nextLine();
            
            String sql = "UPDATE reservations SET payment_status = ? WHERE reservation_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, status);
                pstmt.setInt(2, resId);
                pstmt.executeUpdate();
                System.out.println("Payment status updated!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void generateReport() {
        try {
            System.out.println("\n--- RESERVATION REPORT ---");
            
            // Total reservations
            String totalSql = "SELECT COUNT(*) as total FROM reservations";
            String confirmedSql = "SELECT COUNT(*) as confirmed FROM reservations WHERE status = 'Confirmed'";
            String revenueSql = "SELECT SUM(total_fare) as revenue FROM reservations WHERE payment_status = 'Paid'";
            
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs1 = stmt.executeQuery(totalSql);
                if (rs1.next()) System.out.println("Total Reservations: " + rs1.getInt("total"));
                
                ResultSet rs2 = stmt.executeQuery(confirmedSql);
                if (rs2.next()) System.out.println("Confirmed: " + rs2.getInt("confirmed"));
                
                ResultSet rs3 = stmt.executeQuery(revenueSql);
                if (rs3.next()) System.out.println("Total Revenue: $" + rs3.getDouble("revenue"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private String truncate(String str, int length) {
        if (str == null) return "";
        return str.length() > length ? str.substring(0, length - 3) + "..." : str;
    }
}
