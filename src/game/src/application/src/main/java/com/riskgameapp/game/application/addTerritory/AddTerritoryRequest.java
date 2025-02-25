package com.riskgameapp.game.application.addTerritory;

import com.riskgameapp.shared.application.Request;

public class AddTerritoryRequest extends Request {
  private final String territoryName;

  public AddTerritoryRequest(String aggregateId, String territoryName) {
    super(aggregateId);
    this.territoryName = territoryName;
  }

  public String getTerritoryName() {
    return territoryName;
  }
}
