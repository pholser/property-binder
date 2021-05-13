module com.pholser.util.properties.propertybinder {
  exports com.pholser.util.properties;
  exports com.pholser.util.properties.conversions;

  requires org.slf4j;

  uses com.pholser.util.properties.conversions.Conversion;

  provides com.pholser.util.properties.conversions.Conversion
    with com.pholser.util.properties.conversions.java.io.FileConversion,
      com.pholser.util.properties.conversions.java.lang.BooleanConversion,
      com.pholser.util.properties.conversions.java.lang.ByteConversion,
      com.pholser.util.properties.conversions.java.lang.CharacterConversion,
      com.pholser.util.properties.conversions.java.lang.ClassForNameConversion,
      com.pholser.util.properties.conversions.java.lang.DoubleConversion,
      com.pholser.util.properties.conversions.java.lang.FloatConversion,
      com.pholser.util.properties.conversions.java.lang.IntegerConversion,
      com.pholser.util.properties.conversions.java.lang.LongConversion,
      com.pholser.util.properties.conversions.java.lang.ShortConversion,
      com.pholser.util.properties.conversions.java.lang.StringConversion,
      com.pholser.util.properties.conversions.java.math.BigDecimalConversion,
      com.pholser.util.properties.conversions.java.math.BigIntegerConversion,
      com.pholser.util.properties.conversions.java.net.InetAddressByNameConversion,
      com.pholser.util.properties.conversions.java.net.URIConversion,
      com.pholser.util.properties.conversions.java.net.URLConversion,
      com.pholser.util.properties.conversions.java.nio.CharsetConversion,
      com.pholser.util.properties.conversions.java.util.SimpleDateFormatDateConversion,
      com.pholser.util.properties.conversions.java.util.UUIDConversion,
      com.pholser.util.properties.conversions.java.util.regex.RegexConversion;
}
