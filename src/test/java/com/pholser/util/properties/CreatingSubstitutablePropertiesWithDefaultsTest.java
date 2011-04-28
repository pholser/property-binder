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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static java.util.Collections.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

public class CreatingSubstitutablePropertiesWithDefaultsTest {
    private Properties defaults;
    private SubstitutableProperties properties;

    @Before
    public void setUp() {
        defaults = new Properties();
        defaults.setProperty("first.key", "default.first.value");
        defaults.setProperty("second.key", "default.second.value + [first.key]");

        properties = new SubstitutableProperties(defaults);
    }

    @Test
    public void shouldLeverageDefaults() {
        assertEquals("default.first.value", properties.getProperty("first.key"));
    }

    @Test
    public void shouldSubstituteWithDefaultValue() {
        assertEquals("default.second.value + default.first.value", properties.getProperty("second.key"));
    }

    @Test
    public void doesNotContainKeysOfDefaults() {
        assertFalse(properties.containsKey("first.key"));
        assertFalse(properties.containsKey("second.key"));
    }

    @Test
    public void doesNotContainValuesOfDefaults() {
        assertFalse(properties.containsValue("default.first.value"));
        assertFalse(properties.containsValue("default.second.value + [first.key]"));
    }

    @Test
    public void isStillEmpty() {
        assertTrue(properties.isEmpty());
    }

    @Test
    public void cannotGetDefaultValues() {
        assertNull(properties.get("first.key"));
        assertNull(properties.get("second.key"));
    }

    @Test
    public void enumerationOfPropertyNamesIsNotEmpty() {
        @SuppressWarnings("unchecked")
        List<String> names = (List<String>) list(properties.propertyNames());

        assertThat(names, hasItem("first.key"));
        assertThat(names, hasItem("second.key"));
    }

    @Test
    public void stringSetOfPropertyNamesIsNotEmpty() {
        Set<String> names = properties.stringPropertyNames();

        assertThat(names, hasItem("first.key"));
        assertThat(names, hasItem("second.key"));
    }

    @Test
    public void keysSetIsEmpty() {
        assertEquals(emptyList(), list(properties.keys()));
    }

    @Test
    public void valuesSetIsEmpty() {
        assertEquals(emptyList(), new ArrayList<Object>(properties.values()));
    }

    @Test
    public void addedValueShouldTrumpDefault() {
        properties.setProperty("first.key", "boo");

        assertEquals("default.second.value + boo", properties.getProperty("second.key"));
    }
}
