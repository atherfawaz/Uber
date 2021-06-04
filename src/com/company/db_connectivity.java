package com.company;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class
db_connectivity {

  Connection con;
  Statement stmt;

  db_connectivity() //cons
  {
    try {
      String s = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=UberDb";
      con = DriverManager.getConnection(s, "new_user1", "123");
      stmt = con.createStatement();

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  Person getPerson(String nID) {

    try {
      ResultSet rs = stmt.executeQuery("select * from Person join Account on Person.AccountNum = Account.accountNum where nationalId = '" + nID +"'");
      rs.next();
      Account a1 = new Account(rs.getString("AccountNum"), rs.getDouble("totalCredit"));
      Person p1 = new Person(rs.getString("name"), rs.getString("nationalId"), rs.getString("dateOfBirth"), rs.getString("email"),
          rs.getString("phoneNum"), rs.getBoolean("isDriver"), a1);
      return p1;
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  Driver getDriver(String nID)
  {

    try{
      Person p = getPerson(nID);
      Vehicle v = getDriverVehicle(nID);

      Driver  d = new Driver(p,v);
      ResultSet rs = stmt.executeQuery("select * from DriverDetails where DriverNID= '" + nID +"'");
      d.setRating(rs.getDouble(4));
      d.setStartDate(rs.getString(2));
      d.setTotalEarning(rs.getDouble(3));
      d.setTrips(getTrips(nID));

      return d;
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    return null;
  }
  Passenger getPassenger(String nID)
  {

    try{
      List<Trip> trips = new ArrayList<Trip>();
      
      ResultSet rs = stmt.executeQuery("Select * from Trip where Trip.passenger_cnic = " + nID + "'");
      while(rs.next())
      {
    	  trips.add(getTrip(rs.getInt("trip_ID")));
      }
      Person p = getPerson(nID);
      Passenger passenger = new Passenger(p);
      passenger.setTrips(getTrips(nID));

      return passenger;
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    return null;
  }

  int setPerson(Person passed) {
    try {
      String sql = "insert into Person (?, ?, ?, ?, ?,\r\n" +
              "    	      ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, passed.getName());
      statement.setString(2, passed.getNationalId());
      statement.setString(3, passed.getDateOfBirth());
      statement.setString(4, passed.getEmail());
      statement.setString(5, passed.getPhoneNum());
      statement.setBoolean(6, passed.getIsDriver());
      statement.setString(7, passed.getAccount().getAccountNum());
      int rowsUpdated = stmt.executeUpdate(sql);
      return rowsUpdated;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  int setPerson(String name, String nationalId, String dateOfBirth, String email, String phoneNum,
      Boolean isDriver, Account account) {
    try {
      String sql = "insert into Person (?, ?, ?, ?, ?,\r\n" +
          "    	      ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, name);
      statement.setString(2, nationalId);
      statement.setString(3, dateOfBirth);
      statement.setString(4, email);
      statement.setString(5, phoneNum);
      statement.setBoolean(6, isDriver);
      statement.setString(7, account.getAccountNum());
      int rowsUpdated = stmt.executeUpdate(sql);
      return rowsUpdated;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  int deletePerson(String nID) {
    try {
      String sql = "DELETE FROM Person WHERE username=?";

      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, nID);

      int rowsDeleted = statement.executeUpdate();

      return rowsDeleted;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  Account getAccount(String accID) {

    try {
      ResultSet rs = stmt.executeQuery("select * from Account where accountNum= '" + accID +"'");
      Account a1 = new Account(rs.getString(1), rs.getDouble(2));
      return a1;
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  int setAccount(String accID, double credit) {
    try {
      String sql = "insert into Account (?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, accID);
      statement.setDouble(2, credit);
      int rowsUpdated = stmt.executeUpdate(sql);
      return rowsUpdated;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  int updateAccount(String accID, double credit) {
    try {
      String sql = "update Account set totalCredit=? where accountNum=?";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setDouble(1, credit);
      statement.setString(2, accID);
      int rowsUpdated = stmt.executeUpdate(sql);
      return rowsUpdated;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  int deleteAccount(String accID) {
    try {
      String sql = "DELETE FROM Account WHERE accountNum=?";

      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, accID);

      int rowsDeleted = statement.executeUpdate();

      return rowsDeleted;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  Trip getTrip(int trip_id) {
	    try {
	      ResultSet rs = stmt.executeQuery("select * from Trip where tripID= '" + trip_id +"'");
	      rs.next();
	      Account a1 = new Account();
	      Vehicle v1 = new Vehicle(rs.getString(4), "", "", 0, "");
	      Driver d1 = new Driver("", rs.getString(5), "", "", "", false, a1);
	      Passenger p1 = new Passenger("", rs.getString(6), "", "", "", false, a1);
	      Trip t1 = new Trip(rs.getString(1), rs.getString(2), rs.getString(3), v1, d1, p1,
	          rs.getString(7), rs.getDouble(8));
	      return t1;
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	    return null;
	  }
  List<Trip> getTrips(String driverID)
  {
    List<Trip> trips = new ArrayList<Trip>();
    try {
      ResultSet rs = stmt.executeQuery("select * from Trip where driver_cnic= '" + driverID +"'");
      while(rs.next()) {
        trips.add(getTrip(rs.getInt("tripID")));
      }
      return trips;
    } catch (Exception e) {
    	//donothing
    }
    return null;
  }
//  List<Trip> getPassengerTrips(String nID)
//  {
//    List<Trip> trips = new ArrayList<Trip>();
//    try {
//      ResultSet rs = stmt.executeQuery("Select * from ((Trip join Vehicle on Trip.vehicle_r_num = Vehicle.registrationNum) join Person on Trip.passenger_cnic = Person.nationalId) join Account on Account.accountNum = Person.AccountNum where nationalId = '"+ nID + "'");
//
//      while(rs.next()) {
//    	Account passenger_account = new Account(rs.getString());
//        Passenger p1 = new Passenger(rs4.getString(1), rs.getString(6), "", "", "", false, a1);
//        Account driver_account = new Account();
//    	rs2 = stmt.executeQuery("select * from Vehicle where registrationNum= '" + rs.getString(2) +"'");
//        Trip t1 = new Trip(rs.getString(1), rs.getString(2), rs.getString(3), v1, d1, p1,
//                rs.getString(7), rs.getDouble(8));
//        trips.add(t1);
//      }
//      return trips;
//    } catch (Exception e) {
//      System.out.println(e);
//    }
//    return null;
//  }

  int setTrip(String startingPoint, String destination, String timeForSchedule, Vehicle veh,
              Driver _driv, Passenger _pass, String datetime, Double cost, int tripID) {
    try {
      String sql = "insert into Trip (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, startingPoint);
      statement.setString(2, destination);
      statement.setString(3, timeForSchedule);
      statement.setString(4, veh.getRegistrationNum());
      statement.setString(5, _driv.getNationalId());
      statement.setString(6, _pass.getNationalId());
      statement.setString(7, datetime);
      statement.setDouble(8, cost);
      statement.setInt(9, tripID);
      int rowsUpdated = stmt.executeUpdate(sql);
      return rowsUpdated;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  Vehicle getDriverVehicle(String driverNID)
  {
    try {
      ResultSet rs = stmt.executeQuery("select * from Vehicle where Owner_NID= '" + driverNID + "'");
      Vehicle v1;
      if(rs.getInt(6) == 1) {
        v1 = new Motorcycle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                rs.getString(5));
      }
      else if(rs.getInt(7) == 1)
      {
        v1 = new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                rs.getString(5),rs.getString(9));
      }
      else
      {
        v1 = new Rickshaw(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                rs.getString(5));
      }
      return v1;
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  Vehicle getVehicle(String reg_num) {

    try {
      ResultSet rs = stmt.executeQuery("select * from Vehicle where registrationNum= '" + reg_num + "'");
      Vehicle v1;
      if(rs.getInt(6) == 1) {
        v1 = new Motorcycle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                rs.getString(5));
      }
      else if(rs.getInt(7) == 1)
      {
        v1 = new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                rs.getString(5),rs.getString(9));
      }
      else
      {
        v1 = new Rickshaw(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                rs.getString(5));
      }
      return v1;
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  int setVehicle(String registrationNum, String condition, String make, Integer model,
      String manufacturer) {
    try {
      String sql = "insert into Vehicle (?, ?, ?, ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, registrationNum);
      statement.setString(2, condition);
      statement.setString(1, make);
      statement.setInt(2, model);
      statement.setString(1, manufacturer);
      int rowsUpdated = stmt.executeUpdate(sql);
      return rowsUpdated;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  int deleteVehicle(String registrationNum) {
    try {
      String sql = "DELETE FROM Vehicle WHERE registrationNum=?";

      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, registrationNum);

      int rowsDeleted = statement.executeUpdate();

      return rowsDeleted;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  //x means invalid details, d means driver login success
  //u means uberstaff login success, p means passenger login success
  String getlogin(String login_id, String password) {

    try {
      ResultSet rs = stmt.executeQuery("select * from LoginData where UserID= '" + login_id + "'" + "AND Pass= '" + password + "'");
      if (rs.next() == false) {
        return "x";
      } else {
        return rs.getString(3);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  int setlogin(String id, String password, String usertype) {
    try {
      String sql = "insert into LoginData (?, ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, id);
      statement.setString(2, password);
      statement.setString(1, usertype);
      int rowsUpdated = stmt.executeUpdate(sql);
      return rowsUpdated;
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }
  public static void main(String[] args)
  {
	  db_connectivity d1 = new db_connectivity();
	  Person p1 = d1.getPerson("3520201234344");
	  System.out.println(p1.getName());
	  System.out.println(p1.getEmail());
	  System.out.println(p1.getAccount().getAccountNum());
	  List<Trip> t1 = new ArrayList<>();
	  t1 = d1.getTrips("3520201234343");
	  System.out.println(p1.getAccount().getTotalCredit());
  }
}