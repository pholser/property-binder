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
import java.util.Properties;

import com.pholser.util.properties.ParsedAs;

import com.pholser.util.properties.internal.exceptions.UnsupportedValueTypeException;
import com.pholser.util.properties.internal.separators.ValueSeparator;

import static com.pholser.util.properties.internal.conversions.ValueConverterFactory.*;

class ListValueConverter implements ValueConverter {
    private final ValueSeparator separator;
    private final ValueConverter elementTypeConverter;

    ListValueConverter(Type valueType, ValueSeparator separator, ParsedAs parsePatterns) {
        this.separator = separator;
        this.elementTypeConverter = createScalarConverter(deduceElementType(valueType), parsePatterns);
    }

    public List<Object> convert(String raw, Object... args) {
        List<Object> values = new ArrayList<Object>();
        for (String each : separator.separate(raw))
            values.add(elementTypeConverter.convert(each, args));

        return values;
    }

    private static Class<?> deduceElementType(Type valueType) {
        if (!(valueType instanceof ParameterizedType))
            return String.class;

        ParameterizedType parameterized = (ParameterizedType) valueType;
        Type genericType = parameterized.getActualTypeArguments()[0];
        if (genericType instanceof Class<?>)
            return (Class<?>) genericType;

        if (genericType instanceof WildcardType) {
            WildcardType wildcarded = (WildcardType) genericType;
            Type[] upperBounds = wildcarded.getUpperBounds();
            if (wildcarded.getLowerBounds().length == 0 && Object.class.equals(upperBounds[0]))
                return String.class;
        }

        throw new UnsupportedValueTypeException(valueType);
    }

    public Object nilValue() {
        return new ArrayList<Object>();
    }

    public void resolve(Properties properties) {
        separator.resolve(properties);
    }
}
