package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import static java.util.Arrays.asList;

public class ShortConversion extends Conversion<Short> {
  public ShortConversion() {
    super(asList(short.class, Short.class));
  }

  @Override public Short convert(String formatted, ParsedAs patterns) {
    return Short.valueOf(formatted);
  }
}
