package com.riskgameapp.game.domain.battle.values;

import com.riskgameapp.shared.domain.generic.IValueObject;
import com.riskgameapp.shared.domain.utils.Validator;

public class Troops implements IValueObject {
  private final Integer value;

  private Troops(Integer value) {
    this.value = value;
    validate();
  }

  public static Troops of (Integer value){
    return new Troops(value);
  }

  @Override
  public void validate() {
    Validator.validateNotNull(value, "Troops");
    Validator.validatePositive(value, "Troops");
  }

  public Integer getValue() {
    return value;
  }
}
