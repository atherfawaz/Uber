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
    int which;
    while (true) {
      Uber.mySleep(1000);
      System.out.println("Loaded up the drivers and passengers...");
      System.out.println(
          "Which interface would you like to simulate? Enter the option number\n1. Passenger\n2. Driver\n3. Uberstaff\nAny other option will exit the simulator.");
      choice = input.nextInt();
      if (choice == 1) {
        which = Uber.myRand(0, Uber.passengers.size() - 1);
        Uber.passengers.get(which).passengerInterfaceSimulate();
      } else if (choice == 2) {
        Uber.drivers.get(0).driverInterfaceSimulate();
      } else if (choice == 3) {
        ;
      } else {
        break;
      }
    }
    Uber.mySleep(2000);
    System.out.println(
        "Reached the end of the automate function. Switching control back to Uber.Java...");

  }
}
