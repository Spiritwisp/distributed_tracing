package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.entity.Order;
import com.godel.presentation.tracing.entity.User;
import com.godel.presentation.tracing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final OrderingServiceFeignClient feignClient;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, OrderingServiceFeignClient feignClient) {
    this.userRepository = userRepository;
    this.feignClient = feignClient;
  }

  @Override
  public User getByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  @Override
  public Order getOrder(Integer userId, Integer orderId) {
    User user = userRepository.findUserById(userId);
    return feignClient.getOrder(user.getFavoriteOrder());
  }
}
