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

package com.pholser.util.properties.conversions;

import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toCollection;

/**
 * A conversion strategy for property values.
 *
 * Property binders discover appropriate conversions for property values
 * via the {@linkplain java.util.ServiceLoader service loader} mechanism.
 * If there are many conversions for a given target type, each of them
 * is loaded and each gets a chance to convert a value until one succeeds.
 *
 * @param <V> the target type for the conversion
 */
public abstract class Conversion<V> {
  private final List<TypeToken<V>> valueTypes;

  /**
   * Makes a new conversion.
   *
   * @param valueType target type for the conversion
   */
  protected Conversion(Class<V> valueType) {
    this(singletonList(valueType));
  }

  /**
   * Makes a new conversion. Mostly available for converters of primitive
   * types and their wrappers.
   *
   * @param valueTypes target types for the conversion
   */
  protected Conversion(List<Class<V>> valueTypes) {
    requireNonNull(valueTypes, "need non-null list of value types");
    valueTypes.forEach(t -> requireNonNull(t, "null value type"));

    this.valueTypes =
      valueTypes.stream()
        .map(TypeToken::of)
        .collect(toCollection(ArrayList::new));
  }

  /**
   * Makes a new conversion. Useful when the target type involves generics.
   *
   * @param valueType target type for the conversion
   */
  protected Conversion(TypeToken<V> valueType) {
    requireNonNull(valueType, "null value type");

    this.valueTypes = singletonList(valueType);
  }

  /**
   * Converts the given value to another type, possibly informed by the
   * given patterns.
   *
   * @param value value to be converted
   * @param patterns any patterns that could be used to perform the
   * conversion
   * @return the converted value
   * @throws IllegalArgumentException if problems occurs while converting
   */
  public abstract V convert(String value, List<String> patterns)
    throws IllegalArgumentException;

  public final List<TypeToken<V>> valueTypes() {
    return unmodifiableList(valueTypes);
  }
}
