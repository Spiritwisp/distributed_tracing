package com.godel.presentation.tracing;

import com.godel.presentation.tracing.entity.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class OrderingServiceApp {

  private final MongoTemplate mongoTemplate;

  public OrderingServiceApp(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public static void main(String[] args) {
    SpringApplication.run(OrderingServiceApp.class, args);
  }

  @Bean
  CommandLineRunner preLoadMongo() {
    return args -> {
      Order order = new Order("1", "Carbonara");
      mongoTemplate.save(order);
    };
  }
}
