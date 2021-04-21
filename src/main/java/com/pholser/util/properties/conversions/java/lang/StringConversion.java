package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

public class StringConversion extends Conversion<String> {
  public StringConversion() {
    super(String.class);
  }

  @Override public String convert(String formatted, ParsedAs patterns) {
    return formatted;
  }
}
