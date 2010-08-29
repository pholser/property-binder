package com.pholser.util.properties;

import java.util.Properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreatingSubstitutablePropertiesFromOtherPropertiesTest {
    @Test
    public void copyShouldRetainKeysAndValuesOfOriginal() {
        Properties original = new Properties();
        original.setProperty("first.key", "first.value");
        original.setProperty("second.key", "second.value + [first.key]");

        SubstitutableProperties copy = new SubstitutableProperties(original);
        assertEquals("first.value", copy.getProperty("first.key"));
        assertEquals("second.value + first.value", copy.getProperty("second.key"));
    }

    @Test(expected = NullPointerException.class)
    public void nullStarters() {
        new SubstitutableProperties(null);
    }
}
