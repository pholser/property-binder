package com.pholser.util.properties.examples;

import java.math.BigDecimal;
import java.util.List;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ValuesSeparatedBy;
import com.pholser.util.properties.boundtypes.Ternary;

public interface ExamplePICA {
    String unadorned();

    @BoundProperty( "unconverted.property" )
    String annotated();

    int intProperty();

    Long wrappedLongProperty();

    char[] charArrayProperty();

    List<Character> charListProperty();

    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    List<Ternary> listOfEnumsWithSeparator();

    @DefaultsTo( value = "10" )
    BigDecimal bigDecimalPropertyWithDefault();
}
