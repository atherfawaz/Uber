
import java.text.ParseException;
import java.util.*;
//abc


public class Driver extends Person {

  private Boolean isFree = true;
  private String startDate;
  private double totalEarning;
  private float rating;
  private List<Trip> trips = new ArrayList<>();
  private Vehicle vehicle;
  private Boolean isOnTrip = false;

  Driver()
  {
    vehicle = new Vehicle();
  }
  public Driver(String name, String nationalId, String dateOfBirth, String email,
      String phonenumber, Boolean isdriver, Account account) {
    super(name, nationalId, dateOfBirth, email, phonenumber, isdriver, account);
    super.setDriver(true);
    isFree = true;
    totalEarning = 0;
    rating = 0;
    trips = new ArrayList<>();
    startDate = "Current system date";
    //addVehicle();
    //setVehicle();
  }

  public Driver(Person passed) {
    super(passed);
    addVehicle();
  }

  public void setIsFree(Boolean isFree_) {
    isFree = isFree_;
  }

  public Boolean getIsFree() {
    return isFree;
  }


  public Vehicle getVehicle() {
    return this.vehicle;
  }

  ;

  public float getRating() {
    return this.rating;
  }

  public double getTotalEarning() {
    return this.totalEarning;
  }

  public String getStartDate() {
    return startDate;
  }

  public void recalibrateRating(Integer r) {
    System.out.println(this.getName() + "'s rating before this trip ended was " + rating);
    if (trips.size()!=1) rating = ((rating * trips.size() - 1) + r) / trips.size();
    else rating = r;
    System.out.println(this.getName() + "'s new rating is " + rating);
  }

  public void addToEarnings(double amount) {
    this.totalEarning += amount;
    account.addCredit(amount);
    System.out.println("Driver " + this.getName() + ": " + amount + " has been added to your total earnings.");
  }

  public void addVehicle() {
    Scanner userInput = new Scanner(System.in);
    String choicestring;
    int choiceint;

    System.out.println(
        "Enter the type of vehicle you want to enter into the system. (Car, Rickshaw, Motorcycle):");
    choicestring = userInput.nextLine();

    if (choicestring.equalsIgnoreCase("Car")) {

      Car newCar = new Car();
      newCar = new Car();
      System.out.println("Enter your car type. (Sedan, Small, Luxury):");
      choicestring = userInput.nextLine();
      newCar.setType(choicestring);
      System.out.println("Enter your car's registration number:");
      choicestring = userInput.nextLine();
      newCar.setRegistrationNum(choicestring);
      System.out.println("Enter your car's condition (new/minimal wear/old):");
      choicestring = userInput.nextLine();
      newCar.setCondition(choicestring);
      System.out.println("Enter your car's make:");
      choicestring = userInput.nextLine();
      newCar.setMake(choicestring);
      System.out.println("Enter your car's model:");
      choiceint = userInput.nextInt();
      newCar.setModel(choiceint);
      vehicle = newCar;
      //personalCars.add(newCar);

    } else if (choicestring.equalsIgnoreCase("Rickshaw")) {

      Rickshaw newRickshaw = new Rickshaw();
      System.out.println("Enter your rickshaw's registration number");
      choicestring = userInput.nextLine();
      newRickshaw.setRegistrationNum(choicestring);
      System.out.println("Enter your rickshaw's condition (new/minimal wear/old)");
      choicestring = userInput.nextLine();
      newRickshaw.setCondition(choicestring);
      System.out.println("Enter your rickshaw's make");
      choicestring = userInput.nextLine();
      newRickshaw.setMake(choicestring);
      System.out.println("Enter your rickshaw's model");
      choiceint = userInput.nextInt();
      newRickshaw.setModel(choiceint);
      vehicle = newRickshaw;
     // personalCars.add(newRickshaw);

    } else if (choicestring.equalsIgnoreCase("Motorcycle")) {

      Motorcycle newMotorCycle = new Motorcycle();
      System.out.println("Enter your motorcycle's registration number");
      choicestring = userInput.nextLine();
      newMotorCycle.setRegistrationNum(choicestring);
      System.out.println("Enter your motorcycle's condition (new/minimal wear/old)");
      choicestring = userInput.nextLine();
      newMotorCycle.setCondition(choicestring);
      System.out.println("Enter your motorcycle's make");
      choicestring = userInput.nextLine();
      newMotorCycle.setMake(choicestring);
      System.out.println("Enter your motorcycle's model");
      choiceint = userInput.nextInt();
      newMotorCycle.setModel(choiceint);
      vehicle = newMotorCycle;
      //personalCars.add(newMotorCycle);
    }
  }

