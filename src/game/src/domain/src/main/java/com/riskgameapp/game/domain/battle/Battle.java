package com.riskgameapp.game.domain.battle;

import com.riskgameapp.game.domain.battle.entities.Attack;
import com.riskgameapp.game.domain.battle.entities.Conquest;
import com.riskgameapp.game.domain.battle.entities.Defense;
import com.riskgameapp.game.domain.battle.events.ResolvedAttack;
import com.riskgameapp.game.domain.battle.values.BattleId;
import com.riskgameapp.game.domain.battle.values.DiceResults;
import com.riskgameapp.game.domain.battle.values.IsConquest;
import com.riskgameapp.game.domain.battle.values.Troops;
import com.riskgameapp.shared.domain.generic.AggregateRoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battle extends AggregateRoot<BattleId> {

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
  public void resolveAttack(Integer attackingTroops, Integer attackerTerritoryTroops, Integer defenderTerritoryTroops){
    apply(new ResolvedAttack(attackingTroops, attackerTerritoryTroops, defenderTerritoryTroops));
  }
  //endregion

  //region Public Methods
  //endregion

  //region Private Methods
  private void resolve(Integer attackingTroops, Integer attackerTerritoryTroops, Integer defenderTerritoryTroops){
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 1, 1));
    attack = new Attack(Troops.of(attackingTroops), Troops.of(attackerTerritoryTroops), DiceResults.of(list));
    attack.rollDice();
    defense = new Defense(Troops.of(defenderTerritoryTroops),DiceResults.of(list));
    defense.rollDice();
    conquest = new Conquest(Troops.of(0),Troops.of(0), IsConquest.of(false));
    conquest.calculateLosses(attack.getDiceResults().getValue(), defense.getDiceResults().getValue());
    conquest.validateConquest(defenderTerritoryTroops);
  }

  private void finalizeAttack(){
    //Notificar al agregado player sobre los cambios de las tropas en los territorios.
  }
  //endregion

  // region Getters and Setters
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
