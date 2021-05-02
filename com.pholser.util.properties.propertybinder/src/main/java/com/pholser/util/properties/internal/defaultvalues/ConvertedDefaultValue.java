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

package com.pholser.util.properties.internal.defaultvalues;

import com.pholser.util.properties.PropertySource;
import com.pholser.util.properties.internal.conversions.ValueConverter;
import com.pholser.util.properties.internal.exceptions.MalformedDefaultValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

final class ConvertedDefaultValue implements DefaultValue {
  private static final Logger LOGGER =
    LoggerFactory.getLogger(ConvertedDefaultValue.class);

  private final String value;
  private final ValueConverter converter;
  private final Method method;

  private boolean conversionOccurred;
  private Object converted;

  private ConvertedDefaultValue(
    String value,
    ValueConverter converter,
    Method method) {

    this.value = value;
    this.converter = converter;
    this.method = method;

    try {
      // Convert with any raw patterns available first.
      attemptConversion();
    } catch (IllegalArgumentException ex) {
      if (converter.parsePatterns().hasSubstitutions()) {
        LOGGER.trace(
          "Couldn't convert default value,"
            + " will try again after pattern resolution",
          ex);
      } else {
        throw new MalformedDefaultValueException(value, method, ex);
      }
    }
  }

  static ConvertedDefaultValue fromValue(
    String value,
    ValueConverter converter,
    Method method) {

    return new ConvertedDefaultValue(value, converter, method);
  }

  @Override public Object evaluate() {
    if (conversionOccurred) {
      return converted;
    }

    try {
      // Try again with any resolved patterns.
      attemptConversion();

      return converted;
    } catch (IllegalArgumentException ex) {
      throw new MalformedDefaultValueException(value, method, ex);
    }
  }

  @Override public void resolve(PropertySource properties) {
    // nothing to do here
  }

  private void attemptConversion() {
    converted = converter.convert(value);
    conversionOccurred = true;
  }
}
