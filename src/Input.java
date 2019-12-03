import java.text.ParseException;
import java.util.Scanner;

public class Input extends Thread {

  public void run() {
    System.out.println("[Passenger]\n1. Search for a ride\n2. Show all trips\n3. Show current trip info\n4. Request assistance\n5. Request cancellation" +
            "\n------------------------------\n[Driver]\n6. Request assistance");
    Scanner sc = new Scanner(System.in);
    String choice = sc.nextLine();
    if (choice.equalsIgnoreCase("1")) {
      Trip.tripchoice = 1;
      Trip.robj.interrupt();
    }
    else if (choice.equalsIgnoreCase("2")) {
      System.out.println("Showing all trips...");
      Trip.tripchoice = 2;
      Trip.robj.interrupt();
    }
    else if (choice.equalsIgnoreCase("3")) {
      System.out.println("Showing current trip info...");
      Trip.tripchoice = 3;
      Trip.robj.interrupt();
    }
    else if (choice.equalsIgnoreCase("4")) {
      System.out.println("Assisting Passenger...");
      Trip.tripchoice = 4;
      Trip.robj.interrupt();
    }
    else if (choice.equalsIgnoreCase("5")) {
      System.out.println("Cancelling ride...");
      Trip.tripchoice = 5;
      Trip.robj.interrupt();
    }
    else if (choice.equalsIgnoreCase("6"))
    {
      System.out.println("Assisting Driver...");
      Trip.tripchoice = 6;
      Trip.robj.interrupt();
    }
  }
}
