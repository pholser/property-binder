package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import static java.util.Arrays.asList;

public class LongConversion extends Conversion<Long> {
  public LongConversion() {
    super(asList(long.class, Long.class));
  }

  @Override public Long convert(String formatted, ParsedAs patterns) {
    return Long.valueOf(formatted);
  }
}
