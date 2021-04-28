package com.pholser.util.properties.internal.conversions;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.internal.parsepatterns.ParsePatterns;

class LoadedValueConverter extends ScalarValueConverter {
  private final Conversion<?> loaded;

  LoadedValueConverter(ParsePatterns patterns, Conversion<?> loaded) {
    super(patterns);
    this.loaded = loaded;
  }

  @Override public Object convert(String formatted) {
    return loaded.convert(formatted, parsePatterns().resolved());
  }
}
