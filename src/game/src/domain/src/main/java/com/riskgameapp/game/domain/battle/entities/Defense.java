package com.riskgameapp.game.domain.battle.entities;

import com.riskgameapp.game.domain.battle.values.DefenseId;
import com.riskgameapp.game.domain.battle.values.DiceResults;
import com.riskgameapp.game.domain.battle.values.Troops;
import com.riskgameapp.shared.domain.generic.Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Defense extends Entity<DefenseId> {

  private Troops territoryTroops;
  private DiceResults diceResults;

  public Defense(DefenseId identity, Troops territoryTroops, DiceResults diceResults) {
    super(identity);
    this.territoryTroops = territoryTroops;
    this.diceResults = diceResults;
  }

  public Defense(Troops territoryTroops, DiceResults diceResults) {
    super(new DefenseId());
    this.territoryTroops = territoryTroops;
    this.diceResults = diceResults;
  }

  private Boolean canDefend(){
    return this.territoryTroops.getValue() >= 1;
  }

  public void rollDice(){
    if(this.canDefend()){
      List<Integer> results = new ArrayList<>();
      ThreadLocalRandom random = ThreadLocalRandom.current();
      Integer troopsQuantity = this.territoryTroops.getValue() > 1? 2: 1;
      for(int i = 0; i<troopsQuantity; i++){
        results.add(random.nextInt(1,7));
      }
      results.sort(Comparator.reverseOrder());
      setDiceResults(DiceResults.of(results));
    }
  }

  // region Getters and Setters
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
