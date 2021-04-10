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

import java.lang.reflect.Method;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ErrorsThatOccurWhenConvertingValuesViaMethodsTest {
  @Test public void transformingInvocationTargetExceptions() throws Exception {
    Method method = MethodRaisesException.class.getDeclaredMethod("raisesException", String.class);
    MethodInvokingValueConverter converter =
      new MethodInvokingValueConverter(method, Void.class);

    ValueConversionException ex =
      assertThrows(
        ValueConversionException.class,
        () -> converter.convert(""));
    assertEquals(UnsupportedOperationException.class, ex.getCause().getClass());
  }

  @Test public void transformingIllegalArgumentExceptions() throws Exception {
    Method method = Calendar.class.getDeclaredMethod("getInstance");
    MethodInvokingValueConverter converter =
      new MethodInvokingValueConverter(method, Calendar.class);

    ValueConversionException ex =
      assertThrows(
        ValueConversionException.class,
        () -> converter.convert(""));
    assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
  }

  @Test public void transformingIllegalAccessExceptions() throws Exception {
    Method method = ForTriggeringIllegalAccess.class.getDeclaredMethod("valueOf", String.class);
    MethodInvokingValueConverter converter =
      new MethodInvokingValueConverter(method, String.class);

    ValueConversionException ex =
      assertThrows(
        ValueConversionException.class,
        () -> converter.convert(""));
    assertEquals(IllegalAccessException.class, ex.getCause().getClass());
  }

  @Test public void transformingClassCastExceptions() throws Exception {
    Method method = Integer.class.getDeclaredMethod("valueOf", String.class);
    MethodInvokingValueConverter converter =
      new MethodInvokingValueConverter(method, Boolean.class);

    ValueConversionException ex =
      assertThrows(
        ValueConversionException.class,
        () -> converter.convert("2"));
    assertEquals(ClassCastException.class, ex.getCause().getClass());
  }

  public static class MethodRaisesException {
    public static void raisesException(@SuppressWarnings("unused") String argument) {
      throw new UnsupportedOperationException();
    }
  }
}
