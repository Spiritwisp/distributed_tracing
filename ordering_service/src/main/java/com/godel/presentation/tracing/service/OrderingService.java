package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.entity.Order;
import com.godel.presentation.tracing.repository.OrderingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class OrderingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderingService.class);

  private final OrderingRepository repository;
  private final RestTemplate restTemplate;

  @Autowired
  public OrderingService(OrderingRepository repository, RestTemplate restTemplate) {
    this.repository = repository;
    this.restTemplate = restTemplate;
  }

  public Order getById(String id) {
    LOGGER.debug("Getting Order by id:{}", id);
    return repository.findById(id);
  }

  public void createOrder(String orderName) {
    String url = "http://localhost:8084/cooking-service/cookies";
    restTemplate.postForEntity(url, orderName, String.class);
  }

  public String pingCookingService() {
    String url = "http://localhost:8084/cooking-service/cookies/hello";
    URI uri = URI.create(url);
    ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
    return forEntity.getBody();
  }
}
