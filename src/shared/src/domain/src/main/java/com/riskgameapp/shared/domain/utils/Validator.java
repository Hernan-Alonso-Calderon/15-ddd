package com.riskgameapp.shared.domain.utils;

import java.util.List;

public class Validator {

  public static void validateNotNull(Object value, String fieldName) {
    if (value == null) {
      throw new IllegalArgumentException(fieldName + " can't be null");
    }
  }

  public static void validatePositive(Integer value, String fieldName) {
    if (value == null || value < 0) {
      throw new IllegalArgumentException(fieldName + " must be a positive number");
    }
  }
  public static void validateListNotEmpty(List<?> list, String fieldName) {
    if (list == null || list.isEmpty()) {
      throw new IllegalArgumentException(fieldName + " can't be empty");
    }
  }

  public static void validateListSize(List<?> list, int maxSize, String fieldName) {

    if (list.size() > maxSize) {
      throw new IllegalArgumentException(fieldName + " can't have more than " + maxSize + " items");
    }
  }

  public static void validateIntegerInRange(Integer value, int min, int max, String fieldName) {
    if (value == null || value < min || value > max) {
      throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max);
    }
  }

}
