package com.godel.presentation.tracing;

import com.godel.presentation.tracing.service.CookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/cookies")
public class CookingController {

  private final CookingService service;

  @Autowired
  public CookingController(CookingService service) {
    this.service = service;
  }

/*  @PostMapping
  public void cook(@RequestBody String order) {
    System.out.println("Cooking " + order);
  }*/

  @PostMapping
  public void sendToDelivery() throws IOException {
    service.startDelivery();
    System.out.println("Starting delivery");
  }

  @PostMapping(path = "/confirmation")
  public void deliveryConfirm(@RequestBody String message) {
    System.out.println(message);
  }
}
