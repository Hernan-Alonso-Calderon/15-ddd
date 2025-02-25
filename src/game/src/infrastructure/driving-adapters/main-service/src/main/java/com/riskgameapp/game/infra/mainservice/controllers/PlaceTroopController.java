package com.riskgameapp.game.infra.mainservice.controllers;

import com.riskgameapp.game.application.placeTroop.PlaceTroopRequest;
import com.riskgameapp.game.application.placeTroop.PlaceTroopUseCase;
import com.riskgameapp.game.application.shared.player.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/place-troop")
public class PlaceTroopController {
  private final PlaceTroopUseCase useCase;

  public PlaceTroopController(PlaceTroopUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<PlayerResponse> execute(@RequestBody PlaceTroopRequest request){
    return useCase.execute(request);
  }
}
