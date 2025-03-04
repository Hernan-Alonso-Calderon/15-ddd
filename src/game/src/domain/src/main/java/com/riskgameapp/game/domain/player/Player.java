package com.riskgameapp.game.domain.player;

import com.riskgameapp.game.domain.player.entities.Territory;
import com.riskgameapp.game.domain.player.entities.Troop;
import com.riskgameapp.game.domain.player.events.AddedNewTroop;
import com.riskgameapp.game.domain.player.events.AddedTerritory;
import com.riskgameapp.game.domain.player.events.CreatedPlayer;
import com.riskgameapp.game.domain.player.events.LostTerritoryTroop;
import com.riskgameapp.game.domain.player.events.PlacedTroop;
import com.riskgameapp.game.domain.player.events.RemovedTerritory;
import com.riskgameapp.game.domain.player.values.Name;
import com.riskgameapp.game.domain.player.values.PlayerId;
import com.riskgameapp.shared.domain.generic.AggregateRoot;
import com.riskgameapp.shared.domain.generic.DomainEvent;

import java.util.List;

public class Player extends AggregateRoot<PlayerId> {
  private Name playerName;
  private List<Territory> territories;
  private Troop troop;

  // region Constructors
  public Player(String playerName) {
    super(new PlayerId());
    subscribe(new PlayerHandler(this));
    apply(new CreatedPlayer(playerName));
  }

  private Player(PlayerId identity) {
    super(identity);
    subscribe(new PlayerHandler(this));
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

  public void placeTroop(String territoryName, Integer troopQuantity){
    apply(new PlacedTroop(territoryName, troopQuantity));
  }

  public void addNewTroop(Integer troopQuantity){
    apply(new AddedNewTroop(troopQuantity));
  }

  public static Player from(final String identity, final List<DomainEvent> events) {
    Player player = new Player(PlayerId.of(identity));

    events.forEach(player::apply);
    return player;
  }
  //endregion

  //region Public Methods
  public Territory selectTerritory(String territoryName){
    for(Territory territory : territories){
      if(territory.getTerritoryName().getValue().equals(territoryName)){
        return territory;
      }
    }
    return null;
  }
  //endregion

  //region Private Methods
  //endregion

  // region Getters and Setters
  public Name getPlayerName() {
    return playerName;
  }

  public void setPlayerName(Name playerName) {
    this.playerName = playerName;
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
