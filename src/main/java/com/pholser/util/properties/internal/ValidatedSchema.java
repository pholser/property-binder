/*
 The MIT License

 Copyright (c) 2009-2021 Paul R. Holser, Jr.

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

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.PropertySource;
import com.pholser.util.properties.internal.conversions.ValueConverter;
import com.pholser.util.properties.internal.defaultvalues.DefaultValue;
import com.pholser.util.properties.internal.parsepatterns.ParsePatterns;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

import static com.pholser.util.properties.internal.Schemata.propertyMarkerFor;
import static java.lang.reflect.Proxy.newProxyInstance;
import static java.util.Objects.requireNonNull;

public class ValidatedSchema<T> {
  private final Class<T> schema;
  private final Map<BoundProperty, DefaultValue> defaults;
  private final Map<BoundProperty, ValueConverter> converters;
  private final Map<BoundProperty, ParsePatterns> patterns;

  public ValidatedSchema(
    Class<T> schema,
    Map<BoundProperty, DefaultValue> defaults,
    Map<BoundProperty, ValueConverter> converters,
    Map<BoundProperty, ParsePatterns> patterns) {

    this.schema = schema;
    this.defaults = defaults;
    this.converters = converters;
    this.patterns = patterns;
  }

  public T evaluate(PropertySource properties) {
    requireNonNull(properties, "null properties source");

    resolveParsePatterns(properties);
    resolveConverters(properties);
    resolveDefaultValues(properties);
    return createTypedProxyFor(properties);
  }

  Object convert(PropertySource properties, Method method, Object... args) {
    BoundProperty key = propertyMarkerFor(method);
    ValueConverter converter = converters.get(key);

    return Optional.ofNullable(properties.propertyFor(key))
      .map(v -> converter.convertRaw(v, args))
      .orElse(
        Optional.ofNullable(defaults.get(key))
          .map(DefaultValue::evaluate)
          .orElse(converter.nilValue()));
  }

  String getName() {
    return schema.getName();
  }

  private void resolveParsePatterns(PropertySource properties) {
    patterns.values().forEach(p -> p.resolve(properties));
  }

  private void resolveConverters(PropertySource properties) {
    converters.values().forEach(c -> c.resolve(properties));
  }

  private void resolveDefaultValues(PropertySource properties) {
    defaults.values().forEach(d -> d.resolve(properties));
  }

  private T createTypedProxyFor(PropertySource properties) {
    return schema.cast(
      newProxyInstance(
        schema.getClassLoader(),
        new Class<?>[] {schema},
        new PropertyBinderInvocationHandler(properties, this)));
  }
}
