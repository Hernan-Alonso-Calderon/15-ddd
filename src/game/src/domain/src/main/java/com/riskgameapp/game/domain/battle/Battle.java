package com.riskgameapp.game.domain.battle;

import com.riskgameapp.game.domain.battle.entities.Attack;
import com.riskgameapp.game.domain.battle.entities.Conquest;
import com.riskgameapp.game.domain.battle.entities.Defense;
import com.riskgameapp.game.domain.battle.events.ResolvedAttack;
import com.riskgameapp.game.domain.battle.values.BattleId;
import com.riskgameapp.game.domain.battle.values.BattleResult;
import com.riskgameapp.shared.domain.generic.AggregateRoot;

public class Battle extends AggregateRoot<BattleId> {

  private BattleResult battleResult;
  private Attack attack;
  private Defense defense;
  private Conquest conquest;

  // region Constructors
  public Battle() {
    super(new BattleId());
  }

  private Battle(BattleId identity) {
    super(identity);
  }
  //endregion

  //region Domain Actions
  public void resolveAttack(Integer attackingTroops, Integer defendingTroops){
    apply(new ResolvedAttack(attackingTroops, defendingTroops));
  }
  //endregion

  //region Public Methods
  //endregion

  //region Private Methods
  private void calculateLosses(){

  }
  //endregion

  // region Getters and Setters
  public BattleResult getBattleResult() {
    return battleResult;
  }

  public void setBattleResult(BattleResult battleResult) {
    this.battleResult = battleResult;
  }

  public Attack getAttack() {
    return attack;
  }

  public void setAttack(Attack attack) {
    this.attack = attack;
  }

  public Defense getDefense() {
    return defense;
  }

  public void setDefense(Defense defense) {
    this.defense = defense;
  }

  public Conquest getConquest() {
    return conquest;
  }

  public void setConquest(Conquest conquest) {
    this.conquest = conquest;
  }
  //endregion
}
