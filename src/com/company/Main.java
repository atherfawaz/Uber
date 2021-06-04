package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {

  public static void main(String[] args) throws ParseException, InterruptedException, SQLException {
    Connection con = Database.connectToDB();
    assert con != null;
    Database.showTable(con, "Drivers");
    Database.showTable(con, "Passengers");
    Database.showTable(con, "Trips");

    //Automate.simulateUber("E:\\OneDrive\\College\\Spring 2021\\Advanced Programming\\Project\\Uber\\src\\com\\company\\");
  }
}
