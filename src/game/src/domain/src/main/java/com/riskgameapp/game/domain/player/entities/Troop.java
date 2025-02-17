package com.riskgameapp.game.domain.player.entities;

import com.riskgameapp.game.domain.player.values.TroopId;
import com.riskgameapp.game.domain.player.values.Troops;
import com.riskgameapp.shared.domain.generic.Entity;

public class Troop extends Entity<TroopId> {
  private Troops baseTroops;
  private Troops newTroops;

  public Troop(TroopId identity, Troops baseTroops, Troops newTroops) {
    super(identity);
    this.baseTroops = baseTroops;
    this.newTroops = newTroops;
  }

  public Troop( Troops baseTroops, Troops newTroops) {
    super(new TroopId());
    this.baseTroops = baseTroops;
    this.newTroops = newTroops;
  }

  public Boolean placeNewTroops(Integer troopQuantity){
    if(newTroops.getValue() > troopQuantity){
      Integer result = newTroops.getValue()-troopQuantity;
      setNewTroops(Troops.of(result));
      Integer newBaseTroops = baseTroops.getValue()+troopQuantity;
      setBaseTroops(Troops.of(newBaseTroops));
      return true;
    }
    return false;
  }

  public void decreaseBaseTroops(Integer troopQuantity){
    if(baseTroops.getValue() > troopQuantity){
      Integer result = baseTroops.getValue()-troopQuantity;
      setBaseTroops(Troops.of(result));
    }
  }

  // region Getters and Setters
  public Troops getBaseTroops() {
    return baseTroops;
  }

  public void setBaseTroops(Troops baseTroops) {
    this.baseTroops = baseTroops;
  }

  public Troops getNewTroops() {
    return newTroops;
  }

  public void setNewTroops(Troops newTroops) {
    this.newTroops = newTroops;
  }
  //endregion
}
