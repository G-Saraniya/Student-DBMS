package bus;

/**
 * Reservation Model Class - Represents a reservation entity
 */
public class Reservation {
    private int reservationId;
    private int busId;
    private int passengerId;
    private String seatNumbers;
    private String reservationDate;
    private String travelDate;
    private double totalFare;
    private String status;
    private String paymentStatus;
    
    // Additional fields for display
    private String passengerName;
    private String busNumber;
    private String source;
    private String destination;
    
    public Reservation() {}
    
    public Reservation(int reservationId, int busId, int passengerId, String seatNumbers,
                       String reservationDate, String travelDate, double totalFare,
                       String status, String paymentStatus) {
        this.reservationId = reservationId;
        this.busId = busId;
        this.passengerId = passengerId;
        this.seatNumbers = seatNumbers;
        this.reservationDate = reservationDate;
        this.travelDate = travelDate;
        this.totalFare = totalFare;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }
    
    // Getters and Setters
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    
    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }
    
    public String getSeatNumbers() { return seatNumbers; }
    public void setSeatNumbers(String seatNumbers) { this.seatNumbers = seatNumbers; }
    
    public String getReservationDate() { return reservationDate; }
    public void setReservationDate(String reservationDate) { this.reservationDate = reservationDate; }
    
    public String getTravelDate() { return travelDate; }
    public void setTravelDate(String travelDate) { this.travelDate = travelDate; }
    
    public double getTotalFare() { return totalFare; }
    public void setTotalFare(double totalFare) { this.totalFare = totalFare; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
    
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    @Override
    public String toString() {
        return String.format("Reservation #%d | Bus #%d | Passenger #%d | Seats: %-10s | Date: %s | $%.2f | %s | Payment: %s",
                reservationId, busId, passengerId, seatNumbers, travelDate, totalFare, status, paymentStatus);
    }
}
