package com.pholser.util.properties.internal.conversions;

import com.pholser.util.properties.conversions.Conversion;
import com.pholser.util.properties.internal.parsepatterns.ParsePatterns;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

class LoadedValueConverter extends SingularValueConverter {
  private final List<Conversion<?>> loaded;

  LoadedValueConverter(ParsePatterns patterns, List<Conversion<?>> loaded) {
    super(patterns);
    this.loaded = loaded;
  }

  @Override public Object convert(String formatted) {
    List<String> resolvedPatterns = parsePatterns().resolved();
    List<IllegalArgumentException> exceptions = new ArrayList<>();

    for (Conversion<?> each : loaded) {
      try {
        return each.convert(formatted, resolvedPatterns);
      } catch (IllegalArgumentException ex) {
        exceptions.add(ex);
        // try the next conversion
      }
    }

    throw new IllegalArgumentException(
      "Could not convert [" + formatted + "]: "
        + exceptions.stream()
          .map(Object::toString)
          .collect(joining()));
  }
}
