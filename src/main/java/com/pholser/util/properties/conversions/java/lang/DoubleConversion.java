package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import static java.util.Arrays.asList;

public class DoubleConversion extends Conversion<Double> {
  public DoubleConversion() {
    super(asList(double.class, Double.class));
  }

  @Override public Double convert(String formatted, ParsedAs patterns) {
    return Double.valueOf(formatted);
  }
}
