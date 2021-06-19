package com.Uber;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
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

    else if(status == -1) // user exists
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
}
