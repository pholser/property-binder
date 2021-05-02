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

package com.pholser.util.properties.it;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.PropertyBinder;
import com.pholser.util.properties.ValuesSeparatedBy;
import com.pholser.util.properties.it.boundtypes.IntProperty;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ErrorsThatOccurWhenBindingUntypedMapsToTypedInterfacesTest {
  @Test void returnTypeAndUnderlyingPropertyTypeDisagree() {
    Map<String, Object> items = Map.of("i", new Object());
    PropertyBinder<IntProperty> binder =
      PropertyBinder.forType(IntProperty.class);
    IntProperty bound = binder.bind(items);

    assertThrows(ClassCastException.class, bound::i);
  }

  @Test void primitiveWidening() {
    Map<String, Object> items = Map.of("i", Short.valueOf("2"));
    PropertyBinder<IntProperty> binder =
      PropertyBinder.forType(IntProperty.class);
    IntProperty bound = binder.bind(items);

    assertThrows(ClassCastException.class, bound::i);
  }

  @Test void resolvingASeparatorWithNonStringPropertyValue() {
    Map<String, Object> items =
      Map.of("bar", "A,B,C", "separator", new Object());
    PropertyBinder<WithValueOfSeparator> binder =
      PropertyBinder.forType(WithValueOfSeparator.class);

    assertThrows(ClassCastException.class, () -> binder.bind(items));
  }

  interface WithValueOfSeparator {
    @BoundProperty("bar")
    @ValuesSeparatedBy(valueOf = "[separator]")
    String[] bar();
  }

  @Test void resolvingADefaultValueWithNonStringPropertyValue() {
    Map<String, Object> items = Map.of("bar", "ABC", "other", new Object());
    PropertyBinder<WithValueOfDefault> binder =
      PropertyBinder.forType(WithValueOfDefault.class);

    assertThrows(ClassCastException.class, () -> binder.bind(items));
  }

  interface WithValueOfDefault {
    @BoundProperty("foo")
    @DefaultsTo(valueOf = "[other]")
    String foo();
  }
}
