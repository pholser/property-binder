package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class YearConversion extends Conversion<Year> {
  public YearConversion() {
    super(Year.class);
  }

  @Override public Year convert(String value, List<String> patterns) {
    if (patterns.isEmpty()) {
      return Year.parse(value);
    }

    for (String each : patterns) {
      try {
        return Year.parse(value, DateTimeFormatter.ofPattern(each));
      } catch (DateTimeParseException ex) {
        // try the next pattern
      }
    }

    throw new IllegalArgumentException(
      "Could not parse value [" + value
        + "] using any of the patterns: " + patterns);
  }
}
