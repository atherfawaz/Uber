import java.util.*;

public class LeaderBoard {

  public static LeaderBoard obj;

  private List<Driver> drivers = new ArrayList<Driver>();

  private LeaderBoard() {
    ;
  }

  public static LeaderBoard getInstance() {
    if (obj == null) {
      obj = new LeaderBoard();
      return obj;
    } else {
      return obj;
    }
  }


  public List<Driver> showHighestRated() {
    //TODO implement here;
    return null;
  }

  public List<Driver> showOldest() {
    //TODO implement here;
    return null;
  }

  public List<Driver> showLowestRated() {
    //TODO implement here;
    return null;
  }

  public List<Driver> showBonusEarners() {
    //TODO implement here;
    return null;
  }

  public void computeBonus() {
    //TODO implement here;
  }

}