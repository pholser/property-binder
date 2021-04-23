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

package com.pholser.util.properties;

import com.pholser.util.properties.boundtypes.ScalarProperties;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.pholser.util.properties.boundtypes.Ternary.MAYBE;
import static com.pholser.util.properties.boundtypes.Ternary.YES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BindingScalarPropertiesToTypedInterfacesTest
  extends TypedStringBindingTestSupport<ScalarProperties> {

  @Test void missingStringProperty() {
    assertNull(bound.missingProperty());
  }

  @Test void missingStringPropertyOpt() {
    assertEquals(Optional.empty(), bound.missingPropertyOpt());
  }

  @Test void missingDateProperty() {
    assertNull(bound.missingDateProperty());
  }

  @Test void missingDatePropertyOptWithDefaultAndParseAs() throws Exception {
    assertEquals(
      Optional.of(yyyymmdd("2021-11-28")),
      bound.missingDatePropertyOpt());
  }

  @Test void missingPrimitiveWrapperProperty() {
    assertNull(bound.missingPrimitiveWrapperProperty());
  }

  @Test void stringValuedPropertyToStringMethod() {
    assertEquals("plain", bound.stringProperty());
  }

  @Test void usingMethodNameAsPropertyNameIfNoBoundPropertyAnnotation() {
    assertEquals("howdy", bound.unannotatedProperty());
  }

  @Test void defaultForStringValuedProperty() {
    assertEquals("default", bound.stringPropertyWithDefault());
  }

  @Test void primitiveBooleanValuedPropertyToPrimitiveBooleanMethod() {
    assertTrue(bound.primitiveBooleanProperty());
  }

  @Test void defaultForPrimitiveBooleanValuedProperty() {
    assertTrue(bound.primitiveBooleanPropertyWithDefault());
  }

  @Test void wrappedBooleanValuedPropertyToWrappedBooleanMethod() {
    assertFalse(bound.wrappedBooleanProperty());
  }

  @Test void defaultForWrappedBooleanValuedProperty() {
    assertFalse(bound.wrappedBooleanPropertyWithDefault());
  }

  @Test void primitiveByteValuedPropertyToPrimitiveByteMethod() {
    assertEquals((byte) 0x0A, bound.primitiveByteProperty());
  }

  @Test void defaultForPrimitiveByteValuedProperty() {
    assertEquals((byte) 0x17, bound.primitiveBytePropertyWithDefault());
  }

  @Test void wrappedByteValuedPropertyToWrappedByteMethod() {
    assertEquals(Byte.valueOf("12"), bound.wrappedByteProperty());
  }

  @Test void defaultForWrappedByteValuedProperty() {
    assertEquals(Byte.valueOf("24"), bound.wrappedBytePropertyWithDefault());
  }

  @Test void primitiveCharacterValuedPropertyToPrimitiveCharacterMethod() {
    assertEquals('b', bound.primitiveCharacterProperty());
  }

  @Test void defaultForPrimitiveCharacterValuedProperty() {
    assertEquals('a', bound.primitiveCharacterPropertyWithDefault());
  }

  @Test void wrappedCharacterValuedPropertyToWrappedCharacterMethod() {
    assertEquals(Character.valueOf('d'), bound.wrappedCharacterProperty());
  }

  @Test void defaultForWrappedCharacterValuedProperty() {
    assertEquals(
      Character.valueOf('b'),
      bound.wrappedCharacterPropertyWithDefault());
  }

  @Test void primitiveDoubleValuedPropertyToPrimitiveDoubleMethod() {
    assertEquals(3.14, bound.primitiveDoubleProperty(), 0);
  }

  @Test void defaultForPrimitiveDoubleValuedProperty() {
    assertEquals(1.0, bound.primitiveDoublePropertyWithDefault(), 0);
  }

  @Test void wrappedDoubleValuedPropertyToWrappedDoubleMethod() {
    assertEquals(Double.valueOf("2.5"), bound.wrappedDoubleProperty());
  }

  @Test void defaultForWrappedDoubleValuedProperty() {
    assertEquals(Double.valueOf(2), bound.wrappedDoublePropertyWithDefault());
  }

  @Test void primitiveFloatValuedPropertyToPrimitiveFloatMethod() {
    assertEquals(2.71F, bound.primitiveFloatProperty(), 0);
  }

  @Test void defaultForPrimitiveFloatValuedProperty() {
    assertEquals(3.0F, bound.primitiveFloatPropertyWithDefault(), 0);
  }

  @Test void wrappedFloatValuedPropertyToWrappedFloatMethod() {
    assertEquals(Float.valueOf("6.02"), bound.wrappedFloatProperty());
  }

  @Test void defaultForWrappedFloatValuedProperty() {
    assertEquals(
      Float.valueOf(4.0F),
      bound.wrappedFloatPropertyWithDefault());
  }

  @Test void primitiveIntegerValuedPropertyToPrimitiveIntegerMethod() {
    assertEquals(3, bound.primitiveIntegerProperty());
  }

  @Test void defaultForPrimitiveIntegerValuedProperty() {
    assertEquals(-1, bound.primitiveIntegerPropertyWithDefault());
  }

  @Test void wrappedIntegerValuedPropertyToWrappedIntegerMethod() {
    assertEquals(Integer.valueOf("4"), bound.wrappedIntegerProperty());
  }

  @Test void defaultForWrappedIntegerValuedProperty() {
    assertEquals(
      Integer.valueOf(-2),
      bound.wrappedIntegerPropertyWithDefault());
  }

  @Test
  void defaultForPrimitiveIntegerValuedPropertyFromValueOfAnotherProperty() {
    assertEquals(23, bound.primitiveIntegerPropertyWithReferentialDefault());
  }

  @Test void primitiveLongValuedPropertyToPrimitiveLongMethod() {
    assertEquals(-2, bound.primitiveLongProperty());
  }

  @Test void defaultForPrimitiveLongValuedProperty() {
    assertEquals(7, bound.primitiveLongPropertyWithDefault());
  }

  @Test void wrappedLongValuedPropertyToWrappedLongMethod() {
    assertEquals(Long.valueOf("-3"), bound.wrappedLongProperty());
  }

  @Test void defaultForWrappedLongValuedProperty() {
    assertEquals(Long.valueOf(8), bound.wrappedLongPropertyWithDefault());
  }

  @Test void primitiveShortValuedPropertyToPrimitiveShortMethod() {
    assertEquals((short) 44, bound.primitiveShortProperty());
  }

  @Test void defaultForPrimitiveShortValuedProperty() {
    assertEquals(9, bound.primitiveShortPropertyWithDefault());
  }

  @Test void wrappedShortValuedPropertyToWrappedShortMethod() {
    assertEquals(Short.valueOf("45"), bound.wrappedShortProperty());
  }

  @Test void defaultForWrappedShortValuedProperty() {
    assertEquals(
      Short.valueOf("10"),
      bound.wrappedShortPropertyWithDefault());
  }

  @Test void bigIntegerValuedPropertyToBigIntegerMethod() {
    assertEquals(
      new BigInteger("12345678901234567890"),
      bound.bigIntegerProperty());
  }

  @Test void defaultForBigIntegerValuedProperty() {
    assertEquals(
      BigInteger.valueOf(12345),
      bound.bigIntegerPropertyWithDefault());
  }

  @Test void bigDecimalValuedPropertyToBigDecimalMethod() {
    assertEquals(new BigDecimal("1234.5E-4"), bound.bigDecimalProperty());
  }

  @Test void defaultForBigDecimalValuedProperty() {
    assertEquals(
      new BigDecimal("6789.012"),
      bound.bigDecimalPropertyWithDefault());
  }

  @Test void enumValuedPropertyToEnumMethod() {
    assertEquals(MAYBE, bound.enumProperty());
  }

  @Test void defaultForEnumValuedProperty() {
    assertEquals(YES, bound.enumPropertyWithDefault());
  }

  @Test void bindingDateValuedPropertyToDateMethodUsingParsePatterns()
    throws Exception {

    assertEquals(yyyy("2010"), bound.datePropertyWithParsePatterns());
  }

  @Test void defaultForDateValuedPropertyUsingParsePatterns()
    throws Exception {

    assertEquals(
      yyyy("2003"),
      bound.datePropertyWithDefaultWithParsePatterns());
  }

  @Test void substitution() {
    assertEquals("really plain", bound.substituted());
  }

  @Test void canSuppressPropertySubstitutionWhenAsked() {
    assertEquals("^27[78]{1}[0-9]{8}$", bound.regex());
  }

  @Test void staticMethodsAreNotBound() {
    assertEquals(
      "static methods not bound",
      ScalarProperties.staticMethodsNotBound());
  }

  @Test void defaultAndPrivateMethodsAreNotBound() {
    assertEquals(
      "private methods not bound and default methods not bound",
      bound.defaultMethodsNotBound());
  }

  @Override protected Class<ScalarProperties> boundType() {
    return ScalarProperties.class;
  }

  private Date yyyy(String raw) throws ParseException {
    return new SimpleDateFormat("yyyy").parse(raw);
  }

  private Date yyyymmdd(String raw) throws ParseException {
    return new SimpleDateFormat("yyyy-MM-dd").parse(raw);
  }
}
