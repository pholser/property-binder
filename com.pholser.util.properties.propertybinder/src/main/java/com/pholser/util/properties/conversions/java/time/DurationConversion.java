package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.Duration;
import java.util.List;

public class DurationConversion extends Conversion<Duration> {
  public DurationConversion() {
    super(Duration.class);
  }

  @Override public Duration convert(String value, List<String> patterns) {
    return Duration.parse(value);
  }
}
