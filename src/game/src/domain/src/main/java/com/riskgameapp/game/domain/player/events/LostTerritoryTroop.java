package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class LostTerritoryTroop extends DomainEvent {
  private final String territoryName;
  private final Integer troopQuantity;

  public LostTerritoryTroop(String territoryName, Integer troopQuantity) {
    super(EventsEnum.LOST_TERRITORY_TROOP.name());
    this.territoryName = territoryName;
    this.troopQuantity = troopQuantity;
  }

  public String getTerritoryName() {
    return territoryName;
  }

  public Integer getTroopQuantity() {
    return troopQuantity;
  }
}
