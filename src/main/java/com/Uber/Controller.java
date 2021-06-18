package com.Uber;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  private final Connection con = Database.connectToDB();

  @RequestMapping(value = "register", method = RequestMethod.POST)
  public int register(@RequestBody String data) throws JSONException, SQLException {
    JSONObject passedData = new JSONObject(data);
    String name, password, email;
    name = passedData.getJSONObject("data").getString("name");
    password = passedData.getJSONObject("data").getString("password");
    email = passedData.getJSONObject("data").getString("email");
    Database.registerUser(con, name, password, email);
    return 1;
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public int login(@RequestBody String data) throws JSONException, SQLException {
    JSONObject passedData = new JSONObject(data);
    String password, email;
    password = passedData.getJSONObject("data").getString("password");
    email = passedData.getJSONObject("data").getString("email");
    Database.loginUser(con, email, password);
    return 1;
  }
}
