import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Automate {

  static void simulateUber() throws ParseException, InterruptedException {

    /*
    create uberstaff
    create people
    create drivers
    create passengers
    create cars
    create leaderboard
    create payment system
    */

    ReadJSON.fetchDrivers();
    ReadJSON.fetchPassengers();

    Scanner input = new Scanner(System.in);
    int choice;
    while (true) {
      Uber.mySleep(1000);
      System.out.println("Loaded up the drivers and passengers...");
      System.out.println("Which interface would you like to simulate? Enter the option number\n1. Passenger\n2. Driver\n3. Uberstaff\nAny other option will exit the simulator.");
      choice = input.nextInt();
      if (choice == 1) {
        int which = Uber.myRand(0,Uber.passengers.size()-1);
        Uber.passengers.get(which).passengerInterfaceSimulate();
      }
      else if (choice == 2) {
        Uber.drivers.get(0).driverInterfaceSimulate();
      }
      else if (choice == 3)
      {
        ;
      }
      else break;
    }
    Uber.mySleep(2000);
    System.out.println("Reached the end of the automate function. Switching control back to Uber.Java...");

    /* LEGACY CODE
    Passenger adan = new Passenger("Muhammad Adan", "35202-231231-5", "31-08-1998",
        "xenither@gmail.com",
        "0300-1231236", false, new Account("530018001923672", 0.0));

    Passenger hamza = new Passenger("Hamza Jawad", "35202-123123-2", "12-12-2019",
        "hamzajawad98@gmail.com", "0900-78601", false, new Account("530018001923422", 90.0));

    Driver ather = new Driver("Ather Fawaz", "21233-123124-5", "14-12-1098", "atherfawaz@gmail.com",
        "0900-6663321", true, new Account("530018001923662", 100.0));

    Driver java = new Driver("Java", "21233-123124-5", "14-12-1098", "java@gmail.com",
        "0900-6663321", true, new Account("530018001243662", 100.0));

    Uber.drivers.add(ather);
    Uber.drivers.add(java);
    Uber.passengers.add(hamza);
    Uber.passengers.add(adan);
    hamza.callARide(Uber.drivers);
    hamza.initiatePayment("notcashlul",hamza.getCurrentRide().getTotalCost(),hamza.getCurrentRide().getDriver());
    adan.callARide(Uber.drivers);

   */

  }
}
