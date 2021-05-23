package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class LocalDateConversion extends Conversion<LocalDate> {
  public LocalDateConversion() {
    super(LocalDate.class);
  }

  @Override public LocalDate convert(String value, List<String> patterns) {
    if (patterns.isEmpty()) {
      return LocalDate.parse(value);
    }

    for (String each : patterns) {
      try {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(each));
      } catch (DateTimeParseException ex) {
        // try the next pattern
      }
    }

    throw new IllegalArgumentException(
      "Could not parse value [" + value
        + "] using any of the patterns: " + patterns);
  }
}
