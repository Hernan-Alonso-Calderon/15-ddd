package com.riskgameapp.game.application.addNewTroop;

import com.riskgameapp.game.application.shared.player.PlayerResponse;
import com.riskgameapp.game.application.shared.ports.IEventsRepositoryPort;
import com.riskgameapp.game.domain.player.Player;
import com.riskgameapp.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.riskgameapp.game.application.shared.player.PlayerMapper.mapToPlayer;

public class AddNewTroopUseCase implements ICommandUseCase<AddNewTroopRequest, Mono<PlayerResponse>>{
  private final IEventsRepositoryPort repository;

  public AddNewTroopUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PlayerResponse> execute(AddNewTroopRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Player player = Player.from(request.getAggregateId(), events);
        player.addNewTroop(request.getTroopQuantity());
        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();
        return mapToPlayer(player);
      });
  }
}
