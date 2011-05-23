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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.pholser.util.properties.boundtypes.ScalarPropertyHaver;
import org.junit.Test;

import static com.pholser.util.properties.boundtypes.Ternary.*;
import static org.junit.Assert.*;

public class BindingScalarPropertiesToTypedInterfacesTest extends TypedStringBindingTestSupport<ScalarPropertyHaver> {
    @Test
    public void missingStringProperty() {
        assertNull(bound.missingProperty());
    }

    @Test
    public void missingDateProperty() {
        assertNull(bound.missingDateProperty());
    }

    @Test
    public void missingPrimitiveWrapperProperty() {
        assertNull(bound.missingPrimitiveWrapperProperty());
    }

    @Test
    public void bindingStringValuedPropertyToStringReturningMethod() {
        assertEquals("plain", bound.stringProperty());
    }

    @Test
    public void usingMethodNameAsPropertyNameIfNoBoundPropertyAnnotation() {
        assertEquals("howdy", bound.unannotatedProperty());
    }

    @Test
    public void supplyingDefaultForStringValuedProperty() {
        assertEquals("default", bound.stringPropertyWithDefault());
    }

    @Test
    public void bindingPrimitiveBooleanValuedPropertyToPrimitiveBooleanReturningMethod() {
        assertTrue(bound.primitiveBooleanProperty());
    }

    @Test
    public void supplyingDefaultForPrimitiveBooleanValuedProperty() {
        assertTrue(bound.primitiveBooleanPropertyWithDefault());
    }

    @Test
    public void bindingWrappedBooleanValuedPropertyToWrappedBooleanReturningMethod() {
        assertFalse(bound.wrappedBooleanProperty());
    }

    @Test
    public void supplyingDefaultForWrappedBooleanValuedProperty() {
        assertFalse(bound.wrappedBooleanPropertyWithDefault());
    }

    @Test
    public void bindingPrimitiveByteValuedPropertyToPrimitiveByteReturningMethod() {
        assertEquals((byte) 0x0A, bound.primitiveByteProperty());
    }

    @Test
    public void supplyingDefaultForPrimitiveByteValuedProperty() {
        assertEquals((byte) 0x17, bound.primitiveBytePropertyWithDefault());
    }

    @Test
    public void bindingWrappedByteValuedPropertyToWrappedByteReturningMethod() {
        assertEquals(Byte.valueOf("12"), bound.wrappedByteProperty());
    }

    @Test
    public void supplyingDefaultForWrappedByteValuedProperty() {
        assertEquals(Byte.valueOf("24"), bound.wrappedBytePropertyWithDefault());
    }

    @Test
    public void bindingPrimitiveCharacterValuedPropertyToPrimitiveCharacterReturningMethod() {
        assertEquals('b', bound.primitiveCharacterProperty());
    }

    @Test
    public void supplyingDefaultForPrimitiveCharacterValuedProperty() {
        assertEquals('a', bound.primitiveCharacterPropertyWithDefault());
    }

    @Test
    public void bindingWrappedCharacterValuedPropertyToWrappedCharacterReturningMethod() {
        assertEquals(new Character('d'), bound.wrappedCharacterProperty());
    }

    @Test
    public void supplyingDefaultForWrappedCharacterValuedProperty() {
        assertEquals(Character.valueOf('b'), bound.wrappedCharacterPropertyWithDefault());
    }

    @Test
    public void bindingPrimitiveDoubleValuedPropertyToPrimitiveDoubleReturningMethod() {
        assertEquals(3.14, bound.primitiveDoubleProperty(), 0);
    }

    @Test
    public void supplyingDefaultForPrimitiveDoubleValuedProperty() {
        assertEquals(1.0, bound.primitiveDoublePropertyWithDefault(), 0D);
    }

    @Test
    public void bindingWrappedDoubleValuedPropertyToWrappedDoubleReturningMethod() {
        assertEquals(Double.valueOf("2.5"), bound.wrappedDoubleProperty());
    }

    @Test
    public void supplyingDefaultForWrappedDoubleValuedProperty() {
        assertEquals(Double.valueOf(2.0), bound.wrappedDoublePropertyWithDefault());
    }

    @Test
    public void bindingPrimitiveFloatValuedPropertyToPrimitiveFloatReturningMethod() {
        assertEquals(2.71F, bound.primitiveFloatProperty(), 0);
    }

    @Test
    public void supplyingDefaultForPrimitiveFloatValuedProperty() {
        assertEquals(3.0F, bound.primitiveFloatPropertyWithDefault(), 0F);
    }

