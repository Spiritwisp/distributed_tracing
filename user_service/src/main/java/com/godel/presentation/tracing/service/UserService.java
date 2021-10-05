package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.entity.Order;
import com.godel.presentation.tracing.entity.User;

public interface UserService {

  User getByUsername(String username);

  Order getOrder(Integer userId, Integer orderId);
}
