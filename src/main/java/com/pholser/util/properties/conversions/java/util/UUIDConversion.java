package com.pholser.util.properties.conversions.java.util;

import com.pholser.util.properties.Conversion;

import java.util.List;
import java.util.UUID;

public class UUIDConversion extends Conversion<UUID> {
  public UUIDConversion() {
    super(UUID.class);
  }

  @Override public UUID convert(String value, List<String> patterns) {
    return UUID.fromString(value);
  }
}
