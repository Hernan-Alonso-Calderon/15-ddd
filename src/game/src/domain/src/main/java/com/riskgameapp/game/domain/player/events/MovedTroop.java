package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class MovedTroop extends DomainEvent {
  private final String originTerritory;
  private final String destinyTerritory;
  private final Integer troopQuantity;

  public MovedTroop(String originTerritory, String destinyTerritory, Integer troopQuantity) {
    super(EventsEnum.MOVED_TROOP.name());
    this.originTerritory = originTerritory;
    this.destinyTerritory = destinyTerritory;
    this.troopQuantity = troopQuantity;
  }

  public String getOriginTerritory() {
    return originTerritory;
  }

  public String getDestinyTerritory() {
    return destinyTerritory;
  }

  public Integer getTroopQuantity() {
    return troopQuantity;
  }
}
