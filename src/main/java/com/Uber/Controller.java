package com.Uber;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class Controller {

  @GetMapping("")
  public JSONObject home() {
    JSONObject obj = new JSONObject();
    obj.put("name", "foo");
    obj.put("num", 100);
    obj.put("balance", 1000.21);
    obj.put("is_vip", Boolean.TRUE);
    System.out.print(obj);
    return obj;
  }
}
