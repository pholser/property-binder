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

import com.pholser.util.properties.internal.separators.ValueSeparator;

import java.lang.reflect.Array;

class ArrayConverter extends AggregateValueConverter {
  private final Class<?> componentType;

  ArrayConverter(
    Class<?> componentType,
    ValueSeparator separator,
    ValueConverter elementConverter) {

    super(separator, elementConverter);
    this.componentType = componentType;
  }

  @Override public Object convert(String formatted) {
    String[] pieces = separate(formatted);

    Object array = Array.newInstance(componentType, pieces.length);

    for (int i = 0; i < pieces.length; ++i) {
      Array.set(array, i, elementConverter().convert(pieces[i]));
    }

    return array;
  }

  @Override public Object nilValue() {
    return Array.newInstance(componentType, 0);
  }
}
