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

import com.pholser.util.properties.boundtypes.ArrayOfUnconvertibleTypeWithDefault;
import com.pholser.util.properties.boundtypes.ArrayOfUnconvertibleTypeWithSeparator;
import com.pholser.util.properties.boundtypes.BadDefaultValue;
import com.pholser.util.properties.boundtypes.BadValueSeparator;
import com.pholser.util.properties.boundtypes.CharacterPropertyWithTooLongDefault;
import com.pholser.util.properties.boundtypes.DateWithNonLenientValue;
import com.pholser.util.properties.boundtypes.DefaultValueWithBothValueAndValueOf;
import com.pholser.util.properties.boundtypes.DefaultValueWithNeitherValueNorValueOf;
import com.pholser.util.properties.boundtypes.InterfaceWithSuperinterfaces;
import com.pholser.util.properties.boundtypes.ListOfArrayProperties;
import com.pholser.util.properties.boundtypes.ListOfUnconvertibleTypeWithDefault;
import com.pholser.util.properties.boundtypes.ListOfUnconvertibleTypeWithSeparator;
import com.pholser.util.properties.boundtypes.ListOfUnconvertibleTypeWithValueOfSeparator;
import com.pholser.util.properties.boundtypes.LowerBoundedListProperty;
import com.pholser.util.properties.boundtypes.MissingPrimitiveProperty;
import com.pholser.util.properties.boundtypes.ScalarProperties;
import com.pholser.util.properties.boundtypes.SeparatedWithBothPatternAndValueOf;
import com.pholser.util.properties.boundtypes.SeparatorOnNonAggregateType;
import com.pholser.util.properties.boundtypes.TypeWithNonPublicValueOfProperties;
import com.pholser.util.properties.boundtypes.TypeWithNonStaticValueOfProperties;
import com.pholser.util.properties.boundtypes.TypeWithValueOfWithBadReturnTypeProperties;
import com.pholser.util.properties.boundtypes.UnsupportedAggregateTypeProperties;
import com.pholser.util.properties.boundtypes.UpperBoundedList;
import com.pholser.util.properties.internal.exceptions.AppliedSeparatorToNonAggregateTypeException;
import com.pholser.util.properties.internal.exceptions.BoundTypeNotAnInterfaceException;
import com.pholser.util.properties.internal.exceptions.InterfaceHasSuperinterfacesException;
import com.pholser.util.properties.internal.exceptions.MalformedDefaultValueException;
import com.pholser.util.properties.internal.exceptions.MalformedSeparatorException;
import com.pholser.util.properties.internal.exceptions.MultipleDefaultValueSpecificationException;
import com.pholser.util.properties.internal.exceptions.MultipleSeparatorSpecificationException;
import com.pholser.util.properties.internal.exceptions.NoDefaultValueSpecificationException;
import com.pholser.util.properties.internal.exceptions.UnsupportedValueTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ErrorsThatOccurWhenBindingPropertiesToTypedInterfacesTest
  extends StringBindingTestSupport {

  private PropertyBinder<ScalarProperties> scalar;

  @BeforeEach void setUp() {
    scalar = PropertyBinder.forType(ScalarProperties.class);
  }

  @Test void nonInterfaceClass() {
    assertThrows(
      BoundTypeNotAnInterfaceException.class,
      () -> PropertyBinder.forType(Object.class));
  }

  @Test void annotationClass() {
    assertThrows(
      InterfaceHasSuperinterfacesException.class,
      () -> PropertyBinder.forType(SuppressWarnings.class));
  }

  @Test void nullFile() {
    assertThrows(
      NullPointerException.class,
      () -> scalar.bind((File) null));
  }

  @Test void missingFile() {
    assertThrows(
      FileNotFoundException.class,
      () -> scalar.bind(new File("!(@#*&!@(*#&")));
  }

  @Test void nullInputStream() {
    assertThrows(
      NullPointerException.class,
      () -> scalar.bind((InputStream) null));
  }

  @Test void nullMap() {
    assertThrows(
      NullPointerException.class,
      () -> scalar.bind((Map<String, ?>) null));
  }

  @Test void nullResourceBundle() {
    assertThrows(
      NullPointerException.class,
      () -> scalar.bind((ResourceBundle) null));
  }

  @Test void nullPropertySource() {
    assertThrows(
      NullPointerException.class,
      () -> scalar.bind((PropertySource) null));
  }

  @Test void nullClass() {
    assertThrows(
      NullPointerException.class,
      () -> PropertyBinder.forType(null));
  }

  @Test void nonSingleCharacterValueForCharacterProperty() {
    assertThrows(
      MalformedDefaultValueException.class,
      () ->
        PropertyBinder.forType(
          CharacterPropertyWithTooLongDefault.class));
  }

  @Test void upperBoundedListType() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () -> PropertyBinder.forType(UpperBoundedList.class));
  }

  @Test void lowerBoundedListType() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () -> PropertyBinder.forType(LowerBoundedListProperty.class));
  }

  @Test void listOfArrayType() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () -> PropertyBinder.forType(ListOfArrayProperties.class));
  }

  @Test void arrayOfUnconvertibleTypeWithDefault() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ArrayOfUnconvertibleTypeWithDefault.class));
  }

  @Test void arrayOfUnconvertibleTypeWithSeparator() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ArrayOfUnconvertibleTypeWithSeparator.class));
  }

  @Test void listOfUnconvertibleTypeWithDefault() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ListOfUnconvertibleTypeWithDefault.class));
  }

  @Test void listOfUnconvertibleTypeWithSeparator() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ListOfUnconvertibleTypeWithSeparator.class));
  }

  @Test void listOfUnconvertibleTypeWithValueOfSeparator() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ListOfUnconvertibleTypeWithValueOfSeparator.class));
  }

  @Test void typeWithNonPublicValueOf() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          TypeWithNonPublicValueOfProperties.class));
  }

  @Test void typeWithNonStaticValueOf() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(TypeWithNonStaticValueOfProperties.class));
  }

  @Test void typeWithValueOfWithBadReturnType() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          TypeWithValueOfWithBadReturnTypeProperties.class));
  }

  @Test void missingPrimitiveProperty() throws Exception {
    PropertyBinder<MissingPrimitiveProperty> binder =
      PropertyBinder.forType(MissingPrimitiveProperty.class);
    MissingPrimitiveProperty bound = binder.bind(propertiesFile);

    assertThrows(NullPointerException.class, bound::missingCharacterProperty);
  }

  @Test void badDefaultValue() {
    assertThrows(
      MalformedDefaultValueException.class,
      () -> PropertyBinder.forType(BadDefaultValue.class));
  }

  @Test void badValueSeparator() {
    assertThrows(
      MalformedSeparatorException.class,
      () -> PropertyBinder.forType(BadValueSeparator.class));
  }

  @Test void applyingSeparatorToNonAggregateType() {
    assertThrows(
      AppliedSeparatorToNonAggregateTypeException.class,
      () ->
        PropertyBinder.forType(
          SeparatorOnNonAggregateType.class));
  }

  @Test void separatorWithBothPatternAndValueOf() {
    assertThrows(
      MultipleSeparatorSpecificationException.class,
      () ->
        PropertyBinder.forType(
          SeparatedWithBothPatternAndValueOf.class));
  }

  @Test void typeWithSuperinterfaces() {
    assertThrows(
      InterfaceHasSuperinterfacesException.class,
      () -> PropertyBinder.forType(InterfaceWithSuperinterfaces.class));
  }

  @Test void defaultValueSpecWithBothValueAndValueOf() {
    assertThrows(
      MultipleDefaultValueSpecificationException.class,
      () -> PropertyBinder.forType(DefaultValueWithBothValueAndValueOf.class));
  }

  @Test void defaultValueSpecWithNeitherValueNorValueOf() {
    assertThrows(
      NoDefaultValueSpecificationException.class,
      () ->
        PropertyBinder.forType(DefaultValueWithNeitherValueNorValueOf.class));
  }

  @Test void datePropertiesThatWouldNotPassNonLenientDateFormats()
    throws Exception {

    DateWithNonLenientValue bound =
      PropertyBinder.forType(DateWithNonLenientValue.class)
        .bind(propertiesFile);

    assertThrows(
      IllegalArgumentException.class,
      bound::datePropertyWithNonLenientValue);
  }

  @Test void unsupportedAggregateType() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(UnsupportedAggregateTypeProperties.class));
  }
}
