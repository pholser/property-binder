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

package com.pholser.util.properties.internal;

import java.lang.reflect.Method;
import java.util.Map;

import static java.lang.reflect.Proxy.*;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.PropertySource;
import com.pholser.util.properties.internal.conversions.ValueConverter;
import com.pholser.util.properties.internal.defaultvalues.DefaultValue;

import static com.pholser.util.properties.internal.Schemata.*;

public class ValidatedSchema<T> {
    private final Class<T> schema;
    private final Map<BoundProperty, DefaultValue> defaults;
    private final Map<BoundProperty, ValueConverter> converters;

    public ValidatedSchema(
        Class<T> schema,
        Map<BoundProperty, DefaultValue> defaults,
        Map<BoundProperty, ValueConverter> converters) {

        this.schema = schema;
        this.defaults = defaults;
        this.converters = converters;
    }

    public T evaluate(PropertySource properties) {
        if (properties == null)
            throw new NullPointerException("null properties source");

        resolveConverters(properties);
        resolveDefaultValues(properties);
        return createTypedProxyFor(properties);
    }

    T createTypedProxyFor(PropertySource properties) {
        return schema.cast(newProxyInstance(schema.getClassLoader(),
            new Class<?>[] { schema },
            new PropertyBinderInvocationHandler(properties, this)));
    }

    Object convert(PropertySource properties, Method method, Object... args) {
        BoundProperty key = propertyMarkerFor(method);
        ValueConverter converter = converters.get(key);

        Object value = properties.propertyFor(key);
        if (value != null)
            return converter.convertRaw(value, args);
        if (defaults.containsKey(key))
            return defaults.get(key).evaluate();
        return converter.nilValue();
    }

    String getName() {
        return schema.getName();
    }

    private void resolveConverters(PropertySource properties) {
        for (ValueConverter each : converters.values())
            each.resolve(properties);
    }

    private void resolveDefaultValues(PropertySource properties) {
        for (DefaultValue each : defaults.values())
            each.resolve(properties);
    }
}
