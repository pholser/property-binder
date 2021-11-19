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

import com.pholser.util.properties.PropertyBinder;
import com.pholser.util.properties.PropertySource;
import com.pholser.util.properties.it.boundtypes.ArrayOfUnconvertibleTypeWithDefault;
import com.pholser.util.properties.it.boundtypes.ArrayOfUnconvertibleTypeWithSeparator;
import com.pholser.util.properties.it.boundtypes.BadDefaultValue;
import com.pholser.util.properties.it.boundtypes.BadValueSeparator;
import com.pholser.util.properties.it.boundtypes.CharacterPropertyWithTooLongDefault;
import com.pholser.util.properties.it.boundtypes.DateWithNonLenientValue;
import com.pholser.util.properties.it.boundtypes.DefaultValueWithBothValueAndValueOf;
import com.pholser.util.properties.it.boundtypes.DefaultValueWithNeitherValueNorValueOf;
import com.pholser.util.properties.it.boundtypes.InterfaceWithSuperinterfaces;
import com.pholser.util.properties.it.boundtypes.ListOfArrayProperties;
import com.pholser.util.properties.it.boundtypes.ListOfUnconvertibleTypeWithDefault;
import com.pholser.util.properties.it.boundtypes.ListOfUnconvertibleTypeWithSeparator;
import com.pholser.util.properties.it.boundtypes.ListOfUnconvertibleTypeWithValueOfSeparator;
import com.pholser.util.properties.it.boundtypes.LowerBoundedListProperty;
import com.pholser.util.properties.it.boundtypes.MissingPrimitiveProperty;
import com.pholser.util.properties.it.boundtypes.ScalarProperties;
import com.pholser.util.properties.it.boundtypes.SeparatedWithBothPatternAndValueOf;
import com.pholser.util.properties.it.boundtypes.SeparatorOnNonAggregateType;
import com.pholser.util.properties.it.boundtypes.TypeWithNonPublicValueOfProperties;
import com.pholser.util.properties.it.boundtypes.TypeWithNonStaticValueOfProperties;
import com.pholser.util.properties.it.boundtypes.TypeWithValueOfWithBadReturnTypeProperties;
import com.pholser.util.properties.it.boundtypes.UnconvertibleScalar;
import com.pholser.util.properties.it.boundtypes.UnsupportedAggregateTypeProperties;
import com.pholser.util.properties.it.boundtypes.UpperBoundedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.Map;
import java.util.ResourceBundle;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ErrorsThatOccurWhenBindingPropertiesToTypedInterfacesTest
  extends StringBindingTestSupport {

  private PropertyBinder<ScalarProperties> scalar;

  ErrorsThatOccurWhenBindingPropertiesToTypedInterfacesTest() {
    super("/test.properties", "test", "properties");
  }

  @BeforeEach void setUp() {
    scalar = PropertyBinder.forType(ScalarProperties.class);
  }

  @Test void nonInterfaceClass() {
    assertThrows(
      IllegalArgumentException.class,
      () -> PropertyBinder.forType(Object.class));
  }

  @Test void annotationClass() {
    assertThrows(
      IllegalArgumentException.class,
      () -> PropertyBinder.forType(SuppressWarnings.class));
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
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(
          CharacterPropertyWithTooLongDefault.class));
  }

  @Test void unconvertibleScalar() throws Exception {
    UnconvertibleScalar bound =
      PropertyBinder.forType(UnconvertibleScalar.class)
        .bind(new FileReader(propertiesFile, UTF_8));

    assertThrows(
      IllegalArgumentException.class,
      bound::unconvertible);
  }

  @Test void upperBoundedListType() {
    assertThrows(
      IllegalArgumentException.class,
      () -> PropertyBinder.forType(UpperBoundedList.class));
  }

  @Test void lowerBoundedListType() {
    assertThrows(
      IllegalArgumentException.class,
      () -> PropertyBinder.forType(LowerBoundedListProperty.class));
  }

  @Test void listOfArrayType() {
    assertThrows(
      IllegalArgumentException.class,
      () -> PropertyBinder.forType(ListOfArrayProperties.class));
  }

  @Test void arrayOfUnconvertibleTypeWithDefault() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(ArrayOfUnconvertibleTypeWithDefault.class));
  }

  @Test void arrayOfUnconvertibleTypeWithSeparator() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(ArrayOfUnconvertibleTypeWithSeparator.class));
  }

  @Test void listOfUnconvertibleTypeWithDefault() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(ListOfUnconvertibleTypeWithDefault.class));
  }

  @Test void listOfUnconvertibleTypeWithSeparator() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(ListOfUnconvertibleTypeWithSeparator.class));
  }

  @Test void listOfUnconvertibleTypeWithValueOfSeparator() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(
          ListOfUnconvertibleTypeWithValueOfSeparator.class));
  }

  @Test void typeWithNonPublicValueOf() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(TypeWithNonPublicValueOfProperties.class));
  }

  @Test void typeWithNonStaticValueOf() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(TypeWithNonStaticValueOfProperties.class));
  }

  @Test void typeWithValueOfWithBadReturnType() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(
          TypeWithValueOfWithBadReturnTypeProperties.class));
  }

  @Test void missingPrimitiveProperty() throws Exception {
    PropertyBinder<MissingPrimitiveProperty> binder =
      PropertyBinder.forType(MissingPrimitiveProperty.class);
    MissingPrimitiveProperty bound =
      binder.bind(new FileReader(propertiesFile, UTF_8));

    assertThrows(NullPointerException.class, bound::missingCharacterProperty);
  }

  @Test void badDefaultValue() {
    assertThrows(
      IllegalArgumentException.class,
      () -> PropertyBinder.forType(BadDefaultValue.class));
  }

  @Test void badValueSeparator() {
    assertThrows(
      IllegalArgumentException.class,
      () -> PropertyBinder.forType(BadValueSeparator.class));
  }

  @Test void applyingSeparatorToNonAggregateType() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(SeparatorOnNonAggregateType.class));
  }

  @Test void separatorWithBothPatternAndValueOf() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(SeparatedWithBothPatternAndValueOf.class));
  }

  @Test void typeWithSuperinterfaces() {
    assertThrows(
      IllegalArgumentException.class,
      () -> PropertyBinder.forType(InterfaceWithSuperinterfaces.class));
  }

  @Test void defaultValueSpecWithBothValueAndValueOf() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(DefaultValueWithBothValueAndValueOf.class));
  }

  @Test void defaultValueSpecWithNeitherValueNorValueOf() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(DefaultValueWithNeitherValueNorValueOf.class));
  }

  @Test void datePropertiesThatWouldNotPassNonLenientDateFormats()
    throws Exception {

    DateWithNonLenientValue bound =
      PropertyBinder.forType(DateWithNonLenientValue.class)
        .bind(new FileReader(propertiesFile, UTF_8));

    assertThrows(
      IllegalArgumentException.class,
      bound::datePropertyWithNonLenientValue);
  }

  @Test void unsupportedAggregateType() {
    assertThrows(
      IllegalArgumentException.class,
      () ->
        PropertyBinder.forType(UnsupportedAggregateTypeProperties.class));
  }
}
