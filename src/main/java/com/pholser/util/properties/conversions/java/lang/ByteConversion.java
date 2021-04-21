package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import static java.util.Arrays.asList;

public class ByteConversion extends Conversion<Byte> {
  public ByteConversion() {
    super(asList(byte.class, Byte.class));
  }

  @Override public Byte convert(String formatted, ParsedAs patterns) {
    return Byte.valueOf(formatted);
  }
}
