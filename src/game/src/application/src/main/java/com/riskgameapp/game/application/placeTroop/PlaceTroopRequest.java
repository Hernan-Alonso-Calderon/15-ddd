package com.riskgameapp.game.application.placeTroop;

import com.riskgameapp.shared.application.Request;

public class PlaceTroopRequest extends Request {
  private final String territoryName;
  private final Integer troopQuantity;

  public PlaceTroopRequest(String aggregateId, String territoryName, Integer troopQuantity) {
    super(aggregateId);
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
