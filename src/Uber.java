import java.util.ArrayList;
import java.util.List;

public class Uber {

  public static List<UberStaff> recruitStaff(int staffcount) {
    List<UberStaff> ls = new ArrayList<>();
    for (int i = 0; i < staffcount; i++) {
      ls.add(new UberStaff());
    }
    return ls;
  }

  public static void main(String[] args) {
    //parameters
    int staffcount = 5;

    //initial setup
    LeaderBoard leaderBoard = LeaderBoard.getInstance();
    List<UberStaff> uberstaff = recruitStaff(staffcount);

    System.out.println("Welcome to Uber");
  }
}
