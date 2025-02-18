package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class CreatedPlayer extends DomainEvent {
  private final String name;

  public CreatedPlayer(String name) {
    super(EventsEnum.CREATED_PLAYER.name());
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
