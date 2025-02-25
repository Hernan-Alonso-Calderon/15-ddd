package com.riskgameapp.game.infra.mainservice.controllers;

import com.riskgameapp.game.application.createBattle.CreateBattleRequest;
import com.riskgameapp.game.application.createBattle.CreateBattleUseCase;
import com.riskgameapp.game.application.shared.battle.BattleResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/create-battle")
public class CreateBattleController {
  private final CreateBattleUseCase useCase;

  public CreateBattleController(CreateBattleUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<BattleResponse> execute(@RequestBody CreateBattleRequest request){
    return useCase.execute(request);
  }
}
