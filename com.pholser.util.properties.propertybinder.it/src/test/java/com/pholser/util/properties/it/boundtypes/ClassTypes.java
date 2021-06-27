package com.pholser.util.properties.it.boundtypes;

import com.pholser.util.properties.BoundProperty;

public interface ClassTypes {
  @BoundProperty("raw.class")
  Class rawClass();

  @BoundProperty("unavailable.raw.class")
  Class unavailableRawClass();

  @BoundProperty("missing.raw.class")
  Class missingRawClass();

  @BoundProperty("wildcard.class")
  Class<?> wildcardClass();

  @BoundProperty("unavailable.wildcard.class")
  Class<?> unavailableWildcardClass();

  @BoundProperty("missing.wildcard.class")
  Class<?> missingWildcardClass();

  @BoundProperty("upper.bound.class")
  Class<? extends Number> upperBoundClass();

  @BoundProperty("unavailable.upper.bound.class")
  Class<? extends Number> unavailableUpperBoundClass();

  @BoundProperty("incompatible.upper.bound.class")
  Class<? extends Number> incompatibleUpperBoundClass();

  @BoundProperty("missing.upper.bound.class")
  Class<? extends Number> missingUpperBoundClass();

  @BoundProperty("array.upper.bound.class")
  Class<? extends Number[]> arrayUpperBoundClass();

  @BoundProperty("array.class")
  Class<Integer[]> arrayClass();
}
