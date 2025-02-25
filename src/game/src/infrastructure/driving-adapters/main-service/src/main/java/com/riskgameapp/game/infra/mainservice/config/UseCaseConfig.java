package com.riskgameapp.game.infra.mainservice.config;

import com.riskgameapp.game.application.addNewTroop.AddNewTroopUseCase;
import com.riskgameapp.game.application.addTerritory.AddTerritoryUseCase;
import com.riskgameapp.game.application.createBattle.CreateBattleUseCase;
import com.riskgameapp.game.application.createPlayer.CreatePlayerUseCase;
import com.riskgameapp.game.application.loseTroop.LoseTroopUseCase;
import com.riskgameapp.game.application.placeTroop.PlaceTroopUseCase;
import com.riskgameapp.game.application.removeTerritory.RemoveTerritoryUseCase;
import com.riskgameapp.game.infra.mongo.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
  @Bean
  public AddNewTroopUseCase addNewTroopUseCase(MongoAdapter mongoAdapter) {
    return new AddNewTroopUseCase(mongoAdapter);
  }

  @Bean
  public AddTerritoryUseCase addTerritoryUseCase(MongoAdapter mongoAdapter) {
    return new AddTerritoryUseCase(mongoAdapter);
  }

  @Bean
  public CreateBattleUseCase createBattleUseCase(MongoAdapter mongoAdapter) {
    return new CreateBattleUseCase(mongoAdapter);
  }

  @Bean
  public CreatePlayerUseCase createPlayerUseCase(MongoAdapter mongoAdapter) {
    return new CreatePlayerUseCase(mongoAdapter);
  }

  @Bean
  public LoseTroopUseCase loseTroopUseCase(MongoAdapter mongoAdapter) {
    return new LoseTroopUseCase(mongoAdapter);
  }

  @Bean
  public PlaceTroopUseCase placeTroopUseCase(MongoAdapter mongoAdapter) {
    return new PlaceTroopUseCase(mongoAdapter);
  }

  @Bean
  public RemoveTerritoryUseCase removeTerritoryUseCase(MongoAdapter mongoAdapter) {
    return new RemoveTerritoryUseCase(mongoAdapter);
  }
}
