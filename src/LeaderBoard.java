import java.util.*;
import java.util.concurrent.TimeUnit;

public class LeaderBoard {

  int HIGHESTRATED = 5;
  int LOWESTRATED = 5;

  private static LeaderBoard obj;

  private List<Driver> drivers = new ArrayList<Driver>();

  private LeaderBoard() {
    ;
  }

  static LeaderBoard getInstance() {
    if (obj == null) {
      obj = new LeaderBoard();
      return obj;
    } else {
      return obj;
    }
  }

  class sortByRating implements Comparator<Driver> {

    public int compare(Driver a, Driver b) {

      return (int) (a.getRating() - b.getRating());
    }
  }

  class sortByTime implements Comparator<Driver> {

    public long getDateDifference(Date date1, Date date2, TimeUnit timeUnit) {
      long diffInMillies = date2.getTime() - date1.getTime();
      return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public int compare(Driver a, Driver b) {
     // return (int) getDateDifference(a.getStartDate(), b.getStartDate(), TimeUnit.MINUTES);
      return 0;
    }
  }

  public List<Driver> showHighestRated() {
    //change HIGHESTRATED attribute up in the variables to change maximum highest rated drivers allowed
    drivers.sort(new sortByRating());
    List<Driver> hrated = new ArrayList<>();
    for (int i = drivers.size() - 1; i < drivers.size() && i < HIGHESTRATED + drivers.size(); i++) {
      hrated.add(drivers.get(i));
    }
    return hrated;
  }

  public List<Driver> showOldest() {
    //change HIGHESTRATED attribute up in the variables to change maximum highest rated drivers allowed
    drivers.sort(new sortByTime());
    List<Driver> oldest = new ArrayList<>();
    for (int i = drivers.size() - 1; i < drivers.size() && i < HIGHESTRATED + drivers.size(); i++) {
      oldest.add(drivers.get(i));
    }
    return oldest;
  }

  public List<Driver> showLowestRated() {
    //change LOWESTRATED attribute up in the variables to change maximum highest rated drivers allowed
    drivers.sort(new sortByRating());
    List<Driver> lrated = new ArrayList<>();
    for (int i = 0; i < drivers.size() && i < HIGHESTRATED; i++) {
      lrated.add(drivers.get(i));
    }
    return lrated;
  }

  public List<Driver> showBonusEarners() {
    //TODO implement here;
    return null;
  }

  public void computeBonus() {
    //TODO implement here;
  }

}