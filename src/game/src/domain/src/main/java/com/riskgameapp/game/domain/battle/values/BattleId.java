package com.riskgameapp.game.domain.battle.values;

import com.riskgameapp.shared.domain.generic.Identity;

public class BattleId extends Identity {

  public BattleId() {
    super();
  }

  private BattleId(String value){
    super(value);
  }

  public static BattleId of (String value){
    return new BattleId(value);
  }
}
