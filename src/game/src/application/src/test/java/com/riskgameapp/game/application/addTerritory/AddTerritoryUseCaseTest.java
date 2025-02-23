package com.riskgameapp.game.application.addTerritory;

import com.riskgameapp.game.application.shared.repositories.IEventsRepository;
import com.riskgameapp.game.domain.player.events.CreatedPlayer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddTerritoryUseCaseTest {
  private final AddTerritoryUseCase useCase;
  private final IEventsRepository repository;

  public AddTerritoryUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new AddTerritoryUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new CreatedPlayer("Player 1")
      ));

    AddTerritoryRequest request = new AddTerritoryRequest("aggregateId", "RUSIA");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(1, response.getTerritories().size());
        assertEquals(request.getTerritoryName(), response.getTerritories().get(0).getTerritoryName());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

}