    @Test
    public void bindingWrappedFloatValuedPropertyToWrappedFloatReturningMethod() {
        assertEquals(Float.valueOf("6.02"), bound.wrappedFloatProperty());
    }

    @Test
    public void supplyingDefaultForWrappedFloatValuedProperty() {
        assertEquals(Float.valueOf(4.0F), bound.wrappedFloatPropertyWithDefault());
    }

    @Test
    public void bindingPrimitiveIntegerValuedPropertyToPrimitiveIntegerReturningMethod() {
        assertEquals(3, bound.primitiveIntegerProperty());
    }

    @Test
    public void supplyingDefaultForPrimitiveIntegerValuedProperty() {
        assertEquals(-1, bound.primitiveIntegerPropertyWithDefault());
    }

    @Test
    public void bindingWrappedIntegerValuedPropertyToWrappedIntegerReturningMethod() {
        assertEquals(Integer.valueOf("4"), bound.wrappedIntegerProperty());
    }

    @Test
    public void supplyingDefaultForWrappedIntegerValuedProperty() {
        assertEquals(Integer.valueOf(-2), bound.wrappedIntegerPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForPrimitiveIntegerValuedPropertyFromValueOfAnotherProperty() {
        assertEquals(23, bound.primitiveIntegerPropertyWithReferentialDefault());
    }

    @Test
    public void bindingPrimitiveLongValuedPropertyToPrimitiveLongReturningMethod() {
        assertEquals(-2L, bound.primitiveLongProperty());
    }

    @Test
    public void supplyingDefaultForPrimitiveLongValuedProperty() {
        assertEquals(7L, bound.primitiveLongPropertyWithDefault());
    }

    @Test
    public void bindingWrappedLongValuedPropertyToWrappedLongReturningMethod() {
        assertEquals(Long.valueOf("-3"), bound.wrappedLongProperty());
    }

    @Test
    public void supplyingDefaultForWrappedLongValuedProperty() {
        assertEquals(Long.valueOf(8L), bound.wrappedLongPropertyWithDefault());
    }

    @Test
    public void bindingPrimitiveShortValuedPropertyToPrimitiveShortReturningMethod() {
        assertEquals((short) 44, bound.primitiveShortProperty());
    }

    @Test
    public void supplyingDefaultForPrimitiveShortValuedProperty() {
        assertEquals(9, bound.primitiveShortPropertyWithDefault());
    }

    @Test
    public void bindingWrappedShortValuedPropertyToWrappedShortReturningMethod() {
        assertEquals(Short.valueOf("45"), bound.wrappedShortProperty());
    }

    @Test
    public void supplyingDefaultForWrappedShortValuedProperty() {
        assertEquals(Short.valueOf("10"), bound.wrappedShortPropertyWithDefault());
    }

    @Test
    public void bindingBigIntegerValuedPropertyToBigIntegerReturningMethod() {
        assertEquals(new BigInteger("12345678901234567890"), bound.bigIntegerProperty());
    }

    @Test
    public void supplyingDefaultForBigIntegerValuedProperty() {
        assertEquals(BigInteger.valueOf(12345), bound.bigIntegerPropertyWithDefault());
    }

    @Test
    public void bindingBigDecimalValuedPropertyToBigDecimalReturningMethod() {
        assertEquals(new BigDecimal("1234.5E-4"), bound.bigDecimalProperty());
    }

    @Test
    public void supplyingDefaultForBigDecimalValuedProperty() {
        assertEquals(new BigDecimal("6789.012"), bound.bigDecimalPropertyWithDefault());
    }

    @Test
    public void bindingEnumValuedPropertyToEnumReturningMethod() {
        assertEquals(MAYBE, bound.enumProperty());
    }

    @Test
    public void supplyingDefaultForEnumValuedProperty() {
        assertEquals(MAYBE, bound.enumPropertyWithDefault());
    }

    @Test
    public void bindingDateValuedPropertyToDateReturningMethodUsingParsePatterns() throws Exception {
        assertEquals(yyyy("2010"), bound.datePropertyWithParsePatterns());
    }

    @Test
    public void supplyingDefaultForDateValuedPropertyUsingParsePatterns() throws Exception {
        assertEquals(yyyy("2003"), bound.datePropertyWithDefaultWithParsePatterns());
    }

    @Override
    protected Class<ScalarPropertyHaver> boundType() {
        return ScalarPropertyHaver.class;
    }

    private Date yyyy(String raw) throws ParseException {
        return new SimpleDateFormat("yyyy").parse(raw);
    }
}
