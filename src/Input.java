import java.text.ParseException;
import java.util.Scanner;

public class Input extends Thread {
  public static int val = 0;
  public void run() {

    System.out.println("1. Request Assistance\n2. Cancel a ride");
    Scanner sc = new Scanner(System.in);
    int choice = sc.nextInt();
    if (choice == 1) {
      System.out.println("Assiting...");
      val = 1;
    } else if (choice == 2) {
      System.out.println("Cancelling...");
      val = 2;
    }
  }

  public void startWrapper(Riding ridingobj, Passenger passenger) throws ParseException {
    this.start();
    System.out.println("Returned");
    if (val == 1) {
      ridingobj.interrupt();
      passenger.requestAssistance();
    } else if(val == 2) {
      ridingobj.interrupt();
      passenger.requestCancellation();
    }
  }
}
