
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;


public class db_connectivity {

  Connection con;
  Statement stmt;

  db_connectivity() //cons
  {
    try {
      String s = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=temp1";
      con = DriverManager.getConnection(s, "new_user1", "123");

      stmt = con.createStatement();

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  void displayAll() {

    try {
      ResultSet rs = stmt.executeQuery("select * from my_table");
      while (rs.next()) {

        System.out.println(rs.getInt(1) + "  " + rs.getString(2));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    db_connectivity d1 = new db_connectivity();
    d1.displayAll();
  }
}