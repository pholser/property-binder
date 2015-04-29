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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.ResourceBundle;

import com.pholser.util.properties.boundtypes.ArrayOfUnconvertibleTypeWithDefaultPropertyHaver;
import com.pholser.util.properties.boundtypes.ArrayOfUnconvertibleTypeWithSeparatorPropertyHaver;
import com.pholser.util.properties.boundtypes.BadDefaultValuePropertyHaver;
import com.pholser.util.properties.boundtypes.BadValueSeparatorPropertyHaver;
import com.pholser.util.properties.boundtypes.CharacterPropertyHaverWithTooLongDefault;
import com.pholser.util.properties.boundtypes.DatePropertyWithNonLenientValueHaver;
import com.pholser.util.properties.boundtypes.DefaultValueWithBothValueAndValueOf;
import com.pholser.util.properties.boundtypes.DefaultValueWithNeitherValueNorValueOf;
import com.pholser.util.properties.boundtypes.InterfaceWithSuperinterfaces;
import com.pholser.util.properties.boundtypes.ListOfArrayPropertyHaver;
import com.pholser.util.properties.boundtypes.ListOfUnconvertibleTypeWithDefaultPropertyHaver;
import com.pholser.util.properties.boundtypes.ListOfUnconvertibleTypeWithSeparatorPropertyHaver;
import com.pholser.util.properties.boundtypes.ListOfUnconvertibleTypeWithValueOfSeparatorPropertyHaver;
import com.pholser.util.properties.boundtypes.LowerBoundedListPropertyHaver;
import com.pholser.util.properties.boundtypes.MissingPrimitivePropertyHaver;
import com.pholser.util.properties.boundtypes.ParsedAsOnMethodOfImproperType;
import com.pholser.util.properties.boundtypes.ScalarPropertyHaver;
import com.pholser.util.properties.boundtypes.SeparatedPropertyHaverWithBothPatternAndValueOf;
import com.pholser.util.properties.boundtypes.SeparatorOnNonAggregateTypePropertyHaver;
import com.pholser.util.properties.boundtypes.TypeWithNonPublicValueOfPropertyHaver;
import com.pholser.util.properties.boundtypes.TypeWithNonStaticValueOfPropertyHaver;
import com.pholser.util.properties.boundtypes.TypeWithValueOfWithBadReturnTypePropertyHaver;
import com.pholser.util.properties.boundtypes.UpperBoundedListPropertyHaver;
import com.pholser.util.properties.internal.exceptions.AppliedSeparatorToNonAggregateTypeException;
import com.pholser.util.properties.internal.exceptions.BoundTypeNotAnInterfaceException;
import com.pholser.util.properties.internal.exceptions.InterfaceHasSuperinterfacesException;
import com.pholser.util.properties.internal.exceptions.MalformedDefaultValueException;
import com.pholser.util.properties.internal.exceptions.MalformedSeparatorException;
import com.pholser.util.properties.internal.exceptions.MultipleDefaultValueSpecificationException;
import com.pholser.util.properties.internal.exceptions.MultipleSeparatorSpecificationException;
import com.pholser.util.properties.internal.exceptions.NoDefaultValueSpecificationException;
import com.pholser.util.properties.internal.exceptions.UnsupportedParsedAsTypeException;
import com.pholser.util.properties.internal.exceptions.UnsupportedValueTypeException;
import com.pholser.util.properties.internal.exceptions.ValueConversionException;
import org.junit.Before;
import org.junit.Test;

public class ErrorsThatOccurWhenBindingPropertiesToTypedInterfacesTest extends StringBindingTestSupport {
    private PropertyBinder<ScalarPropertyHaver> scalar;

    @Before public void setUp() {
        scalar = PropertyBinder.forType(ScalarPropertyHaver.class);
    }

    @Test(expected = BoundTypeNotAnInterfaceException.class)
    public void nonInterfaceClass() {
        PropertyBinder.forType(Object.class);
    }

    @Test public void annotationClass() {
        thrown.expect(InterfaceHasSuperinterfacesException.class);

        PropertyBinder.forType(SuppressWarnings.class);
    }

    @Test public void nullFile() throws Exception {
        thrown.expect(NullPointerException.class);

        scalar.bind((File) null);
    }

    @Test public void missingFile() throws Exception {
        thrown.expect(FileNotFoundException.class);

        scalar.bind(new File("!(@#*&!@(*#&"));
    }

    @Test public void nullInputStream() throws Exception {
        thrown.expect(NullPointerException.class);

        scalar.bind((InputStream) null);
    }

    @Test public void nullMap() {
        thrown.expect(NullPointerException.class);

        scalar.bind((Map<String, ?>) null);
    }

