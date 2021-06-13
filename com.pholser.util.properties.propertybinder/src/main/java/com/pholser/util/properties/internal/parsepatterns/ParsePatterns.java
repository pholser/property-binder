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

package com.pholser.util.properties.internal.parsepatterns;

import com.pholser.util.properties.ParsedAs;
import com.pholser.util.properties.PropertySource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.pholser.util.properties.internal.Substitutions.substitute;

public class ParsePatterns {
  private final List<String> raw = new ArrayList<>();
  private final List<String> substitutable = new ArrayList<>();
  private final List<String> resolved = new ArrayList<>();

  public void load(ParsedAs spec) {
    Collections.addAll(raw, spec.value());
    resolved.addAll(raw);
    Collections.addAll(substitutable, spec.valueOf());
  }

  public boolean hasSubstitutions() {
    return !substitutable.isEmpty();
  }

  public List<String> resolved() {
    return new ArrayList<>(resolved);
  }

  public void resolve(PropertySource properties) {
    substitutable.stream()
      .map(each -> substitute(properties, each))
      .forEach(resolved::add);
  }
}
