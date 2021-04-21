package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import static java.util.Arrays.asList;

public class BooleanConversion extends Conversion<Boolean> {
  public BooleanConversion() {
    super(asList(boolean.class, Boolean.class));
  }

  @Override public Boolean convert(String formatted, ParsedAs patterns) {
    return Boolean.valueOf(formatted);
  }
}
