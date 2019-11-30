import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Uber {

  public static void main(String[] args) {

    //parameters
    final int STAFFCOUNT = 5;
    final int HIGHESTRATED = 5;

    //initial setup
    LeaderBoard leaderBoard = LeaderBoard.getInstance();
    List<UberStaff> uberstaff = UberStaff.recruitStaff(STAFFCOUNT);
    Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to  Uber");
    System.out.println("Do you want to simulate the application? Y/N: ");
    String choice = sc.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
      Automate.simulateUber();
    } else {
      System.out.println("Exiting now...");
    }
  }

  Date x = new Date("");

}
