package com.pholser.util.properties.conversions.java.nio.file.attribute;

import com.google.common.reflect.TypeToken;
import com.pholser.util.properties.conversions.Conversion;

import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;

public class PosixFilePermissionsConversion
  extends Conversion<Set<PosixFilePermission>> {

  public PosixFilePermissionsConversion() {
    super(new TypeToken<Set<PosixFilePermission>>() {});
  }

  @Override public Set<PosixFilePermission> convert(
    String value,
    List<String> patterns) {

    return PosixFilePermissions.fromString(value);
  }
}
