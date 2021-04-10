package com.pholser.util.properties.internal;

import com.pholser.util.properties.BoundProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InternalBoundPropertyTest {
  private InternalBoundProperty boundProperty;

  @BeforeEach void setUp() {
    boundProperty = new InternalBoundProperty("x");
  }

  @Test void hasCorrectAnnotationType() {
    assertEquals(BoundProperty.class, boundProperty.annotationType());
  }

  @Test void notEqualToNull() {
    assertNotEquals(boundProperty, null);
  }

  @Test void notEqualToObjectInDifferentHierarchy() {
    assertNotEquals(boundProperty, "x");
  }

  @Test void notEqualIfValuesDiffer() {
    assertNotEquals(boundProperty, new InternalBoundProperty("y"));
  }

  @Test void hashCodeIsThatOfValue() {
    assertEquals("x".hashCode(), boundProperty.hashCode());
  }

  @Test void equalityVersusBakedInAnnotations() throws Exception {
    BoundProperty bakedIn =
      getClass().getDeclaredMethod("x").getAnnotation(BoundProperty.class);

    assertEquals(boundProperty, bakedIn);
    assertEquals(bakedIn, boundProperty);
  }

  @BoundProperty("x") private void x() {
  }
}
