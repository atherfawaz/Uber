
import java.util.*;
//abc

public class Driver extends Person
{
    private String startDate;
    private double totalEarning;
    private float rating;
    private List<Trip> trips = new ArrayList<>();
    private List<Vehicle> personalCars = new ArrayList<>();

    public Driver (String a, String b, String c, String d, String e, Boolean f, String x, Account account)
    {
        super(a,b,c,d,e,f,account);
        super.setDriver(true);
        totalEarning = 0;
        rating = 0;
        trips = null;
        startDate = x;
    }

    public Driver(Person passed) {
        super(passed);
        System.out.println("Enter your ");
    }
    public float getRating()
    {
        return this.rating;
    }
    public double getTotalEarning()
    {
        return this.totalEarning;
    }
    public String getStartDate() { return startDate; }

    public void recalibrateRating(Integer r)
    {
        System.out.println(this.getName() + "'s rating before this trip ended was " + rating);
        rating = ((rating*trips.size()-1)+r)/trips.size();
        System.out.println(this.getName() + "'s new rating is " + rating);
    }
    public void addToEarnings(double amount)
    {
        this.totalEarning += amount;
        System.out.println(amount + "has been added to your total earnings.");
    }
    public Boolean acceptRide(Trip trip)
    {
        int acceptNum = getName().length() +  trip.getStartingPoint().length() - trip.getDestination().length(); //Jawad algorithm
        if (acceptNum>0)
        {
            trip.setDriverNID(super.getNationalId()); // adding all the stuff specific to the driver.
            addRide(trip);
            System.out.println(this.getName() + " has accepted the ride requested.");
            return true;
        }
        return false;
    }
    public void displayTrips()
    {
        for (Trip t:trips)
        {
            String pID = t.getPassengerNId();
            System.out.println("Trip " + t + 1 + " was conducted on " + t.getDateTime());
            System.out.println("It began at " + t.getStartingPoint() + " and ended at " + t.getDestination() + ".");
            System.out.println("The total money you the ride cost the passenger was " + t.getTotalCost() + "\n");
        }
    }
    public void requestAssistance() //unsure about implementation
    {
        System.out.println("Requesting assistance from the uber staff assigned.");
        trips.get(trips.size()-1).helpDriver();
    }
    public void addRide(Trip trip)
    {
        trips.add(trip);
    }
    public Boolean withdrawMoney(double amount)
    {
        if (amount<=totalEarning)
        {
            totalEarning -= amount;
            System.out.println(amount + " has been withdrawn from your total earnings. Remaining amount is " + totalEarning +"\n");
            return true;
        }
        else
        {
            System.out.println("The amount i.e. " + amount + " you're trying to withdraw is greater than the amount present in your total earnings! Your total earnings are " + totalEarning +"\n");
            return false;
        }
    }
    public Trip getCurrentRide()
    {
        return trips.get(trips.size()-1);
    }
    public List<Trip> getPreviousRides()
    {
        if (!(trips.isEmpty()))
            return trips;
        System.out.println("You have not made any trips as a driver yet!\n");
        return null;
    }
}