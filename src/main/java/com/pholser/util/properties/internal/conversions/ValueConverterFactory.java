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

package com.pholser.util.properties.internal.conversions;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ParsedAs;
import com.pholser.util.properties.internal.exceptions.UnsupportedValueTypeException;
import com.pholser.util.properties.internal.separators.ValueSeparator;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.pholser.util.properties.internal.conversions.Classes.wrapperIfPrimitive;

public class ValueConverterFactory {
  private final Map<Class<?>, List<Conversion<?>>> scalars = new HashMap<>();

  public ValueConverterFactory(Iterator<Conversion<?>> loaded) {
    loaded.forEachRemaining(conv ->
      conv.valueTypes().forEach(type ->
        scalars.computeIfAbsent(type, k -> new ArrayList<>())
          .add(conv)));
  }

  public ValueConverter createConverter(
    Method propertyMethod,
    ValueSeparator separator) {

    Class<?> valueType = wrapperIfPrimitive(propertyMethod.getReturnType());
    ParsedAs patterns = propertyMethod.getAnnotation(ParsedAs.class);
    DefaultsTo defaults = propertyMethod.getAnnotation(DefaultsTo.class);

    if (Optional.class.equals(valueType)) {
      return new OptionalValueConverter(
        createScalarConverter(
          deduceElementType(propertyMethod.getGenericReturnType()),
          patterns,
          defaults,
          separator));
    }
    if (valueType.isArray()) {
      return new ArrayValueConverter(
        valueType.getComponentType(),
        separator,
        createScalarConverter(
          valueType.getComponentType(),
          patterns,
          defaults,
          separator));
    }
    if (List.class.equals(valueType)) {
      return new ListValueConverter(
        separator,
        createScalarConverter(
          deduceElementType(propertyMethod.getGenericReturnType()),
          patterns,
          defaults,
          separator));
    }

    return createScalarConverter(valueType, patterns, defaults, separator);
  }

  private ValueConverter createScalarConverter(
    Class<?> valueType,
    ParsedAs patterns,
    DefaultsTo defaults,
    ValueSeparator separator) {

    Class<?> returnType = wrapperIfPrimitive(valueType);

    Conversion<?> loaded = scalars.get(returnType);
    if (loaded != null) {
      return new LoadedValueConverter(patterns, loaded);
    }

    if (defaults != null || (separator != null && !separator.isDefault())) {
      throw new UnsupportedValueTypeException(valueType);
    }

    return new RawValueConverter();
  }

  private Class<?> deduceElementType(Type type) {
    if (type instanceof Class<?>) {
      return String.class;
    }

    ParameterizedType parameterized = (ParameterizedType) type;
    Type generic = parameterized.getActualTypeArguments()[0];
    if (generic instanceof Class<?>) {
      return (Class<?>) generic;
    }

    if (generic instanceof WildcardType) {
      WildcardType wildcard = (WildcardType) generic;
      if (wildcard.getLowerBounds().length == 0
        && Object.class.equals(wildcard.getUpperBounds()[0])) {

        return String.class;
      }
    }

    throw new UnsupportedValueTypeException(type);
  }

}
