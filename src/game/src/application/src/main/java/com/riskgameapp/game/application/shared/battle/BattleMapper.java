package com.riskgameapp.game.application.shared.battle;

import com.riskgameapp.game.domain.battle.Battle;

public class BattleMapper {
  public static BattleResponse mapToBattle(Battle battle) {

    return new BattleResponse(
      battle.getIdentity().getValue(),
      new BattleResponse.Attack(
        battle.getIdentity().getValue(),
        battle.getAttack().getAttackingTroops().getValue(),
        battle.getAttack().getTerritoryTroops().getValue(),
        battle.getAttack().getDiceResults().getValue()
      ),
      new BattleResponse.Defense(
        battle.getIdentity().getValue(),
        battle.getDefense().getTerritoryTroops().getValue(),
        battle.getDefense().getDiceResults().getValue()
      ),
      new BattleResponse.Conquest(
        battle.getIdentity().getValue(),
        battle.getConquest().getAttackerLosses().getValue(),
        battle.getConquest().getDefenderLosses().getValue(),
        battle.getConquest().getIsConquest().getValue()
      )
    );
  }
}
