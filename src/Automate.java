import java.util.ArrayList;
import java.util.List;

public class Automate {

  public static List<UberStaff> staff = UberStaff.recruitStaff(20);
  public static LeaderBoard leaderBoard = LeaderBoard.getInstance();
  public static List<Driver> drivers = new ArrayList<>();

  public static void simulateUber() {

    /*
    create uberstaff
    create people
    create drivers
    create passengers
    create cars
    create leaderboard
    create payment system
    */

    Passenger adan = new Passenger("Muhammad Adan", "35202-231231-5", "31-08-1998",
        "xenither@gmail.com",
        "0300-1231236", false, new Account("530018001923672", 0.0));

    Passenger hamza = new Passenger("Hamza Jawad", "35202-123123-2", "12-12-2019",
        "hamzajawad98@gmail.com", "0900-78601", false, new Account("530018001923422", 90.0));

    Driver ather = new Driver("Ather Fawaz", "21233-123124-5", "14-12-1098", "atherfawaz@gmail.com",
        "0900-6663321", true, new Account("530018001923662", 100.0));

    Driver java = new Driver("Java", "21233-123124-5", "14-12-1098", "java@gmail.com",
        "0900-6663321", true, new Account("530018001243662", 100.0));

    drivers.add(ather);
    drivers.add(java);
    hamza.callARide(drivers);
    adan.callARide(drivers);

  }
}
