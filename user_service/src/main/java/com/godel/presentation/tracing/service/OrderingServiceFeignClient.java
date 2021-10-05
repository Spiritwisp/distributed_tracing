package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ordering-service", url = "http://localhost:8080")
public interface OrderingServiceFeignClient {

  @RequestMapping(method = RequestMethod.GET, value = "/orders/{id}", consumes = "application/json")
  Order getOrder(@PathVariable("id") Integer id);
}
