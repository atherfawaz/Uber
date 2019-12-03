import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


class ReadJSON {

  static void fetchDrivers() {

    //path for ather: C:\Users\ather\Desktop\Uber - Simulataneous\Uber\src\Drivers.json

// simultaneous
    String path = "C:\\Users\\Lodhi\\IdeaProjects\\Uber\\src\\Drivers.json";
    //path for ather: C:\Users\ather\Desktop\Uber - Multithreading\Uber\src\Drivers.json
    //path for hamza: C:\Users\Hamza Jawad\IdeaProjects\Uber\src\Drivers.json

  //  String path = "C:\\Users\\AY\\IdeaProjects\\Uber\\src\\Drivers.json";
// multithreading
    JSONParser jsonParser = new JSONParser();
    try (FileReader reader = new FileReader(path)) {
      //Read JSON file
      Object obj = jsonParser.parse(reader);

      JSONArray driverList = (JSONArray) obj;
      //System.out.println(employeeList);

      //Iterate over employee array
      driverList.forEach(driver -> parseDriverObject((JSONObject) driver));

    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  private static void parseDriverObject(JSONObject employee) {

    //Get driver object within list
    JSONObject driverObject = (JSONObject) employee.get("driver");

    Driver obj = new Driver();

    String name = (String) driverObject.get("name");
    obj.setName(name);

    String nationalid = (String) driverObject.get("nationalid");
    obj.setNationalId(nationalid);

    String dob = (String) driverObject.get("dateofbirth");
    obj.setDateOfBirth(dob);

    String email = (String) driverObject.get("email");
    obj.setEmail(email);

    String phonenumber = (String) driverObject.get("phonenumber");
    obj.setPhoneNum(phonenumber);

    Boolean isDriver = (Boolean) driverObject.get("isdriver");
    obj.setDriver(isDriver);

    String iban = (String) driverObject.get("iban");
    obj.account.setAccountNUm(iban);

    Double balance = (Double) driverObject.get("balance");
    obj.account.setTotalCredit(balance);

    String registration = (String) driverObject.get("registrationnum");
    obj.getVehicle().setRegistrationNum(registration);

    String condition = (String) driverObject.get("condition");
    obj.getVehicle().setCondition(condition);

    String make = (String) driverObject.get("make");
    obj.getVehicle().setMake(make);

    String model = (String) driverObject.get("model");
    obj.getVehicle().setModel(Integer.parseInt(model));

    String manufacturer = (String) driverObject.get("manufacturer");
    obj.getVehicle().setManufacturer(manufacturer);

    Uber.drivers.add(obj);
  }

  static void fetchPassengers() {

    //path for ather: C:\Users\ather\Desktop\Uber - Simulataneous\Uber\src\Passengers.json

//<<<<<<< simultaneous
    String path = "C:\\Users\\Lodhi\\IdeaProjects\\Uber\\src\\Passengers.json";
//=======
    //String path = "C:\\Users\\AY\\IdeaProjects\\Uber\\src\\Passengers.json";
//>>>>>>> multithreading
    JSONParser jsonParser = new JSONParser();
    try (FileReader reader = new FileReader(path)) {
      //Read JSON file
      Object obj = jsonParser.parse(reader);

      JSONArray passengerList = (JSONArray) obj;
      //System.out.println(employeeList);

      //Iterate over employee array
      passengerList.forEach(passenger -> parsePassengerObject((JSONObject) passenger));

    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  private static void parsePassengerObject(JSONObject passenger) {

    //Get passenger object within list
    JSONObject passengerObject = (JSONObject) passenger.get("passenger");

    Passenger obj = new Passenger();

    String name = (String) passengerObject.get("name");
    obj.setName(name);

    String nationalid = (String) passengerObject.get("nationalid");
    obj.setNationalId(nationalid);

    String dob = (String) passengerObject.get("dateofbirth");
    obj.setDateOfBirth(dob);

    String email = (String) passengerObject.get("email");
    obj.setEmail(email);

    String phonenumber = (String) passengerObject.get("phonenumber");
    obj.setPhoneNum(phonenumber);

    Boolean isDriver = (Boolean) passengerObject.get("isdriver");
    obj.setDriver(isDriver);

    String iban = (String) passengerObject.get("iban");
    obj.account.setAccountNUm(iban);

    Double balance = (Double) passengerObject.get("balance");
    obj.account.setTotalCredit(balance);

    Uber.passengers.add(obj);
  }

  static void fetchTrips() {

    //path for ather: C:\Users\ather\Desktop\Uber - Simulataneous\Uber\src\Trips.json

    String path = "C:\\Users\\Lodhi\\IdeaProjects\\Uber\\src\\Trips.json";
    JSONParser jsonParser = new JSONParser();
    try (FileReader reader = new FileReader(path)) {
      //Read JSON file
      Object obj = jsonParser.parse(reader);

      JSONArray tripList = (JSONArray) obj;
      //System.out.println(employeeList);

      //Iterate over employee array
      tripList.forEach(trip -> parseTripObject((JSONObject) trip));

    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  private static void parseTripObject(JSONObject trip) {

    JSONObject tripObject = (JSONObject) trip.get("trip");
    Trip obj = new Trip();

    String sp = (String) tripObject.get("startingpoint");
    obj.setStartingPoint(sp);

    String destination = (String) tripObject.get("destination");
    obj.setDestination(destination);

    String time = (String) tripObject.get("timeforschedule");
    obj.setTimeForSchedule(time);

    obj.addVehicle(Uber.drivers.get(Uber.myRand(1, Uber.drivers.size()) - 1).getVehicle());
    obj.addPassenger(Uber.passengers.get(Uber.myRand(1, Uber.passengers.size()) - 1));
    obj.setDateTime("5 minutes from now");

    Double cost = (Double) tripObject.get("totalCost");
    obj.setTotalCost(cost);

    Uber.trips.add(obj);
  }
}