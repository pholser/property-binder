package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;

import java.util.List;

public class ClassForNameConversion extends Conversion<Class> {
  public ClassForNameConversion() {
    super(Class.class);
  }

  @Override public Class<?> convert(String value, List<String> patterns) {
    try {
      return Class.forName(value);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
