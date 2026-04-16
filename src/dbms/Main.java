package dbms;

import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        
        // Test database connection on startup
        System.out.println("Student DBMS Management System");
        System.out.println("==============================");
        
        if (!DBConnection.testConnection()) {
            System.out.println("Failed to connect to database. Please check your configuration.");
            System.out.println("Update DBConnection.java with correct MySQL credentials.");
            return;
        }
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            
            try {
                System.out.print("\nEnter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        // Add Student
                        student.addStudent();
                        break;
                        
                    case 2:
                        // View Students
                        student.viewAllStudents();
                        break;
                        
                    case 3:
                        // Update Student
                        student.updateStudent();
                        break;
                        
                    case 4:
                        // Delete Student
                        student.deleteStudent();
                        break;
                        
                    case 5:
                        // Search Student
                        student.searchStudent();
                        break;
                        
                    case 6:
                        // Print Students (formatted output)
                        student.printStudents();
                        break;
                        
                    case 7:
                        // Delete Database (Admin)
                        student.deleteDatabase();
                        break;
                        
                    case 8:
                        // Bulk Upload
                        student.bulkUploadFromFile();
                        break;
                        
                    case 9:
                        // Exit
                        System.out.println("\nThank you for using Student DBMS. Goodbye!");
                        running = false;
                        break;
                        
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 9.");
                }
                
                if (running && choice >= 1 && choice <= 8) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        
        // Close database connection before exiting
        DBConnection.closeConnection();
        scanner.close();
    }
    
    /**
     * Display the main menu
     */
    private static void displayMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     STUDENT DBMS MAIN MENU         ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║  1. Add Student                    ║");
        System.out.println("║  2. View Students                  ║");
        System.out.println("║  3. Update Student                 ║");
        System.out.println("║  4. Delete Student                 ║");
        System.out.println("║  5. Search Student                 ║");
        System.out.println("║  6. Print Students (Formatted)     ║");
        System.out.println("║  7. Delete Database (Admin)        ║");
        System.out.println("║  8. Bulk Upload from File          ║");
        System.out.println("║  9. Exit                           ║");
        System.out.println("╚════════════════════════════════════╝");
    }
}
