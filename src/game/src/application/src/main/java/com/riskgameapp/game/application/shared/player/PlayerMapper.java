package com.riskgameapp.game.application.shared.player;

import com.riskgameapp.game.domain.player.Player;

public class PlayerMapper {
  public static PlayerResponse mapToPlayer(Player player) {

    return new PlayerResponse(
      player.getIdentity().getValue(),
      player.getName().getValue(),
      player.getTerritories().stream().map(item -> new PlayerResponse.Territory(item.getIdentity().getValue(), item.getTerritoryName().getValue(), item.getTroopCount().getValue())).toList(),
      new PlayerResponse.Troop(
        player.getTroop().getIdentity().getValue(),
        player.getTroop().getBaseTroops().getValue(),
        player.getTroop().getNewTroops().getValue()
      )
    );
  }
}
