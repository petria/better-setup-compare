package com.airiot.fi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

  @GetMapping("/user")
  public Map<String, Object> getUser(@AuthenticationPrincipal UserDetails userDetails) {
    Map<String, Object> userMap = new HashMap<>();
//    if (userDetails != null) {
    userMap.put("username", userDetails.getUsername());
    userMap.put("roles", userDetails.getAuthorities());
//    }
    return userMap;
  }
}
