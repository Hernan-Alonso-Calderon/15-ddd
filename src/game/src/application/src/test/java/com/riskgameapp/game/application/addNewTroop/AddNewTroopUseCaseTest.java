package com.riskgameapp.game.application.addNewTroop;

import com.riskgameapp.game.application.shared.ports.IEventsRepositoryPort;
import com.riskgameapp.game.domain.player.events.AddedTerritory;
import com.riskgameapp.game.domain.player.events.CreatedPlayer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddNewTroopUseCaseTest {
  private final AddNewTroopUseCase useCase;
  private final IEventsRepositoryPort repository;

  public AddNewTroopUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new AddNewTroopUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new CreatedPlayer("Player 1"),
        new AddedTerritory("RUSIA")
      ));

    AddNewTroopRequest request = new AddNewTroopRequest("aggregateId", 6);
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(1, response.getTerritories().size());
        assertEquals(0, response.getTerritories().get(0).getTroopCount());
        assertEquals(request.getTroopQuantity(), response.getTroop().getNewTroops());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

}