import java.util.ArrayList;
import java.util.List;

public class Automate {

  public static List<UberStaff> staff = UberStaff.recruitStaff(20);
  public static LeaderBoard leaderBoard = LeaderBoard.getInstance();

  public static void simulateUber() {

    //create uberstaff
    //create people
    //create drivers
    //create passengers
    //create cars
    //create leaderboard
    //create payment system
    //

    //make passenger
    /*Person adan = new Person("Muhammad Adan", "35202-231231-5", "31-08-1998", "xenither@gmail.com",
        "0300-1231236", false, new Account("530018001923672", 0.0));
    adan = Person.signUpAs(adan);

    //make driver
    Person ather = new Person("Muhammad Adan", "35202-231231-5", "31-08-1998", "xenither@gmail.com",
        "0300-1231236", false, new Account("530018001923672", 0.0));
    ather = Person.signUpAs(ather);
    */

    Passenger x = new Passenger();
    List<Driver> y = new ArrayList<>();
    y.add(new Driver());
    y.add(new Driver());
    x.callARide(y);
  }
}
