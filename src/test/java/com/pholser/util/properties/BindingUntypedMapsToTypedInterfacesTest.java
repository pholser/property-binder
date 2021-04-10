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

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.assertSame;

class BindingUntypedMapsToTypedInterfacesTest {
  @Test void nonValueType() {
    Object value = new Object();
    Map<String, Object> items = Map.of("any.old.type", value);
    PropertyBinder<NonValueTypeHaver> binder =
      PropertyBinder.forType(NonValueTypeHaver.class);

    NonValueTypeHaver bound = binder.bind(items);

    assertSame(value, bound.anyOldType());
  }

  interface NonValueTypeHaver {
    @BoundProperty("any.old.type") Object anyOldType();
  }

  @Test void arrayOfUnconvertibleType() {
    Object[] array = new Object[0];
    Map<String, Object> items = Map.of("array.of.unconvertible.type", array);
    PropertyBinder<ArrayOfUnconvertibleTypeHaver> binder =
      PropertyBinder.forType(ArrayOfUnconvertibleTypeHaver.class);

    ArrayOfUnconvertibleTypeHaver bound = binder.bind(items);

    assertSame(array, bound.array());
  }

  interface ArrayOfUnconvertibleTypeHaver {
    @BoundProperty("array.of.unconvertible.type") Object[] array();
  }

  @Test void listOfUnconvertibleType() {
    List<Object> list = new ArrayList<>();
    Map<String, Object> items = Map.of("list.of.unconvertible.type", list);
    PropertyBinder<ListOfUnconvertibleTypeHaver> binder =
      PropertyBinder.forType(ListOfUnconvertibleTypeHaver.class);

    ListOfUnconvertibleTypeHaver bound = binder.bind(items);

    assertSame(list, bound.list());
  }

  interface ListOfUnconvertibleTypeHaver {
    @BoundProperty("list.of.unconvertible.type") List<Object> list();
  }

  @Test void nonPublicValueOf() {
    NonPublicValueOfHaver.Thing thing = new NonPublicValueOfHaver.Thing();
    Map<String, Object> items = Map.of("non.public.value.of", thing);
    PropertyBinder<NonPublicValueOfHaver> binder =
      PropertyBinder.forType(NonPublicValueOfHaver.class);

    NonPublicValueOfHaver bound = binder.bind(items);

    assertSame(thing, bound.thing());
  }

  interface NonPublicValueOfHaver {
    @BoundProperty("non.public.value.of") Thing thing();

    class Thing {
      @SuppressWarnings("unused")
      private static Thing valueOf(String raw) {
        return null;
      }
    }
  }

  @Test void nonStaticValueOf() {
    NonStaticValueOfHaver.Thing thing = new NonStaticValueOfHaver.Thing();
    Map<String, Object> items = Map.of("non.static.value.of", thing);
    PropertyBinder<NonStaticValueOfHaver> binder =
      PropertyBinder.forType(NonStaticValueOfHaver.class);

    NonStaticValueOfHaver bound = binder.bind(items);

    assertSame(thing, bound.thing());
  }

  interface NonStaticValueOfHaver {
    @BoundProperty("non.static.value.of") Thing thing();

    class Thing {
      @SuppressWarnings("unused")
      public Thing valueOf(String raw) {
        return null;
      }
    }
  }

  @Test void badValueOfReturnType() {
    BadValueOfTypeHaver.Thing thing = new BadValueOfTypeHaver.Thing();
    Map<String, Object> items = Map.of("bad.value.of.type", thing);
    PropertyBinder<BadValueOfTypeHaver> binder =
      PropertyBinder.forType(BadValueOfTypeHaver.class);

    BadValueOfTypeHaver bound = binder.bind(items);

    assertSame(thing, bound.thing());
  }

  interface BadValueOfTypeHaver {
    @BoundProperty("bad.value.of.type") Thing thing();

    class Thing {
      @SuppressWarnings("unused")
      public static Void valueOf(String raw) {
        return null;
      }
    }
  }

  @Test void argMethodsIgnoreArgsIfPropertyValueNotAString() {
    Object value = new Object();
    Map<String, Object> items = Map.of("key", value);
    PropertyBinder<ArgMethodHaver> binder =
      PropertyBinder.forType(ArgMethodHaver.class);

    ArgMethodHaver bound = binder.bind(items);

    assumeThat(bound.valueFor(1, 'a')).isEqualTo(value);
    assertSame(value, bound.valueFor(2, 'b'));
  }

  interface ArgMethodHaver {
    @BoundProperty("key") Object valueFor(int i, char ch);
  }
}
