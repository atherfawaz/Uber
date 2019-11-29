
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
        return trips.get(trips.size() - 1).getTotalCost();
    }
    public Boolean creditAccount(Double Amount)
    {
        account.addCredit(Amount);
        return true;
    }
    public void initiatePayment(String pType, Double amount)
    {
        if (pType.equalsIgnoreCase("cash"))
        {
            System.out.println("You have successfully paid the driver.");
            return;
        }
        makePayment(amount);
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
        account.debitAccount(amount);
        System.out.println("You have successfully paid the driver");
        return true;
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