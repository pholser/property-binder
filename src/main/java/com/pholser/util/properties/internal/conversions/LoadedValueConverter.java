package com.pholser.util.properties.internal.conversions;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

class LoadedValueConverter extends ScalarValueConverter {
  private final Conversion<?> loaded;

  LoadedValueConverter(ParsedAs patterns, Conversion<?> loaded) {
    super(patterns);
    this.loaded = loaded;
  }

  @Override public Object convert(String formatted) {
    return loaded.convert(formatted, patterns());
  }
}
