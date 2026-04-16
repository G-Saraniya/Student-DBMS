package bus;

import java.util.Scanner;

/**
 * Main - Entry point for Bus Reservation System
 * Menu-driven console application with switch-case controller
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationController controller = new ReservationController();
        
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           BUS RESERVATION SYSTEM                          ║");
        System.out.println("║              Welcome to Bus Booking                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        if (!DBConnection.testConnection()) {
            System.out.println("❌ Database connection failed!");
            System.out.println("1. Create database 'bus_reservation' in MySQL");
            System.out.println("2. Update password in DBConnection.java");
            System.out.println("3. Ensure MySQL is running");
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
                        controller.viewAllBuses();
                        break;
                    case 2:
                        controller.searchBuses();
                        break;
                    case 3:
                        controller.addBus();
                        break;
                    case 4:
                        controller.addPassenger();
                        break;
                    case 5:
                        controller.viewAllPassengers();
                        break;
                    case 6:
                        controller.makeReservation();
                        break;
                    case 7:
                        controller.viewAllReservations();
                        break;
                    case 8:
                        controller.cancelReservation();
                        break;
                    case 9:
                        controller.updatePaymentStatus();
                        break;
                    case 10:
                        controller.generateReport();
                        break;
                    case 0:
                        System.out.println("\nThank you for using Bus Reservation System!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 0-10.");
                }
                
                if (running && choice >= 1 && choice <= 10) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
        
        DBConnection.closeConnection();
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    MAIN MENU                              ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  1. View All Buses                                        ║");
        System.out.println("║  2. Search Buses                                         ║");
        System.out.println("║  3. Add New Bus (Admin)                                  ║");
        System.out.println("║  ─────────────────────────────────────────────────────  ║");
        System.out.println("║  4. Register Passenger                                   ║");
        System.out.println("║  5. View All Passengers                                   ║");
        System.out.println("║  ─────────────────────────────────────────────────────  ║");
        System.out.println("║  6. Make Reservation                                     ║");
        System.out.println("║  7. View All Reservations                                 ║");
        System.out.println("║  8. Cancel Reservation                                    ║");
        System.out.println("║  9. Update Payment Status                                ║");
        System.out.println("║  10. Generate Report                                      ║");
        System.out.println("║  ─────────────────────────────────────────────────────  ║");
        System.out.println("║  0. Exit                                                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}
