package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.Instant;
import java.util.List;

public class InstantConversion extends Conversion<Instant> {
  public InstantConversion() {
    super(Instant.class);
  }

  @Override public Instant convert(String value, List<String> patterns) {
    return Instant.parse(value);
  }
}
