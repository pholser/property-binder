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

package com.pholser.util.properties.internal.conversions;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

import com.pholser.util.properties.ParsedAs;
import com.pholser.util.properties.internal.exceptions.UnsupportedValueTypeException;
import com.pholser.util.properties.internal.separators.ValueSeparator;

import static com.pholser.util.properties.internal.conversions.ValueConverterFactory.*;

class ListValueConverter extends AggregateValueConverter {
    private final ValueConverter scalarConverter;

    ListValueConverter(Type valueType, ValueSeparator separator, ParsedAs patterns) {
        super(separator);
        scalarConverter = createScalarConverter(deduceElementType(valueType), patterns);
    }

    @Override
    public List<Object> convert(String raw, Object... args) {
        List<Object> values = new ArrayList<Object>();
        for (String each : separate(raw))
            values.add(scalarConverter.convert(each, args));

        return values;
    }

    private static Class<?> deduceElementType(Type type) {
        if (!(type instanceof ParameterizedType))
            return String.class;

        ParameterizedType parameterized = (ParameterizedType) type;
        Type generic = parameterized.getActualTypeArguments()[0];
        if (generic instanceof Class<?>)
            return (Class<?>) generic;

        if (generic instanceof WildcardType) {
            WildcardType wildcarded = (WildcardType) generic;
            if (wildcarded.getLowerBounds().length == 0 && Object.class.equals(wildcarded.getUpperBounds()[0]))
                return String.class;
        }

        throw new UnsupportedValueTypeException(type);
    }

    @Override
    public Object nilValue() {
        return new ArrayList<Object>();
    }
}
