
import javax.swing.*;
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
        Trip latestTrip = trips.get(trips.size() -1 );
        String driverNID = latestTrip.getDriverNId();
        for (int i = 0; i < pList.size(); i++)
        {
            if (driverNID.equals(pList.get(i).getNationalId()))
            {
                pList.get(i).
            }
        }

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
        if (account.debitAccount(amount))
        {
            System.out.println("You have successfully paid the driver");
            return true;
        }
        return false;
    }
    public void requestAssistance()
    {
        System.out.println("Requesting assistance from the uber staff assigned.");
        trips.get(trips.size()-1).helpPassenger();
    }
    public void addRide(Trip trip)
    {
        trips.add(trip);
    }
    public void displayRides()
    {
        for(int t = trips.size(); t>=0 ; t--)
        {
            System.out.println("Trip " + t+1 + " was conducted on " + trips.get(t).getDateTime());
            System.out.println("It began at " + trips.get(t).getStartingPoint() + " and ended at " + trips.get(t).getDestination() + ".");
            System.out.println("The total money you the ride cost the passenger was " + trips.get(t).getTotalCost() + "\n");
        }
    }
    public Trip getCurrentRide()
    {
        return trips.get(trips.size() - 1);
    }
    public void callARide()
    {
        Trip temp = new Trip();
        //random gen?
    }
}