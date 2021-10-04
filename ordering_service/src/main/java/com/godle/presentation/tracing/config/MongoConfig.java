package com.godle.presentation.tracing.config;

import brave.Tracing;
import brave.mongodb.MongoDBTracing;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.event.CommandListener;
import de.bwaldvogel.mongo.MongoServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.net.InetSocketAddress;

@Configuration
public class MongoConfig {

  private final Tracing tracing;
  private final String dbName;

  public MongoConfig(Tracing tracing, @Value("${spring.data.mongodb.database}") String dbName) {
    this.tracing = tracing;
    this.dbName = dbName;
  }

  @Bean
  public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient) {
    return new SimpleMongoClientDatabaseFactory(mongoClient, dbName);
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
    return new MongoTemplate(mongoDatabaseFactory);
  }

  @Bean
  public CommandListener listener() {
    return MongoDBTracing.create(tracing).commandListener();
  }

  @Bean
  public MongoClientSettings mongoClientSettings(
      CommandListener listener, MongoServer mongoServer) {
    InetSocketAddress serverAddress = mongoServer.getLocalAddress();
    return MongoClientSettings.builder()
        .addCommandListener(listener)
        .applyConnectionString(
            new ConnectionString(
                "mongodb://"
                    + serverAddress.getHostName()
                    + ":"
                    + serverAddress.getPort()
                    + "/"
                    + dbName))
        .build();
  }

  @Bean
  public MongoClient mongoClient(MongoClientSettings mongoClientSettings) {
    return MongoClients.create(mongoClientSettings);
  }
}
