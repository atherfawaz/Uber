public class Complaint
{
    private String complaintDescription;
    private Driver driver;
    private Passenger passenger;

    Complaint(String complaintDescription, Driver driver, Passenger passenger)
    {
        this.complaintDescription = complaintDescription;
        this.driver = driver;
        this.passenger = passenger;
    }
    public void viewComplaint()
    {
        System.out.println("Complaint by user: " + passenger.getName());
        System.out.println("Registered against driver: " + driver.getName());
        System.out.println("Description: " + complaintDescription);
    }
}
