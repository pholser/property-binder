package com.pholser.util.properties.conversions.java.nio;

import com.pholser.util.properties.Conversion;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

public class CharsetConversion extends Conversion<Charset> {
  public CharsetConversion() {
    super(Charset.class);
  }

  @Override public Charset convert(String value, List<String> patterns) {
    try {
      return Charset.forName(value);
    } catch (IllegalCharsetNameException | UnsupportedCharsetException ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}
