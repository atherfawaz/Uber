
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.text.*;

public class Passenger extends Person {

  private List<Trip> trips;

  public Passenger(String name, String nationalId, String dateOfBirth, String email, String phonenumber, Boolean isdriver, Account account) {
    super(name, nationalId, dateOfBirth, email, phonenumber, isdriver, account);
    trips = new ArrayList<>();
  }

  Passenger() {
    trips = new ArrayList<>();
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

  public void initiatePayment(String pType, Double amount, Driver d) {
    if (pType.equalsIgnoreCase("cash")) {
      System.out.println("You have successfully paid the driver.");
      return;
    }
    makePayment(amount, d);
  }

  public Boolean rateDriver(Integer r) {
    trips.get(trips.size() - 1).giveRating(r);
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

  public Boolean makePayment(Double amount, Driver d) {
    if (account.debitAccount(amount)) {
      if(Uber.paymentSystem.makePayment(amount,d)) {
        System.out.println("You have successfully paid the driver.");
        return true;
      }
    }
    System.out.println("Pay via cash!");
    try
    {
      Thread.sleep(1500);
    }
    catch(InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }
    System.out.println("You have successfully paid the driver.");
    return false;
  }

  public void requestAssistance() {
    System.out.println("Requesting assistance from the Uber staff assigned.");
    trips.get(trips.size() - 1).helpPassenger(Automate.staff);
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

  public void callARide(List<Driver> dList) {
    Boolean validTrip = false;
    Boolean validLoc = false;
    while (!(validTrip)) {
      Scanner input = new Scanner(System.in);
      System.out.println("-------------------------------------------------\nHello " + this.getName() + "!");
      System.out.println("Enter your starting point.\n - Eden Avenue\n - Bhatta Chowk\n - DHA Phase 1\n - Model Town\n");
      String startingPoint = input.nextLine();
      System.out.println("Enter your destination. \n - Eden Avenue\n - Bhatta Chowk\n - DHA Phase 1\n - Model Town\n");
      String destination = input.nextLine();
      String currTime = LocalDateTime.now()
              .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
      Trip trip = new Trip(startingPoint, destination, null, null, null, this, currTime, null);
      if (trip.checkRouteValidity(startingPoint, destination)) {
        validTrip = true;
        trip.calculateShortestRoute();
        trip.calculateFare();
       /* gen random index
        If (person.get(randomIndex).getName().starts with 'u' && person.isFree)
        request assistance from him
        break; */
        boolean loopVar = true;
        int randIndex;
        while (loopVar) {
          randIndex = (int) (Math.random() * ((dList.size() - 1) + 1));
          if (dList.get(randIndex).getIsFree()) {
            dList.get(randIndex).setIsFree(false);
            dList.get(randIndex).acceptRide(trip);
            trip.addDriver(dList.get(randIndex));
            //trip.addVehicle(dList.get(randIndex).getVehicles().get(0));
            addRide(trip);
            loopVar = false;
            trip.startRide();
          }
        }
      }
      else {
        System.out.println("Sorry, you've input the same starting point as the destination, or chosen an unavailable location! Please re-enter your choices.\n");
      }
    }
  }

  void passengerInterface() {
    //implement UI for passenger here
    //maybe a while(true) loop to mimic the state of the app
    //perform all operations here
  }

}