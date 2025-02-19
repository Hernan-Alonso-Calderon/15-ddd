package com.riskgameapp.game.domain.battle.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class CreatedBattle extends DomainEvent {
  private final Integer attackingTroops;
  private final Integer attackerTerritoryTroops;
  private final Integer defenderTerritoryTroops;


  public CreatedBattle(Integer attackingTroops, Integer attackerTerritoryTroops, Integer defenderTerritoryTroops) {
    super(EventsEnum.CREATED_BATTLE.name());
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
