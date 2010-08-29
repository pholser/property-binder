package com.pholser.util.properties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatingEmptySubstitutablePropertiesTest {
    private SubstitutableProperties properties;

    @Before
    public void setUp() {
        properties = new SubstitutableProperties();
    }

    @Test
    public void empty() {
        assertTrue(properties.isEmpty());
    }
}
