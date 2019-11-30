
import javax.print.attribute.DateTimeSyntax;
import java.util.*;

public class Trip {

  private String startingPoint;
  private String destination;
  private String timeForSchedule;
  private String vehicle;
  private Driver driver;
  private Passenger passenger;
  private String dateTime;
  private Double totalCost;

  Trip () { }

  public Trip(String startingPoint, String destination, String timeForSchedule,
      String vehicle, Driver d, Passenger passenger, String dateTime,
      Double totalCost) {
    this.startingPoint = startingPoint;
    this.destination = destination;
    this.timeForSchedule = timeForSchedule;
    this.vehicle = vehicle;
    this.passenger = passenger;
    this.driver = null;
    this.dateTime = dateTime;
    this.totalCost = totalCost;
  }

  public void setRating(Integer integer)
  {
      driver.recalibrateRating((integer));
  }

  public void addDriver (Driver d)
  {
      this.driver = d;
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

  public String getVehicle() {
    return vehicle;
  }

  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }

  public String getDriverNId() {
    return driver.getNationalId();
  }


  public String getPassengerNId() {
    return passenger.getNationalId();
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

  public void startRide() {
    //TODO implement here
  }

  public void calculateFare() {
    //TODO implement here
  }

  public void calculateShortestRoute() {
    //TODO implement here
  }

  public List<String> getTripInfo() {
    //TODO implement here
    return null;
  }

  public Boolean checkRouteValidity(String pLoc, String dLoc) {
    //TODO implement here
    return null;
  }

  public Double calulatePeakHours() {
    //TODO implement here
    return null;
  }

  public void helpDriver() {
    ;//random index gen?
  }

  public void helpPassenger() {
    ;//random index gen?
  }
}