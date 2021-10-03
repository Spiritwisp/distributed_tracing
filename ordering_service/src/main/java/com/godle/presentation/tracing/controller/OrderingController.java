package com.godle.presentation.tracing.controller;

import com.godle.presentation.tracing.entity.Order;
import com.godle.presentation.tracing.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderingController {

  private final OrderingService service;

  @Autowired
  public OrderingController(OrderingService service) {
    this.service = service;
  }

  @GetMapping(path = "/{id}")
  public Order getOrder(@PathVariable String id) {
    return service.getById(id);
  }
}
