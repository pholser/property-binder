package com.pholser.util.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static junit.framework.Assert.*;

public class BindingUntypedMapsToTypedInterfacesTest {
    @Test
    public void nonValueType() {
        Object value = new Object();
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("any.old.type", value);
        PropertyBinder<NonValueTypeHaver> binder = PropertyBinder.forType(NonValueTypeHaver.class);

        NonValueTypeHaver bound = binder.bind(items);

        assertSame(value, bound.anyOldType());
    }

    interface NonValueTypeHaver {
        @BoundProperty("any.old.type")
        Object anyOldType();
    }

    @Test
    public void arrayOfUnconvertibleType() {
        Object[] array = new Object[0];
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("array.of.unconvertible.type", array);
        PropertyBinder<ArrayOfUnconvertibleTypeHaver> binder =
            PropertyBinder.forType(ArrayOfUnconvertibleTypeHaver.class);

        ArrayOfUnconvertibleTypeHaver bound = binder.bind(items);

        assertSame(array, bound.array());
    }

    interface ArrayOfUnconvertibleTypeHaver {
        @BoundProperty("array.of.unconvertible.type")
        Object[] array();
    }

    @Test
    public void listOfUnconvertibleType() {
        List<Object> list = new ArrayList<Object>();
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("list.of.unconvertible.type", list);
        PropertyBinder<ListOfUnconvertibleTypeHaver> binder =
            PropertyBinder.forType(ListOfUnconvertibleTypeHaver.class);

        ListOfUnconvertibleTypeHaver bound = binder.bind(items);

        assertSame(list, bound.list());
    }

    interface ListOfUnconvertibleTypeHaver {
        @BoundProperty("list.of.unconvertible.type")
        List<Object> list();
    }

    @Test
    public void nonPublicValueOf() {
        NonPublicValueOfHaver.Thing thing = new NonPublicValueOfHaver.Thing();
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("non.public.value.of", thing);
        PropertyBinder<NonPublicValueOfHaver> binder = PropertyBinder.forType(NonPublicValueOfHaver.class);

        NonPublicValueOfHaver bound = binder.bind(items);

        assertSame(thing, bound.thing());
    }

    interface NonPublicValueOfHaver {
        @BoundProperty("non.public.value.of")
        Thing thing();

        class Thing {
            @SuppressWarnings("unused")
            private static Thing valueOf(String raw) {
                return null;
            }
        }
    }

    @Test
    public void nonStaticValueOf() {
        NonStaticValueOfHaver.Thing thing = new NonStaticValueOfHaver.Thing();
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("non.static.value.of", thing);
        PropertyBinder<NonStaticValueOfHaver> binder = PropertyBinder.forType(NonStaticValueOfHaver.class);

        NonStaticValueOfHaver bound = binder.bind(items);

        assertSame(thing, bound.thing());
    }

    interface NonStaticValueOfHaver {
        @BoundProperty("non.static.value.of")
        Thing thing();

        class Thing {
            @SuppressWarnings("unused")
            public Thing valueOf(String raw) {
                return null;
            }
        }
    }

    @Test
    public void badValueOfReturnType() {
        BadValueOfTypeHaver.Thing thing = new BadValueOfTypeHaver.Thing();
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("bad.value.of.type", thing);
        PropertyBinder<BadValueOfTypeHaver> binder = PropertyBinder.forType(BadValueOfTypeHaver.class);

        BadValueOfTypeHaver bound = binder.bind(items);

        assertSame(thing, bound.thing());
    }

    interface BadValueOfTypeHaver {
        @BoundProperty("bad.value.of.type")
        Thing thing();

        class Thing {
            @SuppressWarnings("unused")
            public static Void valueOf(String raw) {
                return null;
            }
        }
    }
}
