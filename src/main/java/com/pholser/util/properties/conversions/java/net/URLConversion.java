package com.pholser.util.properties.conversions.java.net;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import java.net.MalformedURLException;
import java.net.URL;

public class URLConversion extends Conversion<URL> {
  public URLConversion() {
    super(URL.class);
  }

  @Override public URL convert(String formatted, ParsedAs patterns) {
    try {
      return new URL(formatted);
    } catch (MalformedURLException ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}
