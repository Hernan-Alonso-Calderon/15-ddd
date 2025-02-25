package com.riskgameapp.game.infra.mainservice.controllers;

import com.riskgameapp.game.application.loseTroop.LoseTroopRequest;
import com.riskgameapp.game.application.loseTroop.LoseTroopUseCase;
import com.riskgameapp.game.application.shared.player.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/lose-troop")
public class LoseTroopController {
  private final LoseTroopUseCase useCase;

  public LoseTroopController(LoseTroopUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<PlayerResponse> execute(@RequestBody LoseTroopRequest request){
    return useCase.execute(request);
  }
}
