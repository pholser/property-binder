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

package com.pholser.util.properties;

import com.pholser.util.properties.internal.Reflection;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static com.pholser.util.properties.ArbitraryArrays.assertReflectArrayEquals;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class TypedStringBindingTestSupport<T>
  extends StringBindingTestSupport {

  protected PropertyBinder<T> binder;
  protected T bound;

  @BeforeEach final void initializeBinderAndBoundType() throws Exception {
    binder = new PropertyBinder<>(boundType());
    bound = binder.bind(propertiesFile);
  }

  protected abstract Class<T> boundType();

  void assertPropertiesEqual(Object expected, Object actual)
    throws Exception {

    List<Method> methods =
      Arrays.stream(boundType().getDeclaredMethods())
        .filter(Reflection::acceptablePropertyMethodAccessLevel)
        .collect(toList());
    for (Method each : methods) {
      Object boundExpected = each.invoke(expected);
      Object boundActual = each.invoke(actual);

      if (each.getReturnType().isArray()) {
        assertReflectArrayEquals(boundExpected, boundActual, each.getName());
      } else {
        assertEquals(boundExpected, boundActual, each.getName());
      }
    }
  }
}
