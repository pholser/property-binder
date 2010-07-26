/*
 The MIT License

 Copyright (c) 2009-2010 Paul R. Holser, Jr.

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

public class BindingScalarPropertiesToTypedInterfacesTest extends TypedBindingTestSupport<ScalarPropertyHaver> {
    @Override
    protected Class<ScalarPropertyHaver> boundType() {
        return ScalarPropertyHaver.class;
    }

    @Test
    public void shouldReturnNullForMissingStringProperty() {
        assertNull(bound.missingProperty());
    }

    @Test
    public void shouldReturnNullForMissingDateProperty() {
        assertNull(bound.missingDateProperty());
    }

    @Test
    public void shouldReturnNullForMissingPrimitiveWrapperProperty() {
        assertNull(bound.missingPrimitiveWrapperProperty());
    }

    @Test
    public void shouldBindStringValuedPropertyToStringReturningMethod() {
        assertEquals("plain", bound.stringProperty());
    }

    @Test
    public void shouldUseMethodNameAsPropertyNameIfNoBoundPropertyAnnotation() {
        assertEquals("howdy", bound.unannotatedProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForStringValuedProperty() {
        assertEquals("default", bound.stringPropertyWithDefault());
    }

    @Test
    public void shouldBindPrimitiveBooleanValuedPropertyToPrimitiveBooleanReturningMethod() {
        assertEquals(true, bound.primitiveBooleanProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveBooleanValuedProperty() {
        assertEquals(true, bound.primitiveBooleanPropertyWithDefault());
    }

    @Test
    public void shouldBindWrappedBooleanValuedPropertyToWrappedBooleanReturningMethod() {
        assertEquals(Boolean.valueOf("false"), bound.wrappedBooleanProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForWrappedBooleanValuedProperty() {
        assertEquals(Boolean.valueOf(false), bound.wrappedBooleanPropertyWithDefault());
    }

    @Test
    public void shouldBindPrimitiveByteValuedPropertyToPrimitiveByteReturningMethod() {
        assertEquals((byte) 0x0A, bound.primitiveByteProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveByteValuedProperty() {
        assertEquals((byte) 0x17, bound.primitiveBytePropertyWithDefault());
    }

    @Test
    public void shouldBindWrappedByteValuedPropertyToWrappedByteReturningMethod() {
        assertEquals(Byte.valueOf("12"), bound.wrappedByteProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForWrappedByteValuedProperty() {
        assertEquals(Byte.valueOf("24"), bound.wrappedBytePropertyWithDefault());
    }

    @Test
    public void shouldBindPrimitiveCharacterValuedPropertyToPrimitiveCharacterReturningMethod() {
        assertEquals('b', bound.primitiveCharacterProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveCharacterValuedProperty() {
        assertEquals('a', bound.primitiveCharacterPropertyWithDefault());
    }

    @Test
    public void shouldBindWrappedCharacterValuedPropertyToWrappedCharacterReturningMethod() {
        assertEquals(new Character('d'), bound.wrappedCharacterProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForWrappedCharacterValuedProperty() {
        assertEquals(Character.valueOf('b'), bound.wrappedCharacterPropertyWithDefault());
    }

    @Test
    public void shouldBindPrimitiveDoubleValuedPropertyToPrimitiveDoubleReturningMethod() {
        assertEquals(3.14, bound.primitiveDoubleProperty(), 0);
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveDoubleValuedProperty() {
        assertEquals(1.0, bound.primitiveDoublePropertyWithDefault(), 0D);
    }

    @Test
    public void shouldBindWrappedDoubleValuedPropertyToWrappedDoubleReturningMethod() {
        assertEquals(Double.valueOf("2.5"), bound.wrappedDoubleProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForWrappedDoubleValuedProperty() {
        assertEquals(Double.valueOf(2.0), bound.wrappedDoublePropertyWithDefault());
    }

    @Test
    public void shouldBindPrimitiveFloatValuedPropertyToPrimitiveFloatReturningMethod() {
        assertEquals(2.71F, bound.primitiveFloatProperty(), 0);
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveFloatValuedProperty() {
        assertEquals(3.0F, bound.primitiveFloatPropertyWithDefault(), 0F);
    }

    @Test
    public void shouldBindWrappedFloatValuedPropertyToWrappedFloatReturningMethod() {
        assertEquals(Float.valueOf("6.02"), bound.wrappedFloatProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForWrappedFloatValuedProperty() {
        assertEquals(Float.valueOf(4.0F), bound.wrappedFloatPropertyWithDefault());
    }

    @Test
    public void shouldBindPrimitiveIntegerValuedPropertyToPrimitiveIntegerReturningMethod() {
        assertEquals(3, bound.primitiveIntegerProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveIntegerValuedProperty() {
        assertEquals(-1, bound.primitiveIntegerPropertyWithDefault());
    }

    @Test
    public void shouldBindWrappedIntegerValuedPropertyToWrappedIntegerReturningMethod() {
        assertEquals(Integer.valueOf("4"), bound.wrappedIntegerProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForWrappedIntegerValuedProperty() {
        assertEquals(Integer.valueOf(-2), bound.wrappedIntegerPropertyWithDefault());
    }

    @Test
    public void shouldBindPrimitiveLongValuedPropertyToPrimitiveLongReturningMethod() {
        assertEquals(-2L, bound.primitiveLongProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveLongValuedProperty() {
        assertEquals(7L, bound.primitiveLongPropertyWithDefault());
    }

    @Test
    public void shouldBindWrappedLongValuedPropertyToWrappedLongReturningMethod() {
        assertEquals(Long.valueOf("-3"), bound.wrappedLongProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForWrappedLongValuedProperty() {
        assertEquals(Long.valueOf(8L), bound.wrappedLongPropertyWithDefault());
    }

    @Test
    public void shouldBindPrimitiveShortValuedPropertyToPrimitiveShortReturningMethod() {
        assertEquals((short) 44, bound.primitiveShortProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveShortValuedProperty() {
        assertEquals(9, bound.primitiveShortPropertyWithDefault());
    }

    @Test
    public void shouldBindWrappedShortValuedPropertyToWrappedShortReturningMethod() {
        assertEquals(Short.valueOf("45"), bound.wrappedShortProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForWrappedShortValuedProperty() {
        assertEquals(Short.valueOf("10"), bound.wrappedShortPropertyWithDefault());
    }

    @Test
    public void shouldBindBigIntegerValuedPropertyToBigIntegerReturningMethod() {
        assertEquals(new BigInteger("12345678901234567890"), bound.bigIntegerProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForBigIntegerValuedProperty() {
        assertEquals(BigInteger.valueOf(12345), bound.bigIntegerPropertyWithDefault());
    }

    @Test
    public void shouldBindBigDecimalValuedPropertyToBigDecimalReturningMethod() {
        assertEquals(new BigDecimal("1234.5E-4"), bound.bigDecimalProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForBigDecimalValuedProperty() {
        assertEquals(new BigDecimal("6789.012"), bound.bigDecimalPropertyWithDefault());
    }

    @Test
    public void shouldBindEnumValuedPropertyToEnumReturningMethod() {
        assertEquals(MAYBE, bound.enumProperty());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForEnumValuedProperty() {
        assertEquals(MAYBE, bound.enumPropertyWithDefault());
    }

    @Test
    public void shouldBindDateValuedPropertyToDateReturningMethodUsingParsePatterns() throws Exception {
        assertEquals(yyyy("2010"), bound.datePropertyWithParsePatterns());
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForDateValuedPropertyUsingParsePatterns() throws Exception {
        assertEquals(yyyy("2003"), bound.datePropertyWithDefaultWithParsePatterns());
    }

    private Date yyyy(String raw) throws ParseException {
        return new SimpleDateFormat("yyyy").parse(raw);
    }
}
