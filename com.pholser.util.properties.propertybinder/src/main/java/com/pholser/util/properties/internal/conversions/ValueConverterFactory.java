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

import com.google.common.reflect.TypeToken;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.conversions.Conversion;
import com.pholser.util.properties.internal.exceptions.UnsupportedValueTypeException;
import com.pholser.util.properties.internal.parsepatterns.ParsePatterns;
import com.pholser.util.properties.internal.separators.ValueSeparator;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ValueConverterFactory {
  private static final Logger LOGGER =
    LoggerFactory.getLogger(ValueConverterFactory.class);

  private final Map<Type, Conversion<?>> registrants = new HashMap<>();

  @SuppressWarnings("rawtypes")
  public ValueConverterFactory(Iterator<Conversion> loaded) {
    loaded.forEachRemaining(this::register);
  }

  private void register(@SuppressWarnings("rawtypes") Conversion conversion) {
    @SuppressWarnings("unchecked")
    List<TypeToken<?>> valueTypes = conversion.valueTypes();

    valueTypes.stream()
      .map(TypeToken::getType)
      .forEach(type -> register(conversion, type));
  }

  private void register(
    @SuppressWarnings("rawtypes") Conversion conversion,
    Type type) {

    if (registrants.containsKey(type)) {
      LOGGER.trace(
        "Ignoring {} as conversion for {}", conversion.getClass(), type);
    } else {
      LOGGER.trace(
        "Registering {} as conversion for {}", conversion.getClass(), type);
      registrants.put(type, conversion);
    }
  }

  @SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
  public ValueConverter createConverter(
    Method propertyMethod,
    ValueSeparator separator,
    ParsePatterns patterns) {

    TypeToken<?> valueType =
      TypeToken.of(propertyMethod.getGenericReturnType());

    Conversion<?> loaded = registrants.get(valueType.getType());
    if (loaded != null) {
      return new LoadedValueConverter(patterns, loaded);
    }

    DefaultsTo defaults = propertyMethod.getAnnotation(DefaultsTo.class);

    if (Optional.class.equals(valueType.getRawType())) {
      return new OptionalValueConverter(
        createScalarConverter(
          typeArgumentOf(valueType.getType()),
          patterns,
          defaults,
          separator));
    }

    if (valueType.isArray()) {
      TypeToken<?> componentType = valueType.getComponentType();

      @SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
      Class<?> componentClass = componentType.getRawType();

      return new ArrayValueConverter(
        componentClass,
        separator,
        createScalarConverter(
          componentType.getType(),
          patterns,
          defaults,
          separator));
    }

    if (List.class.equals(valueType.getRawType())) {
      return new ListValueConverter(
        separator,
        createScalarConverter(
          typeArgumentOf(valueType.getType()),
          patterns,
          defaults,
          separator));
    }

    return createScalarConverter(
      valueType.getType(),
      patterns,
      defaults,
      separator);
  }

  private ValueConverter createScalarConverter(
    Type valueType,
    ParsePatterns patterns,
    DefaultsTo defaults,
    ValueSeparator separator) {

    Conversion<?> loaded = registrants.get(valueType);
    if (loaded != null) {
      return new LoadedValueConverter(patterns, loaded);
    }

    if (valueType instanceof Class<?> && ((Class<?>) valueType).isEnum()) {
      @SuppressWarnings("unchecked")
      Class<Enum> enumType = (Class<Enum>) valueType;

      return new EnumConverter<>(patterns, enumType);
    }

    if (defaults != null || (separator != null && !separator.isDefault())) {
      throw new UnsupportedValueTypeException(valueType);
    }

    return new RawValueConverter(valueType);
  }

  private Class<?> typeArgumentOf(Type type) {
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
