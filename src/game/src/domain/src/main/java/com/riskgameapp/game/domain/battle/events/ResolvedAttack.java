package com.riskgameapp.game.domain.battle.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class ResolvedAttack extends DomainEvent {

  private final Integer attackingTroops;
  private final Integer defendingTroops;

  public ResolvedAttack(Integer attackingTroops, Integer defendingTroops) {
    super(EventsEnum.RESOLVED_ATTACK.name());
    this.attackingTroops = attackingTroops;
    this.defendingTroops = defendingTroops;
  }

  public Integer getAttackingTroops() {
    return attackingTroops;
  }

  public Integer getDefendingTroops() {
    return defendingTroops;
  }
}
