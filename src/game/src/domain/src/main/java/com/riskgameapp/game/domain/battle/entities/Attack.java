package com.riskgameapp.game.domain.battle.entities;

import com.riskgameapp.game.domain.battle.values.AttackId;
import com.riskgameapp.game.domain.battle.values.DiceResults;
import com.riskgameapp.game.domain.battle.values.Troops;
import com.riskgameapp.shared.domain.generic.Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Attack extends Entity<AttackId> {

  private Troops attackingTroops;
  private Troops territoryTroops;
  private DiceResults diceResults;

  public Attack(AttackId identity, Troops attackingTroops, Troops territoryTroops, DiceResults diceResults) {
    super(identity);
    this.attackingTroops = attackingTroops;
    this.territoryTroops = territoryTroops;
    this.diceResults = diceResults;
  }

  public Attack(Troops attackingTroops, Troops territoryTroops, DiceResults diceResults) {
    super(new AttackId());
    this.attackingTroops = attackingTroops;
    this.territoryTroops = territoryTroops;
    this.diceResults = diceResults;
  }

  private Boolean validAttackingTroops(){
    Integer troopsQuantity = this.attackingTroops.getValue();
    if(troopsQuantity >= 1 && troopsQuantity <= 3){
      return true;
    }
    return false;
  }

  private Boolean canAttack(){
    Integer remainingTroops = this.territoryTroops.getValue() - this.attackingTroops.getValue();
    if(this.validAttackingTroops() && remainingTroops >= 1){
      return true;
    }
    return false;
  }

  public void rollDice(){
    if(this.canAttack()){
      List<Integer> results = new ArrayList<>();
      ThreadLocalRandom random = ThreadLocalRandom.current();
      Integer troopsQuantity = this.attackingTroops.getValue();
      for(int i = 0; i<troopsQuantity; i++){
        results.add(random.nextInt(1,7));
      }
      results.sort(Comparator.reverseOrder());
      setDiceResults(DiceResults.of(results));
    }
  }

  // region Getters and Setters
  public Troops getAttackingTroops() {
    return attackingTroops;
  }

  public void setAttackingTroops(Troops attackingTroops) {
    this.attackingTroops = attackingTroops;
  }

  public Troops getTerritoryTroops() {
    return territoryTroops;
  }

  public void setTerritoryTroops(Troops territoryTroops) {
    this.territoryTroops = territoryTroops;
  }

  public DiceResults getDiceResults() {
    return diceResults;
  }

  public void setDiceResults(DiceResults diceResults) {
    this.diceResults = diceResults;
  }
  //endregion
}
