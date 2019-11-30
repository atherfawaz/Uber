
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.text.*;

public class Passenger extends Person {

  private List<Trip> trips;

  public Passenger(String a, String b, String c, String d, String e, Boolean f, Account account) {
    super(a, b, c, d, e, f, account);
  }

  Passenger(Person passed) {
    super(passed);
    trips = new ArrayList<>();
    //pushed
  }
  public double getExpenditure() {
    return trips.get(trips.size() - 1).getTotalCost();
  }

  public Boolean creditAccount(Double Amount) {
    account.addCredit(Amount);
    return true;
  }

  public void initiatePayment(String pType, Double amount) {
    if (pType.equalsIgnoreCase("cash")) {
      System.out.println("You have successfully paid the driver.");
      return;
    }
    makePayment(amount);
  }

  public Boolean rateDriver(Integer r) {
    trips.get(trips.size() - 1).setRating(r);
    return true;
  }

  public Boolean requestCancellation() throws ParseException {
    Trip lastTrip = trips.get(trips.size() - 1);
    String scheduleTime = lastTrip.getTimeForSchedule();
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    Date start = format.parse(scheduleTime);
    Date end = format.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

    if (end.getTime() - start.getTime() < 20 * 10000) {
      return true;
    } else {
      return false;
    }
  }

  public Boolean makePayment(Double amount) {
    if (account.debitAccount(amount)) {
      System.out.println("You have successfully paid the driver");
      return true;
    }
    return false;
  }

  public void requestAssistance() {
    System.out.println("Requesting assistance from the Uber staff assigned.");
    trips.get(trips.size() - 1).helpPassenger();
  }

  public void addRide(Trip trip) {
    trips.add(trip);
  }

  public void displayRides() {
    for (int t = trips.size(); t >= 0; t--) {
      System.out.println("Trip " + t + 1 + " was conducted on " + trips.get(t).getDateTime());
      System.out.println(
          "It began at " + trips.get(t).getStartingPoint() + " and ended at " + trips.get(t)
              .getDestination() + ".");
      System.out.println(
          "The total money you the ride cost the passenger was " + trips.get(t).getTotalCost()
              + "\n");
    }
  }

  public Trip getCurrentRide() {
    return trips.get(trips.size() - 1);
  }

    public void callARide(List<Driver> dList)
    {
        double baseFair = 100;
        double tripDistance = (Math.random() * ((30 - 1) + 1)) + 1;
        double totalCost = baseFair + tripDistance * 25;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your starting point: ");
        String startingPoint = input.nextLine();
        System.out.println("Enter your destination: ");
        String destination = input.nextLine();
        String currTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        Trip trip = new Trip(startingPoint,destination,null,null,null,this,currTime,totalCost);
       /* gen random index
        If (person.get(randomIndex).getName().starts with 'u' && person.isFree)
        request assistance from him
        break; */
        boolean loopVar = true;
        while(loopVar)
        {
            int randIndex = (int) (Math.random() * ((dList.size()) + 1));
            if (dList.get(randIndex).getIsFree())
            {
                dList.get(randIndex).setIsFree(false);
                dList.get(randIndex).acceptRide(trip);
                trip.addDriver(dList.get(randIndex));
                loopVar = false;
            }
        }
    }
}