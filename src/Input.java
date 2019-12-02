import java.text.ParseException;
import java.util.Scanner;

public class Input extends Thread {

  public void run() {
    System.out.println("[Passenger]\n1. Search for a ride\n2. Show all trips\n3. Show current trip info\n4. Request assistance\n5. Request cancellation" +
            "\n------------------------------\n[Driver]\n6. Request assistance");
    Scanner sc = new Scanner(System.in);
    int choice = sc.nextInt();
    if (choice == 1) {
      Trip.tripchoice = 1;
      Trip.robj.stop();
    }
    else if (choice == 2) {
      System.out.println("Showing all trips...");
      Trip.tripchoice = 2;
      Trip.robj.stop();
    }
    else if (choice == 3) {
      System.out.println("Showing current trip info...");
      Trip.tripchoice = 3;
      Trip.robj.stop();
    }
    else if (choice == 4) {
      System.out.println("Assisting Passenger...");
      Trip.tripchoice = 4;
      Trip.robj.stop();
    }
    else if (choice == 5) {
      System.out.println("Cancelling ride...");
      Trip.tripchoice = 5;
      Trip.robj.stop();
    }
    else if (choice == 6)
    {
      System.out.println("Assisting Driver...");
      Trip.tripchoice = 6;
      Trip.robj.stop();
    }
  }
}
