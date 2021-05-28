package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class YearMonthConversion extends Conversion<YearMonth> {
  public YearMonthConversion() {
    super(YearMonth.class);
  }

  @Override public YearMonth convert(String value, List<String> patterns) {
    if (patterns.isEmpty()) {
      return YearMonth.parse(value);
    }

    for (String each : patterns) {
      try {
        return YearMonth.parse(value, DateTimeFormatter.ofPattern(each));
      } catch (DateTimeParseException ex) {
        // try the next pattern
      }
    }

    throw new IllegalArgumentException(
      "Could not parse value [" + value
        + "] using any of the patterns: " + patterns);
  }
}
