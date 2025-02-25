package com.riskgameapp.game.domain.player;

import com.riskgameapp.game.domain.player.entities.Territory;
import com.riskgameapp.game.domain.player.events.AddedTerritory;
import com.riskgameapp.game.domain.player.events.CreatedPlayer;
import com.riskgameapp.game.domain.player.events.LostTerritoryTroop;
import com.riskgameapp.game.domain.player.events.PlacedTroop;
import com.riskgameapp.game.domain.player.events.RemovedTerritory;
import com.riskgameapp.game.domain.player.values.Troops;
import com.riskgameapp.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {
  private Player player;

  @BeforeEach
  void setUp(){
    player = new Player("jugador1");
  }

  @Test
  void createPlayerSuccess(){
    assertNotNull(player.getPlayerName());
    assertNotNull(player.getTroop());
    assertNotNull(player.getTerritories());
    assertEquals("jugador1", player.getPlayerName().getValue());
    assertEquals(0, player.getTroop().getBaseTroops().getValue());
    assertEquals(0, player.getTroop().getNewTroops().getValue());
    assertEquals(0,player.getTerritories().size());
    assertEquals(1,player.getUncommittedEvents().size());
    assertInstanceOf(CreatedPlayer.class, player.getUncommittedEvents().get(0));
  }

  @Test
  void CreatePlayerFailed(){
    assertThrows(IllegalArgumentException.class, () -> new Player(""));
  }

  @Test
  void addTerritorySuccess(){
    player.addTerritory("Argentina");
    assertEquals(1,player.getTerritories().size());
    assertEquals("Argentina", player.getTerritories().get(0).getTerritoryName().getValue());
    assertEquals(2,player.getUncommittedEvents().size());
    assertInstanceOf(AddedTerritory.class, player.getUncommittedEvents().get(1));
  }

  @Test
  void addTerritoryFailed(){
    assertThrows(IllegalArgumentException.class, () -> player.addTerritory(""));
  }

  @Test
  void removeTerritorySuccess(){
    player.addTerritory("Argentina");
    player.removeTerritory("Argentina");
    assertEquals(0,player.getTerritories().size());
    assertEquals(3,player.getUncommittedEvents().size());
    assertInstanceOf(RemovedTerritory.class, player.getUncommittedEvents().get(2));
  }

  @Test
  void loseTerritoryTroopSuccess(){
    player.addTerritory("Brasil");
    player.addTerritory("Argentina");
    player.getTerritories().get(1).increaseTroop(2);
    player.getTroop().setBaseTroops(Troops.of(2));

    player.loseTerritoryTroop("Colombia", 1);
    player.loseTerritoryTroop("Brasil", 1);
    player.loseTerritoryTroop("Argentina", 1);
    assertEquals(2,player.getTerritories().size());
    assertEquals(1,player.getTroop().getBaseTroops().getValue());
    assertEquals(0,player.getTerritories().get(0).getTroopCount().getValue());
    assertEquals(1,player.getTerritories().get(1).getTroopCount().getValue());
    assertEquals(6,player.getUncommittedEvents().size());
    assertInstanceOf(LostTerritoryTroop.class, player.getUncommittedEvents().get(5));
  }

  @Test
  void placeTroopSuccess(){
    player.addTerritory("Argentina");
    player.addNewTroop(2);
    Player player2 = new Player("jugador2");
    player2.addTerritory("Brasil");

    player.placeTroop("Colombia", 1);
    player.placeTroop("Argentina", 1);
    player2.placeTroop("Brasil", 1);

    assertEquals(1,player.getTerritories().size());
    assertEquals(1,player.getTroop().getBaseTroops().getValue());
    assertEquals(1,player.getTroop().getNewTroops().getValue());
    assertEquals(1,player.getTerritories().get(0).getTroopCount().getValue());
    assertEquals(0,player2.getTerritories().get(0).getTroopCount().getValue());
    assertEquals(5,player.getUncommittedEvents().size());
    assertInstanceOf(PlacedTroop.class, player.getUncommittedEvents().get(4));
  }

  @Test
  void selectTerritoryTest(){
    player.addTerritory("Argentina");
    player.addTerritory("Brasil");
    player.addTerritory("Peru");
    Territory selectTerritory1 = player.selectTerritory("Peru");
    Territory selectTerritory2 = player.selectTerritory("Ecuador");
    assertNotNull(selectTerritory1);
    assertEquals("Peru", selectTerritory1.getTerritoryName().getValue());
    assertNull(selectTerritory2);
  }

  @Test
  void fromMethodTest(){
    String playerId = "playerId";
    CreatedPlayer event = new CreatedPlayer("jugador1");
    List<DomainEvent> events = new LinkedList<>();
    events.add(event);
    Player newPlayer = Player.from(playerId, events);

    assertNotNull(newPlayer);
    assertEquals(playerId, newPlayer.getIdentity().getValue());
  }
}