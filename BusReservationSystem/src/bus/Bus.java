package bus;

/**
 * Bus Model Class - Represents a bus entity
 */
public class Bus {
    private int busId;
    private String busNumber;
    private String busName;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private double fare;
    private String busType;
    private String travelDate;
    private String status;
    
    public Bus() {}
    
    public Bus(int busId, String busNumber, String busName, String source, String destination,
               String departureTime, String arrivalTime, int totalSeats, int availableSeats,
               double fare, String busType, String travelDate, String status) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.busName = busName;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fare = fare;
        this.busType = busType;
        this.travelDate = travelDate;
        this.status = status;
    }
    
    // Getters and Setters
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    
    public String getBusName() { return busName; }
    public void setBusName(String busName) { this.busName = busName; }
    
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    
    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }
    
    public String getBusType() { return busType; }
    public void setBusType(String busType) { this.busType = busType; }
    
    public String getTravelDate() { return travelDate; }
    public void setTravelDate(String travelDate) { this.travelDate = travelDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return String.format("Bus %-6s | %-15s | %s → %s | %s - %s | Seats: %d/%d | $%.2f | %s",
                busNumber, busName, source, destination, departureTime, arrivalTime,
                availableSeats, totalSeats, fare, busType);
    }
}
