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

package com.pholser.util.properties.internal.conversions;

import com.pholser.util.properties.internal.exceptions.ValueConversionException;
import com.pholser.util.properties.testonly.ForTriggeringIllegalAccess;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ErrorsThatOccurWhenConvertingValuesViaConstructorsTest {
  @Test public void transformingInvocationTargetExceptions() throws Exception {
    Constructor<ConstructorRaisesException> ctor =
      ConstructorRaisesException.class.getDeclaredConstructor(String.class);

    ValueConversionException ex =
      assertThrows(
        ValueConversionException.class,
        () -> new ConstructorInvokingValueConverter(ctor).convert(""));
    assertEquals(UnsupportedOperationException.class, ex.getCause().getClass());
  }

  @Test public void transformingIllegalArgumentExceptions() throws Exception {
    Constructor<HasPlainOldConstructor> ctor = HasPlainOldConstructor.class.getDeclaredConstructor();

    ValueConversionException ex =
      assertThrows(
        ValueConversionException.class,
        () -> new ConstructorInvokingValueConverter(ctor).convert(""));
    assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
  }

  @Test public void transformingIllegalAccessExceptions() throws Exception {
    Constructor<ForTriggeringIllegalAccess> ctor =
      ForTriggeringIllegalAccess.class.getDeclaredConstructor(String.class);

    ValueConversionException ex =
      assertThrows(
        ValueConversionException.class,
        () -> new ConstructorInvokingValueConverter(ctor).convert(""));
    assertEquals(IllegalAccessException.class, ex.getCause().getClass());
  }

  @Test public void transformingInstantiationExceptions() throws Exception {
    Constructor<CannotBeInstantiated> ctor = CannotBeInstantiated.class.getDeclaredConstructor(String.class);

    ValueConversionException ex =
      assertThrows(
        ValueConversionException.class,
        () -> new ConstructorInvokingValueConverter(ctor).convert(""));
    assertEquals(InstantiationException.class, ex.getCause().getClass());
  }

  static class ConstructorRaisesException {
    ConstructorRaisesException(@SuppressWarnings("unused") String argument) {
      throw new UnsupportedOperationException();
    }
  }

  static class HasPlainOldConstructor {
    // nothing to do here
  }

  abstract static class CannotBeInstantiated {
    CannotBeInstantiated(@SuppressWarnings("unused") String argument) {
      // nothing to do here
    }
  }
}
