package com.godel.presentation.tracing.service;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CookingService {

  private static final String DELIVERY_URL = "http://localhost:8081/delivery-service/delivery";
  private final HttpClient httpClient;

  @Autowired
  public CookingService(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public void startDelivery() throws IOException {
    httpClient.execute(new HttpPost(DELIVERY_URL));
  }
}
