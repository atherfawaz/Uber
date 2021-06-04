package com.company;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.text.*;

public class Passenger extends Person {

  private Boolean isOnTrip = false;
  private List<Trip> trips = new ArrayList<>();

  public Passenger(String name, String nationalId, String dateOfBirth, String email,
      String phonenumber, Boolean isdriver, Account account) {
    super(name, nationalId, dateOfBirth, email, phonenumber, isdriver, account);

  }

  Passenger() {

  }

  Passenger(Person passed) {
    super(passed);
    //pushed
  }

  public Boolean getStatus() {
    return isOnTrip;
  }

  public void setStatus(Boolean isOnTrip) {
    this.isOnTrip = isOnTrip;
  }

  public double getExpenditure() {
    return trips.get(trips.size() - 1).getTotalCost();
  }

  public Boolean creditAccount(Double Amount) {
    account.addCredit(Amount);
    return true;
  }

  public void initiatePayment(String pType, Double amount, Driver d) {
    if (pType.equalsIgnoreCase("cash")) {
      System.out.println("You have successfully paid the driver.");
    } else {
      makePayment(amount, d);
    }
    Uber.mySleep(2000);
    return;
  }

  public Boolean rateDriver(Integer r) {
    trips.get(trips.size() - 1).giveRating(r);
    if (r == 1)
    {
      String complaintText = new String ();
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter report text: ");
      complaintText = scanner.nextLine();
      complaintText = scanner.nextLine();
      Complaint complaint = new Complaint(complaintText,trips.get(trips.size() - 1).getDriver(),this);
      Automate.c.cList.add(complaint);
    }
    return true;
  }

  public Boolean requestCancellation() throws ParseException {
    if (isOnTrip) {
      Trip lastTrip = trips.get(trips.size() - 1);
      String scheduleTime = lastTrip.getTimeForSchedule().substring(11, 18);
      SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
      Date start = format.parse(scheduleTime);
      Date end = format.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
      if (end.getTime() - start.getTime() < 20 * 10000) {
        return true;
      } else {
        return false;
      }
    } else {
      System.out.println(
          "Sorry, a trip has not been initialized by you! You can only request cancellation when a trip has been initialized.");
      return false;
    }
  }

  public Boolean makePayment(Double amount, Driver d) {
    if (account.debitAccount(amount)) {
      if (Uber.paymentSystem.makePayment(amount, d)) {
        System.out.println("You have successfully paid the driver.");
        return true;
      }
    }
    System.out.println("Pay via cash!");
    Uber.mySleep(2000);
    System.out.println("You have successfully paid the driver.");
    return false;
  }

  public void setTrips(List<Trip> trips) {
    this.trips = trips;
  }

  public void requestAssistance() {
    System.out.println("Passenger " + this.getName() + " is requesting assistance from the Uber staff assigned.");
    if (isOnTrip) {
      trips.get(trips.size() - 1).helpPassenger();
    } else {
      System.out.println(
          "You are currently not on a trip! Please request assistance while a trip is ongoing.");
    }
  }

  public void addRide(Trip trip) {
    trips.add(trip);
  }

  public void displayRides() {
    if (!(trips.isEmpty())) {
      for (int t = trips.size() - 1; t >= 0; t--) {
        System.out.println("Trip " + t + 1 + " was conducted on " + trips.get(t).getDateTime());
        System.out.println(
            "It began at " + trips.get(t).getStartingPoint() + " and ended at " + trips.get(t)
                .getDestination() + ".");
        System.out.println(
            "The total money the ride you passenger was " + trips.get(t).getTotalCost()
                + "\n");
      }
    } else {
      System.out.println("You have not made any trips with Uber yet.");
    }
  }

  public void displayCurrentRide() {
    if (isOnTrip) {
      System.out.println(
          "Trip " + (trips.size() - 1) + " was conducted on " + trips.get((trips.size() - 1))
              .getDateTime());
      System.out.println(
          "It began at " + trips.get((trips.size() - 1)).getStartingPoint() + " and ended at "
              + trips.get((trips.size() - 1))
              .getDestination() + ".");
      System.out.println(
          "The total money the ride cost you was " + trips.get((trips.size() - 1)).getTotalCost()
              + "\n");
    } else {
      System.out.println("You are currently not on a trip!");
    }
  }

  public Trip getCurrentRide() {
    return trips.get(trips.size() - 1);
  }

