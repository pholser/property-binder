/*
 The MIT License

 Copyright (c) 2009 Paul R. Holser, Jr.

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

package com.pholser.util.properties.internal;

import java.lang.reflect.Method;
import java.util.Calendar;

import com.pholser.util.properties.internal.exceptions.ValueConversionException;
import com.pholser.util.properties.testonly.ForTriggeringIllegalAccess;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ErrorsThatOccurWhenConvertingValuesViaMethodsTest {
    @Test
    public void shouldTransformInvocationTargetExceptions() throws Exception {
        Method method = MethodRaisesException.class.getDeclaredMethod("raisesException", String.class);

        try {
            new MethodInvokingValueConverter(method, Void.class).convert("");
            fail();
        } catch (ValueConversionException expected) {
            assertThat(expected.getCause(), is(UnsupportedOperationException.class));
        }
    }

    @Test
    public void shouldTransformIllegalArgumentExceptions() throws Exception {
        Method method = Calendar.class.getDeclaredMethod("getInstance");

        try {
            new MethodInvokingValueConverter(method, Calendar.class).convert("");
            fail();
        } catch (ValueConversionException expected) {
            assertThat(expected.getCause(), is(IllegalArgumentException.class));
        }
    }

    @Test
    public void shouldTransformIllegalAccessExceptions() throws Exception {
        Method method = ForTriggeringIllegalAccess.class.getDeclaredMethod("valueOf", String.class);

        try {
            new MethodInvokingValueConverter(method, String.class).convert("");
            fail();
        } catch (ValueConversionException expected) {
            assertThat(expected.getCause(), is(IllegalAccessException.class));
        }
    }

    @Test
    public void shouldTransformClassCastExceptions() throws Exception {
        Method method = Integer.class.getDeclaredMethod("valueOf", String.class);

        try {
            new MethodInvokingValueConverter(method, Boolean.class).convert("2");
            fail();
        } catch (ValueConversionException expected) {
            assertThat(expected.getCause(), is(ClassCastException.class));
        }
    }

    static class MethodRaisesException {
        static void raisesException(@SuppressWarnings("unused") String argument) {
            throw new UnsupportedOperationException();
        }
    }
}
