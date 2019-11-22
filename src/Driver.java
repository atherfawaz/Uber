
import java.util.*;


public class Driver extends Person
{
    private double totalEarning;
    private float rating;
    private List<Trip> trips;

    public Driver(String a, String b, Date c, String d, String e, Boolean f)
    {
        super(a,b,c,d,e,f);
        super.setDriver(true);
        totalEarning = 0;
        rating = 0;
        trips = null;
    }
    public float getRating()
    {
        return this.rating;
    }
    public double getTotalEarning()
    {
        return this.totalEarning;
    }
    public void recalibrateRating(Integer r)
    {
        // TODO implement here
    }
    public void addToEarnings(double amount)
    {
        this.totalEarning += amount;
    }
    public Boolean acceptRide()
    {
        // TODO implement here
        return null;
    }
    public void displayTrips()
    {
        // TODO implement here
    }
    public void requestAssistance()
    {
        // TODO implement here
    }
    public void addRide(Trip trip)
    {
        // TODO implement here
    }
    public Boolean withdrawMoney(double amount)
    {
        // TODO implement here
        return null;
    }
    public Trip getCurrentRide() {
        // TODO implement here
        return null;
    }
    public List<Trip> getPreviousRides() {
        // TODO implement here
        return null;
    }
}