  public void scheduleRide() throws ParseException, InterruptedException {
    Boolean validTrip = false;
    Boolean validLoc = false;
    String destination;
    String startingPoint;
    String vType="";
    int num;
    while (!(validTrip)) {
      Uber.clearScreen();
      Scanner input = new Scanner(System.in);
      System.out.println(
              "Enter your starting point number:\n 1. Eden Avenue\n 2. Bhatta Chowk\n 3. DHA Phase 1\n 4. Model Town\n");
      num = input.nextInt();
      if (num == 1) {
        startingPoint = "Eden Avenue";
      } else if (num == 2) {
        startingPoint = "Bhatta Chowk";
      } else if (num == 3) {
        startingPoint = "DHA Phase 1";
      } else {
        startingPoint = "Model Town";
      }
      System.out.println(
              "Enter your destination number:\n 1. Eden Avenue\n 2. Bhatta Chowk\n 3. DHA Phase 1\n 4. Model Town\n");
      num = input.nextInt();
      if (num == 1) {
        destination = "Eden Avenue";
      } else if (num == 2) {
        destination = "Bhatta Chowk";
      } else if (num == 3) {
        destination = "DHA Phase 1";
      } else {
        destination = "Model Town";
      }
      System.out.println("Enter the date and time you would like to travel at, in the format <dd-MM-yyyy HH:mm:ss> exactly:");
      input.nextLine();
      String schedTime = input.nextLine();
      Trip trip = new Trip(startingPoint, destination, schedTime, null, null, this, schedTime, null);
      if (trip.checkRouteValidity(startingPoint, destination)) {
        validTrip = true;
        trip.calculateShortestRoute();
        trip.calculateFare();
        System.out.println("The estimated fair for your ride is "+ trip.getTotalCost() + ".");
        Uber.mySleep(1500);
        Boolean wrongVType = true;
        while (wrongVType) {
            System.out.println("Enter the vehicle type you would like to travel on:\n- Car\n- Motorcycle\n- Rickshaw");
            vType = input.nextLine();
            if (vType.equalsIgnoreCase("Car") || vType.equalsIgnoreCase("Motorcycle") || vType.equalsIgnoreCase("Rickshaw"))
            {
              wrongVType = false;
            }
            else
            {
              System.out.println("You have entered a wrong vehicle type. Kindly enter a correct option.");
            }
          }
        //
        //
        //
        //
        //DO THE SCHEDULING HERE
        System.out.println("Your ride has successfully been scheduled. A driver will automatically reach " + trip.getStartingPoint() + " at " + schedTime);
        Waiting obj = new Waiting();
        Scheduling scheduleobj = new Scheduling(obj, schedTime);
        ScheduleStarter starterobj = new ScheduleStarter(obj, trip);
        Thread ttimer = new Thread(scheduleobj, "Timer");
        Thread tstarter = new Thread(starterobj, "Do other things");
        ttimer.start();
        tstarter.start();
        //
        //
        //
        //
        //Uber.mySleep(1500);

      } else {
        System.out.println(
                "Sorry, you've input the same starting point as the destination, or chosen an unavailable location! Please re-enter your choices.\n");
        }
      }
    }

