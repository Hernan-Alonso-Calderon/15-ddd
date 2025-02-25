package com.riskgameapp.game.application.createPlayer;

import com.riskgameapp.shared.application.Request;

public class CreatePlayerRequest extends Request {
  private String playerName;

  protected CreatePlayerRequest() {
    super(null);
    this.playerName = null;
  }

  public CreatePlayerRequest(String playerName) {
    super(null);
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }

}
