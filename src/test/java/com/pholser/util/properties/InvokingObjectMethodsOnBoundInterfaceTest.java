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

import static java.lang.System.*;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

public class InvokingObjectMethodsOnBoundInterfaceTest
    extends TypedStringBindingTestSupport<InvokingObjectMethodsOnBoundInterfaceTest.PropertyFacade> {

    @Test
    public void respondingToEqualsByTestingIdentity() throws Exception {
        PropertyFacade second = binder.bind(propertiesFile);
        assertEquals(bound, bound);
        assertFalse(bound.equals(second));
        assertEquals(second, second);
        assertFalse(second.equals(bound));
    }

    @Test
    public void respondingToHashCodeByGivingIdentityHashCode() {
        assertEquals(identityHashCode(bound), bound.hashCode());
    }

    @Test
    public void respondingToToStringByGivingProperties() {
        assertThat(bound.toString(), containsString("wrapped.integer"));
    }

    @Override
    protected Class<PropertyFacade> boundType() {
        return PropertyFacade.class;
    }

    interface PropertyFacade {
        @BoundProperty(value = "string.property")
        String stringProperty();
    }
}