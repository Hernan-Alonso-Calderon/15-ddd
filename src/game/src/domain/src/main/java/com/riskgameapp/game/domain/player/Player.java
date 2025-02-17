package com.riskgameapp.game.domain.player;

import com.riskgameapp.game.domain.player.entities.Territory;
import com.riskgameapp.game.domain.player.entities.Troop;
import com.riskgameapp.game.domain.player.events.AddedTerritory;
import com.riskgameapp.game.domain.player.events.AttackedTerritory;
import com.riskgameapp.game.domain.player.events.LostTerritoryTroop;
import com.riskgameapp.game.domain.player.events.MovedTroop;
import com.riskgameapp.game.domain.player.events.PlacedTroop;
import com.riskgameapp.game.domain.player.events.RemovedTerritory;
import com.riskgameapp.game.domain.player.values.Name;
import com.riskgameapp.game.domain.player.values.PlayerId;
import com.riskgameapp.game.domain.player.values.Troops;
import com.riskgameapp.shared.domain.generic.AggregateRoot;

import java.util.List;

public class Player extends AggregateRoot<PlayerId> {
  private Name name;
  private List<Territory> territories;
  private Troop troop;

  // region Constructors
  public Player() {
    super(new PlayerId());
  }

  private Player(PlayerId identity) {
    super(identity);
  }
  //endregion

  //region Domain Actions
  public void addTerritory(String territoryName){
    apply(new AddedTerritory(territoryName));
  }

  public void removeTerritory(String territoryName){
    apply(new RemovedTerritory(territoryName));
  }

  public void loseTerritoryTroop(String territoryName, Integer troopQuantity){
    apply(new LostTerritoryTroop(territoryName, troopQuantity));
  }

  public void attackTerritory(String territoryName, String defenderTerritoryName){
    apply(new AttackedTerritory(territoryName, defenderTerritoryName));
  }

  public void moveTroop(String originTerritory, String destinyTerritory, Integer troopQuantity){
    apply(new MovedTroop(originTerritory, destinyTerritory, troopQuantity));
  }

  public void placeTroop(String territoryName, Integer troopQuantity){
    apply(new PlacedTroop(territoryName, troopQuantity));
  }
  //endregion

  //region Public Methods
  //endregion

  //region Private Methods
  private void add(String territoryName){
    Territory newTerritory = new Territory(Name.of(territoryName), Troops.of(0));
    territories.add(newTerritory);
  }

  private void remove(String territoryName){
    Territory selectTerritory = this.selectTerritory(territoryName);
    territories.remove(selectTerritory);
  }

  private Territory selectTerritory(String territoryName){
    for(Territory territory : territories){
      if(territory.getTerritoryName().getValue().equals(territoryName)){
        return territory;
      }
    }
    return null;
  }

  private void place(String territoryName, Integer troopQuantity){
    Territory selectTerritory = this.selectTerritory(territoryName);
    if(selectTerritory!= null){
      Boolean canPlace =  troop.placeNewTroops(troopQuantity);
      if(canPlace){
        selectTerritory.increaseTroop(troopQuantity);
      }
    }
  }

  private void move(String originTerritory, String destinyTerritory, Integer troopQuantity){
    Territory origin = this.selectTerritory(originTerritory);
    Territory destiny = this.selectTerritory(destinyTerritory);
    if(origin != null && destiny != null && origin.validateMovement((destinyTerritory))){
      Boolean canMove = origin.decreaseTroop(troopQuantity);
      if(canMove){
        destiny.increaseTroop(troopQuantity);
      }
    }
  }

  private void loseTroop(String territoryName, Integer troopQuantity){
    Territory selectTerritory = this.selectTerritory(territoryName);
    if(selectTerritory!= null){
      Boolean canLose = selectTerritory.decreaseTroop(troopQuantity);
      if(canLose){
        troop.decreaseBaseTroops(troopQuantity);
      }
    }
  }

  private Boolean attack(String territoryName, String defenderTerritoryName){
    Territory selectTerritory = this.selectTerritory(territoryName);
    return selectTerritory.validateMovement(defenderTerritoryName);
  }
  //endregion

  // region Getters and Setters
  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public List<Territory> getTerritories() {
    return territories;
  }

  public void setTerritories(List<Territory> territories) {
    this.territories = territories;
  }

  public Troop getTroop() {
    return troop;
  }

  public void setTroop(Troop troop) {
    this.troop = troop;
  }
  //endregion

}
