package com.riskgameapp.game.domain.battle.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class ResolvedAttack extends DomainEvent {

  public ResolvedAttack() {
    super(EventsEnum.RESOLVED_ATTACK.name());
  }
}
