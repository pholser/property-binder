/*
 The MIT License

 Copyright (c) 2009-2011 Paul R. Holser, Jr.

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
