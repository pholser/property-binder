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

import com.pholser.util.properties.it.boundtypes.ScalarPropertiesWithArgs;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatConversionException;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BindingScalarPropertiesWithArgsToTypedInterfacesTest
  extends TypedStringBindingTestSupport<ScalarPropertiesWithArgs> {

  BindingScalarPropertiesWithArgsToTypedInterfacesTest() {
    super("/test.properties", "test", "properties");
  }

  @Test void passingArgumentsToPropertyMethodAndFormattingOnResult() {
    assertEquals(
      "foo to the bar",
      bound.stringPropertyWithArguments("foo", "bar"));
  }

  @Test
  void passingArgumentsOfVariousTypesToPropertyMethodAndFormattingOnResult()
    throws Exception {

    assertEquals(
      "10 seconds to 12:00:00 AM",
      bound.stringPropertyWithTypedArguments(10, mmddyyyy("12/22/2003")));
  }

  @Test void illTypedArgumentsToPropertyMethodsWithArgs() {
    assertThrows(
      IllegalFormatConversionException.class,
      () ->
        bound.stringPropertyWithIllTypedArguments(
          10,
          mmddyyyy("12/22/2003")));
  }

  @Test void convertingPropertyMethodsWithArgsToTypeOtherThanString()
    throws Exception {

    assertEquals(
      mmddyyyy("02/24/2010"),
      bound.datePropertyByMonthDayYear(2, 24, 2010));
  }

  @Test void applyingArgumentsOfPropertyMethodsToEachOfAnArrayType() {
    assertArrayEquals(
      new String[] {"foobar", "barfoo"},
      bound.stringArrayPropertyWithArguments("foo", "bar"));
  }

  @Test
  void applyingArgumentsOfPropertyMethodsToEachOfListTypeWithCustomSeparator()
    throws Exception {

    assertEquals(
      asList(mmddyyyy("03/23/2010"), mmddyyyy("03/23/2010")),
      bound.dateListPropertyWithArgumentsAndSeparator(3, 23, 2010));
  }

  @Override protected Class<ScalarPropertiesWithArgs> boundType() {
    return ScalarPropertiesWithArgs.class;
  }

  private static Date mmddyyyy(String raw) throws ParseException {
    return new SimpleDateFormat("MM/dd/yyyy").parse(raw);
  }
}
