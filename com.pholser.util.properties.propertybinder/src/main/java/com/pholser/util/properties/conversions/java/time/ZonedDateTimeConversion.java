package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ZonedDateTimeConversion extends Conversion<ZonedDateTime> {
  public ZonedDateTimeConversion() {
    super(ZonedDateTime.class);
  }

  @Override public ZonedDateTime convert(
    String value,
    List<String> patterns) {

    if (patterns.isEmpty()) {
      return ZonedDateTime.parse(value);
    }

    for (String each : patterns) {
      try {
        return ZonedDateTime.parse(value, DateTimeFormatter.ofPattern(each));
      } catch (DateTimeParseException ex) {
        // try the next pattern
      }
    }

    throw new IllegalArgumentException(
      "Could not parse value [" + value
        + "] using any of the patterns: " + patterns);
  }
}
