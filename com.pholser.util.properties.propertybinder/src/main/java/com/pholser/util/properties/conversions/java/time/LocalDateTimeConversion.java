package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class LocalDateTimeConversion extends Conversion<LocalDateTime> {
  public LocalDateTimeConversion() {
    super(LocalDateTime.class);
  }

  @Override public LocalDateTime convert(
    String value,
    List<String> patterns) {

    if (patterns.isEmpty()) {
      return LocalDateTime.parse(value);
    }

    for (String each : patterns) {
      try {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(each));
      } catch (DateTimeParseException ex) {
        // try the next pattern
      }
    }

    throw new IllegalArgumentException(
      "Could not parse value [" + value
        + "] using any of the patterns: " + patterns);
  }
}
