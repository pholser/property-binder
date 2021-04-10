package com.pholser.util.properties.examples;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ParsedAs;
import com.pholser.util.properties.ValuesSeparatedBy;
import com.pholser.util.properties.boundtypes.Ternary;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ExampleSchema {
  String unadorned();

  @BoundProperty("unconverted.property")
  String annotated();

  int intProperty();

  Long wrappedLongProperty();

  char[] charArrayProperty();

  List<Character> charListProperty();

  @ValuesSeparatedBy(pattern = "\\s*,\\s*")
  List<Ternary> listOfEnumsWithSeparator();

  @DefaultsTo(value = "10")
  BigDecimal bigDecimalPropertyWithDefault();

  @BoundProperty("date.property")
  @ParsedAs({"MM/dd/yyyy", "yyyy-MM-dd"})
  Date dateProperty();

  String argsProperty(int quantity, Date time);
}
