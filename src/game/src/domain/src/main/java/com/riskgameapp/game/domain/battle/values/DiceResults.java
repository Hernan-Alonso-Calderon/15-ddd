package com.riskgameapp.game.domain.battle.values;

import com.riskgameapp.shared.domain.generic.IValueObject;
import com.riskgameapp.shared.domain.utils.Validator;

import java.util.List;

public class DiceResults implements IValueObject {
  private final List<Integer> value;

  private DiceResults(List<Integer> value) {
    this.value = value;
  }

  public static DiceResults of (List<Integer> value){
    return new DiceResults(value);
  }

  @Override
  public void validate() {
    String fieldName = "Dice results";
    Validator.validateListNotEmpty(value, fieldName);
    Validator.validateListSize(value, 3, fieldName);
    for (Integer result : value) {
      Validator.validateIntegerInRange(result, 1, 6, "Dice result");
    }
  }

  public List<Integer> getValue() {
    return value;
  }
}
