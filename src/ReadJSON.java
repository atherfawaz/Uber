import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


class ReadJSON {

  static void fetchDrivers() {

    //add your paths here for easy access and changing

    //path for ather: D:\College\Fall 2019\Object Oriented Analysis and Design\Project\Uber\src\Drivers.json
    //path for hamza: C:\Users\Hamza Jawad\IdeaProjects\Uber\src\Drivers.json

    String path = "D:\\College\\Fall 2019\\Object Oriented Analysis and Design\\Project\\Uber\\src\\Drivers.json";
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
    obj.getVehicles().setRegistrationNum(registration);

    String condition = (String) driverObject.get("condition");
    obj.getVehicles().setCondition(condition);

    String make = (String) driverObject.get("make");
    obj.getVehicles().setMake(make);

    String model = (String) driverObject.get("model");
    obj.getVehicles().setModel(Integer.parseInt(model));

    String manufacturer = (String) driverObject.get("manufacturer");
    obj.getVehicles().setManufacturer(manufacturer);

    Uber.drivers.add(obj);
  }

  static void fetchPassengers() {

    //add your paths here for easy access and changing

    //path for ather: D:\College\Fall 2019\Object Oriented Analysis and Design\Project\Uber\src\Passengers.json

    String path = "D:\\College\\Fall 2019\\Object Oriented Analysis and Design\\Project\\Uber\\src\\Passengers.json";
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

  private static void parsePassengerObject(JSONObject employee) {

    //Get driver object within list
    JSONObject driverObject = (JSONObject) employee.get("passenger");

    Passenger obj = new Passenger();

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

    Uber.passengers.add(obj);
  }
}