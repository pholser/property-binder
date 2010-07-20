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

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

import static java.lang.reflect.Proxy.*;

import com.pholser.util.properties.internal.conversions.ValueConverter;
import com.pholser.util.properties.internal.defaultvalues.DefaultValue;

import static com.pholser.util.properties.internal.PICAHelpers.*;

public class ValidatedSchema<T> {
    private final Class<T> schema;
    private final Map<String, DefaultValue> defaults;
    private final Map<String, ValueConverter> converters;

    public ValidatedSchema(Class<T> schema, Map<String, DefaultValue> defaultValues, Map<String, ValueConverter> converters) {
        this.schema = schema;
        this.defaults = defaultValues;
        this.converters = converters;
    }

    public T evaluate(Properties properties) {
        resolveConverters(properties);
        resolveDefaultValues(properties);
        return createTypedProxyFor(properties);
    }

    T createTypedProxyFor(Properties properties) {
        return schema.cast(newProxyInstance(schema.getClassLoader(),
            new Class<?>[] { schema },
            new PropertyBinderInvocationHandler(properties, this)));
    }

    Object convert(Properties properties, Method method) {
        String propertyName = propertyNameFor(method);
        ValueConverter converter = converters.get(propertyName);
        if (properties.containsKey(propertyName))
            return converter.convert(properties.getProperty(propertyName));
        if (defaults.containsKey(propertyName))
            return defaults.get(propertyName).evaluate();
        return converter.nilValue();
    }

    String getName() {
        return schema.getName();
    }

    private void resolveConverters(Properties properties) {
        for (ValueConverter each : converters.values())
            each.resolve(properties);
    }

    private void resolveDefaultValues(Properties properties) {
        for (DefaultValue each : defaults.values())
            each.resolve(properties);
    }
}
