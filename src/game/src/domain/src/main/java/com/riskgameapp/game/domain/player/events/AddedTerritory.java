package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class AddedTerritory extends DomainEvent {
  public AddedTerritory() {
    super(EventsEnum.ADDED_TERRITORY.name());
  }
}
