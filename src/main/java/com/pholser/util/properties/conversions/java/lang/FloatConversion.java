package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import static java.util.Arrays.asList;

public class FloatConversion extends Conversion<Float> {
  public FloatConversion() {
    super(asList(float.class, Float.class));
  }

  @Override public Float convert(String formatted, ParsedAs patterns) {
    return Float.valueOf(formatted);
  }
}
