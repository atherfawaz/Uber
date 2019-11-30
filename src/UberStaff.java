
import java.util.*;

public class UberStaff extends Person {

  private Boolean isFree = true;
  private List<String> pNIDList = new ArrayList<String>();

  public UberStaff() {
    super();
  }

  public UberStaff(String a, String b, String c, String d, String e, Boolean f, Account acc) {
    super(a, b, c, d, e, f, acc);
  }

  public void setIsFree(Boolean isFree_) {
    isFree = isFree_;
  }

  public Boolean getIsFree() {
    return isFree;
  }

  public void cancelRide(Trip tripId) {
    // TODO implement here
  }

  public void helpPassenger(String riderID) {
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
    return true;
  }

  public Boolean verifyDriverDetails(Driver passed) {
    return true;
    //connect with db and do subsequent checking
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

  public void addPerson(String p) {
    pNIDList.add(p);
  }

  public static List<UberStaff> recruitStaff(int staffcount) {
    List<UberStaff> ls = new ArrayList<>();
    for (int i = 0; i < staffcount; i++) {
      ls.add(new UberStaff());
    }
    return ls;
  }
}