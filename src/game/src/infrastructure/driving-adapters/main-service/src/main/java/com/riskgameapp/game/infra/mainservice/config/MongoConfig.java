package com.riskgameapp.game.infra.mainservice.config;

import com.riskgameapp.game.infra.mongo.adapters.MongoAdapter;
import com.riskgameapp.game.infra.mongo.repositories.IEventsRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EntityScan(basePackages = "com.riskgameapp.game.infra.mongo.entities")
@EnableReactiveMongoRepositories(basePackages = "com.riskgameapp.game.infra.mongo.repositories")
public class MongoConfig {
  @Bean
  public MongoAdapter mongoAdapter(IEventsRepository eventsRepository) {
    return new MongoAdapter(eventsRepository);
  }
}
