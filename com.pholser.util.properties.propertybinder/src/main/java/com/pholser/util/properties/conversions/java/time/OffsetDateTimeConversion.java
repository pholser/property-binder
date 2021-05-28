package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class OffsetDateTimeConversion extends Conversion<OffsetDateTime> {
  public OffsetDateTimeConversion() {
    super(OffsetDateTime.class);
  }

  @Override public OffsetDateTime convert(
    String value,
    List<String> patterns) {

    if (patterns.isEmpty()) {
      return OffsetDateTime.parse(value);
    }

    for (String each : patterns) {
      try {
        return OffsetDateTime.parse(value, DateTimeFormatter.ofPattern(each));
      } catch (DateTimeParseException ex) {
        // try the next pattern
      }
    }

    throw new IllegalArgumentException(
      "Could not parse value [" + value
        + "] using any of the patterns: " + patterns);
  }
}
