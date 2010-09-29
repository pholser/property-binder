package com.pholser.util.properties;

import java.util.HashMap;
import java.util.Map;

import com.pholser.util.properties.boundtypes.IntPropertyHaver;

import org.junit.Test;

public class ErrorsThatOccurWhenBindingUntypedMapsToTypedInterfacesTest {
    @Test(expected = ClassCastException.class)
    public void returnTypeAndUnderlyingPropertyTypeDisagree() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("i", new Object());
        PropertyBinder<IntPropertyHaver> binder = PropertyBinder.forType(IntPropertyHaver.class);
        IntPropertyHaver bound = binder.bind(items);

        bound.i();
    }

    @Test(expected = ClassCastException.class)
    public void primitiveWidening() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("i", Short.valueOf("2"));
        PropertyBinder<IntPropertyHaver> binder = PropertyBinder.forType(IntPropertyHaver.class);
        IntPropertyHaver bound = binder.bind(items);

        bound.i();
    }

    @Test(expected = ClassCastException.class)
    public void resolvingASeparatorWithNonStringPropertyValue() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("bar", "A,B,C");
        items.put("separator", new Object());
        PropertyBinder<WithValueOfSeparator> binder = PropertyBinder.forType(WithValueOfSeparator.class);

        binder.bind(items);
    }

    interface WithValueOfSeparator {
        @BoundProperty("bar")
        @ValuesSeparatedBy(valueOf = "[separator]")
        String[] bar();
    }

    @Test(expected = ClassCastException.class)
    public void resolvingADefaultValueWithNonStringPropertyValue() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("bar", "ABC");
        items.put("other", new Object());
        PropertyBinder<WithValueOfDefault> binder = PropertyBinder.forType(WithValueOfDefault.class);

        binder.bind(items);
    }

    interface WithValueOfDefault {
        @BoundProperty("foo")
        @DefaultsTo(valueOf = "[other]")
        String foo();
    }
}
