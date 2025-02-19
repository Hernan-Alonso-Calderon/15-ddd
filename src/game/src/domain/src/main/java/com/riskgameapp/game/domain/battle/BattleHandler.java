package com.riskgameapp.game.domain.battle;

import com.riskgameapp.game.domain.battle.entities.Attack;
import com.riskgameapp.game.domain.battle.entities.Conquest;
import com.riskgameapp.game.domain.battle.entities.Defense;
import com.riskgameapp.game.domain.battle.events.CreatedBattle;
import com.riskgameapp.game.domain.battle.events.ResolvedAttack;
import com.riskgameapp.game.domain.battle.values.DiceResults;
import com.riskgameapp.game.domain.battle.values.IsConquest;
import com.riskgameapp.game.domain.battle.values.Troops;
import com.riskgameapp.shared.domain.generic.DomainActionsContainer;
import com.riskgameapp.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class BattleHandler extends DomainActionsContainer {
  public BattleHandler(Battle battle) {
    add(createBattle(battle));
    add(resolveAttack(battle));
  }

  public Consumer<? extends DomainEvent> createBattle(Battle battle){
    return (CreatedBattle event) -> {
      List<Integer> list = new ArrayList<>(Arrays.asList(1, 1, 1));
      Attack attack = new Attack(Troops.of(event.getAttackingTroops()), Troops.of(event.getAttackerTerritoryTroops()), DiceResults.of(list));
      battle.setAttack(attack);
      Defense defense = new Defense(Troops.of(event.getDefenderTerritoryTroops()),DiceResults.of(list));
      battle.setDefense(defense);
      Conquest conquest = new Conquest(Troops.of(0),Troops.of(0), IsConquest.of(false));
      battle.setConquest(conquest);
    };
  }

  public Consumer<? extends DomainEvent> resolveAttack(Battle battle){
    return (ResolvedAttack event) -> {
      battle.getAttack().rollDice();
      battle.getDefense().rollDice();
      battle.getConquest().calculateLosses(battle.getAttack().getDiceResults().getValue(), battle.getDefense().getDiceResults().getValue());
      battle.getConquest().validateConquest(battle.getDefense().getTerritoryTroops().getValue());
    };
  }
}
