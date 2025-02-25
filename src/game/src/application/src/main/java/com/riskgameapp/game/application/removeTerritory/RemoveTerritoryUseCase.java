package com.riskgameapp.game.application.removeTerritory;

import com.riskgameapp.game.application.shared.player.PlayerResponse;
import com.riskgameapp.game.application.shared.ports.IEventsRepositoryPort;
import com.riskgameapp.game.domain.player.Player;
import com.riskgameapp.shared.application.ICommandUseCase;
import com.riskgameapp.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

import static com.riskgameapp.game.application.shared.player.PlayerMapper.mapToPlayer;

public class RemoveTerritoryUseCase implements ICommandUseCase<RemoveTerritoryRequest, Mono<PlayerResponse>> {
  private final IEventsRepositoryPort repository;

  public RemoveTerritoryUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PlayerResponse> execute(RemoveTerritoryRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        events.sort(Comparator.comparing(DomainEvent::getWhen));
        Player player = Player.from(request.getAggregateId(), events);
        player.removeTerritory(request.getTerritoryName());
        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();

        return mapToPlayer(player);
      });
  }
}
