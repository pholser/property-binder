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
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

public abstract class Conversion<T> {
  private final List<TypeToken<T>> valueTypes;

  protected Conversion(Class<T> valueType) {
    this(singletonList(valueType));
  }

  protected Conversion(List<Class<T>> valueTypes) {
    requireNonNull(valueTypes, "need non-null list of value types");
    valueTypes.forEach(t -> requireNonNull(t, "null value type"));

    this.valueTypes =
      valueTypes.stream()
        .map(TypeToken::of)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  protected Conversion(TypeToken<T> valueType) {
    this(singletonList(valueType));
  }

  protected Conversion(Collection<TypeToken<T>> valueTypes) {
    requireNonNull(valueTypes, "need non-null list of value types");
    valueTypes.forEach(t -> requireNonNull(t, "null value type"));

    this.valueTypes = new ArrayList<>(valueTypes);
  }

  public abstract T convert(String value, List<String> patterns)
    throws IllegalArgumentException;

  public final List<TypeToken<T>> valueTypes() {
    return unmodifiableList(valueTypes);
  }
}
