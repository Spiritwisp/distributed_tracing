package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ordering-service", url = "http://localhost:8081")
public interface OrderingServiceFeignClient {

  @RequestMapping(method = RequestMethod.GET, value = "/orders/{id}", consumes = "application/json")
  Order getOrder(@PathVariable("id") Integer id);


  @RequestMapping(method = RequestMethod.POST, value = "/orders", consumes = "application/json")
  Order createOrder(@RequestBody String orderName);

  @RequestMapping(method = RequestMethod.GET, value = "/orders/hello", consumes = "application/json")
  String hello();
}
