package com.godel.presentation.tracing.controller;

import com.godel.presentation.tracing.service.JerseyClientService;
import com.godel.presentation.tracing.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet {

  private final UserService userService;

  public UserController() {
    userService = new UserService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    String pathInfo = req.getPathInfo();
    String address = userService.getAddressByUserId(Integer.valueOf(pathInfo.replace("/", "")));
    System.out.println(address);

    JerseyClientService clientService = new JerseyClientService(req);
    clientService.sendRequest();
  }
}
