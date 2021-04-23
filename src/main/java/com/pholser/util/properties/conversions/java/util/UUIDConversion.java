package com.pholser.util.properties.conversions.java.util;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import java.util.UUID;

public class UUIDConversion extends Conversion<UUID> {
  public UUIDConversion() {
    super(UUID.class);
  }

  @Override public UUID convert(String value, ParsedAs patterns) {
    return UUID.fromString(value);
  }
}
