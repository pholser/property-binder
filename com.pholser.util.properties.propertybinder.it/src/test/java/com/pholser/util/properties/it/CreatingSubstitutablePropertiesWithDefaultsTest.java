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

import com.pholser.util.properties.SubstitutableProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.list;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreatingSubstitutablePropertiesWithDefaultsTest {
  private SubstitutableProperties props;

  @BeforeEach void setUp() {
    Properties defaults = new Properties();
    defaults.setProperty("first.key", "default.first.value");
    defaults.setProperty("second.key", "default.second.value + [first.key]");

    props = new SubstitutableProperties(defaults);
  }

  @Test void leveragesDefaults() {
    assertEquals("default.first.value", props.getProperty("first.key"));
  }

  @Test void substitutesWithDefaultValue() {
    assertEquals(
      "default.second.value + default.first.value",
      props.getProperty("second.key"));
  }

  @Test void keysOfDefaults() {
    assertFalse(props.containsKey("first.key"));
    assertFalse(props.containsKey("second.key"));
  }

  @Test void valuesOfDefaults() {
    assertFalse(props.containsValue("default.first.value"));
    assertFalse(props.containsValue("default.second.value + [first.key]"));
  }

  @Test void isStillEmpty() {
    assertTrue(props.isEmpty());
  }

  @Test void defaultValues() {
    assertNull(props.get("first.key"));
    assertNull(props.get("second.key"));
  }

  @Test void enumerationOfPropertyNames() {
    @SuppressWarnings("unchecked")
    List<String> names = (List<String>) list(props.propertyNames());

    assertThat(names).contains("first.key", "second.key");
  }

  @Test void stringSetOfPropertyNames() {
    Set<String> names = props.stringPropertyNames();

    assertThat(names).contains("first.key", "second.key");
  }

  @Test void keysSet() {
    assertEquals(emptyList(), list(props.keys()));
  }

  @Test void valuesSet() {
    assertEquals(emptyList(), new ArrayList<>(props.values()));
  }

  @Test void addedValueTrumpsDefault() {
    props.setProperty("first.key", "boo");

    assertEquals(
      "default.second.value + boo",
      props.getProperty("second.key"));
  }
}
