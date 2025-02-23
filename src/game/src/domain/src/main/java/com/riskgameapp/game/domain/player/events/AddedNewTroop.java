package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class AddedNewTroop extends DomainEvent {
  private final Integer troopQuantity;

  public AddedNewTroop(Integer troopQuantity) {
    super(EventsEnum.ADD_NEW_TROOP.name());
    this.troopQuantity = troopQuantity;
  }

  public Integer getTroopQuantity() {
    return troopQuantity;
  }
}
