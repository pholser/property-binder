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

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Substitutions {
  private static final Pattern REFERENCE = Pattern.compile("\\[(.*?)\\]");

  private Substitutions() {
    throw new UnsupportedOperationException();
  }

  public static String substitute(PropertySource properties, String value) {
    String previous = null;
    String substituted = value;

    while (!Objects.equals(previous, substituted)) {
      previous = substituted;
      substituted = substituteReferences(properties, previous);
    }

    return substituted;
  }

  public static Object maybeSubstitute(
    PropertySource properties,
    BoundProperty key,
    Object value) {

    return value instanceof String && !key.suppressSubstitution()
      ? substitute(properties, (String) value)
      : value;
  }

  private static String substituteReferences(
    PropertySource properties,
    CharSequence value) {

    Matcher matcher = REFERENCE.matcher(value);
    StringBuffer buffer = new StringBuffer(value.length() * 2);
    while (matcher.find()) {
      String reference =
        (String) properties.propertyFor(
          new InternalBoundProperty(matcher.group(1)));
      matcher.appendReplacement(buffer, reference == null ? "" : reference);
    }
    matcher.appendTail(buffer);
    return buffer.toString();
  }
}
