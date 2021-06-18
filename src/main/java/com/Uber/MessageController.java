package com.Uber;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  @GetMapping("/hello")
  public String hello() {
    return "This is hello";
  }

  @GetMapping("ather")
  public String ather() {
    return "Hi, Ather";
  }
}