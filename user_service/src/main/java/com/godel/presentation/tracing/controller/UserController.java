package com.godel.presentation.tracing.controller;

import com.godel.presentation.tracing.entity.Order;
import com.godel.presentation.tracing.entity.User;
import com.godel.presentation.tracing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "/{username}")
  public User getUser(@PathVariable String username) {
    return userService.getByUsername(username);
  }

  @GetMapping(path = "/{userId}/orders/{orderId}")
  public Order getOrder(@PathVariable Integer userId, @PathVariable Integer orderId) {
    return userService.getOrder(userId, orderId);
  }
}
