import java.util.ArrayList;
import java.util.List;

public class Uber {

  public static void main(String[] args) {

    //parameters
    int STAFFCOUNT = 5;
    int HIGHESTRATED = 5;

    //initial setup
    LeaderBoard leaderBoard = LeaderBoard.getInstance();
    List<UberStaff> uberstaff = UberStaff.recruitStaff(STAFFCOUNT);

    System.out.println("Welcome to Uber");
  }
}
