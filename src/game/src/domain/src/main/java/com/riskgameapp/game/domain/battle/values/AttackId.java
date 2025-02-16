package com.riskgameapp.game.domain.battle.values;

import com.riskgameapp.shared.domain.generic.Identity;

public class AttackId extends Identity {

  public AttackId() {
    super();
  }

  private AttackId(String value){
    super(value);
  }

  public static AttackId of (String value){
    return new AttackId(value);
  }
}
