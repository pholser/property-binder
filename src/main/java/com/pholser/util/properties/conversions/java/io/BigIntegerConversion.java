package com.pholser.util.properties.conversions.java.io;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import java.math.BigInteger;

public class BigIntegerConversion extends Conversion<BigInteger> {
  public BigIntegerConversion() {
    super(BigInteger.class);
  }

  @Override public BigInteger convert(String formatted, ParsedAs patterns) {
    return new BigInteger(formatted);
  }
}
