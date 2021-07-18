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

package com.pholser.util.properties;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Mark an interface method with this annotation to indicate that values
 * for the property represented by the method are parsed according to the
 * given patterns.
 *
 * The patterns are passed to a
 * {@link com.pholser.util.properties.conversions.Conversion} to be
 * interpret in a conversion-specific way (or not at all). For example, a
 * conversion for {@link java.time.LocalDateTime} might treat the patterns
 * as those accepted by {@link java.time.format.DateTimeFormatter}.
 *
 * The patterns can be a plain values given by {@link #value()}, or values
 * composed in whole or in part of the values of other properties,
 * given by {@link #valueOf()}. References to other properties in a `valueOf`
 * expression are delimited by {@code [} and {@code ]}.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface ParsedAs {
  /**
   * @return patterns with which to parse a property value
   */
  String[] value() default {};

  /**
   * @return expressions from which to build patterns with which to parse
   * a property value
   */
  String[] valueOf() default {};
}
