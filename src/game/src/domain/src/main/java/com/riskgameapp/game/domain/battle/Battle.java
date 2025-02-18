package com.riskgameapp.game.domain.battle;

import com.riskgameapp.game.domain.battle.entities.Attack;
import com.riskgameapp.game.domain.battle.entities.Conquest;
import com.riskgameapp.game.domain.battle.entities.Defense;
import com.riskgameapp.game.domain.battle.events.ResolvedAttack;
import com.riskgameapp.game.domain.battle.values.BattleId;
import com.riskgameapp.shared.domain.generic.AggregateRoot;
import com.riskgameapp.shared.domain.generic.DomainEvent;

import java.util.List;

public class Battle extends AggregateRoot<BattleId> {

  private Attack attack;
  private Defense defense;
  private Conquest conquest;

  // region Constructors
  public Battle() {
    super(new BattleId());
    subscribe(new BattleHandler(this));
  }

  private Battle(BattleId identity) {
    super(identity);
    subscribe(new BattleHandler(this));
  }
  //endregion

  //region Domain Actions
  public void resolveAttack(Integer attackingTroops, Integer attackerTerritoryTroops, Integer defenderTerritoryTroops){
    apply(new ResolvedAttack(attackingTroops, attackerTerritoryTroops, defenderTerritoryTroops));
  }

  public static Battle from(final String identity, final List<DomainEvent> events) {
    Battle battle = new Battle(BattleId.of(identity));

    events.forEach(battle::apply);
    return battle;
  }
  //endregion

  //region Public Methods
  //endregion

  //region Private Methods
  //endregion

  // region Getters and Setters
  public Attack getAttack() {
    return attack;
  }

  public void setAttack(Attack attack) {
    this.attack = attack;
  }

  public Defense getDefense() {
    return defense;
  }

  public void setDefense(Defense defense) {
    this.defense = defense;
  }

  public Conquest getConquest() {
    return conquest;
  }

  public void setConquest(Conquest conquest) {
    this.conquest = conquest;
  }
  //endregion
}
