package com.riskgameapp.game.domain.player.values;

import com.riskgameapp.shared.domain.generic.Identity;

public class TroopId extends Identity {

  public TroopId() {
    super();
  }

  private TroopId(String value){
    super(value);
  }

  public static TroopId of (String value){
    return new TroopId(value);
  }
}
