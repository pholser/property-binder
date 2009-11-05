/*
 The MIT License

 Copyright (c) 2009 Paul R. Holser, Jr.

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

import static com.pholser.util.properties.ArrayUtils.*;
import static com.pholser.util.properties.boundtypes.Ternary.*;
import static java.util.Collections.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import com.pholser.util.properties.boundtypes.ArrayPropertyHaver;
import com.pholser.util.properties.boundtypes.Ternary;

import org.junit.Test;

public class BindingArrayPropertiesToTypedInterfacesTest extends TypedBindingTestSupport<ArrayPropertyHaver> {
    @Override
    protected Class<ArrayPropertyHaver> boundType() {
        return ArrayPropertyHaver.class;
    }

    @Test
    public void shouldBindSeparatedStringValuedPropertyToStringArrayReturningMethod() {
        assertEquals( toList( new String[] { "aaa", "bbb", "ccc" } ), toList( bound.stringArrayProperty() ) );
    }

    @Test
    public void shouldBindSeparatedStringValuedPropertyWithCustomSeparatorToStringArrayReturningMethod() {
        assertEquals(
            toList( new String[] { "dd", "eeee", "fffff" } ),
            toList( bound.stringArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForStringArrayValuedProperty() {
        assertEquals( toList( new String[] { "g", "hh", "iii" } ), toList( bound.stringArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForStringArrayValuedPropertyWithCustomSeparator() {
        assertEquals(
            toList( new String[] { "jjj", "kk", "L" } ),
            toList( bound.stringArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedBooleanValuedPropertyToPrimitiveBooleanArrayReturningMethod() {
        assertEquals(
            toList( new boolean[] { true, false, false, true } ),
            toList( bound.primitiveBooleanArrayProperty() ) );
    }

    @Test
    public void shouldBindBooleanValuedPropertyWithCustomSeparatorToPrimitiveBooleanArrayReturningMethod() {
        assertEquals(
            toList( new boolean[] { false, true } ),
            toList( bound.primitiveBooleanArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveBooleanArrayProperty() {
        assertEquals(
            toList( new boolean[] { false, false, true, false, true } ),
            toList( bound.primitiveBooleanArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveBooleanArrayPropertyWithSeparator() {
        assertEquals(
            toList( new boolean[] { true, true, true, false } ),
            toList( bound.primitiveBooleanArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedBooleanValuedPropertyToBooleanWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Boolean[] { true, false, false, true } ),
            toList( bound.wrappedBooleanArrayProperty() ) );
    }

    @Test
    public void shouldBindBooleanValuedPropertyWithCustomSeparatorToBooleanWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Boolean[] { false, true } ),
            toList( bound.wrappedBooleanArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForBooleanWrapperArrayProperty() {
        assertEquals(
            toList( new Boolean[] { false, false, true, false, true } ),
            toList( bound.wrappedBooleanArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForBooleanWrapperArrayPropertyWithSeparator() {
        assertEquals(
            toList( new Boolean[] { true, true, true, false } ),
            toList( bound.wrappedBooleanArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedByteValuedPropertyToPrimitiveByteArrayReturningMethod() {
        assertEquals( toList( new byte[] { 0x1, 0x2, 0x3 } ), toList( bound.primitiveByteArrayProperty() ) );
    }

    @Test
    public void shouldBindByteValuedPropertyWithCustomSeparatorToPrimitiveByteArrayReturningMethod() {
        assertEquals(
            toList( new byte[] { 0x0, 0x4 } ),
            toList( bound.primitiveByteArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveByteArrayProperty() {
        assertEquals(
            toList( new byte[] { 0x18, 0x19, 0x1A, 0x1B, 0x1C } ),
            toList( bound.primitiveByteArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveByteArrayPropertyWithSeparator() {
        assertEquals(
            toList( new byte[] { 0x1D, 0x1E, 0x1F, 0x20 } ),
            toList( bound.primitiveByteArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedByteValuedPropertyToByteWrapperArrayReturningMethod() {
        assertEquals( toList( new Byte[] { 0x6, 0x7, 0x8, 0x9 } ), toList( bound.wrappedByteArrayProperty() ) );
    }

    @Test
    public void shouldBindByteValuedPropertyWithCustomSeparatorToByteWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Byte[] { 0x2D, 0x39 } ),
            toList( bound.wrappedByteArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForByteWrapperArrayProperty() {
        assertEquals(
            toList( new Byte[] { 0x21, 0x22, 0x23 } ),
            toList( bound.wrappedByteArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForByteWrapperArrayPropertyWithSeparator() {
        assertEquals(
            toList( new Byte[] { 0x24, 0x25, 0x26, 0x27 } ),
            toList( bound.wrappedByteArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedCharacterValuedPropertyToPrimitiveCharacterArrayReturningMethod() {
        assertEquals(
            toList( new char[] { 'c', 'd', 'e', 'f' } ),
            toList( bound.primitiveCharacterArrayProperty() ) );
    }

    @Test
    public void shouldBindCharacterValuedPropertyWithCustomSeparatorToPrimitiveCharacterArrayReturningMethod() {
        assertEquals(
            toList( new char[] { 'g', 'h' } ),
            toList( bound.primitiveCharacterArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveCharacterArrayProperty() {
        assertEquals(
            toList( new char[] { 'h', 'i', 'j', 'k', 'l' } ),
            toList( bound.primitiveCharacterArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveCharacterArrayPropertyWithSeparator() {
        assertEquals(
            toList( new char[] { 'm', 'n', 'o', 'p' } ),
            toList( bound.primitiveCharacterArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedCharacterValuedPropertyToCharacterWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Character[] { 'q', 'r', 's', 't' } ),
            toList( bound.wrappedCharacterArrayProperty() ) );
    }

    @Test
    public void shouldBindCharacterValuedPropertyWithCustomSeparatorToCharacterWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Character[] { 'u', 'v' } ),
            toList( bound.wrappedCharacterArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForCharacterWrapperArrayProperty() {
        assertEquals(
            toList( new Character[] { 'w', 'x', 'y' } ),
            toList( bound.wrappedCharacterArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForCharacterWrapperArrayPropertyWithSeparator() {
        assertEquals(
            toList( new Character[] { 'z', '0', '1', '2' } ),
            toList( bound.wrappedCharacterArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedDoubleValuedPropertyToPrimitiveDoubleArrayReturningMethod() {
        assertEquals( toList( new double[] { 3.0, 4.0, 5.0, 6.0 } ), toList( bound.primitiveDoubleArrayProperty() ) );
    }

    @Test
    public void shouldBindDoubleValuedPropertyWithCustomSeparatorToPrimitiveDoubleArrayReturningMethod() {
        assertEquals(
            toList( new double[] { 7.0, 8.0 } ),
            toList( bound.primitiveDoubleArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveDoubleArrayProperty() {
        assertEquals(
            toList( new double[] { -1.0, -2.0, -3.0, -4.0, -5.0 } ),
            toList( bound.primitiveDoubleArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveDoubleArrayPropertyWithSeparator() {
        assertEquals(
            toList( new double[] { -6.0, -7.0, -8.0, -9.0 } ),
            toList( bound.primitiveDoubleArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedDoubleValuedPropertyToDoubleWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Double[] { -1.0, -2.0, -3.0, -4.0 } ),
            toList( bound.wrappedDoubleArrayProperty() ) );
    }

    @Test
    public void shouldBindDoubleValuedPropertyWithCustomSeparatorToDoubleWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Double[] { -5.0, -6.0 } ),
            toList( bound.wrappedDoubleArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForDoubleWrapperArrayProperty() {
        assertEquals(
            toList( new Double[] { -10.0, -11.0, -12.0 } ),
            toList( bound.wrappedDoubleArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForDoubleWrapperArrayPropertyWithSeparator() {
        assertEquals(
            toList( new Double[] { -13.0, -14.0, -15.0, -16.0 } ),
            toList( bound.wrappedDoubleArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedFloatValuedPropertyToPrimitiveFloatArrayReturningMethod() {
        assertEquals(
            toList( new float[] { 3.3F, 3.4F, 3.5F, 3.6F } ),
            toList( bound.primitiveFloatArrayProperty() ) );
    }

    @Test
    public void shouldBindFloatValuedPropertyWithCustomSeparatorToPrimitiveFloatArrayReturningMethod() {
        assertEquals(
            toList( new float[] { 3.7F, 3.8F } ),
            toList( bound.primitiveFloatArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveFloatArrayProperty() {
        assertEquals(
            toList( new float[] { 1.1F, 1.2F, 1.3F, 1.4F, 1.5F } ),
            toList( bound.primitiveFloatArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveFloatArrayPropertyWithSeparator() {
        assertEquals(
            toList( new float[] { 1.6F, 1.7F, 1.8F, 1.9F } ),
            toList( bound.primitiveFloatArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedFloatValuedPropertyToFloatWrapperArrayReturningMethod() {
        assertEquals( toList( new Float[] { 4.8F, 4.9F, 5.0F, 5.1F } ), toList( bound.wrappedFloatArrayProperty() ) );
    }

    @Test
    public void shouldBindFloatValuedPropertyWithCustomSeparatorToFloatWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Float[] { 5.2F, 5.3F } ),
            toList( bound.wrappedFloatArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForFloatWrapperArrayProperty() {
        assertEquals(
            toList( new Float[] { 2.0F, 2.1F, 2.2F } ),
            toList( bound.wrappedFloatArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForFloatWrapperArrayPropertyWithSeparator() {
        assertEquals(
            toList( new Float[] { 2.3F, 2.4F, 2.5F, 2.6F } ),
            toList( bound.wrappedFloatArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedIntegerValuedPropertyToPrimitiveIntegerArrayReturningMethod() {
        assertEquals( toList( new int[] { -3, -4, -5, -6 } ), toList( bound.primitiveIntegerArrayProperty() ) );
    }

    @Test
    public void shouldBindIntegerValuedPropertyWithCustomSeparatorToPrimitiveIntegerArrayReturningMethod() {
        assertEquals(
            toList( new int[] { -7, -8 } ),
            toList( bound.primitiveIntegerArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveIntegerArrayProperty() {
        assertEquals(
            toList( new int[] { -1, -2, -3, -4, -5 } ),
            toList( bound.primitiveIntegerArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveIntegerArrayPropertyWithSeparator() {
        assertEquals(
            toList( new int[] { -6, -7, -8, -9 } ),
            toList( bound.primitiveIntegerArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedIntegerValuedPropertyToIntegerWrapperArrayReturningMethod() {
        assertEquals( toList( new Integer[] { -18, -19, -20, -21 } ), toList( bound.wrappedIntegerArrayProperty() ) );
    }

    @Test
    public void shouldBindIntegerValuedPropertyWithCustomSeparatorToIntegerWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Integer[] { -22, -23 } ),
            toList( bound.wrappedIntegerArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForIntegerWrapperArrayProperty() {
        assertEquals(
            toList( new Integer[] { -10, -11, -12 } ),
            toList( bound.wrappedIntegerArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForIntegerWrapperArrayPropertyWithSeparator() {
        assertEquals(
            toList( new Integer[] { -13, -14, -15, -16 } ),
            toList( bound.wrappedIntegerArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedLongValuedPropertyToPrimitiveLongArrayReturningMethod() {
        assertEquals( toList( new long[] { 3L, 4L, 5L, 6L } ), toList( bound.primitiveLongArrayProperty() ) );
    }

    @Test
    public void shouldBindLongValuedPropertyWithCustomSeparatorToPrimitiveLongArrayReturningMethod() {
        assertEquals(
            toList( new long[] { 7L, 8L } ),
            toList( bound.primitiveLongArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveLongArrayProperty() {
        assertEquals(
            toList( new long[] { 44L, 45L, 46L, 47L, 48L } ),
            toList( bound.primitiveLongArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveLongArrayPropertyWithSeparator() {
        assertEquals(
            toList( new long[] { 49L, 50L, 51L, 52L } ),
            toList( bound.primitiveLongArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedLongValuedPropertyToLongWrapperArrayReturningMethod() {
        assertEquals( toList( new Long[] { 18L, 19L, 20L, 21L } ), toList( bound.wrappedLongArrayProperty() ) );
    }

    @Test
    public void shouldBindLongValuedPropertyWithCustomSeparatorToLongWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Long[] { 22L, 23L } ),
            toList( bound.wrappedLongArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForLongWrapperArrayProperty() {
        assertEquals(
            toList( new Long[] { 53L, 54L, 55L } ),
            toList( bound.wrappedLongArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForLongWrapperArrayPropertyWithSeparator() {
        assertEquals(
            toList( new Long[] { 56L, 57L, 58L, 59L } ),
            toList( bound.wrappedLongArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedShortValuedPropertyToPrimitiveShortArrayReturningMethod() {
        assertEquals( toList( new short[] { 51, 52, 53, 54 } ), toList( bound.primitiveShortArrayProperty() ) );
    }

    @Test
    public void shouldBindShortValuedPropertyWithCustomSeparatorToPrimitiveShortArrayReturningMethod() {
        assertEquals(
            toList( new short[] { 55, 56 } ),
            toList( bound.primitiveShortArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveShortArrayProperty() {
        assertEquals(
            toList( new short[] { -20, -21, -22, -23, -24 } ),
            toList( bound.primitiveShortArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForPrimitiveShortArrayPropertyWithSeparator() {
        assertEquals(
            toList( new short[] { -25, -26, -27, -28 } ),
            toList( bound.primitiveShortArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedShortValuedPropertyToShortWrapperArrayReturningMethod() {
        assertEquals( toList( new Short[] { 66, 67, 68, 69 } ), toList( bound.wrappedShortArrayProperty() ) );
    }

    @Test
    public void shouldBindShortValuedPropertyWithCustomSeparatorToShortWrapperArrayReturningMethod() {
        assertEquals(
            toList( new Short[] { 70, 71 } ),
            toList( bound.wrappedShortArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForShortWrapperArrayProperty() {
        assertEquals(
            toList( new Short[] { -29, -30, -31 } ),
            toList( bound.wrappedShortArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForShortWrapperArrayPropertyWithSeparator() {
        assertEquals(
            toList( new Short[] { -32, -33, -34, -35 } ),
            toList( bound.wrappedShortArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedBigIntegerValuedPropertyToBigIntegerArrayReturningMethod() {
        assertEquals(
            toList( new BigInteger[] { BigInteger.valueOf( 124 ), BigInteger.valueOf( 125 ) } ),
            toList( bound.bigIntegerArrayProperty() ) );
    }

    @Test
    public void shouldBindBigIntegerValuedPropertyWithCustomSeparatorToBigIntegerArrayReturningMethod() {
        assertEquals(
            toList( new BigInteger[] { BigInteger.valueOf( 126 ), BigInteger.valueOf( 127 ) } ),
            toList( bound.bigIntegerArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForBigIntegerArrayProperty() {
        assertEquals(
            toList( new BigInteger[] {
                BigInteger.valueOf( 128 ), BigInteger.valueOf( 129 ), BigInteger.valueOf( 130 ) } ),
            toList( bound.bigIntegerArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForBigIntegerArrayPropertyWithSeparator() {
        assertEquals(
            toList( new BigInteger[] {
                BigInteger.valueOf( 131 ), BigInteger.valueOf( 132 ), BigInteger.valueOf( 133 ) } ),
            toList( bound.bigIntegerArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedBigDecimalValuedPropertyToBigDecimalArrayReturningMethod() {
        assertEquals(
            toList( new BigDecimal[] { new BigDecimal( "56.78" ), new BigDecimal( "90.12" ) } ),
            toList( bound.bigDecimalArrayProperty() ) );
    }

    @Test
    public void shouldBindBigDecimalValuedPropertyWithCustomSeparatorToBigDecimalArrayReturningMethod() {
        assertEquals(
            toList( new BigDecimal[] { new BigDecimal( "34.567" ), new BigDecimal( "89.012" ) } ),
            toList( bound.bigDecimalArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForBigDecimalArrayProperty() {
        assertEquals(
            toList( new BigDecimal[] { new BigDecimal( "345.67" ), new BigDecimal( "890.12" ) } ),
            toList( bound.bigDecimalArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForBigDecimalArrayPropertyWithSeparator() {
        assertEquals(
            toList( new BigDecimal[] { new BigDecimal( "3456.78" ), new BigDecimal( "9012.34" ) } ),
            toList( bound.bigDecimalArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldBindSeparatedEnumValuedPropertyToEnumArrayReturningMethod() {
        assertEquals( toList( new Ternary[] { YES, YES, NO, MAYBE, YES } ), toList( bound.enumArrayProperty() ) );
    }

    @Test
    public void shouldBindEnumValuedPropertyWithCustomSeparatorToEnumArrayReturningMethod() {
        assertEquals(
            toList( new Ternary[] { NO, NO, MAYBE, MAYBE } ),
            toList( bound.enumArrayPropertyWithCustomSeparator() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForEnumArrayProperty() {
        assertEquals(
            toList( new Ternary[] { YES, NO, NO, MAYBE, YES } ),
            toList( bound.enumArrayPropertyWithDefault() ) );
    }

    @Test
    public void shouldBeAbleToSupplyDefaultForEnumArrayPropertyWithSeparator() {
        assertEquals(
            toList( new Ternary[] { NO, MAYBE, YES, MAYBE } ),
            toList( bound.enumArrayPropertyWithDefaultAndSeparator() ) );
    }

    @Test
    public void shouldGiveZeroLengthArrayForMissingPrimitiveArrayProperty() {
        assertEquals( emptyList(), toList( bound.missingPrimitiveArrayProperty() ) );
    }

    @Test
    public void shouldGiveZeroLengthArrayForMissingObjectArrayProperty() {
        assertEquals( emptyList(), toList( bound.missingObjectArrayProperty() ) );
    }
}
