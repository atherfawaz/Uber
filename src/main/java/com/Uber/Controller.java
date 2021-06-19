package com.Uber;

import java.sql.Connection;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  private final Connection con = Database.connectToDB();

  @RequestMapping(value = "register", method = RequestMethod.POST)
  public ResponseEntity<String> register(@RequestBody String data) throws JSONException, SQLException {
    JSONObject passedData = new JSONObject(data);
    String name, password, email;
    name = passedData.getJSONObject("data").getString("name");
    password = passedData.getJSONObject("data").getString("password");
    email = passedData.getJSONObject("data").getString("email");
    int status = Database.registerUser(con, name, password, email);
    if (status == 1) // all good
      return new ResponseEntity<>("Registered Successfully", HttpStatus.OK);
    else if (status == -1) // user exists
      return new ResponseEntity<>("User already Exists!", HttpStatus.BAD_REQUEST);
    else
      return new ResponseEntity<>("Failed to Register, try again!", HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public ResponseEntity<String> login(@RequestBody String data) throws JSONException, SQLException {
    JSONObject passedData = new JSONObject(data);
    String password, email;
    password = passedData.getJSONObject("data").getString("password");
    email = passedData.getJSONObject("data").getString("email");
    if (Database.loginUser(con, email, password))
      return new ResponseEntity<>("Logged In Successfully", HttpStatus.OK);
    else
      return new ResponseEntity<>("Failed to Login, try again!", HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(value = "getPassengerDetails", method = RequestMethod.POST)
  public ResponseEntity<String> getPassengerDetails(@RequestBody String data) throws JSONException, SQLException {
    JSONObject passedData = new JSONObject(data);
    String email;
    email = passedData.getString("email");
    JSONObject details = Database.getPassengerDetails(con, email);
    return new ResponseEntity<>(details.toString(), HttpStatus.OK);
  }

  @RequestMapping(value = "completeTrip", method = RequestMethod.POST)
  public ResponseEntity<String> completeTrip(@RequestBody String data) throws JSONException, SQLException {
    JSONObject passedData = new JSONObject(data);
    String passengerEmail, driverEmail, pickup, destination, fare, tripID;
    passengerEmail = passedData.getString("passengerEmail");
    driverEmail = passedData.getString("driverEmail");
    pickup = passedData.getString("pickup");
    destination = passedData.getString("destination");
    fare = passedData.getString("fare");
    tripID = passedData.getString("tripID");
    boolean response = Database.completeTrip(con, passengerEmail, driverEmail, pickup, destination, fare, tripID);
    if (response) {
      return new ResponseEntity<>("Trip completed successfully. Thank you for using Uber.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Trip could not be completed. We apologize for the inconvenience caused.",
          HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "reportDriver", method = RequestMethod.POST)
  public ResponseEntity<String> reportDriver(@RequestBody String data) throws JSONException, SQLException {
    JSONObject passedData = new JSONObject(data);
    String passengerEmail, driverEmail, tripID, complaint;
    passengerEmail = passedData.getString("passengerEmail");
    driverEmail = passedData.getString("driverEmail");
    tripID = passedData.getString("tripID");
    complaint = passedData.getString("complaint");
    boolean response = Database.reportDriver(con, passengerEmail, driverEmail, tripID, complaint);
    if (response) {
      return new ResponseEntity<>(
          "Driver has been reported. We apologize for the inconvenience caused. An Uber official will shortly get in touch with you.",
          HttpStatus.OK);
    } else {
      return new ResponseEntity<>("The driver could not be reported. Please check the submitted details and try again.",
          HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "getVehicleDetails", method = RequestMethod.POST)
  public ResponseEntity<String> getVehicleDetails(@RequestBody String data) throws JSONException, SQLException {
    JSONObject passedData = new JSONObject(data);
    String driverEmail, vehicleRegistration;
    driverEmail = passedData.getString("driverEmail");
    vehicleRegistration = passedData.getString("vehicleRegistration");
    JSONObject details = Database.getVehicleDetails(con, driverEmail, vehicleRegistration);
    return new ResponseEntity<>(details.toString(), HttpStatus.OK);
  }
}
