package com.pholser.util.properties.conversions.java.util;

import com.pholser.util.properties.conversions.Conversion;

import java.util.Currency;
import java.util.List;

public class CurrencyConversion extends Conversion<Currency> {
  public CurrencyConversion() {
    super(Currency.class);
  }

  @Override public Currency convert(String value, List<String> patterns) {
    return Currency.getInstance(value);
  }
}
