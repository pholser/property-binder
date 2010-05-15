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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import com.pholser.util.properties.internal.ConstructorInvokingValueConverter;
import com.pholser.util.properties.internal.exceptions.ValueConversionException;
import com.pholser.util.properties.testonly.ForTriggeringIllegalAccess;

import org.junit.Test;

public class ErrorsThatOccurWhenConvertingValuesViaConstructorsTest {
    @Test
    public void shouldTransformInvocationTargetExceptions() throws Exception {
        Constructor<ConstructorRaisesException> ctor =
            ConstructorRaisesException.class.getDeclaredConstructor(String.class);

        try {
            new ConstructorInvokingValueConverter(ctor).convert("");
            fail();
        } catch (ValueConversionException expected) {
            assertThat(expected.getCause(), is(UnsupportedOperationException.class));
        }
    }

    @Test
    public void shouldTransformIllegalArgumentExceptions() throws Exception {
        Constructor<HasPlainOldConstructor> ctor = HasPlainOldConstructor.class.getDeclaredConstructor();

        try {
            new ConstructorInvokingValueConverter(ctor).convert("");
            fail();
        } catch (ValueConversionException expected) {
            assertThat(expected.getCause(), is(IllegalArgumentException.class));
        }
    }

    @Test
    public void shouldTransformIllegalAccessExceptions() throws Exception {
        Constructor<ForTriggeringIllegalAccess> ctor =
            ForTriggeringIllegalAccess.class.getDeclaredConstructor(String.class);

        try {
            new ConstructorInvokingValueConverter(ctor).convert("");
            fail();
        } catch (ValueConversionException expected) {
            assertThat(expected.getCause(), is(IllegalAccessException.class));
        }
    }

    @Test
    public void shouldTransformInstantiationExceptions() throws Exception {
        Constructor<CannotBeInstantiated> ctor = CannotBeInstantiated.class.getDeclaredConstructor(String.class);

        try {
            new ConstructorInvokingValueConverter(ctor).convert("");
            fail();
        } catch (ValueConversionException expected) {
            assertThat(expected.getCause(), is(InstantiationException.class));
        }
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
