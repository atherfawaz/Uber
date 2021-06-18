package com.Uber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

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

  static boolean registerUser(Connection con, String name, String password, String email) {
    /*
     * This function adds a User to the table
     */
    // name = AES.encrypt(name, privateKey);
    // password = AES.encrypt(password, privateKey);

    try {
      PreparedStatement stmt = con.prepareStatement("INSERT INTO Passengers VALUES(?,?,?,?,?)");
      // stmt.setInt(1, );
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

  static Boolean validateUsername(String username) {
    return !username.isEmpty() && username.length() <= 20;
  }

  static Boolean validatePassword(String password) {
    return password.length() >= 4 && password.length() <= 10;
  }

  static boolean loginUser(Connection con, String email, String password) throws SQLException {
    PreparedStatement stmt = con.prepareStatement("SELECT Name from Passengers WHERE Email=? AND Password=?");
    // stmt.setString(1, AES.encrypt(username, privateKey));
    // stmt.setString(2, AES.encrypt(password, privateKey));
    ResultSet result = stmt.executeQuery();
    if (!result.isBeforeFirst()) {
      System.out.println("No person with that credentials exists in the system.");
      return false;
    } else {
      System.out.println("User found.");
      return true;
    }
  }

  static boolean changeUsername(Connection con, String oldUsername, String password, String newUsername)
      throws SQLException {
    boolean loggedIn = loginUser(con, oldUsername, password);
    if (loggedIn) {
      PreparedStatement stmt = con.prepareStatement("UPDATE Users SET Username=? WHERE Username=?");
      stmt.setString(1, AES.encrypt(newUsername, privateKey));
      stmt.setString(2, AES.encrypt(oldUsername, privateKey));
      int i = stmt.executeUpdate();
      System.out.println(i + " records updated.");
      System.out.println("Username updated.");
      // showTable(con);
      return true;
    } else {
      System.out.println("Username not updated.");
      // showTable(con);
      return false;
    }
  }

  static boolean changePassword(Connection con, String username, String password, String newPassword)
      throws SQLException {
    boolean loggedIn = loginUser(con, username, password);
    if (loggedIn) {
      PreparedStatement stmt = con.prepareStatement("UPDATE Users SET Password=? WHERE Username=?");
      stmt.setString(1, AES.encrypt(newPassword, privateKey));
      stmt.setString(2, AES.encrypt(username, privateKey));
      int i = stmt.executeUpdate();
      System.out.println(i + " records updated.");
      System.out.println("Password updated.");
      // showTable(con);
      return true;
    } else {
      System.out.println("Password not updated.");
      // showTable(con);
      return false;
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
