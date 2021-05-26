package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateTimeFormatterConversion
  extends Conversion<DateTimeFormatter> {

  public DateTimeFormatterConversion() {
    super(DateTimeFormatter.class);
  }

  @Override public DateTimeFormatter convert(
    String value,
    List<String> patterns) {

    return DateTimeFormatter.ofPattern(value);
  }
}
