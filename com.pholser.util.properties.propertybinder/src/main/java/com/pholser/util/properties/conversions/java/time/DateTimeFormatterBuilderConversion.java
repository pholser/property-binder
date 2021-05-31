package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class DateTimeFormatterBuilderConversion
  extends Conversion<DateTimeFormatterBuilder> {

  public DateTimeFormatterBuilderConversion() {
    super(DateTimeFormatterBuilder.class);
  }

  @Override public DateTimeFormatterBuilder convert(
    String value,
    List<String> patterns) {

    return new DateTimeFormatterBuilder().appendPattern(value);
  }
}
