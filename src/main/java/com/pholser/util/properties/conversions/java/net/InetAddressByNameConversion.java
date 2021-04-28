package com.pholser.util.properties.conversions.java.net;

import com.pholser.util.properties.Conversion;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class InetAddressByNameConversion extends Conversion<InetAddress> {
  public InetAddressByNameConversion() {
    super(InetAddress.class);
  }

  @Override public InetAddress convert(String value, List<String> patterns) {
    try {
      return InetAddress.getByName(value);
    } catch (UnknownHostException | SecurityException ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}
