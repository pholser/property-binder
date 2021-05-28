package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.ZoneOffset;
import java.util.List;

public class ZoneOffsetConversion extends Conversion<ZoneOffset> {
  public ZoneOffsetConversion() {
    super(ZoneOffset.class);
  }

  @Override public ZoneOffset convert(String value, List<String> patterns) {
    return ZoneOffset.of(value);
  }
}
