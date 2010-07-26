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

package com.pholser.util.properties.internal.defaultvalues;

import java.lang.reflect.Method;
import java.util.Properties;

import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.internal.conversions.ValueConverter;
import com.pholser.util.properties.internal.exceptions.MalformedDefaultValueException;
import com.pholser.util.properties.internal.exceptions.ValueConversionException;

final class ConvertedDefaultValue implements DefaultValue {
    private final Object converted;

    private ConvertedDefaultValue(String value, ValueConverter converter, Method method) {
        try {
            this.converted = converter.convert(value);
        } catch (ValueConversionException ex) {
            throw new MalformedDefaultValueException(value, method, ex);
        }
    }

    static ConvertedDefaultValue fromValue(DefaultsTo defaultValueSpec, ValueConverter converter, Method method) {
        return new ConvertedDefaultValue(defaultValueSpec.value(), converter, method);
    }

    static ConvertedDefaultValue fromValueOf(String valueOf, ValueConverter converter, Method method) {
        return new ConvertedDefaultValue(valueOf, converter, method);
    }

    public Object evaluate() {
        return converted;
    }

    public void resolve(Properties properties) {
        // nothing to do here
    }
}