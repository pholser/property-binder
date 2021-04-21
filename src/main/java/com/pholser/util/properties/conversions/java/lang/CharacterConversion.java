package com.pholser.util.properties.conversions.java.lang;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;
import com.pholser.util.properties.internal.exceptions.ValueConversionException;

import static java.util.Arrays.asList;

public class CharacterConversion extends Conversion<Character> {
  public CharacterConversion() {
    super(asList(char.class, Character.class));
  }

  @Override public Character convert(String formatted, ParsedAs patterns) {
    if (formatted.length() != 1) {
      throw new ValueConversionException(
        "cannot convert [" + formatted + "] to " + Character.class);
    }

    return formatted.charAt(0);
  }
}
