import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Uber {


  //initial setup
  public static LeaderBoard leaderBoard = LeaderBoard.getInstance();
  public static List<UberStaff> uberstaff = UberStaff.recruitStaff(5);
  public static List<Passenger> passengers = new ArrayList<Passenger>();
  public static List<Driver> drivers = new ArrayList<Driver>();
  public static PaymentSystem paymentSystem = new PaymentSystem();

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to Uber");
    System.out.println("Do you want to simulate the application? (Y/N): ");
    String choice = sc.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
      Automate.simulateUber();
    }

    clearScreen();  //ain't working apparently

    System.out.println("Sign up or login? (Sign Up / Login):");
    choice = sc.nextLine();

    Boolean fine = false;
    while (!fine) {

      if (choice.equalsIgnoreCase("sign up")) {
        fine = true;
        System.out.println("Sign up as a passenger or a driver? (Passenger / Driver):");
        choice = sc.nextLine();

        if (choice.equalsIgnoreCase("Passenger")) {
          Person tempUser = Person.makePerson();
          Passenger newUser = new Passenger(tempUser);
          passengers.add(newUser);
          //transfer control. will run all functions as a passenger from this function within the class
          newUser.passengerInterface();

        } else if (choice.equalsIgnoreCase("Driver")) {
          Person tempDriver = Person.makePerson();
          Driver newDriver = new Driver(tempDriver);

          //transfer control. will run all functions as a driver from this function within the class
          newDriver.driverInterface();
        }

      } else if (choice.equalsIgnoreCase("login")) {
        fine = true;
        System.out.println("Log in as a passenger or a driver? (Passenger / Driver):");
        choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Passenger")) {
          //fetch the required passenger object from db and do a subsequent call to <object>.passengerInterface
        } else if (choice.equalsIgnoreCase("Driver")) {
          //fetch the required driver object from db and do a subsequent call to <object>.driverInterface
        }
      } else {
        System.out.println("Incorrect choice. Re-enter your option: ");
        choice = sc.nextLine();
      }
    }

  }

}
