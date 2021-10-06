package com.godel.presentation.tracing.config;

import io.opentracing.Tracer;
import io.opentracing.contrib.apache.http.client.TracingHttpClientBuilder;
import io.opentracing.util.GlobalTracer;
import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

  @Bean
  public HttpClient httpClient() {
    Tracer tracer = GlobalTracer.get();
    return TracingHttpClientBuilder.create().withTracer(tracer).build();
  }
}
