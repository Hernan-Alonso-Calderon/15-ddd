package com.riskgameapp.game.application.createPlayer;

import com.riskgameapp.game.application.shared.repositories.IEventsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreatePlayerUseCaseTest {
  private final CreatePlayerUseCase useCase;
  private final IEventsRepository repository;

  public CreatePlayerUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new CreatePlayerUseCase(repository);
  }

  @Test
  void executeSuccess() {
    CreatePlayerRequest request = new CreatePlayerRequest("Player 1");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getName(), response.getName());
        assertEquals(0, response.getTerritories().size());
        assertEquals(0, response.getTroop().getBaseTroops());
        assertEquals(0, response.getTroop().getNewTroops());
      })
      .verifyComplete();
  }
}