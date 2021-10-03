package com.godle.presentation.tracing.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.bwaldvogel.mongo.MongoServer;
import io.opentracing.Tracer;
import io.opentracing.contrib.mongo.common.TracingCommandListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.net.InetSocketAddress;

@Configuration
public class MongoConfig {

  private final Tracer tracer;

  @Autowired
  public MongoConfig(Tracer tracer) {
    this.tracer = tracer;
  }

  @Bean
  public MongoDatabaseFactory mongoDatabaseFactory(MongoServer mongoServer) {
    InetSocketAddress serverAddress = mongoServer.getLocalAddress();
    return new SimpleMongoClientDatabaseFactory(
        "mongodb://" + serverAddress.getHostName() + ":" + serverAddress.getPort() + "/test");
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
    return new MongoTemplate(mongoDatabaseFactory);
  }

  @Bean
  public TracingCommandListener mongoListener() {
    return new TracingCommandListener.Builder(tracer).build();
  }

  @Bean
  public MongoClient mongoClient(TracingCommandListener mongoListener) {

    MongoClient mongoClient =
        MongoClients.create(
            MongoClientSettings.builder().addCommandListener(mongoListener).build());

    return mongoClient;
  }
}
