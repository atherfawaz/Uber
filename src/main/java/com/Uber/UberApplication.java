package com.Uber;

import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UberApplication {

  public static void main(String[] args) throws SQLException {
    SpringApplication.run(UberApplication.class, args);
    Connection con = Database.connectToDB();
    assert con != null;
    Database.showTable(con, "Drivers");
  }
}
