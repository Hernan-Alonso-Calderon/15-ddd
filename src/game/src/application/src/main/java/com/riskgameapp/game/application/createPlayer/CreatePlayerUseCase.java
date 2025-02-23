package com.riskgameapp.game.application.createPlayer;

import com.riskgameapp.game.application.shared.player.PlayerResponse;
import com.riskgameapp.game.application.shared.repositories.IEventsRepository;
import com.riskgameapp.game.domain.player.Player;
import com.riskgameapp.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.riskgameapp.game.application.shared.player.PlayerMapper.mapToPlayer;

public class CreatePlayerUseCase implements ICommandUseCase<CreatePlayerRequest, Mono<PlayerResponse>> {
  private final IEventsRepository repository;

  public CreatePlayerUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PlayerResponse> execute(CreatePlayerRequest request) {
    Player player = new Player(request.getName());

    player.getUncommittedEvents().forEach(repository::save);
    player.markEventsAsCommitted();
    return Mono.just(mapToPlayer(player));
  }
}
