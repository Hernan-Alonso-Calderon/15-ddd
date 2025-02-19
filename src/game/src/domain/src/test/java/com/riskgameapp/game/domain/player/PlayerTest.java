package com.riskgameapp.game.domain.player;

import com.riskgameapp.game.domain.player.events.CreatedPlayer;
import com.riskgameapp.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {
  private Player player;

  @BeforeEach
  void setUp(){
    player = new Player("jugador1");
  }

  @Test
  void createPlayerSuccess(){
    assertNotNull(player.getName());
    assertNotNull(player.getTroop());
    assertNotNull(player.getTerritories());
    assertEquals("jugador1", player.getName().getValue());
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