package com.pholser.util.properties.boundtypes;

import com.pholser.util.properties.BoundProperty;

import java.util.Random;

public interface UnconvertibleScalar {
  @BoundProperty("unconvertible")
  Random unconvertible();
}
