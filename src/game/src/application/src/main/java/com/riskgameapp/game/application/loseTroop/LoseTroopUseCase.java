package com.riskgameapp.game.application.loseTroop;

import com.riskgameapp.game.application.shared.player.PlayerResponse;
import com.riskgameapp.game.application.shared.ports.IEventsRepositoryPort;
import com.riskgameapp.game.domain.player.Player;
import com.riskgameapp.shared.application.ICommandUseCase;
import com.riskgameapp.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

import static com.riskgameapp.game.application.shared.player.PlayerMapper.mapToPlayer;

public class LoseTroopUseCase implements ICommandUseCase<LoseTroopRequest, Mono<PlayerResponse>> {
  private final IEventsRepositoryPort repository;

  public LoseTroopUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PlayerResponse> execute(LoseTroopRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        events.sort(Comparator.comparing(DomainEvent::getWhen));
        Player player = Player.from(request.getAggregateId(), events);
        player.loseTerritoryTroop(request.getTerritoryName(), request.getTroopQuantity());
        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();

        return mapToPlayer(player);
      });
  }
}
