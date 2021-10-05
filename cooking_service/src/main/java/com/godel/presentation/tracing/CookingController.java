package com.godel.presentation.tracing;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cookies")
public class CookingController {

    @PostMapping
    public void cook(@RequestBody String order){
    System.out.println("Cooking " + order);
    }
}
