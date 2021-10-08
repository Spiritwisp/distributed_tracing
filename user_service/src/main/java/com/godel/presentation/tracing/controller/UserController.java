package com.godel.presentation.tracing.controller;

import com.godel.presentation.tracing.entity.Order;
import com.godel.presentation.tracing.entity.User;
import com.godel.presentation.tracing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "/hello")
  public String getHello() {
    return "Hello from user service!";
  }

  @GetMapping(path = "/{username}")
  public User getUser(@PathVariable String username) {
    return userService.getByUsername(username);
  }

  @GetMapping(path = "/{userId}/orders")
  public Order getOrder(@PathVariable Integer userId) {
    return userService.getOrder(userId);
  }

  @PostMapping(path = "/{userId}/orders")
  public void cook(@PathVariable Integer userId) {
    userService.createOrder(userId);
  }

  @GetMapping(path = "/orders/ping")
  public String pingOrderingService() {
    return userService.pingOrderingService();
  }
}
  Tracing Feign Client