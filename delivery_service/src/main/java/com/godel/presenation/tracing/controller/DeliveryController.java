package com.godel.presenation.tracing.controller;

import com.godel.presenation.tracing.config.TracingFilter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliveryController extends HttpServlet {

  private static final String COOKING_URL =
      "http://localhost:8084/cooking-service/cookies/confirmation";

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    Client client = Client.create();
    client.addFilter(new TracingFilter(req));

    WebResource resource = client.resource(COOKING_URL);
    String ok = resource.type("text/plain").post(String.class, "OK");
    System.out.println(ok);
  }
}
