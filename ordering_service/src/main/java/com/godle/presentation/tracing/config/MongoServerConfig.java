package com.godle.presentation.tracing.config;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoServerConfig {

  @Bean
  public MongoServer mongoServer() {
    MongoServer mongoServer = new MongoServer(new MemoryBackend());
    mongoServer.bind("localhost", 27017);
    return mongoServer;
  }
}
