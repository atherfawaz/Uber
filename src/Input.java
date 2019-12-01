import java.text.ParseException;
import java.util.Scanner;

public class Input extends Thread {

  public void run() {
    System.out.println("1. Request Assistance\n2. Cancel a ride");
    Scanner sc = new Scanner(System.in);
    int choice = sc.nextInt();
    if (choice == 1) {
      System.out.println("Assisting...");
      Trip.tripchoice = 1;
      Trip.robj.stop();
    } else if (choice == 2) {
      System.out.println("Cancelling...");
      Trip.tripchoice = 2;
      Trip.robj.stop();
    }
  }
}
