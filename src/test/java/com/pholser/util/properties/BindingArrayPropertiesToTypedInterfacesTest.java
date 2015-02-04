/*
 The MIT License

 Copyright (c) 2009-2013 Paul R. Holser, Jr.

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

import com.pholser.util.properties.boundtypes.ArrayPropertyHaver;
import com.pholser.util.properties.boundtypes.Ternary;
import org.junit.Test;

import static com.pholser.util.properties.boundtypes.Ternary.*;
import static org.junit.Assert.*;

public class BindingArrayPropertiesToTypedInterfacesTest extends TypedStringBindingTestSupport<ArrayPropertyHaver> {
    @Test
    public void bindingSeparatedStringValuedPropertyToStringArrayMethod() {
        assertArrayEquals(new String[] { "aaa", "bbb", "ccc" }, bound.stringArrayProperty());
    }

    @Test
    public void bindingSeparatedStringValuedPropertyWithCustomSeparatorToStringArrayMethod() {
        assertArrayEquals(new String[] { "dd", "eeee", "fffff" }, bound.stringArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForStringArrayValuedProperty() {
        assertArrayEquals(new String[] { "g", "hh", "iii" }, bound.stringArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForStringArrayValuedPropertyWithCustomSeparator() {
        assertArrayEquals(new String[] { "jjj", "kk", "L" }, bound.stringArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedBooleanValuedPropertyToPrimitiveBooleanArrayMethod() {
        assertArrayEquals(new boolean[] { true, false, false, true }, bound.primitiveBooleanArrayProperty());
    }

    @Test
    public void bindingBooleanValuedPropertyWithCustomSeparatorToPrimitiveBooleanArrayMethod() {
        assertArrayEquals(
            new boolean[] { false, true },
            bound.primitiveBooleanArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForPrimitiveBooleanArrayProperty() {
        assertArrayEquals(
            new boolean[] { false, false, true, false, true },
            bound.primitiveBooleanArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForPrimitiveBooleanArrayPropertyWithSeparator() {
        assertArrayEquals(
            new boolean[] { true, true, true, false },
            bound.primitiveBooleanArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedBooleanValuedPropertyToBooleanWrapperArrayMethod() {
        assertArrayEquals(new Boolean[] { true, false, false, true }, bound.wrappedBooleanArrayProperty());
    }

    @Test
    public void bindingBooleanValuedPropertyWithCustomSeparatorToBooleanWrapperArrayMethod() {
        assertArrayEquals(new Boolean[] { false, true }, bound.wrappedBooleanArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForBooleanWrapperArrayProperty() {
        assertArrayEquals(
            new Boolean[] { false, false, true, false, true },
            bound.wrappedBooleanArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForBooleanWrapperArrayPropertyWithSeparator() {
        assertArrayEquals(
            new Boolean[] { true, true, true, false },
            bound.wrappedBooleanArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedByteValuedPropertyToPrimitiveByteArrayMethod() {
        assertArrayEquals(new byte[] { 0x1, 0x2, 0x3 }, bound.primitiveByteArrayProperty());
    }

    @Test
    public void bindingByteValuedPropertyWithCustomSeparatorToPrimitiveByteArrayMethod() {
        assertArrayEquals(new byte[] { 0x0, 0x4 }, bound.primitiveByteArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForPrimitiveByteArrayProperty() {
        assertArrayEquals(
            new byte[] { 0x18, 0x19, 0x1A, 0x1B, 0x1C },
            bound.primitiveByteArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForPrimitiveByteArrayPropertyWithSeparator() {
        assertArrayEquals(
            new byte[] { 0x1D, 0x1E, 0x1F, 0x20 },
            bound.primitiveByteArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedByteValuedPropertyToByteWrapperArrayMethod() {
        assertArrayEquals(new Byte[] { 0x6, 0x7, 0x8, 0x9 }, bound.wrappedByteArrayProperty());
    }

    @Test
    public void bindingByteValuedPropertyWithCustomSeparatorToByteWrapperArrayMethod() {
        assertArrayEquals(new Byte[] { 0x2D, 0x39 }, bound.wrappedByteArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForByteWrapperArrayProperty() {
        assertArrayEquals(new Byte[] { 0x21, 0x22, 0x23 }, bound.wrappedByteArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForByteWrapperArrayPropertyWithSeparator() {
        assertArrayEquals(
            new Byte[] { 0x24, 0x25, 0x26, 0x27 },
            bound.wrappedByteArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedCharacterValuedPropertyToPrimitiveCharacterArrayMethod() {
        assertArrayEquals(new char[] { 'c', 'd', 'e', 'f' }, bound.primitiveCharacterArrayProperty());
    }

    @Test
    public void bindingCharacterValuedPropertyWithCustomSeparatorToPrimitiveCharacterArrayMethod() {
        assertArrayEquals(new char[] { 'g', 'h' }, bound.primitiveCharacterArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForPrimitiveCharacterArrayProperty() {
        assertArrayEquals(new char[] { 'h', 'i', 'j', 'k', 'l' }, bound.primitiveCharacterArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForPrimitiveCharacterArrayPropertyWithSeparator() {
        assertArrayEquals(
            new char[] { 'm', 'n', 'o', 'p' },
            bound.primitiveCharacterArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedCharacterValuedPropertyToCharacterWrapperArrayMethod() {
        assertArrayEquals(new Character[] { 'q', 'r', 's', 't' }, bound.wrappedCharacterArrayProperty());
    }

    @Test
    public void bindingCharacterValuedPropertyWithCustomSeparatorToCharacterWrapperArrayMethod() {
        assertArrayEquals(new Character[] { 'u', 'v' }, bound.wrappedCharacterArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForCharacterWrapperArrayProperty() {
        assertArrayEquals(new Character[] { 'w', 'x', 'y' }, bound.wrappedCharacterArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForCharacterWrapperArrayPropertyWithSeparator() {
        assertArrayEquals(
            new Character[] { 'z', '0', '1', '2' },
            bound.wrappedCharacterArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedDoubleValuedPropertyToPrimitiveDoubleArrayMethod() {
        assertArrayEquals(new double[] { 3.0, 4.0, 5.0, 6.0 }, bound.primitiveDoubleArrayProperty(), 0D);
    }

    @Test
    public void bindingDoubleValuedPropertyWithCustomSeparatorToPrimitiveDoubleArrayMethod() {
        assertArrayEquals(new double[] { 7.0, 8.0 }, bound.primitiveDoubleArrayPropertyWithCustomSeparator(), 0D);
    }

    @Test
    public void supplyingDefaultForPrimitiveDoubleArrayProperty() {
        assertArrayEquals(
            new double[] { -1.0, -2.0, -3.0, -4.0, -5.0 },
            bound.primitiveDoubleArrayPropertyWithDefault(), 0D);
    }

    @Test
    public void supplyingDefaultForPrimitiveDoubleArrayPropertyWithSeparator() {
        assertArrayEquals(
            new double[] { -6.0, -7.0, -8.0, -9.0 },
            bound.primitiveDoubleArrayPropertyWithDefaultAndSeparator(), 0D);
    }

    @Test
    public void bindingSeparatedDoubleValuedPropertyToDoubleWrapperArrayMethod() {
        assertArrayEquals(new Double[] { -1.0, -2.0, -3.0, -4.0 }, bound.wrappedDoubleArrayProperty());
    }

    @Test
    public void bindingDoubleValuedPropertyWithCustomSeparatorToDoubleWrapperArrayMethod() {
        assertArrayEquals(new Double[] { -5.0, -6.0 }, bound.wrappedDoubleArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForDoubleWrapperArrayProperty() {
        assertArrayEquals(new Double[] { -10.0, -11.0, -12.0 }, bound.wrappedDoubleArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForDoubleWrapperArrayPropertyWithSeparator() {
        assertArrayEquals(
            new Double[] { -13.0, -14.0, -15.0, -16.0 },
            bound.wrappedDoubleArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedFloatValuedPropertyToPrimitiveFloatArrayMethod() {
        assertArrayEquals(new float[] { 3.3F, 3.4F, 3.5F, 3.6F }, bound.primitiveFloatArrayProperty(), 0F);
    }

    @Test
    public void bindingFloatValuedPropertyWithCustomSeparatorToPrimitiveFloatArrayMethod() {
        assertArrayEquals(new float[] { 3.7F, 3.8F }, bound.primitiveFloatArrayPropertyWithCustomSeparator(), 0F);
    }

    @Test
    public void supplyingDefaultForPrimitiveFloatArrayProperty() {
        assertArrayEquals(
            new float[] { 1.1F, 1.2F, 1.3F, 1.4F, 1.5F },
            bound.primitiveFloatArrayPropertyWithDefault(), 0F);
    }

    @Test
    public void supplyingDefaultForPrimitiveFloatArrayPropertyWithSeparator() {
        assertArrayEquals(
            new float[] { 1.6F, 1.7F, 1.8F, 1.9F },
            bound.primitiveFloatArrayPropertyWithDefaultAndSeparator(), 0F);
    }

    @Test
    public void bindingSeparatedFloatValuedPropertyToFloatWrapperArrayMethod() {
        assertArrayEquals(new Float[] { 4.8F, 4.9F, 5.0F, 5.1F }, bound.wrappedFloatArrayProperty());
    }

    @Test
    public void bindingFloatValuedPropertyWithCustomSeparatorToFloatWrapperArrayMethod() {
        assertArrayEquals(new Float[] { 5.2F, 5.3F }, bound.wrappedFloatArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForFloatWrapperArrayProperty() {
        assertArrayEquals(new Float[] { 2.0F, 2.1F, 2.2F }, bound.wrappedFloatArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForFloatWrapperArrayPropertyWithSeparator() {
        assertArrayEquals(
            new Float[] { 2.3F, 2.4F, 2.5F, 2.6F },
            bound.wrappedFloatArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedIntegerValuedPropertyToPrimitiveIntegerArrayMethod() {
        assertArrayEquals(new int[] { -3, -4, -5, -6 }, bound.primitiveIntegerArrayProperty());
    }

    @Test
    public void bindingIntegerValuedPropertyWithCustomSeparatorToPrimitiveIntegerArrayMethod() {
        assertArrayEquals(new int[] { -7, -8 }, bound.primitiveIntegerArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForPrimitiveIntegerArrayProperty() {
        assertArrayEquals(new int[] { -1, -2, -3, -4, -5 }, bound.primitiveIntegerArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForPrimitiveIntegerArrayPropertyWithSeparator() {
        assertArrayEquals(new int[] { -6, -7, -8, -9 }, bound.primitiveIntegerArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedIntegerValuedPropertyToIntegerWrapperArrayMethod() {
        assertArrayEquals(new Integer[] { -18, -19, -20, -21 }, bound.wrappedIntegerArrayProperty());
    }

    @Test
    public void bindingIntegerValuedPropertyWithCustomSeparatorToIntegerWrapperArrayMethod() {
        assertArrayEquals(new Integer[] { -22, -23 }, bound.wrappedIntegerArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForIntegerWrapperArrayProperty() {
        assertArrayEquals(new Integer[] { -10, -11, -12 }, bound.wrappedIntegerArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForIntegerWrapperArrayPropertyWithSeparator() {
        assertArrayEquals(
            new Integer[] { -13, -14, -15, -16 },
            bound.wrappedIntegerArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedLongValuedPropertyToPrimitiveLongArrayMethod() {
        assertArrayEquals(new long[] { 3L, 4L, 5L, 6L }, bound.primitiveLongArrayProperty());
    }

    @Test
    public void bindingLongValuedPropertyWithCustomSeparatorToPrimitiveLongArrayMethod() {
        assertArrayEquals(new long[] { 7L, 8L }, bound.primitiveLongArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForPrimitiveLongArrayProperty() {
        assertArrayEquals(new long[] { 44L, 45L, 46L, 47L, 48L }, bound.primitiveLongArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForPrimitiveLongArrayPropertyWithSeparator() {
        assertArrayEquals(new long[] { 49L, 50L, 51L, 52L }, bound.primitiveLongArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedLongValuedPropertyToLongWrapperArrayMethod() {
        assertArrayEquals(new Long[] { 18L, 19L, 20L, 21L }, bound.wrappedLongArrayProperty());
    }

    @Test
    public void bindingLongValuedPropertyWithCustomSeparatorToLongWrapperArrayMethod() {
        assertArrayEquals(new Long[] { 22L, 23L }, bound.wrappedLongArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForLongWrapperArrayProperty() {
        assertArrayEquals(new Long[] { 53L, 54L, 55L }, bound.wrappedLongArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForLongWrapperArrayPropertyWithSeparator() {
        assertArrayEquals(new Long[] { 56L, 57L, 58L, 59L }, bound.wrappedLongArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedShortValuedPropertyToPrimitiveShortArrayMethod() {
        assertArrayEquals(new short[] { 51, 52, 53, 54 }, bound.primitiveShortArrayProperty());
    }

    @Test
    public void bindingShortValuedPropertyWithCustomSeparatorToPrimitiveShortArrayMethod() {
        assertArrayEquals(new short[] { 55, 56 }, bound.primitiveShortArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForPrimitiveShortArrayProperty() {
        assertArrayEquals(new short[] { -20, -21, -22, -23, -24 }, bound.primitiveShortArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForPrimitiveShortArrayPropertyWithSeparator() {
        assertArrayEquals(
            new short[] { -25, -26, -27, -28 },
            bound.primitiveShortArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedShortValuedPropertyToShortWrapperArrayMethod() {
        assertArrayEquals(new Short[] { 66, 67, 68, 69 }, bound.wrappedShortArrayProperty());
    }

    @Test
    public void bindingShortValuedPropertyWithCustomSeparatorToShortWrapperArrayMethod() {
        assertArrayEquals(new Short[] { 70, 71 }, bound.wrappedShortArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForShortWrapperArrayProperty() {
        assertArrayEquals(new Short[] { -29, -30, -31 }, bound.wrappedShortArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForShortWrapperArrayPropertyWithSeparator() {
        assertArrayEquals(new Short[] { -32, -33, -34, -35 }, bound.wrappedShortArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedBigIntegerValuedPropertyToBigIntegerArrayMethod() {
        assertArrayEquals(
            new BigInteger[] { BigInteger.valueOf(124), BigInteger.valueOf(125) },
            bound.bigIntegerArrayProperty());
    }

    @Test
    public void bindingBigIntegerValuedPropertyWithCustomSeparatorToBigIntegerArrayMethod() {
        assertArrayEquals(
            new BigInteger[] { BigInteger.valueOf(126), BigInteger.valueOf(127) },
            bound.bigIntegerArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForBigIntegerArrayProperty() {
        assertArrayEquals(
            new BigInteger[] { BigInteger.valueOf(128), BigInteger.valueOf(129), BigInteger.valueOf(130) },
            bound.bigIntegerArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForBigIntegerArrayPropertyWithSeparator() {
        assertArrayEquals(
            new BigInteger[] { BigInteger.valueOf(131), BigInteger.valueOf(132), BigInteger.valueOf(133) },
            bound.bigIntegerArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedBigDecimalValuedPropertyToBigDecimalArrayMethod() {
        assertArrayEquals(
            new BigDecimal[] { new BigDecimal("56.78"), new BigDecimal("90.12") },
            bound.bigDecimalArrayProperty());
    }

    @Test
    public void bindingBigDecimalValuedPropertyWithCustomSeparatorToBigDecimalArrayMethod() {
        assertArrayEquals(
            new BigDecimal[] { new BigDecimal("34.567"), new BigDecimal("89.012") },
            bound.bigDecimalArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForBigDecimalArrayProperty() {
        assertArrayEquals(
            new BigDecimal[] { new BigDecimal("345.67"), new BigDecimal("890.12") },
            bound.bigDecimalArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForBigDecimalArrayPropertyWithSeparator() {
        assertArrayEquals(
            new BigDecimal[] { new BigDecimal("3456.78"), new BigDecimal("9012.34") },
            bound.bigDecimalArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedEnumValuedPropertyToEnumArrayMethod() {
        assertArrayEquals(new Ternary[] { YES, YES, NO, MAYBE, YES }, bound.enumArrayProperty());
    }

    @Test
    public void bindingEnumValuedPropertyWithCustomSeparatorToEnumArrayMethod() {
        assertArrayEquals(new Ternary[] { NO, NO, MAYBE, MAYBE }, bound.enumArrayPropertyWithCustomSeparator());
    }

    @Test
    public void supplyingDefaultForEnumArrayProperty() {
        assertArrayEquals(new Ternary[] { YES, NO, NO, MAYBE, YES }, bound.enumArrayPropertyWithDefault());
    }

    @Test
    public void supplyingDefaultForEnumArrayPropertyWithSeparator() {
        assertArrayEquals(new Ternary[] { NO, MAYBE, YES, MAYBE }, bound.enumArrayPropertyWithDefaultAndSeparator());
    }

    @Test
    public void bindingSeparatedDateValuedPropertyToDateArrayMethodWithParsePatterns() throws Exception {
        assertArrayEquals(
            new Date[] { MMM("Jan"), MMM("Feb"), MMM("Mar") },
            bound.dateArrayPropertyWithParsePatterns());
    }

    @Test
    public void bindingDateValuedPropertyWithCustomSeparatorToDateArrayMethod() throws Exception {
        assertArrayEquals(
            new Date[] { MMM("Apr"), MMM("May"), MMM("Jun") },
            bound.dateArrayPropertyWithCustomSeparatorWithParsePatterns());
    }

    @Test
    public void supplyingDefaultForDateArrayProperty() throws Exception {
        assertArrayEquals(
            new Date[] { MMM("Sep"), MMM("Oct") },
            bound.dateArrayPropertyWithDefaultWithParsePatterns());
    }

    @Test
    public void supplyingDefaultForDateArrayPropertyWithSeparator() throws Exception {
        assertArrayEquals(
            new Date[] { MMM("Nov"), MMM("Dec") },
            bound.dateArrayPropertyWithDefaultAndSeparatorWithParsePatterns());
    }

    @Test
    public void givingZeroLengthArrayForMissingPrimitiveArrayProperty() {
        assertArrayEquals(new int[0], bound.missingPrimitiveArrayProperty());
    }

    @Test
    public void givingZeroLengthArrayForMissingObjectArrayProperty() {
        assertArrayEquals(new String[0], bound.missingObjectArrayProperty());
    }

    @Override
    protected Class<ArrayPropertyHaver> boundType() {
        return ArrayPropertyHaver.class;
    }

    private static Date MMM(String raw) throws ParseException {
        return new SimpleDateFormat("MMM").parse(raw);
    }
}
