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

package com.pholser.util.properties.it.boundtypes;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.ParsedAs;
import com.pholser.util.properties.ValuesSeparatedBy;

import java.util.Date;
import java.util.List;

public interface ScalarPropertiesWithArgs {
  @BoundProperty("string.property.with.arguments")
  String stringPropertyWithArguments(String first, String second);

  @BoundProperty("string.property.with.typed.arguments")
  String stringPropertyWithTypedArguments(int quantity, Date time);

  @BoundProperty("string.property.with.ill.typed.arguments")
  String stringPropertyWithIllTypedArguments(int quantity, Date time);

  @BoundProperty("date.property.by.month.day.year")
  @ParsedAs("MM/dd/yyyy")
  Date datePropertyByMonthDayYear(int month, int day, int year);

  @BoundProperty("string.array.property.with.arguments")
  String[] stringArrayPropertyWithArguments(String first, String second);

  @BoundProperty("date.list.property.with.arguments.and.separator")
  @ValuesSeparatedBy(pattern = "\\.\\.\\.")
  @ParsedAs({"MM/dd/yyyy", "yyyy-MM-dd"})
  List<Date> dateListPropertyWithArgumentsAndSeparator(
    int month,
    int day,
    int year);
}
