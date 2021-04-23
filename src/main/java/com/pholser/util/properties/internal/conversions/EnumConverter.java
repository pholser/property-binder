package com.pholser.util.properties.internal.conversions;

import com.pholser.util.properties.ParsedAs;

class EnumConverter<T extends Enum<T>> extends ScalarValueConverter {
  private final Class<T> valueType;

  EnumConverter(ParsedAs patterns, Class<T> valueType) {
    super(patterns);
    this.valueType = valueType;
  }

  @Override public Object convert(String formatted) {
    return Enum.valueOf(valueType, formatted);
  }
}
