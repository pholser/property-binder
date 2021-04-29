import com.pholser.util.properties.conversions.Conversion;
import com.pholser.util.properties.conversions.java.io.BigDecimalConversion;
import com.pholser.util.properties.conversions.java.io.BigIntegerConversion;
import com.pholser.util.properties.conversions.java.io.FileConversion;
import com.pholser.util.properties.conversions.java.lang.BooleanConversion;
import com.pholser.util.properties.conversions.java.lang.ByteConversion;
import com.pholser.util.properties.conversions.java.lang.CharacterConversion;
import com.pholser.util.properties.conversions.java.lang.ClassForNameConversion;
import com.pholser.util.properties.conversions.java.lang.DoubleConversion;
import com.pholser.util.properties.conversions.java.lang.FloatConversion;
import com.pholser.util.properties.conversions.java.lang.IntegerConversion;
import com.pholser.util.properties.conversions.java.lang.LongConversion;
import com.pholser.util.properties.conversions.java.lang.ShortConversion;
import com.pholser.util.properties.conversions.java.lang.StringConversion;
import com.pholser.util.properties.conversions.java.net.InetAddressByNameConversion;
import com.pholser.util.properties.conversions.java.net.URIConversion;
import com.pholser.util.properties.conversions.java.net.URLConversion;
import com.pholser.util.properties.conversions.java.nio.CharsetConversion;
import com.pholser.util.properties.conversions.java.util.RegexConversion;
import com.pholser.util.properties.conversions.java.util.SimpleDateFormatDateConversion;
import com.pholser.util.properties.conversions.java.util.UUIDConversion;

open module com.pholser.util.properties.propertybinder {
  exports com.pholser.util.properties;
//  exports com.pholser.util.properties.conversions;
  exports com.pholser.util.properties.conversions.java.io;
  exports com.pholser.util.properties.conversions.java.lang;
  exports com.pholser.util.properties.conversions.java.net;
  exports com.pholser.util.properties.conversions.java.nio;
  exports com.pholser.util.properties.conversions.java.util;

  requires transitive java.xml;
  requires org.slf4j;
  requires org.assertj.core;
  requires transitive org.junit.jupiter.api;
  requires transitive org.junit.jupiter.engine;

  uses Conversion;

  provides Conversion
    with BigDecimalConversion,
      BigIntegerConversion,
      FileConversion,
      BooleanConversion,
      ByteConversion,
      CharacterConversion,
      ClassForNameConversion,
      DoubleConversion,
      FloatConversion,
      IntegerConversion,
      LongConversion,
      ShortConversion,
      StringConversion,
      InetAddressByNameConversion,
      URIConversion,
      URLConversion,
      CharsetConversion,
      RegexConversion,
      SimpleDateFormatDateConversion,
      UUIDConversion;
}
