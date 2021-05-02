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
import com.pholser.util.properties.PropertyBinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.pholser.util.properties.it.ResourceBundles.bundleWith;
import static org.junit.jupiter.api.Assertions.assertSame;

class BindingUntypedResourceBundlesToTypedInterfacesTest {
  @Test void nonValueType() {
    Object value = new Object();
    ResourceBundle bundle = bundleWith("any.old.type", value);
    PropertyBinder<NonValueTypeProperties> binder =
      PropertyBinder.forType(NonValueTypeProperties.class);

    NonValueTypeProperties bound = binder.bind(bundle);

    assertSame(value, bound.anyOldType());
  }

  interface NonValueTypeProperties {
    @BoundProperty("any.old.type") Object anyOldType();
  }

  @Test void arrayOfUnconvertibleType() {
    Object[] array = new Object[0];
    ResourceBundle bundle = bundleWith("array.of.unconvertible.type", array);
    PropertyBinder<ArrayOfUnconvertibleTypeProperties> binder =
      PropertyBinder.forType(ArrayOfUnconvertibleTypeProperties.class);

    ArrayOfUnconvertibleTypeProperties bound = binder.bind(bundle);

    assertSame(array, bound.array());
  }

  interface ArrayOfUnconvertibleTypeProperties {
    @BoundProperty("array.of.unconvertible.type") Object[] array();
  }

  @Test void listOfUnconvertibleType() {
    List<Object> list = new ArrayList<>();
    ResourceBundle bundle = bundleWith("list.of.unconvertible.type", list);
    PropertyBinder<ListOfUnconvertibleTypeProperties> binder =
      PropertyBinder.forType(ListOfUnconvertibleTypeProperties.class);

    ListOfUnconvertibleTypeProperties bound = binder.bind(bundle);

    assertSame(list, bound.list());
  }

  interface ListOfUnconvertibleTypeProperties {
    @BoundProperty("list.of.unconvertible.type")
    List<Object> list();
  }

  @Test void nonPublicValueOf() {
    NonPublicValueOfProperties.Thing thing =
      new NonPublicValueOfProperties.Thing();
    ResourceBundle bundle = bundleWith("non.public.value.of", thing);
    PropertyBinder<NonPublicValueOfProperties> binder =
      PropertyBinder.forType(NonPublicValueOfProperties.class);

    NonPublicValueOfProperties bound = binder.bind(bundle);

    assertSame(thing, bound.thing());
  }

  interface NonPublicValueOfProperties {
    @BoundProperty("non.public.value.of") Thing thing();

    class Thing {
      @SuppressWarnings("unused")
      private static Thing valueOf(String raw) {
        return null;
      }
    }
  }

  @Test void nonStaticValueOf() {
    NonStaticValueOfProperties.Thing thing =
      new NonStaticValueOfProperties.Thing();
    ResourceBundle bundle = bundleWith("non.static.value.of", thing);
    PropertyBinder<NonStaticValueOfProperties> binder =
      PropertyBinder.forType(NonStaticValueOfProperties.class);

    NonStaticValueOfProperties bound = binder.bind(bundle);

    assertSame(thing, bound.thing());
  }

  interface NonStaticValueOfProperties {
    @BoundProperty("non.static.value.of") Thing thing();

    class Thing {
      @SuppressWarnings("unused")
      public Thing valueOf(String raw) {
        return null;
      }
    }
  }

  @Test void badValueOfReturnType() {
    BadValueOfTypeProperties.Thing thing =
      new BadValueOfTypeProperties.Thing();
    ResourceBundle bundle = bundleWith("bad.value.of.type", thing);
    PropertyBinder<BadValueOfTypeProperties> binder =
      PropertyBinder.forType(BadValueOfTypeProperties.class);

    BadValueOfTypeProperties bound = binder.bind(bundle);

    assertSame(thing, bound.thing());
  }

  interface BadValueOfTypeProperties {
    @BoundProperty("bad.value.of.type") Thing thing();

    class Thing {
      @SuppressWarnings("unused")
      public static Void valueOf(String raw) {
        return null;
      }
    }
  }
}
