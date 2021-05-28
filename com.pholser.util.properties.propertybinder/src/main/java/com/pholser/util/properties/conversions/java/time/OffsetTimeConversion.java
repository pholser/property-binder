package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class OffsetTimeConversion extends Conversion<OffsetTime> {
  public OffsetTimeConversion() {
    super(OffsetTime.class);
  }

  @Override public OffsetTime convert(String value, List<String> patterns) {
    if (patterns.isEmpty()) {
      return OffsetTime.parse(value);
    }

    for (String each : patterns) {
      try {
        return OffsetTime.parse(value, DateTimeFormatter.ofPattern(each));
      } catch (DateTimeParseException ex) {
        // try the next pattern
      }
    }

    throw new IllegalArgumentException(
      "Could not parse value [" + value
        + "] using any of the patterns: " + patterns);
  }
}
