package com.Uber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;

public class Database {

  private static final String privateKey = "uber4sec2ret0key";

  static void showTable(Connection con, String tableName) throws SQLException {
    /*
     * This function prints the Users table to the console
     */
    System.out.println("Printing table: " + tableName);
    Statement st = con.createStatement();
    String str = "SELECT * FROM " + tableName;
    ResultSet rs = st.executeQuery(str);
    ResultSetMetaData rsmd = rs.getMetaData();
    int column_count = rsmd.getColumnCount();
    while (rs.next()) {
      for (int i = 1; i <= column_count; i++) {
        System.out.print(" " + rs.getString(i));
      }
      System.out.println(" ");
    }
  }

  static boolean completeTrip(Connection con, String passengerEmail, String driverEmail, String pickup,
      String destination, String fare, String uniqueID) throws SQLException {
    PreparedStatement stmt = con.prepareStatement("INSERT INTO Trips VALUES(?,?,?,?,?,?,?)");
    stmt.setInt(1, 1);
    stmt.setString(2, driverEmail);
    stmt.setString(3, passengerEmail);
    stmt.setString(4, pickup);
    stmt.setString(5, destination);
    stmt.setString(6, fare);
    stmt.setString(7, uniqueID);
    int i = stmt.executeUpdate();
    if (i == 0) {
      return false;
    }
    System.out.println(i + " records inserted.");
    return true;
  }

  static JSONObject getPassengerDetails(Connection con, String email) throws SQLException, JSONException {
    JSONObject json = new JSONObject();
    PreparedStatement stmt = con.prepareStatement("SELECT * FROM Passengers WHERE Email=?");
    stmt.setString(1, email);
    ResultSet rs = stmt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int column_count = rsmd.getColumnCount();
    while (rs.next()) {
      for (int i = 1; i <= column_count; i++) {
        String name = rsmd.getColumnName(i);
        json.put(name, rs.getString(i));
        // System.out.print(" " + rs.getString(i));
      }
      // System.out.println(" ");
    }
    return json;
  }

  static int getAccountBalance(Connection con, String email) throws SQLException {
    PreparedStatement stmt = con.prepareStatement("SELECT Balance FROM Passengers WHERE Email=?");
    stmt.setString(1, email);
    ResultSet rs = stmt.executeQuery();
    int bal = Integer.parseInt(rs.getString(1));
    System.out.println("Balance from DB: " + bal);
    return bal;
  }

  static boolean registerUser(Connection con, String name, String password, String email) {
    /*
     * This function adds a User to the table
     */
    // name = AES.encrypt(name, privateKey);
    // password = AES.encrypt(password, privateKey);

    try {
      if (loginUser(con, email, password))
        return false;

      PreparedStatement stmt = con.prepareStatement("INSERT INTO Passengers VALUES(?,?,?,?,?)");
      stmt.setInt(1, 1);
      stmt.setString(2, name);
      stmt.setString(3, password);
      stmt.setString(4, email);
      stmt.setInt(5, 0);
      int i = stmt.executeUpdate();
      if (i == 0) {
        return false;
      }
      System.out.println(i + " records inserted.");
      // printing entries
      // showTable(con, "Drivers");
      return true;
    } catch (Exception ex) {
      System.out.println("Database Error occurred: " + ex);
    }
    return true;
  }

  static boolean loginUser(Connection con, String email, String password) throws SQLException {
    PreparedStatement stmt = con.prepareStatement("SELECT Name from Passengers WHERE Email=? AND Password=?");
    stmt.setString(1, email);
    stmt.setString(2, password);
    ResultSet result = stmt.executeQuery();
    if (!result.isBeforeFirst()) {
      System.out.println("No person with that credentials exists in the system.");
      return false;
    } else {
      System.out.println("User found.");
      return true;
    }
  }

  static Connection connectToDB() {
    try {
      Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
      String path = "src/UberDB.accdb";
      String ur = "jdbc:ucanaccess://" + path;
      return DriverManager.getConnection(ur);
    } catch (Exception x) {
      System.out.println("Database Error occurred: " + x);
      JOptionPane.showMessageDialog(null, x);
      return null;
    }
  }

}
