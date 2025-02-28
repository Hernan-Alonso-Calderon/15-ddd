package com.riskgameapp.game.domain.player;

import com.riskgameapp.game.domain.player.entities.Territory;
import com.riskgameapp.game.domain.player.entities.Troop;
import com.riskgameapp.game.domain.player.events.AddedNewTroop;
import com.riskgameapp.game.domain.player.events.AddedTerritory;
import com.riskgameapp.game.domain.player.events.CreatedPlayer;
import com.riskgameapp.game.domain.player.events.LostTerritoryTroop;
import com.riskgameapp.game.domain.player.events.PlacedTroop;
import com.riskgameapp.game.domain.player.events.RemovedTerritory;
import com.riskgameapp.game.domain.player.values.Name;
import com.riskgameapp.game.domain.player.values.Troops;
import com.riskgameapp.shared.domain.generic.DomainActionsContainer;
import com.riskgameapp.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PlayerHandler extends DomainActionsContainer {
  public PlayerHandler(Player player) {
    add(createPlayer(player));
    add(addTerritory(player));
    add(removeTerritory(player));
    add(loseTerritoryTroop(player));
    add(addNewTroop(player));
    add(placeTroop(player));
  }

  public Consumer<? extends DomainEvent> createPlayer(Player player){
    return (CreatedPlayer event) -> {
      player.setPlayerName(Name.of(event.getPlayerName()));
      Troop newTroop = new Troop(Troops.of(0), Troops.of(0));
      player.setTroop(newTroop);
      player.setTerritories(new ArrayList<>());
    };
  }

  public Consumer<? extends DomainEvent> addTerritory(Player player) {
    return (AddedTerritory event) -> {
      // 1. Crear el nuevo territorio
      Territory newTerritory = new Territory(
        Name.of(event.getTerritoryName()),
        Troops.of(0)
      );

      List<Territory> currentTerritories = new ArrayList<>(player.getTerritories());

      boolean territoryExists = currentTerritories.stream()
        .anyMatch(t -> t.getTerritoryName().getValue().equals(newTerritory.getTerritoryName().getValue()));

      if (!territoryExists) {
        currentTerritories.add(newTerritory);
        player.setTerritories(currentTerritories); // Actualizar la lista
      }
    };
  }

  public Consumer<? extends DomainEvent> removeTerritory(Player player){
    return (RemovedTerritory event) -> {
      Territory selectTerritory = player.selectTerritory(event.getTerritoryName());
      player.getTerritories().remove(selectTerritory);
    };
  }

  public Consumer<? extends DomainEvent> loseTerritoryTroop(Player player){
    return (LostTerritoryTroop event) -> {
      Territory selectTerritory = player.selectTerritory(event.getTerritoryName());
      if(selectTerritory!= null){
        Boolean canLose = selectTerritory.decreaseTroop(event.getTroopQuantity());
        if(canLose){
          player.getTroop().decreaseBaseTroops(event.getTroopQuantity());
        }
      }
    };
  }

  public Consumer<? extends DomainEvent> addNewTroop(Player player){
    return (AddedNewTroop event) -> player.getTroop().setNewTroops(Troops.of(event.getTroopQuantity()));
  }

  public Consumer<? extends DomainEvent> placeTroop(Player player){
    return (PlacedTroop event) -> {
      Territory selectTerritory = player.selectTerritory(event.getTerritoryName());
      if(selectTerritory!= null){
        Boolean canPlace =  player.getTroop().placeNewTroops(event.getTroopQuantity());
        if(canPlace){
          selectTerritory.increaseTroop(event.getTroopQuantity());
        }
      }
    };
  }

}
