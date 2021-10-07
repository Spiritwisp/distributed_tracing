package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.repository.UserRepository;

public class UserService {

  private final UserRepository userRepository;

  public UserService() {
    userRepository = new UserRepository();
  }

  public String getAddressByUserId(Integer id) {
    return userRepository.getAddressByUserId(id);
  }
}
