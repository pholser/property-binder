package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateTimeFormatterClassConstantConversion
  extends Conversion<DateTimeFormatter> {

  public DateTimeFormatterClassConstantConversion() {
    super(DateTimeFormatter.class);
  }

  @Override public DateTimeFormatter convert(
    String value,
    List<String> patterns) {

    try {
      Field constant = DateTimeFormatter.class.getField(value);
      return (DateTimeFormatter) constant.get(null);
    } catch (NoSuchFieldException | IllegalAccessException ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}
