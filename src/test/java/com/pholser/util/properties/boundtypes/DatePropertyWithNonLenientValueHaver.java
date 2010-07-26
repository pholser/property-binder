package com.pholser.util.properties.boundtypes;

import java.util.Date;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.ParsedAs;

public interface DatePropertyWithNonLenientValueHaver {
    @BoundProperty("date.property.with.non.lenient.value")
    @ParsedAs({"MM/dd/yyyy"})
    Date datePropertyWithNonLenientValue();
}
