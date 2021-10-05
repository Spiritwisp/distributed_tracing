package com.godel.presentation.tracing.repository;

import com.godel.presentation.tracing.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderingRepository {

  private final MongoTemplate mongoTemplate;

  @Autowired
  public OrderingRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public Order findById(String id) {
    return mongoTemplate.findById(id, Order.class);
  }
}
