import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
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

  public static int myRand(int min, int max) {
    int rand = (int) (Math.random() * ((max - min) + 1)) + min;
    return rand;
  }

  public static void mySleep(long milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public static void main(String[] args) throws ParseException, InterruptedException, IOException {

    Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to Uber");
    System.out.println("Do you want to simulate the application? (Y/N): ");
    String choice = sc.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
      Automate.simulateUber();
      try {
        Automate.simulateUber();
      } catch (Exception e) {
        System.out.println(e);
      }

    }

    /*
    Console console = System.console();
    if(console == null && !GraphicsEnvironment.isHeadless()){
      String filename = Uber.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
      Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
    }else{
      System.out.println("Ather");
      Test.main(new String[0]);
      System.out.println("Program has ended, please type 'exit' to close the console");
    }
    */

    System.out.println("Sign up or login? (Sign Up / Login):");
    db_connectivity db = new db_connectivity();

    choice = sc.nextLine();

    Boolean fine = false;
    while (!fine) {

      if (choice.equalsIgnoreCase("sign up")) {
        fine = true;
        System.out.println("Sign up as a passenger or a driver? (Passenger / Driver):");
        String type = sc.nextLine();
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Enter your password");
        String pass = sc.nextLine();

        int retval = db.setlogin(username, pass, type);
        int retval2 = db.setPerson(Person.makePerson());

        if (choice.equalsIgnoreCase("Passenger")) {
          Person tempUser = Person.makePerson();
          Passenger newUser = new Passenger(tempUser);
          passengers.add(newUser);
          //transfer control. will run all functions as a passenger from this function within the class
          try {
            newUser.passengerInterface();
          } catch (Exception e) {
            System.out.println(e);
          }
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
          System.out.println("Enter your username: ");
          String username = sc.nextLine();
          System.out.println("Enter your password");
          String pass = sc.nextLine();
          String retval = db.getlogin(username, pass);
          if (retval.equalsIgnoreCase("x")) {
            System.out.println("Invalid login");
          } else if (retval.equalsIgnoreCase("p")) {
            //MAKE FUNCTIONS IN DB_CONNECTIVITY TO MAKE DRIVER AND PASSENGER OBJECTS AND RETURN THEM
            //Passenger newPassenger = new Passenger(db.getPassenger(username));
            //newPassenger.getInterface();
          } else if (retval.equalsIgnoreCase("d")) {
            //MAKE FUNCTIONS IN DB_CONNECTIVITY TO MAKE DRIVER AND PASSENGER OBJECTS AND RETURN THEM
            //Driver newDriver = new Driver(db.getDriver(username));
            //newDriver.driverInterface();
          }

        } else if (choice.equalsIgnoreCase("Driver")) {
          //fetch the required driver object from db and do a subsequent call to <object>.driverInterface
          System.out.println("Enter your username: ");
          String username = sc.nextLine();
          System.out.println("Enter your password");
          String pass = sc.nextLine();
          String retval = db.getlogin(username, pass);
          if (retval.equalsIgnoreCase("x")) {
            System.out.println("Invalid login");
          } else if (retval.equalsIgnoreCase("p")) {
            //MAKE FUNCTIONS IN DB_CONNECTIVITY TO MAKE DRIVER AND PASSENGER OBJECTS AND RETURN THEM
            //Passenger newPassenger = new Passenger(db.getPassenger(username));
            //newPassenger.getInterface();
          } else if (retval.equalsIgnoreCase("d")) {
            //MAKE FUNCTIONS IN DB_CONNECTIVITY TO MAKE DRIVER AND PASSENGER OBJECTS AND RETURN THEM
            //Driver newDriver = new Driver(db.getDriver(username));
            //newDriver.driverInterface();
          }
        } else {
          System.out.println("Incorrect choice. Re-enter your option: ");
          choice = sc.nextLine();
        }
      }

    }
  }
}
