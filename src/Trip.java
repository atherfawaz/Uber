
import java.text.ParseException;
import javax.print.attribute.DateTimeSyntax;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Trip {

  private String startingPoint;
  private String destination;
  private String timeForSchedule;
  private Vehicle vehicle;
  private Driver driver;
  private Passenger passenger;
  private String dateTime;
  private Double totalCost;
  private static int tripID = 1;

  public static Riding robj = new Riding();
  public static Input iobj = new Input();
  public static int tripchoice = 0;

  Trip() {
    this.tripID = tripID;
    tripID++;
  }

  public Trip(String startingPoint, String destination, String timeForSchedule,
      Vehicle vehicle, Driver d, Passenger passenger, String dateTime,
      Double totalCost) {
    this.startingPoint = startingPoint;
    this.destination = destination;
    this.timeForSchedule = timeForSchedule;
    this.vehicle = vehicle;
    this.passenger = passenger;
    this.driver = d;
    this.dateTime = dateTime;
    this.totalCost = totalCost;
    this.tripID = tripID;
    tripID++;
  }

  public void giveRating(Integer integer) {
    driver.recalibrateRating((integer));
  }

  public void addDriver(Driver d) {
    this.driver = d;
  }

  public void addVehicle(Vehicle v) {
    this.vehicle = v;
  }

  public String getStartingPoint() {
    return startingPoint;
  }

  public void setStartingPoint(String startingPoint) {
    this.startingPoint = startingPoint;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getTimeForSchedule() {
    return timeForSchedule;
  }

  public void setTimeForSchedule(String timeForSchedule) {
    this.timeForSchedule = timeForSchedule;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public Driver getDriver() {
    return driver;
  }


  public Passenger getPassenger() {
    return passenger;
  }


  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public Double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(Double totalCost) {
    this.totalCost = totalCost;
  }

  public int getTripid() {
    return tripID;
  }

  public void setTotalCost(int tripID) {
    this.tripID = tripID;
  }

  public void startRide() throws ParseException, InterruptedException {
    System.out.println(
        "Your ride from " + startingPoint + " to " + destination + " on a " + this.vehicle
            .getModel() + " " + this.vehicle.getManufacturer() + " " + this.vehicle.getMake()
            + " with Driver " + this.driver.getName() + " is in progress.");

    //Thread.sleep(5000);
    //System.out.println("Ride completed. Please pay the driver " + totalCost + "\n");

    Riding ridingobj = new Riding();  //counter
    Input inputobj = new Input();     //input
    ridingobj.start();
    inputobj.start();
    ridingobj.join();
    //inputobj.join();
    if (tripchoice == 1) {
      passenger.requestAssistance();
    } else if (tripchoice == 2) {
      passenger.requestCancellation();
    } else {
      System.out.println("Ride completed. Please pay the driver " + totalCost + "\n");
    }
  }

  public void calculateFare() {
    double baseFair = 100;
    double tripDistance = (Math.random() * ((30 - 1) + 1)) + 1;
    double peakFactor = calculatePeakHours();
    this.totalCost = (double) Math.round(peakFactor * (baseFair + tripDistance * 25));
  }

  public void calculateShortestRoute() {
    ;
  }

  public List<String> getTripInfo() {
    List<String> tripInfo = new ArrayList<>();
    tripInfo.add(this.dateTime);
    tripInfo.add(this.startingPoint);
    tripInfo.add(this.destination);
    tripInfo.add(this.driver.getName());
    tripInfo.add(this.passenger.getName());
    tripInfo.add(Double.toString(this.totalCost));
    tripInfo.add(Integer.toString(this.tripID));
    return tripInfo;
  }

  public Boolean checkRouteValidity(String pLoc, String dLoc) {
    if (!(pLoc.equalsIgnoreCase(dLoc))
        && (startingPoint.equalsIgnoreCase("Eden Avenue") || startingPoint
        .equalsIgnoreCase("DHA Phase 1") || startingPoint.equalsIgnoreCase("Bhatta Chowk")
        || startingPoint.equalsIgnoreCase("Model Town"))
        && (destination.equalsIgnoreCase("Eden Avenue") || destination
        .equalsIgnoreCase("DHA Phase 1") || destination.equalsIgnoreCase("Bhatta Chowk")
        || destination.equalsIgnoreCase("Model Town"))) {
      return true;
    }
    return false;
  }

  public Double calculatePeakHours() {
    double peakFactor;
    String currTime = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    if (currTime.substring(11, 12) == "01" || currTime.substring(11, 12) == "02"
        || currTime.substring(11, 12) == "03") {
      peakFactor = 1.2;
    } else if (currTime.substring(11, 12) == "04" || currTime.substring(11, 12) == "05"
        || currTime.substring(11, 12) == "06") {
      peakFactor = 1.3;
    } else if (currTime.substring(11, 12) == "07" || currTime.substring(11, 12) == "08"
        || currTime.substring(11, 12) == "09") {
      peakFactor = 1.4;
    } else if (currTime.substring(11, 12) == "10" || currTime.substring(11, 12) == "11"
        || currTime.substring(11, 12) == "12") {
      peakFactor = 1.5;
    }
    return 1.0;
  }

  public void helpDriver() {
    boolean loopVar = true;
    int randIndex;
    while (loopVar) {
      randIndex = (int) (Math.random() * ((Uber.uberstaff.size() - 1) + 1));
      if (Uber.uberstaff.get(randIndex).getIsFree()) {
        Uber.uberstaff.get(randIndex).setIsFree(false);
        Uber.uberstaff.get(randIndex).helpDriver(this.getDriver());
        loopVar = false;
      }
    }
  }

  public void helpPassenger() {
    boolean loopVar = true;
    while (loopVar) {
      int randIndex = (int) (Math.random() * ((Uber.uberstaff.size() - 1) + 1));
      if (Uber.uberstaff.get(randIndex).getIsFree()) {
        Uber.uberstaff.get(randIndex).setIsFree(false);
        Uber.uberstaff.get(randIndex).helpPassenger(this.getPassenger());
        loopVar = false;
      }
    }
  }
}