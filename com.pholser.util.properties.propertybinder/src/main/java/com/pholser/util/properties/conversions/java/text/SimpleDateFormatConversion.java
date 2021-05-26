package com.pholser.util.properties.conversions.java.text;

import com.pholser.util.properties.conversions.Conversion;

import java.text.SimpleDateFormat;
import java.util.List;

public class SimpleDateFormatConversion extends Conversion<SimpleDateFormat> {
  public SimpleDateFormatConversion() {
    super(SimpleDateFormat.class);
  }

  @Override public SimpleDateFormat convert(
    String value,
    List<String> patterns) {

    return new SimpleDateFormat(value);
  }
}
