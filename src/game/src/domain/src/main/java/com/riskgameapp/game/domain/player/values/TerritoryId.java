package com.riskgameapp.game.domain.player.values;

import com.riskgameapp.shared.domain.generic.Identity;

public class TerritoryId extends Identity {

  public TerritoryId() {
    super();
  }

  private TerritoryId(String value){
    super(value);
  }

  public static TerritoryId of (String value){
    return new TerritoryId(value);
  }
}
