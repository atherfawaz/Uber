
import javax.print.attribute.DateTimeSyntax;
import java.util.*;

public class Trip
{
    private String startingPoint;
    private String destination;
    private DateTimeSyntax timeForSchedule;
    private String vehicle;
    private String driverNId;
    private String passengerNId;
    private DateTimeSyntax dateTime;
    private Double totalCost;
    private String uberStaffNID;

    Trip() {}
    public Trip(String startingPoint, String destination, DateTimeSyntax timeForSchedule, String vehicle, String driverNId, String passengerNId, DateTimeSyntax dateTime, Double totalCost,String usNID) {
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.timeForSchedule = timeForSchedule;
        this.vehicle = vehicle;
        this.driverNId = driverNId;
        this.passengerNId = passengerNId;
        this.dateTime = dateTime;
        this.totalCost = totalCost;
        this.uberStaffNID = usNID;
    }
    public void setDriverNID(String a) {this.driverNId = a;}
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

    public DateTimeSyntax getTimeForSchedule() {
        return timeForSchedule;
    }

    public void setTimeForSchedule(DateTimeSyntax timeForSchedule) {
        this.timeForSchedule = timeForSchedule;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDriverNId() {
        return driverNId;
    }

    public void setDriverNId(String driverNId) {
        this.driverNId = driverNId;
    }

    public String getPassengerNId() {
        return passengerNId;
    }

    public void setPassengerNId(String passengerNId) { this.passengerNId = passengerNId; }

    public DateTimeSyntax getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTimeSyntax dateTime) {
        this.dateTime = dateTime;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public void startRide()
    {
        //TODO implement here
    }
    public void calculateFare()
    {
        //TODO implement here
    }
    public void calculateShortestRoute()
    {
        //TODO implement here
    }
    public List<String> getTripInfo()
    {
        //TODO implement here
        return null;
    }
    public Boolean checkRouteValidity(String pLoc, String dLoc)
    {
        //TODO implement here
        return null;
    }
    public Double calulatePeakHours()
    {
        //TODO implement here
        return null;
    }
    public void helpDriver()
    {

    }
}