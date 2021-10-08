package com.godel.presentation.tracing.controller;

import com.godel.presentation.tracing.service.JerseyClientService;
import com.godel.presentation.tracing.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

  private final UserService userService;

  public UserController() {
    userService = new UserService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String address = getAddress(req);
    System.out.println(address);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    JerseyClientService clientService = new JerseyClientService(req);
    clientService.sendRequest();
  }

  private String getAddress(HttpServletRequest req) {
    String pathInfo = req.getPathInfo();
    return userService.getAddressByUserId(Integer.valueOf(pathInfo.replace("/", "")));
  }
}
