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

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.pholser.util.properties.internal.Substitutions.substitute;
import static java.util.stream.Collectors.joining;

/**
 * Properties class with support for property values which can be composed
 * of the values of other properties. Such values specify other property
 * keys delimited by {@code [} and {@code ]}.
 *
 * Inspired by
 * <a href="http://www2.sys-con.com/ITSG/virtualcd/Java/archives/0612/mair/index.html">
 * Enabling Constant Substitution in Property Values</a>.
 */
public class SubstitutableProperties
  extends Properties
  implements PropertySource {

  private static final long serialVersionUID = 1L;

  /**
   * Creates an empty substitutable properties set.
   */
  public SubstitutableProperties() {
    // nothing to do here
  }

  /**
   * Creates a substitutable properties set with defaults that are the
   * keys and values of another properties set.
   *
   * @param defaults default key-value pairs for the new set
   */
  public SubstitutableProperties(Properties defaults) {
    super(defaults);
  }

  @Override public Object propertyFor(BoundProperty key) {
    return key.suppressSubstitution()
      ? super.getProperty(key.value())
      : getProperty(key.value());
  }

  @Override public String getProperty(String key) {
    return substitute(this, super.getProperty(key));
  }

  @Override public synchronized String toString() {
    return "{"
      + Collections.list(propertyNames()).stream()
        .map(p -> (String) p)
        .map(p -> p + '=' + getProperty(p))
        .collect(joining(", "))
      + '}';
  }
}
