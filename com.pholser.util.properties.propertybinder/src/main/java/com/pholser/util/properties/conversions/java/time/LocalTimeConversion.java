package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class LocalTimeConversion extends Conversion<LocalTime> {
  public LocalTimeConversion() {
    super(LocalTime.class);
  }

  @Override public LocalTime convert(String value, List<String> patterns) {
    if (patterns.isEmpty()) {
      return LocalTime.parse(value);
    }

    for (String each : patterns) {
      try {
        return LocalTime.parse(value, DateTimeFormatter.ofPattern(each));
      } catch (DateTimeParseException ex) {
        // try the next pattern
      }
    }

    throw new IllegalArgumentException(
      "Could not parse value [" + value
        + "] using any of the patterns: " + patterns);
  }
}
