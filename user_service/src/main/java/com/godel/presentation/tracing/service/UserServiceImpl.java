package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.entity.User;
import com.godel.presentation.tracing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }
}
