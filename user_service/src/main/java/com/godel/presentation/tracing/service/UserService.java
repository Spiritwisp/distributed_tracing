package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.entity.Order;
import com.godel.presentation.tracing.entity.User;
import com.godel.presentation.tracing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final OrderingServiceFeignClient feignClient;

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

  public String pingOrderingService(){
    return feignClient.hello();
  }
}
