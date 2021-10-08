package com.godel.presentation.tracing.controller;

import com.godel.presentation.tracing.entity.Order;
import com.godel.presentation.tracing.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderingController {

  private final OrderingService service;

  @Autowired
  public OrderingController(OrderingService service) {
    this.service = service;
  }

  @GetMapping(path = "/hello")
  public String hello() {
    return "Hello from ordering service!";
  }

  @GetMapping(path = "/{id}")
  public Order getOrder(@PathVariable String id) {
    return service.getById(id);
  }

  @PostMapping
  public void cook(@RequestBody String order) {
    service.createOrder(order);
  }

  @GetMapping(path = "/cookies/ping")
  public String helloFromCookingService() {
    return service.pingCookingService();
  }
}
