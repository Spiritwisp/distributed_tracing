package com.godel.presentation.tracing.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CookingService {

  private static final String DELIVERY_URL = "http://localhost:8085/delivery-service";
  private final HttpClient httpClient;

  @Autowired
  public CookingService(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public void startDelivery(Integer userId) throws IOException {
    httpClient.execute(new HttpPost(DELIVERY_URL + "/users/" + userId));
  }

  public String pingDeliveryService() throws IOException {
    httpClient.execute(new HttpGet(DELIVERY_URL + "/hello"));
    return "OK";
  }
}
