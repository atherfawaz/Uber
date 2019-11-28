
import java.util.*;

public class UberStaff extends Person {

    public UberStaff() {
        super();
    }

  public UberStaff(String a, String b, Date c, String d, String e, Boolean f, Account acc) {
    super(a, b, c, d, e, f,acc);
  }

  public void cancelRide(Trip tripId) {
    // TODO implement here
  }

  public void helpRider(String riderID) {
    // TODO implement here
  }

  public void helpDriver(String driverID) {
    // TODO implement here
  }

  public void operatorAssistance() {
    // TODO implement here
  }

  public void refundPassenger(String passengerID, double amount) {
    // TODO implement here
  }

  public void notifyVehicleCondition() {
    // TODO implement here
  }

  public Boolean verifyDriverDetails(List<String> details) {
    // TODO implement here
    return null;
  }

  public Boolean verifyVehicleDetails(Vehicle vehicleDetails) {
    // TODO implement here
    return null;
  }

  public void addDriver() {
    // TODO implement here
  }

  public void rejectDriver() {
    // TODO implement here
  }

  public Boolean checkConflictOfInterest(String nationalID) {
    // TODO implement here
    return null;
  }

  public Boolean checkIdentity(String nationalID) {
    // TODO implement here
    return null;
  }

  public Boolean checkCriminalRecord(String nationalID) {
    // TODO implement here
    return null;
  }

  public Boolean checkCarDetails() {
    // TODO implement here
    return null;
  }
}