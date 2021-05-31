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

package com.pholser.util.properties.it;

import com.pholser.util.properties.it.boundtypes.ArrayProperties;
import com.pholser.util.properties.it.boundtypes.Ternary;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.pholser.util.properties.it.boundtypes.Ternary.MAYBE;
import static com.pholser.util.properties.it.boundtypes.Ternary.NO;
import static com.pholser.util.properties.it.boundtypes.Ternary.YES;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BindingArrayPropertiesToTypedInterfacesTest
  extends TypedStringBindingTestSupport<ArrayProperties> {

  BindingArrayPropertiesToTypedInterfacesTest() {
    super("/test.properties", "test", "properties");
  }

  @Test void separatedStringPropertyToStringArrayMethod() {
    assertArrayEquals(
      new String[] {"aaa", "bbb", "ccc"},
      bound.stringArrayProperty());
  }

  @Test void separatedStringPropertyWithCustomSeparatorToStringArrayMethod() {
    assertArrayEquals(
      new String[] {"dd", "eeee", "fffff"},
      bound.stringArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForStringArrayProperty() {
    assertArrayEquals(
      new String[] {"g", "hh", "iii"},
      bound.stringArrayPropertyWithDefault());
  }

  @Test void defaultForStringArrayPropertyWithCustomSeparator() {
    assertArrayEquals(
      new String[] {"jjj", "kk", "L"},
      bound.stringArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBooleanPropertyToPrimitiveBooleanArrayMethod() {
    assertArrayEquals(
      new boolean[] {true, false, false, true},
      bound.primitiveBooleanArrayProperty());
  }

  @Test
  void booleanPropertyWithCustomSeparatorToPrimitiveBooleanArrayMethod() {
    assertArrayEquals(
      new boolean[] {false, true},
      bound.primitiveBooleanArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForPrimitiveBooleanArrayProperty() {
    assertArrayEquals(
      new boolean[] {false, false, true, false, true},
      bound.primitiveBooleanArrayPropertyWithDefault());
  }

  @Test void defaultForPrimitiveBooleanArrayPropertyWithSeparator() {
    assertArrayEquals(
      new boolean[] {true, true, true, false},
      bound.primitiveBooleanArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBooleanPropertyToBooleanWrapperArrayMethod() {
    assertArrayEquals(
      new Boolean[] {true, false, false, true},
      bound.wrappedBooleanArrayProperty());
  }

  @Test void booleanPropertyWithCustomSeparatorToBooleanWrapperArrayMethod() {
    assertArrayEquals(
      new Boolean[] {false, true},
      bound.wrappedBooleanArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForBooleanWrapperArrayProperty() {
    assertArrayEquals(
      new Boolean[] {false, false, true, false, true},
      bound.wrappedBooleanArrayPropertyWithDefault());
  }

  @Test void defaultForBooleanWrapperArrayPropertyWithSeparator() {
    assertArrayEquals(
      new Boolean[] {true, true, true, false},
      bound.wrappedBooleanArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBytePropertyToPrimitiveByteArrayMethod() {
    assertArrayEquals(
      new byte[] {0x1, 0x2, 0x3},
      bound.primitiveByteArrayProperty());
  }

  @Test void bytePropertyWithCustomSeparatorToPrimitiveByteArrayMethod() {
    assertArrayEquals(
      new byte[] {0x0, 0x4},
      bound.primitiveByteArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForPrimitiveByteArrayProperty() {
    assertArrayEquals(
      new byte[] {0x18, 0x19, 0x1A, 0x1B, 0x1C},
      bound.primitiveByteArrayPropertyWithDefault());
  }

  @Test void defaultForPrimitiveByteArrayPropertyWithSeparator() {
    assertArrayEquals(
      new byte[] {0x1D, 0x1E, 0x1F, 0x20},
      bound.primitiveByteArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBytePropertyToByteWrapperArrayMethod() {
    assertArrayEquals(
      new Byte[] {0x6, 0x7, 0x8, 0x9},
      bound.wrappedByteArrayProperty());
  }

  @Test void bytePropertyWithCustomSeparatorToByteWrapperArrayMethod() {
    assertArrayEquals(
      new Byte[] {0x2D, 0x39},
      bound.wrappedByteArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForByteWrapperArrayProperty() {
    assertArrayEquals(
      new Byte[] {0x21, 0x22, 0x23},
      bound.wrappedByteArrayPropertyWithDefault());
  }

  @Test void defaultForByteWrapperArrayPropertyWithSeparator() {
    assertArrayEquals(
      new Byte[] {0x24, 0x25, 0x26, 0x27},
      bound.wrappedByteArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedCharacterPropertyToPrimitiveCharacterArrayMethod() {
    assertArrayEquals(
      new char[] {'c', 'd', 'e', 'f'},
      bound.primitiveCharacterArrayProperty());
  }

  @Test
  void characterPropertyWithCustomSeparatorToPrimitiveCharacterArrayMethod() {
    assertArrayEquals(
      new char[] {'g', 'h'},
      bound.primitiveCharacterArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForPrimitiveCharacterArrayProperty() {
    assertArrayEquals(
      new char[] {'h', 'i', 'j', 'k', 'l'},
      bound.primitiveCharacterArrayPropertyWithDefault());
  }

  @Test void defaultForPrimitiveCharacterArrayPropertyWithSeparator() {
    assertArrayEquals(
      new char[] {'m', 'n', 'o', 'p'},
      bound.primitiveCharacterArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedCharacterPropertyToCharacterWrapperArrayMethod() {
    assertArrayEquals(
      new Character[] {'q', 'r', 's', 't'},
      bound.wrappedCharacterArrayProperty());
  }

  @Test
  void characterPropertyWithCustomSeparatorToCharacterWrapperArrayMethod() {
    assertArrayEquals(
      new Character[] {'u', 'v'},
      bound.wrappedCharacterArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForCharacterWrapperArrayProperty() {
    assertArrayEquals(
      new Character[] {'w', 'x', 'y'},
      bound.wrappedCharacterArrayPropertyWithDefault());
  }

  @Test void defaultForCharacterWrapperArrayPropertyWithSeparator() {
    assertArrayEquals(
      new Character[] {'z', '0', '1', '2'},
      bound.wrappedCharacterArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedDoublePropertyToPrimitiveDoubleArrayMethod() {
    assertArrayEquals(
      new double[] {3.0, 4.0, 5.0, 6.0},
      bound.primitiveDoubleArrayProperty(),
      0);
  }

  @Test void doublePropertyWithCustomSeparatorToPrimitiveDoubleArrayMethod() {
    assertArrayEquals(
      new double[] {7.0, 8.0},
      bound.primitiveDoubleArrayPropertyWithCustomSeparator(),
      0);
  }

  @Test void defaultForPrimitiveDoubleArrayProperty() {
    assertArrayEquals(
      new double[] {-1.0, -2.0, -3.0, -4.0, -5.0},
      bound.primitiveDoubleArrayPropertyWithDefault(),
      0);
  }

  @Test void defaultForPrimitiveDoubleArrayPropertyWithSeparator() {
    assertArrayEquals(
      new double[] {-6.0, -7.0, -8.0, -9.0},
      bound.primitiveDoubleArrayPropertyWithDefaultAndSeparator(),
      0);
  }

  @Test void separatedDoublePropertyToDoubleWrapperArrayMethod() {
    assertArrayEquals(
      new Double[] {-1.0, -2.0, -3.0, -4.0},
      bound.wrappedDoubleArrayProperty());
  }

  @Test void doublePropertyWithCustomSeparatorToDoubleWrapperArrayMethod() {
    assertArrayEquals(
      new Double[] {-5.0, -6.0},
      bound.wrappedDoubleArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForDoubleWrapperArrayProperty() {
    assertArrayEquals(
      new Double[] {-10.0, -11.0, -12.0},
      bound.wrappedDoubleArrayPropertyWithDefault());
  }

  @Test void defaultForDoubleWrapperArrayPropertyWithSeparator() {
    assertArrayEquals(
      new Double[] {-13.0, -14.0, -15.0, -16.0},
      bound.wrappedDoubleArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedFloatPropertyToPrimitiveFloatArrayMethod() {
    assertArrayEquals(
      new float[] {3.3F, 3.4F, 3.5F, 3.6F},
      bound.primitiveFloatArrayProperty(),
      0);
  }

  @Test void floatPropertyWithCustomSeparatorToPrimitiveFloatArrayMethod() {
    assertArrayEquals(
      new float[] {3.7F, 3.8F},
      bound.primitiveFloatArrayPropertyWithCustomSeparator(),
      0);
  }

  @Test void defaultForPrimitiveFloatArrayProperty() {
    assertArrayEquals(
      new float[] {1.1F, 1.2F, 1.3F, 1.4F, 1.5F},
      bound.primitiveFloatArrayPropertyWithDefault(),
      0);
  }

  @Test void defaultForPrimitiveFloatArrayPropertyWithSeparator() {
    assertArrayEquals(
      new float[] {1.6F, 1.7F, 1.8F, 1.9F},
      bound.primitiveFloatArrayPropertyWithDefaultAndSeparator(),
      0);
  }

  @Test void separatedFloatPropertyToFloatWrapperArrayMethod() {
    assertArrayEquals(
      new Float[] {4.8F, 4.9F, 5.0F, 5.1F},
      bound.wrappedFloatArrayProperty());
  }

  @Test void floatPropertyWithCustomSeparatorToFloatWrapperArrayMethod() {
    assertArrayEquals(
      new Float[] {5.2F, 5.3F},
      bound.wrappedFloatArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForFloatWrapperArrayProperty() {
    assertArrayEquals(
      new Float[] {2.0F, 2.1F, 2.2F},
      bound.wrappedFloatArrayPropertyWithDefault());
  }

  @Test void defaultForFloatWrapperArrayPropertyWithSeparator() {
    assertArrayEquals(
      new Float[] {2.3F, 2.4F, 2.5F, 2.6F},
      bound.wrappedFloatArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedIntegerPropertyToPrimitiveIntegerArrayMethod() {
    assertArrayEquals(
      new int[] {-3, -4, -5, -6},
      bound.primitiveIntegerArrayProperty());
  }

  @Test
  void integerPropertyWithCustomSeparatorToPrimitiveIntegerArrayMethod() {
    assertArrayEquals(
      new int[] {-7, -8},
      bound.primitiveIntegerArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForPrimitiveIntegerArrayProperty() {
    assertArrayEquals(
      new int[] {-1, -2, -3, -4, -5},
      bound.primitiveIntegerArrayPropertyWithDefault());
  }

  @Test void defaultForPrimitiveIntegerArrayPropertyWithSeparator() {
    assertArrayEquals(
      new int[] {-6, -7, -8, -9},
      bound.primitiveIntegerArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedIntegerPropertyToIntegerWrapperArrayMethod() {
    assertArrayEquals(
      new Integer[] {-18, -19, -20, -21},
      bound.wrappedIntegerArrayProperty());
  }

  @Test void integerPropertyWithCustomSeparatorToIntegerWrapperArrayMethod() {
    assertArrayEquals(
      new Integer[] {-22, -23},
      bound.wrappedIntegerArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForIntegerWrapperArrayProperty() {
    assertArrayEquals(
      new Integer[] {-10, -11, -12},
      bound.wrappedIntegerArrayPropertyWithDefault());
  }

  @Test void defaultForIntegerWrapperArrayPropertyWithSeparator() {
    assertArrayEquals(
      new Integer[] {-13, -14, -15, -16},
      bound.wrappedIntegerArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedLongPropertyToPrimitiveLongArrayMethod() {
    assertArrayEquals(
      new long[] {3, 4, 5, 6},
      bound.primitiveLongArrayProperty());
  }

  @Test void longPropertyWithCustomSeparatorToPrimitiveLongArrayMethod() {
    assertArrayEquals(
      new long[] {7, 8},
      bound.primitiveLongArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForPrimitiveLongArrayProperty() {
    assertArrayEquals(
      new long[] {44, 45, 46, 47, 48},
      bound.primitiveLongArrayPropertyWithDefault());
  }

  @Test void defaultForPrimitiveLongArrayPropertyWithSeparator() {
    assertArrayEquals(
      new long[] {49, 50, 51, 52},
      bound.primitiveLongArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedLongPropertyToLongWrapperArrayMethod() {
    assertArrayEquals(
      new Long[] {18L, 19L, 20L, 21L},
      bound.wrappedLongArrayProperty());
  }

  @Test void longPropertyWithCustomSeparatorToLongWrapperArrayMethod() {
    assertArrayEquals(
      new Long[] {22L, 23L},
      bound.wrappedLongArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForLongWrapperArrayProperty() {
    assertArrayEquals(
      new Long[] {53L, 54L, 55L},
      bound.wrappedLongArrayPropertyWithDefault());
  }

  @Test void defaultForLongWrapperArrayPropertyWithSeparator() {
    assertArrayEquals(
      new Long[] {56L, 57L, 58L, 59L},
      bound.wrappedLongArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedShortPropertyToPrimitiveShortArrayMethod() {
    assertArrayEquals(
      new short[] {51, 52, 53, 54},
      bound.primitiveShortArrayProperty());
  }

  @Test void shortPropertyWithCustomSeparatorToPrimitiveShortArrayMethod() {
    assertArrayEquals(
      new short[] {55, 56},
      bound.primitiveShortArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForPrimitiveShortArrayProperty() {
    assertArrayEquals(
      new short[] {-20, -21, -22, -23, -24},
      bound.primitiveShortArrayPropertyWithDefault());
  }

  @Test void defaultForPrimitiveShortArrayPropertyWithSeparator() {
    assertArrayEquals(
      new short[] {-25, -26, -27, -28},
      bound.primitiveShortArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedShortPropertyToShortWrapperArrayMethod() {
    assertArrayEquals(
      new Short[] {66, 67, 68, 69},
      bound.wrappedShortArrayProperty());
  }

  @Test void shortPropertyWithCustomSeparatorToShortWrapperArrayMethod() {
    assertArrayEquals(
      new Short[] {70, 71},
      bound.wrappedShortArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForShortWrapperArrayProperty() {
    assertArrayEquals(
      new Short[] {-29, -30, -31},
      bound.wrappedShortArrayPropertyWithDefault());
  }

  @Test void defaultForShortWrapperArrayPropertyWithSeparator() {
    assertArrayEquals(
      new Short[] {-32, -33, -34, -35},
      bound.wrappedShortArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBigIntegerPropertyToBigIntegerArrayMethod() {
    assertArrayEquals(
      new BigInteger[] {BigInteger.valueOf(124), BigInteger.valueOf(125)},
      bound.bigIntegerArrayProperty());
  }

  @Test void bigIntegerPropertyWithCustomSeparatorToBigIntegerArrayMethod() {
    assertArrayEquals(
      new BigInteger[] {BigInteger.valueOf(126), BigInteger.valueOf(127)},
      bound.bigIntegerArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForBigIntegerArrayProperty() {
    assertArrayEquals(
      new BigInteger[] {
        BigInteger.valueOf(128),
        BigInteger.valueOf(129),
        BigInteger.valueOf(130)
      },
      bound.bigIntegerArrayPropertyWithDefault());
  }

  @Test void defaultForBigIntegerArrayPropertyWithSeparator() {
    assertArrayEquals(
      new BigInteger[] {
        BigInteger.valueOf(131),
        BigInteger.valueOf(132),
        BigInteger.valueOf(133)
      },
      bound.bigIntegerArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBigDecimalPropertyToBigDecimalArrayMethod() {
    assertArrayEquals(
      new BigDecimal[] {
        new BigDecimal("56.78"),
        new BigDecimal("90.12")
      },
      bound.bigDecimalArrayProperty());
  }

  @Test void bigDecimalPropertyWithCustomSeparatorToBigDecimalArrayMethod() {
    assertArrayEquals(
      new BigDecimal[] {
        new BigDecimal("34.567"),
        new BigDecimal("89.012")
      },
      bound.bigDecimalArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForBigDecimalArrayProperty() {
    assertArrayEquals(
      new BigDecimal[] {
        new BigDecimal("345.67"),
        new BigDecimal("890.12")
      },
      bound.bigDecimalArrayPropertyWithDefault());
  }

  @Test void defaultForBigDecimalArrayPropertyWithSeparator() {
    assertArrayEquals(
      new BigDecimal[] {
        new BigDecimal("3456.78"),
        new BigDecimal("9012.34")
      },
      bound.bigDecimalArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedEnumPropertyToEnumArrayMethod() {
    assertArrayEquals(
      new Ternary[] {YES, YES, NO, MAYBE, YES},
      bound.enumArrayProperty());
  }

  @Test void enumPropertyWithCustomSeparatorToEnumArrayMethod() {
    assertArrayEquals(
      new Ternary[] {NO, NO, MAYBE, MAYBE},
      bound.enumArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForEnumArrayProperty() {
    assertArrayEquals(
      new Ternary[] {YES, NO, NO, MAYBE, YES},
      bound.enumArrayPropertyWithDefault());
  }

  @Test void defaultForEnumArrayPropertyWithSeparator() {
    assertArrayEquals(
      new Ternary[] {NO, MAYBE, YES, MAYBE},
      bound.enumArrayPropertyWithDefaultAndSeparator());
  }

  @Test void separatedDatePropertyToDateArrayMethodWithParsePatterns()
    throws Exception {

    assertArrayEquals(
      new Date[] {MMM("Jan"), MMM("Feb"), MMM("Mar")},
      bound.dateArrayPropertyWithParsePatterns());
  }

  @Test void datePropertyWithCustomSeparatorToDateArrayMethod()
    throws Exception {

    assertArrayEquals(
      new Date[] {MMM("Apr"), MMM("May"), MMM("Jun")},
      bound.dateArrayPropertyWithCustomSeparatorWithParsePatterns());
  }

  @Test void defaultForDateArrayProperty() throws Exception {
    assertArrayEquals(
      new Date[] {MMM("Sep"), MMM("Oct")},
      bound.dateArrayPropertyWithDefaultWithParsePatterns());
  }

  @Test void defaultForDateArrayPropertyWithSeparator()
    throws Exception {

    assertArrayEquals(
      new Date[] {MMM("Nov"), MMM("Dec")},
      bound.dateArrayPropertyWithDefaultAndSeparatorWithParsePatterns());
  }

  @Test void separatedUuidPropertyToUuidArrayMethod() {
    assertArrayEquals(
      new UUID[] {
        UUID.fromString("f7b469d0-0074-405c-ad55-3a0c76f98144"),
        UUID.fromString("f5d1128e-eb65-4d9d-8fdf-3df474e39e82")
      },
      bound.uuidArrayProperty());
  }

  @Test void separatedUuidPropertyWithCustomSeparatorToUuidArrayMethod() {
    assertArrayEquals(
      new UUID[] {
        UUID.fromString("f85b6a7e-5b40-437b-8e5f-a3b453ec13b9"),
        UUID.fromString("738708df-fadd-4a3c-b263-34166764add5"),
        UUID.fromString("cb56f88d-f545-4574-b3f3-1f708afc85ac")
      },
      bound.uuidArrayPropertyWithCustomSeparator());
  }

  @Test void defaultForUuidArrayProperty() {
    assertArrayEquals(
      new UUID[] {
        UUID.fromString("4abfa655-eabe-4699-9ea9-d7651244c4f9"),
        UUID.fromString("d432a18b-cf25-4221-a206-2d44728f1165")
      },
      bound.uuidArrayPropertyWithDefault());
  }

  @Test void defaultForUuidArrayPropertyWithCustomSeparator() {
    assertArrayEquals(
      new UUID[] {
        UUID.fromString("00d72078-51c6-4686-93d3-d4ee3cc342ce"),
        UUID.fromString("3fab0521-d395-44dd-8464-e88b2c4ff3dc"),
        UUID.fromString("83107da3-4f70-4873-b8df-373b44c3f6db")
      },
      bound.uuidArrayPropertyWithDefaultAndSeparator());
  }

  @Test void givingZeroLengthArrayForMissingPrimitiveArrayProperty() {
    assertArrayEquals(new int[0], bound.missingPrimitiveArrayProperty());
  }

  @Test void givingZeroLengthArrayForMissingObjectArrayProperty() {
    assertArrayEquals(new String[0], bound.missingObjectArrayProperty());
  }

  @Override
  protected Class<ArrayProperties> boundType() {
    return ArrayProperties.class;
  }

  private static Date MMM(String raw) throws ParseException {
    return new SimpleDateFormat("MMM").parse(raw);
  }
}
