package com.riskgameapp.game.domain.battle.values;

import com.riskgameapp.shared.domain.generic.Identity;

public class DefenseId extends Identity {
  public DefenseId() {
    super();
  }

  private DefenseId(String value){
    super(value);
  }

  public static DefenseId of (String value){
    return new DefenseId(value);
  }
}