    @Test public void nullResourceBundle() {
        thrown.expect(NullPointerException.class);

        scalar.bind((ResourceBundle) null);
    }

    @Test public void nullPropertySource() {
        thrown.expect(NullPointerException.class);

        scalar.bind((PropertySource) null);
    }

    @Test public void nullClass() {
        thrown.expect(NullPointerException.class);

        PropertyBinder.forType(null);
    }

    @Test public void nonSingleCharacterValueForCharacterProperty() {
        thrown.expect(MalformedDefaultValueException.class);

        PropertyBinder.forType(CharacterPropertyHaverWithTooLongDefault.class);
    }

    @Test public void upperBoundedListType() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(UpperBoundedListPropertyHaver.class);
    }

    @Test public void lowerBoundedListType() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(LowerBoundedListPropertyHaver.class);
    }

    @Test public void listOfArrayType() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(ListOfArrayPropertyHaver.class);
    }

    @Test public void arrayOfUnconvertibleTypeWithDefault() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(ArrayOfUnconvertibleTypeWithDefaultPropertyHaver.class);
    }

    @Test public void arrayOfUnconvertibleTypeWithSeparator() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(ArrayOfUnconvertibleTypeWithSeparatorPropertyHaver.class);
    }

    @Test public void listOfUnconvertibleTypeWithDefault() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(ListOfUnconvertibleTypeWithDefaultPropertyHaver.class);
    }

    @Test public void listOfUnconvertibleTypeWithSeparator() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(ListOfUnconvertibleTypeWithSeparatorPropertyHaver.class);
    }

    @Test public void listOfUnconvertibleTypeWithValueOfSeparator() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(ListOfUnconvertibleTypeWithValueOfSeparatorPropertyHaver.class);
    }

    @Test public void typeWithNonPublicValueOf() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(TypeWithNonPublicValueOfPropertyHaver.class);
    }

    @Test public void typeWithNonStaticValueOf() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(TypeWithNonStaticValueOfPropertyHaver.class);
    }

    @Test public void typeWithValueOfWithBadReturnType() {
        thrown.expect(UnsupportedValueTypeException.class);

        PropertyBinder.forType(TypeWithValueOfWithBadReturnTypePropertyHaver.class);
    }

    @Test public void missingPrimitiveProperty() throws Exception {
        PropertyBinder<MissingPrimitivePropertyHaver> binder =
            PropertyBinder.forType(MissingPrimitivePropertyHaver.class);
        MissingPrimitivePropertyHaver bound = binder.bind(propertiesFile);

        thrown.expect(NullPointerException.class);

        bound.missingCharacterProperty();
    }

    @Test public void badDefaultValue() {
        thrown.expect(MalformedDefaultValueException.class);

        PropertyBinder.forType(BadDefaultValuePropertyHaver.class);
    }

    @Test public void badValueSeparator() {
        thrown.expect(MalformedSeparatorException.class);

        PropertyBinder.forType(BadValueSeparatorPropertyHaver.class);
    }

    @Test public void applyingSeparatorToNonAggregateType() {
        thrown.expect(AppliedSeparatorToNonAggregateTypeException.class);

        PropertyBinder.forType(SeparatorOnNonAggregateTypePropertyHaver.class);
    }

    @Test public void separatorWithBothPatternAndValueOf() {
        thrown.expect(MultipleSeparatorSpecificationException.class);

        PropertyBinder.forType(SeparatedPropertyHaverWithBothPatternAndValueOf.class);
    }

    @Test public void typeWithSuperinterfaces() {
        thrown.expect(InterfaceHasSuperinterfacesException.class);

        PropertyBinder.forType(InterfaceWithSuperinterfaces.class);
    }

    @Test public void defaultValueSpecWithBothValueAndValueOf() {
        thrown.expect(MultipleDefaultValueSpecificationException.class);

        PropertyBinder.forType(DefaultValueWithBothValueAndValueOf.class);
    }

    @Test public void defaultValueSpecWithNeitherValueNorValueOf() {
        thrown.expect(NoDefaultValueSpecificationException.class);

        PropertyBinder.forType(DefaultValueWithNeitherValueNorValueOf.class);
    }

    @Test public void datePropertiesThatWouldNotPassNonLenientDateFormats() throws Exception {
        DatePropertyWithNonLenientValueHaver bound =
            PropertyBinder.forType(DatePropertyWithNonLenientValueHaver.class).bind(propertiesFile);

        thrown.expect(ValueConversionException.class);

        bound.datePropertyWithNonLenientValue();
    }

    @Test public void parsedAsIfAppliedToTypeOtherThanDate() {
        thrown.expect(UnsupportedParsedAsTypeException.class);

        PropertyBinder.forType(ParsedAsOnMethodOfImproperType.class);
    }
}
