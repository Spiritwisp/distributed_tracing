package com.godle.presentation.tracing.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "orders")
public class Order {

  @Id
  private final String id;
  private String name;

  public Order(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Order order = (Order) o;

    return name.equals(order.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
