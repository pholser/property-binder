package com.pholser.util.properties.internal;

import com.pholser.util.properties.BoundProperty;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InternalBoundPropertyTest {
    private InternalBoundProperty boundProperty;

    @Before public void setUp() {
        boundProperty = new InternalBoundProperty("x");
    }

    @Test public void hasCorrectAnnotationType() {
        assertEquals(BoundProperty.class, boundProperty.annotationType());
    }

    @Test public void notEqualToNull() {
        assertNotEquals(boundProperty, null);
    }

    @Test public void notEqualToObjectInDifferentHierarchy() {
        assertNotEquals(boundProperty, "x");
    }

    @Test public void notEqualIfValuesDiffer() {
        assertNotEquals(boundProperty, new InternalBoundProperty("y"));
    }

    @Test public void hasCodeIsThatOfValue() {
        assertEquals("x".hashCode(), boundProperty.hashCode());
    }
}
