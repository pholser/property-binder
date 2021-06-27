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

import com.pholser.util.properties.PropertySource;
import com.pholser.util.properties.internal.parsepatterns.ParsePatterns;

import java.util.OptionalDouble;

class OptionalDoubleConverter extends ValueConverter {
  private final ValueConverter elementConverter;

  OptionalDoubleConverter(ValueConverter elementConverter) {
    this.elementConverter = elementConverter;
  }

  @Override public Object convert(String formatted) {
    return OptionalDouble.of((double) elementConverter.convert(formatted));
  }

  @Override public Object nilValue() {
    return OptionalDouble.empty();
  }

  @Override public ParsePatterns parsePatterns() {
    return elementConverter.parsePatterns();
  }

  @Override public void resolve(PropertySource properties) {
    // optionals do not resolve
  }
}
