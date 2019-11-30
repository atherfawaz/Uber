import java.util.*;
import java.util.concurrent.TimeUnit;

public class LeaderBoard {

  private final int HIGHESTRATED = 5;
  private final int LOWESTRATED = 5;

  private static LeaderBoard obj;

  private List<Driver> drivers = new ArrayList<>();

  private LeaderBoard() {
    //default constructor
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
    @Override
    public int compare(Driver a, Driver b) {

      return (int) (a.getRating() - b.getRating());
    }
  }

  class sortByBonus implements Comparator<Driver>{
    @Override
    public int compare(Driver a, Driver b)
    {
        float bonus1 = a.getPreviousRides().size() * a.getRating();
        float bonus2 = b.getPreviousRides().size() * b.getRating();

        return Math.round(bonus1-bonus2);

    }


  }

  class sortByTime implements Comparator<Driver> {

    public int getDateDifference(String date1, String date2) {
      //start comparing from year
      //return immediately if difference found
      //date format: DD-MM-YYYY

      int diff = 0;
      int year1 = Integer.parseInt(date1.substring(6, 9));
      int year2 = Integer.parseInt(date2.substring(6, 9));
      if(year1 - year2 == 0)
      {
        int month1 = Integer.parseInt(date1.substring(3,4));
        int month2 = Integer.parseInt(date2.substring(3,4));
        if (month1 - month2 == 0)
        {
          int d1 = Integer.parseInt(date1.substring(0,1));
          int d2 = Integer.parseInt(date2.substring(0,1));
          if (d1-d2 != 0)
          {
            diff = d1-d2;
          }
        }
        else
          diff = month1-month2;
      }
      else
        diff = year1-year2;

      return diff;
    }
    @Override
    public int compare(Driver a, Driver b) {
      return getDateDifference(a.getStartDate(), b.getStartDate());
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
    List<Driver> hrated = showHighestRated();
    hrated.sort(new sortByBonus());

    return hrated;
  }

  public void computeBonus() {
    List<Driver> hrated = showHighestRated();
    for(Driver d: hrated)
    {
      float bonus = d.getPreviousRides().size() * d.getRating();
      d.addToEarnings(bonus);
    }
  }

}