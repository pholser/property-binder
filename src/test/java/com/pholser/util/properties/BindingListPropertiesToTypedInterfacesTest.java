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

import static java.util.Arrays.*;
import static java.util.Collections.*;

import com.pholser.util.properties.boundtypes.ListPropertyHaver;
import org.junit.Test;

import static com.pholser.util.properties.boundtypes.Ternary.*;
import static org.junit.Assert.*;

public class BindingListPropertiesToTypedInterfacesTest extends TypedStringBindingTestSupport<ListPropertyHaver> {
    @Test public void bindingSeparatedStringValuedPropertyToStringListMethod() {
        assertEquals(asList("aaa", "bbb", "ccc"), bound.stringListProperty());
    }

    @Test public void bindingSeparatedStringValuedPropertyWithCustomSeparatorToStringListMethod() {
        assertEquals(asList("dd", "eeee", "fffff"), bound.stringListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForStringListValuedProperty() {
        assertEquals(asList("g", "hh", "iii"), bound.stringListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForStringListValuedPropertyWithCustomSeparator() {
        assertEquals(asList("jjj", "kk", "L"), bound.stringListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedBooleanValuedPropertyToBooleanListMethod() {
        assertEquals(asList(true, false, false, true), bound.booleanListProperty());
    }

    @Test public void bindingBooleanValuedPropertyWithCustomSeparatorToBooleanListMethod() {
        assertEquals(asList(false, true), bound.booleanListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForBooleanListProperty() {
        assertEquals(asList(false, false, true, false, true), bound.booleanListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForBooleanListPropertyWithSeparator() {
        assertEquals(asList(true, true, true, false), bound.booleanListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedByteValuedPropertyToByteListMethod() {
        assertEquals(
            asList(Byte.valueOf("6"), Byte.valueOf("7"), Byte.valueOf("8"), Byte.valueOf("9")),
            bound.byteListProperty());
    }

    @Test public void bindingByteValuedPropertyWithCustomSeparatorToByteListMethod() {
        assertEquals(asList(Byte.valueOf("45"), Byte.valueOf("57")), bound.byteListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForByteListProperty() {
        assertEquals(
            asList(Byte.valueOf("33"), Byte.valueOf("34"), Byte.valueOf("35")),
            bound.byteListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForByteListPropertyWithSeparator() {
        assertEquals(
            asList(Byte.valueOf("36"), Byte.valueOf("37"), Byte.valueOf("38"), Byte.valueOf("39")),
            bound.byteListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedCharacterValuedPropertyToCharacterListMethod() {
        assertEquals(asList('q', 'r', 's', 't'), bound.characterListProperty());
    }

    @Test public void bindingCharacterValuedPropertyWithCustomSeparatorToCharacterListMethod() {
        assertEquals(asList('u', 'v'), bound.characterListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForCharacterListProperty() {
        assertEquals(asList('w', 'x', 'y'), bound.characterListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForCharacterListPropertyWithSeparator() {
        assertEquals(asList('z', '0', '1', '2'), bound.characterListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedDoubleValuedPropertyToDoubleListMethod() {
        assertEquals(asList(-1.0, -2.0, -3.0, -4.0), bound.doubleListProperty());
    }

    @Test public void bindingDoubleValuedPropertyWithCustomSeparatorToDoubleListMethod() {
        assertEquals(asList(-5.0, -6.0), bound.doubleListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForDoubleListProperty() {
        assertEquals(asList(-10.0, -11.0, -12.0), bound.doubleListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForDoubleListPropertyWithSeparator() {
        assertEquals(asList(-13.0, -14.0, -15.0, -16.0), bound.doubleListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedFloatValuedPropertyToFloatListMethod() {
        assertEquals(asList(4.8F, 4.9F, 5.0F, 5.1F), bound.floatListProperty());
    }

    @Test public void bindingFloatValuedPropertyWithCustomSeparatorToFloatListMethod() {
        assertEquals(asList(5.2F, 5.3F), bound.floatListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForFloatListProperty() {
        assertEquals(asList(2.0F, 2.1F, 2.2F), bound.floatListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForFloatListPropertyWithSeparator() {
        assertEquals(asList(2.3F, 2.4F, 2.5F, 2.6F), bound.floatListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedIntegerValuedPropertyToIntegerListMethod() {
        assertEquals(asList(-18, -19, -20, -21), bound.integerListProperty());
    }

    @Test public void bindingIntegerValuedPropertyWithCustomSeparatorToIntegerListMethod() {
        assertEquals(asList(-22, -23), bound.integerListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForIntegerListProperty() {
        assertEquals(asList(-10, -11, -12), bound.integerListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForIntegerListPropertyWithSeparator() {
        assertEquals(asList(-13, -14, -15, -16), bound.integerListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedLongValuedPropertyToLongListMethod() {
        assertEquals(asList(18L, 19L, 20L, 21L), bound.longListProperty());
    }

    @Test public void bindingLongValuedPropertyWithCustomSeparatorToLongListMethod() {
        assertEquals(asList(22L, 23L), bound.longListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForLongListProperty() {
        assertEquals(asList(53L, 54L, 55L), bound.longListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForLongListPropertyWithSeparator() {
        assertEquals(asList(56L, 57L, 58L, 59L), bound.longListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedShortValuedPropertyToShortListMethod() {
        assertEquals(
            asList(Short.valueOf("66"), Short.valueOf("67"), Short.valueOf("68"), Short.valueOf("69")),
            bound.shortListProperty());
    }

    @Test public void bindingShortValuedPropertyWithCustomSeparatorToShortListMethod() {
        assertEquals(asList(Short.valueOf("70"), Short.valueOf("71")), bound.shortListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForShortListProperty() {
        assertEquals(
            asList(Short.valueOf("-29"), Short.valueOf("-30"), Short.valueOf("-31")),
            bound.shortListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForShortListPropertyWithSeparator() {
        assertEquals(
            asList(Short.valueOf("-32"), Short.valueOf("-33"), Short.valueOf("-34"), Short.valueOf("-35")),
            bound.shortListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedBigIntegerValuedPropertyToBigIntegerListMethod() {
        assertEquals(asList(BigInteger.valueOf(124), BigInteger.valueOf(125)), bound.bigIntegerListProperty());
    }

    @Test public void bindingBigIntegerValuedPropertyWithCustomSeparatorToBigIntegerListMethod() {
        assertEquals(
            asList(BigInteger.valueOf(126), BigInteger.valueOf(127)),
            bound.bigIntegerListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForBigIntegerListProperty() {
        assertEquals(
            asList(BigInteger.valueOf(128), BigInteger.valueOf(129), BigInteger.valueOf(130)),
            bound.bigIntegerListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForBigIntegerListPropertyWithSeparator() {
        assertEquals(
            asList(BigInteger.valueOf(131), BigInteger.valueOf(132), BigInteger.valueOf(133)),
            bound.bigIntegerListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedBigDecimalValuedPropertyToBigDecimalListMethod() {
        assertEquals(asList(new BigDecimal("56.78"), new BigDecimal("90.12")), bound.bigDecimalListProperty());
    }

    @Test public void bindingBigDecimalValuedPropertyWithCustomSeparatorToBigDecimalListMethod() {
        assertEquals(
            asList(new BigDecimal("34.567"), new BigDecimal("89.012")),
            bound.bigDecimalListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForBigDecimalListProperty() {
        assertEquals(
            asList(new BigDecimal("345.67"), new BigDecimal("890.12")),
            bound.bigDecimalListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForBigDecimalListPropertyWithSeparator() {
        assertEquals(
            asList(new BigDecimal("3456.78"), new BigDecimal("9012.34")),
            bound.bigDecimalListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedEnumValuedPropertyToEnumListMethod() {
        assertEquals(asList(YES, YES, NO, MAYBE, YES), bound.enumListProperty());
    }

    @Test public void bindingEnumValuedPropertyWithCustomSeparatorToEnumListMethod() {
        assertEquals(asList(NO, NO, MAYBE, MAYBE), bound.enumListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForEnumListProperty() {
        assertEquals(asList(YES, NO, NO, MAYBE, YES), bound.enumListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForEnumListPropertyWithSeparator() {
        assertEquals(asList(NO, MAYBE, YES, MAYBE), bound.enumListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedDateValuedPropertyToDateListMethod() throws Exception {
        assertEquals(asList(M("2"), M("3")), bound.dateListProperty());
    }

    @Test public void bindingDateValuedPropertyWithCustomSeparatorToDateListMethod() throws Exception {
        assertEquals(asList(M("4"), M("5"), M("6")), bound.dateListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForDateListProperty() throws Exception {
        assertEquals(asList(M("7"), M("8")), bound.dateListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForDateListPropertyWithSeparator() throws Exception {
        assertEquals(asList(M("10"), M("11")), bound.dateListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedStringValuedPropertyToRawListMethod() {
        assertEquals(asList("aaa", "bbb", "ccc"), bound.rawListProperty());
    }

    @Test public void bindingSeparatedStringValuedPropertyWithCustomSeparatorToRawListMethod() {
        assertEquals(asList("dd", "eeee", "fffff"), bound.rawListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForRawListValuedProperty() {
        assertEquals(asList("YES", "NO", "NO", "MAYBE", "YES"), bound.rawListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForRawListValuedPropertyWithCustomSeparator() {
        assertEquals(asList("NO", "MAYBE", "YES", "MAYBE"), bound.rawListPropertyWithDefaultAndSeparator());
    }

    @Test public void bindingSeparatedStringValuedPropertyToHuhListMethod() {
        assertEquals(asList("xx", "yyy", "zzzz"), bound.huhListProperty());
    }

    @Test public void bindingSeparatedStringValuedPropertyWithCustomSeparatorToHuhListMethod() {
        assertEquals(asList("u", "vv", "www"), bound.huhListPropertyWithCustomSeparator());
    }

    @Test public void supplyingDefaultForHuhListValuedProperty() {
        assertEquals(asList("YES", "NO", "MAYBE"), bound.huhListPropertyWithDefault());
    }

    @Test public void supplyingDefaultForHuhListValuedPropertyWithCustomSeparator() {
        assertEquals(asList("NO", "MAYBE", "YES"), bound.huhListPropertyWithDefaultAndSeparator());
    }

    @Test public void givingEmptyListForMissingListProperty() {
        assertEquals(emptyList(), bound.missingListProperty());
    }

    @Override protected Class<ListPropertyHaver> boundType() {
        return ListPropertyHaver.class;
    }

    private static Date M(String raw) throws ParseException {
        return new SimpleDateFormat("M").parse(raw);
    }
}
