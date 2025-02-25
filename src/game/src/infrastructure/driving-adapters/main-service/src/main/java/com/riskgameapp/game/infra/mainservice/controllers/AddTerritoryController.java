package com.riskgameapp.game.infra.mainservice.controllers;

import com.riskgameapp.game.application.addTerritory.AddTerritoryRequest;
import com.riskgameapp.game.application.addTerritory.AddTerritoryUseCase;
import com.riskgameapp.game.application.shared.player.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/add-territory")
public class AddTerritoryController {
  private final AddTerritoryUseCase useCase;

  public AddTerritoryController(AddTerritoryUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<PlayerResponse> execute(@RequestBody AddTerritoryRequest request){
    return useCase.execute(request);
  }
}
