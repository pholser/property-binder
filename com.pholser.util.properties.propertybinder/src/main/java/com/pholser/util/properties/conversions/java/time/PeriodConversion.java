package com.pholser.util.properties.conversions.java.time;

import com.pholser.util.properties.conversions.Conversion;

import java.time.Period;
import java.util.List;

public class PeriodConversion extends Conversion<Period> {
  public PeriodConversion() {
    super(Period.class);
  }

  @Override public Period convert(String value, List<String> patterns) {
    return Period.parse(value);
  }
}
