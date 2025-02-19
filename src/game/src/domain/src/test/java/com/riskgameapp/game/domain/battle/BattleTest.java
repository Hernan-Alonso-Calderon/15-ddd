package com.riskgameapp.game.domain.battle;

import com.riskgameapp.game.domain.battle.events.CreatedBattle;
import com.riskgameapp.game.domain.battle.events.ResolvedAttack;
import com.riskgameapp.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BattleTest {
  private Battle battle;

  @BeforeEach
  void setUp(){
    battle = new Battle(1, 2, 1);
  }

  @Test
  void createBattleSuccess(){
    assertNotNull(battle.getAttack());
    assertNotNull(battle.getDefense());
    assertNotNull(battle.getConquest());
    assertEquals(1, battle.getAttack().getAttackingTroops().getValue());
    assertEquals(2, battle.getAttack().getTerritoryTroops().getValue());
    assertEquals(1, battle.getDefense().getTerritoryTroops().getValue());
    assertEquals(0, battle.getConquest().getAttackerLosses().getValue());
    assertEquals(0, battle.getConquest().getDefenderLosses().getValue());
    assertEquals(false, battle.getConquest().getIsConquest().getValue());
    assertEquals(1,battle.getUncommittedEvents().size());
    assertInstanceOf(CreatedBattle.class, battle.getUncommittedEvents().get(0));
  }

  @Test
  void createBattleFailed(){
    assertThrows(IllegalArgumentException.class, () -> new Battle(-1, -2, -1));
  }

  @Test
  void resolveAttackSuccess(){
    battle.resolveAttack();
    assertEquals(1, battle.getAttack().getDiceResults().getValue().size());
    assertEquals(1, battle.getDefense().getDiceResults().getValue().size());
    assertEquals(2,battle.getUncommittedEvents().size());
    assertInstanceOf(ResolvedAttack.class, battle.getUncommittedEvents().get(1));
  }

  @Test
  void fromMethodTest(){
    String battleId = "battleId";
    CreatedBattle event = new CreatedBattle(3, 5, 2);
    List<DomainEvent> events = new LinkedList<>();
    events.add(event);

    Battle newBattle = Battle.from(battleId, events);

    assertNotNull(newBattle);
    assertEquals(battleId, newBattle.getIdentity().getValue());
  }

}