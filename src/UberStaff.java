
import java.util.*;

public class UberStaff extends Person {

  private Boolean isFree = true;
  List<Complaint> cList = new ArrayList<>();


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

  public void cancelRide(Trip tripId)
  {
      for (int i = 0; i < Uber.passengers.size(); i++)
      {
          if (tripId.getDriver().equals(Uber.passengers.get(i)))
          {
              Uber.passengers.get(i).removeTrip(tripId);
          }
      }

      for (int i = 0; i < Uber.drivers.size(); i++)
      {
          if (tripId.getDriver().equals(Uber.drivers.get(i)))
          {
              Uber.drivers.get(i).removeTrip(tripId);
          }
      }
  }

  public void helpPassenger(Passenger p)
  {
      System.out.println("HELPLINE FOR PASSENGERS");
      operatorAssistance((p.getName()));
  }

  public void helpDriver(Driver d)
  {
      System.out.println("HELPLINE FOR DRIVERS");
      operatorAssistance(d.getName());
  }

  public void operatorAssistance(String ID)
  {
      System.out.println("Our operator " + super.getName() + " is on call with " + ID);
  }

  public void refundPassenger(Passenger passenger, double amount)
  {
      passenger.creditAccount(amount);
  }

  public void notifyVehicleCondition(Vehicle v) {
      System.out.println("Condition of " + v.getRegistrationNum() + " is: " + v.getCondition());
  }

  public Boolean verifyDriverDetails(String details) { //this will receive the person's name
    if (checkIdentity(details) && checkCriminalRecord(details) && checkConflictOfInterest((details)))
        return true;
    else
        return false;
  }

  public Boolean verifyDriverDetails(Driver passed) { //don't understand this BY USMAN
    return true;
    //connect with db and do subsequent checking
  }

  public Boolean verifyVehicleDetails(Vehicle vehicleDetails)
  {
      System.out.println("Verifying that " + vehicleDetails.getRegistrationNum() + "in the national vehicle database");
      System.out.println(vehicleDetails.getRegistrationNum() + " has not been involved in any crimes");
      return true;
  }

  public void addDriver(Driver d) //Before calling this function, check verifyDriverDetails
  {
      Uber.drivers.add(d);

  }


  public Boolean checkConflictOfInterest(String nationalID) {
    for (int i = 0; i < Uber.drivers.size(); i++)
    {
        if (Uber.drivers.get(i).getName().equalsIgnoreCase((nationalID)))
        {
            return false;
        }
    }
    return true;
  }

  public Boolean checkIdentity(String nationalID)
  {
      System.out.println("Verifying that the applicant is in the national database");
      System.out.println("Applicant's credentials exist in NADRA");
      return true;
  }

  public Boolean checkCriminalRecord(String nationalID)
  {
      System.out.println("Verifying that the applicant does not have any criminal history");
      System.out.println("Clearance received from authorities concerned");
      //Assuming that NADRA and authorities concerned intimate us that
      //applicant does not have a criminal history
      return true;
  }

  public static List<UberStaff> recruitStaff(int staffcount) {
    List<UberStaff> list = new ArrayList<>();
    for (int i = 0; i < staffcount; ++i) {
      list.add(new UberStaff());
    }
    return list;
  }

  public void addComplaint(String complaintDescription, Driver driver, Passenger passenger)
  {
      Complaint c = new Complaint(complaintDescription, driver, passenger);
      cList.add(c);
  }
  public void viewComplaints()
  {
      System.out.println("Staff assigned: " + this.getName());
      for (int i = 0; i < cList.size(); i++)
      {
          cList.get(i).viewComplaint();
      }
  }
}