  public Boolean acceptRide(Trip trip) {
    //int acceptNum = getName().length() + trip.getStartingPoint().length() - trip.getDestination()
    //  .length(); //Jawad algorithm
    //if (acceptNum > 0) {
    trip.addDriver(this); // adding all the stuff specific to the driver.
    addRide(trip);
    System.out.println(this.getName() + " has accepted the ride requested.");
    isOnTrip = true;
    return true;
  }

  public void setVehicle()
  {
    vehicle = new Car("219861293","new","Camry",2009,"Toyota","sedan");
  }

  public void displayTrips() {
    if (!(trips.isEmpty())) {
      for (int t = trips.size() - 1; t >= 0; t--) {
        System.out.println("Trip " + t + 1 + " was conducted on " + trips.get(t).getDateTime());
        System.out.println(
                "It began at " + trips.get(t).getStartingPoint() + " and ended at " + trips.get(t).getDestination() + ".");
        System.out.println(
                "The total money the ride  " + trips.get(t).getTotalCost() + "\n");
      }
    }
    else
    {
      System.out.println("You have not made any trips with Uber yet.");
    }
  }

  public void displayCurrentRide() {
    if (isOnTrip) {
      System.out.println(
              "Trip " + (trips.size() - 1) + " was conducted on " + trips.get((trips.size() - 1))
                      .getDateTime());
      System.out.println(
              "It began at " + trips.get((trips.size() - 1)).getStartingPoint() + " and ended at "
                      + trips.get((trips.size() - 1))
                      .getDestination() + ".");
      System.out.println(
              "The total money you will earn from this trip is " + trips.get((trips.size() - 1)).getTotalCost()
                      + "\n");
    } else {
      System.out.println("You are currently not on a trip!");
    }
  }

  public void requestAssistance() //unsure about implementation
  {
    if (isOnTrip) {
      System.out.println("Driver " + this.getName() + " is requesting assistance from the uber staff assigned.");
      trips.get(trips.size() - 1).helpDriver();
    }
    else
    {
      System.out.println(
              "You are currently not on a trip! Please request assistance while a trip is ongoing.");
    }
  }

  public void addRide(Trip trip) {
    trips.add(trip);
  }

  public boolean withdrawMoney(Double amount) {
    return account.cashout(amount);
  }

  public Trip getCurrentRide() {
    return trips.get(trips.size() - 1);
  }

  public List<Trip> getPreviousRides() {
    if (!(trips.isEmpty())) {
      return trips;
    }
    System.out.println("You have not made any trips as a driver yet!\n");
    return null;
  }


  void driverInterfaceSimulate()
  {
    ;
  }

  public void removeTrip(Trip t) {
    if (trips != null) {
      trips.remove(t);
    }
  }

  public void driverInterface() {
    //implement UI for uber staff here
    //maybe a while(true) loop to mimic the state of the app
    //perform all operations here
    while (true)
    {
      System.out.println("-------------------------------------------------\nHello " + this.getName() + "!\nDo you want to \n1.See a list of trips\n2.View your earnings");
      Scanner input = new Scanner(System.in);
      int choice = input.nextInt();
      if (choice == 1)
      {
        System.out.println("Displaying all trips for " + this.getName());
        if (trips.size() == 0)
        {
          System.out.println("No trip information available.");
        }
        else
        {
          for (int i = 0; i < trips.size(); i++)
          {
            System.out.println(i+1 + ". ");
            trips.get(i).displayTrip();
          }
        }
      }
      else if (choice == 2)
      {
        System.out.println("You have earned a total of RS " + getTotalEarning());
      }
      else
      {
        System.out.println("Sorry, you did not enter any of the mentioned options. Please enter a correct option.");
      }
    }
  }


}