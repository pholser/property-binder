module com.pholser.util.properties.propertybinder.modularuser {
  requires com.pholser.util.properties.propertybinder;

  exports com.pholser.util.properties.propertybinder.modularuser;

  provides com.pholser.util.properties.conversions.Conversion
    with com.pholser.util.properties.propertybinder.modularuser.SecretKeyConversion;
}