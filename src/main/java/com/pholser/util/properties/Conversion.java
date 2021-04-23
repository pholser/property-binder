package com.pholser.util.properties;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

public abstract class Conversion<T> {
  private final List<Class<T>> valueTypes;

  protected Conversion(Class<T> valueType) {
    this(singletonList(valueType));
  }

  protected Conversion(List<Class<T>> valueTypes) {
    requireNonNull(valueTypes, "need non-null list of value types");
    valueTypes.forEach(t -> requireNonNull(t, "null value type"));

    this.valueTypes = new ArrayList<>(valueTypes);
  }

  public abstract T convert(String formatted, ParsedAs patterns)
    throws IllegalArgumentException;

  public final List<Class<T>> valueTypes() {
    return unmodifiableList(valueTypes);
  }
}
