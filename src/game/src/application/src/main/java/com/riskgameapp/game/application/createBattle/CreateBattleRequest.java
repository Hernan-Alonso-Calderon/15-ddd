package com.riskgameapp.game.application.createBattle;

import com.riskgameapp.shared.application.Request;

public class CreateBattleRequest extends Request {
  private final Integer attackingTroops;
  private final Integer attackerTerritoryTroops;
  private final Integer defenderTerritoryTroops;

  public CreateBattleRequest(Integer attackingTroops, Integer attackerTerritoryTroops, Integer defenderTerritoryTroops) {
    super(null);
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
