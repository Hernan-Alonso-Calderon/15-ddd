package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class RemovedTerritory extends DomainEvent {
  private final String territoryName;

  public RemovedTerritory(String territoryName) {
    super(EventsEnum.REMOVED_TERRITORY.name());
    this.territoryName = territoryName;
  }

  public String getTerritoryName() {
    return territoryName;
  }
}
