
import java.util.*;
//abc

public class Driver extends Person {

  private Boolean isFree = true;
  private String startDate;
  private double totalEarning;
  private float rating;
  private List<Trip> trips = new ArrayList<>();
  private Vehicle vehicle;

  Driver() {
    vehicle = new Vehicle();
    //
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
    System.out.println(amount + "has been added to your total earnings.");
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
    return true;
  }

  public void setVehicle()
  {
    vehicle = new Car("219861293","new","Camry",2009,"Toyota","sedan");
  }

  public void displayTrips() {
    for (Trip t : trips) {
      System.out.println("Trip " + t + 1 + " was conducted on " + t.getDateTime());
      System.out.println(
          "It began at " + t.getStartingPoint() + " and ended at " + t.getDestination() + ".");
      System.out.println(
          "The total money you the ride cost the passenger was " + t.getTotalCost() + "\n");
    }
  }

  public void requestAssistance() //unsure about implementation
  {
    System.out.println("Requesting assistance from the uber staff assigned.");
    trips.get(trips.size() - 1).helpDriver();
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

  void driverInterface()
  {
    ;
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

}