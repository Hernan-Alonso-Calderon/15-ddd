package com.riskgameapp.game.application.createPlayer;

import com.riskgameapp.shared.application.Request;

public class CreatePlayerRequest extends Request {
  private final String name;

  protected CreatePlayerRequest(String name) {
    super(null);
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
