package com.pholser.util.properties.boundtypes;

import java.util.Date;
import java.util.List;

import com.pholser.util.properties.ValuesSeparatedBy;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.ParsedAs;

public interface ScalarPropertyWithArgsHaver {
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
    List<Date> dateListPropertyWithArgumentsAndSeparator(int month, int day, int year);
}
