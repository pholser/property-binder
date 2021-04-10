package com.pholser.util.properties.examples;

import com.pholser.util.properties.PropertyBinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.pholser.util.properties.boundtypes.Ternary.MAYBE;
import static com.pholser.util.properties.boundtypes.Ternary.NO;
import static com.pholser.util.properties.boundtypes.Ternary.YES;
import static java.math.BigDecimal.TEN;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleTest {
  private ExampleSchema bound;

  @BeforeEach void initializeFixture() throws Exception {
    PropertyBinder<ExampleSchema> binder =
      PropertyBinder.forType(ExampleSchema.class);
    bound = binder.bind(new File("src/test/resources/example.properties"));
  }

  @Test void bindingToPropertyWithMethodName() {
    assertEquals("no conversion", bound.unadorned());
  }

  @Test void bindingToPropertyWithAnnotation() {
    assertEquals("also no conversion", bound.annotated());
  }

  @Test void convertingPrimitivePropertyValues() {
    assertEquals(2, bound.intProperty());
  }

  @Test void convertingWrappedPrimitivePropertyValues() {
    assertEquals(Long.valueOf(-1), bound.wrappedLongProperty());
  }

  @Test void convertingCommaSeparatedValuedPropertyToArray() {
    assertArrayEquals(new char[] {'a', 'b', 'c'}, bound.charArrayProperty());
  }

  @Test void convertingCommaSeparatedValuedPropertyToList() {
    assertEquals(asList('d', 'e', 'f'), bound.charListProperty());
  }

  @Test void honoringDifferentSeparatorsForAggregateProperties() {
    assertEquals(
      asList(YES, NO, YES, MAYBE),
      bound.listOfEnumsWithSeparator());
  }

  @Test void honoringDefaultValueIndicationWhenPropertyNotPresent() {
    assertEquals(TEN, bound.bigDecimalPropertyWithDefault());
  }

  @Test void honoringDateFormatSpecificationsForDateProperties()
    throws Exception {

    assertEquals(MMddyyyy("02/14/2010"), bound.dateProperty());
  }

  @Test void formattingPropertiesCorrespondingToMethodsWithArguments()
    throws Exception {

    assertEquals(
      "10 seconds to 12:00:00 AM",
      bound.argsProperty(10, MMddyyyy("01/01/2011")));
  }

  private static Date MMddyyyy(String raw) throws ParseException {
    return new SimpleDateFormat("MM/dd/yyyy").parse(raw);
  }
}
