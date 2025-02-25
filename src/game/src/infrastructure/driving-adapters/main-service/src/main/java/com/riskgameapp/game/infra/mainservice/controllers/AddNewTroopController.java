package com.riskgameapp.game.infra.mainservice.controllers;

import com.riskgameapp.game.application.addNewTroop.AddNewTroopRequest;
import com.riskgameapp.game.application.addNewTroop.AddNewTroopUseCase;
import com.riskgameapp.game.application.shared.player.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/add-new-troop")
public class AddNewTroopController {
  private final AddNewTroopUseCase useCase;

  public AddNewTroopController(AddNewTroopUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<PlayerResponse> execute(@RequestBody AddNewTroopRequest request){
    return useCase.execute(request);
  }
}
