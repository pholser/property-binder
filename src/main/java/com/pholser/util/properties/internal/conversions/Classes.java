package com.pholser.util.properties.internal.conversions;

import java.util.Map;

public final class Classes {
  private static final Map<Class<?>, Class<?>> WRAPPERS =
    Map.of(
      Boolean.TYPE, Boolean.class,
      Byte.TYPE, Byte.class,
      Character.TYPE, Character.class,
      Double.TYPE, Double.class,
      Float.TYPE, Float.class,
      Integer.TYPE, Integer.class,
      Long.TYPE, Long.class,
      Short.TYPE, Short.class,
      Void.TYPE, Void.class);

  private Classes() {
    throw new UnsupportedOperationException();
  }

  @SuppressWarnings("unchecked")
  public static <T> Class<T> wrapperIfPrimitive(Class<T> clazz) {
    return (Class<T>) WRAPPERS.getOrDefault(clazz, clazz);
  }
}
