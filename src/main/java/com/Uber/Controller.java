package com.Uber;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @RequestMapping(value="register", method=RequestMethod.POST)
  public int register(@RequestBody String data) throws JSONException {
    System.out.println(new JSONObject(data));
    return 1;
  }
}
