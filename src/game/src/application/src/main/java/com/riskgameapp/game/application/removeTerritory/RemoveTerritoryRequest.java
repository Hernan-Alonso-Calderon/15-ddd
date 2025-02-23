package com.riskgameapp.game.application.removeTerritory;

import com.riskgameapp.shared.application.Request;

public class RemoveTerritoryRequest extends Request {
  private final String territoryName;

  protected RemoveTerritoryRequest(String aggregateId, String territoryName) {
    super(aggregateId);
    this.territoryName = territoryName;
  }

  public String getTerritoryName() {
    return territoryName;
  }
}
