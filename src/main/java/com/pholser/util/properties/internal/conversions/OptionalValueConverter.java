package com.pholser.util.properties.internal.conversions;

import com.pholser.util.properties.PropertySource;

import java.util.Optional;

class OptionalValueConverter extends AbstractValueConverter {
  private final ValueConverter elementConverter;

  OptionalValueConverter(ValueConverter elementConverter) {
    this.elementConverter = elementConverter;
  }

  @Override public Object convert(String raw, Object... args) {
    return Optional.ofNullable(elementConverter.convert(raw, args));
  }

  @Override public Object nilValue() {
    return Optional.empty();
  }

  @Override public void resolve(PropertySource properties) {
    // optionals do not resolve
  }
}
