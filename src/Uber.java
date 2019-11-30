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

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to  Uber");
    System.out.println("Do you want to simulate the application? Y/N: ");
    String choice = sc.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
      Automate.simulateUber();
    }

    clearScreen();

    System.out.println("Ather");
  }

}
