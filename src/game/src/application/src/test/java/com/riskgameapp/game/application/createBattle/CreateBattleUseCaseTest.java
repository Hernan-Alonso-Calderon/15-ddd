package com.riskgameapp.game.application.createBattle;

import com.riskgameapp.game.application.shared.ports.IEventsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateBattleUseCaseTest {
  private final CreateBattleUseCase useCase;
  private final IEventsRepositoryPort repository;

  public CreateBattleUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new CreateBattleUseCase(repository);
  }

  @Test
  void executeSuccess() {
    CreateBattleRequest request = new CreateBattleRequest(3, 5, 2);
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAttackingTroops(), response.getAttack().getAttackingTroops());
        assertEquals(3, response.getAttack().getDiceResults().size());
        assertEquals(request.getAttackerTerritoryTroops(), response.getAttack().getAttackerTerritoryTroops());
        assertEquals(request.getDefenderTerritoryTroops(), response.getDefense().getDefenderTerritoryTroops());
        assertEquals(2, response.getDefense().getDiceResults().size());
      })
      .verifyComplete();
  }

}