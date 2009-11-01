package com.pholser.util.properties.examples;

import java.util.List;

import com.pholser.util.properties.BoundProperty;

public interface ExamplePICA {
    String unadorned();

    @BoundProperty( "unconverted.property" )
    String annotated();

    int intProperty();

    Long wrappedLongProperty();

    char[] charArrayProperty();

    List<Character> charListProperty();
}
