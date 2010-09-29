package com.pholser.util.properties.boundtypes;

import com.pholser.util.properties.BoundProperty;

public interface IntPropertyHaver {
    @BoundProperty("i")
    int i();
}