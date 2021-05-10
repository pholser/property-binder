package com.pholser.util.properties.propertybinder.modularuser;

import com.pholser.util.properties.conversions.Conversion;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;

public class SecretKeyConversion extends Conversion<SecretKey> {
  public SecretKeyConversion() {
    super(SecretKey.class);
  }

  @Override public SecretKey convert(String value, List<String> patterns) {
    byte[] keyBytes = Base64.getDecoder().decode(value);
    return new SecretKeySpec(keyBytes, patterns.get(0));
  }
}
