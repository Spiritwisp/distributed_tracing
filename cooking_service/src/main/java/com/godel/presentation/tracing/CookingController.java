package com.godel.presentation.tracing;

import com.godel.presentation.tracing.service.CookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping(path = "/{userId}")
  public void sendToDelivery(@PathVariable Integer userId) throws IOException {
    System.out.println("Starting delivery");
    service.startDelivery(userId);
  }

  @PostMapping(path = "/confirmation")
  public void deliveryConfirm(@RequestBody String message) {
    System.out.println(message);
  }
}
