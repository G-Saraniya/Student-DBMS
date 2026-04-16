package bus;

/**
 * Passenger Model Class - Represents a passenger entity
 */
public class Passenger {
    private int passengerId;
    private String name;
    private String email;
    private String phone;
    private int age;
    private String gender;
    private String address;
    
    public Passenger() {}
    
    public Passenger(int passengerId, String name, String email, String phone, 
                     int age, String gender, String address) {
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }
    
    // Getters and Setters
    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    @Override
    public String toString() {
        return String.format("Passenger #%d: %-20s | %-25s | %-12s | Age: %d | %s",
                passengerId, name, email, phone, age, gender);
    }
}
