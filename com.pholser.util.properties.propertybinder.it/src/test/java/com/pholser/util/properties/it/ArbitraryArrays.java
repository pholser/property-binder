/*
 The MIT License

 Copyright (c) 2009-2021 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
