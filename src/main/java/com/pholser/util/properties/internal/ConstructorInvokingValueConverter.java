/*
 The MIT License

 Copyright (c) 2009-2010 Paul R. Holser, Jr.

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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.pholser.util.properties.internal.exceptions.ValueConversionException;

class ConstructorInvokingValueConverter implements ValueConverter {
    private final Constructor<?> ctor;

    ConstructorInvokingValueConverter(Constructor<?> ctor) {
        this.ctor = ctor;
    }

    public Object convert(String raw) {
        try {
            return ctor.newInstance(raw);
        } catch (InstantiationException ex) {
            throw new ValueConversionException(ex);
        } catch (IllegalAccessException ex) {
            throw new ValueConversionException(ex);
        } catch (IllegalArgumentException ex) {
            throw new ValueConversionException(ex);
        } catch (InvocationTargetException ex) {
            throw new ValueConversionException(ex.getTargetException());
        }
    }

    public Object nilValue() {
        return null;
    }

    public void resolve(Properties properties) {
        // nothing to do here
    }
}