  public void callARide() throws ParseException, InterruptedException {
    Boolean validTrip = false;
    Boolean validLoc = false;
    String destination;
    String startingPoint;
    String vType="";
    int num;
    while (!(validTrip)) {
      Uber.clearScreen();
      Scanner input = new Scanner(System.in);
      System.out.println(
          "Enter your starting point number:\n 1. Eden Avenue\n 2. Bhatta Chowk\n 3. DHA Phase 1\n 4. Model Town\n");
      num = input.nextInt();
      if (num == 1) {
        startingPoint = "Eden Avenue";
      } else if (num == 2) {
        startingPoint = "Bhatta Chowk";
      } else if (num == 3) {
        startingPoint = "DHA Phase 1";
      } else {
        startingPoint = "Model Town";
      }
      System.out.println(
          "Enter your destination number:\n 1. Eden Avenue\n 2. Bhatta Chowk\n 3. DHA Phase 1\n 4. Model Town\n");
      num = input.nextInt();
      if (num == 1) {
        destination = "Eden Avenue";
      } else if (num == 2) {
        destination = "Bhatta Chowk";
      } else if (num == 3) {
        destination = "DHA Phase 1";
      } else {
        destination = "Model Town";
      }

      String currTime = LocalDateTime.now()
          .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
      Trip trip = new Trip(startingPoint, destination, null, null, null, this, currTime, null);
      if (trip.checkRouteValidity(startingPoint, destination)) {
        validTrip = true;
        trip.calculateShortestRoute();
        trip.calculateFare();
        System.out.println("The estimated fair for your ride is "+ trip.getTotalCost() + ". Would you still like to search for a ride? Enter Yes to continue, no to exit.");
        input.nextLine();
        if (input.nextLine().equalsIgnoreCase("No"))
        {
          break;
        }
        boolean loopVar = true;
        boolean wrongVType = true;
        int randIndex;
        int rating_;
        Boolean ratingCheck = false;
        while (loopVar) {
          randIndex = Uber.myRand(0, Uber.drivers.size() - 1);
          while (wrongVType)
          {
            System.out.println("Enter the vehicle type you would like to travel on:\n- Car\n- Motorcycle\n- Rickshaw");
            vType = input.nextLine();
            if (vType.equalsIgnoreCase("Car") || vType.equalsIgnoreCase("Motorcycle") || vType.equalsIgnoreCase("Rickshaw"))
            {
              wrongVType = false;
            }
            else
            {
              System.out.println("You have entered a wrong vehicle type. Kindly enter a correct option.");
            }
          }
          System.out.println("Searching for a ride...");
          Uber.mySleep(3000);
          if (Uber.drivers.get(randIndex).getIsFree() && Uber.drivers.get(randIndex).hasVehicleType(vType)) {
            Uber.drivers.get(randIndex).setIsFree(false);
            Uber.drivers.get(randIndex).acceptRide(trip);
            trip.addDriver(Uber.drivers.get(randIndex));
            trip.addVehicle(Uber.drivers.get(randIndex).getVehicle());
            addRide(trip);
            loopVar = false;
            this.setStatus(true);
            trip.startRide();
            if (Trip.tripchoice != 5) {
              initiatePayment("notcash", getCurrentRide().getTotalCost(),
                  getCurrentRide().getDriver());
              while (ratingCheck == false) {
                System.out.println(
                    "Now that the trip is complete, what rating would you like to give the driver, "
                        + trip.getDriver().getName()
                        + ", for this trip?\nRate on a scale of 1 (very unsatisfactory) to 5 (highly satisfactory).");
                rating_ = input.nextInt();
                if (rating_ >= 1 && rating_ <= 5) {
                  ratingCheck = true;
                  rateDriver(rating_);
                } else {
                  System.out.println(
                      "Sorry, you can only rate between 1 and 5. Kindly enter rating again.");
                }
              }
              this.setStatus(false);
            } else {
              System.out.println("Trip ended prematurely.");
              this.setStatus(false);
            }
          }
          else
          {
            System.out.println("Ride not found. Searching again.");
            Uber.mySleep(1000);
          }
        }
      } else {
        System.out.println(
            "Sorry, you've input the same starting point as the destination, or chosen an unavailable location! Please re-enter your choices.\n");
      }
    }
  }

  void passengerInterface() throws ParseException, InterruptedException {
    while (true) {
      Uber.clearScreen();
      System.out.println(
          "-------------------------------------------------\nHello " + this.getName()
              + "!\nPlease enter the number of one of the options below to begin your journey with Uber.");
      System.out.println(
          "1. Search for a ride\n2. Show all trips\n3. Show current trip info\n4. Request assistance\n5. Request cancellation\n6. Schedule ride\n7. Exit");
      Scanner input = new Scanner(System.in);
      int choice = input.nextInt();
      if (choice == 1) {
        this.callARide();
      } else if (choice == 2) {
        this.displayRides();
      } else if (choice == 3) {
        this.displayCurrentRide();
      } else if (choice == 4) {
        this.requestAssistance();
      } else if (choice == 5) {
        this.requestCancellation();
      }
      else if (choice == 6)
      {
        this.scheduleRide();
      } else if (choice == 7) {
        break;
      } else {
        System.out.println(
            "Sorry, you did not enter any of the mentioned options. Please enter a correct option.\n");
      }
    }
  }

