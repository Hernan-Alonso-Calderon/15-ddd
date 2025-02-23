package com.riskgameapp.game.application.removeTerritory;

import com.riskgameapp.game.application.shared.repositories.IEventsRepository;
import com.riskgameapp.game.domain.player.events.AddedTerritory;
import com.riskgameapp.game.domain.player.events.CreatedPlayer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RemoveTerritoryUseCaseTest {
  private final RemoveTerritoryUseCase useCase;
  private final IEventsRepository repository;

  public RemoveTerritoryUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new RemoveTerritoryUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new CreatedPlayer("Player 1"),
        new AddedTerritory("RUSIA")
      ));

    RemoveTerritoryRequest request = new RemoveTerritoryRequest("aggregateId", "RUSIA");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(0, response.getTerritories().size());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

}