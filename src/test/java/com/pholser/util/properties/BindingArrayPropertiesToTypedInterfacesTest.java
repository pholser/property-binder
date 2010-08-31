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

import static java.util.Collections.*;

import com.pholser.util.properties.boundtypes.ArrayPropertyHaver;
import com.pholser.util.properties.boundtypes.Ternary;
import org.junit.Test;

import static com.pholser.util.properties.ArrayUtils.*;
import static com.pholser.util.properties.boundtypes.Ternary.*;
import static org.junit.Assert.*;

public class BindingArrayPropertiesToTypedInterfacesTest extends TypedBindingTestSupport<ArrayPropertyHaver> {
    @Test
    public void bindingSeparatedStringValuedPropertyToStringArrayMethod() {
        assertEquals(toList(new String[] { "aaa", "bbb", "ccc" }), toList(bound.stringArrayProperty()));
    }

    @Test
    public void bindingSeparatedStringValuedPropertyWithCustomSeparatorToStringArrayMethod() {
        assertEquals(toList(new String[] { "dd", "eeee", "fffff" }),
            toList(bound.stringArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForStringArrayValuedProperty() {
        assertEquals(toList(new String[] { "g", "hh", "iii" }), toList(bound.stringArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForStringArrayValuedPropertyWithCustomSeparator() {
        assertEquals(toList(new String[] { "jjj", "kk", "L" }),
            toList(bound.stringArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedBooleanValuedPropertyToPrimitiveBooleanArrayMethod() {
        assertEquals(toList(new boolean[] { true, false, false, true }),
            toList(bound.primitiveBooleanArrayProperty()));
    }

    @Test
    public void bindingBooleanValuedPropertyWithCustomSeparatorToPrimitiveBooleanArrayMethod() {
        assertEquals(toList(new boolean[] { false, true }),
            toList(bound.primitiveBooleanArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForPrimitiveBooleanArrayProperty() {
        assertEquals(toList(new boolean[] { false, false, true, false, true }),
            toList(bound.primitiveBooleanArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForPrimitiveBooleanArrayPropertyWithSeparator() {
        assertEquals(toList(new boolean[] { true, true, true, false }),
            toList(bound.primitiveBooleanArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedBooleanValuedPropertyToBooleanWrapperArrayMethod() {
        assertEquals(toList(new Boolean[] { true, false, false, true }), toList(bound.wrappedBooleanArrayProperty()));
    }

    @Test
    public void bindingBooleanValuedPropertyWithCustomSeparatorToBooleanWrapperArrayMethod() {
        assertEquals(toList(new Boolean[] { false, true }),
            toList(bound.wrappedBooleanArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForBooleanWrapperArrayProperty() {
        assertEquals(toList(new Boolean[] { false, false, true, false, true }),
            toList(bound.wrappedBooleanArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForBooleanWrapperArrayPropertyWithSeparator() {
        assertEquals(toList(new Boolean[] { true, true, true, false }),
            toList(bound.wrappedBooleanArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedByteValuedPropertyToPrimitiveByteArrayMethod() {
        assertEquals(toList(new byte[] { 0x1, 0x2, 0x3 }), toList(bound.primitiveByteArrayProperty()));
    }

    @Test
    public void bindingByteValuedPropertyWithCustomSeparatorToPrimitiveByteArrayMethod() {
        assertEquals(toList(new byte[] { 0x0, 0x4 }), toList(bound.primitiveByteArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForPrimitiveByteArrayProperty() {
        assertEquals(toList(new byte[] { 0x18, 0x19, 0x1A, 0x1B, 0x1C }),
            toList(bound.primitiveByteArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForPrimitiveByteArrayPropertyWithSeparator() {
        assertEquals(toList(new byte[] { 0x1D, 0x1E, 0x1F, 0x20 }),
            toList(bound.primitiveByteArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedByteValuedPropertyToByteWrapperArrayMethod() {
        assertEquals(toList(new Byte[] { 0x6, 0x7, 0x8, 0x9 }), toList(bound.wrappedByteArrayProperty()));
    }

    @Test
    public void bindingByteValuedPropertyWithCustomSeparatorToByteWrapperArrayMethod() {
        assertEquals(toList(new Byte[] { 0x2D, 0x39 }), toList(bound.wrappedByteArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForByteWrapperArrayProperty() {
        assertEquals(toList(new Byte[] { 0x21, 0x22, 0x23 }), toList(bound.wrappedByteArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForByteWrapperArrayPropertyWithSeparator() {
        assertEquals(toList(new Byte[] { 0x24, 0x25, 0x26, 0x27 }),
            toList(bound.wrappedByteArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedCharacterValuedPropertyToPrimitiveCharacterArrayMethod() {
        assertEquals(toList(new char[] { 'c', 'd', 'e', 'f' }), toList(bound.primitiveCharacterArrayProperty()));
    }

    @Test
    public void bindingCharacterValuedPropertyWithCustomSeparatorToPrimitiveCharacterArrayMethod() {
        assertEquals(toList(new char[] { 'g', 'h' }),
            toList(bound.primitiveCharacterArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForPrimitiveCharacterArrayProperty() {
        assertEquals(toList(new char[] { 'h', 'i', 'j', 'k', 'l' }),
            toList(bound.primitiveCharacterArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForPrimitiveCharacterArrayPropertyWithSeparator() {
        assertEquals(toList(new char[] { 'm', 'n', 'o', 'p' }),
            toList(bound.primitiveCharacterArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedCharacterValuedPropertyToCharacterWrapperArrayMethod() {
        assertEquals(toList(new Character[] { 'q', 'r', 's', 't' }), toList(bound.wrappedCharacterArrayProperty()));
    }

    @Test
    public void bindingCharacterValuedPropertyWithCustomSeparatorToCharacterWrapperArrayMethod() {
        assertEquals(toList(new Character[] { 'u', 'v' }),
            toList(bound.wrappedCharacterArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForCharacterWrapperArrayProperty() {
        assertEquals(toList(new Character[] { 'w', 'x', 'y' }),
            toList(bound.wrappedCharacterArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForCharacterWrapperArrayPropertyWithSeparator() {
        assertEquals(toList(new Character[] { 'z', '0', '1', '2' }),
            toList(bound.wrappedCharacterArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedDoubleValuedPropertyToPrimitiveDoubleArrayMethod() {
        assertEquals(toList(new double[] { 3.0, 4.0, 5.0, 6.0 }), toList(bound.primitiveDoubleArrayProperty()));
    }

    @Test
    public void bindingDoubleValuedPropertyWithCustomSeparatorToPrimitiveDoubleArrayMethod() {
        assertEquals(toList(new double[] { 7.0, 8.0 }), toList(bound.primitiveDoubleArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForPrimitiveDoubleArrayProperty() {
        assertEquals(toList(new double[] { -1.0, -2.0, -3.0, -4.0, -5.0 }),
            toList(bound.primitiveDoubleArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForPrimitiveDoubleArrayPropertyWithSeparator() {
        assertEquals(toList(new double[] { -6.0, -7.0, -8.0, -9.0 }),
            toList(bound.primitiveDoubleArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedDoubleValuedPropertyToDoubleWrapperArrayMethod() {
        assertEquals(toList(new Double[] { -1.0, -2.0, -3.0, -4.0 }), toList(bound.wrappedDoubleArrayProperty()));
    }

    @Test
    public void bindingDoubleValuedPropertyWithCustomSeparatorToDoubleWrapperArrayMethod() {
        assertEquals(toList(new Double[] { -5.0, -6.0 }), toList(bound.wrappedDoubleArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForDoubleWrapperArrayProperty() {
        assertEquals(toList(new Double[] { -10.0, -11.0, -12.0 }),
            toList(bound.wrappedDoubleArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForDoubleWrapperArrayPropertyWithSeparator() {
        assertEquals(toList(new Double[] { -13.0, -14.0, -15.0, -16.0 }),
            toList(bound.wrappedDoubleArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedFloatValuedPropertyToPrimitiveFloatArrayMethod() {
        assertEquals(toList(new float[] { 3.3F, 3.4F, 3.5F, 3.6F }), toList(bound.primitiveFloatArrayProperty()));
    }

    @Test
    public void bindingFloatValuedPropertyWithCustomSeparatorToPrimitiveFloatArrayMethod() {
        assertEquals(toList(new float[] { 3.7F, 3.8F }),
            toList(bound.primitiveFloatArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForPrimitiveFloatArrayProperty() {
        assertEquals(toList(new float[] { 1.1F, 1.2F, 1.3F, 1.4F, 1.5F }),
            toList(bound.primitiveFloatArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForPrimitiveFloatArrayPropertyWithSeparator() {
        assertEquals(toList(new float[] { 1.6F, 1.7F, 1.8F, 1.9F }),
            toList(bound.primitiveFloatArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedFloatValuedPropertyToFloatWrapperArrayMethod() {
        assertEquals(toList(new Float[] { 4.8F, 4.9F, 5.0F, 5.1F }), toList(bound.wrappedFloatArrayProperty()));
    }

    @Test
    public void bindingFloatValuedPropertyWithCustomSeparatorToFloatWrapperArrayMethod() {
        assertEquals(toList(new Float[] { 5.2F, 5.3F }), toList(bound.wrappedFloatArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForFloatWrapperArrayProperty() {
        assertEquals(toList(new Float[] { 2.0F, 2.1F, 2.2F }), toList(bound.wrappedFloatArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForFloatWrapperArrayPropertyWithSeparator() {
        assertEquals(toList(new Float[] { 2.3F, 2.4F, 2.5F, 2.6F }),
            toList(bound.wrappedFloatArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedIntegerValuedPropertyToPrimitiveIntegerArrayMethod() {
        assertEquals(toList(new int[] { -3, -4, -5, -6 }), toList(bound.primitiveIntegerArrayProperty()));
    }

    @Test
    public void bindingIntegerValuedPropertyWithCustomSeparatorToPrimitiveIntegerArrayMethod() {
        assertEquals(toList(new int[] { -7, -8 }), toList(bound.primitiveIntegerArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForPrimitiveIntegerArrayProperty() {
        assertEquals(toList(new int[] { -1, -2, -3, -4, -5 }),
            toList(bound.primitiveIntegerArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForPrimitiveIntegerArrayPropertyWithSeparator() {
        assertEquals(toList(new int[] { -6, -7, -8, -9 }),
            toList(bound.primitiveIntegerArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedIntegerValuedPropertyToIntegerWrapperArrayMethod() {
        assertEquals(toList(new Integer[] { -18, -19, -20, -21 }), toList(bound.wrappedIntegerArrayProperty()));
    }

    @Test
    public void bindingIntegerValuedPropertyWithCustomSeparatorToIntegerWrapperArrayMethod() {
        assertEquals(toList(new Integer[] { -22, -23 }),
            toList(bound.wrappedIntegerArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForIntegerWrapperArrayProperty() {
        assertEquals(toList(new Integer[] { -10, -11, -12 }), toList(bound.wrappedIntegerArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForIntegerWrapperArrayPropertyWithSeparator() {
        assertEquals(toList(new Integer[] { -13, -14, -15, -16 }),
            toList(bound.wrappedIntegerArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedLongValuedPropertyToPrimitiveLongArrayMethod() {
        assertEquals(toList(new long[] { 3L, 4L, 5L, 6L }), toList(bound.primitiveLongArrayProperty()));
    }

    @Test
    public void bindingLongValuedPropertyWithCustomSeparatorToPrimitiveLongArrayMethod() {
        assertEquals(toList(new long[] { 7L, 8L }), toList(bound.primitiveLongArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForPrimitiveLongArrayProperty() {
        assertEquals(toList(new long[] { 44L, 45L, 46L, 47L, 48L }),
            toList(bound.primitiveLongArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForPrimitiveLongArrayPropertyWithSeparator() {
        assertEquals(toList(new long[] { 49L, 50L, 51L, 52L }),
            toList(bound.primitiveLongArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedLongValuedPropertyToLongWrapperArrayMethod() {
        assertEquals(toList(new Long[] { 18L, 19L, 20L, 21L }), toList(bound.wrappedLongArrayProperty()));
    }

    @Test
    public void bindingLongValuedPropertyWithCustomSeparatorToLongWrapperArrayMethod() {
        assertEquals(toList(new Long[] { 22L, 23L }), toList(bound.wrappedLongArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForLongWrapperArrayProperty() {
        assertEquals(toList(new Long[] { 53L, 54L, 55L }), toList(bound.wrappedLongArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForLongWrapperArrayPropertyWithSeparator() {
        assertEquals(toList(new Long[] { 56L, 57L, 58L, 59L }),
            toList(bound.wrappedLongArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedShortValuedPropertyToPrimitiveShortArrayMethod() {
        assertEquals(toList(new short[] { 51, 52, 53, 54 }), toList(bound.primitiveShortArrayProperty()));
    }

    @Test
    public void bindingShortValuedPropertyWithCustomSeparatorToPrimitiveShortArrayMethod() {
        assertEquals(toList(new short[] { 55, 56 }), toList(bound.primitiveShortArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForPrimitiveShortArrayProperty() {
        assertEquals(toList(new short[] { -20, -21, -22, -23, -24 }),
            toList(bound.primitiveShortArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForPrimitiveShortArrayPropertyWithSeparator() {
        assertEquals(toList(new short[] { -25, -26, -27, -28 }),
            toList(bound.primitiveShortArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedShortValuedPropertyToShortWrapperArrayMethod() {
        assertEquals(toList(new Short[] { 66, 67, 68, 69 }), toList(bound.wrappedShortArrayProperty()));
    }

    @Test
    public void bindingShortValuedPropertyWithCustomSeparatorToShortWrapperArrayMethod() {
        assertEquals(toList(new Short[] { 70, 71 }), toList(bound.wrappedShortArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForShortWrapperArrayProperty() {
        assertEquals(toList(new Short[] { -29, -30, -31 }), toList(bound.wrappedShortArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForShortWrapperArrayPropertyWithSeparator() {
        assertEquals(toList(new Short[] { -32, -33, -34, -35 }),
            toList(bound.wrappedShortArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedBigIntegerValuedPropertyToBigIntegerArrayMethod() {
        assertEquals(toList(new BigInteger[] { BigInteger.valueOf(124), BigInteger.valueOf(125) }),
            toList(bound.bigIntegerArrayProperty()));
    }

    @Test
    public void bindingBigIntegerValuedPropertyWithCustomSeparatorToBigIntegerArrayMethod() {
        assertEquals(toList(new BigInteger[] { BigInteger.valueOf(126), BigInteger.valueOf(127) }),
            toList(bound.bigIntegerArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForBigIntegerArrayProperty() {
        assertEquals(toList(new BigInteger[] { BigInteger.valueOf(128), BigInteger.valueOf(129),
            BigInteger.valueOf(130) }),
            toList(bound.bigIntegerArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForBigIntegerArrayPropertyWithSeparator() {
        assertEquals(toList(new BigInteger[] { BigInteger.valueOf(131), BigInteger.valueOf(132),
            BigInteger.valueOf(133) }),
            toList(bound.bigIntegerArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedBigDecimalValuedPropertyToBigDecimalArrayMethod() {
        assertEquals(toList(new BigDecimal[] { new BigDecimal("56.78"), new BigDecimal("90.12") }),
            toList(bound.bigDecimalArrayProperty()));
    }

    @Test
    public void bindingBigDecimalValuedPropertyWithCustomSeparatorToBigDecimalArrayMethod() {
        assertEquals(toList(new BigDecimal[] { new BigDecimal("34.567"), new BigDecimal("89.012") }),
            toList(bound.bigDecimalArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForBigDecimalArrayProperty() {
        assertEquals(toList(new BigDecimal[] { new BigDecimal("345.67"), new BigDecimal("890.12") }),
            toList(bound.bigDecimalArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForBigDecimalArrayPropertyWithSeparator() {
        assertEquals(toList(new BigDecimal[] { new BigDecimal("3456.78"), new BigDecimal("9012.34") }),
            toList(bound.bigDecimalArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedEnumValuedPropertyToEnumArrayMethod() {
        assertEquals(toList(new Ternary[] { YES, YES, NO, MAYBE, YES }), toList(bound.enumArrayProperty()));
    }

    @Test
    public void bindingEnumValuedPropertyWithCustomSeparatorToEnumArrayMethod() {
        assertEquals(toList(new Ternary[] { NO, NO, MAYBE, MAYBE }),
            toList(bound.enumArrayPropertyWithCustomSeparator()));
    }

    @Test
    public void supplyingDefaultForEnumArrayProperty() {
        assertEquals(toList(new Ternary[] { YES, NO, NO, MAYBE, YES }), toList(bound.enumArrayPropertyWithDefault()));
    }

    @Test
    public void supplyingDefaultForEnumArrayPropertyWithSeparator() {
        assertEquals(toList(new Ternary[] { NO, MAYBE, YES, MAYBE }),
            toList(bound.enumArrayPropertyWithDefaultAndSeparator()));
    }

    @Test
    public void bindingSeparatedDateValuedPropertyToDateArrayMethodWithParsePatterns() throws Exception {
        assertEquals(toList(new Date[] { MMM("Jan"), MMM("Feb"), MMM("Mar") }),
            toList(bound.dateArrayPropertyWithParsePatterns()));
    }

    @Test
    public void bindingDateValuedPropertyWithCustomSeparatorToDateArrayMethod() throws Exception {
        assertEquals(toList(new Date[] { MMM("Apr"), MMM("May"), MMM("Jun") }),
            toList(bound.dateArrayPropertyWithCustomSeparatorWithParsePatterns()));
    }

    @Test
    public void supplyingDefaultForDateArrayProperty() throws Exception {
        assertEquals(toList(new Date[] { MMM("Sep"), MMM("Oct") }),
            toList(bound.dateArrayPropertyWithDefaultWithParsePatterns()));
    }

    @Test
    public void supplyingDefaultForDateArrayPropertyWithSeparator() throws Exception {
        assertEquals(toList(new Date[] { MMM("Nov"), MMM("Dec")}),
            toList(bound.dateArrayPropertyWithDefaultAndSeparatorWithParsePatterns()));
    }

    @Test
    public void givingZeroLengthArrayForMissingPrimitiveArrayProperty() {
        assertEquals(emptyList(), toList(bound.missingPrimitiveArrayProperty()));
    }

    @Test
    public void givingZeroLengthArrayForMissingObjectArrayProperty() {
        assertEquals(emptyList(), toList(bound.missingObjectArrayProperty()));
    }

    @Override
    protected Class<ArrayPropertyHaver> boundType() {
        return ArrayPropertyHaver.class;
    }

    private static Date MMM(String raw) throws ParseException {
        return new SimpleDateFormat("MMM").parse(raw);
    }
}
