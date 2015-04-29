/*
 The MIT License

 Copyright (c) 2009-2013 Paul R. Holser, Jr.

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
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

public class InvokingToStringOnBoundProxiesTest {
    private PropertyBinder<Schema> binder;

    @Before public void setUp() {
        binder = PropertyBinder.forType(Schema.class);
    }

    @Test public void propertiesSource() {
        Properties props = new Properties();
        props.setProperty("foo", "bar");
        Schema bound = binder.bind(props);

        String asString = bound.toString();

        assertThat(asString, containsString(Schema.class.getName()));
        assertThat(asString, containsString(props.toString()));
    }

    @Test public void mapSource() {
        Map<String, String> properties = Collections.singletonMap("foo", "bar");
        Schema bound = binder.bind(properties);

        String asString = bound.toString();

        assumeThat(asString, containsString(Schema.class.getName()));
        assertThat(asString, containsString(properties.toString()));
    }

    @Test public void resourceBundleSource() {
        ResourceBundle bundle = ResourceBundles.bundleWith("foo", "bar");
        Schema bound = binder.bind(bundle);

        String asString = bound.toString();

        assumeThat(asString, containsString(Schema.class.getName()));
        assertThat(asString, containsString(bundle.toString()));
    }

    @Test
    public void arbitraryPropertySource() {
        PropertySource source = new PropertySource() {
            @Override public Object propertyFor(BoundProperty key) {
                return null;
            }

            @Override public String toString() {
                return "qwerty";
            }
        };
        Schema bound = binder.bind(source);

        String asString = bound.toString();

        assumeThat(asString, containsString(Schema.class.getName()));
        assertThat(asString, containsString("qwerty"));
    }

    interface Schema {
        // no methods needed
    }
}
