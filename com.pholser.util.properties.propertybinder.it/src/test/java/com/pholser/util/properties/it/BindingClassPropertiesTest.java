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

import com.pholser.util.properties.it.boundtypes.ClassTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BindingClassPropertiesTest
  extends TypedStringBindingTestSupport<ClassTypes> {

  BindingClassPropertiesTest() {
    super("/class-types.properties", "class-types", "properties");
  }

  @Override protected Class<ClassTypes> boundType() {
    return ClassTypes.class;
  }

  @Test void rawClass() {
    assertEquals(Object.class, bound.rawClass());
  }

  @Test void unavailableRawClass() {
    assertNull(bound.unavailableRawClass());
  }

  @Test void missingRawClass() {
    assertThrows(
      IllegalArgumentException.class,
      () -> bound.missingRawClass());
  }

  @Test void wildcardClass() {
    assertEquals(Double.class, bound.wildcardClass());
  }

  @Test void unavailableWildcardClass() {
    assertNull(bound.unavailableWildcardClass());
  }

  @Test void missingWildcardClass() {
    assertThrows(
      IllegalArgumentException.class,
      () -> bound.missingWildcardClass());
  }

  @Test void upperBoundClass() {
    assertEquals(Integer.class, bound.upperBoundClass());
  }

  @Test void unavailableUpperBoundClass() {
    assertNull(bound.unavailableUpperBoundClass());
  }

  @Test void incompatibleUpperBoundClass() {
    assertThrows(
      IllegalArgumentException.class,
      () -> bound.incompatibleUpperBoundClass());
  }

  @Test void missingUpperBoundClass() {
    assertThrows(
      IllegalArgumentException.class,
      () -> bound.missingUpperBoundClass());
  }

  @Test void arrayClass() {
    assertEquals(Integer[].class, bound.arrayClass());
  }
}
