package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class RemovedTerritory extends DomainEvent {
  public RemovedTerritory() {
    super(EventsEnum.REMOVED_TERRITORY.name());
  }
}
