package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class AttackedTerritory extends DomainEvent {
  private final String territoryName;
  private final String defenderTerritoryName;

  public AttackedTerritory(String territoryName, String defenderTerritoryName) {
    super(EventsEnum.ATTACKED_TERRITORY.name());
    this.territoryName = territoryName;
    this.defenderTerritoryName = defenderTerritoryName;
  }

  public String getTerritoryName() {
    return territoryName;
  }

  public String getDefenderTerritoryName() {
    return defenderTerritoryName;
  }
}
