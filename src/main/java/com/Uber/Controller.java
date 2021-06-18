package com.Uber;

import java.sql.Connection;
import java.sql.SQLException;

import javax.print.DocFlavor.STRING;
import javax.xml.crypto.Data;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
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
    
    HttpHeaders responseHeaders = new HttpHeaders();
    if(Database.registerUser(con, name, password, email)) {
      responseHeaders.set("loginStatus", 
      "User Found");

      return ResponseEntity.ok()
                          .headers(responseHeaders)
                          .body("From Login");
    }
    else {
      responseHeaders.set("loginStatus", 
      "User Not Found");

      return ResponseEntity.badRequest()
                          .headers(responseHeaders)
                          .body("From Login");
    }
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public ResponseEntity<String> login(@RequestBody String data) throws JSONException, SQLException {
    JSONObject passedData = new JSONObject(data);
    String password, email;
    password = passedData.getJSONObject("data").getString("password");
    email = passedData.getJSONObject("data").getString("email");
    
    HttpHeaders responseHeaders = new HttpHeaders();
    if(Database.loginUser(con, email, password)) {
      responseHeaders.set("loginStatus", 
      "User Found");

      return ResponseEntity.ok()
                          .headers(responseHeaders)
                          .body("From Login");
    }
    else {
      responseHeaders.set("loginStatus", 
      "User Not Found");

      return ResponseEntity.badRequest()
                          .headers(responseHeaders)
                          .body("From Login");
    }
  }
}
