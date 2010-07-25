package com.pholser.util.properties.boundtypes;

import java.util.Date;

import com.pholser.util.properties.BoundProperty;

public interface ScalarPropertyWithArgsHaver {
    @BoundProperty("string.property.with.arguments")
    String stringPropertyWithArguments(String first, String second);

    @BoundProperty("string.property.with.typed.arguments")
    String stringPropertyWithTypedArguments(int quantity, Date time);

    @BoundProperty("string.property.with.ill.typed.arguments")
    String stringPropertyWithIllTypedArguments(int quantity, Date time);
}
