package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class AddedTerritory extends DomainEvent {
  private final String territoryName;

  public AddedTerritory(String territoryName) {
    super(EventsEnum.ADDED_TERRITORY.name());
    this.territoryName = territoryName;
  }

  public String getTerritoryName() {
    return territoryName;
  }
}
