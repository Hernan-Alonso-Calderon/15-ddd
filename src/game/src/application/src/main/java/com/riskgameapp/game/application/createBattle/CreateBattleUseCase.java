package com.riskgameapp.game.application.createBattle;

import com.riskgameapp.game.application.shared.battle.BattleResponse;
import com.riskgameapp.game.application.shared.ports.IEventsRepositoryPort;
import com.riskgameapp.game.domain.battle.Battle;
import com.riskgameapp.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.riskgameapp.game.application.shared.battle.BattleMapper.mapToBattle;

public class CreateBattleUseCase implements ICommandUseCase<CreateBattleRequest, Mono<BattleResponse>> {
  private final IEventsRepositoryPort repository;

  public CreateBattleUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<BattleResponse> execute(CreateBattleRequest request) {
    Battle battle = new Battle(request.getAttackingTroops(), request.getAttackerTerritoryTroops(), request.getDefenderTerritoryTroops());
    battle.resolveAttack();

    battle.getUncommittedEvents().forEach(repository::save);
    battle.markEventsAsCommitted();
    return Mono.just(mapToBattle(battle));
  }
}
