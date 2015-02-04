/*
 The MIT License

 Copyright (c) 2009-2013 Paul R. Holser, Jr.

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

import java.lang.reflect.Method;
import java.util.Calendar;

import com.pholser.util.properties.internal.exceptions.ValueConversionException;
import com.pholser.util.properties.testonly.ForTriggeringIllegalAccess;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.pholser.util.properties.ExceptionMatchers.*;
import static org.junit.rules.ExpectedException.*;

public class ErrorsThatOccurWhenConvertingValuesViaMethodsTest {
    @Rule public final ExpectedException thrown = none();

    @Test public void transformingInvocationTargetExceptions() throws Exception {
        thrown.expect(ValueConversionException.class);
        thrown.expect(causeOfType(UnsupportedOperationException.class));

        Method method = MethodRaisesException.class.getDeclaredMethod("raisesException", String.class);

        new MethodInvokingValueConverter(method, Void.class).convert("");
    }

    @Test public void transformingIllegalArgumentExceptions() throws Exception {
        thrown.expect(ValueConversionException.class);
        thrown.expect(causeOfType(IllegalArgumentException.class));

        Method method = Calendar.class.getDeclaredMethod("getInstance");

        new MethodInvokingValueConverter(method, Calendar.class).convert("");
    }

    @Test public void transformingIllegalAccessExceptions() throws Exception {
        thrown.expect(ValueConversionException.class);
        thrown.expect(causeOfType(IllegalAccessException.class));

        Method method = ForTriggeringIllegalAccess.class.getDeclaredMethod("valueOf", String.class);

        new MethodInvokingValueConverter(method, String.class).convert("");
    }

    @Test public void transformingClassCastExceptions() throws Exception {
        thrown.expect(ValueConversionException.class);
        thrown.expect(causeOfType(ClassCastException.class));

        Method method = Integer.class.getDeclaredMethod("valueOf", String.class);

        new MethodInvokingValueConverter(method, Boolean.class).convert("2");
    }

    public static class MethodRaisesException {
        public static void raisesException(@SuppressWarnings("unused") String argument) {
            throw new UnsupportedOperationException();
        }
    }
}
