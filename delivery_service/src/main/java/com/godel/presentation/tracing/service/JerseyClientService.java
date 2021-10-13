package com.godel.presentation.tracing.service;

import com.godel.presentation.tracing.config.TracingFilter;
import com.sun.jersey.api.client.Client;

import javax.servlet.http.HttpServletRequest;

public class JerseyClientService {

  private static final String CONFIRMATION_URL =
      "http://localhost:8084/cooking-service/cookies/confirmation";
  private final Client client;

  public JerseyClientService(HttpServletRequest request) {
    client = Client.create();
    client.addFilter(new TracingFilter(request));
  }

  public void sendRequest() {
    client.resource(CONFIRMATION_URL).type("text/plain").post(String.class, "OK!");
  }
}
