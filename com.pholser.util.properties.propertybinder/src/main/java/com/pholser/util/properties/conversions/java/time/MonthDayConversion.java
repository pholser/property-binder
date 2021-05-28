package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MonthDayConversion extends Conversion<MonthDay> {
  public MonthDayConversion() {
    super(MonthDay.class);
  }

  @Override public MonthDay convert(String value, List<String> patterns) {
    if (patterns.isEmpty()) {
      return MonthDay.parse(value);
    }

    for (String each : patterns) {
      try {
        return MonthDay.parse(value, DateTimeFormatter.ofPattern(each));
      } catch (DateTimeParseException ex) {
        // try the next pattern
      }
    }

    throw new IllegalArgumentException(
      "Could not parse value [" + value
        + "] using any of the patterns: " + patterns);
  }
}
