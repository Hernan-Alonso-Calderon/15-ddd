package com.riskgameapp.game.application.addNewTroop;

import com.riskgameapp.shared.application.Request;

public class AddNewTroopRequest extends Request {
  private final Integer troopQuantity;

  public AddNewTroopRequest(String aggregateId, Integer troopQuantity) {
    super(aggregateId);
    this.troopQuantity = troopQuantity;
  }

  public Integer getTroopQuantity() {
    return troopQuantity;
  }
}
