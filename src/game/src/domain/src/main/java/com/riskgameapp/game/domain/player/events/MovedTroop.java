package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class MovedTroop extends DomainEvent {
  public MovedTroop() {
    super(EventsEnum.MOVED_TROOP.name());
  }
}
