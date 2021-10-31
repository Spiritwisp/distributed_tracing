package com.godel.presentation.tracing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {

  @Id
  @Column(name = "user_id")
  private Integer id;

  @EqualsAndHashCode.Include
  private String username;
  @EqualsAndHashCode.Include
  private String address;

  @Column(name = "favorite_order")
  private Integer favoriteOrder;
}
