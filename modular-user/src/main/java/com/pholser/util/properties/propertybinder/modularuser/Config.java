/*
 The MIT License

 Copyright (c) 2009-2021 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.pholser.util.properties.propertybinder.modularuser;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ParsedAs;

import javax.crypto.SecretKey;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface Config {
  @BoundProperty("string.property")
  String stringProperty();

  String unannotatedProperty();

  @BoundProperty("string.property.with.default")
  @DefaultsTo("default")
  String stringPropertyWithDefault();

  @BoundProperty("primitive.boolean.property")
  boolean primitiveBooleanProperty();

  @BoundProperty("wrapped.boolean.property")
  Boolean wrappedBooleanProperty();

  @BoundProperty("primitive.boolean.property.with.default")
  @DefaultsTo("true")
  boolean primitiveBooleanPropertyWithDefault();

  @BoundProperty("wrapped.boolean.property.with.default")
  @DefaultsTo("false")
  Boolean wrappedBooleanPropertyWithDefault();

  @BoundProperty("primitive.byte.property")
  byte primitiveByteProperty();

  @BoundProperty("wrapped.byte.property")
  Byte wrappedByteProperty();

  @BoundProperty("primitive.byte.property.with.default")
  @DefaultsTo("23")
  byte primitiveBytePropertyWithDefault();

  @BoundProperty("wrapped.byte.property.with.default")
  @DefaultsTo("24")
  Byte wrappedBytePropertyWithDefault();

  @BoundProperty("primitive.character.property")
  char primitiveCharacterProperty();

  @BoundProperty("wrapped.character.property")
  Character wrappedCharacterProperty();

  @BoundProperty("primitive.character.property.with.default")
  @DefaultsTo("a")
  char primitiveCharacterPropertyWithDefault();

  @BoundProperty("wrapped.character.property.with.default")
  @DefaultsTo("b")
  Character wrappedCharacterPropertyWithDefault();

  @BoundProperty("primitive.double.property")
  double primitiveDoubleProperty();

  @BoundProperty("wrapped.double.property")
  Double wrappedDoubleProperty();

  @BoundProperty("primitive.double.property.with.default")
  @DefaultsTo("1.0")
  double primitiveDoublePropertyWithDefault();

  @BoundProperty("wrapped.double.property.with.default")
  @DefaultsTo("2.0")
  Double wrappedDoublePropertyWithDefault();

  @BoundProperty("primitive.float.property")
  float primitiveFloatProperty();

  @BoundProperty("wrapped.float.property")
  Float wrappedFloatProperty();

  @BoundProperty("primitive.float.property.with.default")
  @DefaultsTo("3.0")
  float primitiveFloatPropertyWithDefault();

  @BoundProperty("wrapped.float.property.with.default")
  @DefaultsTo("4.0")
  Float wrappedFloatPropertyWithDefault();

  @BoundProperty("primitive.integer.property")
  int primitiveIntegerProperty();

  @BoundProperty("wrapped.integer.property")
  Integer wrappedIntegerProperty();

  @BoundProperty("primitive.integer.property.with.default")
  @DefaultsTo("-1")
  int primitiveIntegerPropertyWithDefault();

  @BoundProperty("wrapped.integer.property.with.default")
  @DefaultsTo("-2")
  Integer wrappedIntegerPropertyWithDefault();

  @BoundProperty("primitive.integer.property.with.referential.default")
  @DefaultsTo(valueOf = "[integer.property]")
  int primitiveIntegerPropertyWithReferentialDefault();

  @BoundProperty("primitive.long.property")
  long primitiveLongProperty();

  @BoundProperty("wrapped.long.property")
  Long wrappedLongProperty();

  @BoundProperty("primitive.long.property.with.default")
  @DefaultsTo("7")
  long primitiveLongPropertyWithDefault();

  @BoundProperty("wrapped.long.property.with.default")
  @DefaultsTo("8")
  Long wrappedLongPropertyWithDefault();

  @BoundProperty("primitive.short.property")
  short primitiveShortProperty();

  @BoundProperty("wrapped.short.property")
  Short wrappedShortProperty();

  @BoundProperty("primitive.short.property.with.default")
  @DefaultsTo("9")
  short primitiveShortPropertyWithDefault();

  @BoundProperty("wrapped.short.property.with.default")
  @DefaultsTo("10")
  Short wrappedShortPropertyWithDefault();

  @BoundProperty("big.integer.property")
  BigInteger bigIntegerProperty();

  @BoundProperty("big.integer.property.with.default")
  @DefaultsTo("12345")
  BigInteger bigIntegerPropertyWithDefault();

  @BoundProperty("big.decimal.property")
  BigDecimal bigDecimalProperty();

  @BoundProperty("big.decimal.property.with.default")
  @DefaultsTo("6789.012")
  BigDecimal bigDecimalPropertyWithDefault();

  @BoundProperty("enum.property")
  Ternary enumProperty();

  @BoundProperty("enum.property.with.default")
  @DefaultsTo("YES")
  Ternary enumPropertyWithDefault();

  @BoundProperty("missing.property")
  String missingProperty();

  @BoundProperty("missing.property.opt")
  Optional<String> missingPropertyOpt();

  @BoundProperty("missing.date.property")
  @ParsedAs("yyyy-MM-dd")
  Date missingDateProperty();

  @BoundProperty("missing.date.property.opt")
  @ParsedAs("yyyy-MM-dd")
  @DefaultsTo("2021-11-28")
  Optional<Date> missingDatePropertyOpt();

  @BoundProperty("missing.primitive.wrapper.property")
  Integer missingPrimitiveWrapperProperty();

  @BoundProperty("date.property.with.parse.patterns")
  @ParsedAs("yyyy")
  Date datePropertyWithParsePatterns();

  @BoundProperty("date.property.with.default.with.parse.patterns")
  @ParsedAs(valueOf = "yy[date.property.two.digit.year]")
  @DefaultsTo("2003")
  Date datePropertyWithDefaultWithParsePatterns();

  @BoundProperty("uuid.property")
  UUID uuidProperty();

  @BoundProperty("missing.uuid.property")
  UUID missingUuidProperty();

  @BoundProperty("optional.uuid.property")
  Optional<UUID> optionalUuidProperty();

  @BoundProperty("missing.optional.uuid.property")
  Optional<UUID> missingOptionalUuidProperty();

  @BoundProperty("uuid.property.with.default")
  @DefaultsTo("dddeec43-63c8-4513-8cdf-a9b4e49e479a")
  UUID uuidPropertyWithDefault();

  @BoundProperty("missing.uuid.property.with.default")
  @DefaultsTo("c30377fa-7050-4a84-9ce5-007826101485")
  UUID missingUuidPropertyWithDefault();

  @BoundProperty("optional.uuid.property.with.default")
  @DefaultsTo("65fd2d5c-9807-47bd-bcd2-deeae0eda574")
  Optional<UUID> optionalUuidPropertyWithDefault();

  @BoundProperty("missing.optional.uuid.property.with.default")
  @DefaultsTo("e1eb72d7-31bb-41a1-9d95-63130c613874")
  Optional<UUID> missingOptionalUuidPropertyWithDefault();

  @BoundProperty("with.substitution")
  String substituted();

  @BoundProperty(
    value = "need.to.suppress.substitution",
    suppressSubstitution = true)
  String regex();

  @BoundProperty(value = "file.permissions")
  Set<PosixFilePermission> filePermissions();

  @BoundProperty("aes.key")
  @ParsedAs("AES")
  SecretKey aesKey();

  @BoundProperty("static.methods.not.bound")
  static String staticMethodsNotBound() {
    return "static methods not bound";
  }

  @BoundProperty("private.methods.not.bound")
  private String privateMethodsNotBound() {
    return "private methods not bound";
  }
}
