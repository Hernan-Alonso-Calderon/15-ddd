package com.riskgameapp.game.domain.battle.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class ResolvedAttack extends DomainEvent {

  private final Integer attackingTroops;
  private final Integer attackerTerritoryTroops;
  private final Integer defenderTerritoryTroops;


  public ResolvedAttack(Integer attackingTroops,  Integer attackerTerritoryTroops, Integer defenderTerritoryTroops) {
    super(EventsEnum.RESOLVED_ATTACK.name());
    this.attackingTroops = attackingTroops;
    this.attackerTerritoryTroops = attackerTerritoryTroops;
    this.defenderTerritoryTroops = defenderTerritoryTroops;
  }

  public Integer getAttackingTroops() {
    return attackingTroops;
  }

  public Integer getAttackerTerritoryTroops() {
    return attackerTerritoryTroops;
  }

  public Integer getDefenderTerritoryTroops() {
    return defenderTerritoryTroops;
  }
}
