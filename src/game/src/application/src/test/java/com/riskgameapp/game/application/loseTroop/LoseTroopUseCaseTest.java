package com.riskgameapp.game.application.loseTroop;

import com.riskgameapp.game.application.shared.repositories.IEventsRepository;
import com.riskgameapp.game.domain.player.events.AddedNewTroop;
import com.riskgameapp.game.domain.player.events.AddedTerritory;
import com.riskgameapp.game.domain.player.events.CreatedPlayer;
import com.riskgameapp.game.domain.player.events.PlacedTroop;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class LoseTroopUseCaseTest {
  private final LoseTroopUseCase useCase;
  private final IEventsRepository repository;

  public LoseTroopUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new LoseTroopUseCase(repository);
  }

  @Test
  void executeSuccess(){
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new CreatedPlayer("Player 1"),
        new AddedTerritory("RUSIA"),
        new AddedNewTroop(4),
        new PlacedTroop("RUSIA", 4)
      ));

    LoseTroopRequest request = new LoseTroopRequest("aggregateId", "RUSIA", 2);
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(1, response.getTerritories().size());
        assertEquals(request.getTerritoryName(), response.getTerritories().get(0).getTerritoryName());
        assertEquals(2, response.getTerritories().get(0).getTroopCount());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

}