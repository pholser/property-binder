package com.pholser.util.properties;

import java.util.ResourceBundle;

import com.pholser.util.properties.boundtypes.IntPropertyHaver;
import org.junit.Test;

import static com.pholser.util.properties.ResourceBundles.*;

public class ErrorsThatOccurWhenBindingUntypedResourceBundlesToTypedInterfacesTest {
    @Test(expected = ClassCastException.class)
    public void returnTypeAndUnderlyingPropertyTypeDisagree() {
        ResourceBundle bundle = bundleWith("i", new Object());
        PropertyBinder<IntPropertyHaver> binder = PropertyBinder.forType(IntPropertyHaver.class);
        IntPropertyHaver bound = binder.bind(bundle);

        bound.i();
    }

    @Test(expected = ClassCastException.class)
    public void primitiveWidening() {
        ResourceBundle bundle = bundleWith("i", Byte.valueOf("2"));
        PropertyBinder<IntPropertyHaver> binder = PropertyBinder.forType(IntPropertyHaver.class);
        IntPropertyHaver bound = binder.bind(bundle);

        bound.i();
    }

    @Test(expected = ClassCastException.class)
    public void resolvingASeparatorWithNonStringPropertyValue() {
        ResourceBundle bundle = bundleWith("bar", "A,B,C", "separator", new Object());
        PropertyBinder<WithValueOfSeparator> binder = PropertyBinder.forType(WithValueOfSeparator.class);

        binder.bind(bundle);
    }

    interface WithValueOfSeparator {
        @BoundProperty("bar")
        @ValuesSeparatedBy(valueOf = "[separator]")
        String[] bar();
    }

    @Test(expected = ClassCastException.class)
    public void resolvingADefaultValueWithNonStringPropertyValue() {
        ResourceBundle bundle = bundleWith("other", new Object());
        PropertyBinder<WithValueOfDefault> binder = PropertyBinder.forType(WithValueOfDefault.class);

        binder.bind(bundle);
    }

    interface WithValueOfDefault {
        @BoundProperty("foo")
        @DefaultsTo(valueOf = "[other]")
        String foo();
    }
}
