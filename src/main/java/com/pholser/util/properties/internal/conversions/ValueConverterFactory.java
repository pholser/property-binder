/*
 The MIT License

 Copyright (c) 2009-2011 Paul R. Holser, Jr.

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.reflect.Modifier.*;
import static java.util.Collections.*;

import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ParsedAs;
import com.pholser.util.properties.internal.exceptions.UnsupportedParsedAsTypeException;
import com.pholser.util.properties.internal.exceptions.UnsupportedValueTypeException;
import com.pholser.util.properties.internal.separators.ValueSeparator;

public class ValueConverterFactory {
    static final Map<Class<?>, Class<?>> WRAPPERS;
    static final int SMALL_PRIME = 13;

    static {
        Map<Class<?>, Class<?>> wrappers = new HashMap<Class<?>, Class<?>>(SMALL_PRIME);
        wrappers.put(Boolean.TYPE, Boolean.class);
        wrappers.put(Byte.TYPE, Byte.class);
        wrappers.put(Character.TYPE, Character.class);
        wrappers.put(Double.TYPE, Double.class);
        wrappers.put(Float.TYPE, Float.class);
        wrappers.put(Integer.TYPE, Integer.class);
        wrappers.put(Long.TYPE, Long.class);
        wrappers.put(Short.TYPE, Short.class);
        wrappers.put(Void.TYPE, Void.class);
        WRAPPERS = unmodifiableMap(wrappers);
    }

    public ValueConverter createConverter(Method propertyMethod, ValueSeparator separator) {
        Class<?> valueType = targetTypeFor(propertyMethod);
        ParsedAs patterns = propertyMethod.getAnnotation(ParsedAs.class);
        DefaultsTo defaults = propertyMethod.getAnnotation(DefaultsTo.class);

        if (valueType.isArray())
            return new ArrayValueConverter(valueType, separator, patterns, defaults);

        if (List.class.equals(valueType))
            return new ListValueConverter(propertyMethod.getGenericReturnType(), separator, patterns, defaults);

        return createScalarConverter(valueType, patterns, defaults, separator);
    }

    public static ValueConverter createScalarConverter(Class<?> valueType, ParsedAs patterns, DefaultsTo defaults,
        ValueSeparator separator) {

        Class<?> returnType = wrapperIfPrimitive(valueType);
        ValueConverter pattern = parsePatternsConverter(returnType, patterns);
        if (pattern != null)
            return pattern;

        ValueConverter valueOf = valueOfConverter(returnType);
        if (valueOf != null)
            return valueOf;

        ValueConverter constructor = constructorConverter(returnType);
        if (constructor != null)
            return constructor;

        if (defaults != null || (separator != null && !separator.isDefault()))
            throw new UnsupportedValueTypeException(valueType);

        return new RawValueConverter();
    }

    private static ValueConverter parsePatternsConverter(Class<?> valueType, ParsedAs patterns) {
        if (patterns == null)
            return null;
        if (valueType.isAssignableFrom(Date.class))
            return new SimpleDateFormatParseValueConverter(patterns);
        throw new UnsupportedParsedAsTypeException(valueType);
    }

    private static ValueConverter valueOfConverter(Class<?> valueType) {
        if (Character.class.equals(valueType))
            return new CharacterValueOfConverter();

        try {
            Method valueOf = valueType.getDeclaredMethod("valueOf", String.class);
            return isPotentialConverter(valueOf, valueType)
                ? new MethodInvokingValueConverter(valueOf, valueType)
                : null;
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }

    private static ValueConverter constructorConverter(Class<?> valueType) {
        try {
            return new ConstructorInvokingValueConverter(valueType.getConstructor(String.class));
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }

    private static boolean isPotentialConverter(Method method, Class<?> expectedReturnType) {
        int modifiers = method.getModifiers();
        return isPublic(modifiers) && isStatic(modifiers) && expectedReturnType.equals(method.getReturnType());
    }

    private static Class<?> targetTypeFor(Method method) {
        Class<?> returnType = method.getReturnType();
        return returnType.isPrimitive() ? wrapperIfPrimitive(returnType) : returnType;
    }

    static Class<?> wrapperIfPrimitive(Class<?> clazz) {
        return WRAPPERS.containsKey(clazz) ? WRAPPERS.get(clazz) : clazz;
    }
}
