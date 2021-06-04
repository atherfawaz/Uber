package com.company;

import java.sql.Connection;
import java.text.ParseException;
import java.util.Scanner;

public class Automate {

  public static Complaints c = new Complaints();

  static void simulateUber(String arg) throws ParseException, InterruptedException {

    /*
    create uberstaff
    create people
    create drivers
    create passengers
    create cars
    create leaderboard
    create payment system
    */

    ReadJSON.fetchDrivers(arg);
    ReadJSON.fetchPassengers(arg);
    ReadJSON.fetchTrips(arg);

    Scanner input = new Scanner(System.in);
    int choice;
    int which;
    while (true) {
      Uber.mySleep(1000);
      System.out.println("Loaded up the drivers, passengers, and trips for simulation...");
      System.out.println(
          "Which interface would you like to simulate? Enter the option number\n1. Passenger\n2. Driver\n3. Uberstaff\nAny other option will exit the simulator.");
      choice = input.nextInt();
      if (choice == 1) {
        which = Uber.myRand(0, Uber.passengers.size() - 1);
        Uber.passengers.get(which).passengerInterface();
      } else if (choice == 2) {
        which = Uber.myRand(0, Uber.drivers.size() - 1);
        Uber.drivers.get(which).driverInterfaceSimulate();
      } else if (choice == 3) {
        which = Uber.myRand(0, Uber.uberstaff.size() - 1);
        Uber.uberstaff.get(which).uberStaffInterface();
      } else {
        break;
      }
    }
    Uber.mySleep(2000);
    System.out.println(
        "Reached the end of the automate function. Switching control back to Uber.Java...");
  }
}
