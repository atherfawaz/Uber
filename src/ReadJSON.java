import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ReadJSON {

  public static void read() {
    String path = "D:\\College\\Fall 2019\\Object Oriented Analysis and Design\\Project\\Uber\\src\\Drivers.json";
    JSONParser jsonParser = new JSONParser();
    try (FileReader reader = new FileReader(path)) {
      //Read JSON file
      Object obj = jsonParser.parse(reader);

      JSONArray driverList = (JSONArray) obj;
      //System.out.println(employeeList);

      //Iterate over employee array
      driverList.forEach(driver -> parseDriverObject((JSONObject) driver));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
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

    Uber.drivers.add(obj);
  }
}
