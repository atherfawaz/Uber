
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.text.*;

public class Passenger extends Person {

  private Boolean isOnTrip = false;
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

  public Boolean getStatus()
  {
    return isOnTrip;
  }

  public void setStatus(Boolean isOnTrip)
  {
    this.isOnTrip = isOnTrip;
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
    Uber.mySleep(2000);
    return;
  }

  public Boolean rateDriver(Integer r) {
    trips.get(trips.size() - 1).giveRating(r);
    return true;
  }

  public Boolean requestCancellation() throws ParseException {
    if (isOnTrip) {
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
    else
    {
      System.out.println("Sorry, a trip has not been initialized by you! You can only request cancellation when a trip has been initialized.");
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
    Uber.mySleep(2000);
    System.out.println("You have successfully paid the driver.");
    return false;
  }

  public void requestAssistance() {
    System.out.println("Requesting assistance from the Uber staff assigned.");
    System.out.println("Requesting assistance from the Uber staff assigned.");
    if (isOnTrip) {
        trips.get(trips.size() - 1).helpPassenger();
    }
    else
    {
        System.out.println("You are currently not on a trip! Please request assistance while a trip is ongoing.");
    }
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
      if (isOnTrip) {
          System.out.println("Trip " + (trips.size() - 1) + " was conducted on " + trips.get((trips.size() - 1)).getDateTime());
          System.out.println(
                  "It began at " + trips.get((trips.size() - 1)).getStartingPoint() + " and ended at " + trips.get((trips.size() - 1))
                          .getDestination() + ".");
          System.out.println(
                  "The total money the ride cost you was " + trips.get((trips.size() - 1)).getTotalCost()
                          + "\n");
      }
      else
      {
          System.out.println("You are currently not on a trip!");
      }
  }

  public Trip getCurrentRide() {
    return trips.get(trips.size() - 1);
  }

  public void callARide() {
    Boolean validTrip = false;
    Boolean validLoc = false;
    while (!(validTrip)) {
      Uber.clearScreen();
      Scanner input = new Scanner(System.in);
      System.out.println("Enter your starting point.\n - Eden Avenue\n - Bhatta Chowk\n - DHA Phase 1\n - Model Town\n");
      String startingPoint = input.nextLine();
      System.out.println("Enter your destination. \n - Eden Avenue\n - Bhatta Chowk\n - DHA Phase 1\n - Model Town\n");
      String destination = input.nextLine();
      String currTime = LocalDateTime.now() .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
      Trip trip = new Trip(startingPoint, destination, null, null, null, this, currTime, null);
      if (trip.checkRouteValidity(startingPoint, destination)) {
        validTrip = true;
        trip.calculateShortestRoute();
        trip.calculateFare();
        boolean loopVar = true;
        int randIndex;
        int rating_;
        Boolean ratingCheck = false;
        while (loopVar) {
          randIndex = (int) (Math.random() * ((Uber.drivers.size() - 1) + 1));
          if (Uber.drivers.get(randIndex).getIsFree()) {
            Uber.drivers.get(randIndex).setIsFree(false);
            System.out.println("Searching for a ride...");
            Uber.mySleep(3000);
            Uber.drivers.get(randIndex).acceptRide(trip);
            trip.addDriver(Uber.drivers.get(randIndex));
            trip.addVehicle(Uber.drivers.get(randIndex).getVehicle());
            addRide(trip);
            loopVar = false;
            trip.startRide();
            this.setStatus(true);
            initiatePayment("notcash", getCurrentRide().getTotalCost(), getCurrentRide().getDriver());
            while (ratingCheck == false) {
              System.out.println("Now that the trip is complete, what rating would you like to give the driver, " + trip.getDriver().getName() + ", for this trip?\nRate on a scale of 1 (very unsatisfactory) to 5 (highly satisfactory).");
              rating_ = input.nextInt();
              if (rating_ >= 1 && rating_ <= 5) {
                ratingCheck = true;
                rateDriver(rating_);
              }
              else System.out.println("Sorry, you can only rate between 1 and 5. Kindly enter rating again.");
            }
            this.setStatus(false);
          }
        }
      }
      else {
        System.out.println("Sorry, you've input the same starting point as the destination, or chosen an unavailable location! Please re-enter your choices.\n");
      }
    }
  }

  void passengerInterface() throws ParseException {
    while (true)
    {
      Uber.clearScreen();
      System.out.println("-------------------------------------------------\nHello " + this.getName() + "!\nPlease enter the number of one of the options below to begin your journey with Uber.");
      System.out.println("1. Search for a ride\n2. Show all trips\n3. Show current trip info\n4. Request assistance\n5. Request cancellation\n6. Exit");
      Scanner input = new Scanner(System.in);
      int choice = input.nextInt();
      if (choice == 1)
      {
        this.callARide();
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
        this.requestCancellation();
      }
      else if (choice == 6)
      {
        break;
      }
      else
      {
        System.out.println("Sorry, you did not enter any of the mentioned options. Please enter a correct option.\n");
      }
    }
  }

  public void callARideSimulator() {
    Boolean validTrip = false;
    Boolean validLoc = false;
    while (!(validTrip)) {
      Uber.clearScreen();
      System.out.println("Enter your starting point.\n - Eden Avenue\n - Bhatta Chowk\n - DHA Phase 1\n - Model Town\n");
      Uber.mySleep(3000);
      String startingPoint = "Eden Avenue";
      System.out.println("Selected starting point is " + startingPoint);
      System.out.println("Enter your destination. \n - Eden Avenue\n - Bhatta Chowk\n - DHA Phase 1\n - Model Town\n");
      String destination = "Bhatta Chowk";
      System.out.println("Selected destination is "+ destination);
      String currTime = LocalDateTime.now() .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
      Trip trip = new Trip(startingPoint, destination, null, null, null, this, currTime, null);
      if (trip.checkRouteValidity(startingPoint, destination)) {
        validTrip = true;
        trip.calculateShortestRoute();
        trip.calculateFare();
        boolean loopVar = true;
        int randIndex;
        int rating_;
        Boolean ratingCheck = false;
        while (loopVar) {
          randIndex = (int) (Math.random() * ((Uber.drivers.size() - 1) + 1));
          if (Uber.drivers.get(randIndex).getIsFree()) {
            Uber.drivers.get(randIndex).setIsFree(false);
            System.out.println("Searching for a ride...");
            Uber.mySleep(3000);
            Uber.drivers.get(randIndex).acceptRide(trip);
            trip.addDriver(Uber.drivers.get(randIndex));
            trip.addVehicle(Uber.drivers.get(randIndex).getVehicle());
            addRide(trip);
            loopVar = false;
            trip.startRide();
            this.setStatus(true);
            initiatePayment("notcash", getCurrentRide().getTotalCost(), getCurrentRide().getDriver());
            System.out.println("Now that the trip is complete, what rating would you like to give the driver, " + trip.getDriver().getName() + ", for this trip?\nRate on a scale of 1 (very unsatisfactory) to 5 (highly satisfactory).");
            rating_ = 3;
            System.out.println("You have given the driver a rating of 3.");
            this.setStatus(false);
          }
        }
      }
      else {
        System.out.println("Sorry, you've input the same starting point as the destination, or chosen an unavailable location! Please re-enter your choices.\n");
      }
    }
  }

  void passengerInterfaceSimulate() throws ParseException {
    while (true)
    {
      Uber.clearScreen();
      System.out.println("-------------------------------------------------\nHello " + this.getName() + "!\nPlease enter the number of one of the options below to begin your journey with Uber.");
      System.out.println("1. Search for a ride\n2. Show all trips\n3. Show current trip info\n4. Request assistance\n5. Request cancellation\n6. Exit");
      int choice = 1;
      if (choice == 1)
      {
        this.callARideSimulator();
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
        this.requestCancellation();
      }
      else if (choice == 6)
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