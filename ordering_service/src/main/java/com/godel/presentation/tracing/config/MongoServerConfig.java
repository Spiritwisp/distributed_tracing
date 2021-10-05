package com.godel.presentation.tracing.config;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoServerConfig {

  private final String host;
  private final Integer port;

  public MongoServerConfig(
      @Value("${spring.data.mongodb.host}") String host,
      @Value("${spring.data.mongodb.port}") Integer port) {
    this.host = host;
    this.port = port;
  }

  @Bean
  public MongoServer mongoServer() {
    MongoServer mongoServer = new MongoServer(new MemoryBackend());
    mongoServer.bind(host, port);
    return mongoServer;
  }
}
