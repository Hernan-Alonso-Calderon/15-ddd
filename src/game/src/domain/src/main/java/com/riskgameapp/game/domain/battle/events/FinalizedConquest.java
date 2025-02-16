package com.riskgameapp.game.domain.battle.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class FinalizedConquest extends DomainEvent {
  public FinalizedConquest() {
    super(EventsEnum.FINALIZED_CONQUEST.name());
  }
}
