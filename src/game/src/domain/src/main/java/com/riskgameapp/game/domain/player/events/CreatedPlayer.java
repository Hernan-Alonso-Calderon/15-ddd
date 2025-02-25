package com.riskgameapp.game.domain.player.events;

import com.riskgameapp.shared.domain.generic.DomainEvent;

public class CreatedPlayer extends DomainEvent {
  private final String playerName;

  public CreatedPlayer(String playerName) {
    super(EventsEnum.CREATED_PLAYER.name());
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }
}
