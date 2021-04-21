package com.pholser.util.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

public abstract class Conversion<T> {
  private final List<Class<T>> valueTypes;

  protected Conversion(Class<T> valueType) {
    this(singletonList(valueType));
  }

  protected Conversion(List<Class<T>> valueTypes) {
    if (valueTypes == null
      || valueTypes.stream().anyMatch(Objects::isNull)) {

      throw new NullPointerException("null value types list or elements");
    }

    this.valueTypes = new ArrayList<>(valueTypes);
  }

  public abstract T convert(String formatted, ParsedAs patterns);

  public final List<Class<T>> valueTypes() {
    return unmodifiableList(valueTypes);
  }
}
