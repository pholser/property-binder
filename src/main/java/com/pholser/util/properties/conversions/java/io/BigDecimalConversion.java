package com.pholser.util.properties.conversions.java.io;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import java.math.BigDecimal;

public class BigDecimalConversion extends Conversion<BigDecimal> {
  public BigDecimalConversion() {
    super(BigDecimal.class);
  }

  @Override public BigDecimal convert(String formatted, ParsedAs patterns) {
    return new BigDecimal(formatted);
  }
}
