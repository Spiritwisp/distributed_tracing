package com.godle.presentation.tracing.service;

import com.godle.presentation.tracing.entity.Order;
import com.godle.presentation.tracing.repository.OrderingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderingService.class);

  private final OrderingRepository repository;

  @Autowired
  public OrderingService(OrderingRepository repository) {
    this.repository = repository;
  }

  public Order getById(String id) {
    LOGGER.debug("Getting Order by id:{}", id);
    return repository.findById(id);
  }


}
