package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import static java.util.Arrays.asList;

public class IntegerConversion extends Conversion<Integer> {
  public IntegerConversion() {
    super(asList(int.class, Integer.class));
  }

  @Override public Integer convert(String formatted, ParsedAs patterns) {
    return Integer.valueOf(formatted);
  }
}
