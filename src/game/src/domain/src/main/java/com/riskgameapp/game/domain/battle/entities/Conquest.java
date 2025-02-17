package com.riskgameapp.game.domain.battle.entities;

import com.riskgameapp.game.domain.battle.values.ConquestId;
import com.riskgameapp.game.domain.battle.values.IsConquest;
import com.riskgameapp.game.domain.battle.values.Troops;
import com.riskgameapp.shared.domain.generic.Entity;

import java.util.List;

public class Conquest extends Entity<ConquestId> {
  private Troops attackerLosses;
  private Troops defenderLosses;
  private IsConquest isConquest;

  public Conquest(ConquestId identity, Troops attackerLosses, Troops defenderLosses, IsConquest isConquest) {
    super(identity);
    this.attackerLosses = attackerLosses;
    this.defenderLosses = defenderLosses;
    this.isConquest = isConquest;
  }

  public Conquest(Troops attackerLosses, Troops defenderLosses, IsConquest isConquest) {
    super(new ConquestId());
    this.attackerLosses = attackerLosses;
    this.defenderLosses = defenderLosses;
    this.isConquest = isConquest;
  }

  public void calculateLosses(List<Integer> attackerDice, List<Integer> defenderDice){
    Integer diceQuantity = Math.abs(attackerDice.size() - defenderDice.size());
    Integer attackerLosses = 0;
    Integer defenderLosses = 0;
    for(int i = 0; i<diceQuantity; i++){
      if(attackerDice.get(i) > defenderDice.get(i)){
        defenderLosses++;
      }
      else{
        attackerLosses++;
      }
    }
    setAttackerLosses(Troops.of(attackerLosses));
    setDefenderLosses(Troops.of(defenderLosses));
  }

  public void validateConquest(Integer defenderTerritoryTroops){
    if(defenderTerritoryTroops-defenderLosses.getValue() < 1){
      setIsConquest(IsConquest.of(true));
    }else{
      setIsConquest(IsConquest.of(false));
    }
  }

  // region Getters and Setters
  public Troops getAttackerLosses() {
    return attackerLosses;
  }

  public void setAttackerLosses(Troops attackerLosses) {
    this.attackerLosses = attackerLosses;
  }

  public Troops getDefenderLosses() {
    return defenderLosses;
  }

  public void setDefenderLosses(Troops defenderLosses) {
    this.defenderLosses = defenderLosses;
  }

  public IsConquest getIsConquest() {
    return isConquest;
  }

  public void setIsConquest(IsConquest isConquest) {
    this.isConquest = isConquest;
  }
  //endregion
}
