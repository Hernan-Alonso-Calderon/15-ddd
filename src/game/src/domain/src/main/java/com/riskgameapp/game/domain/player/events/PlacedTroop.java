package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class PlacedTroop extends DomainEvent {

  public PlacedTroop() {
    super(EventsEnum.PLACED_TROOP.name());
  }
}
