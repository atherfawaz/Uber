
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.text.*;

public class Passenger extends Person {

  private List<Trip> trips = new ArrayList<>();

  public Passenger(String name, String nationalId, String dateOfBirth, String email, String phonenumber, Boolean isdriver, Account account) {
    super(name, nationalId, dateOfBirth, email, phonenumber, isdriver, account);

  }

  Passenger() {

  }

  Passenger(Person passed) {
    super(passed);
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
    }
    else {
      makePayment(amount, d);
    }
    try
    {
      Thread.sleep(2000);
    }
    catch(InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }
    return;
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
    trips.get(trips.size() - 1).helpPassenger();
  }

  public void addRide(Trip trip) {
    trips.add(trip);
  }

  public void displayRides() {
    if (!(trips.isEmpty())) {
      for (int t = trips.size() - 1; t >= 0; t--) {
        System.out.println("Trip " + t + 1 + " was conducted on " + trips.get(t).getDateTime());
        System.out.println(
                "It began at " + trips.get(t).getStartingPoint() + " and ended at " + trips.get(t)
                        .getDestination() + ".");
        System.out.println(
                "The total money the ride you passenger was " + trips.get(t).getTotalCost()
                        + "\n");
      }
    }
    else
    {
      System.out.println("You have not made any trips with Uber yet.");
    }
  }

  public void displayCurrentRide(){
    System.out.println("Trip " + (trips.size()-1) + " was conducted on " + trips.get((trips.size()-1)).getDateTime());
    System.out.println(
            "It began at " + trips.get((trips.size()-1)).getStartingPoint() + " and ended at " + trips.get((trips.size()-1))
                    .getDestination() + ".");
    System.out.println(
            "The total money the ride cost you was " + trips.get((trips.size()-1)).getTotalCost()
                    + "\n");
  }

  public Trip getCurrentRide() {
    return trips.get(trips.size() - 1);
  }

  public void callARide(List<Driver> dList) {
    Boolean validTrip = false;
    Boolean validLoc = false;
    while (!(validTrip)) {
      Uber.clearScreen();
      Scanner input = new Scanner(System.in);
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
            System.out.println("Searching for a ride...");
            try
            {
              Thread.sleep(3000);
            }
            catch(InterruptedException ex)
            {
              Thread.currentThread().interrupt();
            }
            dList.get(randIndex).acceptRide(trip);
            trip.addDriver(dList.get(randIndex));
            //trip.addVehicle(dList.get(randIndex).getVehicles().get(0));
            addRide(trip);
            loopVar = false;
            trip.startRide();
            initiatePayment("notcash", getCurrentRide().getTotalCost(), getCurrentRide().getDriver());
          }
        }
      }
      else {
        System.out.println("Sorry, you've input the same starting point as the destination, or chosen an unavailable location! Please re-enter your choices.\n");
      }
    }
  }

  void passengerInterface() throws ParseException {
    //implement UI for passenger here
    //maybe a while(true) loop to mimic the state of the app
    //perform all operations here
    while (true)
    {
      Uber.clearScreen();
      System.out.println("-------------------------------------------------\nHello " + this.getName() + "!\nPlease enter the number of one of the options below to begin your journey with Uber.");
      System.out.println("1. Search for a ride\n2. Show all trips\n3. Show current trip info\n4. Request assistance\n5. Exit");
      Scanner input = new Scanner(System.in);
      int choice = input.nextInt();
      if (choice == 1)
      {
        this.callARide(Uber.drivers);
      }
      else if (choice == 2)
      {
        this.displayRides();
      }
      else if (choice == 3)
      {
        this.displayCurrentRide();
      }
      else if (choice == 4)
      {
        this.requestAssistance();
      }
      else if (choice == 5)
      {
        break;
      }
      else
      {
        System.out.println("Sorry, you did not enter any of the mentioned options. Please enter a correct option.\n");
      }
    }
  }


  public void removeTrip(Trip t)
  {
    if (trips != null) trips.remove(t);
  }

}