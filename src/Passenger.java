
import java.util.*;

public class Passenger extends Person
{
    private List<Trip> trips;
    public Passenger(String a, String b, Date c, String d, String e, Boolean f, Account account)
    {
        super(a,b,c,d,e,f,account);
    }
   public double getExpenditure()
    {
        // TODO implement here
        return 0.0d;
    }
    public Boolean creditAccount(Double Amount)
    {
        // TODO implement here
        return null;
    }
    public void initiatePayment(String pType)
    {
        // TODO implement here
    }
    public Boolean rateDriver(Integer r)
    {
        // TODO implement here
        return null;
    }
    public Boolean requestCancellation()
    {
        // TODO implement here
        return null;
    }
    public Boolean makePayment(Double amount)
    {
        // TODO implement here
        return null;
    }
    public void requestAssistance()
    {
        // TODO implement here
    }
    public void addRide(Trip trip)
    {
        // TODO implement here
    }
    public void displayRides()
    {
        // TODO implement here
    }
    public Trip getCurrentRide()
    {
        // TODO implement here
        return null;
    }
    public void callARide()
    {
        // TODO implement here
    }
}