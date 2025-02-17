package com.riskgameapp.shared.domain.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiskBoard {
  private static final Map<String, List<String>> BOARD = createBoard();

  private static Map<String, List<String>> createBoard() {
    Map<String, List<String>> map = new HashMap<>();

    map.put("Argentina", Arrays.asList("Brasil", "Perú"));
    map.put("Brasil", Arrays.asList("Argentina", "Venezuela", "Perú"));
    map.put("Perú", Arrays.asList("Argentina", "Brasil", "Venezuela"));
    map.put("Venezuela", Arrays.asList("Brasil", "Perú", "Centroamérica"));
    map.put("Centroamérica", Arrays.asList("Venezuela", "México"));
    map.put("México", Arrays.asList("Centroamérica", "EE.UU."));
    map.put("EE.UU.", Arrays.asList("México", "Canadá"));
    map.put("Canadá", Arrays.asList("EE.UU."));

    return Collections.unmodifiableMap(map); // Hace el mapa inmutable
  }

  public static Map<String, List<String>> getBoard() {
    return BOARD;
  }

  public static List<String> getNeighbors(String territory) {
    return BOARD.getOrDefault(territory, Collections.emptyList());
  }

}
