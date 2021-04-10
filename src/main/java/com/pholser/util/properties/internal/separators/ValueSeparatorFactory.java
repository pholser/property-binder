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

package com.pholser.util.properties.internal.separators;

import com.pholser.util.properties.ValuesSeparatedBy;

import java.lang.reflect.Method;

import static com.pholser.util.properties.internal.Schemata.annotationDefault;
import static com.pholser.util.properties.internal.Schemata.isDefaultPattern;
import static com.pholser.util.properties.internal.Schemata.isDefaultSeparatorValueOf;

public class ValueSeparatorFactory {
  public ValueSeparator createSeparator(
    ValuesSeparatedBy spec,
    Method method) {

    Object patternDefault = annotationDefault(ValuesSeparatedBy.class, "pattern");
    return spec == null
      ? new RegexValueSeparator(patternDefault.toString(), method)
      : createSeparatorBasedOnSpec(spec, method);
  }

  private static ValueSeparator createSeparatorBasedOnSpec(
    ValuesSeparatedBy spec,
    Method method) {

    if (isDefaultPattern(spec) && !isDefaultSeparatorValueOf(spec))
      return new SubstitutableRegexValueSeparator(spec.valueOf(), method);
    return new RegexValueSeparator(spec.pattern(), method);
  }
}
