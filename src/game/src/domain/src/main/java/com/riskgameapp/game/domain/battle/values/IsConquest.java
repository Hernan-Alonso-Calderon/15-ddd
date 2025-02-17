package com.riskgameapp.game.domain.battle.values;

import com.riskgameapp.shared.domain.generic.IValueObject;
import com.riskgameapp.shared.domain.utils.Validator;

public class IsConquest implements IValueObject {
  private final Boolean value;

  private IsConquest(Boolean value) {
    this.value = value;
    validate();
  }

  public static IsConquest of (Boolean value){
    return new IsConquest(value);
  }

  @Override
  public void validate() {
    Validator.validateNotNull(value, "Conquest flag");
  }

  public Boolean getValue() {
    return value;
  }
}
