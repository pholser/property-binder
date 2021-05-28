package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.ZoneId;
import java.util.List;

public class ZoneIdConversion extends Conversion<ZoneId> {
  public ZoneIdConversion() {
    super(ZoneId.class);
  }

  @Override public ZoneId convert(String value, List<String> patterns) {
    return ZoneId.of(value);
  }
}
