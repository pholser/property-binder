package com.pholser.util.properties.conversions.java.util;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;
import com.pholser.util.properties.internal.exceptions.ValueConversionException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Arrays.asList;

public class SimpleDateFormatDateConversion extends Conversion<Date> {
  public SimpleDateFormatDateConversion() {
    super(Date.class);
  }

  @Override public Date convert(String formatted, ParsedAs patterns) {
    if (patterns == null) {
      throw new ValueConversionException(
        "Could not parse value [" + formatted
          + "], need to specify @" + ParsedAs.class.getSimpleName());
    }

    for (String each : patterns.value()) {
      try {
        DateFormat formatter = new SimpleDateFormat(each);
        formatter.setLenient(false);
        return formatter.parse(formatted);
      } catch (ParseException ex) {
        // try the next pattern
      }
    }

    throw new ValueConversionException(
      "Could not parse value [" + formatted
        + "] using any of the patterns: " + asList(patterns.value()));
  }
}
