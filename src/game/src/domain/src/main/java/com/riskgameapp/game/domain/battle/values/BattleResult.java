package com.riskgameapp.game.domain.battle.values;

import com.riskgameapp.shared.domain.generic.IValueObject;
import com.riskgameapp.shared.domain.utils.Validator;

public class BattleResult implements IValueObject {
  private final Integer attackerLosses;
  private final Integer defenderLosses;
  private final Boolean isConquest;

  private BattleResult(Integer attackerLosses, Integer defenderLosses, Boolean isConquest) {
    this.attackerLosses = attackerLosses;
    this.defenderLosses = defenderLosses;
    this.isConquest = isConquest;
    validate();
  }

  public static BattleResult of (Integer attackerLosses, Integer defenderLosses, Boolean isConquest){
    return new BattleResult(attackerLosses, defenderLosses, isConquest);
  }

  @Override
  public void validate() {
    Validator.validateNotNull(attackerLosses, "Attacker losses");
    Validator.validateNotNull(defenderLosses, "Defender losses");
    Validator.validateNotNull(isConquest, "Conquest flag");
    Validator.validatePositive(attackerLosses, "Attacker losses");
    Validator.validatePositive(defenderLosses, "Defender losses");
  }

  public Integer getAttackerLosses() {
    return attackerLosses;
  }

  public Integer getDefenderLosses() {
    return defenderLosses;
  }

  public Boolean getConquest() {
    return isConquest;
  }
}
