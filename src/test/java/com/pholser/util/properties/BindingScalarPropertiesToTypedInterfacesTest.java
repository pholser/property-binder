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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.pholser.util.properties.boundtypes.ScalarPropertyHaver;
import org.junit.Test;

import static com.pholser.util.properties.boundtypes.Ternary.*;
import static org.junit.Assert.*;

public class BindingScalarPropertiesToTypedInterfacesTest
    extends TypedStringBindingTestSupport<ScalarPropertyHaver> {

    @Test public void missingStringProperty() {
        assertNull(bound.missingProperty());
    }

    @Test public void missingDateProperty() {
        assertNull(bound.missingDateProperty());
    }

    @Test public void missingPrimitiveWrapperProperty() {
        assertNull(bound.missingPrimitiveWrapperProperty());
    }

    @Test public void stringValuedPropertyToStringMethod() {
        assertEquals("plain", bound.stringProperty());
    }

    @Test public void usingMethodNameAsPropertyNameIfNoBoundPropertyAnnotation() {
        assertEquals("howdy", bound.unannotatedProperty());
    }

    @Test public void defaultForStringValuedProperty() {
        assertEquals("default", bound.stringPropertyWithDefault());
    }

    @Test public void primitiveBooleanValuedPropertyToPrimitiveBooleanMethod() {
        assertTrue(bound.primitiveBooleanProperty());
    }

    @Test public void defaultForPrimitiveBooleanValuedProperty() {
        assertTrue(bound.primitiveBooleanPropertyWithDefault());
    }

    @Test public void wrappedBooleanValuedPropertyToWrappedBooleanMethod() {
        assertFalse(bound.wrappedBooleanProperty());
    }

    @Test public void defaultForWrappedBooleanValuedProperty() {
        assertFalse(bound.wrappedBooleanPropertyWithDefault());
    }

    @Test public void primitiveByteValuedPropertyToPrimitiveByteMethod() {
        assertEquals((byte) 0x0A, bound.primitiveByteProperty());
    }

    @Test public void defaultForPrimitiveByteValuedProperty() {
        assertEquals((byte) 0x17, bound.primitiveBytePropertyWithDefault());
    }

    @Test public void wrappedByteValuedPropertyToWrappedByteMethod() {
        assertEquals(Byte.valueOf("12"), bound.wrappedByteProperty());
    }

    @Test public void defaultForWrappedByteValuedProperty() {
        assertEquals(Byte.valueOf("24"), bound.wrappedBytePropertyWithDefault());
    }

    @Test public void primitiveCharacterValuedPropertyToPrimitiveCharacterMethod() {
        assertEquals('b', bound.primitiveCharacterProperty());
    }

    @Test public void defaultForPrimitiveCharacterValuedProperty() {
        assertEquals('a', bound.primitiveCharacterPropertyWithDefault());
    }

    @Test public void wrappedCharacterValuedPropertyToWrappedCharacterMethod() {
        assertEquals(new Character('d'), bound.wrappedCharacterProperty());
    }

    @Test public void defaultForWrappedCharacterValuedProperty() {
        assertEquals(Character.valueOf('b'), bound.wrappedCharacterPropertyWithDefault());
    }

    @Test public void primitiveDoubleValuedPropertyToPrimitiveDoubleMethod() {
        assertEquals(3.14, bound.primitiveDoubleProperty(), 0);
    }

    @Test public void defaultForPrimitiveDoubleValuedProperty() {
        assertEquals(1.0, bound.primitiveDoublePropertyWithDefault(), 0D);
    }

    @Test public void wrappedDoubleValuedPropertyToWrappedDoubleMethod() {
        assertEquals(Double.valueOf("2.5"), bound.wrappedDoubleProperty());
    }

    @Test public void defaultForWrappedDoubleValuedProperty() {
        assertEquals(Double.valueOf(2.0), bound.wrappedDoublePropertyWithDefault());
    }

    @Test public void primitiveFloatValuedPropertyToPrimitiveFloatMethod() {
        assertEquals(2.71F, bound.primitiveFloatProperty(), 0);
    }

    @Test public void defaultForPrimitiveFloatValuedProperty() {
        assertEquals(3.0F, bound.primitiveFloatPropertyWithDefault(), 0F);
    }

    @Test public void wrappedFloatValuedPropertyToWrappedFloatMethod() {
        assertEquals(Float.valueOf("6.02"), bound.wrappedFloatProperty());
    }

    @Test public void defaultForWrappedFloatValuedProperty() {
        assertEquals(Float.valueOf(4.0F), bound.wrappedFloatPropertyWithDefault());
    }

    @Test public void primitiveIntegerValuedPropertyToPrimitiveIntegerMethod() {
        assertEquals(3, bound.primitiveIntegerProperty());
    }

    @Test public void defaultForPrimitiveIntegerValuedProperty() {
        assertEquals(-1, bound.primitiveIntegerPropertyWithDefault());
    }

    @Test public void wrappedIntegerValuedPropertyToWrappedIntegerMethod() {
        assertEquals(Integer.valueOf("4"), bound.wrappedIntegerProperty());
    }

    @Test public void defaultForWrappedIntegerValuedProperty() {
        assertEquals(Integer.valueOf(-2), bound.wrappedIntegerPropertyWithDefault());
    }

    @Test public void defaultForPrimitiveIntegerValuedPropertyFromValueOfAnotherProperty() {
        assertEquals(23, bound.primitiveIntegerPropertyWithReferentialDefault());
    }

    @Test public void primitiveLongValuedPropertyToPrimitiveLongMethod() {
        assertEquals(-2L, bound.primitiveLongProperty());
    }

    @Test public void defaultForPrimitiveLongValuedProperty() {
        assertEquals(7L, bound.primitiveLongPropertyWithDefault());
    }

    @Test public void wrappedLongValuedPropertyToWrappedLongMethod() {
        assertEquals(Long.valueOf("-3"), bound.wrappedLongProperty());
    }

    @Test public void defaultForWrappedLongValuedProperty() {
        assertEquals(Long.valueOf(8L), bound.wrappedLongPropertyWithDefault());
    }

    @Test public void primitiveShortValuedPropertyToPrimitiveShortMethod() {
        assertEquals((short) 44, bound.primitiveShortProperty());
    }

    @Test public void defaultForPrimitiveShortValuedProperty() {
        assertEquals(9, bound.primitiveShortPropertyWithDefault());
    }

    @Test public void wrappedShortValuedPropertyToWrappedShortMethod() {
        assertEquals(Short.valueOf("45"), bound.wrappedShortProperty());
    }

    @Test public void defaultForWrappedShortValuedProperty() {
        assertEquals(Short.valueOf("10"), bound.wrappedShortPropertyWithDefault());
    }

    @Test public void bigIntegerValuedPropertyToBigIntegerMethod() {
        assertEquals(new BigInteger("12345678901234567890"), bound.bigIntegerProperty());
    }

    @Test public void defaultForBigIntegerValuedProperty() {
        assertEquals(BigInteger.valueOf(12345), bound.bigIntegerPropertyWithDefault());
    }

    @Test public void bigDecimalValuedPropertyToBigDecimalMethod() {
        assertEquals(new BigDecimal("1234.5E-4"), bound.bigDecimalProperty());
    }

    @Test public void defaultForBigDecimalValuedProperty() {
        assertEquals(new BigDecimal("6789.012"), bound.bigDecimalPropertyWithDefault());
    }

    @Test public void enumValuedPropertyToEnumMethod() {
        assertEquals(MAYBE, bound.enumProperty());
    }

    @Test public void defaultForEnumValuedProperty() {
        assertEquals(MAYBE, bound.enumPropertyWithDefault());
    }

    @Test public void bindingDateValuedPropertyToDateMethodUsingParsePatterns() throws Exception {
        assertEquals(yyyy("2010"), bound.datePropertyWithParsePatterns());
    }

    @Test public void defaultForDateValuedPropertyUsingParsePatterns() throws Exception {
        assertEquals(yyyy("2003"), bound.datePropertyWithDefaultWithParsePatterns());
    }

    @Test public void substitution() throws Exception {
        assertEquals("really plain", bound.substituted());
    }

    @Test public void canSuppressPropertySubstitutionWhenAsked() throws Exception {
        assertEquals("^27[78]{1}[0-9]{8}$", bound.regex());
    }

    @Override protected Class<ScalarPropertyHaver> boundType() {
        return ScalarPropertyHaver.class;
    }

    private Date yyyy(String raw) throws ParseException {
        return new SimpleDateFormat("yyyy").parse(raw);
    }
}
