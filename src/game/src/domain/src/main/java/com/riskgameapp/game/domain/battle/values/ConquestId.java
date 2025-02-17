package com.riskgameapp.game.domain.battle.values;

import com.riskgameapp.shared.domain.generic.Identity;

public class ConquestId extends Identity {
  public ConquestId() {
    super();
  }

  private ConquestId(String value){
    super(value);
  }

  public static ConquestId of (String value){
    return new ConquestId(value);
  }
}
