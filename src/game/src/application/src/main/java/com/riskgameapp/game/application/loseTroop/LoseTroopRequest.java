package com.riskgameapp.game.application.loseTroop;

import com.riskgameapp.shared.application.Request;

public class LoseTroopRequest extends Request {
  private final String territoryName;
  private final Integer troopQuantity;

  public LoseTroopRequest(String aggregateId, String territoryName, Integer troopQuantity) {
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
