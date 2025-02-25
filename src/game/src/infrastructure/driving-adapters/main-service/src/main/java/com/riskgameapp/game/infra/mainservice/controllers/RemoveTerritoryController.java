package com.riskgameapp.game.infra.mainservice.controllers;

import com.riskgameapp.game.application.removeTerritory.RemoveTerritoryRequest;
import com.riskgameapp.game.application.removeTerritory.RemoveTerritoryUseCase;
import com.riskgameapp.game.application.shared.player.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/remove-territory")
public class RemoveTerritoryController {
  private final RemoveTerritoryUseCase useCase;

  public RemoveTerritoryController(RemoveTerritoryUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<PlayerResponse> execute(@RequestBody RemoveTerritoryRequest request){
    return useCase.execute(request);
  }
}
