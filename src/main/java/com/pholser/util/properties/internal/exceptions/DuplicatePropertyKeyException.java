package com.pholser.util.properties.internal.exceptions;

import com.pholser.util.properties.BoundProperty;

import java.lang.reflect.Method;

public class DuplicatePropertyKeyException
  extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public DuplicatePropertyKeyException(
    Class<?> schema,
    Method method,
    BoundProperty key) {

    super("Bound type " + schema.getSimpleName()
      + " maps multiple methods to property key " + key.value()
      + ", including " + method.getName());
  }
}
