package com.riskgameapp.game.domain.player.entities;

import com.riskgameapp.game.domain.player.values.Name;
import com.riskgameapp.game.domain.player.values.TerritoryId;
import com.riskgameapp.game.domain.player.values.Troops;
import com.riskgameapp.shared.domain.generic.Entity;
import com.riskgameapp.shared.domain.utils.RiskBoard;

import java.util.List;

public class Territory extends Entity<TerritoryId> {
  private Name territoryName;
  private Troops troopCount;

  public Territory(TerritoryId identity, Name territoryName, Troops troopCount) {
    super(identity);
    this.territoryName = territoryName;
    this.troopCount = troopCount;
  }

  public Territory(Name territoryName, Troops troopCount) {
    super(new TerritoryId());
    this.territoryName = territoryName;
    this.troopCount = troopCount;
  }

  public Boolean validateMovement(String territoryToMove){
    List<String> neighbors = RiskBoard.getNeighbors(territoryName.getValue());
    return neighbors.contains(territoryToMove);
  }

  public void increaseTroop(Integer troopQuantity){
    Integer newTroopCount = troopCount.getValue() + troopQuantity;
    setTroopCount(Troops.of(newTroopCount));
  }

  public Boolean decreaseTroop(Integer troopQuantity){
    Integer newTroopCount = troopCount.getValue() - troopQuantity;
    if(newTroopCount >= 0){
      setTroopCount(Troops.of(newTroopCount));
      return true;
    }
    return false;
  }

  // region Getters and Setters
  public Name getTerritoryName() {
    return territoryName;
  }

  public void setTerritoryName(Name territoryName) {
    this.territoryName = territoryName;
  }

  public Troops getTroopCount() {
    return troopCount;
  }

  public void setTroopCount(Troops troopCount) {
    this.troopCount = troopCount;
  }
  //endregion
}
