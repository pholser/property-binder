package com.pholser.util.properties.it;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ArbitraryArrays {
  private ArbitraryArrays() {
    throw new UnsupportedOperationException();
  }

  static void assertReflectArrayEquals(
    Object expected,
    Object actual,
    String message) {

    Class<?> expectedType = expected.getClass();
    Class<?> actualType = actual.getClass();

    assertTrue(expectedType.isArray(), expectedType + " not an array");
    assertTrue(actualType.isArray(), actual + " not an array");
    assertEquals(expectedType, actualType);

    if (expected instanceof Object[]) {
      assertArrayEquals((Object[]) expected, (Object[]) actual, message);
    } else if (expected instanceof boolean[]) {
      assertArrayEquals((boolean[]) expected, (boolean[]) actual, message);
    } else if (expected instanceof byte[]) {
      assertArrayEquals((byte[]) expected, (byte[]) actual, message);
    } else if (expected instanceof char[]) {
      assertArrayEquals((char[]) expected, (char[]) actual, message);
    } else if (expected instanceof double[]) {
      assertArrayEquals((double[]) expected, (double[]) actual, message);
    } else if (expected instanceof float[]) {
      assertArrayEquals((float[]) expected, (float[]) actual, message);
    } else if (expected instanceof int[]) {
      assertArrayEquals((int[]) expected, (int[]) actual, message);
    } else if (expected instanceof long[]) {
      assertArrayEquals((long[]) expected, (long[]) actual, message);
    } else {
      assertArrayEquals((short[]) expected, (short[]) actual, message);
    }
  }
}
