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

package com.pholser.util.properties.internal.validation;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ValuesSeparatedBy;
import com.pholser.util.properties.internal.ValidatedSchema;
import com.pholser.util.properties.internal.conversions.ValueConverter;
import com.pholser.util.properties.internal.conversions.ValueConverterFactory;
import com.pholser.util.properties.internal.defaultvalues.DefaultValue;
import com.pholser.util.properties.internal.defaultvalues.DefaultValueFactory;
import com.pholser.util.properties.internal.exceptions.AppliedSeparatorToNonAggregateTypeException;
import com.pholser.util.properties.internal.exceptions.BoundTypeNotAnInterfaceException;
import com.pholser.util.properties.internal.exceptions.InterfaceHasSuperinterfacesException;
import com.pholser.util.properties.internal.exceptions.MultipleDefaultValueSpecificationException;
import com.pholser.util.properties.internal.exceptions.MultipleSeparatorSpecificationException;
import com.pholser.util.properties.internal.exceptions.NoDefaultValueSpecificationException;
import com.pholser.util.properties.internal.separators.ValueSeparator;
import com.pholser.util.properties.internal.separators.ValueSeparatorFactory;

import static com.pholser.util.properties.internal.Schemata.*;

public class SchemaValidator {
    private final ValueConverterFactory converterFactory = new ValueConverterFactory();
    private final ValueSeparatorFactory separatorFactory = new ValueSeparatorFactory();
    private final DefaultValueFactory defaultValueFactory = new DefaultValueFactory();

    public <T> ValidatedSchema<T> validate(Class<T> schema) {
        ensureInterface(schema);
        ensureNoSuperinterfaces(schema);

        Method[] methods = schema.getDeclaredMethods();
        Map<String, ValueConverter> converters = new HashMap<String, ValueConverter>(methods.length);
        Map<String, DefaultValue> defaults = new HashMap<String, DefaultValue>(methods.length);
        Map<String, ValueSeparator> separators = new HashMap<String, ValueSeparator>(methods.length);

        for (Method each : methods) {
            String key = propertyNameFor(each);
            collectSeparatorIfAggregateType(separators, each, key);
            collectConverter(converters, separators, each, key);
            collectDefaultValue(defaults, converters.get(key), each, key);
        }

        return new ValidatedSchema<T>(schema, defaults, converters);
    }

    private static void ensureInterface(Class<?> schema) {
        if (!schema.isInterface())
            throw new BoundTypeNotAnInterfaceException(schema);
    }

    private static void ensureNoSuperinterfaces(Class<?> schema) {
        if (schema.getInterfaces().length != 0)
            throw new InterfaceHasSuperinterfacesException(schema);
    }

    private void collectSeparatorIfAggregateType(Map<String, ValueSeparator> separators, Method method,
        String key) {

        boolean isAggregate = isAggregateType(method.getReturnType());
        ValuesSeparatedBy separator = method.getAnnotation(ValuesSeparatedBy.class);
        if (separator != null) {
            if (!isAggregate)
                throw new AppliedSeparatorToNonAggregateTypeException(method);

            if (!(isDefaultPattern(separator) || isDefaultSeparatorValueOf(separator))) {
                throw new MultipleSeparatorSpecificationException(separator, method);
            }
        }

        if (isAggregate)
            separators.put(key, separatorFactory.createSeparator(separator, method));
    }

    private void collectConverter(Map<String, ValueConverter> converters, Map<String, ValueSeparator> separators,
        Method method, String key) {

        converters.put(key, converterFactory.createConverter(method, separators.get(key)));
    }

    private void collectDefaultValue(Map<String, DefaultValue> defaults, ValueConverter converter, Method method,
        String key) {

        DefaultValue defaultValue = createDefaultValue(method, converter);
        if (defaultValue != null)
            defaults.put(key, defaultValue);
    }

    private DefaultValue createDefaultValue(Method method, ValueConverter converter) {
        DefaultsTo spec = method.getAnnotation(DefaultsTo.class);
        if (spec == null)
            return null;

        boolean valueIsDefault = isDefaultDefaultValue(spec);
        boolean valueOfIsDefault = isDefaultDefaultValueOf(spec);
        if (!(valueIsDefault || valueOfIsDefault))
            throw new MultipleDefaultValueSpecificationException(spec, method);
        if (valueIsDefault && valueOfIsDefault)
            throw new NoDefaultValueSpecificationException(method);

        return defaultValueFactory.createDefaultValue(spec, converter, method);
    }

    public static boolean isAggregateType(Class<?> clazz) {
        return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
    }
}
