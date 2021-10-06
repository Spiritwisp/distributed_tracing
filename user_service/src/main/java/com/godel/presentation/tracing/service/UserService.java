package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.entity.Order;
import com.godel.presentation.tracing.entity.User;
import com.godel.presentation.tracing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final OrderingServiceFeignClient feignClient;

  @Autowired
  public UserService(UserRepository userRepository, OrderingServiceFeignClient feignClient) {
    this.userRepository = userRepository;
    this.feignClient = feignClient;
  }

  public User getByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  public Order getOrder(Integer userId) {
    User user = userRepository.findUserById(userId);
    return feignClient.getOrder(user.getFavoriteOrder());
  }

  public void createOrder(Integer userId) {
    Order order = getOrder(userId);
    feignClient.createOrder(order.getName());
  }
}
