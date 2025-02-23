package com.riskgameapp.game.application.placeTroop;

import com.riskgameapp.game.application.shared.repositories.IEventsRepository;
import com.riskgameapp.game.domain.player.events.AddedNewTroop;
import com.riskgameapp.game.domain.player.events.AddedTerritory;
import com.riskgameapp.game.domain.player.events.CreatedPlayer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlaceTroopUseCaseTest {
  private final PlaceTroopUseCase useCase;
  private final IEventsRepository repository;

  public PlaceTroopUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new PlaceTroopUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new CreatedPlayer("Player 1"),
        new AddedTerritory("RUSIA"),
        new AddedNewTroop(5)
      ));

    PlaceTroopRequest request = new PlaceTroopRequest("aggregateId", "RUSIA", 4);
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(1, response.getTerritories().size());
        assertEquals(request.getTerritoryName(), response.getTerritories().get(0).getTerritoryName());
        assertEquals(request.getTroopQuantity(), response.getTerritories().get(0).getTroopCount());
        assertEquals(1, response.getTroop().getNewTroops());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

}