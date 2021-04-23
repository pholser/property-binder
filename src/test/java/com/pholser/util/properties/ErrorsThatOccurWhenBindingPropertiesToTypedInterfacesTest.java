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

  private PropertyBinder<ScalarPropertyHaver> scalar;

  @BeforeEach void setUp() {
    scalar = PropertyBinder.forType(ScalarPropertyHaver.class);
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
          CharacterPropertyHaverWithTooLongDefault.class));
  }

  @Test void upperBoundedListType() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () -> PropertyBinder.forType(UpperBoundedListPropertyHaver.class));
  }

  @Test void lowerBoundedListType() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () -> PropertyBinder.forType(LowerBoundedListPropertyHaver.class));
  }

  @Test void listOfArrayType() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () -> PropertyBinder.forType(ListOfArrayPropertyHaver.class));
  }

  @Test void arrayOfUnconvertibleTypeWithDefault() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ArrayOfUnconvertibleTypeWithDefaultPropertyHaver.class));
  }

  @Test void arrayOfUnconvertibleTypeWithSeparator() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ArrayOfUnconvertibleTypeWithSeparatorPropertyHaver.class));
  }

  @Test void listOfUnconvertibleTypeWithDefault() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ListOfUnconvertibleTypeWithDefaultPropertyHaver.class));
  }

  @Test void listOfUnconvertibleTypeWithSeparator() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ListOfUnconvertibleTypeWithSeparatorPropertyHaver.class));
  }

  @Test void listOfUnconvertibleTypeWithValueOfSeparator() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          ListOfUnconvertibleTypeWithValueOfSeparatorPropertyHaver.class));
  }

  @Test void typeWithNonPublicValueOf() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(TypeWithNonPublicValueOfPropertyHaver.class));
  }

  @Test void typeWithNonStaticValueOf() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(TypeWithNonStaticValueOfPropertyHaver.class));
  }

  @Test void typeWithValueOfWithBadReturnType() {
    assertThrows(
      UnsupportedValueTypeException.class,
      () ->
        PropertyBinder.forType(
          TypeWithValueOfWithBadReturnTypePropertyHaver.class));
  }

  @Test void missingPrimitiveProperty() throws Exception {
    PropertyBinder<MissingPrimitivePropertyHaver> binder =
      PropertyBinder.forType(MissingPrimitivePropertyHaver.class);
    MissingPrimitivePropertyHaver bound = binder.bind(propertiesFile);

    assertThrows(NullPointerException.class, bound::missingCharacterProperty);
  }

  @Test void badDefaultValue() {
    assertThrows(
      MalformedDefaultValueException.class,
      () -> PropertyBinder.forType(BadDefaultValuePropertyHaver.class));
  }

  @Test void badValueSeparator() {
    assertThrows(
      MalformedSeparatorException.class,
      () -> PropertyBinder.forType(BadValueSeparatorPropertyHaver.class));
  }

  @Test void applyingSeparatorToNonAggregateType() {
    assertThrows(
      AppliedSeparatorToNonAggregateTypeException.class,
      () ->
        PropertyBinder.forType(
          SeparatorOnNonAggregateTypePropertyHaver.class));
  }

  @Test void separatorWithBothPatternAndValueOf() {
    assertThrows(
      MultipleSeparatorSpecificationException.class,
      () ->
        PropertyBinder.forType(
          SeparatedPropertyHaverWithBothPatternAndValueOf.class));
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

    DatePropertyWithNonLenientValueHaver bound =
      PropertyBinder.forType(DatePropertyWithNonLenientValueHaver.class)
        .bind(propertiesFile);

    assertThrows(
      IllegalArgumentException.class,
      bound::datePropertyWithNonLenientValue);
  }
}
