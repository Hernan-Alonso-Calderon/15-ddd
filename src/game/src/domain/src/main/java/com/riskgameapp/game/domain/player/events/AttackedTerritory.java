package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class AttackedTerritory extends DomainEvent {
  public AttackedTerritory() {
    super(EventsEnum.ATTACKED_TERRITORY.name());
  }
}
