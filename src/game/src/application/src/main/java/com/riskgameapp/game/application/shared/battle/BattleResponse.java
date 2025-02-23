package com.riskgameapp.game.application.shared.battle;

import java.util.List;

public class BattleResponse {
  private final String battleId;
  private final Attack attack;
  private final Defense defense;
  private final Conquest conquest;

  public BattleResponse(String battleId, Attack attack, Defense defense, Conquest conquest) {
    this.battleId = battleId;
    this.attack = attack;
    this.defense = defense;
    this.conquest = conquest;
  }

  public String getBattleId() {
    return battleId;
  }

  public Attack getAttack() {
    return attack;
  }

  public Defense getDefense() {
    return defense;
  }

  public Conquest getConquest() {
    return conquest;
  }

  public static class Attack {
    private final String id;
    private final Integer attackingTroops;
    private final Integer attackerTerritoryTroops;
    private final List<Integer> diceResults;

    public Attack(String id, Integer attackingTroops, Integer attackerTerritoryTroops, List<Integer> diceResults) {
      this.id = id;
      this.attackingTroops = attackingTroops;
      this.attackerTerritoryTroops = attackerTerritoryTroops;
      this.diceResults = diceResults;
    }

    public String getId() {
      return id;
    }

    public Integer getAttackingTroops() {
      return attackingTroops;
    }

    public Integer getAttackerTerritoryTroops() {
      return attackerTerritoryTroops;
    }

    public List<Integer> getDiceResults() {
      return diceResults;
    }
  }

  public static class Defense {
    private final String id;
    private final Integer defenderTerritoryTroops;
    private final List<Integer> diceResults;

    public Defense(String id, Integer defenderTerritoryTroops, List<Integer> diceResults) {
      this.id = id;
      this.defenderTerritoryTroops = defenderTerritoryTroops;
      this.diceResults = diceResults;
    }

    public String getId() {
      return id;
    }

    public Integer getDefenderTerritoryTroops() {
      return defenderTerritoryTroops;
    }

    public List<Integer> getDiceResults() {
      return diceResults;
    }
  }

  public static class Conquest {
    private final String id;
    private final Integer attackerLosses;
    private final Integer defenderLosses;
    private final Boolean isConquest;

    public Conquest(String id, Integer attackerLosses, Integer defenderLosses, Boolean isConquest) {
      this.id = id;
      this.attackerLosses = attackerLosses;
      this.defenderLosses = defenderLosses;
      this.isConquest = isConquest;
    }

    public String getId() {
      return id;
    }

    public Integer getAttackerLosses() {
      return attackerLosses;
    }

    public Integer getDefenderLosses() {
      return defenderLosses;
    }

    public Boolean getIsConquest() {
      return isConquest;
    }
  }
}
