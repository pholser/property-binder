module com.pholser.util.properties.propertybinder {
  exports com.pholser.util.properties;
  exports com.pholser.util.properties.conversions;

  requires transitive com.google.common;
  requires org.slf4j;
  requires static com.github.spotbugs.annotations;

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
      com.pholser.util.properties.conversions.java.nio.file.attribute.PosixFilePermissionsConversion,
      com.pholser.util.properties.conversions.java.text.SimpleDateFormatConversion,
      com.pholser.util.properties.conversions.java.time.DateTimeFormatterBuilderConversion,
      com.pholser.util.properties.conversions.java.time.DateTimeFormatterClassConstantConversion,
      com.pholser.util.properties.conversions.java.time.DateTimeFormatterConversion,
      com.pholser.util.properties.conversions.java.time.DurationConversion,
      com.pholser.util.properties.conversions.java.time.InstantConversion,
      com.pholser.util.properties.conversions.java.time.LocalDateConversion,
      com.pholser.util.properties.conversions.java.time.LocalDateTimeConversion,
      com.pholser.util.properties.conversions.java.time.LocalTimeConversion,
      com.pholser.util.properties.conversions.java.time.MonthDayConversion,
      com.pholser.util.properties.conversions.java.time.OffsetDateTimeConversion,
      com.pholser.util.properties.conversions.java.time.OffsetTimeConversion,
      com.pholser.util.properties.conversions.java.time.PeriodConversion,
      com.pholser.util.properties.conversions.java.time.YearConversion,
      com.pholser.util.properties.conversions.java.time.YearMonthConversion,
      com.pholser.util.properties.conversions.java.time.ZonedDateTimeConversion,
      com.pholser.util.properties.conversions.java.time.ZoneIdConversion,
      com.pholser.util.properties.conversions.java.time.ZoneOffsetConversion,
      com.pholser.util.properties.conversions.java.util.CurrencyConversion,
      com.pholser.util.properties.conversions.java.util.SimpleDateFormatDateConversion,
      com.pholser.util.properties.conversions.java.util.UUIDConversion,
      com.pholser.util.properties.conversions.java.util.regex.RegexConversion;
}
