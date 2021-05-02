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

import com.pholser.util.properties.it.boundtypes.ListProperties;
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
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BindingListPropertiesToTypedInterfacesTest
  extends TypedStringBindingTestSupport<ListProperties> {

  @Test void separatedStringPropertyToStringListMethod() {
    assertEquals(asList("aaa", "bbb", "ccc"), bound.stringListProperty());
  }

  @Test void separatedStringPropertyWithCustomSeparatorToStringListMethod() {
    assertEquals(
      asList("dd", "eeee", "fffff"),
      bound.stringListPropertyWithCustomSeparator());
  }

  @Test void defaultForStringListProperty() {
    assertEquals(
      asList("g", "hh", "iii"),
      bound.stringListPropertyWithDefault());
  }

  @Test void defaultForStringListPropertyWithCustomSeparator() {
    assertEquals(
      asList("jjj", "kk", "L"),
      bound.stringListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBooleanPropertyToBooleanListMethod() {
    assertEquals(
      asList(true, false, false, true),
      bound.booleanListProperty());
  }

  @Test void booleanPropertyWithCustomSeparatorToBooleanListMethod() {
    assertEquals(
      asList(false, true),
      bound.booleanListPropertyWithCustomSeparator());
  }

  @Test void defaultForBooleanListProperty() {
    assertEquals(
      asList(false, false, true, false, true),
      bound.booleanListPropertyWithDefault());
  }

  @Test void defaultForBooleanListPropertyWithSeparator() {
    assertEquals(
      asList(true, true, true, false),
      bound.booleanListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBytePropertyToByteListMethod() {
    assertEquals(
      asList(
        Byte.valueOf("6"),
        Byte.valueOf("7"),
        Byte.valueOf("8"),
        Byte.valueOf("9")),
      bound.byteListProperty());
  }

  @Test void bytePropertyWithCustomSeparatorToByteListMethod() {
    assertEquals(
      asList(Byte.valueOf("45"), Byte.valueOf("57")),
      bound.byteListPropertyWithCustomSeparator());
  }

  @Test void defaultForByteListProperty() {
    assertEquals(
      asList(Byte.valueOf("33"), Byte.valueOf("34"), Byte.valueOf("35")),
      bound.byteListPropertyWithDefault());
  }

  @Test void defaultForByteListPropertyWithSeparator() {
    assertEquals(
      asList(
        Byte.valueOf("36"),
        Byte.valueOf("37"),
        Byte.valueOf("38"),
        Byte.valueOf("39")),
      bound.byteListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedCharacterPropertyToCharacterListMethod() {
    assertEquals(asList('q', 'r', 's', 't'), bound.characterListProperty());
  }

  @Test void characterPropertyWithCustomSeparatorToCharacterListMethod() {
    assertEquals(
      asList('u', 'v'),
      bound.characterListPropertyWithCustomSeparator());
  }

  @Test void defaultForCharacterListProperty() {
    assertEquals(
      asList('w', 'x', 'y'),
      bound.characterListPropertyWithDefault());
  }

  @Test void defaultForCharacterListPropertyWithSeparator() {
    assertEquals(
      asList('z', '0', '1', '2'),
      bound.characterListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedDoublePropertyToDoubleListMethod() {
    assertEquals(
      asList(-1.0, -2.0, -3.0, -4.0),
      bound.doubleListProperty());
  }

  @Test void doublePropertyWithCustomSeparatorToDoubleListMethod() {
    assertEquals(
      asList(-5.0, -6.0),
      bound.doubleListPropertyWithCustomSeparator());
  }

  @Test void defaultForDoubleListProperty() {
    assertEquals(
      asList(-10.0, -11.0, -12.0),
      bound.doubleListPropertyWithDefault());
  }

  @Test void defaultForDoubleListPropertyWithSeparator() {
    assertEquals(
      asList(-13.0, -14.0, -15.0, -16.0),
      bound.doubleListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedFloatPropertyToFloatListMethod() {
    assertEquals(
      asList(4.8F, 4.9F, 5.0F, 5.1F),
      bound.floatListProperty());
  }

  @Test void floatPropertyWithCustomSeparatorToFloatListMethod() {
    assertEquals(
      asList(5.2F, 5.3F),
      bound.floatListPropertyWithCustomSeparator());
  }

  @Test void defaultForFloatListProperty() {
    assertEquals(
      asList(2.0F, 2.1F, 2.2F),
      bound.floatListPropertyWithDefault());
  }

  @Test void defaultForFloatListPropertyWithSeparator() {
    assertEquals(
      asList(2.3F, 2.4F, 2.5F, 2.6F),
      bound.floatListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedIntegerPropertyToIntegerListMethod() {
    assertEquals(
      asList(-18, -19, -20, -21),
      bound.integerListProperty());
  }

  @Test void integerPropertyWithCustomSeparatorToIntegerListMethod() {
    assertEquals(
      asList(-22, -23),
      bound.integerListPropertyWithCustomSeparator());
  }

  @Test void defaultForIntegerListProperty() {
    assertEquals(
      asList(-10, -11, -12),
      bound.integerListPropertyWithDefault());
  }

  @Test void defaultForIntegerListPropertyWithSeparator() {
    assertEquals(
      asList(-13, -14, -15, -16),
      bound.integerListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedLongPropertyToLongListMethod() {
    assertEquals(
      asList(18L, 19L, 20L, 21L),
      bound.longListProperty());
  }

  @Test void longPropertyWithCustomSeparatorToLongListMethod() {
    assertEquals(
      asList(22L, 23L),
      bound.longListPropertyWithCustomSeparator());
  }

  @Test void defaultForLongListProperty() {
    assertEquals(
      asList(53L, 54L, 55L),
      bound.longListPropertyWithDefault());
  }

  @Test void defaultForLongListPropertyWithSeparator() {
    assertEquals(
      asList(56L, 57L, 58L, 59L),
      bound.longListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedShortPropertyToShortListMethod() {
    assertEquals(
      asList(
        Short.valueOf("66"),
        Short.valueOf("67"),
        Short.valueOf("68"),
        Short.valueOf("69")),
      bound.shortListProperty());
  }

  @Test void shortPropertyWithCustomSeparatorToShortListMethod() {
    assertEquals(
      asList(Short.valueOf("70"), Short.valueOf("71")),
      bound.shortListPropertyWithCustomSeparator());
  }

  @Test void defaultForShortListProperty() {
    assertEquals(
      asList(
        Short.valueOf("-29"),
        Short.valueOf("-30"),
        Short.valueOf("-31")),
      bound.shortListPropertyWithDefault());
  }

  @Test void defaultForShortListPropertyWithSeparator() {
    assertEquals(
      asList(
        Short.valueOf("-32"),
        Short.valueOf("-33"),
        Short.valueOf("-34"),
        Short.valueOf("-35")),
      bound.shortListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBigIntegerPropertyToBigIntegerListMethod() {
    assertEquals(
      asList(BigInteger.valueOf(124), BigInteger.valueOf(125)),
      bound.bigIntegerListProperty());
  }

  @Test void bigIntegerPropertyWithCustomSeparatorToBigIntegerListMethod() {
    assertEquals(
      asList(BigInteger.valueOf(126), BigInteger.valueOf(127)),
      bound.bigIntegerListPropertyWithCustomSeparator());
  }

  @Test void defaultForBigIntegerListProperty() {
    assertEquals(
      asList(
        BigInteger.valueOf(128),
        BigInteger.valueOf(129),
        BigInteger.valueOf(130)),
      bound.bigIntegerListPropertyWithDefault());
  }

  @Test void defaultForBigIntegerListPropertyWithSeparator() {
    assertEquals(
      asList(
        BigInteger.valueOf(131),
        BigInteger.valueOf(132),
        BigInteger.valueOf(133)),
      bound.bigIntegerListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedBigDecimalPropertyToBigDecimalListMethod() {
    assertEquals(
      asList(new BigDecimal("56.78"), new BigDecimal("90.12")),
      bound.bigDecimalListProperty());
  }

  @Test void bigDecimalPropertyWithCustomSeparatorToBigDecimalListMethod() {
    assertEquals(
      asList(new BigDecimal("34.567"), new BigDecimal("89.012")),
      bound.bigDecimalListPropertyWithCustomSeparator());
  }

  @Test void defaultForBigDecimalListProperty() {
    assertEquals(
      asList(new BigDecimal("345.67"), new BigDecimal("890.12")),
      bound.bigDecimalListPropertyWithDefault());
  }

  @Test void defaultForBigDecimalListPropertyWithSeparator() {
    assertEquals(
      asList(new BigDecimal("3456.78"), new BigDecimal("9012.34")),
      bound.bigDecimalListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedEnumPropertyToEnumListMethod() {
    assertEquals(asList(YES, YES, NO, MAYBE, YES), bound.enumListProperty());
  }

  @Test void enumPropertyWithCustomSeparatorToEnumListMethod() {
    assertEquals(
      asList(NO, NO, MAYBE, MAYBE),
      bound.enumListPropertyWithCustomSeparator());
  }

  @Test void defaultForEnumListProperty() {
    assertEquals(
      asList(YES, NO, NO, MAYBE, YES),
      bound.enumListPropertyWithDefault());
  }

  @Test void defaultForEnumListPropertyWithSeparator() {
    assertEquals(
      asList(NO, MAYBE, YES, MAYBE),
      bound.enumListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedUuidPropertyToStringListMethod() {
    assertEquals(
      asList(
        UUID.fromString("f7b469d0-0074-405c-ad55-3a0c76f98144"),
        UUID.fromString("f5d1128e-eb65-4d9d-8fdf-3df474e39e82")),
      bound.uuidListProperty());
  }

  @Test void separatedUuidPropertyWithCustomSeparatorToUuidListMethod() {
    assertEquals(
      asList(
        UUID.fromString("f85b6a7e-5b40-437b-8e5f-a3b453ec13b9"),
        UUID.fromString("738708df-fadd-4a3c-b263-34166764add5"),
        UUID.fromString("cb56f88d-f545-4574-b3f3-1f708afc85ac")),
      bound.uuidListPropertyWithCustomSeparator());
  }

  @Test void defaultForUuidListProperty() {
    assertEquals(
      asList(
        UUID.fromString("4abfa655-eabe-4699-9ea9-d7651244c4f9"),
        UUID.fromString("d432a18b-cf25-4221-a206-2d44728f1165")),
      bound.uuidListPropertyWithDefault());
  }

  @Test void defaultForUuidListPropertyWithCustomSeparator() {
    assertEquals(
      asList(
        UUID.fromString("00d72078-51c6-4686-93d3-d4ee3cc342ce"),
        UUID.fromString("3fab0521-d395-44dd-8464-e88b2c4ff3dc"),
        UUID.fromString("83107da3-4f70-4873-b8df-373b44c3f6db")),
      bound.uuidListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedDatePropertyToDateListMethod() throws Exception {
    assertEquals(asList(M("2"), M("3")), bound.dateListProperty());
  }

  @Test void datePropertyWithCustomSeparatorToDateListMethod()
    throws Exception {

    assertEquals(
      asList(M("4"), M("5"), M("6")),
      bound.dateListPropertyWithCustomSeparator());
  }

  @Test void defaultForDateListProperty() throws Exception {
    assertEquals(
      asList(M("7"), M("8")),
      bound.dateListPropertyWithDefault());
  }

  @Test void defaultForDateListPropertyWithSeparator() throws Exception {
    assertEquals(
      asList(M("10"), M("11")),
      bound.dateListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedStringPropertyToRawListMethod() {
    assertEquals(asList("aaa", "bbb", "ccc"), bound.rawListProperty());
  }

  @Test void separatedStringPropertyWithCustomSeparatorToRawListMethod() {
    assertEquals(
      asList("dd", "eeee", "fffff"),
      bound.rawListPropertyWithCustomSeparator());
  }

  @Test void defaultForRawListProperty() {
    assertEquals(
      asList("YES", "NO", "NO", "MAYBE", "YES"),
      bound.rawListPropertyWithDefault());
  }

  @Test void defaultForRawListPropertyWithCustomSeparator() {
    assertEquals(
      asList("NO", "MAYBE", "YES", "MAYBE"),
      bound.rawListPropertyWithDefaultAndSeparator());
  }

  @Test void separatedStringPropertyToHuhListMethod() {
    assertEquals(asList("xx", "yyy", "zzzz"), bound.huhListProperty());
  }

  @Test void separatedStringPropertyWithCustomSeparatorToHuhListMethod() {
    assertEquals(
      asList("u", "vv", "www"),
      bound.huhListPropertyWithCustomSeparator());
  }

  @Test void defaultForHuhListProperty() {
    assertEquals(
      asList("YES", "NO", "MAYBE"),
      bound.huhListPropertyWithDefault());
  }

  @Test void defaultForHuhListPropertyWithCustomSeparator() {
    assertEquals(
      asList("NO", "MAYBE", "YES"),
      bound.huhListPropertyWithDefaultAndSeparator());
  }

  @Test void givingEmptyListForMissingListProperty() {
    assertEquals(emptyList(), bound.missingListProperty());
  }

  @Override protected Class<ListProperties> boundType() {
    return ListProperties.class;
  }

  private static Date M(String raw) throws ParseException {
    return new SimpleDateFormat("M").parse(raw);
  }
}
