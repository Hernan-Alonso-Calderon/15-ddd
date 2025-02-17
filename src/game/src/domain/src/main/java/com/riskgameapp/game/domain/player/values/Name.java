package com.riskgameapp.game.domain.player.values;

import com.riskgameapp.shared.domain.generic.IValueObject;
import com.riskgameapp.shared.domain.utils.Validator;

public class Name implements IValueObject {
  private final String value;

  private Name(String value) {
    this.value = value;
    validate();
  }

  public static Name of (String value){
    return new Name(value);
  }

  @Override
  public void validate() {
    Validator.validateNotNull(value, "Name");
    Validator.validateNotBlank(value, "Name");
    Validator.validateAlphanumericCharacters(value, "Name");
  }

  public String getValue() {
    return value;
  }
}