  public void callARideSimulator() throws ParseException, InterruptedException {
    Boolean validTrip = false;
    Boolean validLoc = false;
    String destination;
    String startingPoint;
    while (!(validTrip)) {
      Uber.clearScreen();
      System.out.println(
          "Enter your starting point.\n - Eden Avenue\n - Bhatta Chowk\n - DHA Phase 1\n - Model Town\n");
      Uber.mySleep(3000);
      int rand = Uber.myRand(1, 4);
      if (rand == 1) {
        startingPoint = "Eden Avenue";
      } else if (rand == 2) {
        startingPoint = "Bhatta Chowk";
      } else if (rand == 3) {
        startingPoint = "DHA Phase 1";
      } else {
        startingPoint = "Model Town";
      }
      System.out.println("Selected starting point is " + startingPoint);
      Uber.mySleep(2000);

      System.out.println(
          "Enter your destination. \n - Eden Avenue\n - Bhatta Chowk\n - DHA Phase 1\n - Model Town\n");
      Uber.mySleep(2000);
      rand = Uber.myRand(1, 4);
      if (rand == 1) {
        destination = "Eden Avenue";
      } else if (rand == 2) {
        destination = "Bhatta Chowk";
      } else if (rand == 3) {
        destination = "DHA Phase 1";
      } else {
        destination = "Model Town";
      }
      System.out.println("Selected destination is " + destination);
      Uber.mySleep(2000);
      String currTime = LocalDateTime.now()
          .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
      Trip trip = new Trip(startingPoint, destination, currTime, null, null, this, currTime, null);
      if (trip.checkRouteValidity(startingPoint, destination)) {
        validTrip = true;
        trip.calculateShortestRoute();
        trip.calculateFare();
        System.out.println("The estimated fair for your ride is "+ trip.getTotalCost() + ". Would you still like to search for a ride? Enter Yes to continue, no to exit.");
        Uber.mySleep(2000);
        System.out.println("You chose to continue searching.");
        Uber.mySleep(2000);
        boolean loopVar = true;
        int randIndex;
        int rating_;
        Boolean ratingCheck = false;
        while (loopVar) {
          randIndex = Uber.myRand(0, Uber.drivers.size() - 1);
          System.out.println("Enter the vehicle type you would like to travel on:\n- Car\n- Motorcycle\n- Rickshaw");
          String vType = "Car";
          Uber.mySleep(2000);
          System.out.println("You chose " + vType + " as the preferred vehicle type.");
          Uber.mySleep(2000);
          if (Uber.drivers.get(randIndex).getIsFree() && Uber.drivers.get(randIndex).hasVehicleType(vType)) {
            Uber.drivers.get(randIndex).setIsFree(false);
            System.out.println("Searching for a ride...");
            Uber.mySleep(3000);
            Uber.drivers.get(randIndex).acceptRide(trip);
            trip.addDriver(Uber.drivers.get(randIndex));
            trip.addVehicle(Uber.drivers.get(randIndex).getVehicle());
            addRide(trip);
            loopVar = false;
            this.setStatus(true);
            trip.getDriver().setStatus(true);
            trip.startRide();
            Trip.iobj = null;
            if (Trip.tripchoice != 5) {
              initiatePayment("notcash", getCurrentRide().getTotalCost(),
                  getCurrentRide().getDriver());
              System.out.println(
                  "Now that the trip is complete, what rating would you like to give the driver, "
                      + trip.getDriver().getName()
                      + ", for this trip?\nRate on a scale of 1 (very unsatisfactory) to 5 (highly satisfactory).");
              Uber.mySleep(2000);
              rating_ = 1;//Uber.myRand(1, 5);
              rateDriver(rating_);
              System.out.println("You have given the driver a rating of " + rating_);
              this.setStatus(false);
              trip.getDriver().setStatus(false);
            } else {
              System.out.println("Trip ended prematurely");
              trip.getDriver().setStatus(false);
              this.setStatus(false);
            }
          }
        }
      } else {
        System.out.println(
            "Sorry, you've input the same starting point as the destination, or chosen an unavailable location! Please re-enter your choices.\n");
      }
    }
  }

  void passengerInterfaceSimulate() throws ParseException, InterruptedException {
    int choice = 1;
    while (true) {
      Uber.clearScreen();
      System.out.println(
          "-------------------------------------------------\nHello " + this.getName()
              + "!\nPlease enter the number of one of the options below to begin your journey with Uber.");
      System.out.println(
          "1. Search for a ride\n2. Show all trips\n3. Show current trip info\n4. Request assistance\n5. Request cancellation\n6. Exit");
      if (choice == 1) {
        Uber.mySleep(2000);
        System.out.println("You chose to search for a ride.\n");
        Uber.mySleep(2000);
        this.callARideSimulator();
        Uber.mySleep(2000);
      } else if (choice == 2) {
        Uber.mySleep(2000);
        System.out.println("You chose to show all trips.\n");
        Uber.mySleep(2000);
        this.displayRides();
        Uber.mySleep(2000);
      } else if (choice == 3) {
        Uber.mySleep(2000);
        System.out.println("You chose to show current trip info.\n");
        Uber.mySleep(2000);
        this.displayCurrentRide();
        Uber.mySleep(2000);
      } else if (choice == 4) {
        Uber.mySleep(2000);
        System.out.println("You chose to request assistance from an uber staff member.\n");
        Uber.mySleep(2000);
        this.requestAssistance();
        Uber.mySleep(2000);
      } else if (choice == 5) {
        Uber.mySleep(2000);
        System.out.println("You chose to request cancellation.\n");
        Uber.mySleep(2000);
        this.requestCancellation();
        Uber.mySleep(2000);
      } else {
        System.out.println("You chose to exit back to simulator screen.\n");
        Uber.mySleep(2000);
        break;
      }
      choice++;
    }
  }


  public void removeTrip(Trip t) {
    if (trips != null) {
      trips.remove(t);
    }
  }

}