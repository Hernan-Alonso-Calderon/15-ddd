package com.riskgameapp.game.application.shared.player;
import java.util.List;

public class PlayerResponse {
  private final String playerId;
  private final String name;
  private final List<Territory> territories;
  private final Troop troop;

  public PlayerResponse(String playerId, String name, List<Territory> territories, Troop troop) {
    this.playerId = playerId;
    this.name = name;
    this.territories = territories;
    this.troop = troop;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getName() {
    return name;
  }

  public List<Territory> getTerritories() {
    return territories;
  }

  public Troop getTroop() {
    return troop;
  }

  public static class Territory {
    private final String id;
    private final String territoryName;
    private final Integer troopCount;

    public Territory(String id, String territoryName, Integer troopCount) {
      this.id = id;
      this.territoryName = territoryName;
      this.troopCount = troopCount;
    }

    public String getId() {
      return id;
    }

    public String getTerritoryName() {
      return territoryName;
    }

    public Integer getTroopCount() {
      return troopCount;
    }
  }

  public static class Troop {
    private final String id;
    private final Integer baseTroops;
    private final Integer newTroops;

    public Troop(String id, Integer baseTroops, Integer newTroops) {
      this.id = id;
      this.baseTroops = baseTroops;
      this.newTroops = newTroops;
    }

    public String getId() {
      return id;
    }

    public Integer getBaseTroops() {
      return baseTroops;
    }

    public Integer getNewTroops() {
      return newTroops;
    }
  }
}
