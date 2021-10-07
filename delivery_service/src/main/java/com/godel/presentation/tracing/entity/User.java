package com.godel.presentation.tracing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @Column(name = "user_id")
  private Integer id;

  private String username;
  private String address;

  @Column(name = "favorite_order")
  private Integer favoriteOrder;

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getFavoriteOrder() {
    return favoriteOrder;
  }

  public void setFavoriteOrder(Integer favoriteOrder) {
    this.favoriteOrder = favoriteOrder;
  }
}
