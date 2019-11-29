
import java.util.*;


public class Driver extends Person
{
    private Date startDate;
    private double totalEarning;
    private float rating;
    private List<Trip> trips;
    public Driver(String a, String b, Date c, String d, String e, Boolean f, Date x, Account account)
    {
        super(a,b,c,d,e,f,account);
        super.setDriver(true);
        totalEarning = 0;
        rating = 0;
        trips = null;
        startDate = x;
    }
    public float getRating()
    {
        return this.rating;
    }
    public double getTotalEarning()
    {
        return this.totalEarning;
    }
    public Date getStartDate() { return startDate; }

    public void recalibrateRating(Integer r)
    {
        System.out.println(this.getName() + "'s rating before this trip ended was " + rating);
        rating = ((rating*trips.size()-1)+r)/trips.size();
        System.out.println(this.getName() + "'s new rating is " + rating);
    }
    public void addToEarnings(double amount)
    {
        this.totalEarning += amount;
    }
    public Boolean acceptRide(Passenger P, Trip trip)
    {
        int acceptNum = P.getName().length() - trip.getStartingPoint().length() - trip.getDestination().length();
        if (acceptNum>0)
        {
            addRide(trip);
            System.out.println(this.getName() + " has accepted the ride requested by " + P.getName());
            return true;
        }
        return false;
    }
    public void displayTrips()
    {
        for (Trip t:trips)
        {
            int i = 1;
            String pID = t.getPassengerNId();
            System.out.println("Trip " + i + " was conducted on " + t.getDateTime());
            System.out.println("The passenger for this trip was " + t.getPassengerNId());
            System.out.println("It began at " + t.getStartingPoint() + " and ended at " + t.getDestination() + ".");
            System.out.println("The total money you the ride cost the passenger was " + t.getTotalCost() + "\n");
        }
    }
    public void requestAssistance() //unsure about implementation
    {
        // TODO implement here
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