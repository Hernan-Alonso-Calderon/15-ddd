package com.riskgameapp.game.infra.mainservice.controllers;

import com.riskgameapp.game.application.createPlayer.CreatePlayerRequest;
import com.riskgameapp.game.application.createPlayer.CreatePlayerUseCase;
import com.riskgameapp.game.application.shared.player.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/create-player")
public class CreatePlayerController {
  private final CreatePlayerUseCase useCase;

  public CreatePlayerController(CreatePlayerUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<PlayerResponse> execute(@RequestBody CreatePlayerRequest request){
    return useCase.execute(request);
  }
}
